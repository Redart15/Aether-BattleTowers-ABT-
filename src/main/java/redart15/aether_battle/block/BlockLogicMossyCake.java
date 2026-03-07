package redart15.aether_battle.block;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogicEdible;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.util.phys.AABB;
import net.minecraft.core.world.World;
import net.minecraft.core.world.WorldSource;
import redart15.aether_battle.item.AetherBattleItems;
import sunsetsatellite.catalyst.effects.api.effect.IHasEffects;

public class BlockLogicMossyCake extends BlockLogicEdible {
	public BlockLogicMossyCake(Block<?> block) {
		super(block, 4, 0, () -> AetherBattleItems.MOSSY_CAKE);
	}

	public AABB getBlockBoundsFromState(WorldSource world, int x, int y, int z) {
		int l = world.getBlockMetadata(x, y, z);
		float f = 0.0625F;
		float f1 = (float)(1 + l * 2) / 16.0F;
		float f2 = 0.5F;
		return AABB.getTemporaryBB(f1, 0.0F, f, 1.0F - f, f2, 1.0F - f);
	}

	public boolean onBlockRightClicked(World world, int x, int y, int z, Player player, Side side, double xPlaced, double yPlaced) {
		if(player instanceof IHasEffects){
			// apply regen
		}
		return super.onBlockRightClicked(world, x, y, z, player, side, xPlaced, yPlaced);
	}
}
