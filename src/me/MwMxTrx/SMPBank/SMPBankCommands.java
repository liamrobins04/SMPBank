package me.MwMxTrx.SMPBank;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SMPBankCommands implements CommandExecutor {
	/*
	 * SMPBank by MwMxTrx for ICU 4U (Engineering Design and Information Technology) Culminating Project, June 2021.
	 * Licensed under GNU GPLv3. See license.txt.
	 * All code is original unless stated with comments.
	 * Questions comments or concerns can be directed to my Spigot account.
	 *
	 * SMPBank - GUI-based banking system designed for survival multiplayer using Spigot's API.
	 * Copyright (C) 2021 Liam Robins
	 * 
	 */
	static SMPBank bank = SMPBank.getInstance();
	@Override
	public boolean onCommand(CommandSender p, Command command, String label, String[] args) {
		//Handles commands by players/server console within game.
		if(label.equalsIgnoreCase("smpbank") || label.equalsIgnoreCase("bank")) {
			//Chat commands for the plugin for administrational use
			if(args.length == 0) {
				p.sendMessage("§3SMPBank developed by MwMxTrx for IDC 4U Culminating Project.");
				p.sendMessage("§7Bank Prefix: §r" + bank.bankName());
				p.sendMessage("§7Bank Name: §r" + bank.simpleBankName());
				p.sendMessage("§7Plugin Version: §r" + bank.getDescription().getVersion());
				p.sendMessage("§7For a list of commands, use '/bank help'.");
			}else{
				if(args[0].equalsIgnoreCase("help") || args[0].equalsIgnoreCase("?")) {
					p.sendMessage("§3Commands for SMPBank:");
					p.sendMessage("§7/smpbank | /bank §rRoot command for SMPBank.");
					p.sendMessage("§7...help | ...?: §rShows a list of available commands for SMPBank.");
					p.sendMessage("§7...balance | ...bal: §rChecks your personal account balance.");
					if(p.hasPermission("smpbank.see") || p.isOp()) {
						p.sendMessage("§7...see (player): §rChecks the balance of another player.");
					}
					if(p.hasPermission("smpbank.note") || p.isOp()) {
						p.sendMessage("§7...note (value) [amount] [player]: §rGives bank note item.");
					}
					if(p.hasPermission("smpbank.account") || p.isOp()) {
						p.sendMessage("§7...account (set/give/take) (amount) (player): §rModify the balance of a player.");
					}
				}else if(args[0].equalsIgnoreCase("bal") || args[0].equalsIgnoreCase("balance") && !(p instanceof Player)){
					p.sendMessage(bank.bankName() + "§r§7» You have a balance of $" + bank.getBalance(bank.stringUID(bank.getServer().getPlayer(p.getName()))) + ".");
				}else if(args[0].equalsIgnoreCase("note")){
					if(!p.hasPermission("smpbank.note") || !p.isOp()) {
						p.sendMessage(bank.bankName() + "§r§7» You do not have permission to use this command.");
					}else {
						Player pl = (Player) p;
						if(args.length == 2){
							int v = Integer.parseInt(args[1]);
							if(!SMPBankItems.giveNote(v, 1, pl)) {
								p.sendMessage(bank.bankName() + "§r§7» Command inputted improperly.");
								p.sendMessage(bank.bankName() + "§r§7» Notes available: 1, 5, 10, 20, 50, 100 and 1000.");
								p.sendMessage(bank.bankName() + "§r§7» Command usage: /bank note (value) [amount] [player].");
							}
						}else if(args.length == 3){
							int v = Integer.parseInt(args[1]);
							int a = Integer.parseInt(args[2]);
							if(!SMPBankItems.giveNote(v, a, pl)) {
								p.sendMessage(bank.bankName() + "§r§7» Command inputted improperly.");
								p.sendMessage(bank.bankName() + "§r§7» Notes available: 1, 5, 10, 20, 50, 100 and 1000.");
								p.sendMessage(bank.bankName() + "§r§7» Command usage: /bank note (value) [amount] [player].");
							}
						}else if (args.length == 4){
							int v = Integer.parseInt(args[1]);
							int a = Integer.parseInt(args[2]);
							Player tp = Bukkit.getPlayer(args[3]);
							if(tp == null) {
								p.sendMessage(bank.bankName() + "§r§7» Bank notes can only be given to online players.");
								p.sendMessage(bank.bankName() + "§r§7» Notes available: 1, 5, 10, 20, 50, 100 and 1000.");
								p.sendMessage(bank.bankName() + "§r§7» Command usage: /bank note (value) [amount] [player].");
							}else {
								if(!SMPBankItems.giveNote(v, a, tp)) {
									p.sendMessage(bank.bankName() + "§r§7» Command inputted improperly.");
									p.sendMessage(bank.bankName() + "§r§7» Notes available: 1, 5, 10, 20, 50, 100 and 1000.");
									p.sendMessage(bank.bankName() + "§r§7» Command usage: /bank note (value) [amount] [player].");
								}else {
									
								}
							}
						}else {
							p.sendMessage(bank.bankName() + "§r§7» Too many arguments.");
							p.sendMessage(bank.bankName() + "§r§7» Notes available: 1, 5, 10, 20, 50, 100 and 1000.");
							p.sendMessage(bank.bankName() + "§r§7» Command usage: /bank note (value) [amount] [player].");
						}
					}
				}else if(args[0].equalsIgnoreCase("see")){
					if(!p.hasPermission("smpbank.see") || !p.isOp()) {
						p.sendMessage(bank.bankName() + "§r§7» You do not have permission to use this command.");
					}else {
						if(args.length !=2) {
							p.sendMessage(bank.bankName() + "§r§7» Too many/not enough arguments.");
							p.sendMessage(bank.bankName() + "§r§7» Command usage: /bank see (player).");
						}else {
							Player target = Bukkit.getPlayer(args[1]);
							if(target == null) {
								p.sendMessage(bank.bankName() + "§r§7» Player not found.");
								p.sendMessage(bank.bankName() + "§r§7» Command usage: /bank see (player).");
							}else {
								p.sendMessage(bank.bankName() + "§r§7» The balance of " + target.getName() + " is $" + bank.getBalance(bank.stringUID(target)));
							}
						}
					}
				}else if(args[0].equalsIgnoreCase("account")){
					if(!p.hasPermission("smpbank.account") || !p.isOp()) {
						p.sendMessage(bank.bankName() + "§r§7» You do not have permission to use this command.");
					}else {
						if(args.length != 4) {
							p.sendMessage(bank.bankName() + "§r§7» Too many/not enough arguments.");
							p.sendMessage(bank.bankName() + "§r§7» Command usage: /bank account (set/give/take) (amount) (player).");
						}else {
							Player target = Bukkit.getPlayer(args[3]);
							if(target == null) {
								p.sendMessage(bank.bankName() + "§r§7» Player not found.");
								p.sendMessage(bank.bankName() + "§r§7» Command usage: /bank account (set/give/take) (amount) (player).");
							}else {
								int a = Integer.parseInt(args[2]);
								if(!bank.getBalanceFile().contains(args[3])) {
									bank.setBalance(args[3], bank.getConfig().getInt("starting-balance"));
								}
								if(args[1].equalsIgnoreCase("set")){
									bank.setBalance(bank.stringUID(target), a);
									p.sendMessage(bank.bankName() + "§r§7» The account balance of "+target.getName()+" has been set to $"+a+".");
									if(a < 0) {
										p.sendMessage(bank.bankName() + "§r§7» Warning: This player's account balance is less than $0!");
									}
									target.sendMessage(bank.bankName() + "§r§7» Your account balance has been set to $" + a +".");
								}else if(args[1].equalsIgnoreCase("give")) {
									bank.giveBalance(bank.stringUID(target), a);
									p.sendMessage(bank.bankName() + "§r§7» $" + a + " has been addded to the account of "+target.getName()+".");
									if(a < 0) {
										p.sendMessage(bank.bankName() + "§r§7» Warning: This player's account balance is less than $0!");
									}
									target.sendMessage(bank.bankName() + "§r§7» $" + a + " has been added to your account.");
									target.sendMessage(bank.bankName() + "§r§7» You have a new balance of $" + bank.getBalance(bank.stringUID(target)));
								}else if(args[1].equalsIgnoreCase("take")){
									bank.takeBalance(bank.stringUID(target), a);
									p.sendMessage(bank.bankName() + "§r§7» $" + a + " has been taken from the account of "+target.getName() + ".");
									if(a < 0) {
										p.sendMessage(bank.bankName() + "§r§7» Warning: This player's account balance is less than $0!");
									}
									target.sendMessage(bank.bankName() + "§r§7» $" + a + " has been taken from your account.");
									target.sendMessage(bank.bankName() + "§r§7» You have a new balance of $" + bank.getBalance(bank.stringUID(target)));
								}else {
									p.sendMessage(bank.bankName() + "§r§7» Please specify the action.");
									p.sendMessage(bank.bankName() + "§r§7» Command usage: /bank account (set/give/take) (amount) (uuid).");
								}
							}
						}
					}
				}else {
					p.sendMessage(bank.bankName() + "§r§7» Command not recognized. Please type '/bank help' for available commands.");
				}
			}
			return true;
		}
		return false;
	}
}
