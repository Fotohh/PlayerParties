package com.github.fotohh.ultimateparties.api.events;

import com.github.fotohh.ultimateparties.api.party.Party;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class OnPartyCreate extends Event {

    private final HandlerList handlerList = new HandlerList();

    private final Party party;
    private final Player player;

    public OnPartyCreate(Party party, Player player){
        this.party = party;
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public Party getParty() {
        return party;
    }

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }

}
