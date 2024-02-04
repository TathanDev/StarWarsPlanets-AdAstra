package fr.tathan.swplanets.common.items;


import fr.tathan.swplanets.common.registry.SoundsRegistry;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class JediLightSaber extends SwordItem {

    public JediLightSaber(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
    }

    @Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        pTarget.setSecondsOnFire(6);
        if (!pAttacker.level().isClientSide) {
            pAttacker.level().playSeededSound(null, pAttacker.getX(), pAttacker.getY(), pAttacker.getZ(), SoundsRegistry.SABER_ATTACK_SOUND.get(), SoundSource.PLAYERS, 1.0F, 1.0F, 0);
        }

        return super.hurtEnemy(pStack, pTarget, pAttacker);

    }
    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(Component.translatable("tooltip.swplanets.jedilightsaber.tooltip.shift"));
        pTooltipComponents.add(Component.translatable("tooltip.swplanets.jedilightsaber.tooltip.shift.two"));

    }

}