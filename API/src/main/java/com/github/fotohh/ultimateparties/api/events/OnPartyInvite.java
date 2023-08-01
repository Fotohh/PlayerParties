package com.github.fotohh.ultimateparties.api.events;

import com.github.fotohh.ultimateparties.api.party.PartyInvite;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class OnPartyInvite extends Event {

    private static final HandlerList handlers = new HandlerList();

    private final PartyInvite partyInvite;

    public OnPartyInvite(PartyInvite invite){
        this.partyInvite = invite;
    }

    public PartyInvite getPartyInvite() {
        return partyInvite;
    }

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

}
