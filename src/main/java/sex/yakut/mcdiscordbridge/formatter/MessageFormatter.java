package sex.yakut.mcdiscordbridge.formatter;

import org.bukkit.entity.Player;
import org.bukkit.block.Block;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.Achievement;
import sex.yakut.mcdiscordbridge.config.PluginConfig;

public class MessageFormatter {
    public static String formatChat(Player player, String message, String format) {
        return format.replace("{player}", player.getName())
                .replace("{message}", message)
                .replace("{world}", player.getWorld().getName());
    }
    public static String formatJoin(Player player, String format) {
        return format.replace("{player}", player.getName())
                .replace("{world}", player.getWorld().getName());
    }
    public static String formatQuit(Player player, String format) {
        return format.replace("{player}", player.getName())
                .replace("{world}", player.getWorld().getName());
    }
    public static String formatKick(Player player, String reason, String format) {
        return format.replace("{player}", player.getName())
                .replace("{reason}", reason)
                .replace("{world}", player.getWorld().getName());
    }
    public static String formatDeath(Player player, PlayerDeathEvent event, PluginConfig.DeathEventConfig cfg) {
        String msg = event.getDeathMessage();
        String result = cfg.format.replace("{player}", player.getName())
                .replace("{death_message}", msg);
        return result;
    }
    public static String formatAchievement(Player player, Achievement achievement, String format, boolean showDesc) {
        String result = format.replace("{player}", player.getName())
                .replace("{achievement}", achievement.name());
        return result;
    }
    public static String formatAchievement(org.bukkit.entity.Player player, String advancementKey, String format, boolean showDesc) {
        return format.replace("{player}", player.getName())
                .replace("{achievement}", advancementKey)
                .replace("{world}", player.getWorld().getName());
    }
    public static String formatPlayerCommand(Player player, String command, String format) {
        return format.replace("{player}", player.getName())
                .replace("{command}", command);
    }
    public static String formatConsoleCommand(String command, String format) {
        return format.replace("{command}", command);
    }
    public static String formatBlockBreak(Player player, Block block, String format) {
        return format.replace("{player}", player.getName())
                .replace("{block}", block.getType().name())
                .replace("{location}", block.getLocation().toString());
    }
    public static String formatBlockPlace(Player player, Block block, String format) {
        return format.replace("{player}", player.getName())
                .replace("{block}", block.getType().name())
                .replace("{location}", block.getLocation().toString());
    }
    public static String toJson(String content) {
        return "{\"content\": " + escapeJson(content) + "}";
    }
    private static String escapeJson(String s) {
        return '"' + s.replace("\"", "\\\"") + '"';
    }
}