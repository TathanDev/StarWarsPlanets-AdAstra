package fr.tathan.swplanets.common.items;


import fr.tathan.swplanets.common.entity.LaserEntity;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.SmallFireball;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Rotation;


public class Blaster extends Item {



    public Blaster(Properties properties) {
        super(properties);
    }


    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand handIn) {
        if (!level.isClientSide) {

            LaserEntity fireball = new LaserEntity(level, player, 0, 0, 0, player.getDirection());
            fireball.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 2F, 1.0F);

            fireball.rotate(Rotation.CLOCKWISE_90);

            level.addFreshEntity(fireball);
        }

        return new InteractionResultHolder<>(InteractionResult.SUCCESS, player.getItemInHand(handIn));

    }



}
