package sex.yakut.mcdiscordbridge.webhook;

import org.bukkit.Bukkit;
import sex.yakut.mcdiscordbridge.config.ConfigManager;
import sex.yakut.mcdiscordbridge.utils.AsyncTaskExecutor;
import sex.yakut.mcdiscordbridge.utils.ExceptionHandler;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class WebhookSender {
    private final DiscordWebhookClient client;
    private final Logger logger;
    private final BlockingQueue<String> queue = new LinkedBlockingQueue<>();
    private final int RATE_LIMIT_MS = 1500;
    private final int RETRY_COUNT = 3;
    private volatile boolean running = true;

    public WebhookSender(ConfigManager configManager) {
        this.logger = Bukkit.getLogger();
        this.client = new DiscordWebhookClient(
                configManager.getPluginConfig().webhook.url,
                logger
        );
        startWorker();
    }

    public void sendAsync(String jsonPayload) {
        queue.offer(jsonPayload);
    }

    private void startWorker() {
        AsyncTaskExecutor.runAsync(() -> {
            while (running) {
                try {
                    String payload = queue.poll(1, TimeUnit.SECONDS);
                    if (payload != null) {
                        sendWithRetry(payload);
                        Thread.sleep(RATE_LIMIT_MS);
                    }
                } catch (InterruptedException ignored) {
                } catch (Exception e) {
                    ExceptionHandler.handle(e, logger);
                }
            }
        });
    }

    private void sendWithRetry(String payload) {
        int attempt = 0;
        while (attempt < RETRY_COUNT) {
            try {
                int code = client.send(payload);
                if (code >= 200 && code < 300) {
                    logger.fine("Webhook gönderildi (" + code + ")");
                    return;
                } else {
                    logger.warning("Webhook gönderilemedi (HTTP " + code + ") - Payload: " + payload);
                }
            } catch (Exception e) {
                ExceptionHandler.handle(e, logger);
            }
            attempt++;
            try { Thread.sleep(1000); } catch (InterruptedException ignored) {}
        }
        logger.severe("Webhook 3 denemede gönderilemedi! Son payload: " + payload);
    }

    public void shutdown() {
        running = false;
    }

    public Logger getLogger() {
        return logger;
    }
}