package com.zszf.ayakabase;

import com.zszf.ayakabase.events.EventManager;
import com.zszf.ayakabase.modules.ModuleManager;
import com.zszf.ayakabase.utils.Const;
import lombok.Getter;
import net.fabricmc.api.ModInitializer;
import net.minecraft.client.MinecraftClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Ayakabase implements ModInitializer {

    public Logger logger = LoggerFactory.getLogger(Const.MOD_ID);

    public static MinecraftClient mc = MinecraftClient.getInstance();

    @Getter
    public static Ayakabase instance;

    public ModuleManager moduleManager;

    public EventManager eventManager;

    @Override
    public void onInitialize() {
        logger.info("Initializing Ayakabase");
        moduleManager = new ModuleManager();
        moduleManager.init();
        eventManager = new EventManager();
        instance = this;
    }

}
