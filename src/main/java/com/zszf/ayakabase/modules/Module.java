package com.zszf.ayakabase.modules;

import com.zszf.ayakabase.annotations.ModuleInfo;
import net.minecraft.client.MinecraftClient;

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
    }

    public Module(ModuleInfo info){
        if(info!=null) {
            name = info.name();
            enabled = info.autoEnabled();
            type = info.type();
            keyBinding = info.keyBinding();
            visible = info.visible();
        }
    }

    public void toggle(){
        if(enabled){
            enabled = false;
            // TODO: unregister events
            onDisable();
        }else{
            enabled = true;
            // TODO: register events
            onEnable();
        }
    }

    public void onEnable(){

    }

    public void onDisable(){

    }

}
