package com.zszf.ayakabase.events;

import com.zszf.ayakabase.annotations.EventPriority;
import com.zszf.ayakabase.annotations.EventTarget;
import com.zszf.ayakabase.events.impl.Event;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class EventManager {
    private final Map<Method, Class<?>> registeredMethodMap;
    private final Map<Method, Object> methodObjectMap;
    private final Map<Class<? extends Event>, List<Method>> priorityMethodMap;


    public EventManager() {
        this.registeredMethodMap = new ConcurrentHashMap<>();
        this.methodObjectMap = new ConcurrentHashMap<>();
        this.priorityMethodMap = new ConcurrentHashMap<>();
    }

    public void register(Object obj) {
        Class<?> clazz = obj.getClass();
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            Annotation[] annotations = method.getAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation.annotationType() == EventTarget.class) {
                    registeredMethodMap.put(method, method.getParameterTypes()[0]);
                    methodObjectMap.put(method, obj);
                    Class<? extends Event> eventClass = method.getParameterTypes()[0].asSubclass(Event.class);
                    priorityMethodMap.computeIfAbsent(eventClass, k -> new CopyOnWriteArrayList<>()).add(method);
                }
            }
        }
    }

    public void unregister(Object obj) {
        Class<?> clazz = obj.getClass();
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            Annotation[] annotations = method.getAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation.annotationType() == EventTarget.class) {
                    registeredMethodMap.remove(method);
                    methodObjectMap.remove(method);
                    Class<? extends Event> eventClass = method.getParameterTypes()[0].asSubclass(Event.class);
                    priorityMethodMap.remove(eventClass);
                }
            }
        }
    }

    public Event call(Event event) {
        Class<? extends Event> eventClass = event.getClass();
        List<Method> methods = priorityMethodMap.get(eventClass);
        if (methods != null) {
            methods.sort(Comparator.comparing(method -> {
                EventPriority priority = method.getAnnotation(EventPriority.class);
                return priority.value();
            }));
            for (Method method : methods) {
                Object obj = methodObjectMap.get(method);
                method.setAccessible(true);
                try {
                    method.invoke(obj, event);
                } catch (InvocationTargetException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return event;
    }
}
