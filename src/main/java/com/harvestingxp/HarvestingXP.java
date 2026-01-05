package com.harvestingxp;

import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

@SuppressWarnings("removal")
@Mod(HarvestingXP.MODID)
public class HarvestingXP {

    public static final String MODID = "harvestingxp", MODVERSION = "1.0";

    public HarvestingXP() {
        MinecraftForge.EVENT_BUS.register(this);
        // Loading config
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC, "Harvesting-XP.toml");
    }

    @SubscribeEvent
    public void onPlayerHarvestingCrops(PlayerInteractEvent.LeftClickBlock event) {
        if (event.getLevel().isLoaded(event.getPos())) {
            BlockState blockState = event.getLevel().getBlockState(event.getPos());
            if (blockState.getBlock() instanceof CropBlock crop)
                if (crop.isMaxAge(blockState))
                    event.getEntity().giveExperiencePoints(Config.xp);
        }
    }
}
