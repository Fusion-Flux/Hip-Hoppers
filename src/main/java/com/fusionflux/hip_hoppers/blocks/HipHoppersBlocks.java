package com.fusionflux.hip_hoppers.blocks;

import com.fusionflux.hip_hoppers.blocks.DownHoppers.EnderHopperBlock;
import com.fusionflux.hip_hoppers.blocks.DownHoppers.EnderHopperBlockEntity;
import com.fusionflux.hip_hoppers.blocks.NormalHoppers.CustomHopperBlock;
import com.fusionflux.hip_hoppers.blocks.NormalHoppers.CustomHopperBlockEntity;
import com.fusionflux.hip_hoppers.blocks.UpHoppers.EnderUpperBlock;
import com.fusionflux.hip_hoppers.blocks.UpHoppers.EnderUpperBlockEntity;
import com.fusionflux.hip_hoppers.blocks.UpHoppers.UpperBlock;
import com.fusionflux.hip_hoppers.blocks.UpHoppers.UpperBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class HipHoppersBlocks {
    public static final UpperBlock UPPER_BLOCK = new UpperBlock(FabricBlockSettings.copyOf(Blocks.HOPPER),8);
    public static final UpperBlock SPEEDY_UPPER_BLOCK = new UpperBlock(FabricBlockSettings.copyOf(Blocks.HOPPER),4);
    public static final UpperBlock SUPER_SPEEDY_UPPER_BLOCK = new UpperBlock(FabricBlockSettings.copyOf(Blocks.HOPPER),2);
    public static BlockEntityType<UpperBlockEntity> UPPER_BLOCK_ENTITY;

    public static final EnderUpperBlock ENDER_UPPER_BLOCK = new EnderUpperBlock(FabricBlockSettings.copyOf(Blocks.HOPPER),8);
    public static final EnderUpperBlock SPEEDY_ENDER_UPPER_BLOCK = new EnderUpperBlock(FabricBlockSettings.copyOf(Blocks.HOPPER),4);
    public static final EnderUpperBlock SUPER_SPEEDY_ENDER_UPPER_BLOCK = new EnderUpperBlock(FabricBlockSettings.copyOf(Blocks.HOPPER),2);
    public static BlockEntityType<EnderUpperBlockEntity> ENDER_UPPER_BLOCK_ENTITY;

    public static final EnderHopperBlock ENDER_HOPPER_BLOCK = new EnderHopperBlock(FabricBlockSettings.copyOf(Blocks.HOPPER),8);
    public static final EnderHopperBlock SPEEDY_ENDER_HOPPER_BLOCK = new EnderHopperBlock(FabricBlockSettings.copyOf(Blocks.HOPPER),4);
    public static final EnderHopperBlock SUPER_SPEEDY_ENDER_HOPPER_BLOCK = new EnderHopperBlock(FabricBlockSettings.copyOf(Blocks.HOPPER),2);
    public static BlockEntityType<EnderHopperBlockEntity> ENDER_HOPPER_BLOCK_ENTITY;


    public static final CustomHopperBlock SPEEDY_HOPPER_BLOCK = new CustomHopperBlock(FabricBlockSettings.copyOf(Blocks.HOPPER),4);
    public static final CustomHopperBlock SUPER_SPEEDY_HOPPER_BLOCK = new CustomHopperBlock(FabricBlockSettings.copyOf(Blocks.HOPPER),2);
    public static BlockEntityType<CustomHopperBlockEntity> CUSTOM_HOPPER_BLOCK_ENTITY;


    public static void registerBlocks() {
        UPPER_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier("hip_hoppers", "upper_entity"), FabricBlockEntityTypeBuilder.create(UpperBlockEntity::new, UPPER_BLOCK,SPEEDY_UPPER_BLOCK,SUPER_SPEEDY_UPPER_BLOCK).build(null));
        Registry.register(Registry.BLOCK, new Identifier("hip_hoppers", "upper"), UPPER_BLOCK);
        Registry.register(Registry.ITEM, new Identifier("hip_hoppers", "upper"), new BlockItem(UPPER_BLOCK, new Item.Settings().group(ItemGroup.REDSTONE)));

        Registry.register(Registry.BLOCK, new Identifier("hip_hoppers", "speedy_upper"), SPEEDY_UPPER_BLOCK);
        Registry.register(Registry.ITEM, new Identifier("hip_hoppers", "speedy_upper"), new BlockItem(SPEEDY_UPPER_BLOCK, new Item.Settings().group(ItemGroup.REDSTONE)));

        Registry.register(Registry.BLOCK, new Identifier("hip_hoppers", "super_speedy_upper"), SUPER_SPEEDY_UPPER_BLOCK);
        Registry.register(Registry.ITEM, new Identifier("hip_hoppers", "super_speedy_upper"), new BlockItem(SUPER_SPEEDY_UPPER_BLOCK, new Item.Settings().group(ItemGroup.REDSTONE)));


        ENDER_UPPER_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier("hip_hoppers", "ender_upper_entity"), FabricBlockEntityTypeBuilder.create(EnderUpperBlockEntity::new, ENDER_UPPER_BLOCK,SPEEDY_ENDER_UPPER_BLOCK,SUPER_SPEEDY_ENDER_UPPER_BLOCK).build(null));
        Registry.register(Registry.BLOCK, new Identifier("hip_hoppers", "ender_upper"), ENDER_UPPER_BLOCK);
        Registry.register(Registry.ITEM, new Identifier("hip_hoppers", "ender_upper"), new BlockItem(ENDER_UPPER_BLOCK, new Item.Settings().group(ItemGroup.REDSTONE)));

        Registry.register(Registry.BLOCK, new Identifier("hip_hoppers", "speedy_ender_upper"), SPEEDY_ENDER_UPPER_BLOCK);
        Registry.register(Registry.ITEM, new Identifier("hip_hoppers", "speedy_ender_upper"), new BlockItem(SPEEDY_ENDER_UPPER_BLOCK, new Item.Settings().group(ItemGroup.REDSTONE)));

        Registry.register(Registry.BLOCK, new Identifier("hip_hoppers", "super_speedy_ender_upper"), SUPER_SPEEDY_ENDER_UPPER_BLOCK);
        Registry.register(Registry.ITEM, new Identifier("hip_hoppers", "super_speedy_ender_upper"), new BlockItem(SUPER_SPEEDY_ENDER_UPPER_BLOCK, new Item.Settings().group(ItemGroup.REDSTONE)));


        ENDER_HOPPER_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier("hip_hoppers", "ender_hopper_entity"), FabricBlockEntityTypeBuilder.create(EnderHopperBlockEntity::new, ENDER_HOPPER_BLOCK, SPEEDY_ENDER_HOPPER_BLOCK, SUPER_SPEEDY_ENDER_HOPPER_BLOCK).build(null));
        Registry.register(Registry.BLOCK, new Identifier("hip_hoppers", "ender_hopper"), ENDER_HOPPER_BLOCK);
        Registry.register(Registry.ITEM, new Identifier("hip_hoppers", "ender_hopper"), new BlockItem(ENDER_HOPPER_BLOCK, new Item.Settings().group(ItemGroup.REDSTONE)));

        Registry.register(Registry.BLOCK, new Identifier("hip_hoppers", "speedy_ender_hopper"), SPEEDY_ENDER_HOPPER_BLOCK);
        Registry.register(Registry.ITEM, new Identifier("hip_hoppers", "speedy_ender_hopper"), new BlockItem(SPEEDY_ENDER_HOPPER_BLOCK, new Item.Settings().group(ItemGroup.REDSTONE)));

        Registry.register(Registry.BLOCK, new Identifier("hip_hoppers", "super_speedy_ender_hopper"), SUPER_SPEEDY_ENDER_HOPPER_BLOCK);
        Registry.register(Registry.ITEM, new Identifier("hip_hoppers", "super_speedy_ender_hopper"), new BlockItem(SUPER_SPEEDY_ENDER_HOPPER_BLOCK, new Item.Settings().group(ItemGroup.REDSTONE)));





        CUSTOM_HOPPER_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier("hip_hoppers", "custom_hopper_entity"), FabricBlockEntityTypeBuilder.create(CustomHopperBlockEntity::new,SPEEDY_HOPPER_BLOCK, SUPER_SPEEDY_HOPPER_BLOCK).build(null));
        Registry.register(Registry.BLOCK, new Identifier("hip_hoppers", "speedy_hopper"), SPEEDY_HOPPER_BLOCK);
        Registry.register(Registry.ITEM, new Identifier("hip_hoppers", "speedy_hopper"), new BlockItem(SPEEDY_HOPPER_BLOCK, new Item.Settings().group(ItemGroup.REDSTONE)));

        Registry.register(Registry.BLOCK, new Identifier("hip_hoppers", "super_speedy_hopper"), SUPER_SPEEDY_HOPPER_BLOCK);
        Registry.register(Registry.ITEM, new Identifier("hip_hoppers", "super_speedy_hopper"), new BlockItem(SUPER_SPEEDY_HOPPER_BLOCK, new Item.Settings().group(ItemGroup.REDSTONE)));
    }
}
