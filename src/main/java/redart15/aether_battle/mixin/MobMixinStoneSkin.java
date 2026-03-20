package redart15.aether_battle.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.Mob;
import net.minecraft.core.util.helper.DamageType;
import org.spongepowered.asm.mixin.Mixin;
import redart15.aether_battle.effect.AetherBattleEffects;
import sunsetsatellite.catalyst.effects.api.effect.IHasEffects;

@Mixin(value = Mob.class, remap = false)
public abstract class MobMixinStoneSkin {

	@WrapMethod(method = "hurt")
	private boolean adjustDamage(Entity attacker, int damage, DamageType type, Operation<Boolean> original){
		IHasEffects<?> mob = (IHasEffects<?>) this;
		damage = AetherBattleEffects.adjustDamageStoneSkin(damage, mob.getContainer(), mob);
		return original.call(attacker, damage, type);
	}

}
