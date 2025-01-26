package com.dudebehinddude.villagerskeeptfclampslit;

import com.mojang.logging.LogUtils;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(VillagersKeepTFCLampsLit.MODID)
public class VillagersKeepTFCLampsLit {

    private static final Logger LOGGER = LogUtils.getLogger();

    // Define mod id in a common place for everything to reference
    public static final String MODID = "villagerskeeptfclampslit";

    @SuppressWarnings("removal")
    public VillagersKeepTFCLampsLit() {
        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }
}
