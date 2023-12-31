package fr.tathan.swplanets.blocks;

import fr.tathan.swplanets.blocks.entity.BlasterUpgraderEntity;
import fr.tathan.swplanets.registry.FabricBlockEntityRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.world.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class BlasterUpgrader extends BaseEntityBlock implements EntityBlock {
    private static final VoxelShape SHAPE = Block.box(0, 0, 0, 16, 16, 16);

    public BlasterUpgrader(Properties settings) {
        super(settings);
    }


    @Override
    public VoxelShape getShape(BlockState state, BlockGetter
            world, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new BlasterUpgraderEntity(pos, state);
    }

    @Override
    public void onRemove(BlockState state, Level world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof BlasterUpgraderEntity) {
                Containers.dropContents(world, pos, (BlasterUpgraderEntity)blockEntity);
                world.updateNeighbourForOutputSignal(pos,this);
            }
            super.onRemove(state, world, pos, newState, moved);
        }
    }

    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (!world.isClientSide()) {
            MenuProvider screenHandlerFactory = ((BlasterUpgraderEntity) world.getBlockEntity(pos));

            if (screenHandlerFactory != null) {
                player.openMenu(screenHandlerFactory);
            }
        }

        return InteractionResult.SUCCESS;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level world, BlockState state, BlockEntityType<T> type) {
        return createTickerHelper(type, FabricBlockEntityRegistry.BLASTER_UPGRADER_BE,
                (world1, pos, state1, blockEntity) -> blockEntity.tick(world1, pos, state1, blockEntity));
    }

}
