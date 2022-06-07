package com.fusionflux.hip_hoppers.mixin;

import com.fusionflux.hip_hoppers.blocks.DownHoppers.EnderHopperBlock;
import com.fusionflux.hip_hoppers.blocks.DownHoppers.EnderHopperBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import net.minecraft.block.BlockState;
import net.minecraft.block.HopperBlock;
import net.minecraft.block.entity.Hopper;
import net.minecraft.block.entity.HopperBlockEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import net.fabricmc.fabric.api.transfer.v1.item.InventoryStorage;
import net.fabricmc.fabric.api.transfer.v1.item.ItemStorage;
import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;
import net.fabricmc.fabric.api.transfer.v1.storage.StorageUtil;

@Mixin(EnderHopperBlockEntity.class)
public class HopperBlockEntityMixin {
    @Inject(
            method = "Lcom/fusionflux/hip_hoppers/blocks/DownHoppers/EnderHopperBlockEntity;insert(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;Lnet/minecraft/inventory/Inventory;)Z",
            at = @At(
                    value = "INVOKE_ASSIGN",
                    target = "Lcom/fusionflux/hip_hoppers/blocks/DownHoppers/EnderHopperBlockEntity;getOutputInventory(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;)Lnet/minecraft/inventory/Inventory;"
            ), locals = LocalCapture.CAPTURE_FAILHARD,
            cancellable = true
    )
    private static void hookInsert(World world, BlockPos pos, BlockState state, Inventory inventory, CallbackInfoReturnable<Boolean> cir, Inventory targetInventory) {
        // Let vanilla handle the transfer if it found an inventory.
        if (targetInventory != null) return;

        // Otherwise inject our transfer logic.
        Direction direction = state.get(EnderHopperBlock.FACING);
        BlockPos targetPos = pos.offset(direction).offset(direction);
        Storage<ItemVariant> target = ItemStorage.SIDED.find(world, targetPos, direction.getOpposite());

        if (target != null) {
            long moved = StorageUtil.move(
                    InventoryStorage.of(inventory, direction),
                    target,
                    iv -> true,
                    1,
                    null
            );
            cir.setReturnValue(moved == 1);
        }
    }

    @Inject(
            at = @At(
                    value = "INVOKE_ASSIGN",
                    target = "Lcom/fusionflux/hip_hoppers/blocks/DownHoppers/EnderHopperBlockEntity;getInputInventory(Lnet/minecraft/world/World;Lnet/minecraft/block/entity/Hopper;)Lnet/minecraft/inventory/Inventory;"
            ),
            method = "Lcom/fusionflux/hip_hoppers/blocks/DownHoppers/EnderHopperBlockEntity;extract(Lnet/minecraft/world/World;Lnet/minecraft/block/entity/Hopper;)Z",
            locals = LocalCapture.CAPTURE_FAILHARD,
            cancellable = true
    )
    private static void hookExtract(World world, Hopper hopper, CallbackInfoReturnable<Boolean> cir, Inventory inputInventory) {
        // Let vanilla handle the transfer if it found an inventory.
        if (inputInventory != null) return;

        // Otherwise inject our transfer logic.
        BlockPos sourcePos = new BlockPos(hopper.getHopperX(), hopper.getHopperY() + 2.0D, hopper.getHopperZ());
        Storage<ItemVariant> source = ItemStorage.SIDED.find(world, sourcePos, Direction.DOWN);

        if (source != null) {
            long moved = StorageUtil.move(
                    source,
                    InventoryStorage.of(hopper, Direction.UP),
                    iv -> true,
                    1,
                    null
            );
            cir.setReturnValue(moved == 1);
        }
    }
}