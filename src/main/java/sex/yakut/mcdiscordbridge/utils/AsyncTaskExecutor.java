/**
 * AsyncTaskExecutor - BukkitScheduler ile asenkron task çalıştırır.
 *
 * @author yakut
 */
package sex.yakut.mcdiscordbridge.utils;

import org.bukkit.Bukkit;

/**
 * Asenkron task çalıştırmak için yardımcı sınıf.
 */
public class AsyncTaskExecutor {
    public static void runAsync(Runnable runnable) {
        Bukkit.getScheduler().runTaskAsynchronously(Bukkit.getPluginManager().getPlugin("MCDiscordBridge"), runnable);
    }
}
