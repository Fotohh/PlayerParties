/**
 * The package com.github.fotohh.ultimateparties.api contains interfaces related to the Party system in the UltimateParties API.
 * These interfaces provide methods for accessing and managing the party functionality within the UltimateParties plugin.
 *
 * @since 0.0.1
 */
package com.github.fotohh.ultimateparties.api;

import com.github.fotohh.ultimateparties.api.party.PartyManager;
import com.github.fotohh.ultimateparties.api.player.PlayerManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * The UltimatePartiesAPI interface provides access to the party functionality of the UltimateParties plugin.
 * Plugins can use this interface to access the PartyManager and PlayerManager, allowing them to interact with parties and player data.
 *
 * <p>The interface provides methods to:</p>
 * <ul>
 *     <li>{@link #getPlugin()} - Get the JavaPlugin instance of the UltimateParties plugin.</li>
 *     <li>{@link #getPartyManager()} - Get the PartyManager, responsible for managing parties and party-related operations.</li>
 *     <li>{@link #getPlayerManager()} - Get the PlayerManager, responsible for managing player data and interactions within the party system.</li>
 * </ul>
 *
 * Example of usage:
 * <pre>
 * {@code @Override
 *     public void onEnable() {
 *         // Initialize the UltimatePartiesAPI instance
 *         ultimatePartiesAPI = (UltimatePartiesAPI) getServer().getPluginManager().getPlugin("UltimateParties");
 *
 *         if (ultimatePartiesAPI == null) {
 *             // UltimateParties plugin is not loaded or not found, handle the error accordingly
 *             getLogger().severe("UltimateParties plugin is not installed or not loaded. Disabling MyPlugin.");
 *             getServer().getPluginManager().disablePlugin(this);
 *             return;
 *         }
 *
 *         // Your plugin initialization code here...
 *     }}
 * </pre>
 *
 * <p>The UltimatePartiesAPI serves as the main entry point for plugins to access the party functionality provided by the UltimateParties plugin.</p>
 *
 * @since 0.0.1
 */
public interface UltimatePartiesAPI {

    /**
     * Gets the JavaPlugin instance of the UltimateParties plugin.
     *
     * @return The JavaPlugin instance of the UltimateParties plugin.
     * @since 0.0.1
     */
    JavaPlugin getPlugin();

    /**
     * Gets the PartyManager, responsible for managing parties and party-related operations.
     *
     * @return The PartyManager instance.
     * @since 0.0.1
     */
    PartyManager getPartyManager();

    /**
     * Gets the PlayerManager, responsible for managing player data and interactions within the party system.
     *
     * @return The PlayerManager instance.
     * @since 0.0.1
     */
    PlayerManager getPlayerManager();

}
