package fr.tathan.swplanets.registry;

import fr.tathan.swplanets.Constants;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;

public class TagsRegistry {

    public static final TagKey<Biome> STARWARS_PLANETS;
    public static final TagKey<Biome> TATOOINE_LIKE;

    public static void init() {
    }
    static {
        STARWARS_PLANETS = TagKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(Constants.MODID, "starwars_planets"));
        TATOOINE_LIKE = TagKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(Constants.MODID, "tatooine_like"));
    }

}
