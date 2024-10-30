package com.zszf.ayakabase.modules;

import com.zszf.ayakabase.Ayakabase;
import com.zszf.ayakabase.annotations.ModuleInfo;

public abstract class Module {
    protected String name;
    protected boolean enabled;
    protected ModuleType type;
    protected int keyBinding;
    protected boolean visible;

    public Module(){
        Class clazz = this.getClass();
        for(var i:clazz.getAnnotations()){
            if(i instanceof ModuleInfo v){
                name = v.name();
                enabled = v.autoEnabled();
                type = v.type();
                keyBinding = v.keyBinding();
                visible = v.visible();
            }
        }
        if (enabled) {
            Ayakabase.getInstance().eventManager.register(this);
        }
    }

    public Module(ModuleInfo info){
        if(info!=null) {
            name = info.name();
            enabled = info.autoEnabled();
            type = info.type();
            keyBinding = info.keyBinding();
            visible = info.visible();
        }
        if (enabled) {
            Ayakabase.getInstance().eventManager.register(this);
        }
    }

    public void toggle(){
        if(enabled){
            enabled = false;
            Ayakabase.getInstance().eventManager.unregister(this);
            onDisable();
        }else{
            enabled = true;
            Ayakabase.getInstance().eventManager.register(this);
            onEnable();
        }
    }

    public void onEnable(){

    }

    public void onDisable(){

    }

}
