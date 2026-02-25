/**
 * WebhookEmbed - Discord Webhook embed nesnesi.
 * Embed içeriğini temsil eden POJO.
 *
 * @author yakut
 */
package sex.yakut.mcdiscordbridge.webhook;

import java.util.ArrayList;
import java.util.List;

/**
 * Discord Webhook embed nesnesi.
 */
public class WebhookEmbed {
    public String title;
    public String description;
    public String url;
    public Integer color;
    public WebhookEmbedFooter footer;
    public WebhookEmbedImage image;
    public WebhookEmbedAuthor author;
    public List<WebhookEmbedField> fields = new ArrayList<>();

    public WebhookEmbed() {}
}
