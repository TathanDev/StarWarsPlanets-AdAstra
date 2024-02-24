package fr.tathan.swplanets.mixin;

import com.teamresourceful.resourcefullib.common.utils.modinfo.ModInfoUtils;
import fr.tathan.swplanets.common.platform.Services;
import fr.tathan.swplanets.common.registry.SoundsRegistry;
import fr.tathan.swplanets.common.registry.TagsRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(Minecraft.class)
public class PlayerAttackMixin {

    @Inject(method = "startAttack", at = @At("HEAD"))
    private void startAttack(CallbackInfoReturnable<Boolean> cir) {
        Minecraft minecraft = (Minecraft) ((Object) this);
        if (!ModInfoUtils.isModLoaded("bettercombat")) {

            ItemStack stack = minecraft.player.getItemInHand(InteractionHand.MAIN_HAND);
            if(stack.is(TagsRegistry.LIGHT_SABERS)) {
                minecraft.player.playSound(getSwingSound(), 1.0F, 1.0F);
            }
        }
    }

    public SoundEvent getSwingSound() {

        Random random = new Random();
        int sound = random.nextInt(2);

        switch (sound) {
            case 0:
                return SoundsRegistry.LIGHT_SABER_SOUND_1.get();
            case 1:
                return SoundsRegistry.LIGHT_SABER_SOUND_2.get();
            case 2:
                return SoundsRegistry.LIGHT_SABER_SOUND_3.get();
        }
        return SoundsRegistry.LIGHT_SABER_SOUND_1.get();
    }
}
