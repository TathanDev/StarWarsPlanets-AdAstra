package fr.tathan.swplanets.common.registry;

import com.teamresourceful.resourcefullib.common.registry.RegistryEntry;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistries;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistry;
import fr.tathan.swplanets.Constants;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

public class SoundsRegistry {

    public static final ResourcefulRegistry<SoundEvent> SOUNDS = ResourcefulRegistries.create(BuiltInRegistries.SOUND_EVENT, Constants.MODID);

    public static final RegistryEntry<SoundEvent> STAR_WARS_OPENING_MUSIC = SOUNDS.register("star_wars_opening_music", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Constants.MODID, "star_wars_opening_music")));
    public static final RegistryEntry<SoundEvent> SABER_ATTACK_SOUND = SOUNDS.register("saber_attack_sound", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Constants.MODID, "saber_attack_sound")));
    public static final RegistryEntry<SoundEvent> BLASTER_SOUND = SOUNDS.register("blaster_sound", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Constants.MODID, "blaster_sound")));


}
