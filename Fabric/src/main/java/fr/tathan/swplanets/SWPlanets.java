package fr.tathan.swplanets;

import fr.tathan.swplanets.registry.ItemsRegistry;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.network.chat.Component;

public class SWPlanets implements ModInitializer {

	@Override
	public void onInitialize() {

		Constants.LOG.info("Star Wars Planets 🚀!");
		CommonClass.init();
		ItemsRegistry.onRegisterCreativeTabs((loc, item, items) -> FabricItemGroup.builder(loc)
				.title(Component.translatable("itemGroup." + loc.getNamespace() + "." + loc.getPath()))
				.icon(() -> item.get().getDefaultInstance())
				.displayItems((featureFlagSet, output, bl) -> items.forEach(output::accept))
				.build());

	}
}
