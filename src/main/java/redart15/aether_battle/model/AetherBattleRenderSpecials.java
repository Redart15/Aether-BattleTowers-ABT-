package redart15.aether_battle.model;

import net.minecraft.client.render.tessellator.Tessellator;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.item.ItemStack;

public interface AetherBattleRenderSpecials {
	void renderItemSpecialOnPlayer(Tessellator t, Player player, ItemStack stack, int layer, float partialTick);
	default void preRenderInventory(){}
	default void postRenderInventory(){}
}
