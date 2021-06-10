package me.MwMxTrx.SMPBank;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class SMPBankSignEvents implements Listener {
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
	@EventHandler
	public void signMake(SignChangeEvent e) {
		//Allows a server administrator/people with the given permission to create an ATM sign
		if(!bank.getConfig().getBoolean("atm-sign-requires-permission")) {
			if(e.getLine(0).equalsIgnoreCase("[atm]")) {
				e.setLine(0, bank.bankName());
				e.setLine(1, "§7§lRC §r§7§oAccess ATM");
				e.setLine(2, "§7§lLC §r§7§oQuick Balance");
				e.setLine(3, "§7§lSRC §r§7§oDeposit");
			}
		}else if(e.getPlayer().hasPermission("smpbank.create-sign") || e.getPlayer().isOp()) {
			if(e.getLine(0).equalsIgnoreCase("[atm]")) {
				e.setLine(0, bank.bankName());
				e.setLine(1, "§7§lRC §r§7§oAccess ATM");
				e.setLine(2, "§7§lLC §r§7§oQuick Balance");
				e.setLine(3, "§7§lSRC §r§7§oDeposit");
			}
		}
	}
	@EventHandler
	public void signInteract(PlayerInteractEvent e) {
		//Executes when a player interacts with signs in game
		Action a = e.getAction();
		Player p = e.getPlayer();
		Block b = e.getClickedBlock();
		if(b == null) return;
		if(bank.isSign(b)) {
			if (b.getState() instanceof Sign) {
				Sign s = (Sign)b.getState();
				if(a == Action.RIGHT_CLICK_BLOCK && s.getLine(1).contains("§") && s.getLine(1).contains("Access ATM")) {
					e.setCancelled(true);
					if(p.isSneaking()) {
						//Quick deposit
						ItemMeta inh = p.getInventory().getItemInMainHand().getItemMeta();
						if(inh == null) return;
						if(inh.getDisplayName().contains("1 ") && inh.getLore().get(0).contains(bank.simpleBankName())) {
							if (p.getInventory().getItemInMainHand().getAmount() == 1) {
								p.getInventory().setItemInMainHand(new ItemStack(Material.AIR));
								bank.giveBalance(bank.stringUID(p), 1);
								p.sendMessage(bank.bankName() + "§r§7» You have deposited $1. New balance: $" + bank.getBalance(bank.stringUID(p)) + ".");
							} else {
								p.getInventory().getItemInMainHand().setAmount(p.getInventory().getItemInMainHand().getAmount()-1);
								bank.giveBalance(bank.stringUID(p), 1);
								p.sendMessage(bank.bankName() + "§r§7» You have deposited $1. New balance: $" + bank.getBalance(bank.stringUID(p)) + ".");
							}
						}
						if(inh.getDisplayName().contains("5 ") && inh.getLore().get(0).contains(bank.simpleBankName())) {
							if (p.getInventory().getItemInMainHand().getAmount() == 1) {
								p.getInventory().setItemInMainHand(new ItemStack(Material.AIR));
								bank.giveBalance(bank.stringUID(p), 5);
								p.sendMessage(bank.bankName() + "§r§7» You have deposited $5. New balance: $" + bank.getBalance(bank.stringUID(p)) + ".");
							} else {
								p.getInventory().getItemInMainHand().setAmount(p.getInventory().getItemInMainHand().getAmount()-1);
								bank.giveBalance(bank.stringUID(p), 5);
								p.sendMessage(bank.bankName() + "§r§7» You have deposited $5. New balance: $" + bank.getBalance(bank.stringUID(p)) + ".");
							}
						}
						if(inh.getDisplayName().contains("10 ") && inh.getLore().get(0).contains(bank.simpleBankName())) {
							if (p.getInventory().getItemInMainHand().getAmount() == 1) {
								p.getInventory().setItemInMainHand(new ItemStack(Material.AIR));
								bank.giveBalance(bank.stringUID(p), 10);
								p.sendMessage(bank.bankName() + "§r§7» You have deposited $10. New balance: $" + bank.getBalance(bank.stringUID(p)) + ".");
							} else {
								p.getInventory().getItemInMainHand().setAmount(p.getInventory().getItemInMainHand().getAmount()-1);
								bank.giveBalance(bank.stringUID(p), 10);
								p.sendMessage(bank.bankName() + "§r§7» You have deposited $10. New balance: $" + bank.getBalance(bank.stringUID(p)) + ".");
							}
						}
						if(inh.getDisplayName().contains("20 ") && inh.getLore().get(0).contains(bank.simpleBankName())) {
							if (p.getInventory().getItemInMainHand().getAmount() == 1) {
								p.getInventory().setItemInMainHand(new ItemStack(Material.AIR));
								bank.giveBalance(bank.stringUID(p), 20);
								p.sendMessage(bank.bankName() + "§r§7» You have deposited $20. New balance: $" + bank.getBalance(bank.stringUID(p)) + ".");
							} else {
								p.getInventory().getItemInMainHand().setAmount(p.getInventory().getItemInMainHand().getAmount()-1);
								bank.giveBalance(bank.stringUID(p), 20);
								p.sendMessage(bank.bankName() + "§r§7» You have deposited $20. New balance: $" + bank.getBalance(bank.stringUID(p)) + ".");
							}
						}
						if(inh.getDisplayName().contains("50 ") && inh.getLore().get(0).contains(bank.simpleBankName())) {
							if (p.getInventory().getItemInMainHand().getAmount() == 1) {
								p.getInventory().setItemInMainHand(new ItemStack(Material.AIR));
								bank.giveBalance(bank.stringUID(p), 50);
								p.sendMessage(bank.bankName() + "§r§7» You have deposited $50. New balance: $" + bank.getBalance(bank.stringUID(p)) + ".");
							} else {
								p.getInventory().getItemInMainHand().setAmount(p.getInventory().getItemInMainHand().getAmount()-1);
								bank.giveBalance(bank.stringUID(p), 50);
								p.sendMessage(bank.bankName() + "§r§7» You have deposited $50. New balance: $" + bank.getBalance(bank.stringUID(p)) + ".");
							}
						}
						if(inh.getDisplayName().contains("100 ") && inh.getLore().get(0).contains(bank.simpleBankName())) {
							if (p.getInventory().getItemInMainHand().getAmount() == 1) {
								p.getInventory().setItemInMainHand(new ItemStack(Material.AIR));
								bank.giveBalance(bank.stringUID(p), 100);
								p.sendMessage(bank.bankName() + "§r§7» You have deposited $100. New balance: $" + bank.getBalance(bank.stringUID(p)) + ".");
							} else {
								p.getInventory().getItemInMainHand().setAmount(p.getInventory().getItemInMainHand().getAmount()-1);
								bank.giveBalance(bank.stringUID(p), 100);
								p.sendMessage(bank.bankName() + "§r§7» You have deposited $100. New balance: $" + bank.getBalance(bank.stringUID(p)) + ".");
							}
						}
						if(inh.getDisplayName().contains("500 ") && inh.getLore().get(0).contains(bank.simpleBankName())) {
							if (p.getInventory().getItemInMainHand().getAmount() == 1) {
								p.getInventory().setItemInMainHand(new ItemStack(Material.AIR));
								bank.giveBalance(bank.stringUID(p), 500);
								p.sendMessage(bank.bankName() + "§r§7» You have deposited $500. New balance: $" + bank.getBalance(bank.stringUID(p)) + ".");
							} else {
								p.getInventory().getItemInMainHand().setAmount(p.getInventory().getItemInMainHand().getAmount()-1);
								bank.giveBalance(bank.stringUID(p), 500);
								p.sendMessage(bank.bankName() + "§r§7» You have deposited $500. New balance: $" + bank.getBalance(bank.stringUID(p)) + ".");
							}
						}
						if(inh.getDisplayName().contains("1000 ") && inh.getLore().get(0).contains(bank.simpleBankName())) {
							if (p.getInventory().getItemInMainHand().getAmount() == 1) {
								p.getInventory().setItemInMainHand(new ItemStack(Material.AIR));
								bank.giveBalance(bank.stringUID(p), 1000);
								p.sendMessage(bank.bankName() + "§r§7» You have deposited $1000. New balance: $" + bank.getBalance(bank.stringUID(p)) + ".");
							} else {
								p.getInventory().getItemInMainHand().setAmount(p.getInventory().getItemInMainHand().getAmount()-1);
								bank.giveBalance(bank.stringUID(p), 1000);
								p.sendMessage(bank.bankName() + "§r§7» You have deposited $1000. New balance: $" + bank.getBalance(bank.stringUID(p)) + ".");
							}
						}
						if(inh.getDisplayName().contains("10000 ") && inh.getLore().get(0).contains(bank.simpleBankName())) {
							if (p.getInventory().getItemInMainHand().getAmount() == 1) {
								p.getInventory().setItemInMainHand(new ItemStack(Material.AIR));
								bank.giveBalance(bank.stringUID(p), 10000);
								p.sendMessage(bank.bankName() + "§r§7» You have deposited $10000. New balance: $" + bank.getBalance(bank.stringUID(p)) + ".");
							} else {
								p.getInventory().getItemInMainHand().setAmount(p.getInventory().getItemInMainHand().getAmount()-1);
								bank.giveBalance(bank.stringUID(p), 10000);
								p.sendMessage(bank.bankName() + "§r§7» You have deposited $10000. New balance: $" + bank.getBalance(bank.stringUID(p)) + ".");
							}
						}
					}else{
						//Access ATM
						SMPBankGUI.show("main", p);
					}
				}else if(a == Action.LEFT_CLICK_BLOCK && s.getLine(1).contains("§") && s.getLine(1).contains("Access ATM")) {
					if(!p.isSneaking()) {
						//Show Balance
						e.setCancelled(true);
						p.sendMessage(bank.bankName() + "§r§7» You have a balance of $" + bank.getBalance(bank.stringUID(p) + "."));
					}else{
						//Crouch to Break Sign
					}
				}
			}
		}
	}
	
}
