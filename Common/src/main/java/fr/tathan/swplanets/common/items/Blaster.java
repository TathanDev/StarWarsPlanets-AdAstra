package fr.tathan.swplanets.common.items;


import fr.tathan.swplanets.Constants;
import fr.tathan.swplanets.common.entity.LaserEntity;
import fr.tathan.swplanets.common.registry.ItemsRegistry;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;

import java.util.List;


public class Blaster extends TieredItem {

    public int distance = 50;
    public boolean zoom = false;

    public Blaster(Properties properties) {
        super(StarWarsTiers.PLASTIC, properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand handIn) {
        player.startUsingItem(handIn);
        return InteractionResultHolder.consume(player.getItemInHand(handIn));

    }

    @Override
    public void releaseUsing(ItemStack stack, Level level, LivingEntity entity, int $$3) {
        if (!level.isClientSide) {

            LaserEntity laser = new LaserEntity(level, entity, 0, 0, 0, this.distance, entity.getName().getString());
            laser.setPos(entity.getX(), entity.getY() + 1.5, entity.getZ());
            laser.setOwner(entity);
            laser.shootFromRotation(entity, entity.getXRot(), entity.getYRot(), 0.0F, 3.0F, 1.0F);
            laser.setItem(ItemsRegistry.LASER_ITEM.get().getDefaultInstance());
            level.addFreshEntity(laser);
        }

    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        if (this.zoom) {
            return UseAnim.SPYGLASS;
        } else {
            return UseAnim.BOW;
        }
    }

    @Override
    public int getUseDuration(ItemStack p_40680_) {
        return 200;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level $$1, List<Component> components, TooltipFlag $$3) {

        if(Screen.hasShiftDown()) {
            components.add(Component.translatable("tooltip.swplanets.blaster.zoom.shift", this.zoom));
            components.add(Component.translatable("tooltip.swplanets.blaster.lifetime.shift", getDistance()));
        } else {
            components.add(Component.translatable("tooltip.swplanets.shift"));

        }
        super.appendHoverText(stack, $$1, components, $$3);
    }


    public boolean getZoom() {
        return this.zoom;
    }

    public int getDistance() {
        return this.distance;
    }

    public void setUpgrade(BlasterUpgrade upgradeItem) {

        if (upgradeItem.getLifeTime() > 30) {
            this.distance = upgradeItem.getLifeTime();
        }

        this.zoom = upgradeItem.getZoom();

        this.getDefaultInstance().getOrCreateTag().putInt("distance", this.distance);
        this.getDefaultInstance().getOrCreateTag().putBoolean("zoom", this.zoom);
    }
}
