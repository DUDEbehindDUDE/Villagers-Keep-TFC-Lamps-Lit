package com.dudebehinddude.villagerskeeptfclampslit.mixin;

import net.dries007.tfc.common.blockentities.LampBlockEntity;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(LampBlockEntity.class)
public interface TFCLampBlockEntity {
    @Accessor(value = "tank")
    FluidTank getTank();
}
