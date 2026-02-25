package sex.yakut.mcdiscordbridge.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import sex.yakut.mcdiscordbridge.config.ConfigManager;
import sex.yakut.mcdiscordbridge.config.PluginConfig;
import sex.yakut.mcdiscordbridge.webhook.WebhookSender;
import sex.yakut.mcdiscordbridge.formatter.MessageFormatter;
import sex.yakut.mcdiscordbridge.formatter.EmbedFormatter;

public class BlockInteractionListener extends BaseEventListener {
    public BlockInteractionListener(ConfigManager configManager, WebhookSender webhookSender) {
        super(configManager, webhookSender);
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        PluginConfig.EventConfig breakCfg = configManager.getPluginConfig().events.blockBreak;
        if (!breakCfg.enabled) return;
        String formatted = MessageFormatter.formatBlockBreak(event.getPlayer(), event.getBlock(), breakCfg.format);
        if (breakCfg.useEmbed) {
            webhookSender.sendAsync(EmbedFormatter.formatBlockBreakEmbed(event.getPlayer(), event.getBlock(), breakCfg));
        } else {
            webhookSender.sendAsync(MessageFormatter.toJson(formatted));
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        PluginConfig.EventConfig placeCfg = configManager.getPluginConfig().events.blockPlace;
        if (!placeCfg.enabled) return;
        String formatted = MessageFormatter.formatBlockPlace(event.getPlayer(), event.getBlock(), placeCfg.format);
        if (placeCfg.useEmbed) {
            webhookSender.sendAsync(EmbedFormatter.formatBlockPlaceEmbed(event.getPlayer(), event.getBlock(), placeCfg));
        } else {
            webhookSender.sendAsync(MessageFormatter.toJson(formatted));
        }
    }
}