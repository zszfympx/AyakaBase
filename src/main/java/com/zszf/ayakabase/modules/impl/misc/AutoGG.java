package com.zszf.ayakabase.modules.impl.misc;

import com.zszf.ayakabase.modules.Module;
import net.minecraft.text.Text;

import static com.zszf.ayakabase.Ayakabase.mc;

public class AutoGG extends Module{
    @Override
    public void onEnable(){
        mc.inGameHud.getChatHud().addMessage(Text.of("GG"));
    }
}
