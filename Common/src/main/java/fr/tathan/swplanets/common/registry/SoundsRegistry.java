package fr.tathan.swplanets.common.registry;

import fr.tathan.swplanets.Constants;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;

public class SoundsRegistry {

    public static final RegistrationProvider<SoundEvent> SOUNDS = RegistrationProvider.get(Registries.SOUND_EVENT, Constants.MODID);

    public static final RegistryObject<SoundEvent> STAR_WARS_OPENING_MUSIC = SOUNDS.register("star_wars_opening_music", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Constants.MODID, "star_wars_opening_music")));
    public static final RegistryObject<SoundEvent> SABER_ATTACK_SOUND = SOUNDS.register("saber_attack_sound", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Constants.MODID, "saber_attack_sound")));
    public static final RegistryObject<SoundEvent> BLASTER_SOUND = SOUNDS.register("blaster_sound", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Constants.MODID, "blaster_sound")));
    public static final RegistryObject<SoundEvent> LIGHT_SABER_SOUND_1 = SOUNDS.register("light_saber_sound_1", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Constants.MODID, "light_saber_sound_1")));
    public static final RegistryObject<SoundEvent> LIGHT_SABER_SOUND_2 = SOUNDS.register("light_saber_sound_2", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Constants.MODID, "light_saber_sound_2")));
    public static final RegistryObject<SoundEvent> LIGHT_SABER_SOUND_3 = SOUNDS.register("light_saber_sound_3", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Constants.MODID, "light_saber_sound_3")));


    public static void init() {
    }

}
