package net.fabricmc.example.blocks.UpHoppers;

import net.fabricmc.example.blocks.HipHoppersBlocks;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.Hopper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.stat.Stats;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.util.*;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class SkupperBlock extends BlockWithEntity {

    public static final DirectionProperty FACING;
    public static final BooleanProperty ENABLED;
    private static final VoxelShape BOTTOM_SHAPE;
    private static final VoxelShape INSIDE_SHAPE;
    private static final VoxelShape MIDDLE_SHAPE;
    private static final VoxelShape OUTSIDE_SHAPE;
    private static final VoxelShape DEFAULT_SHAPE;
    private static final VoxelShape UP_SHAPE;
    private static final VoxelShape EAST_SHAPE;
    private static final VoxelShape NORTH_SHAPE;
    private static final VoxelShape SOUTH_SHAPE;
    private static final VoxelShape WEST_SHAPE;
    private static final VoxelShape UP_RAYCAST_SHAPE;
    private static final VoxelShape EAST_RAYCAST_SHAPE;
    private static final VoxelShape NORTH_RAYCAST_SHAPE;
    private static final VoxelShape SOUTH_RAYCAST_SHAPE;
    private static final VoxelShape WEST_RAYCAST_SHAPE;
    private final int transferSpeed;

    public SkupperBlock(Settings settings,int itemMoveSpeed) {
        super(settings);
        this.transferSpeed = itemMoveSpeed;
        this.setDefaultState((BlockState)((BlockState)((BlockState)this.stateManager.getDefaultState()).with(FACING, Direction.UP)).with(ENABLED, true));
    }

    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return switch ((Direction) state.get(FACING)) {
            case UP -> UP_SHAPE;
            case NORTH -> NORTH_SHAPE;
            case SOUTH -> SOUTH_SHAPE;
            case WEST -> WEST_SHAPE;
            case EAST -> EAST_SHAPE;
            default -> DEFAULT_SHAPE;
        };
    }
    @Override
    public boolean isTranslucent(BlockState state, BlockView world, BlockPos pos) {
        return true;
    }

    public VoxelShape getRaycastShape(BlockState state, BlockView world, BlockPos pos) {
        return switch ((Direction) state.get(FACING)) {
            case UP -> UP_RAYCAST_SHAPE;
            case NORTH -> NORTH_RAYCAST_SHAPE;
            case SOUTH -> SOUTH_RAYCAST_SHAPE;
            case WEST -> WEST_RAYCAST_SHAPE;
            case EAST -> EAST_RAYCAST_SHAPE;
            default -> Hopper.INSIDE_SHAPE;
        };
    }

    public BlockState getPlacementState(ItemPlacementContext ctx) {
        Direction direction = ctx.getSide().getOpposite();
        return (BlockState)((BlockState)this.getDefaultState().with(FACING, direction.getAxis() == Direction.Axis.Y ? Direction.UP : direction)).with(ENABLED, true);
    }

    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new SkupperBlockEntity(pos, state,this.transferSpeed);
    }

    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return world.isClient ? null : checkType(type, HipHoppersBlocks.SKUPPER_BLOCK_ENTITY, SkupperBlockEntity::serverTick);
    }

    public void onPlaced(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack) {
        if (itemStack.hasCustomName()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof SkupperBlockEntity) {
                ((SkupperBlockEntity)blockEntity).setCustomName(itemStack.getName());
            }
        }

    }

    public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
        if (!oldState.isOf(state.getBlock())) {
            this.updateEnabled(world, pos, state);
        }
    }

    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (world.isClient) {
            return ActionResult.SUCCESS;
        } else {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof SkupperBlockEntity) {
                player.openHandledScreen((SkupperBlockEntity)blockEntity);
                player.incrementStat(Stats.INSPECT_HOPPER);
            }

            return ActionResult.CONSUME;
        }
    }

    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block block, BlockPos fromPos, boolean notify) {
        this.updateEnabled(world, pos, state);
    }

    private void updateEnabled(World world, BlockPos pos, BlockState state) {
        boolean bl = !world.isReceivingRedstonePower(pos);
        if (bl != (Boolean)state.get(ENABLED)) {
            world.setBlockState(pos, (BlockState)state.with(ENABLED, bl), 4);
        }

    }

    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (!state.isOf(newState.getBlock())) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof SkupperBlockEntity) {
                ItemScatterer.spawn(world, pos, (SkupperBlockEntity)blockEntity);
                world.updateComparators(pos, this);
            }

            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    public boolean hasComparatorOutput(BlockState state) {
        return true;
    }

    public int getComparatorOutput(BlockState state, World world, BlockPos pos) {
        return ScreenHandler.calculateComparatorOutput(world.getBlockEntity(pos));
    }

    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return (BlockState)state.with(FACING, rotation.rotate((Direction)state.get(FACING)));
    }

    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation((Direction)state.get(FACING)));
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(new Property[]{FACING, ENABLED});
    }

    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof SkupperBlockEntity) {
            SkupperBlockEntity.onEntityCollided(world, pos, state, entity, (SkupperBlockEntity)blockEntity);
        }

    }

    public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
        return false;
    }

    static {
        ENABLED = Properties.ENABLED;
        FACING = DirectionProperty.of("facing", (facing) -> facing != Direction.DOWN);
        BOTTOM_SHAPE = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 6.0D, 16.0D);
        MIDDLE_SHAPE = Block.createCuboidShape(4.0D, 6.0D, 4.0D, 12.0D, 12.0D, 12.0D);
        OUTSIDE_SHAPE = VoxelShapes.union(MIDDLE_SHAPE, BOTTOM_SHAPE);
        INSIDE_SHAPE = Block.createCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 5.0D, 14.0D);
        DEFAULT_SHAPE = VoxelShapes.combineAndSimplify(OUTSIDE_SHAPE, INSIDE_SHAPE, BooleanBiFunction.ONLY_FIRST);
        UP_SHAPE = VoxelShapes.union(DEFAULT_SHAPE, Block.createCuboidShape(6.0D, 12.0D, 6.0D, 10.0D, 16.0D, 10.0D));
        EAST_SHAPE = VoxelShapes.union(DEFAULT_SHAPE, Block.createCuboidShape(12.0D, 8.0D, 6.0D, 16.0D, 12.0D, 10.0D));
        NORTH_SHAPE = VoxelShapes.union(DEFAULT_SHAPE, Block.createCuboidShape(6.0D, 8.0D, 0.0D, 10.0D, 12.0D, 4.0D));
        SOUTH_SHAPE = VoxelShapes.union(DEFAULT_SHAPE, Block.createCuboidShape(6.0D, 8.0D, 12.0D, 10.0D, 12.0D, 16.0D));
        WEST_SHAPE = VoxelShapes.union(DEFAULT_SHAPE, Block.createCuboidShape(0.0D, 8.0D, 6.0D, 4.0D, 12.0D, 10.0D));
        UP_RAYCAST_SHAPE = INSIDE_SHAPE;
        EAST_RAYCAST_SHAPE = VoxelShapes.union(INSIDE_SHAPE, Block.createCuboidShape(12.0D, 6.0D, 6.0D, 16.0D, 8.0D, 10.0D));
        NORTH_RAYCAST_SHAPE = VoxelShapes.union(INSIDE_SHAPE, Block.createCuboidShape(6.0D, 6.0D, 0.0D, 10.0D, 8.0D, 4.0D));
        SOUTH_RAYCAST_SHAPE = VoxelShapes.union(INSIDE_SHAPE, Block.createCuboidShape(6.0D, 6.0D, 12.0D, 10.0D, 8.0D, 16.0D));
        WEST_RAYCAST_SHAPE = VoxelShapes.union(INSIDE_SHAPE, Block.createCuboidShape(0.0D, 6.0D, 6.0D, 4.0D, 8.0D, 10.0D));
    }
}
