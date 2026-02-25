/**
 * ReloadSubCommand - /mdb reload alt komutunu işler.
 *
 * @author yakut
 */
package sex.yakut.mcdiscordbridge.command.subcommands;

import org.bukkit.command.CommandSender;
import org.bukkit.ChatColor;
import sex.yakut.mcdiscordbridge.config.ConfigManager;

/**
 * /mdb reload komutunu yöneten sınıf.
 */
public class ReloadSubCommand {
    private final ConfigManager configManager;

    public ReloadSubCommand(ConfigManager configManager) {
        this.configManager = configManager;
    }

    public boolean execute(CommandSender sender, String[] args) {
        configManager.reloadConfig();
        sender.sendMessage(ChatColor.GREEN + "Yapılandırma başarıyla yeniden yüklendi!");
        return true;
    }
}
