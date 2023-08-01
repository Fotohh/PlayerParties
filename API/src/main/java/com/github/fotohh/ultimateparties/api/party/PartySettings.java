package com.github.fotohh.ultimateparties.api.party;

public enum PartySettings {

    ALL_INVITE(false);

    private boolean defaultValue;

    PartySettings(boolean defaultValue){
        this.defaultValue = defaultValue;
    }

    public void setDefaultValue(boolean defaultValue) {
        this.defaultValue = defaultValue;
    }

    public boolean getDefaultValue() {
        return defaultValue;
    }

}
