package fr.tathan.swplanets.blocks.entity;

import fr.tathan.swplanets.Constants;
import fr.tathan.swplanets.common.items.Blaster;
import fr.tathan.swplanets.common.items.BlasterUpgrade;
import fr.tathan.swplanets.common.registry.TagsRegistry;
import fr.tathan.swplanets.menu.BlasterUpgraderScreenHandler;
import fr.tathan.swplanets.registry.FabricBlockEntityRegistry;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class BlasterUpgraderEntity extends BlockEntity implements ExtendedScreenHandlerFactory, ImplementedInventory {

    private final NonNullList<ItemStack> inventory = NonNullList.withSize(3, ItemStack.EMPTY);

    public BlasterUpgraderEntity(BlockPos pos, BlockState state) {
        super(FabricBlockEntityRegistry.BLASTER_UPGRADER_BE, pos, state);
    }


    @Override
    public Component getDisplayName() {
        return Component.literal("Blaster Upgrader");
    }


    @Override
    protected void saveAdditional(CompoundTag nbt) {
        super.saveAdditional(nbt);
        ContainerHelper.saveAllItems(nbt, inventory);
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        ContainerHelper.loadAllItems(nbt, inventory);
    }
    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int syncId, Inventory playerInventory, Player player) {
        return new BlasterUpgraderScreenHandler(syncId, playerInventory, this);
    }

    @Override
    public NonNullList<ItemStack> getItems() {
        return inventory;
    }


    @Override
    public ItemStack removeItemNoUpdate(int i) {
        return null;
    }

    public void tick(Level level, BlockPos pos, BlockState state, BlasterUpgraderEntity pEntity) {
        if(level.isClientSide()) {
            return;
        }

        if(hasRecipe(pEntity)) {
            Constants.LOG.error("Recipe !!");
            setChanged(level, pos, state);
            craft(pEntity);

        } else {
            setChanged(level, pos, state);
        }
    }

    private boolean hasRecipe(BlasterUpgraderEntity entity) {

        boolean hasBlasterInFirstSlot = getItem(0).is(TagsRegistry.BLASTERS);
        boolean hasUpgradeInSecondSlot = getItem(1).is(TagsRegistry.BLASTER_UPGRADE);

        return hasBlasterInFirstSlot && hasUpgradeInSecondSlot;
    }

    public boolean isSlotEmpty(int slot) {
        return getItem(slot).isEmpty();
    }


    private void craft(BlasterUpgraderEntity entity){
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

    private static boolean canInsertItemIntoOutputSlot(SimpleContainer inventory, ItemStack stack) {
        return inventory.getItem(2).getItem() == stack.getItem() || inventory.getItem(2).isEmpty();
    }

    private static boolean canInsertAmountIntoOutputSlot(SimpleContainer inventory) {
        return inventory.getItem(2).getMaxStackSize() > inventory.getItem(2).getCount();
    }


    @Override
    public void writeScreenOpeningData(ServerPlayer player, FriendlyByteBuf buf) {
        buf.writeBlockPos(this.getBlockPos());

    }
}
