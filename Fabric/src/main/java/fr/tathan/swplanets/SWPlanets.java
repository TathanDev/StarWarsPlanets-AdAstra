package fr.tathan.swplanets;

import fr.tathan.swplanets.registry.ItemsRegistry;
import fr.tathan.swplanets.registry.TagsRegistry;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

import net.minecraft.world.level.levelgen.GenerationStep;

public class SWPlanets implements ModInitializer {


	@Override
	public void onInitialize() {

		Constants.LOG.info("Star Wars Planets 🚀!");
		CommonClass.init();
		/**
		ItemsRegistry.onRegisterCreativeTabs((loc, item, items) -> FabricItemGroup.builder(loc)
				.title(Component.translatable("itemGroup." + loc.getNamespace() + "." + loc.getPath()))
				.icon(() -> item.get().getDefaultInstance())
				.displayItems((featureFlagSet, output) -> items.forEach(output::accept))
				.build());
		 */

		BiomeModifications.addFeature(
				BiomeSelectors.tag(TagsRegistry.STARWARS_PLANETS),
				GenerationStep.Decoration.UNDERGROUND_ORES,
				ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(Constants.MODID, "kyber_crystal_ore_deepslate"))
		);
		BiomeModifications.addFeature(
				BiomeSelectors.tag(TagsRegistry.TATOOINE_LIKE),
				GenerationStep.Decoration.UNDERGROUND_ORES,
				ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(Constants.MODID, "kyber_crystal_ore_sandstone"))
		);

		BiomeModifications.addFeature(
				BiomeSelectors.tag(TagsRegistry.MANDALORE_BIOME),
				GenerationStep.Decoration.UNDERGROUND_ORES,
				ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(Constants.MODID, "beskar_ore_sandstone"))
		);


	}


}
