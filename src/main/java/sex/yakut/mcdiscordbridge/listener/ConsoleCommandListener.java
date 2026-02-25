package sex.yakut.mcdiscordbridge.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.server.ServerCommandEvent;
import sex.yakut.mcdiscordbridge.config.ConfigManager;
import sex.yakut.mcdiscordbridge.config.PluginConfig;
import sex.yakut.mcdiscordbridge.webhook.WebhookSender;
import sex.yakut.mcdiscordbridge.formatter.MessageFormatter;
import sex.yakut.mcdiscordbridge.formatter.EmbedFormatter;

public class ConsoleCommandListener extends BaseEventListener {
    public ConsoleCommandListener(ConfigManager configManager, WebhookSender webhookSender) {
        super(configManager, webhookSender);
    }

    @EventHandler
    public void onConsoleCommand(ServerCommandEvent event) {
        PluginConfig.EventConfig cmdCfg = configManager.getPluginConfig().events.consoleCommand;
        if (!cmdCfg.enabled) return;
        String formatted = MessageFormatter.formatConsoleCommand(event.getCommand(), cmdCfg.format);
        if (cmdCfg.useEmbed) {
            webhookSender.sendAsync(EmbedFormatter.formatConsoleCommandEmbed(event.getCommand(), cmdCfg));
        } else {
            webhookSender.sendAsync(MessageFormatter.toJson(formatted));
        }
    }
}