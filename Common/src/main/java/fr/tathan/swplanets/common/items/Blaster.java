package fr.tathan.swplanets.common.items;


import fr.tathan.swplanets.common.entity.LaserEntity;
import fr.tathan.swplanets.common.registry.ItemsRegistry;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;


public class Blaster extends TieredItem {



    public Blaster(Properties properties) {
        super(StarWarsTiers.PLASTIC, properties);
    }


    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand handIn) {
        if (!level.isClientSide) {

            LaserEntity fireball = new LaserEntity(level, player, 0, 0, 0);
            fireball.setPos(player.getX(), player.getY() + 1.5, player.getZ());
            fireball.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 1.0F);
            fireball.setItem(ItemsRegistry.LASER_ITEM.get().getDefaultInstance());
            level.addFreshEntity(fireball);

            player.getItemInHand(handIn).hurtAndBreak(1, player, (p_220009_1_) -> {
                p_220009_1_.broadcastBreakEvent(handIn);
            });


        }

        return new InteractionResultHolder<>(InteractionResult.SUCCESS, player.getItemInHand(handIn));

    }



}
