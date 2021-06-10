package me.MwMxTrx.SMPBank;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class SMPBankGUIEvents implements Listener {
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
	//Test for clicks
	@EventHandler
	public void onClick(InventoryClickEvent e) {
		if(!(e.getWhoClicked() instanceof Player)) return;
		Player p = (Player) e.getWhoClicked();
		if(e.getCurrentItem() == null) return;
		ItemStack i = e.getCurrentItem();
		ItemMeta im = i.getItemMeta();
		if(e.getInventory().getHolder() instanceof SMPBankHolderMainGUI) {
			e.setCancelled(true);
			e.getInventory().setItem(1, SMPBankItems.guiBalance(p));
			if(im.getDisplayName().contains("Deposit")) {
				p.openInventory(SMPBankGUI.getGUI("deposit", p));
			}else if(im.getDisplayName().contains("Withdraw")) {
				p.openInventory(SMPBankGUI.getGUI("withdraw", p));
			}else if(im.getDisplayName().contains("Exchange")) {
				p.openInventory(SMPBankGUI.getGUI("exchange", p));
			}else if(im.getLore().get(1).contains("Click to Refresh")) {
				SMPBankGUI.show("main", p);
			}
		}
		if(e.getInventory().getHolder() instanceof SMPBankHolderDeposit) {
			e.setCancelled(true);
			if(im == null) return;
			if(im.getDisplayName().contains("Return to Menu")) {
				p.openInventory(SMPBankGUI.getGUI("main", p));
			}
			if(im.getLore() == null) return;
			if(im.getDisplayName().contains("1 ") && im.getLore().get(0).contains(bank.simpleBankName())) {
				int amt = i.getAmount();
				bank.giveBalance(bank.stringUID(p), amt*1);
				p.getInventory().setItem(e.getSlot(), new ItemStack(Material.AIR));
				p.updateInventory();
				p.sendMessage(bank.bankName() + "§r§7» You have deposited $" + amt*1 + ". New balance: $" + bank.getBalance(bank.stringUID(p)) + ".");
				SMPBankGUI.show("deposit", p);
			}else if(im.getDisplayName().contains("5 ") && im.getLore().get(0).contains(bank.simpleBankName())) {
				int amt = i.getAmount();
				bank.giveBalance(bank.stringUID(p), amt*5);
				p.getInventory().setItem(e.getSlot(), new ItemStack(Material.AIR));
				p.updateInventory();
				p.sendMessage(bank.bankName() + "§r§7» You have deposited $" + amt*5 + ". New balance: $" + bank.getBalance(bank.stringUID(p)) + ".");
				SMPBankGUI.show("deposit", p);
			}else if(im.getDisplayName().contains("10 ") && im.getLore().get(0).contains(bank.simpleBankName())) {
				int amt = i.getAmount();
				bank.giveBalance(bank.stringUID(p), amt*10);
				p.getInventory().setItem(e.getSlot(), new ItemStack(Material.AIR));
				p.updateInventory();
				p.sendMessage(bank.bankName() + "§r§7» You have deposited $" + amt*10 + ". New balance: $" + bank.getBalance(bank.stringUID(p)) + ".");
				SMPBankGUI.show("deposit", p);
			}else if(im.getDisplayName().contains("20 ") && im.getLore().get(0).contains(bank.simpleBankName())) {
				int amt = i.getAmount();
				bank.giveBalance(bank.stringUID(p), amt*20);
				p.getInventory().setItem(e.getSlot(), new ItemStack(Material.AIR));
				p.updateInventory();
				p.sendMessage(bank.bankName() + "§r§7» You have deposited $" + amt*20 + ". New balance: $" + bank.getBalance(bank.stringUID(p)) + ".");
				SMPBankGUI.show("deposit", p);
			}else if(im.getDisplayName().contains("50 ") && im.getLore().get(0).contains(bank.simpleBankName())) {
				int amt = i.getAmount();
				bank.giveBalance(bank.stringUID(p), amt*50);
				p.getInventory().setItem(e.getSlot(), new ItemStack(Material.AIR));
				p.updateInventory();
				p.sendMessage(bank.bankName() + "§r§7» You have deposited $" + amt*50 + ". New balance: $" + bank.getBalance(bank.stringUID(p)) + ".");
				SMPBankGUI.show("deposit", p);
			}else if(im.getDisplayName().contains("100 ") && im.getLore().get(0).contains(bank.simpleBankName())) {
				int amt = i.getAmount();
				bank.giveBalance(bank.stringUID(p), amt*100);
				p.getInventory().setItem(e.getSlot(), new ItemStack(Material.AIR));
				p.updateInventory();
				p.sendMessage(bank.bankName() + "§r§7» You have deposited $" + amt*100 + ". New balance: $" + bank.getBalance(bank.stringUID(p)) + ".");
				SMPBankGUI.show("deposit", p);
			}else if(im.getDisplayName().contains("1000 ") && im.getLore().get(0).contains(bank.simpleBankName())) {
				int amt = i.getAmount();
				bank.giveBalance(bank.stringUID(p), amt*1000);
				p.getInventory().setItem(e.getSlot(), new ItemStack(Material.AIR));
				p.updateInventory();
				p.sendMessage(bank.bankName() + "§r§7» You have deposited $" + amt*1000 + ". New balance: $" + bank.getBalance(bank.stringUID(p)) + ".");
				SMPBankGUI.show("deposit", p);
			}else if(im.getLore().get(1).contains("Click to Refresh")) {
				SMPBankGUI.show("deposit", p);
			}
		}
		if(e.getInventory().getHolder() instanceof SMPBankHolderWithdraw) {
			e.setCancelled(true);
			if(im == null) return;
			if(im.getDisplayName().contains("Return to Menu")) {
				p.openInventory(SMPBankGUI.getGUI("main", p));
			}
			if(im.getLore() == null) return;
			if(im.getLore().get(1).contains("Click to Refresh")) {
				SMPBankGUI.show("withdraw", p);
			}
			if(im.getLore().size() != 3) {
				//play-sound-cant-afford
				return;
			}
			if(e.getAction() == InventoryAction.MOVE_TO_OTHER_INVENTORY) {
				//Shift click stack
				if(im.getDisplayName().contains("1 ")) {
					if(bank.getBalance(bank.stringUID(p)) < 1*64) {
						//play-sound-cant-afford
					}else {
						//play-sound-success
						bank.takeBalance(bank.stringUID(p), 1*64);
						ItemStack is = SMPBankItems.bankNote1();
						is.setAmount(64);
						p.getInventory().addItem(is);
						p.sendMessage(bank.bankName() + "§r§7» You withdrew $" + 1*64 +". New balance: $" + bank.getBalance(bank.stringUID(p)) + ".");
					}
				}else if(im.getDisplayName().contains("5 ")){
					if(bank.getBalance(bank.stringUID(p)) < 5*64) {
						//play-sound-cant-afford
					}else {
						//play-sound-success
						bank.takeBalance(bank.stringUID(p), 5*64);
						ItemStack is = SMPBankItems.bankNote5();
						is.setAmount(64);
						p.getInventory().addItem(is);
						p.sendMessage(bank.bankName() + "§r§7» You withdrew $" + 5*64 +". New balance: $" + bank.getBalance(bank.stringUID(p)) + ".");
					}
				}else if(im.getDisplayName().contains("10 ")){
					if(bank.getBalance(bank.stringUID(p)) < 10*64) {
						//play-sound-cant-afford
					}else {
						//play-sound-success
						bank.takeBalance(bank.stringUID(p), 10*64);
						ItemStack is = SMPBankItems.bankNote10();
						is.setAmount(64);
						p.getInventory().addItem(is);
						p.sendMessage(bank.bankName() + "§r§7» You withdrew $" + 10*64 +". New balance: $" + bank.getBalance(bank.stringUID(p)) + ".");
					}
				}else if(im.getDisplayName().contains("20 ")){
					if(bank.getBalance(bank.stringUID(p)) < 20*64) {
						//play-sound-cant-afford
					}else {
						//play-sound-success
						bank.takeBalance(bank.stringUID(p), 20*64);
						ItemStack is = SMPBankItems.bankNote20();
						is.setAmount(64);
						p.getInventory().addItem(is);
						p.sendMessage(bank.bankName() + "§r§7» You withdrew $" + 20*64 +". New balance: $" + bank.getBalance(bank.stringUID(p)) + ".");
					}
				}else if(im.getDisplayName().contains("50 ")){
					if(bank.getBalance(bank.stringUID(p)) < 50*64) {
						//play-sound-cant-afford
					}else {
						//play-sound-success
						bank.takeBalance(bank.stringUID(p), 50*64);
						ItemStack is = SMPBankItems.bankNote50();
						is.setAmount(64);
						p.getInventory().addItem(is);
						p.sendMessage(bank.bankName() + "§r§7» You withdrew $" + 50*64 +". New balance: $" + bank.getBalance(bank.stringUID(p)) + ".");
					}
				}else if(im.getDisplayName().contains("100 ")){
					if(bank.getBalance(bank.stringUID(p)) < 100*64) {
						//play-sound-cant-afford
					}else {
						//play-sound-success
						bank.takeBalance(bank.stringUID(p), 100*64);
						ItemStack is = SMPBankItems.bankNote100();
						is.setAmount(64);
						p.getInventory().addItem(is);
						p.sendMessage(bank.bankName() + "§r§7» You withdrew $" + 100*64 +". New balance: $" + bank.getBalance(bank.stringUID(p)) + ".");
					}
				}else if(im.getDisplayName().contains("1000 ")){
					if(bank.getBalance(bank.stringUID(p)) < 1000*64) {
						//play-sound-cant-afford
					}else {
						//play-sound-success
						bank.takeBalance(bank.stringUID(p), 1000*64);
						ItemStack is = SMPBankItems.bankNote1000();
						is.setAmount(64);
						p.getInventory().addItem(is);
						p.sendMessage(bank.bankName() + "§r§7» You withdrew $" + 1000*64 +". New balance: $" + bank.getBalance(bank.stringUID(p)) + ".");
					}
				}
				SMPBankGUI.show("withdraw", p); //Refreshes
			}else {
				//Normal click
				if(im.getLore().get(1).contains(bank.simpleBankName()) || im.getLore().get(2).contains(bank.simpleBankName())) {
					if(im.getDisplayName().contains("1 ")) {
						if(bank.getBalance(bank.stringUID(p)) < 1) {
							//play-sound-cant-afford
						}else {
							//play-sound-success
							bank.takeBalance(bank.stringUID(p), 1);
							p.getInventory().addItem(SMPBankItems.bankNote1());
							p.sendMessage(bank.bankName() + "§r§7» You withdrew $1. New balance: $" + bank.getBalance(bank.stringUID(p)) + ".");
						}
					}else if(im.getDisplayName().contains("5 ")){
						if(bank.getBalance(bank.stringUID(p)) < 5) {
							//play-sound-cant-afford
						}else {
							//play-sound-success
							bank.takeBalance(bank.stringUID(p), 5);
							p.getInventory().addItem(SMPBankItems.bankNote5());
							p.sendMessage(bank.bankName() + "§r§7» You withdrew $5. New balance: $" + bank.getBalance(bank.stringUID(p)) + ".");
						}
					}else if(im.getDisplayName().contains("10 ")){
						if(bank.getBalance(bank.stringUID(p)) < 10) {
							//play-sound-cant-afford
						}else {
							//play-sound-success
							bank.takeBalance(bank.stringUID(p), 10);
							p.getInventory().addItem(SMPBankItems.bankNote10());
							p.sendMessage(bank.bankName() + "§r§7» You withdrew $10. New balance: $" + bank.getBalance(bank.stringUID(p)) + ".");
						}
					}else if(im.getDisplayName().contains("20 ")){
						if(bank.getBalance(bank.stringUID(p)) < 20) {
							//play-sound-cant-afford
						}else {
							//play-sound-success
							bank.takeBalance(bank.stringUID(p), 20);
							p.getInventory().addItem(SMPBankItems.bankNote20());
							p.sendMessage(bank.bankName() + "§r§7» You withdrew $20. New balance: $" + bank.getBalance(bank.stringUID(p)) + ".");
						}
					}else if(im.getDisplayName().contains("50 ")){
						if(bank.getBalance(bank.stringUID(p)) < 50) {
							//play-sound-cant-afford
						}else {
							//play-sound-success
							bank.takeBalance(bank.stringUID(p), 50);
							p.getInventory().addItem(SMPBankItems.bankNote50());
							p.sendMessage(bank.bankName() + "§r§7» You withdrew $50. New balance: $" + bank.getBalance(bank.stringUID(p)) + ".");
						}
					}else if(im.getDisplayName().contains("100 ")){
						if(bank.getBalance(bank.stringUID(p)) < 100) {
							//play-sound-cant-afford
						}else {
							//play-sound-success
							bank.takeBalance(bank.stringUID(p), 100);
							p.getInventory().addItem(SMPBankItems.bankNote100());
							p.sendMessage(bank.bankName() + "§r§7» You withdrew $100. New balance: $" + bank.getBalance(bank.stringUID(p)) + ".");
						}
					}else if(im.getDisplayName().contains("1000 ")){
						if(bank.getBalance(bank.stringUID(p)) < 1000) {
							//play-sound-cant-afford
						}else {
							//play-sound-success
							bank.takeBalance(bank.stringUID(p), 1000);
							p.getInventory().addItem(SMPBankItems.bankNote1000());
							p.sendMessage(bank.bankName() + "§r§7» You withdrew $1000. New balance: $" + bank.getBalance(bank.stringUID(p)) + ".");
						}
					}
					SMPBankGUI.show("withdraw", p);
				}else return; //Only bank note for GUI uses lore ln1/2, if its 0 then its a player note and I'm not letting that dupe glitch occur. Only bank notes carry name lore
			}
		}else if(e.getInventory().getHolder() instanceof SMPBankHolderExchange) {
			e.setCancelled(true);
			if(im.getDisplayName().contains("Return to Menu")) {
				p.openInventory(SMPBankGUI.getGUI("main", p));
			}
			if(im.getLore() == null) {
				if(i.getType() == Material.DIAMOND) {
					int amt = i.getAmount();
					bank.giveBalance(bank.stringUID(p), amt*bank.diamondPrice());
					p.getInventory().setItem(e.getSlot(), new ItemStack(Material.AIR));
					p.updateInventory();
					if(amt == 1) {
						p.sendMessage(bank.bankName() + "§r§7» You have exchanged " + amt + " Diamond for $" + bank.diamondPrice()*amt +". New balance: $" + bank.getBalance(bank.stringUID(p)) + ".");
					}else {
						p.sendMessage(bank.bankName() + "§r§7» You have exchanged " + amt + " Diamonds for $" + bank.diamondPrice()*amt + ". New balance: $" + bank.getBalance(bank.stringUID(p)) + ".");
					}
					SMPBankGUI.show("exchange", p);
				}else if(i.getType() == Material.DIAMOND_BLOCK) {
					int amt = i.getAmount();
					bank.giveBalance(bank.stringUID(p), amt*bank.diamondPrice()*9);
					p.getInventory().setItem(e.getSlot(), new ItemStack(Material.AIR));
					p.updateInventory();
					if(amt == 1) {
						p.sendMessage(bank.bankName() + "§r§7» You have exchanged " + amt + " Block of Diamond for $" + bank.diamondPrice()*amt*9 +". New balance: $" + bank.getBalance(bank.stringUID(p)) + ".");
					}else {
						p.sendMessage(bank.bankName() + "§r§7» You have exchanged " + amt + " Blocks of Diamond for $" + bank.diamondPrice()*amt*9 + ". New balance: $" + bank.getBalance(bank.stringUID(p)) + ".");
					}
					SMPBankGUI.show("exchange", p);
				}
				return;
			}
			if(im.getLore().size() !=2) return;
			if(im.getLore().get(1).contains("Click to Refresh")) {
				SMPBankGUI.show("exchange", p);
			}else if(i.getType() == Material.DIAMOND) {
				if(e.getAction() == InventoryAction.MOVE_TO_OTHER_INVENTORY) {
					if(bank.getBalance(bank.stringUID(p)) < bank.diamondPrice()*64) {
						//play-sound-cant-afford
					}else {
						//play-sound-success
						bank.takeBalance(bank.stringUID(p), bank.diamondPrice()*64);
						ItemStack is = new ItemStack(Material.DIAMOND);
						is.setAmount(64);
						p.getInventory().addItem(is);
						p.sendMessage(bank.bankName() + "§r§7» You have exchanged $" + bank.diamondPrice()*64 +" for 64 Diamonds. New balance: $" + bank.getBalance(bank.stringUID(p)) + ".");
						SMPBankGUI.show("exchange", p);
					}
				}else {
					if(bank.getBalance(bank.stringUID(p)) < bank.diamondPrice()) {
						//play-sound-cant-afford
					}else {
						//play-sound-success
						bank.takeBalance(bank.stringUID(p), bank.diamondPrice());
						ItemStack is = new ItemStack(Material.DIAMOND);
						is.setAmount(1);
						p.getInventory().addItem(is);
						p.sendMessage(bank.bankName() + "§r§7» You have exchanged $" + bank.diamondPrice() +" for 1 Diamond. New balance: $" + bank.getBalance(bank.stringUID(p)) + ".");
						SMPBankGUI.show("exchange", p);
					}
				}
			}else if(i.getType() == Material.DIAMOND_BLOCK) {
				if(e.getAction() == InventoryAction.MOVE_TO_OTHER_INVENTORY) {
					if(bank.getBalance(bank.stringUID(p)) < bank.diamondPrice()*64*9) {
						//play-sound-cant-afford
					}else {
						//play-sound-success
						bank.takeBalance(bank.stringUID(p), bank.diamondPrice()*64*9);
						ItemStack is = new ItemStack(Material.DIAMOND_BLOCK);
						is.setAmount(64);
						p.getInventory().addItem(is);
						p.sendMessage(bank.bankName() + "§r§7» You have exchanged $" + bank.diamondPrice()*64*9 +" for 64 Blocks of Diamond. New balance: $" + bank.getBalance(bank.stringUID(p)) + ".");
						SMPBankGUI.show("exchange", p);
					}
				}else {
					if(bank.getBalance(bank.stringUID(p)) < bank.diamondPrice()*9) {
						//play-sound-cant-afford
					}else {
						//play-sound-success
						bank.takeBalance(bank.stringUID(p), bank.diamondPrice()*9);
						ItemStack is = new ItemStack(Material.DIAMOND_BLOCK);
						is.setAmount(1);
						p.getInventory().addItem(is);
						p.sendMessage(bank.bankName() + "§r§7» You have exchanged $" + bank.diamondPrice()*9 +" for 1 Block of Diamond. New balance: $" + bank.getBalance(bank.stringUID(p)) + ".");
						SMPBankGUI.show("exchange", p);
					}
				}
			}
			
		}
	}
	//Remove inventories on player leave
	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		Player p = (Player) e.getPlayer();
		SMPBankGUI.removeGUI("main", p);
		SMPBankGUI.removeGUI("deposit", p);
		SMPBankGUI.removeGUI("withdraw", p);
		SMPBankGUI.removeGUI("exchange", p);
	}
}
