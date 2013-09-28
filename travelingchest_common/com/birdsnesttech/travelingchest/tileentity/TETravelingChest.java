package com.birdsnesttech.travelingchest.tileentity;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import TFC.Core.TFC_ItemHeat;
import TFC.TileEntities.TileEntityChestTFC;

public class TETravelingChest extends TileEntityChestTFC implements IInventory {
	
	public ItemStack[] contents = new ItemStack[18];
	
	public float lidAngle;
	 
	public float prevLidAngle;
	
	public int numUsingPlayers;
	
	private int ticksSinceSync;

	@Override
	public int getSizeInventory() {
		return 18;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		return this.contents[i];
	}

	@Override
	public ItemStack decrStackSize(int i, int j) {
		if (this.contents[i] != null) {
            ItemStack is;

            if (this.contents[i].stackSize <= j) {
            	
                is = this.contents[i];
                this.contents[i] = null;
                this.onInventoryChanged();
                return is;
                
            } else {
            	
                is = this.contents[i].splitStack(j);
                if (this.contents[i].stackSize == 0) {
                    this.contents[i] = null;
                }
                this.onInventoryChanged();
                return is;
            }
        } else {
            return null;
        }
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i) {
		if (this.contents[i] != null) {
            ItemStack is = this.contents[i];
            this.contents[i] = null;
            return is;
        } else {
            return null;
        }
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack) {
		this.contents[i] = itemstack;
		
		if( itemstack != null && itemstack.stackSize > this.getInventoryStackLimit() ) {
			itemstack.stackSize = this.getInventoryStackLimit();
		}
		
		this.onInventoryChanged();
	}

	@Override
	public String getInvName() {
		// TODO Auto-generated method stub
		return "container.chest";
	}

	@Override
	public boolean isInvNameLocalized() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
    public void updateEntity()
    {
        super.updateEntity();
        
        TFC_ItemHeat.HandleContainerHeatChest(this.worldObj, contents, xCoord, yCoord, zCoord);
        
        if (++this.ticksSinceSync % 20 * 4 == 0)
        {

        }

        this.prevLidAngle = this.lidAngle;
        float var1 = 0.1F;
        double var4;

        if (this.numUsingPlayers > 0 && this.lidAngle == 0.0F)
        {
            double var2 = this.xCoord + 0.5D;
            var4 = this.zCoord + 0.5D;

            this.worldObj.playSoundEffect(var2, this.yCoord + 0.5D, var4, "random.chestopen", 0.5F, this.worldObj.rand.nextFloat() * 0.1F + 0.9F);
        }

        if (this.numUsingPlayers == 0 && this.lidAngle > 0.0F || this.numUsingPlayers > 0 && this.lidAngle < 1.0F)
        {
            float var8 = this.lidAngle;

            if (this.numUsingPlayers > 0)
            {
                this.lidAngle += var1;
            }
            else
            {
                this.lidAngle -= var1;
            }

            if (this.lidAngle > 1.0F)
            {
                this.lidAngle = 1.0F;
            }

            float var3 = 0.5F;

            if (this.lidAngle < var3 && var8 >= var3)
            {
                var4 = this.xCoord + 0.5D;
                double var6 = this.zCoord + 0.5D;

                this.worldObj.playSoundEffect(var4, this.yCoord + 0.5D, var6, "random.chestclosed", 0.5F, this.worldObj.rand.nextFloat() * 0.1F + 0.9F);
            }

            if (this.lidAngle < 0.0F)
            {
                this.lidAngle = 0.0F;
            }
        }
    }
	
    /**
     * Reads a tile entity from NBT.
     */
    public void readFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readFromNBT(par1NBTTagCompound);
        NBTTagList nbttaglist = par1NBTTagCompound.getTagList("Items");
        this.contents = new ItemStack[this.getSizeInventory()];

        for (int i = 0; i < nbttaglist.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound1 = (NBTTagCompound)nbttaglist.tagAt(i);
            int j = nbttagcompound1.getByte("Slot") & 255;

            if (j >= 0 && j < this.contents.length)
            {
                this.contents[j] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }
    }

    /**
     * Writes a tile entity to NBT.
     */
    public void writeToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeToNBT(par1NBTTagCompound);
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < this.contents.length; ++i)
        {
            if (this.contents[i] != null)
            {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte)i);
                this.contents[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }

        par1NBTTagCompound.setTag("Items", nbttaglist);

    }
    
    @Override
    public boolean receiveClientEvent(int par1, int par2)
    {
        if (par1 == 1)
        {
            this.numUsingPlayers = par2;
        }
        return true;
    }
}
