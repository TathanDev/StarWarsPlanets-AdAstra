package fr.tathan.swplanets.common.registry;

import fr.tathan.swplanets.Constants;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.biome.Biome;
public class TagsRegistry {

    public static final TagKey<Biome> STARWARS_PLANETS;
    public static final TagKey<Biome> TATOOINE_LIKE;
    public static final TagKey<Biome> MANDALORE_BIOME;

    public static final TagKey<Item> BLASTERS;
    public static final TagKey<Item> BLASTER_UPGRADE;

    public static void init() {
    }

    static {
        STARWARS_PLANETS = TagKey.create(Registries.BIOME, new ResourceLocation(Constants.MODID, "starwars_planets"));
        TATOOINE_LIKE = TagKey.create(Registries.BIOME, new ResourceLocation(Constants.MODID, "tatooine_like"));
        MANDALORE_BIOME = TagKey.create(Registries.BIOME, new ResourceLocation(Constants.MODID, "mandalore_biomes"));

        BLASTERS = TagKey.create(Registries.ITEM, new ResourceLocation(Constants.MODID, "blasters"));
        BLASTER_UPGRADE = TagKey.create(Registries.ITEM, new ResourceLocation(Constants.MODID, "blaster_upgrade"));
    }


}
