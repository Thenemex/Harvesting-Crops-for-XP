package com.harvestingxp;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;

@Mod.EventBusSubscriber(modid = HarvestingCropsXP.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config {

    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    protected static final ForgeConfigSpec.IntValue XP = BUILDER.comment("The amount of XP given to the player when he harvest crops").defineInRange("xp", 5, 0, 100);

    static final ForgeConfigSpec SPEC = BUILDER.build();

    public static int xp;

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event) {
        xp = XP.get();
    }
}
