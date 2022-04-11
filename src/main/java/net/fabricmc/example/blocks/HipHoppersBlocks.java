package net.fabricmc.example.blocks;

import net.fabricmc.example.blocks.DownHoppers.GrabberBlock;
import net.fabricmc.example.blocks.DownHoppers.GrabberBlockEntity;
import net.fabricmc.example.blocks.DownHoppers.SkipperBlock;
import net.fabricmc.example.blocks.DownHoppers.SkipperBlockEntity;
import net.fabricmc.example.blocks.NormalHoppers.CustomHopperBlock;
import net.fabricmc.example.blocks.NormalHoppers.CustomHopperBlockEntity;
import net.fabricmc.example.blocks.UpHoppers.*;
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

    public static final SkupperBlock SKUPPER_BLOCK = new SkupperBlock(FabricBlockSettings.copyOf(Blocks.HOPPER),8);
    public static final SkupperBlock SPEEDY_SKUPPER_BLOCK = new SkupperBlock(FabricBlockSettings.copyOf(Blocks.HOPPER),4);
    public static final SkupperBlock SUPER_SPEEDY_SKUPPER_BLOCK = new SkupperBlock(FabricBlockSettings.copyOf(Blocks.HOPPER),2);
    public static BlockEntityType<SkupperBlockEntity> SKUPPER_BLOCK_ENTITY;

    public static final GrupperBlock GRUPPER_BLOCK = new GrupperBlock(FabricBlockSettings.copyOf(Blocks.HOPPER),8);
    public static final GrupperBlock SPEEDY_GRUPPER_BLOCK = new GrupperBlock(FabricBlockSettings.copyOf(Blocks.HOPPER),4);
    public static final GrupperBlock SUPER_SPEEDY_GRUPPER_BLOCK = new GrupperBlock(FabricBlockSettings.copyOf(Blocks.HOPPER),2);
    public static BlockEntityType<GrupperBlockEntity> GRUPPER_BLOCK_ENTITY;



    public static final SkipperBlock SKIPPER_BLOCK = new SkipperBlock(FabricBlockSettings.copyOf(Blocks.HOPPER),8);
    public static final SkipperBlock SPEEDY_SKIPPER_BLOCK = new SkipperBlock(FabricBlockSettings.copyOf(Blocks.HOPPER),4);
    public static final SkipperBlock SUPER_SPEEDY_SKIPPER_BLOCK = new SkipperBlock(FabricBlockSettings.copyOf(Blocks.HOPPER),2);
    public static BlockEntityType<SkipperBlockEntity> SKIPPER_BLOCK_ENTITY;


    public static final GrabberBlock GRABBER_BLOCK = new GrabberBlock(FabricBlockSettings.copyOf(Blocks.HOPPER),8);
    public static final GrabberBlock SPEEDY_GRABBER_BLOCK = new GrabberBlock(FabricBlockSettings.copyOf(Blocks.HOPPER),4);
    public static final GrabberBlock SUPER_SPEEDY_GRABBER_BLOCK = new GrabberBlock(FabricBlockSettings.copyOf(Blocks.HOPPER),2);
    public static BlockEntityType<GrabberBlockEntity> GRABBER_BLOCK_ENTITY;

    public static final CustomHopperBlock SPEEDY_HOPPER_BLOCK = new CustomHopperBlock(FabricBlockSettings.copyOf(Blocks.HOPPER),4);
    public static final CustomHopperBlock SUPER_SPEEDY_HOPPER_BLOCK = new CustomHopperBlock(FabricBlockSettings.copyOf(Blocks.HOPPER),2);
    public static BlockEntityType<CustomHopperBlockEntity> CUSTOM_HOPPER_BLOCK_ENTITY;


    public static void registerBlocks() {
        UPPER_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier("modid", "upper_entity"), FabricBlockEntityTypeBuilder.create(UpperBlockEntity::new, UPPER_BLOCK,SPEEDY_UPPER_BLOCK,SUPER_SPEEDY_UPPER_BLOCK).build(null));
        Registry.register(Registry.BLOCK, new Identifier("modid", "upper"), UPPER_BLOCK);
        Registry.register(Registry.ITEM, new Identifier("modid", "upper"), new BlockItem(UPPER_BLOCK, new Item.Settings().group(ItemGroup.REDSTONE)));

        Registry.register(Registry.BLOCK, new Identifier("modid", "speedy_upper"), SPEEDY_UPPER_BLOCK);
        Registry.register(Registry.ITEM, new Identifier("modid", "speedy_upper"), new BlockItem(SPEEDY_UPPER_BLOCK, new Item.Settings().group(ItemGroup.REDSTONE)));

        Registry.register(Registry.BLOCK, new Identifier("modid", "super_speedy_upper"), SUPER_SPEEDY_UPPER_BLOCK);
        Registry.register(Registry.ITEM, new Identifier("modid", "super_speedy_upper"), new BlockItem(SUPER_SPEEDY_UPPER_BLOCK, new Item.Settings().group(ItemGroup.REDSTONE)));



        SKUPPER_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier("modid", "skupper_entity"), FabricBlockEntityTypeBuilder.create(SkupperBlockEntity::new, SKUPPER_BLOCK,SPEEDY_SKUPPER_BLOCK,SUPER_SPEEDY_SKUPPER_BLOCK).build(null));
        Registry.register(Registry.BLOCK, new Identifier("modid", "skupper"), SKUPPER_BLOCK);
        Registry.register(Registry.ITEM, new Identifier("modid", "skupper"), new BlockItem(SKUPPER_BLOCK, new Item.Settings().group(ItemGroup.REDSTONE)));

        Registry.register(Registry.BLOCK, new Identifier("modid", "speedy_skupper"), SPEEDY_SKUPPER_BLOCK);
        Registry.register(Registry.ITEM, new Identifier("modid", "speedy_skupper"), new BlockItem(SPEEDY_SKUPPER_BLOCK, new Item.Settings().group(ItemGroup.REDSTONE)));

        Registry.register(Registry.BLOCK, new Identifier("modid", "super_speedy_skupper"), SUPER_SPEEDY_SKUPPER_BLOCK);
        Registry.register(Registry.ITEM, new Identifier("modid", "super_speedy_skupper"), new BlockItem(SUPER_SPEEDY_SKUPPER_BLOCK, new Item.Settings().group(ItemGroup.REDSTONE)));

        GRUPPER_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier("modid", "grupper_entity"), FabricBlockEntityTypeBuilder.create(GrupperBlockEntity::new,GRUPPER_BLOCK,SPEEDY_GRUPPER_BLOCK,SUPER_SPEEDY_GRUPPER_BLOCK).build(null));
        Registry.register(Registry.BLOCK, new Identifier("modid", "grupper"), GRUPPER_BLOCK);
        Registry.register(Registry.ITEM, new Identifier("modid", "grupper"), new BlockItem(GRUPPER_BLOCK, new Item.Settings().group(ItemGroup.REDSTONE)));

        Registry.register(Registry.BLOCK, new Identifier("modid", "speedy_grupper"), SPEEDY_GRUPPER_BLOCK);
        Registry.register(Registry.ITEM, new Identifier("modid", "speedy_grupper"), new BlockItem(SPEEDY_GRUPPER_BLOCK, new Item.Settings().group(ItemGroup.REDSTONE)));

        Registry.register(Registry.BLOCK, new Identifier("modid", "super_speedy_grupper"), SUPER_SPEEDY_GRUPPER_BLOCK);
        Registry.register(Registry.ITEM, new Identifier("modid", "super_speedy_grupper"), new BlockItem(SUPER_SPEEDY_GRUPPER_BLOCK, new Item.Settings().group(ItemGroup.REDSTONE)));



        SKIPPER_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier("modid", "skipper_entity"), FabricBlockEntityTypeBuilder.create(SkipperBlockEntity::new, SKIPPER_BLOCK, SPEEDY_SKIPPER_BLOCK, SUPER_SPEEDY_SKIPPER_BLOCK).build(null));
        Registry.register(Registry.BLOCK, new Identifier("modid", "skipper"), SKIPPER_BLOCK);
        Registry.register(Registry.ITEM, new Identifier("modid", "skipper"), new BlockItem(SKIPPER_BLOCK, new Item.Settings().group(ItemGroup.REDSTONE)));

        Registry.register(Registry.BLOCK, new Identifier("modid", "speedy_skipper"), SPEEDY_SKIPPER_BLOCK);
        Registry.register(Registry.ITEM, new Identifier("modid", "speedy_skipper"), new BlockItem(SPEEDY_SKIPPER_BLOCK, new Item.Settings().group(ItemGroup.REDSTONE)));

        Registry.register(Registry.BLOCK, new Identifier("modid", "super_speedy_skipper"), SUPER_SPEEDY_SKIPPER_BLOCK);
        Registry.register(Registry.ITEM, new Identifier("modid", "super_speedy_skipper"), new BlockItem(SUPER_SPEEDY_SKIPPER_BLOCK, new Item.Settings().group(ItemGroup.REDSTONE)));



        GRABBER_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier("modid", "jumper_entity"), FabricBlockEntityTypeBuilder.create(GrabberBlockEntity::new, GRABBER_BLOCK,SPEEDY_GRABBER_BLOCK,SUPER_SPEEDY_GRABBER_BLOCK).build(null));
        Registry.register(Registry.BLOCK, new Identifier("modid", "grabber"), GRABBER_BLOCK);
        Registry.register(Registry.ITEM, new Identifier("modid", "grabber"), new BlockItem(GRABBER_BLOCK, new Item.Settings().group(ItemGroup.REDSTONE)));

        Registry.register(Registry.BLOCK, new Identifier("modid", "speedy_grabber"), SPEEDY_GRABBER_BLOCK);
        Registry.register(Registry.ITEM, new Identifier("modid", "speedy_grabber"), new BlockItem(SPEEDY_GRABBER_BLOCK, new Item.Settings().group(ItemGroup.REDSTONE)));

        Registry.register(Registry.BLOCK, new Identifier("modid", "super_speedy_grabber"), SUPER_SPEEDY_GRABBER_BLOCK);
        Registry.register(Registry.ITEM, new Identifier("modid", "super_speedy_grabber"), new BlockItem(SUPER_SPEEDY_GRABBER_BLOCK, new Item.Settings().group(ItemGroup.REDSTONE)));




        CUSTOM_HOPPER_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier("modid", "custom_hopper_entity"), FabricBlockEntityTypeBuilder.create(CustomHopperBlockEntity::new,SPEEDY_HOPPER_BLOCK, SUPER_SPEEDY_HOPPER_BLOCK).build(null));
        Registry.register(Registry.BLOCK, new Identifier("modid", "speedy_hopper"), SPEEDY_HOPPER_BLOCK);
        Registry.register(Registry.ITEM, new Identifier("modid", "speedy_hopper"), new BlockItem(SPEEDY_HOPPER_BLOCK, new Item.Settings().group(ItemGroup.REDSTONE)));

        Registry.register(Registry.BLOCK, new Identifier("modid", "super_speedy_hopper"), SUPER_SPEEDY_HOPPER_BLOCK);
        Registry.register(Registry.ITEM, new Identifier("modid", "super_speedy_hopper"), new BlockItem(SUPER_SPEEDY_HOPPER_BLOCK, new Item.Settings().group(ItemGroup.REDSTONE)));
    }
}
