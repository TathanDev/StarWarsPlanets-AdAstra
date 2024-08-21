package fr.tathan.swplanets.datagen.provider;

import earth.terrarium.adastra.client.dimension.MovementType;
import earth.terrarium.adastra.client.dimension.PlanetRenderer;
import earth.terrarium.adastra.client.dimension.SkyRenderable;
import earth.terrarium.adastra.client.utils.DimensionRenderingUtils;
import fr.tathan.swplanets.Constants;
import fr.tathan.swplanets.common.registry.LevelRegistry;
import fr.tathan.swplanets.common.util.PlanetRenderUtil;
import fr.tathan.swplanets.datagen.provider.base.ModCodecProvider;
import net.minecraft.core.Registry;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;


public class PlanetsRendererProvider extends ModCodecProvider<PlanetRenderer> {

    public static final ResourceKey<Registry<PlanetRenderer>> PLANET_REGISTRY = ResourceKey.createRegistryKey(new ResourceLocation(Constants.MODID, "planets_renderer"));
    public static final int DEFAULT_SUNRISE_COLOR = 0xd85f33;

    public static final SimpleWeightedRandomList<Integer> COLORED_STARS = SimpleWeightedRandomList.<Integer>builder()
        .add(0xA9BCDFFF, 3)   // Blue
        .add(0xBBD7FFFF, 5)   // Blue-White,
        .add(0xFFF4E8FF, 100) // Yellow-White
        .add(0xFFD1A0FF, 80)  // Orange
        .add(0xFF8A8AFF, 150) // Red
        .add(0xFF4500FF, 10)  // Orange-Red
        .add(0xFFFFF4FF, 60)  // White
        .add(0xFFF8E7FF, 40)  // Pale Yellow
        .add(0xFFFFFF00, 20)  // Very Pale Yellow
        .add(0xFFFF0000, 1)   // Bright Red
        .build();

    public static final SimpleWeightedRandomList<Integer> DEFAULT_STARS = SimpleWeightedRandomList.<Integer>builder()
        .add(0xffffffff, 1)
        .build();

    public PlanetsRendererProvider(PackOutput packOutput) {
        super(packOutput, PlanetRenderer.CODEC, PLANET_REGISTRY, PackOutput.Target.RESOURCE_PACK);
    }

    @Override
    protected void build(BiConsumer<ResourceLocation, PlanetRenderer> consumer) {

        orbit(consumer, LevelRegistry.ENDOR_ORBIT, PlanetRenderUtil.ENDOR, 0xff3c7cda, 10,
            new SkyRenderable(PlanetRenderUtil.DEATH_STAR, 8, new Vec3(80, 0, 30), new Vec3(0, -5, 0), MovementType.STATIC, 0xffafb8cc));

        orbit(consumer, LevelRegistry.HOTH_ORBIT, DimensionRenderingUtils.MARS, 0xffb6552b, 7);

        orbit(consumer, LevelRegistry.MANDALORE_ORBIT, DimensionRenderingUtils.MARS, 0xffb6552b, 7);

        orbit(consumer, LevelRegistry.MUSTAFAR_ORBIT, DimensionRenderingUtils.VENUS, 0xfff3c476, 14);

        orbit(consumer, LevelRegistry.TAOOINE_ORBIT, DimensionRenderingUtils.MERCURY, 0xffab6989, 22,
            new SkyRenderable(DimensionRenderingUtils.RED_SUN, 8, new Vec3(80, 0, 30), new Vec3(0, -5, 0), MovementType.TIME_OF_DAY, 0xffafb8cc));


        consumer.accept(LevelRegistry.TATOOINE.location(), new PlanetRenderer(
            LevelRegistry.TATOOINE,
            true,
            true,
            false,
            false,
            true,
            DEFAULT_SUNRISE_COLOR,
            0,
            Optional.of(0.6f),
            0,
            true,
            COLORED_STARS,
            List.of(
                new SkyRenderable(DimensionRenderingUtils.SUN, 9, Vec3.ZERO, Vec3.ZERO, MovementType.TIME_OF_DAY, 0xffffffd9),
                new SkyRenderable(DimensionRenderingUtils.RED_SUN, 9, new Vec3(0, 0, 0), new Vec3(0, 0, 0), MovementType.TIME_OF_DAY, 0xffffffd9)
            )));

        consumer.accept(LevelRegistry.ENDOR.location(), new PlanetRenderer(
            LevelRegistry.ENDOR,
            false,
            true,
            false,
            false,
            true,
            DEFAULT_SUNRISE_COLOR,
            0,
            Optional.empty(),
            0,
            false,
            DEFAULT_STARS,
            List.of(
                new SkyRenderable(DimensionRenderingUtils.SUN, 9, Vec3.ZERO, Vec3.ZERO, MovementType.TIME_OF_DAY, 0xffffffd9),
                new SkyRenderable(PlanetRenderUtil.DEATH_STAR, 2, new Vec3(20, 20, 180), new Vec3(0, 0, 0), MovementType.STATIC, true, 0xffb4908d)
            )));

        consumer.accept(LevelRegistry.MANDALORE.location(), new PlanetRenderer(
            LevelRegistry.MANDALORE,
            false,
            true,
            false,
            true,
            true,
            0xf9c21a,
            1500,
            Optional.empty(),
            180,
            false,
            DEFAULT_STARS,
            List.of(
                new SkyRenderable(DimensionRenderingUtils.SUN, 14, Vec3.ZERO, Vec3.ZERO, MovementType.TIME_OF_DAY_REVERSED, true, 0xfff48c61)
            )));

    }

    private static void orbit(BiConsumer<ResourceLocation, PlanetRenderer> consumer, ResourceKey<Level> planet, ResourceLocation planetTexture, int backlightColor, int sunScale, SkyRenderable... additionalRenderables) {
        List<SkyRenderable> renderables = new ArrayList<>();
        renderables.add(new SkyRenderable(DimensionRenderingUtils.SUN, sunScale, Vec3.ZERO, Vec3.ZERO, MovementType.TIME_OF_DAY, 0xffffffd9));
        renderables.add(new SkyRenderable(planetTexture, 80, new Vec3(180, 0, 0), Vec3.ZERO, MovementType.STATIC, backlightColor));
        renderables.addAll(List.of(additionalRenderables));
        consumer.accept(
            planet.location(),
            new PlanetRenderer(
                planet,
                true,
                true,
                false,
                false,
                false,
                DEFAULT_SUNRISE_COLOR,
                13000,
                Optional.of(0.6f),
                0,
                true,
                COLORED_STARS,
                renderables
            ));
    }

    @Override
    public @NotNull String getName() {
        return "Planets Renderers";
    }
}
