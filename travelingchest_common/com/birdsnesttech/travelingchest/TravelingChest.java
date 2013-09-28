package com.birdsnesttech.travelingchest;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import TFC.TFCItems;

import com.birdsnesttech.travelingchest.blocks.ModBlocks;
import com.birdsnesttech.travelingchest.items.ModItems;
import com.birdsnesttech.travelingchest.items.ItemBlocks.ItemBlockTravelingChest;
import com.birdsnesttech.travelingchest.lib.Config;
import com.birdsnesttech.travelingchest.lib.LocalizationHandler;
import com.birdsnesttech.travelingchest.lib.Strings;
import com.birdsnesttech.travelingchest.proxy.CommonProxy;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = Strings.modid, name = "TravelingChest", version = "0.0.1", dependencies = "after:TerraFirmaCraft")
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class TravelingChest {

	@Instance("TravelingChest")
	public static TravelingChest instance;

	@SidedProxy(clientSide = "com.birdsnesttech.travelingchest.core.proxy.ClientProxy", serverSide = "com.birdsnesttech.travelingchest.core.proxy.CommonProxy")
	public static CommonProxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		Config.load(event.getSuggestedConfigurationFile());
		LocalizationHandler.loadLanguages();

		ModItems.registerItems();
		ModBlocks.registerBlocks();

	}

	@EventHandler
	public void init(FMLInitializationEvent event) {

		GameRegistry.registerBlock(
				ModBlocks.travelChest,
				ItemBlockTravelingChest.class,
				Strings.modid
						+ (ModBlocks.travelChest.getUnlocalizedName()
								.substring(5)));
		System.out.println("Traveling Chest Init");
		proxy.registerTileEntities(true);
		proxy.registerBlockHandler();
		proxy.registerEntityRenderInformation();
		proxy.registerBlockRenderInformation();
		proxy.registerItemRenderInformation();

		NetworkRegistry.instance().registerGuiHandler(this, proxy);
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		GameRegistry.addRecipe(new ItemStack(ModBlocks.travelChest, 1), "BLB",
				"LCL", "BLB", 'L', TFCItems.TerraLeather, 'B',
				TFCItems.BrassSheet, 'C', Block.chest);
		// MinecraftForge.EVENT_BUS.register(new EventExtraHandler());
		// MinecraftForge.EVENT_BUS.register(new EntityLivingHandler());
		// proxy.registerTickHandlers();
	}

}
