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

    public int lifetime = 50;
    public boolean zoom = false;

    public Blaster(Properties properties) {
        super(StarWarsTiers.PLASTIC, properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand handIn) {

        if (!level.isClientSide) {

            LaserEntity laser = new LaserEntity(level, player, 0, 0, 0, this.getLifetime(), player.getName().getString());
            laser.setPos(player.getX(), player.getY() + 1.5, player.getZ());
            laser.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 1.0F);
            laser.setItem(ItemsRegistry.LASER_ITEM.get().getDefaultInstance());
            level.addFreshEntity(laser);

        }
        return ItemUtils.startUsingInstantly(level, player, handIn);

    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        if (this.zoom) {
            return UseAnim.SPYGLASS;
        }
        return super.getUseAnimation(stack);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level $$1, List<Component> components, TooltipFlag $$3) {

        if(Screen.hasShiftDown()) {
            components.add(Component.translatable("tooltip.swplanets.blaster.zoom.shift", this.zoom));
            components.add(Component.translatable("tooltip.swplanets.blaster.lifetime.shift", getLifetime()));
        } else {
            components.add(Component.translatable("tooltip.swplanets.shift", getLifetime()));

        }
        super.appendHoverText(stack, $$1, components, $$3);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack $$0, Level $$1, LivingEntity $$2) {
        this.stopUsing($$2);
        return $$0;
    }

    @Override
    public void releaseUsing(ItemStack $$0, Level $$1, LivingEntity $$2, int $$3) {
        this.stopUsing($$2);
    }
    @Override
    public int getUseDuration(ItemStack stack) {
        return 1200;
    }

    private void stopUsing(LivingEntity $$0) {
        $$0.playSound(SoundEvents.SPYGLASS_STOP_USING, 1.0F, 1.0F);
    }

    public boolean getZoom() {
        return this.zoom;
    }

    public int getLifetime() {
        return this.lifetime;
    }

    public void setUpgrade(BlasterUpgrade upgradeItem) {

        if (upgradeItem.getLifeTime() > 30) {
            this.lifetime = upgradeItem.getLifeTime();
        }

        this.zoom = upgradeItem.getZoom();

        this.getDefaultInstance().getOrCreateTag().putInt("lifetime", this.lifetime);
        this.getDefaultInstance().getOrCreateTag().putBoolean("zoom", this.zoom);
    }
}
