package redart15.aether_battle.effect;

import net.minecraft.core.Global;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.Mob;
import sunsetsatellite.catalyst.effects.api.effect.*;
import sunsetsatellite.catalyst.effects.api.modifier.Modifier;

import java.util.List;

public class RegenrationEffect extends Effect {

	public RegenrationEffect(String nameKey, String id, List<Modifier<?>> modifiers, EffectTimeType effectTimeType, int maxStack) {
		super(nameKey, id, modifiers, effectTimeType, maxStack);
	}

	@Override
	public <T> void tick(EffectStack effectStack, EffectContainer<T> effectContainer) {
		Entity entity = (Entity) effectContainer.getParent();
		if(!(entity instanceof Mob)) {
			return;
		}
		Mob mob = (Mob) entity;
		if ((mob.tickCount % (1 * Global.TICKS_PER_SECOND) == 0) && mob.getHealth() < mob.getMaxHealth()) {
			mob.heal(1);
		}
	}
}
