/**
 * DiscordWebhookClient - Discord Webhook API ile iletişim kuran sınıf.
 * HttpsURLConnection ile tam embed desteği sağlar.
 *
 * @author yakut
 */
package sex.yakut.mcdiscordbridge.webhook;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;

/**
 * Discord Webhook API'ye mesaj göndermek için kullanılan self-contained client.
 * Embed desteği ve hata yönetimi içerir.
 */
public class DiscordWebhookClient {
    private final String webhookUrl;
    private final Logger logger;

    /**
     * DiscordWebhookClient oluşturur.
     * @param webhookUrl Webhook URL
     * @param logger Logger
     */
    public DiscordWebhookClient(String webhookUrl, Logger logger) {
        this.webhookUrl = webhookUrl;
        this.logger = logger;
    }

    /**
     * Discord Webhook'a JSON payload gönderir.
     * @param jsonPayload Gönderilecek JSON
     * @return HTTP response code
     * @throws Exception Hata durumunda fırlatılır
     */
    public int send(String jsonPayload) throws Exception {
        if (webhookUrl == null || webhookUrl.isEmpty()) {
            logger.warning("Webhook URL tanımlı değil, mesaj gönderilemiyor.");
            return -1;
        }
        URL url = new URL(webhookUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("User-Agent", "MCDiscordBridge/1.0.0 (+https://github.com/yakut/MCDiscordBridge)");
        connection.setDoOutput(true);
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(5000);
        byte[] out = jsonPayload.getBytes(StandardCharsets.UTF_8);
        try (OutputStream os = connection.getOutputStream()) {
            os.write(out);
        }
        int responseCode = connection.getResponseCode();
        connection.disconnect();
        return responseCode;
    }
}
