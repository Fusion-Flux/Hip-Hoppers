package net.fabricmc.example.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.example.blocks.HipHoppersBlocks;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.item.DyeableItem;

public class HipHoppersClient implements ClientModInitializer {
    public static void registerBlockRenderLayers() {
        BlockRenderLayerMap.INSTANCE.putBlock(HipHoppersBlocks.GRABBER_BLOCK, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(HipHoppersBlocks.SKIPPER_BLOCK, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(HipHoppersBlocks.GRUPPER_BLOCK, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(HipHoppersBlocks.SKUPPER_BLOCK, RenderLayer.getTranslucent());
    }

    @Override
    public void onInitializeClient() {
        registerBlockRenderLayers();
    }

}
