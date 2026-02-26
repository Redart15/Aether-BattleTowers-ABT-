package redart15.aether_battle.effect;

import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.MobPathfinder;
import net.minecraft.core.entity.monster.MobMonster;
import sunsetsatellite.catalyst.effects.api.effect.Effect;
import sunsetsatellite.catalyst.effects.api.effect.EffectContainer;

public interface HarmFullEffect {

	default void doHarm(EffectContainer<?> effectContainer, Effect effect, Entity attacker){
		Entity entity = (Entity)effectContainer.getParent();
		if(entity instanceof MobMonster){
			((MobPathfinder)entity).setTarget(attacker);
		}
	}
}
