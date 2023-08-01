/**
 * The package com.github.fotohh.ultimateparties.api.party contains interfaces and classes related to the Party system in the UltimateParties API.
 * Parties allow players to form groups and perform actions together.
 *
 * <p>The {@link com.github.fotohh.ultimateparties.api.party.PartyManager} interface provides methods to manage parties,
 * including retrieving existing parties, adding new parties, and removing parties.</p>
 *
 * @since 0.0.1
 */
package com.github.fotohh.ultimateparties.api.party;

import java.util.ArrayList;

/**
 * The PartyManager interface defines methods to manage parties within the UltimateParties API.
 * It allows retrieval of existing parties, adding new parties, and removing parties.
 *
 * <p>Implementations of this interface can provide different ways of managing parties,
 * such as storing parties in memory, a database, or any other data structure.</p>
 *
 * @since 0.0.1
 */
public interface PartyManager {

    /**
     * Gets the list of existing parties managed by this PartyManager.
     *
     * @return The list of existing parties.
     * @since 0.0.1
     */
    ArrayList<Party> getParties();

    /**
     * Removes a party from the PartyManager.
     * This will disband the party and notify all members about the disbandment.
     *
     * @param party The party to be removed.
     * @since 0.0.1
     */
    void removeParty(Party party);

    /**
     * Adds a new party to the PartyManager.
     *
     * @param party The party to be added.
     * @since 0.0.1
     */
    void addParty(Party party);
}
