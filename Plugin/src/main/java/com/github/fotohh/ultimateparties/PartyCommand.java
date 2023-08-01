package com.github.fotohh.ultimateparties;

import com.github.fotohh.command.CommandHandler;
import com.github.fotohh.command.errors.NotEnoughArguments;
import com.github.fotohh.command.errors.SenderOnly;
import com.github.fotohh.ultimateparties.api.party.MemberType;
import com.github.fotohh.ultimateparties.api.party.Party;
import com.github.fotohh.ultimateparties.api.party.PartyInvite;
import com.github.fotohh.ultimateparties.api.party.PartySettings;
import com.github.fotohh.ultimateparties.api.player.PartyPlayer;
import com.github.fotohh.ultimateparties.party.PartyImpl;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class PartyCommand {

    public PartyCommand(UltimateParties parties) {

        NotEnoughArguments arguments = new NotEnoughArguments();
        arguments.setArgumentLength(1);

        CommandHandler handler = new CommandHandler("party", parties, new SenderOnly(), arguments);

        handler.setIncorrectSenderMessage("You must be a player to use this command!")
                .setUsageMessage("/party <arguments>")
                .setNoPermissionMessage("You do not have the correct permissions to use this command!", "playerparties.commands.party")
                .onExecute(commandExecution -> {

                    String[] args = commandExecution.getArgs();

                    if (args[0].equalsIgnoreCase("create")) {
                        new PartyImpl(commandExecution.getPlayer().getUniqueId(), parties);
                        commandExecution.getPlayer().sendMessage("Successfully created the party!");
                    }else if(args[0].equalsIgnoreCase("promote")){
                        Party party = parties.getPlayerManager().getPlayer(commandExecution.getPlayer().getUniqueId()).getParty();
                        if (party == null) {
                            commandExecution.getPlayer().sendMessage("You are not in a party!");
                            return;
                        }
                        if (party.getMemberType(commandExecution.getPlayer().getUniqueId()) == MemberType.MEMBER || party.getMemberType(commandExecution.getPlayer().getUniqueId()) == MemberType.ELDER) {
                            commandExecution.getPlayer().sendMessage("You do not have the required permissions to execute this command!");
                            return;
                        }
                        if(args.length == 2){
                            Player player = Bukkit.getPlayer(args[1]);
                            if(player == null || player.isOnline()){
                                commandExecution.getPlayer().sendMessage("That player is invalid!");
                                return;
                            }
                            if(!party.getMembers().containsKey(player.getUniqueId())){
                                commandExecution.getPlayer().sendMessage("That player is not in the party!");
                            }
                        }else{
                            commandExecution.getPlayer().sendMessage("Incorrect usage! /party demote <player>");
                        }
                    } else if (args[0].equalsIgnoreCase("demote")) {
                        Party party = parties.getPlayerManager().getPlayer(commandExecution.getPlayer().getUniqueId()).getParty();
                        if (party == null) {
                            commandExecution.getPlayer().sendMessage("You are not in a party!");
                            return;
                        }
                        if (party.getMemberType(commandExecution.getPlayer().getUniqueId()) == MemberType.MEMBER || party.getMemberType(commandExecution.getPlayer().getUniqueId()) == MemberType.ELDER) {
                            commandExecution.getPlayer().sendMessage("You do not have the required permissions to execute this command!");
                            return;
                        }
                        if(args.length == 2){

                        }else{
                            commandExecution.getPlayer().sendMessage("Incorrect usage! /party demote <player>");
                        }
                    } else if (args[0].equalsIgnoreCase("settings")) {

                        Party party = parties.getPlayerManager().getPlayer(commandExecution.getPlayer().getUniqueId()).getParty();
                        if (party == null) {
                            commandExecution.getPlayer().sendMessage("You are not in a party!");
                            return;
                        }
                        if (party.getMemberType(commandExecution.getPlayer().getUniqueId()) == MemberType.MEMBER || party.getMemberType(commandExecution.getPlayer().getUniqueId()) == MemberType.ELDER) {
                            commandExecution.getPlayer().sendMessage("You do not have the required permissions to execute this command!");
                            return;
                        }

                        if (args.length == 1) {
                            String msg = """
                                    -------Party Settings-------
                                                                        
                                    """;
                            for (PartySettings settings : party.getPartySettings()) {
                                msg = msg.concat(settings.toString() + "(" + settings.getDefaultValue() + ")\n");
                            }

                            commandExecution.getPlayer().sendMessage(msg);

                        } else if (args.length == 3) {
                            String setting = args[1];
                            switch (setting) {
                                case "ALL_INVITE" -> {
                                    String boolString = args[2];
                                    if (boolString.equalsIgnoreCase("true") || boolString.equalsIgnoreCase("false")) {
                                        boolean value = Boolean.parseBoolean(boolString);
                                        party.getPartySettings().get(0).setDefaultValue(value);
                                        commandExecution.getPlayer().sendMessage("Successfully set value " + setting + " to " + value);
                                    } else {
                                        commandExecution.getPlayer().sendMessage("Please enter a valid value!");
                                    }
                                }
                                default -> {
                                    commandExecution.getPlayer().sendMessage("That setting does not exist!");
                                }
                            }
                        } else {
                            commandExecution.sendIncorrectUsageMessage();
                        }

                    } else if (args[0].equalsIgnoreCase("invite")) {

                        if (args.length == 2) {

                            Party party = parties.getPlayerManager().getPlayer(commandExecution.getPlayer().getUniqueId()).getParty();
                            if (party == null) {
                                commandExecution.getPlayer().sendMessage("You are not in a party!");
                                return;
                            }

                            if (party.getMemberType(commandExecution.getPlayer().getUniqueId()) == MemberType.MEMBER && !party.getPartySettings().get(0).getDefaultValue()) {
                                commandExecution.getPlayer().sendMessage("You do not have the required permissions to execute this command!");
                                return;
                            }

                            Player player = Bukkit.getPlayer(args[1]);

                            if (player == null || !player.isOnline()) {
                                commandExecution.getPlayer().sendMessage("This player is invalid or not online!");
                                return;
                            }

                            if (player.getUniqueId().equals(commandExecution.getPlayer().getUniqueId())) {
                                commandExecution.getPlayer().sendMessage("You cannot invite yourself!");
                                return;
                            }

                            parties.getPlayerManager().getPlayer(commandExecution.getPlayer().getUniqueId()).getParty().invitePlayer(player.getUniqueId(), commandExecution.getPlayer().getUniqueId());
                            player.sendMessage("You have been invited to " + commandExecution.getPlayer().getDisplayName() + "'s party! Run /party accept or /party accept " + commandExecution.getPlayer().getDisplayName());
                        } else {
                            commandExecution.getPlayer().sendMessage("You must add a target! /party invite <target>");
                        }
                    } else if (args[0].equalsIgnoreCase("disband")) {
                        Party party = parties.getPlayerManager().getPlayer(commandExecution.getPlayer().getUniqueId()).getParty();
                        if (party == null) {
                            commandExecution.getPlayer().sendMessage("You must be in a party to disband it!");
                            return;
                        }
                        if (party.getMemberType(commandExecution.getPlayer().getUniqueId()) != MemberType.OWNER) {
                            commandExecution.getPlayer().sendMessage("You do not have the required permissions to execute this command!");
                            return;
                        }
                        parties.getPartyManager().removeParty(party);
                    } else if (args[0].equalsIgnoreCase("kick")) {
                        Party party = parties.getPlayerManager().getPlayer(commandExecution.getPlayer().getUniqueId()).getParty();
                        if (party == null) {
                            commandExecution.getPlayer().sendMessage("You are not in a party!");
                            return;
                        }
                        if (party.getMemberType(commandExecution.getPlayer().getUniqueId()) == MemberType.MEMBER || party.getMemberType(commandExecution.getPlayer().getUniqueId()) == MemberType.ELDER) {
                            commandExecution.getPlayer().sendMessage("You do not have the required permissions to execute this command!");
                            return;
                        }
                        if (args.length != 2) {
                            commandExecution.getPlayer().sendMessage("You must add a target! /party kick <target>");
                            return;
                        }
                        Player player = Bukkit.getPlayer(args[1]);
                        if (player == null || !player.isOnline()) {
                            commandExecution.getPlayer().sendMessage("That player is invalid!");
                            return;
                        }
                        if (player.getUniqueId().equals(commandExecution.getPlayer().getUniqueId())) {
                            commandExecution.getPlayer().sendMessage("You cannot kick yourself!");
                            return;
                        }
                        if (!party.getMembers().containsKey(player.getUniqueId())) {
                            commandExecution.getPlayer().sendMessage("That player is not in the party!");
                            return;
                        }
                        if (party.getCurrentOwner().equals(player.getUniqueId()) || party.getMemberType(player.getUniqueId()) == MemberType.MODERATOR) {
                            commandExecution.getPlayer().sendMessage("You cannot kick that player!");
                            return;
                        }
                        party.kickPlayer(player.getUniqueId());
                        player.sendMessage("You have been kicked from the party!");
                    } else if (args[0].equalsIgnoreCase("leave")) {
                        Party party = parties.getPlayerManager().getPlayer(commandExecution.getPlayer().getUniqueId()).getParty();
                        if (party == null) {
                            commandExecution.getPlayer().sendMessage("You are not in a party!");
                            return;
                        }
                        if (party.getCurrentOwner().equals(commandExecution.getPlayer().getUniqueId())) {
                            commandExecution.getPlayer().sendMessage("You cannot leave your own party! Run /party disband");
                            return;
                        }
                        party.getMembers().remove(commandExecution.getPlayer().getUniqueId());
                        party.announceMessage(commandExecution.getPlayer().getDisplayName() + " has left the party!");
                    } else if (args[0].equalsIgnoreCase("accept")) {

                        PartyPlayer player = parties.getPlayerManager().getPlayer(commandExecution.getPlayer().getUniqueId());

                        if (player.getPartyInvites().isEmpty()) {
                            commandExecution.getPlayer().sendMessage("You must have a party invite pending to run this command!");
                            return;
                        }

                        PartyInvite latestInvite = player.getPartyInvites().get(0);


                        if (args.length == 2) {
                            for (PartyInvite invite : player.getPartyInvites()) {

                                Player p = Bukkit.getPlayer(invite.getInviter());

                                if (p.getDisplayName().equalsIgnoreCase(args[1])) {
                                    invite.getParty().addPlayer(commandExecution.getPlayer().getUniqueId());
                                    player.removePartyInvite(invite);
                                    invite.getParty().announceMessage(commandExecution.getPlayer().getDisplayName() + " has joined the party!");
                                    return;
                                }

                            }
                            commandExecution.getPlayer().sendMessage("You have no invites from that player!");
                        } else {
                            latestInvite.getParty().addPlayer(commandExecution.getPlayer().getUniqueId());
                            player.removePartyInvite(latestInvite);
                            latestInvite.getParty().announceMessage(commandExecution.getPlayer().getDisplayName() + " has joined the party!");
                        }
                    } else if (args[0].equalsIgnoreCase("deny")) {
                        PartyPlayer player = parties.getPlayerManager().getPlayer(commandExecution.getPlayer().getUniqueId());

                        if (player.getPartyInvites().isEmpty()) {
                            commandExecution.getPlayer().sendMessage("You must have a party invite pending to run this command!");
                            return;
                        }

                        PartyInvite latestInvite = player.getPartyInvites().get(0);


                        if (args.length == 2) {
                            for (PartyInvite invite : player.getPartyInvites()) {

                                Player p = Bukkit.getPlayer(invite.getInviter());

                                if (p.getDisplayName().equalsIgnoreCase(args[1])) {
                                    player.removePartyInvite(invite);
                                    Bukkit.getPlayer(invite.getInviter()).sendMessage(commandExecution.getPlayer().getDisplayName() + " has denied the invite!");
                                    return;
                                }

                            }
                            commandExecution.getPlayer().sendMessage("You have no invites from that player!");
                        } else {
                            player.removePartyInvite(latestInvite);
                            Bukkit.getPlayer(latestInvite.getInviter()).sendMessage(commandExecution.getPlayer().getDisplayName() + " has denied the invite!");
                        }
                    } else if (args[0].equalsIgnoreCase("list")) {
                        PartyPlayer player = parties.getPlayerManager().getPlayer(commandExecution.getPlayer().getUniqueId());

                        if (player.getParty() == null) {
                            commandExecution.getPlayer().sendMessage("You must be in a party to run this command!");
                            return;
                        }

                        Party party = player.getParty();

                        String msg = String.format("""
                                -------Party List-------
                                > (Owner) %s
                                                                
                                """, Bukkit.getPlayer(party.getCurrentOwner()).getDisplayName());

                        for (UUID uuid : party.getMembers().keySet()) {
                            Player target = Bukkit.getPlayer(uuid);
                            MemberType type = party.getMembers().get(uuid);
                            msg = String.format(msg.concat("> (%s) %s\n"), type.toString(), target.getDisplayName());
                        }

                        commandExecution.getPlayer().sendMessage(msg);

                    }
                });
    }
}
