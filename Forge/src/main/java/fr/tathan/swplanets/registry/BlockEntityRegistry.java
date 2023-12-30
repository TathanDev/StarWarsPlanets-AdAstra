package fr.tathan.swplanets.registry;
import fr.tathan.swplanets.Constants;
import fr.tathan.swplanets.blocks.entity.BlasterUpgraderEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockEntityRegistry {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, Constants.MODID);

    public static final RegistryObject<BlockEntityType<BlasterUpgraderEntity>> BLASTER_UPGRADER =
            BLOCK_ENTITIES.register("blaster_upgrader", () ->
                    BlockEntityType.Builder.of(BlasterUpgraderEntity::new,
                            ForgeBlockRegistry.BLASTER_UPGRADER.get()).build(null));

}
