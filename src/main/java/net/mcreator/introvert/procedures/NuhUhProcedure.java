package net.mcreator.introvert.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraft.network.chat.Component;

import net.mcreator.introvert.IntrovertMod;

@Mod.EventBusSubscriber
public class NuhUhProcedure {
    @SubscribeEvent
    public static void onChat(ServerChatEvent event) {
        execute(event);
    }

    public static void execute(ServerChatEvent event) {
        if (event == null) {
            return;
        }

        // Cancel the chat message
        event.setCanceled(true);

        // Optionally notify the player
        /*event.getPlayer().sendSystemMessage(
            Component.literal("Messaging is disabled by the Introvert mod.");
	);
	*/
	IntrovertMod.LOGGER.info("Message blocked because you supposed to be an Introvert!!!");
    }
}
