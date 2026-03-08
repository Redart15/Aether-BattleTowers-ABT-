package redart15.aether_battle.effect;

import net.minecraft.core.Global;
import sunsetsatellite.catalyst.CatalystEffects;
import sunsetsatellite.catalyst.effects.api.attribute.Attribute;
import sunsetsatellite.catalyst.effects.api.attribute.Attributes;
import sunsetsatellite.catalyst.effects.api.attribute.type.IntAttribute;
import sunsetsatellite.catalyst.effects.api.effect.Effect;
import sunsetsatellite.catalyst.effects.api.effect.EffectTimeType;
import sunsetsatellite.catalyst.effects.api.effect.Effects;
import sunsetsatellite.catalyst.effects.api.effect.render.EffectRenderer;
import sunsetsatellite.catalyst.effects.api.effect.render.EffectRendererDispatcher;
import sunsetsatellite.catalyst.effects.api.modifier.ModifierType;
import sunsetsatellite.catalyst.effects.api.modifier.type.IntModifier;
import turniplabs.halplibe.helper.EnvironmentHelper;

import java.util.ArrayList;
import java.util.Collections;

import static redart15.aether_battle.AetherBattleMod.MOD_ID;

public class AetherBattleEffects {
	private static boolean hasInit = false;
	public  static Effect regeneration;
	public static Effect stoneSkin;
	public static Attribute stoneSkinAttributes = new IntAttribute("attribute.stone.skin", 0).setAsDefault();



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
			"effect." + MOD_ID + ".stone_skin",
			MOD_ID + ":stone_skin",
			Collections.singletonList(new IntModifier(stoneSkinAttributes, ModifierType.ADD, 1)),
			EffectTimeType.PERMANENT,
			10
		)
			.setPersistent();
	}

	private static void registerEffects() {
		Effects effects = Effects.getInstance();
		Attributes attributes = Attributes.getInstance();
		effects.register(regeneration.id, regeneration);
		effects.register(stoneSkin.id, stoneSkin);
		attributes.register(MOD_ID + ":stone_skin", stoneSkinAttributes);
	}

	private static void assignEffectRenderers() {
		EffectRendererDispatcher dispatcher = EffectRendererDispatcher.getInstance();
		dispatcher.addDispatch(regeneration, new EffectRenderer<>(regeneration).setIcon("regeneration.png"));
		dispatcher.addDispatch(stoneSkin, new EffectRenderer<>(stoneSkin).setIcon("stoneSkin.png"));
	}
}
