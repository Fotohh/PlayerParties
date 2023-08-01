package com.github.fotohh.ultimateparties.party;

import com.github.fotohh.ultimateparties.api.party.Party;
import com.github.fotohh.ultimateparties.api.party.PartyManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class PartyManagerImpl implements PartyManager {

    private final ArrayList<Party> parties = new ArrayList<>();

    @Override
    public ArrayList<Party> getParties() {
        return parties;
    }

    @Override
    public void disbandParty(Party party) {

        party.getMembers().forEach((uuid, type) -> {
            Player player = Bukkit.getPlayer(uuid);
            player.sendMessage("The party has been disbanded!");
        });

        party.disband();
    };

    @Override
    public void addParty(Party party) {
        parties.add(party);
    }

}
