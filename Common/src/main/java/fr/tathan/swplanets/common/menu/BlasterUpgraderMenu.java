package fr.tathan.swplanets.common.menu;

import earth.terrarium.adastra.common.menus.base.BaseContainerMenu;
import earth.terrarium.adastra.common.menus.slots.CustomSlot;
import fr.tathan.swplanets.common.blocks.entities.BlasterUpgraderEntity;
import fr.tathan.swplanets.common.registry.MenusRegistry;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import org.jetbrains.annotations.Nullable;

public class BlasterUpgraderMenu extends BaseContainerMenu<BlasterUpgraderEntity> {
    public BlasterUpgraderMenu( int id, Inventory inventory, BlasterUpgraderEntity entity) {
        super(MenusRegistry.BLASTER_UPGRADER.get(), id, inventory, entity);
    }

    @Override
    protected int getContainerInputEnd() {
        return 2;
    }

    @Override
    protected int getInventoryStart() {
        return 2;
    }

    @Override
    public int getPlayerInvXOffset() {
        return 8;
    }

    @Override
    protected int startIndex() {
        return 0;
    }

    @Override
    public int getPlayerInvYOffset() {
        return 142;
    }

    @Override
    protected void addMenuSlots() {
        addSlot(new Slot(entity, 0, 56, 20));
        addSlot(new Slot(entity, 1, 47, 38));
        addSlot(CustomSlot.noPlace(entity, 14, 129, 56));
    }
}
