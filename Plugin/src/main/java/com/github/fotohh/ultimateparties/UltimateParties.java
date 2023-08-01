package com.github.fotohh.ultimateparties;

import com.github.fotohh.ultimateparties.api.UltimatePartiesAPI;
import com.github.fotohh.ultimateparties.api.party.PartyManager;
import com.github.fotohh.ultimateparties.api.player.PlayerManager;
import com.github.fotohh.ultimateparties.party.PartyManagerImpl;
import com.github.fotohh.ultimateparties.player.PlayerManagerImpl;
import org.bukkit.plugin.java.JavaPlugin;

public class UltimateParties extends JavaPlugin implements UltimatePartiesAPI {

    PlayerManager playerManager = new PlayerManagerImpl();
    PartyManager partyManager = new PartyManagerImpl();

    public PartyManager getPartyManager() {
        return partyManager;
    }

    public PlayerManager getPlayerManager() {
        return playerManager;
    }

    @Override
    public void onEnable() {
        new PartyCommand(this);
    }

    @Override
    public void onDisable() {

    }

    @Override
    public JavaPlugin getPlugin() {
        return this;
    }
}
