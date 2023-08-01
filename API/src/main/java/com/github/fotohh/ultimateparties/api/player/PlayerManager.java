/**
 * The package com.github.fotohh.ultimateparties.api.player contains interfaces related to managing players and their interactions with the Party system in the UltimateParties API.
 * The PlayerManager interface provides methods for managing player data and interactions within the party functionality.
 *
 * @since 0.0.1
 */
package com.github.fotohh.ultimateparties.api.player;

import java.util.HashMap;
import java.util.UUID;

/**
 * The PlayerManager interface represents a manager responsible for handling player-related data and interactions in the UltimateParties API.
 * It provides methods to manage player data and retrieve information about players in the party system.
 *
 * <p>The interface provides methods to:</p>
 * <ul>
 *     <li>{@link #getPlayers()} - Get a HashMap of all registered players and their corresponding {@link PartyPlayer} objects.</li>
 *     <li>{@link #addPlayer(UUID)} - Add a player with the given UUID to the player manager.</li>
 *     <li>{@link #removePlayer(UUID)} - Remove a player with the given UUID from the player manager.</li>
 *     <li>{@link #containsPlayer(UUID)} - Check if the player manager contains a player with the given UUID.</li>
 *     <li>{@link #getPlayer(UUID)} - Get the {@link PartyPlayer} object for the player with the given UUID.</li>
 * </ul>
 *
 * <p>The PlayerManager is responsible for managing player data and interactions within the party system. Plugins can use this interface to access and modify player data for the UltimateParties API.</p>
 *
 * @since 0.0.1
 */
public interface PlayerManager {

    /**
     * Gets a HashMap containing all registered players and their corresponding {@link PartyPlayer} objects.
     *
     * @return A HashMap containing all registered players and their corresponding {@link PartyPlayer} objects.
     * @since 0.0.1
     */
    HashMap<UUID, PartyPlayer> getPlayers();

    /**
     * Adds a player with the given UUID to the player manager.
     *
     * @param uuid The UUID of the player to add.
     * @since 0.0.1
     */
    void addPlayer(UUID uuid);

    /**
     * Removes a player with the given UUID from the player manager.
     *
     * @param uuid The UUID of the player to remove.
     * @since 0.0.1
     */
    void removePlayer(UUID uuid);

    /**
     * Checks if the player manager contains a player with the given UUID.
     *
     * @param uuid The UUID of the player to check.
     * @return {@code true} if the player manager contains the player, {@code false} otherwise.
     * @since 0.0.1
     */
    boolean containsPlayer(UUID uuid);

    /**
     * Gets the {@link PartyPlayer} object for the player with the given UUID.
     *
     * @param uuid The UUID of the player to get the PartyPlayer object for.
     * @return The {@link PartyPlayer} object for the player with the given UUID, or {@code null} if the player is not registered.
     * @since 0.0.1
     */
    PartyPlayer getPlayer(UUID uuid);

}
