package com.birdsnesttech.travelingchest.lib;

import java.io.File;

import net.minecraftforge.common.Configuration;


public class Config {
	private static final String categorySettings = "settings";
	private static Configuration config;
	
	public static int travelChestID = 500;
	public static int travelChestGUI = 0;
	
	public static int breakChance = 50;
	
	public static void load(File file) {
		Configuration config = new Configuration(file);
		config.load();	
		//Blocks
		travelChestID = config.getBlock("travelChest", travelChestID).getInt();

		//Modifications
		breakChance = config.get("General","Chance of travel chest breaking out of 1000", breakChance).getInt(50);
		
		config.save();
	}

}
