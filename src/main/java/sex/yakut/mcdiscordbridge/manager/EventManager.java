package sex.yakut.mcdiscordbridge.manager;

import sex.yakut.mcdiscordbridge.config.ConfigManager;
import sex.yakut.mcdiscordbridge.webhook.WebhookSender;
import sex.yakut.mcdiscordbridge.formatter.EmbedFormatter;
import sex.yakut.mcdiscordbridge.config.PluginConfig;

public class EventManager {
    private final ConfigManager configManager;
    private final WebhookSender webhookSender;

    public EventManager(ConfigManager configManager, WebhookSender webhookSender) {
        this.configManager = configManager;
        this.webhookSender = webhookSender;
    }

    public void handleServerStart() {
        PluginConfig.EventConfig cfg = configManager.getPluginConfig().events.serverStart;
        if (cfg != null && cfg.enabled) {
            if (cfg.useEmbed) {
                webhookSender.sendAsync(EmbedFormatter.formatServerStartEmbed(cfg));
            } else {
                webhookSender.sendAsync("{\"content\": \"Sunucu başlatıldı.\"}");
            }
        }
    }

    public void handleServerStop() {
        PluginConfig.EventConfig cfg = configManager.getPluginConfig().events.serverStop;
        if (cfg != null && cfg.enabled) {
            if (cfg.useEmbed) {
                webhookSender.sendAsync(EmbedFormatter.formatServerStopEmbed(cfg));
            } else {
                webhookSender.sendAsync("{\"content\": \"Sunucu kapatıldı.\"}");
            }
        }
    }
}