package fr.tathan.swplanets.common.items;

import fr.tathan.swplanets.common.entities.LaserEntity;
import fr.tathan.swplanets.common.registry.ItemsRegistry;
import fr.tathan.swplanets.common.registry.SoundsRegistry;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;
public class Blaster extends TieredItem {


    public boolean zoom = false;

    public Blaster(Properties properties) {
        super(StarWarsTiers.PLASTIC, properties);
    }

    public Blaster(Properties properties, boolean zoom) {
        super(StarWarsTiers.PLASTIC, properties);
        this.zoom = zoom;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand handIn) {
        player.startUsingItem(handIn);
        return InteractionResultHolder.consume(player.getItemInHand(handIn));

    }

    @Override
    public void releaseUsing(ItemStack stack, Level level, LivingEntity entity, int $$3) {
        if (!level.isClientSide) {
            level.playSeededSound(null, entity.getX(), entity.getY(), entity.getZ(), SoundsRegistry.BLASTER_SOUND.get(), SoundSource.PLAYERS, 1.0F, 1.0F, 0);
            LaserEntity laser = new LaserEntity(level, entity, 0, 0, 0, 60, entity.getName().getString());
            laser.setPos(entity.getX(), entity.getY() + 1.5, entity.getZ());
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

        } else {
            components.add(Component.translatable("tooltip.swplanets.shift"));

        }
        super.appendHoverText(stack, $$1, components, $$3);
    }


    public boolean getZoom() {
        return this.zoom;
    }


    public void setUpgrade(BlasterUpgrade upgradeItem) {

        this.zoom = upgradeItem.getZoom();
        this.getDefaultInstance().getOrCreateTagElement("upgrade").putBoolean("zoom", this.zoom);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack $$0, Level level, LivingEntity $$2) {

        return $$0;
    }

}
