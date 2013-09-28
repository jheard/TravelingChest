package com.birdsnesttech.travelingchest.blocks;

import com.birdsnesttech.travelingchest.lib.Config;

import net.minecraft.block.Block;

public class ModBlocks {
	
	public static int travelChestRenderID;
	
	public static Block travelChest;

	public static void registerBlocks() {
			
		travelChest = new BlockTravelingChest(Config.travelChestID,0).setHardness(2.5F).setUnlocalizedName("travelingchest").setStepSound(Block.soundWoodFootstep);
		
	}

}
