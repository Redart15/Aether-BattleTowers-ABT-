package redart15.aether_battle;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redart15.aether_battle.block.AetherBattleBlocks;
import redart15.aether_battle.block.AetherBattleBlockTags;
import redart15.aether_battle.config.AetherBattleConfig;
import redart15.aether_battle.effect.AetherBattleEffects;
import redart15.aether_battle.entity.AetherBattleEntities;
import redart15.aether_battle.item.AetherBattleItemTags;
import redart15.aether_battle.item.AetherBattleItems;
import turniplabs.halplibe.util.GameStartEntrypoint;

public class AetherBattleMod implements ModInitializer, GameStartEntrypoint{
	public static final String MOD_ID = "aether_battle";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	@Override
	public void onInitialize() {
		LOGGER.info("ExampleMod initialized.");
	}

	@Override
	public void beforeGameStart() {
		AetherBattleConfig.init();
		AetherBattleBlocks.init();
		AetherBattleItems.init();
		AetherBattleEntities.init();
	}

	@Override
	public void afterGameStart() {
		AetherBattleBlockTags.init();
		AetherBattleItemTags.init();
		AetherBattleEffects.init();
	}

}
