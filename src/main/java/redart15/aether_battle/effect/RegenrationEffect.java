package redart15.aether_battle.effect;

import jamdoggie.betterbattletowers.util.ParticleHelper;
import net.minecraft.core.Global;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.Mob;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.util.helper.MathHelper;
import sunsetsatellite.catalyst.effects.api.effect.*;
import sunsetsatellite.catalyst.effects.api.modifier.Modifier;
import teamport.aether.mixin.accessors.EntityAccessor;

import java.util.List;
import java.util.Random;

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
		int time = effectStack.getTimeLeft();
		if(mob.getHealth() < mob.getMaxHealth() && (time % (4 * Global.TICKS_PER_SECOND) == 0)){
			mob.heal(1);
		}
		spawnParticles(mob);
	}

	private static void spawnParticles(Mob mob) {
		Random random = ((EntityAccessor)mob).getRandom();

		double angle = MathHelper.toRadians(random.nextInt(360));
		double radius = mob.bbWidth / 2.0f * random.nextFloat();
		double lx = mob.x + radius * Math.cos(angle);
		double lz = mob.z + radius * Math.sin(angle);
		double ly = mob.y + mob.bbHeight / 1.5f;

		if(mob instanceof Player){
			ly = mob.y - mob.heightOffset + mob.bbHeight / 2.0f;
		}
		ParticleHelper.spawnParticle(mob.world, "heart", lx, ly, lz, 0, 0.4, 0, 0, 32);
	}
}
