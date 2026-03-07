package redart15.aether_battle.mixin;

import com.mojang.nbt.tags.ListTag;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.player.inventory.container.ContainerInventory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import redart15.aether_battle.item.IAccessoryEffectsExtended;
import teamport.aether.item.accessory.IAccessoryEffects;

@Mixin(value = ContainerInventory.class, remap = false)
public class ContainerMixinAddEffect{

	@Inject(method = "readFromNBT", at = @At("TAIL"))
	public void activateAccessories(ListTag nbttaglist, CallbackInfo ci) {
		ContainerInventory inv = (ContainerInventory) (Object) this;
		for (ItemStack item : inv.armorInventory) {
			if (item != null && item.getItem() instanceof IAccessoryEffectsExtended) {
				((IAccessoryEffectsExtended) item.getItem()).addEffect(inv.player, item);
			}
		}
	}
}
