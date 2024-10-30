package com.zszf.ayakabase.annotations;

import com.zszf.ayakabase.modules.ModuleType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ModuleInfo {
    String name();
    String description();
    ModuleType type();
    int keyBinding() default -1;
    boolean autoEnabled() default false;
    boolean visible() default true;
}
