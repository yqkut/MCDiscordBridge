package sex.yakut.mcdiscordbridge.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import sex.yakut.mcdiscordbridge.config.ConfigManager;
import sex.yakut.mcdiscordbridge.config.PluginConfig;
import sex.yakut.mcdiscordbridge.webhook.WebhookSender;
import sex.yakut.mcdiscordbridge.formatter.MessageFormatter;
import sex.yakut.mcdiscordbridge.formatter.EmbedFormatter;

public class PlayerCommandListener extends BaseEventListener {
    public PlayerCommandListener(ConfigManager configManager, WebhookSender webhookSender) {
        super(configManager, webhookSender);
    }

    @EventHandler
    public void onPlayerCommand(PlayerCommandPreprocessEvent event) {
        PluginConfig.EventConfig cmdCfg = configManager.getPluginConfig().events.playerCommand;
        if (!cmdCfg.enabled) return;
        String formatted = MessageFormatter.formatPlayerCommand(event.getPlayer(), event.getMessage(), cmdCfg.format);
        if (cmdCfg.useEmbed) {
            webhookSender.sendAsync(EmbedFormatter.formatPlayerCommandEmbed(event.getPlayer(), event.getMessage(), cmdCfg));
        } else {
            webhookSender.sendAsync(MessageFormatter.toJson(formatted));
        }
    }
}