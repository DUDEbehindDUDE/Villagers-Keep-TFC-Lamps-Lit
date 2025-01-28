# Villagers Keep TFC Lamps Lit

A Minecraft mod that makes TerraFirmaCraft lamps more viable for permanent structures by having nearby villagers
maintain them.

## Download

This mod is available to download on [Modrinth](https://modrinth.com/mod/villagers-keep-tfc-lamps-lit) and 
[Curseforge](https://curseforge.com/minecraft/mc-mods/villagers-keep-tfc-lamps-lit).

## 📝 Features

### Current Features

- Villagers automatically replenish fuel in nearby TFC lamps when they run low
- Natural feeling maintenance with randomized refill amounts
- Only already-lit lamps are refilled (empty or unlit lamps are ignored)
- Fully configurable values for fine-tuning the experience

### Planned Fearures

- Config to whitelist/blacklist fuels
- Config to add additional entities that can automatically replenish lanterns
- Refill cooldown based on how long it should take for the fuel to burn, to prevent being able to infinitely extract
  fuel (this shouldn't be a super major concern as-is though; more information further below)

## 📕 How It Works

The mod checks for nearby villagers when a lamp block is ticked (should work with any block entity extending TFC's
LampBlock class). If villagers are within range and the lamp's fuel level falls below the configured threshold, the
lamp will be refilled up to a random amount of fuel (as defined by the range in the config).

## ✏️ Configuration

All values can be adjusted in the bundled config file (found in `forge_dir/config/villagerskeeptfclampslit.toml`):

- `villagerRange`: Distance (in blocks) villagers need to be from a lamp to maintain it
    - Default: 24
    - Min: 1, Max: Integer.MAX_VALUE

- `fuelThreshold`: Fuel level (in mB) at which villagers will refill lamps
    - Default: 50
    - Min: 1, Max: 254
    - Note: Each mB lasts approximately 8000 ticks (~6-7 minutes)

- `minFuelReplenish`: Minimum amount of fuel (in mB) the lamp is refilled to
    - Default: 75
    - Min: 1, Max: 250

- `maxFuelReplenish`: Maximum amount of fuel (in mB) the lamp is refilled to
    - Default: 150
    - Min: 1, Max: 250

## ℹ️ Technical Note

When using mods that can extract fluids (e.g. Create), it is probably possible to infinitely extract the fuel as it is
being refilled by villagers. However, this shouldn't be too big of a deal because:

1. Lamps only get refilled on random block ticks
2. If a lamp is emptied before being ticked, it will extinguish
3. The config values can be adjusted to lower amounts if needed, to thwart mechanisms like really slow pump extraction.

Basically, the only way it's possible is if you either extract the fluids slow enough or you find a way to keep a
small amount of fluid in the lamp. I might later refine this by adding a proper cooldown to how often the lantern can be
refilled, and it's one of the things I plan on adding to this mod.

## 🛠️ Building

1. Clone the repository
2. Open a terminal/command prompt in the project directory
3. Run one of the following commands:
    - Windows: `gradlew build`
    - Linux/macOS: `./gradlew build`
4. Find the built jar file in `build/libs/`

**Requirements:**
- Java Development Kit (JDK) 17
- Gradle (included in wrapper)

If you encounter build errors, make sure you're using Java 17 as older or newer versions may not work correctly. You 
can also try building through an IDE (like IntelliJ) which should automatically set everything up when importing the
project.

## ⚖️ License

This project is under the MIT license. Feel free to include in modpacks. For more information see the license file.