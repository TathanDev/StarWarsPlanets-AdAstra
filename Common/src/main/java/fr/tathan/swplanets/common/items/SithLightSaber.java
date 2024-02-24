package fr.tathan.swplanets.common.items;

import fr.tathan.swplanets.common.registry.SoundsRegistry;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Random;

public class SithLightSaber extends SwordItem {

    public SithLightSaber(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);

    }

    @Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {


        if (!pAttacker.level().isClientSide) {
            pAttacker.level().playSeededSound(null, pAttacker.getX(), pAttacker.getY(), pAttacker.getZ(), SoundsRegistry.SABER_ATTACK_SOUND.get(), SoundSource.PLAYERS, 1.0F, 1.0F, 0);
        }
        return super.hurtEnemy(pStack, pTarget, pAttacker);

    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(Component.translatable("tooltip.swplanets.sithlightsaber.tooltip.shift"));
        pTooltipComponents.add(Component.translatable("tooltip.swplanets.sithlightsaber.tooltip.shift.two"));
    }

    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        player.level().playSeededSound(null, player.getX(), player.getY(), player.getZ(), getSwingSound(), SoundSource.PLAYERS, 1.0F, 1.0F, 0);
        return super.use(level, player, hand);
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