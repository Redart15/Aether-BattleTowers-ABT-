package redart15.aether_battle.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.player.inventory.menu.MenuAbstract;
import net.minecraft.core.player.inventory.slot.Slot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import redart15.aether_battle.item.IAccessoryEffectsExtended;

@Mixin(value = MenuAbstract.class, remap = false)
public abstract class MenuAbstractMixinAddEffect {

	@Inject(method = "handleArmorEquip", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/item/IArmorItem;getArmorPiece()I"))
	private void addEffect(Slot slot, Player player, CallbackInfo ci, @Local ItemStack stackInSlot){
		if(stackInSlot.getItem() instanceof IAccessoryEffectsExtended){
			((IAccessoryEffectsExtended) stackInSlot.getItem()).addEffect(player, stackInSlot);
		}
	}
}
