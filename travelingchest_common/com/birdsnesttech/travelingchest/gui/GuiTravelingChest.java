package com.birdsnesttech.travelingchest.gui;

import org.lwjgl.opengl.GL11;

import com.birdsnesttech.travelingchest.tileentity.TETravelingChest;
import com.birdsnesttech.travelingchest.container.ContainerTravelingChest;

import TFC.Core.TFC_Core;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class GuiTravelingChest extends GuiContainer {

	private IInventory upperChestInventory;
	private IInventory lowerChestInventory;

	/**
	 * window height is calculated with this values, the more rows, the heigher
	 */
	private int inventoryRows = 0;

	public GuiTravelingChest(IInventory par1IInventory, IInventory chestInv, World world, int x, int y, int z)
	{
		super(new ContainerTravelingChest(par1IInventory, chestInv, world, x, y, z));

		this.upperChestInventory = par1IInventory;
		this.lowerChestInventory = chestInv;

		this.allowUserInput = false;
		short var3 = 222;
		int var4 = var3 - 108;
		this.inventoryRows = lowerChestInventory.getSizeInventory() / 9;
		this.ySize = var4 + this.inventoryRows * 18;
	}

	/**
	 * Draw the foreground layer for the GuiContainer (everything in front of the items)
	 */
	protected void drawGuiContainerForegroundLayer()
	{
		this.fontRenderer.drawString(StatCollector.translateToLocal(this.lowerChestInventory.getInvName()), 8, 6, 4210752);
		this.fontRenderer.drawString(StatCollector.translateToLocal(this.upperChestInventory.getInvName()), 8, this.ySize - 96 + 2, 4210752);
	}

	/**
	 * Draw the background layer for the GuiContainer (everything behind the items)
	 */
	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
	{
		TFC_Core.bindTexture(new ResourceLocation("textures/gui/container/generic_54.png"));
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		int var5 = (this.width - this.xSize) / 2;
		int var6 = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(var5, var6, 0, 0, this.xSize, this.inventoryRows * 18 + 17);
		this.drawTexturedModalRect(var5, var6 + this.inventoryRows * 18 + 17, 0, 126, this.xSize, 96);
	}

}
