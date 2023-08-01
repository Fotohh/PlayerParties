package com.github.fotohh.ultimateparties;

import com.github.fotohh.command.CommandHandler;
import com.github.fotohh.command.errors.NotEnoughArguments;
import com.github.fotohh.command.errors.SenderOnly;
import com.github.fotohh.ultimateparties.api.party.MemberType;
import com.github.fotohh.ultimateparties.api.party.Party;
import com.github.fotohh.ultimateparties.api.party.PartyInvite;
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
                    } else if (args[0].equalsIgnoreCase("invite")) {

                        if (args.length == 2) {

                            Party party = parties.getPlayerManager().getPlayer(commandExecution.getPlayer().getUniqueId()).getParty();
                            if (party == null) {
                                commandExecution.getPlayer().sendMessage("You are not in a party!");
                                return;
                            }

                            Player player = Bukkit.getPlayer(UUID.fromString(args[1]));

                            if (player == null || !player.isOnline()) {
                                commandExecution.getPlayer().sendMessage("This player is invalid or not online!");
                                return;
                            }

                            parties.getPlayerManager().getPlayer(commandExecution.getPlayer().getUniqueId()).getParty().invitePlayer(player.getUniqueId(), commandExecution.getPlayer().getUniqueId());

                        } else {
                            commandExecution.getPlayer().sendMessage("You must add a target! /party invite <target>");
                        }
                    } else if (args[0].equalsIgnoreCase("disband")) {
                        Party party = parties.getPlayerManager().getPlayer(commandExecution.getPlayer().getUniqueId()).getParty();
                        if (party == null) {
                            commandExecution.getPlayer().sendMessage("You must be in a party to disband it!");
                            return;
                        }
                        parties.getPartyManager().disbandParty(party);
                    } else if (args[0].equalsIgnoreCase("kick")) {
                        Party party = parties.getPlayerManager().getPlayer(commandExecution.getPlayer().getUniqueId()).getParty();
                        if (party == null) {
                            commandExecution.getPlayer().sendMessage("You are not in a party!");
                            return;
                        }
                        if (args.length != 2) {
                            commandExecution.getPlayer().sendMessage("You must add a target! /party kick <target>");
                            return;
                        }
                        Player player = Bukkit.getPlayer(UUID.fromString(args[1]));
                        if (player == null || !player.isOnline()) {
                            commandExecution.getPlayer().sendMessage("That player is invalid!");
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
                                    invite.getParty().announceMessage(commandExecution.getPlayer().getDisplayName() + " has joined the party!");
                                    return;
                                }

                            }
                            commandExecution.getPlayer().sendMessage("You have no invites from that player!");
                        } else {
                            latestInvite.getParty().addPlayer(commandExecution.getPlayer().getUniqueId());
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
                                    player.getPartyInvites().remove(invite);
                                    Bukkit.getPlayer(invite.getInviter()).sendMessage(commandExecution.getPlayer().getDisplayName() + " has denied the invite!");
                                    return;
                                }

                            }
                            commandExecution.getPlayer().sendMessage("You have no invites from that player!");
                        } else {
                            player.getPartyInvites().remove(latestInvite);
                            Bukkit.getPlayer(latestInvite.getInviter()).sendMessage(commandExecution.getPlayer().getDisplayName() + " has denied the invite!");
                        }
                    }
                });
    }
}
