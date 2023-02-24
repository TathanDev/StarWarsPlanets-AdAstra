package fr.tathan.swplanets.items;

import fr.tathan.swplanets.Constants;
import fr.tathan.swplanets.registry.SoundsRegistry;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CarpetBlock;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SithLightSaber extends SwordItem {

    public SithLightSaber(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);

    }

    @Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {


        if (pAttacker instanceof Player player) {
         player.playSound(SoundsRegistry.SABER_ATTACK_SOUND_1.get(), 1.0f, 1.0f);
         }
        return super.hurtEnemy(pStack, pTarget, pAttacker);

    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(Component.translatable("tooltip.swplanets.sithlightsaber.tooltip.shift"));
        pTooltipComponents.add(Component.translatable("tooltip.swplanets.sithlightsaber.tooltip.shift.two"));

    }

}