package com.fusionflux.hip_hoppers.blocks.DownHoppers;

import com.fusionflux.hip_hoppers.blocks.HipHoppersBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.Hopper;
import net.minecraft.block.entity.HopperBlockEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;
import java.util.List;
import java.util.function.BooleanSupplier;
import java.util.stream.Collectors;

public class EnderHopperBlockEntityRedux extends HopperBlockEntity  implements Hopper {

    private DefaultedList<ItemStack> inventory;
    private int transferCooldown;
    private long lastTickTime;
    private int itemTransferCooldown;

    public EnderHopperBlockEntityRedux(BlockPos pos, BlockState state) {
        this(pos, state,8);
    }

    public EnderHopperBlockEntityRedux(BlockPos pos, BlockState state, int transferSpeed) {
        super(pos, state);
        this.inventory = DefaultedList.ofSize(5, ItemStack.EMPTY);
        this.transferCooldown = -1;
        this.itemTransferCooldown = transferSpeed;
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        this.inventory = DefaultedList.ofSize(this.size(), ItemStack.EMPTY);
        if (!this.deserializeLootTable(nbt)) {
            Inventories.readNbt(nbt, this.inventory);
        }
        this.itemTransferCooldown = nbt.getInt("ItemTransferCooldown");
        this.transferCooldown = nbt.getInt("TransferCooldown");
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        if (!this.serializeLootTable(nbt)) {
            Inventories.writeNbt(nbt, this.inventory);
        }
        nbt.putInt("ItemTransferCooldown", this.itemTransferCooldown);
        nbt.putInt("TransferCooldown", this.transferCooldown);
    }


   // public static void serverTick(World world, BlockPos pos, BlockState state, EnderHopperBlockEntityRedux blockEntity) {
   //     --blockEntity.transferCooldown;
   //     blockEntity.lastTickTime = world.getTime();
   //     if (!blockEntity.needsCooldown()) {
   //         blockEntity.setTransferCooldown(0);
   //         insertAndExtract(world, pos, state, blockEntity, () -> {
   //             return extract((World)world, (Hopper)blockEntity);
   //         });
   //     }
//
   // }


    private static boolean insertAndExtract(World world, BlockPos pos, BlockState state, EnderHopperBlockEntityRedux blockEntity, BooleanSupplier booleanSupplier) {
        if (world.isClient) {
            return false;
        } else {
            if (!blockEntity.needsCooldown() && (Boolean)state.get(EnderHopperBlock.ENABLED)) {
                boolean bl = false;
                if (!blockEntity.isEmpty()) {
                    bl = insert(world, pos, state, blockEntity);
                }

                if (!blockEntity.isFull()) {
                    bl |= booleanSupplier.getAsBoolean();
                }

                if (bl) {
                    blockEntity.setTransferCooldown(blockEntity.itemTransferCooldown);
                    markDirty(world, pos, state);
                    return true;
                }
            }

            return false;
        }
    }


    private static boolean insert(World world, BlockPos pos, BlockState state, Inventory inventory) {
        Inventory inventory2 = getOutputInventory(world, pos, state);
        if (inventory2 == null) {
            return false;
        } else {
            Direction direction = ((Direction)state.get(EnderHopperBlock.FACING)).getOpposite();
            if (isInventoryFull(inventory2, direction)) {
                return false;
            } else {
                for(int i = 0; i < inventory.size(); ++i) {
                    if (!inventory.getStack(i).isEmpty()) {
                        ItemStack itemStack = inventory.getStack(i).copy();
                        ItemStack itemStack2 = transfer(inventory, inventory2, inventory.removeStack(i, 1), direction);
                        if (itemStack2.isEmpty()) {
                            inventory2.markDirty();
                            return true;
                        }

                        inventory.setStack(i, itemStack);
                    }
                }

                return false;
            }
        }
    }

    public static boolean extract(World world, Hopper hopper) {
        Inventory inventory = getInputInventory(world, hopper);
        if (inventory != null) {
            Direction direction = Direction.DOWN;
            return !isInventoryEmpty(inventory, direction) && getAvailableSlots(inventory, direction).anyMatch((slot) -> extract(hopper, inventory, slot, direction));
        } else {
            Iterator var3 = getInputItemEntities(world, hopper).iterator();

            ItemEntity itemEntity;
            do {
                if (!var3.hasNext()) {
                    return false;
                }

                itemEntity = (ItemEntity)var3.next();
            } while(!extract((Inventory)hopper, (ItemEntity)itemEntity));

            return true;
        }
    }

    private static ItemStack transfer(@Nullable Inventory from, Inventory to, ItemStack stack, int slot, @Nullable Direction side) {
        ItemStack itemStack = to.getStack(slot);
        if (canInsert(to, stack, slot, side)) {
            boolean bl = false;
            boolean bl2 = to.isEmpty();
            if (itemStack.isEmpty()) {
                to.setStack(slot, stack);
                stack = ItemStack.EMPTY;
                bl = true;
            } else if (canMergeItems(itemStack, stack)) {
                int i = stack.getMaxCount() - itemStack.getCount();
                int j = Math.min(stack.getCount(), i);
                stack.decrement(j);
                itemStack.increment(j);
                bl = j > 0;
            }

            if (bl) {
                if (bl2 && to instanceof EnderHopperBlockEntityRedux) {
                    EnderHopperBlockEntityRedux hopperBlockEntity = (EnderHopperBlockEntityRedux)to;
                    if (!hopperBlockEntity.isDisabled()) {
                        int j = 0;
                        if (from instanceof EnderHopperBlockEntityRedux) {
                            EnderHopperBlockEntityRedux hopperBlockEntity2 = (EnderHopperBlockEntityRedux)from;
                            if (hopperBlockEntity.lastTickTime >= hopperBlockEntity2.lastTickTime) {
                                j = 1;
                            }
                        }

                        hopperBlockEntity.setTransferCooldown(hopperBlockEntity.itemTransferCooldown - j);
                    }
                }

                to.markDirty();
            }
        }

        return stack;
    }


    @Nullable
    private static Inventory getOutputInventory(World world, BlockPos pos, BlockState state) {
        Direction direction = (Direction)state.get(EnderHopperBlock.FACING);
        return getInventoryAt(world, pos.offset(direction).offset(direction));
    }

    @Nullable
    private static Inventory getInputInventory(World world, Hopper hopper) {
        return getInventoryAt(world, hopper.getHopperX(), hopper.getHopperY() + 2.0D, hopper.getHopperZ());
    }

    public static List<ItemEntity> getInputItemEntities(World world, Hopper hopper) {
        return (List)hopper.getInputAreaShape().getBoundingBoxes().stream().flatMap((box) -> {
            return world.getEntitiesByClass(ItemEntity.class, box.offset(hopper.getHopperX() - 0.5D, (hopper.getHopperY() - 0.5D)+1, hopper.getHopperZ() - 0.5D), EntityPredicates.VALID_ENTITY).stream();
        }).collect(Collectors.toList());
    }


    private boolean isDisabled() {
        return this.transferCooldown > this.itemTransferCooldown;
    }

}
