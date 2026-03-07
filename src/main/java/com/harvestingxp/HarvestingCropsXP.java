package com.harvestingxp;

import com.harvestingxp.events.EventHandler;
import com.harvestingxp.events.PlayerHarvestingCropsEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import java.util.ArrayList;

@SuppressWarnings("removal")
@Mod(HarvestingCropsXP.MODID)
public class HarvestingCropsXP {

    public static final String MODID = "harvestingcropsforxp";

    public static final ArrayList<EventHandler> handlers = new ArrayList<>(5);

    public HarvestingCropsXP() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::commonSetup);

        // Loading config
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC, "Harvesting-XP.toml");

        MinecraftForge.EVENT_BUS.register(this);
    }

    protected void commonSetup(final FMLCommonSetupEvent event) {
        // Instanciating handlers
        EventHandler handler = new PlayerHarvestingCropsEvent();
        handlers.add(handler);
    }
}
