package redart15.aether_battle.mixin;

import net.minecraft.client.render.Lighting;
import net.minecraft.client.render.entity.MobRendererPlayer;
import net.minecraft.client.render.tessellator.Tessellator;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.item.ItemStack;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import redart15.aether_battle.item.AetherBattleRenderSpecials;

@Mixin(value = MobRendererPlayer.class, remap = false)
public class MobRendererPlayerMixinCircle {

	@Inject(method = "renderSpecials(Lnet/minecraft/core/entity/player/Player;F)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/player/inventory/container/ContainerInventory;armorItemInSlot(I)Lnet/minecraft/core/item/ItemStack;"))
	public void renderExtras(Player player, float partialTick, CallbackInfo ci){
		for(int renderPass = 0; renderPass < player.inventory.armorInventory.length; renderPass++){
			GL11.glPushMatrix();
			ItemStack itemstack = player.inventory.armorItemInSlot(renderPass);
			if (itemstack != null && itemstack.getItem() instanceof AetherBattleRenderSpecials) {
				((AetherBattleRenderSpecials) itemstack.getItem()).renderSpecials(Tessellator.instance, player, itemstack, renderPass, partialTick);
			}
			GL11.glPopMatrix();
		}
	}


}
