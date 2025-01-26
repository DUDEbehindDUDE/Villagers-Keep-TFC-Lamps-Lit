package com.dudebehinddude.villagerskeeptfclampslit.mixin;

import net.dries007.tfc.common.blockentities.TFCBlockEntities;
import net.dries007.tfc.common.blocks.devices.LampBlock;
import net.dries007.tfc.common.fluids.SimpleFluid;
import net.dries007.tfc.common.fluids.TFCFluids;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

import static com.dudebehinddude.villagerskeeptfclampslit.Config.*;

@Mixin(LampBlock.class)
public class TFCLampBlock {
    @Inject(method = "randomTick", at = @At("HEAD"))
    private void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random, CallbackInfo ci) {
        level.getBlockEntity(pos, TFCBlockEntities.LAMP.get()).ifPresent(lampBlockEntity -> {
            // The accessor is because the internal tank is protected
            TFCLampBlockEntity lampBlockEntityAccessor = (TFCLampBlockEntity) lampBlockEntity;
            int currentFuelLevel = lampBlockEntityAccessor.getTank().getFluidAmount();
            FluidStack currentFuel = lampBlockEntityAccessor.getTank().getFluid();
            FluidStack refillFluid;

            // Check if eligible for refueling
            if (currentFuelLevel >= fuelThreshold) return;

            // Check if villagers are nearby
            List<Villager> nearbyVillagers = level.getEntitiesOfClass(Villager.class, new AABB(pos).inflate(villagerRange), villager -> true);
            if (nearbyVillagers.isEmpty()) return;

            // It's fill time :)
            int fillAmount = (int) (Math.random() * (maxFuelReplenish - minFuelReplenish)) + minFuelReplenish - currentFuelLevel;
            if (currentFuel != FluidStack.EMPTY) {
                refillFluid = new FluidStack(currentFuel, fillAmount);
            } else {
                // I don't think this is possible, but you never know
                refillFluid = new FluidStack(
                        TFCFluids.SIMPLE_FLUIDS.get(SimpleFluid.OLIVE_OIL).getSource(),
                        fillAmount
                );
            }
            lampBlockEntityAccessor.getTank().fill(refillFluid, IFluidHandler.FluidAction.EXECUTE);
        });
    }
}
