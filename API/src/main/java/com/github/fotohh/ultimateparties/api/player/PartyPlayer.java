
package com.github.fotohh.ultimateparties.api.player;

import com.github.fotohh.ultimateparties.api.party.Party;
import com.github.fotohh.ultimateparties.api.party.PartyInvite;

import java.util.ArrayList;
import java.util.UUID;

/**
 * This interface represents a party player.
 */
public interface PartyPlayer {

    void setParty(Party party);

    /**
     * Get the UUID of the party player.

     *
     * @return UUID of the party player
     */
    UUID getUUID();

    /**
     * Get the party of the player.
     *
     * @return Party of the player
     */
    Party getParty();

    boolean isInParty();

    void acceptInvite(PartyInvite invite);

    void addPartyInvite(PartyInvite invite);

    void removePartyInvite(PartyInvite invite);

    ArrayList<PartyInvite> getPartyInvites();


}
