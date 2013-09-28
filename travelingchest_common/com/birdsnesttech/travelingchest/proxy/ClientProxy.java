package com.birdsnesttech.travelingchest.proxy;

import com.birdsnesttech.travelingchest.gui.GuiTravelingChest;
import com.birdsnesttech.travelingchest.lib.Config;
import com.birdsnesttech.travelingchest.render.TETravelingChestRenderer;
import com.birdsnesttech.travelingchest.tileentity.TETravelingChest;
import com.birdsnesttech.travelingchest.blocks.ModBlocks;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy {
	
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
			TileEntity te = world.getBlockTileEntity(x, y, z);
			if (ID < 0) {
				return null;
			}
			if (ID == Config.travelChestGUI) {
				return new GuiTravelingChest(player.inventory,(TETravelingChest)te,world,x,y,z);
			}
			return null;
	}

	@Override
	public void registerTileEntities(boolean b) {
		super.registerTileEntities(false);
		ClientRegistry.registerTileEntity(TETravelingChest.class, "travelingchest", new TETravelingChestRenderer());
	}
	
	@Override
	public void registerBlockHandler(){
		//System.out.println("Inside the block handler");
		RenderingRegistry.registerBlockHandler(ModBlocks.travelChestRenderID = RenderingRegistry.getNextAvailableRenderId(), new TETravelingChestRenderer());
	}



}
