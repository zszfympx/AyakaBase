package com.zszf.ayakabase.mixin;

import com.zszf.ayakabase.Ayakabase;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Mixin(ClientPlayerEntity.class)
public class MixinClientPlayerEntity {
    @Inject(method = "sendMessage(Lnet/minecraft/text/Text;)V", at = @At("HEAD"), cancellable = true)
    public void hookCommandExecute(Text message, CallbackInfo ci) {
        if (message.getString().startsWith(".")) {
            var msg = message.getString().substring(1).split(" ");
            List<String> params = new ArrayList<>(Arrays.asList(msg).subList(1, msg.length));
            Ayakabase.getInstance().commandManager.callCommand(msg[0], params);
            ci.cancel();
        }
    }
}
