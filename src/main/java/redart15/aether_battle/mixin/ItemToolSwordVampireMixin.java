package redart15.aether_battle.mixin;

import net.minecraft.core.entity.Mob;
import net.minecraft.core.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import redart15.aether_battle.effect.AetherBattleEffects;
import sunsetsatellite.catalyst.effects.api.effect.EffectStack;
import sunsetsatellite.catalyst.effects.api.effect.IHasEffects;
import teamport.aether.item.item_tool.ItemToolSwordVampire;

@Mixin(value = ItemToolSwordVampire.class, remap = false)
public abstract class ItemToolSwordVampireMixin {

	@Inject(method = "hitEntity", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/entity/Mob;getHealth()I", shift = At.Shift.BEFORE))
	public void addParticles(ItemStack itemstack, Mob target, Mob attacker, CallbackInfoReturnable<Boolean> cir){
		EffectStack stack = new EffectStack((IHasEffects<?>) target, AetherBattleEffects.bleeding, 1);
		stack.start(((IHasEffects<?>) target).getContainer());
		((IHasEffects<?>) target).getContainer().add(stack);
	}
}
