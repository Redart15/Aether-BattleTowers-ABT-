package redart15.aether_battle.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.Mob;
import net.minecraft.core.util.helper.DamageType;
import org.spongepowered.asm.mixin.Mixin;
import redart15.aether_battle.effect.AetherBattleEffects;
import sunsetsatellite.catalyst.effects.api.effect.EffectContainer;
import sunsetsatellite.catalyst.effects.api.effect.EffectStack;
import sunsetsatellite.catalyst.effects.api.effect.IHasEffects;

import java.util.List;

@Mixin(value = Mob.class, remap = false)
public abstract class MobMixinStoneSkin {

	@WrapMethod(method = "hurt")
	private boolean adjustDamage(Entity attacker, int damage, DamageType type, Operation<Boolean> original){
		Mob mob = (Mob) (Object) this;
		EffectContainer<?> container = ((IHasEffects<?>)mob).getContainer();
		if (!container.hasEffect(AetherBattleEffects.stoneSkin)) {
			return original.call(attacker, damage, type);
		}
		int stackSize = 0;
		List<EffectStack> listStack = container.getEffects();
		for (EffectStack effectStack : listStack) {
			if (effectStack.getEffect() == AetherBattleEffects.stoneSkin) {
				stackSize = effectStack.getAmount();
			}
		}
		if(damage > stackSize){
			return original.call(attacker, damage, type);
		}
		return original.call(attacker, 1, type);
	}
}
