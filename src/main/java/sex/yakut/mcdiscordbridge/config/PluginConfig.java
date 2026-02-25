/**
 * PluginConfig - config.yml dosyasının POJO temsili.
 * Tüm ayarları ve event yapılandırmalarını içerir.
 *
 * @author yakut
 */
package sex.yakut.mcdiscordbridge.config;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import java.util.*;

/**
 * config.yml dosyasındaki tüm ayarları tutan POJO.
 */
public class PluginConfig {
    public WebhookConfig webhook;
    public ServerConfig server;
    public EventsConfig events;

    /**
     * config.yml'den PluginConfig oluşturur.
     * @param config Bukkit FileConfiguration
     * @return PluginConfig
     */
    public static PluginConfig from(FileConfiguration config) {
        PluginConfig pc = new PluginConfig();
        pc.webhook = WebhookConfig.from(config.getConfigurationSection("webhook"));
        pc.server = ServerConfig.from(config.getConfigurationSection("server"));
        pc.events = EventsConfig.from(config.getConfigurationSection("events"));
        return pc;
    }

    public static class WebhookConfig {
        public String url;
        public String username;
        public String avatarUrl;
        public static WebhookConfig from(ConfigurationSection section) {
            WebhookConfig wc = new WebhookConfig();
            wc.url = section.getString("url", "");
            wc.username = section.getString("username", "Minecraft Sunucu");
            wc.avatarUrl = section.getString("avatar-url", "");
            return wc;
        }
    }

    public static class ServerConfig {
        public String name;
        public String iconUrl;
        public static ServerConfig from(ConfigurationSection section) {
            ServerConfig sc = new ServerConfig();
            sc.name = section.getString("name", "My Awesome 1.8 Server");
            sc.iconUrl = section.getString("icon-url", "");
            return sc;
        }
    }

    public static class EventsConfig {
        public List<String> enabled;
        public ChatEventConfig chat;
        public EventConfig join, quit, kick, death, achievement, playerCommand, consoleCommand, blockBreak, blockPlace, serverStart, serverStop;
        public static EventsConfig from(ConfigurationSection section) {
            EventsConfig ec = new EventsConfig();
            ec.enabled = section.getStringList("enabled");
            ec.chat = ChatEventConfig.from(section.getConfigurationSection("chat"));
            ec.join = EventConfig.from(section.getConfigurationSection("join"));
            ec.quit = EventConfig.from(section.getConfigurationSection("quit"));
            ec.kick = EventConfig.from(section.getConfigurationSection("kick"));
            ec.death = DeathEventConfig.from(section.getConfigurationSection("death"));
            ec.achievement = AchievementEventConfig.from(section.getConfigurationSection("achievement"));
            ec.playerCommand = EventConfig.from(section.getConfigurationSection("player_command"));
            ec.consoleCommand = EventConfig.from(section.getConfigurationSection("console_command"));
            ec.blockBreak = EventConfig.from(section.getConfigurationSection("block_break"));
            ec.blockPlace = EventConfig.from(section.getConfigurationSection("block_place"));
            ec.serverStart = EventConfig.from(section.getConfigurationSection("server_start"));
            ec.serverStop = EventConfig.from(section.getConfigurationSection("server_stop"));
            return ec;
        }
    }

    public static class EventConfig {
        public boolean enabled;
        public boolean useEmbed;
        public String color;
        public String format;
        public static EventConfig from(ConfigurationSection section) {
            if (section == null) return new EventConfig();
            EventConfig ec = new EventConfig();
            ec.enabled = section.getBoolean("enabled", true);
            ec.useEmbed = section.getBoolean("use-embed", true);
            ec.color = section.getString("color", "#00FF00");
            ec.format = section.getString("format", "");
            return ec;
        }
    }

    public static class ChatEventConfig extends EventConfig {
        public boolean ignoreCommands;
        public static ChatEventConfig from(ConfigurationSection section) {
            ChatEventConfig cc = new ChatEventConfig();
            EventConfig base = EventConfig.from(section);
            cc.enabled = base.enabled;
            cc.useEmbed = base.useEmbed;
            cc.color = base.color;
            cc.format = base.format;
            cc.ignoreCommands = section.getBoolean("ignore-commands", true);
            return cc;
        }
    }

    public static class DeathEventConfig extends EventConfig {
        public boolean showCoordinates;
        public boolean showCause;
        public boolean showKiller;
        public static DeathEventConfig from(ConfigurationSection section) {
            DeathEventConfig dc = new DeathEventConfig();
            EventConfig base = EventConfig.from(section);
            dc.enabled = base.enabled;
            dc.useEmbed = base.useEmbed;
            dc.color = base.color;
            dc.format = base.format;
            dc.showCoordinates = section.getBoolean("show-coordinates", true);
            dc.showCause = section.getBoolean("show-cause", true);
            dc.showKiller = section.getBoolean("show-killer", true);
            return dc;
        }
    }

    public static class AchievementEventConfig extends EventConfig {
        public boolean showDescription;
        public static AchievementEventConfig from(ConfigurationSection section) {
            AchievementEventConfig ac = new AchievementEventConfig();
            EventConfig base = EventConfig.from(section);
            ac.enabled = base.enabled;
            ac.useEmbed = base.useEmbed;
            ac.color = base.color;
            ac.format = base.format;
            ac.showDescription = section.getBoolean("show-description", true);
            return ac;
        }
    }
}
