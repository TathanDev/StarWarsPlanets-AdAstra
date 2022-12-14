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
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
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
        pTarget.setSecondsOnFire(6);

        Random rand = new Random();

        int random_sound = rand.nextInt(4);

        if(random_sound == 0) {
            pAttacker.playSound(SoundsRegistry.SABER_ATTACK_SOUND_1.get(), 1f, 1f);
        }  else if (random_sound == 1){
            pAttacker.playSound(SoundsRegistry.SABER_ATTACK_SOUND_2.get(), 1f, 1f);
        }    else if (random_sound == 2){
            pAttacker.playSound(SoundsRegistry.SABER_ATTACK_SOUND_3.get(), 1f, 1f);
        }  else if (random_sound == 3){
            pAttacker.playSound(SoundsRegistry.SABER_ATTACK_SOUND_4.get(), 1f, 1f);
        }
        return super.hurtEnemy(pStack, pTarget, pAttacker);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(Component.translatable("tooltip.swplanets.sithlightsaber.tooltip.shift"));
        pTooltipComponents.add(Component.translatable("tooltip.swplanets.sithlightsaber.tooltip.shift.two"));

    }

}