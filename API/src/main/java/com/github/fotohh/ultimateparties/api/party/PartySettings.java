/**
 * The package com.github.fotohh.ultimateparties.api.party contains interfaces and classes related to the Party system in the UltimateParties API.
 * Parties allow players to form groups and perform actions together.
 *
 * <p>The {@link com.github.fotohh.ultimateparties.api.party.PartySettings} enum provides different settings that can be applied to a party.</p>
 *
 * @since 0.0.1
 */
package com.github.fotohh.ultimateparties.api.party;

/**
 * The PartySettings enum represents different settings that can be applied to a party in the UltimateParties API.
 * Each setting has an associated default value.
 *
 * <p>The available party settings are:</p>
 * <ul>
 *     <li>{@link #ALL_INVITE} - A setting that determines whether all members can invite others to join the party.
 *     The default value for this setting is {@code false}.</li>
 * </ul>
 *
 * <p>The enum provides methods to get and set the default value for each setting.</p>
 *
 * @since 0.0.1
 */
public enum PartySettings {

    /**
     * A setting that determines whether all members can invite others to join the party.
     * The default value for this setting is {@code false}.
     */
    ALL_INVITE(false);

    private boolean defaultValue;

    /**
     * Constructs a PartySettings enum with the specified default value.
     *
     * @param defaultValue The default value for the setting.
     * @since 0.0.1
     */
    PartySettings(boolean defaultValue) {
        this.defaultValue = defaultValue;
    }

    /**
     * Sets the default value for the setting.
     *
     * @param defaultValue The default value to be set.
     * @since 0.0.1
     */
    public void setDefaultValue(boolean defaultValue) {
        this.defaultValue = defaultValue;
    }

    /**
     * Gets the default value for the setting.
     *
     * @return The default value of the setting.
     * @since 0.0.1
     */
    public boolean getDefaultValue() {
        return defaultValue;
    }
}
