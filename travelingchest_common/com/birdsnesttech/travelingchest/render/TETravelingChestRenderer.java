package com.birdsnesttech.travelingchest.render;

import net.minecraft.block.Block;
import net.minecraft.client.model.ModelChest;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.birdsnesttech.travelingchest.blocks.ModBlocks;
import com.birdsnesttech.travelingchest.lib.Strings;
import com.birdsnesttech.travelingchest.tileentity.TETravelingChest;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class TETravelingChestRenderer extends TileEntitySpecialRenderer
		implements ISimpleBlockRenderingHandler {
	private static final ResourceLocation texture = new ResourceLocation(
			Strings.modid2 + ":textures/entity/chest/travelingchest.png");

	/** The normal small chest model. */
	private ModelChest chestModel = new ModelChest();

	/**
	 * Renders the TileEntity for the chest at a position.
	 */
	public void renderTETravelingChestAt(TETravelingChest par1TileEntityChest,
			double par2, double par4, double par6, float par8) {
		int i;

		if (!par1TileEntityChest.hasWorldObj()) {
			i = 0;
		} else {
			i = par1TileEntityChest.getBlockMetadata();
		}

		ModelChest modelchest;
		modelchest = this.chestModel;
		this.bindTexture(texture);

		GL11.glPushMatrix();
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glTranslatef((float) par2, (float) par4 + 1.0F,
				(float) par6 + 1.0F);
		GL11.glScalef(1.0F, -1.0F, -1.0F);
		GL11.glTranslatef(0.5F, 0.5F, 0.5F);
		short short1 = 0;

		if (i == 2) {
			short1 = 180;
		}

		if (i == 3) {
			short1 = 0;
		}

		if (i == 4) {
			short1 = 90;
		}

		if (i == 5) {
			short1 = -90;
		}

		GL11.glRotatef(short1, 0.0F, 1.0F, 0.0F);
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
		float f1 = par1TileEntityChest.prevLidAngle
				+ (par1TileEntityChest.lidAngle - par1TileEntityChest.prevLidAngle)
				* par8;

		f1 = 1.0F - f1;
		f1 = 1.0F - f1 * f1 * f1;
		modelchest.chestLid.rotateAngleX = -(f1 * (float) Math.PI / 2.0F);
		modelchest.renderAll();
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glPopMatrix();
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	}

	@Override
	public void renderTileEntityAt(TileEntity par1TileEntity, double par2,
			double par4, double par6, float par8) {
		this.renderTETravelingChestAt((TETravelingChest) par1TileEntity, par2,
				par4, par6, par8);
	}

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID,
			RenderBlocks renderer) {
		TETravelingChest te = new TETravelingChest();
		GL11.glRotatef(90.0f, 0.0F, 1.0F, 0.0F);
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
		TileEntityRenderer.instance.renderTileEntityAt(te, 0.0D, 0.0D, 0.0D,
				0.0F);
		GL11.glTranslatef(0.5F, 0.5F, 0.5F);
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z,
			Block block, int modelId, RenderBlocks renderer) {
		return false;
	}

	@Override
	public boolean shouldRender3DInInventory() {
		return true;
	}

	@Override
	public int getRenderId() {
		return ModBlocks.travelChestRenderID;
	}
}
