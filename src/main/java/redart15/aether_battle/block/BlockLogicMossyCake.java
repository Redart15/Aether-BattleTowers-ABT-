package redart15.aether_battle.block;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogicEdible;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.util.phys.AABB;
import net.minecraft.core.world.World;
import net.minecraft.core.world.WorldSource;
import redart15.aether_battle.effect.AetherBattleEffects;
import redart15.aether_battle.item.AetherBattleItems;
import sunsetsatellite.catalyst.effects.api.effect.EffectStack;
import sunsetsatellite.catalyst.effects.api.effect.IHasEffects;

import java.util.Random;

public class BlockLogicMossyCake extends BlockLogicEdible {
	private static final Random random = new Random();

	public BlockLogicMossyCake(Block<?> block) {
		super(block, 4, 0, () -> AetherBattleItems.MOSSY_CAKE);
	}

	@Override
	public AABB getBlockBoundsFromState(WorldSource world, int x, int y, int z) {
		int l = world.getBlockMetadata(x, y, z);
		float f = 0.0625F;
		float xMin = l >= 2 ? 0.5F : f;
		float zMin = l >= 3 ? 0.5F : f;
		float f2 = 0.375F;
		return AABB.getTemporaryBB(xMin, 0.0F, zMin, 1.0F - f, f2, 1.0F - f);
	}

	@Override
	public boolean onBlockRightClicked(World world, int x, int y, int z, Player player, Side side, double xPlaced, double yPlaced) {
		if (player instanceof IHasEffects) {
			IHasEffects<?> hasEffect = (IHasEffects<?>) player;
			EffectStack stack = new EffectStack(hasEffect, AetherBattleEffects.regeneration);
			hasEffect.getContainer().add(stack);
			stack.start(hasEffect.getContainer());
			world.playSoundAtEntity(player, player, "random.bite", 0.5F + (random.nextFloat() - random.nextFloat()) * 0.1F, 1.1F + (random.nextFloat() - random.nextFloat()) * 0.1F);
		}
		int data = world.getBlockMetadata(x, y, z) + 1;
		if (data >= this.maxBites) {
			world.setBlockWithNotify(x, y, z, 0);
		} else {
			world.setBlockMetadataWithNotify(x, y, z, data);
			world.markBlockDirty(x, y, z);
		}
		return true;
	}
}
