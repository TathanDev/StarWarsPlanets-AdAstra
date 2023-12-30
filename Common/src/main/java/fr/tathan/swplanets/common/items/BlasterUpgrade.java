package fr.tathan.swplanets.common.items;

import net.minecraft.world.item.Item;

public class BlasterUpgrade extends Item {

    private final boolean zoom;
    private final int lifeTime;

    public BlasterUpgrade(Properties $$0, boolean zoom, int lifeTime) {
        super($$0);
        this.zoom = zoom;
        this.lifeTime = lifeTime;
    }

    public boolean getZoom() {
        return zoom;
    }

    public int getLifeTime() {
        return lifeTime;
    }

}
