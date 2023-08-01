package com.github.fotohh.ultimateparties.api.party;

import java.util.UUID;

public interface PartyInvite {

    Party getParty();

    UUID getInviter();

    UUID getTarget();

}
