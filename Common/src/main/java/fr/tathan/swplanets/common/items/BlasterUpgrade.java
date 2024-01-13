package fr.tathan.swplanets.common.items;

import net.minecraft.world.item.Item;

public class BlasterUpgrade extends Item {

    private final boolean zoom;
    private final int distance;

    public BlasterUpgrade(Properties $$0, boolean zoom, int distance) {
        super($$0);
        this.zoom = zoom;
        this.distance = distance;
    }

    public boolean getZoom() {
        return zoom;
    }

    public int getLifeTime() {
        return distance;
    }

}
