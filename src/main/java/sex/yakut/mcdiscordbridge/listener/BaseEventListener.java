/**
 * BaseEventListener - Tüm event listener'lar için soyut temel sınıf.
 * Ortak bağımlılıkları ve yardımcı metodları içerir.
 *
 * @author yakut
 */
package sex.yakut.mcdiscordbridge.listener;

import org.bukkit.event.Listener;
import sex.yakut.mcdiscordbridge.config.ConfigManager;
import sex.yakut.mcdiscordbridge.webhook.WebhookSender;

/**
 * Tüm event listener'larının temelini oluşturan soyut sınıf.
 */
public abstract class BaseEventListener implements Listener {
    protected final ConfigManager configManager;
    protected final WebhookSender webhookSender;

    /**
     * Temel listener constructor.
     * @param configManager ConfigManager
     * @param webhookSender WebhookSender
     */
    public BaseEventListener(ConfigManager configManager, WebhookSender webhookSender) {
        this.configManager = configManager;
        this.webhookSender = webhookSender;
    }
}
