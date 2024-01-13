package fr.tathan.swplanets.common.items;


import fr.tathan.swplanets.common.entity.LaserEntity;
import fr.tathan.swplanets.common.registry.ItemsRegistry;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;


public class Blaster extends TieredItem {



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

            LaserEntity laser = new LaserEntity(level, entity, 0, 0, 0);
            laser.setPos(entity.getX(), entity.getY() + 1.5, entity.getZ());
            laser.setOwner(entity);
            laser.shootFromRotation(entity, entity.getXRot(), entity.getYRot(), 0.0F, 3.0F, 1.0F);
            laser.setItem(ItemsRegistry.LASER_ITEM.get().getDefaultInstance());
            level.addFreshEntity(laser);

        }
    }

    @Override
    public UseAnim getUseAnimation(ItemStack $$0) {
        return UseAnim.BOW;
    }

    public int getUseDuration(ItemStack p_40680_) {
        return 200;
    }

}
