/**
 * ColorConverter - Hex renkleri int'e çevirir.
 *
 * @author yakut
 */
package sex.yakut.mcdiscordbridge.utils;

/**
 * Hex renk kodunu int'e çeviren yardımcı sınıf.
 */
public class ColorConverter {
    public static int hexToInt(String hex) {
        if (hex == null) return 0x00FF00;
        hex = hex.replace("#", "");
        try {
            return Integer.parseInt(hex, 16);
        } catch (Exception e) {
            return 0x00FF00;
        }
    }
}
