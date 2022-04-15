package com.fusionflux.hip_hoppers.client;

import net.fabricmc.api.ClientModInitializer;

public class HipHoppersClient implements ClientModInitializer {
    public static void registerBlockRenderLayers() {
    }

    @Override
    public void onInitializeClient() {
        registerBlockRenderLayers();
    }

}
