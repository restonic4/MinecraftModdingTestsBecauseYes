package com.restonic4.tests.client;

import com.chaotic_loom.under_control.api.whitelist.WhitelistAPI;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

import java.util.ArrayList;
import java.util.List;

import static com.restonic4.tests.Tests.MOD_ID;

public class TestsClient implements ClientModInitializer {
    private static final List<String> allowedPlayers = new ArrayList<>();

    @Override
    public void onInitializeClient() {
        allowedPlayers.add("restonic4");

        WhitelistAPI.registerWhitelist(MOD_ID, "early_access", (String username) -> allowedPlayers.contains(username));

        ClientTickEvents.START_CLIENT_TICK.register((minecraft) -> {
            //if (!WhitelistAPI.isAllowed(MOD_ID, "early_access",minecraft.getUser().getGameProfile().getName())) {
                //minecraft.setScreen(new FatalErrorScreen(Component.literal("Not allowed!"), Component.literal("You are not whitelisted to use this mod!")));
            //}
        });
    }
}
