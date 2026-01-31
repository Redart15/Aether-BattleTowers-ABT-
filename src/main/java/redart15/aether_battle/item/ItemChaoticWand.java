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
import redart15.aether_battle.block.AetherBattleTags;
import redart15.aether_battle.entity.projectile.ProjectileChaoticWand;
import teamport.aether.entity.DamageInstance;

import java.util.List;
import java.util.Random;

import static teamport.aether.entity.DamageInstance.inst;

public class ItemChaoticWand extends Item {
	private static final int RANGE = 16;
	private static final int MAXDAMAGE = 19;

	public ItemChaoticWand(String translationKey, String namespaceId, int id, ToolMaterial material) {
		super(translationKey, namespaceId, id);
		this.maxStackSize = 1;
		this.setMaxDamage(material.getDurability());
	}

	public float getStrVsBlock(ItemStack itemstack, Block<?> block) {
		return block.hasTag(AetherBattleTags.BRIDLE) ? 100.0F : 1.5F;
	}

	@Override
	public ItemStack onUseItem(ItemStack itemstack, World world, Player player) {
		if (player.attackTime <= 0) {
			player.attackTime = 20;
			return this.shootBeam(itemstack, world, player);
		}
		return itemstack;
	}

	private ItemStack shootBeam(ItemStack itemstack, World world, Player player) {
		Random random = world.rand;
		DamageInstance instance = getDamage(random);
		if(instance.getDamage() == 1){
			player.hurt(player, 1, instance.getType());
			itemstack.damageItem(rollDice(random), player);
		}else if(instance.getDamage() == 20){
			world.entityJoinedWorld(new ProjectileChaoticWand(world, player, instance, getDamage(random)));
			itemstack.damageItem(1, player);
		}else{
			world.entityJoinedWorld(new ProjectileChaoticWand(world, player, instance));
			itemstack.damageItem(1, player);
		}
		return itemstack;
	}

	public static DamageInstance getDamage(Random random) {
		List<DamageType> damageTypes = DamageType.values();
		DamageType damageType = damageTypes.get(random.nextInt(damageTypes.size()));
		int damage = rollDice(random);
		return inst(damage, damageType);
	}

	public static int rollDice(Random random){
		return random.nextInt(MAXDAMAGE) + 1;
	}

	public void onUseByActivator(ItemStack itemStack, TileEntityActivator activatorBlock, World world, Random random, int blockX, int blockY, int blockZ, double offX, double offY, double offZ, Direction direction) {
		blockX += direction.getOffsetX();
		blockY += direction.getOffsetY();
		blockZ += direction.getOffsetZ();
		Block<?> block = world.getBlock(blockX, blockY, blockZ);
		if(block != null && block.hasTag(AetherBattleTags.BRIDLE)){
			world.setBlockWithNotify(blockX, blockY, blockZ, 0);
			world.playBlockEvent(null, LevelListener.EVENT_BLOCK_BREAK, blockX, blockY, blockZ, block.id());
		}
	}

	public boolean canHarvestBlock(Mob mob, ItemStack itemStack, Block<?> block) {
		return false;
	}

}
