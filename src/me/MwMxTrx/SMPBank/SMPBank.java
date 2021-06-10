package me.MwMxTrx.SMPBank;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class SMPBank extends JavaPlugin implements Listener{
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
	private File balanceFile;
    private FileConfiguration balFileConfig;

    private static SMPBank instance;
    public static SMPBank getInstance() {
    	return instance;
    }

	@Override
	public void onEnable() {
		instance = this;
		this.saveDefaultConfig();
		this.createbalanceFile();
		this.getServer().getPluginManager().registerEvents(this, this);
		this.getServer().getPluginManager().registerEvents(new SMPBankGUIEvents(), this);
		this.getServer().getPluginManager().registerEvents(new SMPBankSignEvents(), this);
		this.getCommand("bank").setExecutor(new SMPBankCommands());
		this.getCommand("smpbank").setExecutor(new SMPBankCommands());
		for(Player p : getServer().getOnlinePlayers()) {
			accountCheck(p);
		}
	}
	public void onDisable() {
		this.saveBalances();
		instance = null;
	}
	
//Creating file to save player balances (Written from Spigot and Bukkit wiki)
    public FileConfiguration getBalanceFile() {
        return this.balFileConfig;
    }
    private void createbalanceFile() {
        balanceFile = new File(getDataFolder(), "balance.yml");
        if (!balanceFile.exists()) {
            balanceFile.getParentFile().mkdirs();
            saveResource("balance.yml", false);
         }
        balFileConfig= new YamlConfiguration();
        try {
            balFileConfig.load(balanceFile);
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    public void saveBalances() {
        if (balFileConfig == null || balanceFile == null) {
            return;
        }
        try {
            getBalanceFile().save(balanceFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
//Making life simpler for myself
	protected boolean isSign(Block b) {
		if(b.getType() == Material.SPRUCE_SIGN
				|| b.getType() == Material.SPRUCE_WALL_SIGN
				|| b.getType() == Material.ACACIA_SIGN
				|| b.getType() == Material.ACACIA_WALL_SIGN
				|| b.getType() == Material.OAK_SIGN
				|| b.getType() == Material.OAK_WALL_SIGN
				|| b.getType() == Material.DARK_OAK_SIGN
				|| b.getType() == Material.DARK_OAK_WALL_SIGN
				|| b.getType() == Material.BIRCH_SIGN
				|| b.getType() == Material.BIRCH_WALL_SIGN) {
			return true;
		}
		return false;
	}
	protected int diamondPrice() {
		return this.getConfig().getInt("diamond-price");
	}
	protected String stringUID(Player player) {
		return player.getUniqueId().toString();
	}
	protected String bankName() {
		return this.getConfig().getString("bank-name-formatting").replaceAll("&", "§");
	}
	protected String simpleBankName() {
		return this.getConfig().getString("bank-name");
	}
	protected int getBalance(String uuid) {
		//Returns the balance of the player (references balance file)
		return this.getBalanceFile().getInt(uuid + ".balance");
	}
	protected void takeBalance(String uuid, int amount) {
		//Takes money away from player
		int newBalance = getBalance(uuid)-amount;
		setBalance(uuid, newBalance);
	}
	protected void giveBalance(String uuid, int amount) {
		//Gives money to player
		int newBalance = getBalance(uuid)+amount;
		setBalance(uuid, newBalance);
	}
	protected void setBalance(String uuid, int newBalance) {
		//Sets the players balance and saves to balance file
		this.getBalanceFile().set(uuid + ".balance", newBalance);
		this.saveBalances();
	}	
	protected void showGUI(Player p) {
		
	}
	
//Creating accounts for people who do not have accounts yet/updating names for those that have changed username
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		accountCheck(p);
	}
	public void accountCheck(Player p) {
		if(this.getBalanceFile().contains(stringUID(p))) {
			this.getBalanceFile().set(stringUID(p) + ".name", p.getName());
		}else {
			this.getBalanceFile().set(stringUID(p) + ".name", p.getName());
			setBalance(stringUID(p), this.getConfig().getInt("starting-balance"));
		}
	}
}
