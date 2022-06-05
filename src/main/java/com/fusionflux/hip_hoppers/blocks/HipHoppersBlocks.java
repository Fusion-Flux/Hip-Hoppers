package com.fusionflux.hip_hoppers.blocks;

import com.fusionflux.hip_hoppers.HipHoppersMod;
import com.fusionflux.hip_hoppers.blocks.DownHoppers.EnderHopperBlock;
import com.fusionflux.hip_hoppers.blocks.DownHoppers.EnderHopperBlockEntity;
import com.fusionflux.hip_hoppers.blocks.DownHoppers.EnderHopperBlockEntityRedux;
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
    public static final UpperBlock COPPER_UPPER_BLOCK = new UpperBlock(FabricBlockSettings.copyOf(Blocks.HOPPER),4);
    public static final UpperBlock GOLD_PLATED_COPPER_UPPER_BLOCK = new UpperBlock(FabricBlockSettings.copyOf(Blocks.HOPPER),2);
    public static BlockEntityType<UpperBlockEntity> UPPER_BLOCK_ENTITY;

    public static final EnderUpperBlock ENDER_UPPER_BLOCK = new EnderUpperBlock(FabricBlockSettings.copyOf(Blocks.HOPPER),8);
    public static final EnderUpperBlock COPPER_ENDER_UPPER_BLOCK = new EnderUpperBlock(FabricBlockSettings.copyOf(Blocks.HOPPER),4);
    public static final EnderUpperBlock GOLD_PLATED_COPPER_ENDER_UPPER_BLOCK = new EnderUpperBlock(FabricBlockSettings.copyOf(Blocks.HOPPER),2);
    public static BlockEntityType<EnderUpperBlockEntity> ENDER_UPPER_BLOCK_ENTITY;

    public static final EnderHopperBlock ENDER_HOPPER_BLOCK = new EnderHopperBlock(FabricBlockSettings.copyOf(Blocks.HOPPER),8);
    public static final EnderHopperBlock COPPER_ENDER_HOPPER_BLOCK = new EnderHopperBlock(FabricBlockSettings.copyOf(Blocks.HOPPER),4);
    public static final EnderHopperBlock GOLD_PLATED_COPPER_ENDER_HOPPER_BLOCK = new EnderHopperBlock(FabricBlockSettings.copyOf(Blocks.HOPPER),2);
    public static BlockEntityType<EnderHopperBlockEntityRedux> ENDER_HOPPER_BLOCK_ENTITY;


    public static final CustomHopperBlock COPPER_HOPPER_BLOCK = new CustomHopperBlock(FabricBlockSettings.copyOf(Blocks.HOPPER),4);
    public static final CustomHopperBlock GOLD_PLATED_COPPER_HOPPER_BLOCK = new CustomHopperBlock(FabricBlockSettings.copyOf(Blocks.HOPPER),2);
    public static BlockEntityType<CustomHopperBlockEntity> CUSTOM_HOPPER_BLOCK_ENTITY;


    public static void registerBlocks() {

        ENDER_HOPPER_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier("hip_hoppers", "ender_hopper_entity"), FabricBlockEntityTypeBuilder.create(EnderHopperBlockEntityRedux::new, ENDER_HOPPER_BLOCK, COPPER_ENDER_HOPPER_BLOCK, GOLD_PLATED_COPPER_ENDER_HOPPER_BLOCK).build(null));
        Registry.register(Registry.BLOCK, new Identifier("hip_hoppers", "ender_hopper"), ENDER_HOPPER_BLOCK);
        Registry.register(Registry.ITEM, new Identifier("hip_hoppers", "ender_hopper"), new BlockItem(ENDER_HOPPER_BLOCK, new Item.Settings().group(HipHoppersMod.HipHoppersGroup)));

        Registry.register(Registry.BLOCK, new Identifier("hip_hoppers", "copper_ender_hopper"), COPPER_ENDER_HOPPER_BLOCK);
        Registry.register(Registry.ITEM, new Identifier("hip_hoppers", "copper_ender_hopper"), new BlockItem(COPPER_ENDER_HOPPER_BLOCK, new Item.Settings().group(HipHoppersMod.HipHoppersGroup)));

        Registry.register(Registry.BLOCK, new Identifier("hip_hoppers", "gold_plated_copper_ender_hopper"), GOLD_PLATED_COPPER_ENDER_HOPPER_BLOCK);
        Registry.register(Registry.ITEM, new Identifier("hip_hoppers", "gold_plated_copper_ender_hopper"), new BlockItem(GOLD_PLATED_COPPER_ENDER_HOPPER_BLOCK, new Item.Settings().group(HipHoppersMod.HipHoppersGroup)));

        CUSTOM_HOPPER_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier("hip_hoppers", "custom_hopper_entity"), FabricBlockEntityTypeBuilder.create(CustomHopperBlockEntity::new,COPPER_HOPPER_BLOCK, GOLD_PLATED_COPPER_HOPPER_BLOCK).build(null));
        Registry.register(Registry.BLOCK, new Identifier("hip_hoppers", "copper_hopper"), COPPER_HOPPER_BLOCK);
        Registry.register(Registry.ITEM, new Identifier("hip_hoppers", "copper_hopper"), new BlockItem(COPPER_HOPPER_BLOCK, new Item.Settings().group(HipHoppersMod.HipHoppersGroup)));

        Registry.register(Registry.BLOCK, new Identifier("hip_hoppers", "gold_plated_copper_hopper"), GOLD_PLATED_COPPER_HOPPER_BLOCK);
        Registry.register(Registry.ITEM, new Identifier("hip_hoppers", "gold_plated_copper_hopper"), new BlockItem(GOLD_PLATED_COPPER_HOPPER_BLOCK, new Item.Settings().group(HipHoppersMod.HipHoppersGroup)));



        UPPER_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier("hip_hoppers", "upper_entity"), FabricBlockEntityTypeBuilder.create(UpperBlockEntity::new, UPPER_BLOCK,COPPER_UPPER_BLOCK,GOLD_PLATED_COPPER_UPPER_BLOCK).build(null));
        Registry.register(Registry.BLOCK, new Identifier("hip_hoppers", "upper"), UPPER_BLOCK);
        Registry.register(Registry.ITEM, new Identifier("hip_hoppers", "upper"), new BlockItem(UPPER_BLOCK, new Item.Settings().group(HipHoppersMod.HipHoppersGroup)));

        Registry.register(Registry.BLOCK, new Identifier("hip_hoppers", "copper_upper"), COPPER_UPPER_BLOCK);
        Registry.register(Registry.ITEM, new Identifier("hip_hoppers", "copper_upper"), new BlockItem(COPPER_UPPER_BLOCK, new Item.Settings().group(HipHoppersMod.HipHoppersGroup)));

        Registry.register(Registry.BLOCK, new Identifier("hip_hoppers", "gold_plated_copper_upper"), GOLD_PLATED_COPPER_UPPER_BLOCK);
        Registry.register(Registry.ITEM, new Identifier("hip_hoppers", "gold_plated_copper_upper"), new BlockItem(GOLD_PLATED_COPPER_UPPER_BLOCK, new Item.Settings().group(HipHoppersMod.HipHoppersGroup)));

        ENDER_UPPER_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier("hip_hoppers", "ender_upper_entity"), FabricBlockEntityTypeBuilder.create(EnderUpperBlockEntity::new, ENDER_UPPER_BLOCK,COPPER_ENDER_UPPER_BLOCK,GOLD_PLATED_COPPER_ENDER_UPPER_BLOCK).build(null));
        Registry.register(Registry.BLOCK, new Identifier("hip_hoppers", "ender_upper"), ENDER_UPPER_BLOCK);
        Registry.register(Registry.ITEM, new Identifier("hip_hoppers", "ender_upper"), new BlockItem(ENDER_UPPER_BLOCK, new Item.Settings().group(HipHoppersMod.HipHoppersGroup)));

        Registry.register(Registry.BLOCK, new Identifier("hip_hoppers", "copper_ender_upper"), COPPER_ENDER_UPPER_BLOCK);
        Registry.register(Registry.ITEM, new Identifier("hip_hoppers", "copper_ender_upper"), new BlockItem(COPPER_ENDER_UPPER_BLOCK, new Item.Settings().group(HipHoppersMod.HipHoppersGroup)));

        Registry.register(Registry.BLOCK, new Identifier("hip_hoppers", "gold_plated_copper_ender_upper"), GOLD_PLATED_COPPER_ENDER_UPPER_BLOCK);
        Registry.register(Registry.ITEM, new Identifier("hip_hoppers", "gold_plated_copper_ender_upper"), new BlockItem(GOLD_PLATED_COPPER_ENDER_UPPER_BLOCK, new Item.Settings().group(HipHoppersMod.HipHoppersGroup)));

    }
}
