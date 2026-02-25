package sex.yakut.mcdiscordbridge.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerKickEvent;
import sex.yakut.mcdiscordbridge.config.ConfigManager;
import sex.yakut.mcdiscordbridge.config.PluginConfig;
import sex.yakut.mcdiscordbridge.webhook.WebhookSender;
import sex.yakut.mcdiscordbridge.formatter.MessageFormatter;
import sex.yakut.mcdiscordbridge.formatter.EmbedFormatter;

public class PlayerConnectionListener extends BaseEventListener {
    public PlayerConnectionListener(ConfigManager configManager, WebhookSender webhookSender) {
        super(configManager, webhookSender);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        PluginConfig.EventConfig joinCfg = configManager.getPluginConfig().events.join;
        if (!joinCfg.enabled) return;
        String formatted = MessageFormatter.formatJoin(event.getPlayer(), joinCfg.format);
        if (joinCfg.useEmbed) {
            webhookSender.sendAsync(EmbedFormatter.formatJoinEmbed(event.getPlayer(), joinCfg));
        } else {
            webhookSender.sendAsync(MessageFormatter.toJson(formatted));
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        PluginConfig.EventConfig quitCfg = configManager.getPluginConfig().events.quit;
        if (!quitCfg.enabled) return;
        String formatted = MessageFormatter.formatQuit(event.getPlayer(), quitCfg.format);
        if (quitCfg.useEmbed) {
            webhookSender.sendAsync(EmbedFormatter.formatQuitEmbed(event.getPlayer(), quitCfg));
        } else {
            webhookSender.sendAsync(MessageFormatter.toJson(formatted));
        }
    }

    @EventHandler
    public void onPlayerKick(PlayerKickEvent event) {
        PluginConfig.EventConfig kickCfg = configManager.getPluginConfig().events.kick;
        if (!kickCfg.enabled) return;
        String formatted = MessageFormatter.formatKick(event.getPlayer(), event.getReason(), kickCfg.format);
        if (kickCfg.useEmbed) {
            webhookSender.sendAsync(EmbedFormatter.formatKickEmbed(event.getPlayer(), event.getReason(), kickCfg));
        } else {
            webhookSender.sendAsync(MessageFormatter.toJson(formatted));
        }
    }
}