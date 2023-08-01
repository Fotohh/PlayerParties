package com.github.fotohh.ultimateparties.player;

import com.github.fotohh.ultimateparties.api.player.PartyPlayer;
import com.github.fotohh.ultimateparties.api.player.PlayerManager;

import java.util.HashMap;
import java.util.UUID;

public class PlayerManagerImpl implements PlayerManager {

    private final HashMap<UUID, PartyPlayer> partyPlayers = new HashMap<>();

    @Override
    public HashMap<UUID, PartyPlayer> getPlayers() {
        return partyPlayers;
    }

    @Override
    public void addPlayer(UUID uuid) {
        partyPlayers.put(uuid, new PartyPlayerImpl(uuid));
    }

    @Override
    public void removePlayer(UUID uuid) {
        partyPlayers.remove(uuid);
    }

    @Override
    public boolean containsPlayer(UUID uuid) {
        return partyPlayers.containsKey(uuid);
    }

    @Override
    public PartyPlayer getPlayer(UUID uuid) {
        PartyPlayer player = partyPlayers.getOrDefault(uuid, new PartyPlayerImpl(uuid));
        partyPlayers.putIfAbsent(uuid, player);
        return player;
    }

}
