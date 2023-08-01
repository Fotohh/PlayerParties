package com.github.fotohh.ultimateparties.api.party;

/**
 * The MemberType enum represents different roles that a player can have within a party in the UltimateParties API.
 * Each member type has an associated weight that determines their rank in the party hierarchy.
 *
 * <p>The available member types are:</p>
 * <ul>
 *     <li>{@link #OWNER} - The owner of the party, with the highest rank and privileges.</li>
 *     <li>{@link #MODERATOR} - A moderator in the party, with elevated privileges.</li>
 *     <li>{@link #ELDER} - An elder in the party, with higher privileges than regular members.</li>
 *     <li>{@link #MEMBER} - A regular member of the party with standard privileges.</li>
 * </ul>
 *
 * <p>The enum provides methods to get the weight of each member type and to retrieve the member type based on its weight value.</p>
 *
 * @since 0.0.1
 */
public enum MemberType {

    /**
     * The owner of the party, with the highest rank and privileges.
     */
    OWNER,

    /**
     * A moderator in the party, with elevated privileges.
     */
    MODERATOR,

    /**
     * An elder in the party, with higher privileges than regular members.
     */
    ELDER,

    /**
     * A regular member of the party with standard privileges.
     */
    MEMBER;

    /**
     * Gets the weight associated with the member type.
     * The weight determines the rank of the member type in the party hierarchy.
     *
     * @return The weight of the member type.
     * @since 0.0.1
     */
    public int getWeight() {
        return switch (this) {
            case ELDER -> 2;
            case OWNER -> 4;
            case MODERATOR -> 3;
            default -> 1;
        };
    }

    /**
     * Gets the {@link MemberType} based on the provided weight value.
     * This method allows for retrieving the member type corresponding to a specific rank in the party hierarchy.
     *
     * @param value The weight value representing the rank of the member type.
     * @return The corresponding {@link MemberType}, or {@link #MEMBER} if the weight value does not match any specific rank.
     * @since 0.0.1
     */
    public MemberType getRankByWeight(int value) {
        return switch (value) {
            default -> MEMBER;
            case 2 -> ELDER;
            case 3 -> MODERATOR;
            case 4 -> OWNER;
        };
    }
}
