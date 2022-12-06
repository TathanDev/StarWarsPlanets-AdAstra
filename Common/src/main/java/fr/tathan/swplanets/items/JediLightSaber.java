package fr.tathan.swplanets.items;


import fr.tathan.swplanets.registry.SoundsRegistry;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class JediLightSaber extends SwordItem {



    public JediLightSaber(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);


    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {

        Random rand = new Random();

        int random_sound = rand.nextInt(4);

        if(random_sound == 0) {
            player.playSound(SoundsRegistry.SABER_ATTACK_SOUND_1.get(), 1f, 1f);
        }  else if (random_sound == 1){
            player.playSound(SoundsRegistry.SABER_ATTACK_SOUND_2.get(), 1f, 1f);
        }    else if (random_sound == 2){
            player.playSound(SoundsRegistry.SABER_ATTACK_SOUND_3.get(), 1f, 1f);
        }  else if (random_sound == 3){
            player.playSound(SoundsRegistry.SABER_ATTACK_SOUND_4.get(), 1f, 1f);
        }

        return InteractionResultHolder.pass(player.getItemInHand(hand));
    }

    @Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        pTarget.setSecondsOnFire(6);
        return false;
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(Component.translatable("tooltip.swplanets.jedilightsaber.tooltip.shift"));
        pTooltipComponents.add(Component.translatable("tooltip.swplanets.jedilightsaber.tooltip.shift.two"));

    }

}