package com.github.fotohh.ultimateparties.party;

import com.github.fotohh.ultimateparties.UltimateParties;
import com.github.fotohh.ultimateparties.api.events.OnPartyCreate;
import com.github.fotohh.ultimateparties.api.events.OnPartyInvite;
import com.github.fotohh.ultimateparties.api.party.MemberType;
import com.github.fotohh.ultimateparties.api.party.Party;
import com.github.fotohh.ultimateparties.api.party.PartyInvite;
import com.github.fotohh.ultimateparties.api.party.PartySettings;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.*;

public class PartyImpl implements Party {

    private final HashMap<UUID, MemberType> members = new HashMap<>();

    private UUID currentOwnerUUID;

    private final UUID partyUUID;

    private final UltimateParties parties;

    public PartyImpl(UUID playerUUID, UltimateParties parties){
        currentOwnerUUID = playerUUID;
        partyUUID = UUID.randomUUID();
        this.parties = parties;
        settings.addAll(Arrays.asList(PartySettings.values()));
        parties.getServer().getPluginManager().callEvent(new OnPartyCreate(this, Bukkit.getPlayer(playerUUID)));
        parties.getPartyManager().addParty(this);
        parties.getPlayerManager().getPlayer(playerUUID).setParty(this);
    }

    @Override
    public void setMemberType(UUID uuid, MemberType type) {
        if (type == MemberType.OWNER) {
            demoteCurrentOwner();
            currentOwnerUUID = uuid;
        }
        members.replace(uuid, type);
    }

    private final List<PartySettings> settings = new ArrayList<>();

    @Override
    public HashMap<UUID, MemberType> getMembers() {
        return members;
    }

    @Override
    public MemberType getMemberType(UUID uuid) {
        return members.get(uuid);
    }

    @Override
    public void addPlayer(UUID uuid){
        members.put(uuid, MemberType.MEMBER);
        parties.getPlayerManager().getPlayer(uuid).setParty(this);
    }

    @Override
    public void disband(){
        members.clear();
        currentOwnerUUID = null;
        parties.getPartyManager().removeParty(this);
    }

    @Override
    public UUID getPartyUUID() {
        return partyUUID;
    }

    @Override
    public List<PartySettings> getPartySettings() {
        return settings;
    }

    @Override
    public boolean promotePlayer(UUID uuid) {
        return adjustPlayerRank(uuid, 1);
    }

    @Override
    public boolean demotePlayer(UUID uuid) {
        return adjustPlayerRank(uuid, -1);
    }

    @Override
    public UUID getCurrentOwner() {
        return currentOwnerUUID;
    }

    private boolean adjustPlayerRank(UUID uuid, int rankChange) {
        MemberType type = getMemberType(uuid);
        if (type == MemberType.MEMBER || type == MemberType.OWNER) {
            return false; // Can't promote a MEMBER to OWNER or demote an OWNER.
        }

        int weight = type.getWeight();
        MemberType newRank = type.getRankByWeight(weight + rankChange);

        if (newRank == MemberType.OWNER) {
            demoteCurrentOwner();
        }

        members.replace(uuid, newRank);

        if (newRank == MemberType.OWNER) {
            currentOwnerUUID = uuid; // Update the current owner UUID after promoting to OWNER
        }

        return true;
    }

    @Override
    public void announceMessage(String msg){
        for(UUID uuid : members.keySet()){
            Player player = Bukkit.getPlayer(uuid);
            player.sendMessage(msg);
        }
    }

    private void demoteCurrentOwner() {
        if (currentOwnerUUID != null) {
            demotePlayer(currentOwnerUUID);
            currentOwnerUUID = null; // Clear the current owner UUID after demotion
        }
    }

    @Override
    public void kickPlayer(UUID uuid) {
        members.remove(uuid);
    }

    @Override
    public void invitePlayer(UUID target, UUID inviter) {
        PartyInvite inv = new PartyInviteImpl(this,inviter,target);
        parties.getServer().getPluginManager().callEvent(new OnPartyInvite(inv));
        parties.getPlayerManager().getPlayer(target).addPartyInvite(inv);
    }
}
