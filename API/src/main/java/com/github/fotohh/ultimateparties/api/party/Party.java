package com.github.fotohh.ultimateparties.api.party;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * The Party interface represents a party within the UltimateParties API.
 * A party is a group of players who can perform actions together.
 *
 * <p>Parties are identified by a unique UUID ({@link #getPartyUUID()}) and managed through various methods provided by this interface.</p>
 *
 * <p>Each party has a current owner ({@link #getCurrentOwner()}), who has certain privileges and responsibilities in the party.
 * Members of the party and their roles are stored in a HashMap with player UUIDs as keys and {@link MemberType} as values.
 * The party owner can promote and demote players between different roles.</p>
 *
 * <p>Parties can have various settings ({@link PartySettings}), and the list of settings for a party can be retrieved with {@link #getPartySettings()}.</p>
 *
 * @since 0.0.1
 */
public interface Party {

    /**
     * Gets the HashMap containing the members of the party and their respective roles ({@link MemberType}).
     *
     * @return The HashMap with player UUIDs as keys and their corresponding {@link MemberType} as values.
     * @since 0.0.1
     */
    HashMap<UUID, MemberType> getMembers();

    /**
     * Gets the {@link MemberType} of a specific player in the party.
     *
     * @param uuid The UUID of the player whose role is to be retrieved.
     * @return The {@link MemberType} of the specified player, or null if the player is not a member of the party.
     * @since 0.0.1
     */
    MemberType getMemberType(UUID uuid);

    /**
     * Adds a player to the party.
     * The player will be added with the default role ({@link MemberType#MEMBER}).
     *
     * @param uuid The UUID of the player to be added to the party.
     * @since 0.0.1
     */
    void addPlayer(UUID uuid);

    /**
     * Disbands the party, removing all members and settings.
     * After disbanding, the party will no longer exist.
     *
     * @since 0.0.1
     */
    void disband();

    /**
     * Gets the UUID of the party, which serves as a unique identifier for the party.
     *
     * @return The UUID of the party.
     * @since 0.0.1
     */
    UUID getPartyUUID();

    /**
     * Gets the UUID of the current owner in the party.
     *
     * @return The UUID of the current owner, or null if the party has no owner (e.g., after disbanding).
     * @since 0.0.1
     */
    UUID getCurrentOwner();

    /**
     * Sets the {@link MemberType} of a specific player in the party.
     *
     * @param uuid The UUID of the player whose role is to be set.
     * @param type The new {@link MemberType} for the specified player.
     * @since 0.0.1
     */
    void setMemberType(UUID uuid, MemberType type);

    /**
     * Gets the list of {@link PartySettings} for the party.
     *
     * @return The list of {@link PartySettings} for the party.
     * @since 0.0.1
     */
    List<PartySettings> getPartySettings();

    /**
     * Promotes a player in the party to a higher role (e.g., from {@link MemberType#MEMBER} to {@link MemberType#ELDER} to {@link MemberType#MODERATOR} to {@link MemberType#OWNER}).
     *
     * @param uuid The UUID of the player to be promoted.
     * @return true if the player was successfully promoted, false otherwise (e.g., player not found in the party, player is already the highest member role).
     * @since 0.0.1
     */
    boolean promotePlayer(UUID uuid);

    /**
     * Demotes a player in the party to a lower role (e.g., from {@link MemberType#OWNER} to {@link MemberType#MODERATOR} to {@link MemberType#ELDER} to {@link MemberType#MEMBER}).
     *
     * @param uuid The UUID of the player to be demoted.
     * @return true if the player was successfully demoted, false otherwise (e.g., player not found in the party, player is already the lowest member role).
     * @since 0.0.1
     */
    boolean demotePlayer(UUID uuid);

    /**
     * Announces a message to all members of the party.
     *
     * @param msg The message to be announced to the party members.
     * @since 0.0.1
     */
    void announceMessage(String msg);

    /**
     * Kicks a player from the party, removing them as a member.
     *
     * @param uuid The UUID of the player to be kicked.
     * @since 0.0.1
     */
    void kickPlayer(UUID uuid);

    /**
     * Invites a player to join the party.
     * The player receiving the invitation will be informed that they have been invited by the specified inviter.
     *
     * @param target  The UUID of the player being invited.
     * @param inviter The UUID of the player who is inviting the target.
     * @since 0.0.1
     */
    void invitePlayer(UUID target, UUID inviter);
}
