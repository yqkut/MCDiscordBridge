package sex.yakut.mcdiscordbridge.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import sex.yakut.mcdiscordbridge.config.ConfigManager;
import sex.yakut.mcdiscordbridge.config.PluginConfig;
import sex.yakut.mcdiscordbridge.webhook.WebhookSender;
import sex.yakut.mcdiscordbridge.formatter.MessageFormatter;
import sex.yakut.mcdiscordbridge.formatter.EmbedFormatter;

public class PlayerChatListener extends BaseEventListener {
    public PlayerChatListener(ConfigManager configManager, WebhookSender webhookSender) {
        super(configManager, webhookSender);
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        PluginConfig.ChatEventConfig chatCfg = configManager.getPluginConfig().events.chat;
        if (!chatCfg.enabled) return;
        if (chatCfg.ignoreCommands && event.getMessage().startsWith("/")) return;
        String formatted = MessageFormatter.formatChat(event.getPlayer(), event.getMessage(), chatCfg.format);
        if (chatCfg.useEmbed) {
            String embedJson = EmbedFormatter.formatChatEmbed(event.getPlayer(), event.getMessage(), chatCfg);
            if (embedJson == null || embedJson.trim().equals("{}")) {
                webhookSender.getLogger().warning("Boş embed JSON, webhook gönderilmiyor. Message: " + event.getMessage());
                return;
            }
            webhookSender.sendAsync(embedJson);
        } else {
            String json = MessageFormatter.toJson(formatted);
            if (json == null || json.trim().equals("{}")) {
                webhookSender.getLogger().warning("Boş mesaj JSON, webhook gönderilmiyor. Message: " + event.getMessage());
                return;
            }
            webhookSender.sendAsync(json);
        }
    }
}