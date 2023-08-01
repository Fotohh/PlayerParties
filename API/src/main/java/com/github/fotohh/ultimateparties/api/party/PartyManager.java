package com.github.fotohh.ultimateparties.api.party;

import java.util.ArrayList;

public interface PartyManager {

    ArrayList<Party> getParties();

    void disbandParty(Party party);

    void addParty(Party party);

}
