package com.djlead.leadmod;

import com.djlead.leadmod.init.MyBlocks;
import com.djlead.leadmod.init.MyItems;
import com.djlead.leadmod.init.MyRecipes;
import com.djlead.leadmod.init.MyTab;
import com.djlead.leadmod.worldgen.WorldGeneratorOre;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import com.djlead.leadmod.proxy.CommonProxy;
import net.minecraftforge.fml.common.registry.GameRegistry;


/** Main Class
 *  Created by Lead on 11-9-2015.
 */

@Mod(modid = Reference.MODID, name = Reference.MODNAME , version = Reference.MODVERSION, dependencies = "required-after:FML")

public class Main {

    @Instance(value=Reference.MODID)
    public static Main instance;

    @SidedProxy(modId=Reference.MODID, clientSide=Reference.CLIENT_PROXY, serverSide=Reference.SERVER_PROXY)
    public static CommonProxy proxy;

    // create Creative Inventory Tab
    public static final MyTab tabCrea = new MyTab("tabCrea");

    // initialization
    @EventHandler    //PreInits Handler used for creating blocks, init, plugin APIs and to register item/blocks with GameRegistry
    public void preInit(FMLPreInitializationEvent event) {

        // Initialize and register Items & blocks
        MyItems.InitItem();
        MyItems.register();
        MyBlocks.InitBlocks();
        MyBlocks.register();

    }

    @EventHandler    // Init build ui data structures, crafting recipes and register new handler and API useage
    public void init(FMLInitializationEvent event) {

        proxy.registerRenders();

        GameRegistry.registerWorldGenerator(new WorldGeneratorOre(), 0);

        // Add custom recipes
        MyRecipes.makeRecipes();


    }

    @EventHandler    //Postinit deals with communication with other mods
    public void postInit(FMLPostInitializationEvent event) {

    }
}
