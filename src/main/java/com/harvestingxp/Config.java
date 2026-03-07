package com.harvestingxp;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = HarvestingCropsXP.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config {

    public static final ForgeConfigSpec SPEC;
    public static final Values VALUES;

    public static class Values {
        private final ForgeConfigSpec.BooleanValue GIVE_XP_TO_PLAYER_ENABLED, SPAWN_XP_ORB_ENABLED;
        private final ForgeConfigSpec.IntValue XP_GIVEN_TO_PLAYER_AMOUNT, XP_ORB_VALUE_AMOUNT;

        private Values(ForgeConfigSpec.Builder builder) {
            builder.push("Give XP to Player");
            {
                GIVE_XP_TO_PLAYER_ENABLED = builder
                        .comment(" Will give the xp directly to player harvesting the crops")
                        .define("giveXPtoPlayer_enabled", true);

                XP_GIVEN_TO_PLAYER_AMOUNT = builder
                        .comment(" The amount of XP the player will receive")
                        .defineInRange("xpGiven_value", 5, 0, 100);

                builder.pop();
            }

            builder.push("Spawning XP Orbs");
            {
                SPAWN_XP_ORB_ENABLED = builder
                        .comment(" Will spawn an XP Orb on the crop block harvested by the player")
                        .define("spawnXPOrb_enabled", false);

                XP_ORB_VALUE_AMOUNT = builder
                        .comment(" The amount of XP contained in the orb")
                        .defineInRange("xpOrb_value", 5, 0, 100);

                builder.pop();
            }
        }
    }

    static {
        var pair = new ForgeConfigSpec.Builder().configure(Values::new);
        VALUES = pair.getLeft();
        SPEC = pair.getRight();
    }

    // Give XP to Player
    public static boolean isGiveXpToPlayerEnabled() {
        return VALUES.GIVE_XP_TO_PLAYER_ENABLED.get();
    }
    public static int getXpGivenValue() {
        return VALUES.XP_GIVEN_TO_PLAYER_AMOUNT.get();
    }
    // Spawning XP Orbs
    public static boolean isSpawnXpOrbEnabled() {
        return VALUES.SPAWN_XP_ORB_ENABLED.get();
    }
    public static int getXpOrbValue() {
        return VALUES.XP_ORB_VALUE_AMOUNT.get();
    }


}
