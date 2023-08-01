package com.github.fotohh.ultimateparties.api.player;

import java.util.HashMap;
import java.util.UUID;

public interface PlayerManager {

    HashMap<UUID, PartyPlayer> getPlayers();

    void addPlayer(UUID uuid);

    void removePlayer(UUID uuid);

    boolean containsPlayer(UUID uuid);

    PartyPlayer getPlayer(UUID uuid);

}
