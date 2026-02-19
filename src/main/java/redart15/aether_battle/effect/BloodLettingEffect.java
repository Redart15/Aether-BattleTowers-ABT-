package redart15.aether_battle.effect;

import jamdoggie.betterbattletowers.util.ParticleHelper;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.Mob;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.util.helper.DamageType;
import net.minecraft.core.util.helper.MathHelper;
import net.minecraft.core.world.World;
import redart15.aether_battle.item.AetherBattleItems;
import sunsetsatellite.catalyst.effects.api.effect.*;
import sunsetsatellite.catalyst.effects.api.modifier.Modifier;
import teamport.aether.effect.AetherEffects;

import java.util.List;
import java.util.Random;

public class BloodLettingEffect extends Effect {
	public Random random = new Random();

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
	public <T> void tick(EffectStack effectStack, EffectContainer<T> effectContainer) {
		if(effectContainer.getParent() instanceof Mob && this.random.nextFloat() > 0.6){
			Mob mob = (Mob)effectContainer.getParent();
			double angle = MathHelper.toRadians(this.random.nextInt(360));
			double radius = mob.bbWidth / 2.0f;
			double lx = mob.x + radius * Math.cos(angle);
			double lz = mob.z + radius * Math.sin(angle);
			double ly = mob.y + mob.bbHeight / 1.5f;

			if(mob instanceof Player){
				ly = mob.y - mob.heightOffset + mob.bbHeight / 2.0f;
			}
			ParticleHelper.spawnParticle(mob.world, "bleeding", lx, ly, lz, 0, -0.05, 0, 0, 16.0F);
		}
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
