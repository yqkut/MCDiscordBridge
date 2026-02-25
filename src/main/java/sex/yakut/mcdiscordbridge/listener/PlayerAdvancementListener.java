package sex.yakut.mcdiscordbridge.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;
import sex.yakut.mcdiscordbridge.config.ConfigManager;
import sex.yakut.mcdiscordbridge.config.PluginConfig;
import sex.yakut.mcdiscordbridge.webhook.WebhookSender;
import sex.yakut.mcdiscordbridge.formatter.MessageFormatter;
import sex.yakut.mcdiscordbridge.formatter.EmbedFormatter;

public class PlayerAdvancementListener extends BaseEventListener {
    public PlayerAdvancementListener(ConfigManager configManager, WebhookSender webhookSender) {
        super(configManager, webhookSender);
    }

    @EventHandler
    public void onPlayerAdvancement(PlayerAdvancementDoneEvent event) {
        PluginConfig.AchievementEventConfig achCfg = (PluginConfig.AchievementEventConfig) configManager.getPluginConfig().events.achievement;
        if (!achCfg.enabled) return;
        String advancementKey = event.getAdvancement().getKey().getKey();
        String formatted = MessageFormatter.formatAchievement(event.getPlayer(), advancementKey, achCfg.format, achCfg.showDescription);
        if (achCfg.useEmbed) {
            webhookSender.sendAsync(EmbedFormatter.formatAdvancementEmbed(event.getPlayer(), advancementKey, achCfg));
        } else {
            webhookSender.sendAsync(MessageFormatter.toJson(formatted));
        }
    }
}