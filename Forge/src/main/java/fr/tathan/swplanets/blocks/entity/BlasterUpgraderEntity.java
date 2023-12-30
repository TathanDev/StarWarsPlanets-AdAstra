package fr.tathan.swplanets.blocks.entity;

import fr.tathan.swplanets.Constants;
import fr.tathan.swplanets.common.items.Blaster;
import fr.tathan.swplanets.common.items.BlasterUpgrade;
import fr.tathan.swplanets.common.registry.ItemsRegistry;
import fr.tathan.swplanets.common.registry.TagsRegistry;
import fr.tathan.swplanets.menu.BlasterUpgraderMenu;
import fr.tathan.swplanets.registry.BlockEntityRegistry;
import fr.tathan.swplanets.registry.ForgeMenuTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BlasterUpgraderEntity extends BlockEntity implements MenuProvider{
    private final ItemStackHandler itemHandler = new ItemStackHandler(3) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }
    };

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

    public BlasterUpgraderEntity(BlockPos p_155229_, BlockState p_155230_) {
        super(BlockEntityRegistry.BLASTER_UPGRADER.get(), p_155229_, p_155230_);
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Blaster Upgrader");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int p_39954_, Inventory p_39955_, Player p_39956_) {
        return new BlasterUpgraderMenu(p_39954_, p_39955_, this);
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == ForgeCapabilities.ITEM_HANDLER) {
            return lazyItemHandler.cast();
        }

        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }

    @Override
    protected void saveAdditional(CompoundTag nbt) {
        nbt.put("inventory", itemHandler.serializeNBT());

        super.saveAdditional(nbt);
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        itemHandler.deserializeNBT(nbt.getCompound("inventory"));
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, BlasterUpgraderEntity pEntity) {
        if(level.isClientSide()) {
            return;
        }

        if(hasRecipe(pEntity)) {
            setChanged(level, pos, state);
            craft(pEntity);

        } else {
            setChanged(level, pos, state);
        }
    }

    private static boolean hasRecipe(BlasterUpgraderEntity entity) {
        SimpleContainer inventory = new SimpleContainer(entity.itemHandler.getSlots());
        for (int i = 0; i < entity.itemHandler.getSlots(); i++) {
            inventory.setItem(i, entity.itemHandler.getStackInSlot(i));
        }

        boolean hasBlasterInFirstSlot = entity.itemHandler.getStackInSlot(0).is(TagsRegistry.BLASTERS);
        boolean hasUpgradeInSecondSlot = entity.itemHandler.getStackInSlot(1).is(TagsRegistry.BLASTER_UPGRADE);

        return hasBlasterInFirstSlot && hasUpgradeInSecondSlot && canInsertAmountIntoOutputSlot(inventory) &&
                canInsertItemIntoOutputSlot(inventory, new ItemStack(ItemsRegistry.BLASTER.get(), 1));
    }

    private static ItemStack getItem(int slot, BlasterUpgraderEntity entity) {
       return entity.itemHandler.getStackInSlot(slot);
    }

    private static void craft(BlasterUpgraderEntity entity){
        ItemStack upgrade_input = getItem(1, entity);
        ItemStack vehicle_input = getItem(0, entity);

        if (!upgrade_input.isEmpty() && !vehicle_input.isEmpty()) {
            if (upgrade_input.getItem() instanceof BlasterUpgrade upgrade) {
                ItemStack output = vehicle_input.copy();

                if (output.getItem() instanceof Blaster rocket) {
                    rocket.setUpgrade(upgrade);
                }

                output.setCount(1);

                entity.itemHandler.extractItem(0, 1, false);
                entity.itemHandler.extractItem(1, 1, false);
                entity.itemHandler.insertItem(2, output, false);
            }
        }

    }

    private static boolean canInsertItemIntoOutputSlot(SimpleContainer inventory, ItemStack stack) {
        return inventory.getItem(2).getItem() == stack.getItem() || inventory.getItem(2).isEmpty();
    }

    private static boolean canInsertAmountIntoOutputSlot(SimpleContainer inventory) {
        return inventory.getItem(2).getMaxStackSize() > inventory.getItem(2).getCount();
    }


}
