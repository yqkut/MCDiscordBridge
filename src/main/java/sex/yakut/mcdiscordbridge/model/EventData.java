/**
 * EventData - Discord'a gönderilecek event verisi.
 *
 * @author yakut
 */
package sex.yakut.mcdiscordbridge.model;

import org.bukkit.entity.Player;
import org.bukkit.block.Block;
import org.bukkit.Achievement;
import org.bukkit.event.entity.PlayerDeathEvent;

/**
 * Discord'a gönderilecek event verisi.
 */
public class EventData {
    public DiscordEventType type;
    public Player player;
    public String message;
    public String reason;
    public Achievement achievement;
    public PlayerDeathEvent deathEvent;
    public Block block;
    public String command;
    public String world;
    public String location;
    public String serverName;
    public String serverIconUrl;
    public long timestamp;
    // ...gerekirse ek alanlar...
}
