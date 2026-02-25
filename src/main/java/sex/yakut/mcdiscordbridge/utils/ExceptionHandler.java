/**
 * ExceptionHandler - Hataları loglar ve yönetir.
 *
 * @author yakut
 */
package sex.yakut.mcdiscordbridge.utils;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Exception handling için yardımcı sınıf.
 */
public class ExceptionHandler {
    public static void handle(Exception e, Logger logger) {
        logger.log(Level.SEVERE, "[MCDiscordBridge] Hata oluştu: " + e.getMessage(), e);
    }
}
