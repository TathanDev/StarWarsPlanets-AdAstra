package fr.tathan.swplanets.common.registry;

import com.teamresourceful.resourcefullib.common.registry.RegistryEntry;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistries;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistry;
import earth.terrarium.adastra.common.menus.base.BaseContainerMenu;
import earth.terrarium.adastra.common.registry.ModMenus;
import earth.terrarium.botarium.common.registry.RegistryHelpers;
import fr.tathan.swplanets.Constants;
import fr.tathan.swplanets.common.blocks.entities.BlasterUpgraderEntity;
import fr.tathan.swplanets.common.menu.BlasterUpgraderMenu;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.block.entity.BlockEntity;

public class MenusRegistry {

    public static final ResourcefulRegistry<MenuType<?>> MENUS = ResourcefulRegistries.create(BuiltInRegistries.MENU, Constants.MODID);
    public static final RegistryEntry<MenuType<BlasterUpgraderMenu>> BLASTER_UPGRADER = MENUS.register("blaster_upgrader_menu",
        () -> createMenuType(BlasterUpgraderMenu::new, BlasterUpgraderEntity.class));

    private static <T extends BaseContainerMenu<E>, E extends BlockEntity> MenuType<T> createMenuType(ModMenus.Factory<T, E> factory, Class<E> clazz) {
        return RegistryHelpers.createMenuType((id, inventory, buf) -> factory.create(
            id,
            inventory,
            BaseContainerMenu.getBlockEntityFromBuf(inventory.player.level(), buf, clazz)
        ));

    }
}
