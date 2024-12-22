package net.mcreator.introvert.procedures;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.mcreator.introvert.IntrovertMod;

@Mod.EventBusSubscriber
public class WhisperProcedure {
    @SubscribeEvent
    public static void onRegisterCommands(RegisterCommandsEvent event) {
        CommandDispatcher<CommandSourceStack> dispatcher = event.getDispatcher();

        // Disable /msg
        dispatcher.register(Commands.literal("msg")
                .then(Commands.argument("target", StringArgumentType.word())
                        .then(Commands.argument("message", StringArgumentType.greedyString())
                                .executes(context -> blockMessage(context))))
        );

        // Disable /tell
        dispatcher.register(Commands.literal("tell")
                .then(Commands.argument("target", StringArgumentType.word())
                        .then(Commands.argument("message", StringArgumentType.greedyString())
                                .executes(context -> blockMessage(context))))
        );

        // Disable /w
        dispatcher.register(Commands.literal("w")
                .then(Commands.argument("target", StringArgumentType.word())
                        .then(Commands.argument("message", StringArgumentType.greedyString())
                                .executes(context -> blockMessage(context))))
        );
    }

    private static int blockMessage(CommandContext<CommandSourceStack> context) {
        CommandSourceStack source = context.getSource();

        // Notify the player that messaging is disabled
        source.sendFailure(net.minecraft.network.chat.Component.literal("Private messaging is disabled by the Introvert mod."));

        // Log the attempt
        IntrovertMod.LOGGER.info("Blocked private message attempt by player: {}", source.getTextName());

        // Always return success to prevent fallback behavior
        return Command.SINGLE_SUCCESS;
    }
}
