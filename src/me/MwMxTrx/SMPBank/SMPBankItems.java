package me.MwMxTrx.SMPBank;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class SMPBankItems {
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
	
	//GUI Buttons:
	protected static ItemStack guiExchange() {
		//Diamond Exchange Button
		ItemStack is = new ItemStack(Material.DIAMOND_BLOCK);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName("§3Exchange");
		ArrayList<String> lore = new ArrayList<>();
		lore.add("§fWithdraw or deposit diamonds");
		lore.add("§fin exchange for money.");
		im.setLore(lore);
		is.setItemMeta(im);
		return is;
	}
	//protected static ItemStack guiGlass() {
		//Placeholder
		//ItemStack is = new ItemStack(Material.LIGHT_GRAY_STAINED_GLASS_PANE);
		//ItemMeta im = is.getItemMeta();
		//im.setDisplayName("§l");
		//is.setItemMeta(im);
		//return is;
	//}
	protected static ItemStack guiDeposit() {
		//Deposit Menu
		ItemStack is = new ItemStack(Material.LIME_CONCRETE);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName("§aDeposit");
		ArrayList<String> lore = new ArrayList<>();
		lore.add("§fDeposit bank notes.");
		im.setLore(lore);
		is.setItemMeta(im);
		return is;
	}
	protected static ItemStack guiWithdraw() {
		//Withdraw Menu
		ItemStack is = new ItemStack(Material.RED_CONCRETE);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName("§cWithdraw");
		ArrayList<String> lore = new ArrayList<>();
		lore.add("§fWithdraw bank notes.");
		im.setLore(lore);
		is.setItemMeta(im);
		return is;
	}
	protected static ItemStack guiReturnMenu() {
		//Withdraw Menu
		ItemStack is = new ItemStack(Material.RED_CONCRETE);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName("§cReturn to Menu");
		is.setItemMeta(im);
		return is;
	}
	protected static ItemStack guiDepositInstruction() {
		//Tells players how to deposit in GUI
		ItemStack is = new ItemStack(Material.FILLED_MAP);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName("§7Deposit Bank Notes");
		ArrayList<String> lore = new ArrayList<>();
		lore.add("§fClick on bank notes in your");
		lore.add("§finventory to deposit the stack.");
		im.setLore(lore);
		is.setItemMeta(im);
		return is;
	}
	protected static ItemStack guiWithdrawInstruction() {
		//Tells players how to withdraw in GUI
		ItemStack is = new ItemStack(Material.FILLED_MAP);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName("§7Withdraw Bank Notes");
		ArrayList<String> lore = new ArrayList<>();
		lore.add("§fClick notes to withdraw one.");
		lore.add("§fShift-Click to withdraw stack.");
		im.setLore(lore);
		is.setItemMeta(im);
		return is;
	}
	protected static ItemStack guiExchangeInstruction() {
		//Tells players how to exchange in GUI
		ItemStack is = new ItemStack(Material.FILLED_MAP);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName("§7Exchange Account Balance with Diamonds");
		ArrayList<String> lore = new ArrayList<>();
		lore.add("§fClick on diamonds in ATM to withdraw.");
		lore.add("§fClick on diamonds in inventory to deposit.");
		lore.add("§fShift-Click for full stack.");
		im.setLore(lore);
		is.setItemMeta(im);
		return is;
	}
	protected static ItemStack guiBalance(Player p) {
		//Shows players balance
		ItemStack is = new ItemStack(Material.WRITABLE_BOOK);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName("§7" + p.getName());
		ArrayList<String> lore = new ArrayList<>();
		lore.add("§fYour balance is: $" + bank.getBalance(bank.stringUID(p)));
		lore.add("§fClick to Refresh.");
		im.setLore(lore);
		is.setItemMeta(im);
		return is;
	}
	//GUI Diamond Exchange
	protected static ItemStack guiDiamondExchange(Player p) {
		//Tells players how to withdraw in GUI
		ItemStack is = new ItemStack(Material.DIAMOND);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName("§3Diamond");
		ArrayList<String> lore = new ArrayList<>();
		if(bank.getBalance(bank.stringUID(p)) < bank.diamondPrice()) {
			lore.add("§cPrice: " + bank.diamondPrice());
			is.setType(Material.BARRIER);
		}else {
			lore.add("§fPrice: " + bank.diamondPrice());
		}
		if(bank.getBalance(bank.stringUID(p)) < bank.diamondPrice()*64) {
			lore.add("§cStack Price: " + bank.diamondPrice()*64);
		}else {
			lore.add("§fStack Price: " + bank.diamondPrice()*64);
		}
		im.setLore(lore);
		is.setItemMeta(im);
		return is;
	}
	protected static ItemStack guiDiamondBlockExchange(Player p) {
		//Tells players how to withdraw in GUI
		ItemStack is = new ItemStack(Material.DIAMOND_BLOCK);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName("§3Block of Diamond");
		ArrayList<String> lore = new ArrayList<>();
		if(bank.getBalance(bank.stringUID(p)) < bank.diamondPrice()*9) {
			lore.add("§cPrice: " + bank.diamondPrice()*9);
			is.setType(Material.BARRIER);
		}else {
			lore.add("§fPrice: " + bank.diamondPrice()*9);
		}
		if(bank.getBalance(bank.stringUID(p)) < bank.diamondPrice()*64*9) {
			lore.add("§cStack Price: " + bank.diamondPrice()*64*9);
		}else {
			lore.add("§fStack Price: " + bank.diamondPrice()*64*9);
		}
		im.setLore(lore);
		is.setItemMeta(im);
		return is;
	}
	
	//Give Bank Note
	protected static boolean giveNote(int value, int amount, Player p) {
		if(p == null) return false;
		switch(value) {
		case 1:
			ItemStack note1 = SMPBankItems.bankNote1();
			note1.setAmount(amount);
			p.getInventory().addItem(note1);
			return true;
		case 5:
			ItemStack note5 = SMPBankItems.bankNote5();
			note5.setAmount(amount);
			p.getInventory().addItem(note5);
			return true;
		case 10:
			ItemStack note10 = SMPBankItems.bankNote10();
			note10.setAmount(amount);
			p.getInventory().addItem(note10);
			return true;
		case 20:
			ItemStack note20 = SMPBankItems.bankNote20();
			note20.setAmount(amount);
			p.getInventory().addItem(note20);
			return true;
		case 50:
			ItemStack note50 = SMPBankItems.bankNote50();
			note50.setAmount(amount);
			p.getInventory().addItem(note50);
			return true;
		case 100:
			ItemStack note100 = SMPBankItems.bankNote100();
			note100.setAmount(amount);
			p.getInventory().addItem(note100);
			return true;
		case 1000:
			ItemStack note1000 = SMPBankItems.bankNote1000();
			note1000.setAmount(amount);
			p.getInventory().addItem(note1000);
			return true;
		default: return false;
		}
	}
	
	//GUI Withdraw Bank Notes
		protected static ItemStack GUIBankNote1(Player p) {
			ItemStack is = new ItemStack(Material.PAPER);
			ItemMeta im = is.getItemMeta();
			im.setDisplayName("§a$1 Note");
			ArrayList<String> lore = new ArrayList<>();
			
			if(bank.getBalance(bank.stringUID(p)) < 1) {
				is.setType(Material.BARRIER);
				lore.add("§cCannot afford");
			}else {
				lore.add("§fAvailable Notes: " + (int) Math.floor(bank.getBalance(bank.stringUID(p))/1));
				if(bank.getBalance(bank.stringUID(p)) < 1*64) {
					lore.add("§cCannot afford stack");
				}else {
					lore.add("§fAvailable Stacks: " + (int) Math.floor(bank.getBalance(bank.stringUID(p))/(1*64)));
				}
			}
			lore.add("§7" + bank.simpleBankName());
			im.setLore(lore);
			is.setItemMeta(im);
			return is;
		}
		protected static ItemStack GUIBankNote5(Player p) {
			ItemStack is = new ItemStack(Material.PAPER);
			ItemMeta im = is.getItemMeta();
			im.setDisplayName("§a$5 Note");
			ArrayList<String> lore = new ArrayList<>();
			if(bank.getBalance(bank.stringUID(p)) < 5) {
				is.setType(Material.BARRIER);
				lore.add("§cCannot afford");
			}else {
				lore.add("§fAvailable Notes: " + (int) Math.floor(bank.getBalance(bank.stringUID(p))/5));
				if(bank.getBalance(bank.stringUID(p)) < 5*64) {
					lore.add("§cCannot afford stack");
				}else {
					lore.add("§fAvailable Stacks: " + (int) Math.floor(bank.getBalance(bank.stringUID(p))/(5*64)));
				}
			}
			lore.add("§7" + bank.simpleBankName());
			im.setLore(lore);
			is.setItemMeta(im);
			return is;
		}
		protected static ItemStack GUIBankNote10(Player p) {
			ItemStack is = new ItemStack(Material.PAPER);
			ItemMeta im = is.getItemMeta();
			im.setDisplayName("§a$10 Note");
			ArrayList<String> lore = new ArrayList<>();
			
			if(bank.getBalance(bank.stringUID(p)) < 10) {
				is.setType(Material.BARRIER);
				lore.add("§cCannot afford");
			}else {
				lore.add("§fAvailable Notes: " + (int) Math.floor(bank.getBalance(bank.stringUID(p))/10));
				if(bank.getBalance(bank.stringUID(p)) < 10*64) {
					lore.add("§cCannot afford stack");
				}else {
					lore.add("§fAvailable Stacks: " + (int) Math.floor(bank.getBalance(bank.stringUID(p))/(10*64)));
				}
			}
			lore.add("§7" + bank.simpleBankName());
			im.setLore(lore);
			is.setItemMeta(im);
			return is;
		}
		protected static ItemStack GUIBankNote20(Player p) {
			ItemStack is = new ItemStack(Material.PAPER);
			ItemMeta im = is.getItemMeta();
			im.setDisplayName("§a$20 Note");
			ArrayList<String> lore = new ArrayList<>();
			
			if(bank.getBalance(bank.stringUID(p)) < 20) {
				is.setType(Material.BARRIER);
				lore.add("§cCannot afford");
			}else {
				lore.add("§fAvailable Notes: " + (int) Math.floor(bank.getBalance(bank.stringUID(p))/20));
				if(bank.getBalance(bank.stringUID(p)) < 20*64) {
					lore.add("§cCannot afford stack");
				}else {
					lore.add("§fAvailable Stacks: " + (int) Math.floor(bank.getBalance(bank.stringUID(p))/(20*64)));
				}
			}
			lore.add("§7" + bank.simpleBankName());
			im.setLore(lore);
			is.setItemMeta(im);
			return is;
		}
		protected static ItemStack GUIBankNote50(Player p) {
			ItemStack is = new ItemStack(Material.PAPER);
			ItemMeta im = is.getItemMeta();
			im.setDisplayName("§a$50 Note");
			ArrayList<String> lore = new ArrayList<>();
			
			if(bank.getBalance(bank.stringUID(p)) < 50) {
				is.setType(Material.BARRIER);
				lore.add("§cCannot afford");
			}else {
				lore.add("§fAvailable Notes: " + (int) Math.floor(bank.getBalance(bank.stringUID(p))/50));
				if(bank.getBalance(bank.stringUID(p)) < 50*64) {
					lore.add("§cCannot afford stack");
				}else {
					lore.add("§fAvailable Stacks: " + (int) Math.floor(bank.getBalance(bank.stringUID(p))/(50*64)));
				}
			}
			lore.add("§7" + bank.simpleBankName());
			im.setLore(lore);
			is.setItemMeta(im);
			return is;
		}
		protected static ItemStack GUIBankNote100(Player p) {
			ItemStack is = new ItemStack(Material.PAPER);
			ItemMeta im = is.getItemMeta();
			im.setDisplayName("§a$100 Note");
			ArrayList<String> lore = new ArrayList<>();
			
			if(bank.getBalance(bank.stringUID(p)) < 100) {
				is.setType(Material.BARRIER);
				lore.add("§cCannot afford");
			}else {
				lore.add("§fAvailable Notes: " + (int) Math.floor(bank.getBalance(bank.stringUID(p))/100));
				if(bank.getBalance(bank.stringUID(p)) < 100*64) {
					lore.add("§cCannot afford stack");
				}else {
					lore.add("§fAvailable Stacks: " + (int) Math.floor(bank.getBalance(bank.stringUID(p))/(100*64)));
				}
			}
			lore.add("§7" + bank.simpleBankName());
			im.setLore(lore);
			is.setItemMeta(im);
			return is;
		}
		protected static ItemStack GUIBankNote1000(Player p) {
			ItemStack is = new ItemStack(Material.PAPER);
			ItemMeta im = is.getItemMeta();
			im.setDisplayName("§a$1000 Note");
			ArrayList<String> lore = new ArrayList<>();
			
			if(bank.getBalance(bank.stringUID(p)) < 1000) {
				is.setType(Material.BARRIER);
				lore.add("§cCannot afford");
			}else {
				lore.add("§fAvailable Notes: " + (int) Math.floor(bank.getBalance(bank.stringUID(p))/1000));
				if(bank.getBalance(bank.stringUID(p)) < 1000*64) {
					lore.add("§cCannot afford stack");
				}else {
					lore.add("§fAvailable Stacks: " + (int) Math.floor(bank.getBalance(bank.stringUID(p))/(1000*64)));
				}
			}
			lore.add("§7" + bank.simpleBankName());
			im.setLore(lore);
			is.setItemMeta(im);
			return is;
		}
	//Bank Notes
	protected static ItemStack bankNote1() {
		ItemStack is = new ItemStack(Material.PAPER);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName("§a$1 Note");
		ArrayList<String> lore = new ArrayList<>();
		lore.add("§7" + bank.simpleBankName());
		im.setLore(lore);
		is.setItemMeta(im);
		return is;
	}
	protected static ItemStack bankNote5() {
		ItemStack is = new ItemStack(Material.PAPER);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName("§a$5 Note");
		ArrayList<String> lore = new ArrayList<>();
		lore.add("§7" + bank.simpleBankName());
		im.setLore(lore);
		is.setItemMeta(im);
		return is;
	}
	protected static ItemStack bankNote10() {
		ItemStack is = new ItemStack(Material.PAPER);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName("§a$10 Note");
		ArrayList<String> lore = new ArrayList<>();
		lore.add("§7" + bank.simpleBankName());
		im.setLore(lore);
		is.setItemMeta(im);
		return is;
	}
	protected static ItemStack bankNote20() {
		ItemStack is = new ItemStack(Material.PAPER);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName("§a$20 Note");
		ArrayList<String> lore = new ArrayList<>();
		lore.add("§7" + bank.simpleBankName());
		im.setLore(lore);
		is.setItemMeta(im);
		return is;
	}
	protected static ItemStack bankNote50() {
		ItemStack is = new ItemStack(Material.PAPER);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName("§a$50 Note");
		ArrayList<String> lore = new ArrayList<>();
		lore.add("§7" + bank.simpleBankName());
		im.setLore(lore);
		is.setItemMeta(im);
		return is;
	}
	protected static ItemStack bankNote100() {
		ItemStack is = new ItemStack(Material.PAPER);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName("§a$100 Note");
		ArrayList<String> lore = new ArrayList<>();
		lore.add("§7" + bank.simpleBankName());
		im.setLore(lore);
		is.setItemMeta(im);
		return is;
	}
	protected static ItemStack bankNote1000() {
		ItemStack is = new ItemStack(Material.PAPER);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName("§a$1000 Note");
		ArrayList<String> lore = new ArrayList<>();
		lore.add("§7" + bank.simpleBankName());
		im.setLore(lore);
		is.setItemMeta(im);
		return is;
	}
}
