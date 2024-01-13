package fr.tathan.swplanets.common.items;

import fr.tathan.swplanets.common.registry.SoundsRegistry;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

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