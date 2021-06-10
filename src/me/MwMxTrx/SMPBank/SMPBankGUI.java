package me.MwMxTrx.SMPBank;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class SMPBankGUI {
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
	static Map<String, Inventory> inventoryGUIs = new HashMap<String, Inventory>();
	
	protected static void show(String gui, Player p) {
		p.openInventory(getGUI(gui, p));
	}
	
	protected static Inventory getGUI(String gui, Player p) {
		Inventory i = inventoryGUIs.get(gui + "-" + p.getName());
		if(i == null) {
			if(gui == "main") {
				//Main GUI Screen
				i = Bukkit.createInventory(new SMPBankHolderMainGUI(), 9, bank.bankName() + "§r§8»");
				//for(int x = 0; x < 9; x++) { Old: Putting glass in empty slots
					//i.setItem(x, SMPBankItems.guiGlass());
				//}
				i.setItem(1, SMPBankItems.guiBalance(p));
				if(bank.getConfig().getBoolean("diamond-exchange")) {
					i.setItem(5, SMPBankItems.guiWithdraw());
					i.setItem(6, SMPBankItems.guiExchange());
				}else {
					i.setItem(6, SMPBankItems.guiWithdraw());
				}
				i.setItem(7, SMPBankItems.guiDeposit());
			}else if (gui == "deposit"){
				i = Bukkit.createInventory(new SMPBankHolderDeposit(), 9, bank.bankName() + "§r§8» Deposit");
				i.setItem(4, SMPBankItems.guiDepositInstruction());
				i.setItem(1, SMPBankItems.guiBalance(p));
				i.setItem(7, SMPBankItems.guiReturnMenu());
			}else if (gui == "withdraw") {
				i = Bukkit.createInventory(new SMPBankHolderWithdraw(), 18, bank.bankName() + "§r§8» Withdraw");
				i.setItem(4, SMPBankItems.guiWithdrawInstruction());
				i.setItem(1, SMPBankItems.guiBalance(p));
				i.setItem(7, SMPBankItems.guiReturnMenu());
				i.setItem(10, SMPBankItems.GUIBankNote1(p));
				i.setItem(11, SMPBankItems.GUIBankNote5(p));
				i.setItem(12, SMPBankItems.GUIBankNote10(p));
				i.setItem(13, SMPBankItems.GUIBankNote20(p));
				i.setItem(14, SMPBankItems.GUIBankNote50(p));
				i.setItem(15, SMPBankItems.GUIBankNote100(p));
				i.setItem(16, SMPBankItems.GUIBankNote1000(p));
			}else if(gui == "exchange") {
				i = Bukkit.createInventory(new SMPBankHolderExchange(), 9, bank.bankName() + "§r§8» Exchange");
				i.setItem(1, SMPBankItems.guiBalance(p));
				i.setItem(3, SMPBankItems.guiDiamondExchange(p));
				i.setItem(4, SMPBankItems.guiExchangeInstruction());
				i.setItem(5, SMPBankItems.guiDiamondBlockExchange(p));
				i.setItem(7, SMPBankItems.guiReturnMenu());
			}
		}
		return i;
	}
	protected static void removeGUI(String gui, Player p) {
		if(!inventoryGUIs.containsKey(gui + "-" + p.getName())) {
			return;
		}
		inventoryGUIs.remove(gui + "-" + p.getName());
	}
}
