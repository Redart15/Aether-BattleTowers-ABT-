package redart15.aether_battle.effect;

import net.minecraft.core.Global;
import sunsetsatellite.catalyst.effects.api.effect.Effect;
import sunsetsatellite.catalyst.effects.api.effect.EffectTimeType;
import sunsetsatellite.catalyst.effects.api.effect.Effects;
import sunsetsatellite.catalyst.effects.api.effect.render.EffectRenderer;
import sunsetsatellite.catalyst.effects.api.effect.render.EffectRendererDispatcher;
import turniplabs.halplibe.helper.EnvironmentHelper;

import java.util.ArrayList;

import static redart15.aether_battle.AetherBattleMod.MOD_ID;

public class AetherBattleEffects {
	private static boolean hasInit = false;
	public  static Effect regeneration;
	public static Effect stoneSkin;


	private AetherBattleEffects() { /* no need*/ }

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
		regeneration = new RegenrationEffect(
			"effect." + MOD_ID + ".regeneration",
			MOD_ID + ":regeneration",
			new ArrayList<>(),
			EffectTimeType.ADD, 1
		)
			.setDefaultDuration(20 * Global.TICKS_PER_SECOND)
			.setDurationIncrease(4 * Global.TICKS_PER_SECOND);

		stoneSkin = new Effect(
			"effect." + MOD_ID + ".stone.skin",
			MOD_ID + ":stone_skin",
			new ArrayList<>(),
			EffectTimeType.PERMANENT, 30
		);
	}

	private static void registerEffects() {
		Effects effects = Effects.getInstance();
		effects.register(regeneration.id, regeneration);
		effects.register(stoneSkin.id, stoneSkin);
	}

	private static void assignEffectRenderers() {
		EffectRendererDispatcher dispatcher = EffectRendererDispatcher.getInstance();
		dispatcher.addDispatch(regeneration, new EffectRenderer<>(regeneration).setIcon("regeneration.png"));
		dispatcher.addDispatch(stoneSkin, new EffectRenderer<>(stoneSkin).setIcon("stoneSkin.png"));
	}
}
