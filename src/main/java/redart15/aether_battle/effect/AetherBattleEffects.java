package redart15.aether_battle.effect;

import net.minecraft.core.Global;
import sunsetsatellite.catalyst.effects.api.attribute.Attribute;
import sunsetsatellite.catalyst.effects.api.attribute.Attributes;
import sunsetsatellite.catalyst.effects.api.attribute.type.IntAttribute;
import sunsetsatellite.catalyst.effects.api.attribute.type.NumberAttribute;
import sunsetsatellite.catalyst.effects.api.effect.*;
import sunsetsatellite.catalyst.effects.api.effect.render.EffectRenderer;
import sunsetsatellite.catalyst.effects.api.effect.render.EffectRendererDispatcher;
import sunsetsatellite.catalyst.effects.api.modifier.ModifierType;
import sunsetsatellite.catalyst.effects.api.modifier.type.IntModifier;
import turniplabs.halplibe.helper.EnvironmentHelper;

import java.util.ArrayList;
import java.util.Collections;

import static redart15.aether_battle.AetherBattleMod.MOD_ID;

// java:S1104  java:S1444  java:S120
@SuppressWarnings({"java:S1104", "java:S1444", "java:S120"})
public class AetherBattleEffects {
	private static boolean hasInit = false;
	public  static Effect regeneration;
	public static Effect stoneSkin;
	public static Attribute<Integer> stoneSkinAttributes = new IntAttribute("attribute.stone.skin", 0).setAsDefault();

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
			EffectTimeType.RESET, 1
		)
			.setDefaultDuration(12 * Global.TICKS_PER_SECOND);

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

	public static int adjustDamageStoneSkin(int damage, EffectContainer<?> container, IHasEffects<?> mob) {
		if (container.hasAttribute(stoneSkinAttributes)) {
			int stackSize = 0;
			for (Attribute<?> attribute : container.getAttributes()) {
				if (attribute == stoneSkinAttributes) {
					@SuppressWarnings("unchecked")
					NumberAttribute<Integer> nf = (NumberAttribute<Integer>) attribute;
					stackSize = nf.calculate(mob, nf.getBaseValue());
				}
			}
			damage = Math.max(damage - stackSize, 0) + (int) Math.ceil(stackSize / 2.0f);
		}
		return damage;
	}
}
