package fr.tathan.swplanets.common.blocks.entities;

import earth.terrarium.adastra.common.blockentities.base.ContainerMachineBlockEntity;
import earth.terrarium.adastra.common.blockentities.base.RedstoneControl;
import earth.terrarium.adastra.common.blockentities.base.sideconfig.Configuration;
import earth.terrarium.adastra.common.blockentities.base.sideconfig.ConfigurationEntry;
import earth.terrarium.adastra.common.blockentities.base.sideconfig.ConfigurationType;
import earth.terrarium.adastra.common.blockentities.machines.FuelRefineryBlockEntity;
import earth.terrarium.adastra.common.blockentities.machines.NasaWorkbenchBlockEntity;
import earth.terrarium.adastra.common.constants.ConstantComponents;
import earth.terrarium.adastra.common.utils.TransferUtils;
import fr.tathan.swplanets.Constants;
import fr.tathan.swplanets.common.items.Blaster;
import fr.tathan.swplanets.common.items.BlasterUpgrade;
import fr.tathan.swplanets.common.menu.BlasterUpgraderMenu;
import fr.tathan.swplanets.common.registry.TagsRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Predicate;

public class BlasterUpgraderEntity extends ContainerMachineBlockEntity {
    public static final List<ConfigurationEntry> SIDE_CONFIG = List.of(
        new ConfigurationEntry(ConfigurationType.SLOT, Configuration.NONE, ConstantComponents.SIDE_CONFIG_INPUT_SLOTS)
    );
    private static final int[] INPUT_SLOTS = {0, 1};
    public BlasterUpgraderEntity(BlockPos pos, BlockState state) {
        super(pos, state, 3);
        this.setRedstoneControl(RedstoneControl.NEVER_ON);
    }

    @Override
    public List<ConfigurationEntry> getDefaultConfig() {
        return SIDE_CONFIG;
    }

    @Override
    public int[] getSlotsForFace(Direction side) {
        return INPUT_SLOTS;
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return new BlasterUpgraderMenu( i, inventory, this);
    }

    @Override
    public void tickSideInteractions(BlockPos pos, Predicate<Direction> filter, List<ConfigurationEntry> sideConfig) {
        TransferUtils.pushItemsNearby(this, pos, INPUT_SLOTS, sideConfig.get(0), filter);
        TransferUtils.pullItemsNearby(this, pos, INPUT_SLOTS, sideConfig.get(0), filter);
    }


    @Override
    public void serverTick(ServerLevel level, long time, BlockState state, BlockPos pos) {
        if(level.isClientSide()) {
            return;
        }

        if(hasRecipe()) {
            Constants.LOG.error("Recipe !!");
            setChanged(level, pos, state);
            craft();

        } else {
            setChanged(level, pos, state);
        }
    }

    private boolean hasRecipe() {

        boolean hasBlasterInFirstSlot = getItem(0).is(TagsRegistry.BLASTERS);
        boolean hasUpgradeInSecondSlot = getItem(1).is(TagsRegistry.BLASTER_UPGRADE);

        return hasBlasterInFirstSlot && hasUpgradeInSecondSlot;
    }


    private void craft(){
        ItemStack upgrade_input = getItem(1);
        ItemStack vehicle_input = getItem(0);

        if (!upgrade_input.isEmpty() && !vehicle_input.isEmpty()) {
            if (upgrade_input.getItem() instanceof BlasterUpgrade upgrade) {
                ItemStack output = vehicle_input.copy();

                if (output.getItem() instanceof Blaster rocket) {
                    rocket.setUpgrade(upgrade);
                }

                output.setCount(1);
                Constants.LOG.error("Crafting Blaster");
                removeItem(0, 1);
                removeItem(1, 1);
                setItem(2, output);
            }
        }

    }
}
