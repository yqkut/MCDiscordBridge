package sex.yakut.mcdiscordbridge.listener;

import org.bukkit.Achievement;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerAchievementAwardedEvent;
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
    public void onPlayerAchievement(PlayerAchievementAwardedEvent event) {
        PluginConfig.AchievementEventConfig achCfg = (PluginConfig.AchievementEventConfig) configManager.getPluginConfig().events.achievement;
        if (!achCfg.enabled) return;
        Achievement achievement = event.getAchievement();
        String formatted = MessageFormatter.formatAchievement(event.getPlayer(), achievement, achCfg.format, achCfg.showDescription);
        if (achCfg.useEmbed) {
            webhookSender.sendAsync(EmbedFormatter.formatAchievementEmbed(event.getPlayer(), achievement, achCfg));
        } else {
            webhookSender.sendAsync(MessageFormatter.toJson(formatted));
        }
    }
}