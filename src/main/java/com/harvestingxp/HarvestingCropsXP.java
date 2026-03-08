package com.harvestingxp;

import com.harvestingxp.api.events.EventHandler;
import com.harvestingxp.config.Config;
import com.harvestingxp.model.events.PlayerHarvestingCropsCountEvent;
import com.harvestingxp.model.events.PlayerHarvestingCropsEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@SuppressWarnings("removal")
@Mod(HarvestingCropsXP.MODID)
public class HarvestingCropsXP {

    public static final String MODID = "harvestingcropsforxp";

    public EventHandler handler;

    public HarvestingCropsXP() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::commonSetup);

        // Loading config
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC, "Harvesting-XP.toml");

        MinecraftForge.EVENT_BUS.register(this);
    }

    protected void commonSetup(final FMLCommonSetupEvent event) {
        // Instanciating handlers
        if (Config.isGiveXpToPlayerEnabled() || Config.isSpawnXpOrbEnabled()) {
            if (Config.isCountEnabled()) handler = new PlayerHarvestingCropsCountEvent();
            else handler = new PlayerHarvestingCropsEvent();
            handler.register();
        }
    }
}
