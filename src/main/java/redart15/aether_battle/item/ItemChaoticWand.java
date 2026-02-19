package redart15.aether_battle.item;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.entity.TileEntityActivator;
import net.minecraft.core.entity.Mob;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.material.ToolMaterial;
import net.minecraft.core.util.helper.DamageType;
import net.minecraft.core.util.helper.Direction;
import net.minecraft.core.world.LevelListener;
import net.minecraft.core.world.World;
import redart15.aether_battle.block.AetherBattleBlockTags;
import redart15.aether_battle.entity.projectile.ProjectileChaotic;
import teamport.aether.entity.DamageInstance;

import java.util.List;
import java.util.Random;

import static teamport.aether.entity.DamageInstance.inst;

public class ItemChaoticWand extends Item {
	private static final int MAXDAMAGE = 19;

	public ItemChaoticWand(String translationKey, String namespaceId, int id, ToolMaterial material) {
		super(translationKey, namespaceId, id);
		this.maxStackSize = 1;
		this.setMaxDamage(material.getDurability());
	}

	public float getStrVsBlock(ItemStack itemstack, Block<?> block) {
		return block.hasTag(AetherBattleBlockTags.BRIDLE) ? 100.0F : 1.5F;
	}

	@Override
	public ItemStack onUseItem(ItemStack itemstack, World world, Player player) {
		if (player.attackTime <= 0) {
			player.attackTime = 20;
			player.swingItem();
			return this.shootBeam(itemstack, world, player);
		}
		return itemstack;
	}

	private ItemStack shootBeam(ItemStack itemstack, World world, Player player) {
		Random random = world.rand;
		DamageInstance[] instances = this.setUpDamage(random);
		DamageInstance instance = instances[0];
		if (instances.length == 1) {
			world.entityJoinedWorld(new ProjectileChaotic(world, player, instance));
			itemstack.damageItem(instance.getDamage(), player);
			return itemstack;
		}
		world.entityJoinedWorld(new ProjectileChaotic(world, player, instances));
		itemstack.damageItem(1, player);
		return itemstack;
	}

	public DamageInstance[] setUpDamage(Random random) {
		int[] rolls = new int[5];
		int count = 1;
		rolls[0] = rollDice(random);
		while (rolls[count - 1] > 19) {
			rolls[count] = rollDice(random);
			count++;
		}
		DamageInstance[] instances = new DamageInstance[count];
		List<DamageType> damageTypes = DamageType.values();
		for (int c = 0; c < count; c++) {
			instances[c] = inst((int)Math.ceil(rolls[c] / 2.0F), damageTypes.get(random.nextInt(damageTypes.size())));
		}
		return instances;
	}

	public static int rollDice(Random random) {
		return random.nextInt(MAXDAMAGE) + 1;
	}

	public void onUseByActivator(ItemStack itemStack, TileEntityActivator activatorBlock, World world, Random random, int blockX, int blockY, int blockZ, double offX, double offY, double offZ, Direction direction) {
		blockX += direction.getOffsetX();
		blockY += direction.getOffsetY();
		blockZ += direction.getOffsetZ();
		Block<?> block = world.getBlock(blockX, blockY, blockZ);
		if (block != null && block.hasTag(AetherBattleBlockTags.BRIDLE)) {
			world.setBlockWithNotify(blockX, blockY, blockZ, 0);
			world.playBlockEvent(null, LevelListener.EVENT_BLOCK_BREAK, blockX, blockY, blockZ, block.id());
		}
	}

	public boolean canHarvestBlock(Mob mob, ItemStack itemStack, Block<?> block) {
		return false;
	}

}
