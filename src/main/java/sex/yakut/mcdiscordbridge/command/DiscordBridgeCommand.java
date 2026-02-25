/**
 * DiscordBridgeCommand - Ana /mdb komutu ve alt komutları yönetir.
 * /mdb reload, /mdb status vs.
 *
 * @author yakut
 */
package sex.yakut.mcdiscordbridge.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.ChatColor;
import sex.yakut.mcdiscordbridge.config.ConfigManager;
import sex.yakut.mcdiscordbridge.command.subcommands.ReloadSubCommand;

/**
 * /mdb ana komutunu yöneten sınıf.
 */
public class DiscordBridgeCommand implements CommandExecutor {
    private final ConfigManager configManager;
    private final ReloadSubCommand reloadSubCommand;

    public DiscordBridgeCommand(ConfigManager configManager) {
        this.configManager = configManager;
        this.reloadSubCommand = new ReloadSubCommand(configManager);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("mcdiscordbridge.admin")) {
            sender.sendMessage(ChatColor.RED + "Bu komutu kullanmak için yetkiniz yok!");
            return true;
        }
        if (args.length == 0) {
            sender.sendMessage(ChatColor.YELLOW + "/mdb reload - Yapılandırmayı yeniden yükle");
            sender.sendMessage(ChatColor.YELLOW + "/mdb status - Plugin durumu");
            return true;
        }
        if (args[0].equalsIgnoreCase("reload")) {
            return reloadSubCommand.execute(sender, args);
        }
        if (args[0].equalsIgnoreCase("status")) {
            sender.sendMessage(ChatColor.GREEN + "MCDiscordBridge v1.0.0 aktif.");
            return true;
        }
        sender.sendMessage(ChatColor.RED + "Bilinmeyen alt komut: " + args[0]);
        return true;
    }
}
