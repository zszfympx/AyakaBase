package com.zszf.ayakabase.modules;

import com.zszf.ayakabase.modules.impl.misc.AutoGG;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ModuleManager {
    public static List<Module> modules = new ArrayList<>();

    public static void add(Module module) {
        modules.add(module);
    }

    public void init(){
        add(new AutoGG());
    }

    public Module getModule(String name) {
        for (Module module : modules) {
            if(Objects.equals(module.name, name)){
                return module;
            }
        }
        return null;
    }

    public Module getModule(Class<? extends Module> module) {
        for(Module m : modules){
            if(Objects.equals(m.name, module.getName())){
                return m;
            }
        }
        return null;
    }

}
