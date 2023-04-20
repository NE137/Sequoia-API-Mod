package com.timon.war;


import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = "war",version = "1.0.0")
public class war {

   public static final String VERSION = "1.0.0";
   public static final String MOD_ID = "war";

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new EventHandler());
    }

    public war(){

        MinecraftForge.EVENT_BUS.register(this);

    }
}

