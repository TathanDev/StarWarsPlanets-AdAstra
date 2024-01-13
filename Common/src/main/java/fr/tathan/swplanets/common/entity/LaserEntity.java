package fr.tathan.swplanets.common.entity;

import fr.tathan.swplanets.common.registry.ItemsRegistry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.SmallFireball;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.concurrent.atomic.AtomicBoolean;

public class LaserEntity extends SmallFireball {
    public int life;
    public int lifetime;
    public String owner;

    public LaserEntity(EntityType<? extends SmallFireball> $$0, Level $$1) {
        super($$0, $$1);
        this.life = 0;
        this.lifetime = lifetime;

    }

    public LaserEntity(Level $$0, LivingEntity $$1, double $$2, double $$3, double $$4, int lifetime, String owner) {
        super($$0, $$1, $$2, $$3, $$4);
        this.life = 0;
        this.lifetime = lifetime;
        this.owner = owner;
    }

    public LaserEntity(Level $$0, double $$1, double $$2, double $$3, double $$4, double $$5, double $$6, int lifetime) {
        super($$0, $$1, $$2, $$3, $$4, $$5, $$6);
        this.life = 0;
        this.lifetime = lifetime;

    }


    @Override
    public void addAdditionalSaveData(CompoundTag $$0) {
        super.addAdditionalSaveData($$0);
        $$0.putInt("Life", this.life);
        $$0.putInt("LifeTime", this.lifetime);
        $$0.putString("Owner", this.owner);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag $$0) {
        super.readAdditionalSaveData($$0);
        this.life = $$0.getInt("Life");
        this.lifetime = $$0.getInt("LifeTime");
        this.owner = $$0.getString("Owner");
    }


    public boolean isOwnerOnline() {
        AtomicBoolean isOnline = new AtomicBoolean(false);

        this.level().getServer().getPlayerList().getPlayers().forEach(player -> {
            if(player.getName().getString().equals(this.owner)) {
                isOnline.set(true);
            }
        });

        return isOnline.get();
    }

    @Override
    public void tick() {

        if(!this.isOwnerOnline()) {
            this.discard();
        }

        if(!this.level().isClientSide) {
            this.clearFire();
        }

        ++this.life;
        if(!this.level().isClientSide && this.life > this.lifetime ) {
            this.discard();
        }
        super.tick();

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
    public boolean hurt(DamageSource $$0, float $$1) {
        return true;
    }

    @Override
    public ItemStack getItem() {
        return ItemsRegistry.LASER_ITEM.get().getDefaultInstance();
    }
}
