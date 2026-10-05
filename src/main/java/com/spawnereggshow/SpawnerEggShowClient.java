package com.spawnereggshow;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SpawnerEggShowClient implements ClientModInitializer {
	public static final String NAMESPACE = "spawnereggshow";
	public static final Logger LOGGER = LoggerFactory.getLogger(NAMESPACE);

	@Override
	public void onInitializeClient() {
		// Register lore needles -> custom item-model.
		// Each call: model name first, then any number of substrings that should
		// match (case-insensitive). First-registered wins, so put more specific
		// patterns (e.g. "stray") BEFORE more general ones (e.g. "skeleton").
		reg("spawner_blaze",       "blaze", "блейз", "всполох", "ифрит");
		reg("spawner_breeze",      "breeze", "бриз", "вихрь");
		reg("spawner_cave_spider", "cave spider", "пещерный паук");
		reg("spawner_cow",         "cow", "корова");
		reg("spawner_creeper",     "creeper", "крипер");
		reg("spawner_enderman",    "enderman", "эндермен", "эндермэн");
		reg("spawner_ghast",       "ghast", "гаст");
		reg("spawner_guardian",    "guardian", "страж");
		reg("spawner_magma_cube",  "magma cube", "magma", "магма куб", "магма-куб", "магмакуб");
		reg("spawner_panda",       "panda", "панда");
		reg("spawner_pig",         "pig", "свинья");
		reg("spawner_sheep",       "sheep", "овца");
		reg("spawner_shulker",     "shulker", "шалкер", "шулкер");
		reg("spawner_silverfish",  "silverfish", "чешуйница");
		reg("spawner_stray",       "stray", "ледяной скелет", "странник", "зимогор");
		reg("spawner_skeleton",    "skeleton", "скелет");
		reg("spawner_slime",       "slime", "слизень", "слайм");
		reg("spawner_spider",      "spider", "паук");
		reg("spawner_zombie",      "зомби", "zombie");
		reg("spawner_drowned",     "утопленник", "drowned");
		reg("spawner_default",     "пусто", "пустой");

		LOGGER.info("SpawnerEggShow loaded with {} spawner patterns", SpawnerTextures.size());
	}

	private static void reg(String modelName, String... needles) {
		Identifier id = Identifier.of(NAMESPACE, modelName);
		for (String needle : needles) {
			SpawnerTextures.register(needle, id);
		}
	}
}
