package com.restonic4.tests;

import com.chaotic_loom.under_control.api.server.ServerAPI;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.server.level.ServerPlayer;

public class ServerCMD {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("tp_server")
                .requires(source -> source.hasPermission(2))
                .executes(ctx -> setVanish(ctx))
        );
    }

    private static int setVanish(CommandContext<CommandSourceStack> ctx) {
        try {
            ServerAPI.transferPlayer(ctx.getSource().getPlayer(), "restonic4-small-test.exaroton.me", 55463);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 1;
    }
}
