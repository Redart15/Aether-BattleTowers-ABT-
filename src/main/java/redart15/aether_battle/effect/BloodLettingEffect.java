package redart15.aether_battle.effect;

import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.Mob;
import net.minecraft.core.util.helper.DamageType;
import net.minecraft.core.world.World;
import redart15.aether_battle.item.AetherBattleItems;
import sunsetsatellite.catalyst.effects.api.effect.*;
import sunsetsatellite.catalyst.effects.api.modifier.Modifier;
import teamport.aether.effect.AetherEffects;

import java.util.List;

public class BloodLettingEffect extends BleedingEffect {

	public BloodLettingEffect(String nameKey, String id, List<Modifier<?>> modifiers, EffectTimeType effectTimeType, int maxStack) {
		super(nameKey, id, modifiers, effectTimeType, maxStack);
	}

	@Override
	public <T> void stackAdded(EffectStack effectStack, EffectContainer<T> effectContainer) {
		Entity entity = (Entity) effectContainer.getParent();
		if(entity instanceof Mob){
			entity.hurt((Entity)null, 1, DamageType.GENERIC);
		}
		World world = entity.world;
		if(world == null){
			return;
		}
		world.dropItem((int)Math.round(entity.x), (int)Math.round(entity.y), (int)Math.round(entity.z), AetherBattleItems.CONGEALED_BLOOD.getDefaultStack());
	}

	@Override
	public <T> void expired(EffectStack effectStack, EffectContainer<T> effectContainer) {
		effectContainer.remove(AetherEffects.poisonEffect);
		EffectStack newStack = new EffectStack((IHasEffects<?>)effectContainer.getParent(), AetherBattleEffects.blood_letting, effectStack.getAmount() - 1);
		effectContainer.add(newStack);
		newStack.start(effectContainer);
	}

	@Override
	public boolean canApplyTo(Entity target) {
		return target instanceof Mob && super.canApplyTo(target);
	}
}
