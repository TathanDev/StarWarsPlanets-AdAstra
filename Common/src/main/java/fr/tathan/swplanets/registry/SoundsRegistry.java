package fr.tathan.swplanets.registry;

import fr.tathan.swplanets.Constants;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.Item;

import java.util.function.Supplier;

public class SoundsRegistry {

    public static final RegistrationProvider<SoundEvent> SOUNDS = RegistrationProvider.get(Registry.SOUND_EVENT, Constants.MODID);

    public static final RegistryObject<SoundEvent> STAR_WARS_OPENING_MUSIC = SOUNDS.register("star_wars_opening_music", () -> new SoundEvent(new ResourceLocation(Constants.MODID, "star_wars_opening_music")));
    public static final RegistryObject<SoundEvent> SABER_ATTACK_SOUND_1 = SOUNDS.register("saber_attack_sound_1", () -> new SoundEvent(new ResourceLocation(Constants.MODID, "saber_attack_sound_1")));
    public static final RegistryObject<SoundEvent> SABER_ATTACK_SOUND_2 = SOUNDS.register("saber_attack_sound_2", () -> new SoundEvent(new ResourceLocation(Constants.MODID, "saber_attack_sound_2")));
    public static final RegistryObject<SoundEvent> SABER_ATTACK_SOUND_3 = SOUNDS.register("saber_attack_sound_3", () -> new SoundEvent(new ResourceLocation(Constants.MODID, "saber_attack_sound_3")));
    public static final RegistryObject<SoundEvent> SABER_ATTACK_SOUND_4 = SOUNDS.register("saber_attack_sound_4", () -> new SoundEvent(new ResourceLocation(Constants.MODID, "saber_attack_sound_4")));

    public static void init() {
    }

}
