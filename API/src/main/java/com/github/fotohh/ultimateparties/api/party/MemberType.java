package com.github.fotohh.ultimateparties.api.party;

public enum MemberType {
    OWNER,
    MODERATOR,
    ELDER,
    MEMBER;

    public int getWeight(){
        return switch (this){
            case ELDER -> 2;
            case OWNER -> 4;
            case MODERATOR -> 3;
            default -> 1;
        };
    }
    public MemberType getRankByWeight(int value){
        return switch (value){
            default -> MEMBER;
            case 2 -> ELDER;
            case 3 -> MODERATOR;
            case 4 -> OWNER;
        };
    }
}
