/**
 * ConfigManager - Plugin yapılandırma yönetimi.
 * Config dosyasını yükler, kaydeder ve hot-reload desteği sunar.
 *
 * @author yakut
 */
package sex.yakut.mcdiscordbridge.config;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import sex.yakut.mcdiscordbridge.utils.ExceptionHandler;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * Plugin yapılandırmasını yöneten sınıf.
 * config.yml dosyasını okur, yazar ve PluginConfig POJO'suna mapler.
 */
public class ConfigManager {
    private final Plugin plugin;
    private final Logger logger;
    private File configFile;
    private FileConfiguration config;
    private PluginConfig pluginConfig;

    /**
     * ConfigManager oluşturur.
     * @param plugin Ana plugin referansı
     */
    public ConfigManager(Plugin plugin) {
        this.plugin = plugin;
        this.logger = plugin.getLogger();
    }

    /**
     * Config dosyasını yükler ve PluginConfig'e mapler.
     */
    public void loadConfig() {
        try {
            if (!plugin.getDataFolder().exists()) {
                plugin.getDataFolder().mkdirs();
            }
            configFile = new File(plugin.getDataFolder(), "config.yml");
            if (!configFile.exists()) {
                plugin.saveResource("config.yml", false);
            }
            config = YamlConfiguration.loadConfiguration(configFile);
            pluginConfig = PluginConfig.from(config);
            logger.info("Config başarıyla yüklendi.");
        } catch (Exception e) {
            ExceptionHandler.handle(e, logger);
        }
    }

    /**
     * Config dosyasını yeniden yükler (hot-reload).
     */
    public void reloadConfig() {
        loadConfig();
        logger.info("Config yeniden yüklendi.");
    }

    /**
     * Config dosyasını kaydeder.
     */
    public void saveConfig() {
        try {
            if (config != null && configFile != null) {
                config.save(configFile);
                logger.info("Config kaydedildi.");
            }
        } catch (IOException e) {
            ExceptionHandler.handle(e, logger);
        }
    }

    /**
     * Bukkit FileConfiguration erişimi.
     * @return FileConfiguration
     */
    public FileConfiguration getRawConfig() {
        return config;
    }

    /**
     * PluginConfig POJO erişimi.
     * @return PluginConfig
     */
    public PluginConfig getPluginConfig() {
        return pluginConfig;
    }
}
