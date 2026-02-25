/**
 * PlayerHeadUtil - mc-heads.net avatar/head/body linkleri üretir.
 *
 * @author yakut
 */
package sex.yakut.mcdiscordbridge.utils;

import org.bukkit.entity.Player;

/**
 * Oyuncu kafası ve vücut görselleri için mc-heads.net linkleri.
 */
public class PlayerHeadUtil {
    public static String getAvatarUrl(Player player) {
        return "https://mc-heads.net/avatar/" + player.getUniqueId() + "/128.png";
    }
    public static String getBodyUrl(Player player) {
        return "https://mc-heads.net/body/" + player.getUniqueId() + "/128.png";
    }
    public static String getHeadUrl(Player player) {
        return "https://mc-heads.net/head/" + player.getUniqueId() + "/128.png";
    }
}
