package com.harvestingxp.model.events;

import com.harvestingxp.config.Config;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class PlayerHarvestingCropsCountEvent extends PlayerHarvestingCropsEvent {

    protected final int countLimit;
    protected int count = 0;

    public PlayerHarvestingCropsCountEvent() {
        super();
        countLimit = Config.getCountLimitValue();
    }

    @SubscribeEvent
    public void onPlayerHarvestingCrops(PlayerInteractEvent.LeftClickBlock event) {
        if (!event.getLevel().isClientSide && event.getLevel().isLoaded(event.getPos())) {
            BlockState blockState = event.getLevel().getBlockState(event.getPos());
            if (blockState.getBlock() instanceof CropBlock crop && crop.isMaxAge(blockState)) {
                if (++count == countLimit) {
                    count = 0;
                    if (isGiveXpEnabled) event.getEntity().giveExperiencePoints(xpGivenValue);
                    if (isOrbEnabled) {
                        BlockPos pos = event.getPos();
                        event.getLevel().addFreshEntity(new ExperienceOrb(event.getLevel(), pos.getX(), pos.getY(), pos.getZ(), xpOrbValue));
                    }
                }
            }
        }
    }
}
