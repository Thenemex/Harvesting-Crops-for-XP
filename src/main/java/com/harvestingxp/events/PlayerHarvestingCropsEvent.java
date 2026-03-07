package com.harvestingxp.events;

import com.harvestingxp.Config;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class PlayerHarvestingCropsEvent implements EventHandler {

    protected final boolean isOrbEnabled, isGiveXpEnabled;
    protected final int xpGivenValue, xpOrbValue;

    public PlayerHarvestingCropsEvent() {
        this.isGiveXpEnabled = Config.isGiveXpToPlayerEnabled();
        this.isOrbEnabled = Config.isSpawnXpOrbEnabled();
        this.xpGivenValue = Config.getXpGivenValue();
        this.xpOrbValue = Config.getXpOrbValue();
        if (isGiveXpEnabled || isOrbEnabled)
            MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onPlayerHarvestingCrops(PlayerInteractEvent.LeftClickBlock event) {
        if (!event.getLevel().isClientSide && event.getLevel().isLoaded(event.getPos())) {
            BlockState blockState = event.getLevel().getBlockState(event.getPos());
            if (blockState.getBlock() instanceof CropBlock crop && crop.isMaxAge(blockState)) {
                BlockPos pos = event.getPos();
                if (isOrbEnabled)
                    event.getLevel().addFreshEntity(new ExperienceOrb(event.getLevel(), pos.getX(), pos.getY(), pos.getZ(), xpOrbValue));
                if (isGiveXpEnabled)
                    event.getEntity().giveExperiencePoints(xpGivenValue);
            }
        }
    }
}
