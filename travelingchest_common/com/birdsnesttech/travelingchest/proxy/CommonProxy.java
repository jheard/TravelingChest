package com.birdsnesttech.travelingchest.proxy;

import com.birdsnesttech.travelingchest.tileentity.TETravelingChest;
import com.birdsnesttech.travelingchest.container.ContainerTravelingChest;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.registry.GameRegistry;

public class CommonProxy implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity te = world.getBlockTileEntity(x, y, z);
		if (ID < 0) {
			return null;
		}
		if (ID == 0) {
			return new ContainerTravelingChest(player.inventory,(TETravelingChest)te,world,x,y,z);
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		// TODO Auto-generated method stub
		return null;
	}

	public void registerTileEntities(boolean b) {
		if (b) {
			GameRegistry.registerTileEntity(TETravelingChest.class, "travelingchest");
		}		
	}

	public void registerEntityRenderInformation() {
		// TODO Auto-generated method stub
		
	}

	public void registerBlockRenderInformation() {
		// TODO Auto-generated method stub
		
	}

	public void registerItemRenderInformation() {
		// TODO Auto-generated method stub
		
	}

	public void registerBlockHandler() {
		// TODO Auto-generated method stub
		
	}

}
