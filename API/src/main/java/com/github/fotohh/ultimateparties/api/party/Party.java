package com.github.fotohh.ultimateparties.api.party;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public interface Party {

    HashMap<UUID, MemberType> getMembers();

    MemberType getMemberType(UUID uuid);

    void addPlayer(UUID uuid);

    void disband();

    UUID getPartyUUID();

    UUID getCurrentOwner();

    void setMemberType(UUID uuid, MemberType type);

    List<PartySettings> getPartySettings();

    boolean promotePlayer(UUID uuid);

    boolean demotePlayer(UUID uuid);

    void announceMessage(String msg);

    void kickPlayer(UUID uuid);

    void invitePlayer(UUID target, UUID inviter);

}
