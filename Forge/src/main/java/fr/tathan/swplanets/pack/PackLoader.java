package fr.tathan.swplanets.pack;

import fr.tathan.swplanets.Constants;
import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.PackResources;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.PathPackResources;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraft.server.packs.repository.RepositorySource;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraftforge.forgespi.locating.IModFile;

import java.util.function.Consumer;

public class PackLoader implements RepositorySource {

    public static final String PACK_ID = Constants.MODID + ".old_lightsabers_models";
    private final PackResources resources;

    public PackLoader(IModFile modFile) {
        this.resources = new PathPackResources("Old Light Sabers Models",  modFile.findResource("old_lightsabers_models"), true);
        Constants.LOG.error("PackLoader: " + modFile.findResource("old_lightsabers_models"));
    }
    @Override
    public void loadPacks(Consumer<Pack> consumer) {
        Pack pack = Pack.create(PACK_ID, Component.literal("Old Light Sabers"), false, new Pack.ResourcesSupplier() {
            @Override
            public PackResources open(String pId) {
                return resources;
            }
        }, new Pack.Info(Component.literal("The Old Light Sabers Model"), 15, FeatureFlagSet.of()), PackType.CLIENT_RESOURCES, Pack.Position.TOP, true, PackSource.BUILT_IN);
        consumer.accept(pack);

    }
}
