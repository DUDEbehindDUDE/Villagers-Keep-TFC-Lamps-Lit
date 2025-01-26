package com.dudebehinddude.villagerskeeptfclampslit;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;

// An example config class. This is not required, but it's a good idea to have one to keep your config organized.
// Demonstrates how to use Forge's config APIs
@Mod.EventBusSubscriber(modid = VillagersKeepTFCLampsLit.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config {
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    private static final ForgeConfigSpec.IntValue VILLAGER_RANGE = BUILDER.comment("How close villagers need to be for lamps to stay filled").defineInRange("villagerRange", 24, 1, Integer.MAX_VALUE);
    private static final ForgeConfigSpec.IntValue FUEL_THRESHOLD = BUILDER.comment("The point (in mb) at which villagers will refill lamps. For reference each mb is ~8000 ticks (6-ish mins I think?)").defineInRange("fuelThreshold", 50, 1, 254);
    private static final ForgeConfigSpec.IntValue MIN_FUEL_REPLENISH = BUILDER.comment("When refilling a random value to refill it to will be chosen. This is the lower bound.").defineInRange("minFuelReplenish", 75, 1, 250);
    private static final ForgeConfigSpec.IntValue MAX_FUEL_REPLENISH = BUILDER.comment("When refilling a random value to refill it to will be chosen. This is the upper bound.").defineInRange("maxFuelReplenish", 150, 1, 250);


    static final ForgeConfigSpec SPEC = BUILDER.build();

    public static int villagerRange;
    public static int fuelThreshold;
    public static int minFuelReplenish;
    public static int maxFuelReplenish;

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event) {
        villagerRange = VILLAGER_RANGE.get();
        fuelThreshold = FUEL_THRESHOLD.get();
        maxFuelReplenish = MIN_FUEL_REPLENISH.get();
        minFuelReplenish = MAX_FUEL_REPLENISH.get();
    }
}
