package redart15.aether_battle.effect;

import turniplabs.halplibe.helper.EnvironmentHelper;

public class AetherBattleEffects {
	private static boolean hasInit = false;


	private AetherBattleEffects() {
	}

	public static void init() {
		if (!hasInit) {
			hasInit = true;
			assignEffects();
			registerEffects();
			if (!EnvironmentHelper.isServerEnvironment()) {
				assignEffectRenderers();
			}
		}
	}

	private static void assignEffects() {
	}

	private static void registerEffects() {
	}

	private static void assignEffectRenderers() {
	}
}
