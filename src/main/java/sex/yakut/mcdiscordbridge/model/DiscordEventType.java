/**
 * DiscordEventType - Discord'a gönderilecek event tipleri.
 *
 * @author yakut
 */
package sex.yakut.mcdiscordbridge.model;

/**
 * Discord'a gönderilecek event tipleri.
 */
public enum DiscordEventType {
    CHAT,
    JOIN,
    QUIT,
    KICK,
    DEATH,
    ACHIEVEMENT,
    PLAYER_COMMAND,
    CONSOLE_COMMAND,
    BLOCK_BREAK,
    BLOCK_PLACE,
    SERVER_START,
    SERVER_STOP
}
