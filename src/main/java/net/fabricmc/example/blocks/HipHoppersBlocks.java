package net.fabricmc.example.blocks;

import net.fabricmc.example.blocks.DownHoppers.JumperBlock;
import net.fabricmc.example.blocks.DownHoppers.JumperBlockEntity;
import net.fabricmc.example.blocks.DownHoppers.SkipperBlock;
import net.fabricmc.example.blocks.DownHoppers.SkipperBlockEntity;
import net.fabricmc.example.blocks.UpHoppers.UpperBlock;
import net.fabricmc.example.blocks.UpHoppers.UpperBlockEntity;
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
    public static final UpperBlock UPPER_BLOCK = new UpperBlock(FabricBlockSettings.copyOf(Blocks.HOPPER));
    public static BlockEntityType<UpperBlockEntity> UPPER_BLOCK_ENTITY;


    public static final SkipperBlock SKIPPER_BLOCK = new SkipperBlock(FabricBlockSettings.copyOf(Blocks.HOPPER));
    public static BlockEntityType<SkipperBlockEntity> SKIPPER_BLOCK_ENTITY;

    public static final JumperBlock JUMPER_BLOCK = new JumperBlock(FabricBlockSettings.copyOf(Blocks.HOPPER));
    public static BlockEntityType<JumperBlockEntity> JUMPER_BLOCK_ENTITY;
    public static void registerBlocks() {
        UPPER_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier("modid", "upper_entity"), FabricBlockEntityTypeBuilder.create(UpperBlockEntity::new, UPPER_BLOCK).build(null));
        Registry.register(Registry.BLOCK, new Identifier("modid", "upper"), UPPER_BLOCK);
        Registry.register(Registry.ITEM, new Identifier("modid", "upper"), new BlockItem(UPPER_BLOCK, new Item.Settings().group(ItemGroup.REDSTONE)));


        SKIPPER_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier("modid", "skipper_entity"), FabricBlockEntityTypeBuilder.create(SkipperBlockEntity::new, SKIPPER_BLOCK).build(null));
        Registry.register(Registry.BLOCK, new Identifier("modid", "skipper"), SKIPPER_BLOCK);
        Registry.register(Registry.ITEM, new Identifier("modid", "skipper"), new BlockItem(SKIPPER_BLOCK, new Item.Settings().group(ItemGroup.REDSTONE)));

        JUMPER_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier("modid", "jumper_entity"), FabricBlockEntityTypeBuilder.create(JumperBlockEntity::new, JUMPER_BLOCK).build(null));
        Registry.register(Registry.BLOCK, new Identifier("modid", "jumper"), JUMPER_BLOCK);
        Registry.register(Registry.ITEM, new Identifier("modid", "jumper"), new BlockItem(JUMPER_BLOCK, new Item.Settings().group(ItemGroup.REDSTONE)));
    }
}
