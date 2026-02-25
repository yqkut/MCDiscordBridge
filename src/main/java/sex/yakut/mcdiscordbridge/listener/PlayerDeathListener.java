package sex.yakut.mcdiscordbridge.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;
import sex.yakut.mcdiscordbridge.config.ConfigManager;
import sex.yakut.mcdiscordbridge.config.PluginConfig;
import sex.yakut.mcdiscordbridge.webhook.WebhookSender;
import sex.yakut.mcdiscordbridge.formatter.MessageFormatter;
import sex.yakut.mcdiscordbridge.formatter.EmbedFormatter;

public class PlayerDeathListener extends BaseEventListener {
    public PlayerDeathListener(ConfigManager configManager, WebhookSender webhookSender) {
        super(configManager, webhookSender);
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        PluginConfig.DeathEventConfig deathCfg = (PluginConfig.DeathEventConfig) configManager.getPluginConfig().events.death;
        if (!deathCfg.enabled) return;
        Player player = event.getEntity();
        String formatted = MessageFormatter.formatDeath(player, event, deathCfg);
        if (deathCfg.useEmbed) {
            webhookSender.sendAsync(EmbedFormatter.formatDeathEmbed(player, event, deathCfg));
        } else {
            webhookSender.sendAsync(MessageFormatter.toJson(formatted));
        }
    }
}