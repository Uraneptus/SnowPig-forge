package com.uraneptus.snowpig.mixins;

import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Pig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntityRenderer.class)
public class SPLivingEntityRendererMixin {

    @Inject(method = "isShaking(Lnet/minecraft/world/entity/LivingEntity;)Z", at = @At("HEAD"), cancellable = true)
    private void snowpig_isShaking(LivingEntity entity, CallbackInfoReturnable<Boolean> cir) {
        if (entity instanceof Pig pig) {
            if (pig.isFullyFrozen()) {
                cir.setReturnValue(true);
            }
        }
    }
}
