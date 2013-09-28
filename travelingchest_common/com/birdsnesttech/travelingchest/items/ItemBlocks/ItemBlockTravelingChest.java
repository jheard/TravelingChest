package com.birdsnesttech.travelingchest.items.ItemBlocks;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import TFC.API.ISize;
import TFC.API.Enums.EnumSize;
import TFC.API.Enums.EnumWeight;
import TFC.Items.ItemTerra;

public class ItemBlockTravelingChest extends ItemBlock implements ISize {
	

	public ItemBlockTravelingChest(int par1) {
		super(par1);
		this.maxStackSize = 1;
	}

	@Override
	public EnumSize getSize() {
		return EnumSize.HUGE;
	}

	@Override
	public EnumWeight getWeight() {
		return EnumWeight.HEAVY;
	}

	@Override
	public boolean canStack() {
		return false;
	}

	@Override
	public void addInformation(ItemStack par1ItemStack,
			EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
		// TODO Auto-generated method stub
		super.addInformation(par1ItemStack, par2EntityPlayer, par3List, par4);
		ItemTerra.addSizeInformation(this, par3List);
	}

}
