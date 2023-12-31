package fr.tathan.swplanets.registry;

import fr.tathan.swplanets.Constants;
import fr.tathan.swplanets.menu.BlasterUpgraderMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ForgeItemRegistry {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Constants.MODID);

    public static final RegistryObject<BlockItem> BLASTER_UPGRADER_ITEM =
            ITEMS.register("blaster_upgrader_menu", () -> new BlockItem(ForgeBlockRegistry.BLASTER_UPGRADER.get(), new Item.Properties()));


}
