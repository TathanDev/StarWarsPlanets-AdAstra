package fr.tathan.swplanets.common.entity;

import fr.tathan.swplanets.Constants;
import fr.tathan.swplanets.common.registry.EntityRegistry;
import fr.tathan.swplanets.common.registry.ItemsRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.projectile.SmallFireball;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class LaserEntity extends SmallFireball {
    public int life;
    public int lifetime;

    public LaserEntity(EntityType<? extends SmallFireball> $$0, Level $$1) {
        super($$0, $$1);
        this.life = 0;
        this.lifetime = 50 + this.random.nextInt(6) + this.random.nextInt(7);

    }

    public LaserEntity(Level $$0, LivingEntity $$1, double $$2, double $$3, double $$4) {
        super($$0, $$1, $$2, $$3, $$4);
        this.life = 0;
        this.lifetime = 50 + this.random.nextInt(6) + this.random.nextInt(7);

    }

    public LaserEntity(Level $$0, double $$1, double $$2, double $$3, double $$4, double $$5, double $$6) {
        super($$0, $$1, $$2, $$3, $$4, $$5, $$6);
        this.life = 0;
        this.lifetime = 50 + this.random.nextInt(6) + this.random.nextInt(7);

    }


    @Override
    public void addAdditionalSaveData(CompoundTag $$0) {
        super.addAdditionalSaveData($$0);
        $$0.putInt("Life", this.life);
        $$0.putInt("LifeTime", this.lifetime);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag $$0) {
        super.readAdditionalSaveData($$0);
        this.life = $$0.getInt("Life");
        this.lifetime = $$0.getInt("LifeTime");
    }

    @Override
    public void tick() {
        super.tick();
        this.clearFire();

        ++this.life;
        if(!this.level().isClientSide && this.life > this.lifetime ) {
            Constants.LOG.error("LaserEntity:" + life + ">" + lifetime);
            this.discard();
        }
    }

    @Override
    public boolean isOnFire() {
        return false;
    }

    @Override
    protected boolean shouldBurn() {
        return false;
    }
    @Override
    public ItemStack getItem() {
        return ItemsRegistry.LASER_ITEM.get().getDefaultInstance();
    }
}
