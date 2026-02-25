package sex.yakut.mcdiscordbridge;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.HandlerList;
import sex.yakut.mcdiscordbridge.config.ConfigManager;
import sex.yakut.mcdiscordbridge.webhook.WebhookSender;
import sex.yakut.mcdiscordbridge.listener.*;
import sex.yakut.mcdiscordbridge.command.DiscordBridgeCommand;
import sex.yakut.mcdiscordbridge.manager.EventManager;
import sex.yakut.mcdiscordbridge.utils.ExceptionHandler;

public class MCDiscordBridge extends JavaPlugin {
    private static MCDiscordBridge instance;
    private ConfigManager configManager;
    private WebhookSender webhookSender;
    private EventManager eventManager;

    public static MCDiscordBridge getInstance() {
        return instance;
    }

    @Override
    public void onLoad() {
        instance = this;
    }

    @Override
    public void onEnable() {
        try {
            this.configManager = new ConfigManager(this);
            this.configManager.loadConfig();
            this.webhookSender = new WebhookSender(this.configManager);
            this.eventManager = new EventManager(this.configManager, this.webhookSender);
            registerListeners();
            getCommand("mdb").setExecutor(new DiscordBridgeCommand(this.configManager));
            this.eventManager.handleServerStart();
            getLogger().info("MCDiscordBridge başarıyla etkinleştirildi.");
        } catch (Exception e) {
            ExceptionHandler.handle(e, getLogger());
            getServer().getPluginManager().disablePlugin(this);
        }
    }

    @Override
    public void onDisable() {
        try {
            if (this.eventManager != null) {
                this.eventManager.handleServerStop();
            }
            HandlerList.unregisterAll(this);
            getLogger().info("MCDiscordBridge devre dışı bırakıldı.");
        } catch (Exception e) {
            ExceptionHandler.handle(e, getLogger());
        }
    }

    private void registerListeners() {
        Bukkit.getPluginManager().registerEvents(new PlayerChatListener(this.configManager, this.webhookSender), this);
        Bukkit.getPluginManager().registerEvents(new PlayerConnectionListener(this.configManager, this.webhookSender), this);
        Bukkit.getPluginManager().registerEvents(new PlayerDeathListener(this.configManager, this.webhookSender), this);
        String version = Bukkit.getServer().getBukkitVersion();
        int major = 1, minor = 8;
        try {
            String[] parts = version.split("\\.");
            if (parts.length >= 2) {
                major = Integer.parseInt(parts[0]);
                minor = Integer.parseInt(parts[1]);
            }
        } catch (Exception ignored) {}
        if (major > 1 || minor >= 13) {
            try {
                Class.forName("org.bukkit.event.player.PlayerAdvancementDoneEvent");
                Bukkit.getPluginManager().registerEvents(new PlayerAdvancementListener(this.configManager, this.webhookSender), this);
            } catch (Throwable t) {
                getLogger().warning("Advancement listener yüklenemedi: " + t.getMessage());
            }
        } else {
            Bukkit.getPluginManager().registerEvents(new PlayerAchievementListener(this.configManager, this.webhookSender), this);
        }
        Bukkit.getPluginManager().registerEvents(new PlayerCommandListener(this.configManager, this.webhookSender), this);
        Bukkit.getPluginManager().registerEvents(new ConsoleCommandListener(this.configManager, this.webhookSender), this);
        Bukkit.getPluginManager().registerEvents(new BlockInteractionListener(this.configManager, this.webhookSender), this);
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }
}