package com.restonic4.tests;

import com.chaotic_loom.under_control.api.config.ConfigAPI;
import com.chaotic_loom.under_control.api.incompatibilities.IncompatibilitiesAPI;
import com.chaotic_loom.under_control.api.vanish.VanishAPI;
import com.chaotic_loom.under_control.commands.VanishCommand;
import com.chaotic_loom.under_control.config.ConfigProvider;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.border.WorldBorder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Tests implements ModInitializer {
    public static final String MOD_ID = "test";

    @Override
    public void onInitialize() {
        IncompatibilitiesAPI.registerIncompatibleMod(MOD_ID, "cheat");
        IncompatibilitiesAPI.registerIncompatibleMod(MOD_ID, "iris");

        ServerLifecycleEvents.SERVER_STARTED.register((server) -> {
            ConfigProvider configProvider = ConfigAPI.registerServerConfig(MOD_ID, server);
            configProvider.registerOption("i_forgor", true, "idk, i just forgor");
        });

        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            ServerCMD.register(dispatcher);
        });

        ServerTickEvents.START_SERVER_TICK.register((server) -> {
            for (ServerPlayer player : server.getPlayerList().getPlayers()) {
                if (player.isCrouching()) {
                    VanishAPI.vanish(player);
                } else {
                    VanishAPI.unVanish(player);
                }
            }
        });
    }
}
