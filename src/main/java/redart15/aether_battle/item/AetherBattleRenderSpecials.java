package redart15.aether_battle.item;

import net.minecraft.client.render.tessellator.Tessellator;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.item.ItemStack;

public interface AetherBattleRenderSpecials {
	void renderSpecials(Tessellator t, Player player, ItemStack stack, int layer, float partialTick);
}
