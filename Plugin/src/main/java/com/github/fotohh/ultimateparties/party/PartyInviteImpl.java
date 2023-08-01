package com.github.fotohh.ultimateparties.party;

import com.github.fotohh.ultimateparties.api.party.Party;
import com.github.fotohh.ultimateparties.api.party.PartyInvite;

import java.util.UUID;

public class PartyInviteImpl implements PartyInvite {

    private final Party party;

    private final UUID inviter;

    private final UUID target;

    public PartyInviteImpl(Party party, UUID i, UUID t){
        this.party = party;
        this.inviter = i;
        this.target = t;
    }

    @Override
    public Party getParty() {
        return party;
    }

    @Override
    public UUID getInviter() {
        return inviter;
    }

    @Override
    public UUID getTarget() {
        return target;
    }

}
