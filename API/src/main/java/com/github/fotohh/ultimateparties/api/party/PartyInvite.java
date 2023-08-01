/**
 * The package com.github.fotohh.ultimateparties.api.party contains interfaces and classes related to the Party system in the UltimateParties API.
 * Parties allow players to form groups and perform actions together.
 *
 * <p>The core component of a party is the {@link com.github.fotohh.ultimateparties.api.party.Party} interface,
 * which defines methods for managing party members, ownership, settings, and more.</p>
 *
 * <p>The {@link com.github.fotohh.ultimateparties.api.party.PartyInvite} interface provides methods to handle party invitations,
 * allowing players to invite others to join their party.</p>
 *
 * @since 0.0.1
 */
package com.github.fotohh.ultimateparties.api.party;

import java.util.UUID;

/**
 * The PartyInvite interface represents a party invitation within the UltimateParties API.
 * A party invitation is created when a player invites another player to join their party.
 *
 * <p>Invitations are identified by the inviter's UUID ({@link #getInviter()}) and the target player's UUID ({@link #getTarget()}).
 * The invitation is associated with the {@link com.github.fotohh.ultimateparties.api.party.Party} the inviter belongs to.</p>
 *
 * @since 0.0.1
 */
public interface PartyInvite {

    /**
     * Gets the {@link Party} associated with this invitation.
     *
     * @return The Party associated with this invitation.
     * @since 0.0.1
     */
    Party getParty();

    /**
     * Gets the UUID of the player who sent the invitation (inviter).
     *
     * @return The UUID of the inviter.
     * @since 0.0.1
     */
    UUID getInviter();

    /**
     * Gets the UUID of the player who received the invitation (target).
     *
     * @return The UUID of the target player.
     * @since 0.0.1
     */
    UUID getTarget();
}
