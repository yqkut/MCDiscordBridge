package sex.yakut.mcdiscordbridge.formatter;

import org.bukkit.entity.Player;
import org.bukkit.block.Block;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.Achievement;
import sex.yakut.mcdiscordbridge.config.PluginConfig;

public class EmbedFormatter {
    // Her event için embed JSON'u döndüren static fonksiyonlar burada olacak.
    public static String formatChatEmbed(Player player, String message, PluginConfig.ChatEventConfig cfg) {
        String color = cfg.color != null ? cfg.color : "#00FF00";
        int colorInt = 0x00FF00;
        try { colorInt = Integer.parseInt(color.replace("#", ""), 16); } catch (Exception ignored) {}
        String playerName = player.getName();
        String avatarUrl = "https://mc-heads.net/avatar/" + player.getUniqueId() + "/128.png";
        return "{" +
                "\"username\": \"" + playerName + "\"," +
                "\"avatar_url\": \"" + avatarUrl + "\"," +
                "\"embeds\": [" +
                "  {" +
                "    \"description\": \"" + message.replace("\"", "\\\"") + "\"," +
                "    \"color\": " + colorInt +
                "  }" +
                "]" +
                "}";
    }
    public static String formatJoinEmbed(Player player, PluginConfig.EventConfig cfg) {
        String color = cfg.color != null ? cfg.color : "#3498db";
        int colorInt = 0x3498db;
        try { colorInt = Integer.parseInt(color.replace("#", ""), 16); } catch (Exception ignored) {}
        String playerName = player.getName();
        String avatarUrl = "https://mc-heads.net/avatar/" + player.getUniqueId() + "/128.png";
        return "{" +
                "\"username\": \"" + playerName + "\"," +
                "\"avatar_url\": \"" + avatarUrl + "\"," +
                "\"embeds\": [" +
                "  {" +
                "    \"title\": \"Sunucuya Katıldı\"," +
                "    \"description\": \"" + playerName + " sunucuya katıldı!\"," +
                "    \"color\": " + colorInt +
                "  }" +
                "]" +
                "}";
    }
    public static String formatQuitEmbed(Player player, PluginConfig.EventConfig cfg) {
        String color = cfg.color != null ? cfg.color : "#e67e22";
        int colorInt = 0xe67e22;
        try { colorInt = Integer.parseInt(color.replace("#", ""), 16); } catch (Exception ignored) {}
        String playerName = player.getName();
        String avatarUrl = "https://mc-heads.net/avatar/" + player.getUniqueId() + "/128.png";
        return "{" +
                "\"username\": \"" + playerName + "\"," +
                "\"avatar_url\": \"" + avatarUrl + "\"," +
                "\"embeds\": [" +
                "  {" +
                "    \"title\": \"Sunucudan Ayrıldı\"," +
                "    \"description\": \"" + playerName + " sunucudan ayrıldı!\"," +
                "    \"color\": " + colorInt +
                "  }" +
                "]" +
                "}";
    }
    public static String formatKickEmbed(Player player, String reason, PluginConfig.EventConfig cfg) {
        String color = cfg.color != null ? cfg.color : "#e74c3c";
        int colorInt = 0xe74c3c;
        try { colorInt = Integer.parseInt(color.replace("#", ""), 16); } catch (Exception ignored) {}
        String playerName = player.getName();
        String avatarUrl = "https://mc-heads.net/avatar/" + player.getUniqueId() + "/128.png";
        return "{" +
                "\"username\": \"" + playerName + "\"," +
                "\"avatar_url\": \"" + avatarUrl + "\"," +
                "\"embeds\": [" +
                "  {" +
                "    \"title\": \"Oyuncu Atıldı\"," +
                "    \"description\": \"" + playerName + " atıldı. Sebep: " + reason.replace("\"", "\\\"") + "\"," +
                "    \"color\": " + colorInt +
                "  }" +
                "]" +
                "}";
    }
    public static String formatDeathEmbed(Player player, PlayerDeathEvent event, PluginConfig.DeathEventConfig cfg) {
        String color = cfg.color != null ? cfg.color : "#c0392b";
        int colorInt = 0xc0392b;
        try { colorInt = Integer.parseInt(color.replace("#", ""), 16); } catch (Exception ignored) {}
        String playerName = player.getName();
        String avatarUrl = "https://mc-heads.net/avatar/" + player.getUniqueId() + "/128.png";
        String deathMsg = event.getDeathMessage();
        return "{" +
                "\"username\": \"" + playerName + "\"," +
                "\"avatar_url\": \"" + avatarUrl + "\"," +
                "\"embeds\": [" +
                "  {" +
                "    \"title\": \"Ölüm\"," +
                "    \"description\": \"" + playerName + " öldü: " + (deathMsg != null ? deathMsg.replace("\"", "\\\"") : "") + "\"," +
                "    \"color\": " + colorInt +
                "  }" +
                "]" +
                "}";
    }
    public static String formatAchievementEmbed(Player player, Achievement achievement, PluginConfig.AchievementEventConfig cfg) {
        String color = cfg.color != null ? cfg.color : "#f1c40f";
        int colorInt = 0xf1c40f;
        try { colorInt = Integer.parseInt(color.replace("#", ""), 16); } catch (Exception ignored) {}
        String playerName = player.getName();
        String avatarUrl = "https://mc-heads.net/avatar/" + player.getUniqueId() + "/128.png";
        String achievementName = achievement.name();
        return "{" +
                "\"username\": \"" + playerName + "\"," +
                "\"avatar_url\": \"" + avatarUrl + "\"," +
                "\"embeds\": [" +
                "  {" +
                "    \"title\": \"Başarı Kazanıldı\"," +
                "    \"description\": \"" + playerName + " başarı kazandı: " + achievementName.replace("\"", "\\\"") + "\"," +
                "    \"color\": " + colorInt +
                "  }" +
                "]" +
                "}";
    }
    public static String formatPlayerCommandEmbed(Player player, String command, PluginConfig.EventConfig cfg) {
        String color = cfg.color != null ? cfg.color : "#8e44ad";
        int colorInt = 0x8e44ad;
        try { colorInt = Integer.parseInt(color.replace("#", ""), 16); } catch (Exception ignored) {}
        String playerName = player.getName();
        String avatarUrl = "https://mc-heads.net/avatar/" + player.getUniqueId() + "/128.png";
        return "{" +
                "\"username\": \"" + playerName + "\"," +
                "\"avatar_url\": \"" + avatarUrl + "\"," +
                "\"embeds\": [" +
                "  {" +
                "    \"title\": \"Komut Kullanıldı\"," +
                "    \"description\": \"" + playerName + " komut kullandı: " + command.replace("\"", "\\\"") + "\"," +
                "    \"color\": " + colorInt +
                "  }" +
                "]" +
                "}";
    }
    public static String formatConsoleCommandEmbed(String command, PluginConfig.EventConfig cfg) {
        String color = cfg.color != null ? cfg.color : "#2ecc71";
        int colorInt = 0x2ecc71;
        try { colorInt = Integer.parseInt(color.replace("#", ""), 16); } catch (Exception ignored) {}
        return "{" +
                "\"username\": \"Konsol\"," +
                "\"embeds\": [" +
                "  {" +
                "    \"title\": \"Konsol Komutu\"," +
                "    \"description\": \"Konsoldan komut: " + command.replace("\"", "\\\"") + "\"," +
                "    \"color\": " + colorInt +
                "  }" +
                "]" +
                "}";
    }
    public static String formatBlockBreakEmbed(Player player, Block block, PluginConfig.EventConfig cfg) {
        String color = cfg.color != null ? cfg.color : "#16a085";
        int colorInt = 0x16a085;
        try { colorInt = Integer.parseInt(color.replace("#", ""), 16); } catch (Exception ignored) {}
        String playerName = player.getName();
        String avatarUrl = "https://mc-heads.net/avatar/" + player.getUniqueId() + "/128.png";
        String blockType = block.getType().name();
        String location = block.getLocation().toString();
        return "{" +
                "\"username\": \"" + playerName + "\"," +
                "\"avatar_url\": \"" + avatarUrl + "\"," +
                "\"embeds\": [" +
                "  {" +
                "    \"title\": \"Blok Kırıldı\"," +
                "    \"description\": \"" + playerName + " blok kırdı: " + blockType + " (" + location.replace("\"", "\\\"") + ")\"," +
                "    \"color\": " + colorInt +
                "  }" +
                "]" +
                "}";
    }
    public static String formatBlockPlaceEmbed(Player player, Block block, PluginConfig.EventConfig cfg) {
        String color = cfg.color != null ? cfg.color : "#27ae60";
        int colorInt = 0x27ae60;
        try { colorInt = Integer.parseInt(color.replace("#", ""), 16); } catch (Exception ignored) {}
        String playerName = player.getName();
        String avatarUrl = "https://mc-heads.net/avatar/" + player.getUniqueId() + "/128.png";
        String blockType = block.getType().name();
        String location = block.getLocation().toString();
        return "{" +
                "\"username\": \"" + playerName + "\"," +
                "\"avatar_url\": \"" + avatarUrl + "\"," +
                "\"embeds\": [" +
                "  {" +
                "    \"title\": \"Blok Koyuldu\"," +
                "    \"description\": \"" + playerName + " blok koydu: " + blockType + " (" + location.replace("\"", "\\\"") + ")\"," +
                "    \"color\": " + colorInt +
                "  }" +
                "]" +
                "}";
    }
    public static String formatServerStartEmbed(PluginConfig.EventConfig cfg) {
        String color = cfg.color != null ? cfg.color : "#2980b9";
        int colorInt = 0x2980b9;
        try { colorInt = Integer.parseInt(color.replace("#", ""), 16); } catch (Exception ignored) {}
        return "{" +
                "\"username\": \"Minecraft Sunucu\"," +
                "\"embeds\": [" +
                "  {" +
                "    \"title\": \"Sunucu Başlatıldı\"," +
                "    \"description\": \"Sunucu başarıyla başlatıldı!\"," +
                "    \"color\": " + colorInt +
                "  }" +
                "]" +
                "}";
    }
    public static String formatServerStopEmbed(PluginConfig.EventConfig cfg) {
        String color = cfg.color != null ? cfg.color : "#34495e";
        int colorInt = 0x34495e;
        try { colorInt = Integer.parseInt(color.replace("#", ""), 16); } catch (Exception ignored) {}
        return "{" +
                "\"username\": \"Minecraft Sunucu\"," +
                "\"embeds\": [" +
                "  {" +
                "    \"title\": \"Sunucu Kapatıldı\"," +
                "    \"description\": \"Sunucu başarıyla kapatıldı!\"," +
                "    \"color\": " + colorInt +
                "  }" +
                "]" +
                "}";
    }
    // 1.13+ için advancement embed
    public static String formatAdvancementEmbed(org.bukkit.entity.Player player, String advancementKey, sex.yakut.mcdiscordbridge.config.PluginConfig.AchievementEventConfig cfg) {
        String color = cfg.color != null ? cfg.color : "#f1c40f";
        int colorInt = 0xf1c40f;
        try { colorInt = Integer.parseInt(color.replace("#", ""), 16); } catch (Exception ignored) {}
        String playerName = player.getName();
        String avatarUrl = "https://mc-heads.net/avatar/" + player.getUniqueId() + "/128.png";
        return "{" +
                "\"username\": \"" + playerName + "\"," +
                "\"avatar_url\": \"" + avatarUrl + "\"," +
                "\"embeds\": [" +
                "  {" +
                "    \"title\": \"Advancement\"," +
                "    \"description\": \"" + playerName + " advancement kazandı: " + advancementKey.replace("\"", "\\\"") + "\"," +
                "    \"color\": " + colorInt +
                "  }" +
                "]" +
                "}";
    }
}