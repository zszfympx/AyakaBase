package com.zszf.ayakabase.mixin;

import com.zszf.ayakabase.Ayakabase;
import com.zszf.ayakabase.events.events.PostUpdateEvent;
import com.zszf.ayakabase.events.events.PreUpdateEvent;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class MixinMinecraftClient {
    @Inject(method = "tick", at = @At("HEAD"))
    public void hookPreUpdateEvent(CallbackInfo ci) {
        Ayakabase.instance.eventManager.call(new PreUpdateEvent());
    }

    @Inject(method = "tick", at = @At("TAIL"))
    public void hookPostUpdateEvent(CallbackInfo ci) {
        Ayakabase.instance.eventManager.call(new PostUpdateEvent());
    }
}
