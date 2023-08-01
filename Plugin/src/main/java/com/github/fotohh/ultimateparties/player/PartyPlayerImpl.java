package com.github.fotohh.ultimateparties.player;

import com.github.fotohh.ultimateparties.UltimateParties;
import com.github.fotohh.ultimateparties.api.party.Party;
import com.github.fotohh.ultimateparties.api.party.PartyInvite;
import com.github.fotohh.ultimateparties.api.player.PartyPlayer;

import java.util.ArrayList;
import java.util.UUID;

public class PartyPlayerImpl implements PartyPlayer {

    private final ArrayList<PartyInvite> invites = new ArrayList<>();

    private final UUID uuid;

    private Party party;

    public PartyPlayerImpl(UUID uuid){
        this.uuid = uuid;
    }

    @Override
    public void setParty(Party party) {
        this.party = party;
    }

    @Override
    public UUID getUUID() {
        return uuid;
    }

    @Override
    public Party getParty() {
        return party;
    }

    @Override
    public boolean isInParty() {
        return party != null;
    }

    @Override
    public void acceptInvite(PartyInvite invite){
        invite.getParty().addPlayer(uuid);
    }

    @Override
    public void addPartyInvite(PartyInvite invite) {
        invites.add(invite);
    }

    @Override
    public void removePartyInvite(PartyInvite invite) {
        invites.remove(invite);
    }

    @Override
    public ArrayList<PartyInvite> getPartyInvites() {
        return invites;
    }

}
