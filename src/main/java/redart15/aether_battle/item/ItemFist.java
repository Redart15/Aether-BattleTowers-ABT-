package redart15.aether_battle.item;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.Blocks;
import net.minecraft.core.block.tag.BlockTags;
import net.minecraft.core.data.tag.Tag;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.Mob;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.material.ArmorMaterial;
import net.minecraft.core.item.material.ToolMaterial;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;
import teamport.aether.block.AetherBlockTags;
import teamport.aether.item.accessory.ItemGloves;

public class ItemFist  extends ItemGloves implements ItemGloveTool{
	private final Tag<Block<?>>[] tagEffectiveAgainst = new Tag[]{
		BlockTags.MINEABLE_BY_SWORD,
		BlockTags.MINEABLE_BY_PICKAXE,
		BlockTags.MINEABLE_BY_AXE,
		BlockTags.MINEABLE_BY_SHOVEL,
		AetherBlockTags.MINEABLE_BY_AETHER_SWORD,
		AetherBlockTags.MINEABLE_BY_AETHER_PICKAXE,
		AetherBlockTags.MINEABLE_BY_AETHER_AXE,
		AetherBlockTags.MINEABLE_BY_AETHER_SHOVEL
	};
	private final int damageVsEntity;
	protected ToolMaterial material;

	public ItemFist(String translationKey, String namespaceId, int id, ArmorMaterial material, int accessoryPiece) {
		super(translationKey, namespaceId, id, material, accessoryPiece);
		this.damageVsEntity = 3;
		this.material = ToolMaterial.diamond;
	}

	public float getStrVsBlock(ItemStack itemstack, Block<?> block) {
		return this.hasTag(block, this.tagEffectiveAgainst) ? this.material.getEfficiency(false) : 1.0F;
	}

	private boolean hasTag(Block<?> block, Tag<Block<?>>[] tagEffectiveAgainst) {
		for(Tag tag : tagEffectiveAgainst){
			if(block.hasTag(tag)){
				return true;
			}
		}
		return false;
	}

	public boolean hitEntity(ItemStack itemstack, Mob target, Mob attacker) {
		itemstack.damageItem(2, attacker);
		return true;
	}

	public boolean onBlockDestroyed(World world, ItemStack itemstack, int i, int x, int y, int z, Side side, Mob mob) {
		Block<?> block = Blocks.blocksList[i];
		if (block != null && (block.getHardness() > 0.0F || this.isSilkTouch())) {
			itemstack.damageItem(1, mob);
		}

		return true;
	}

	public int getDamageVsEntity(Entity entity, ItemStack is) {
		return this.damageVsEntity;
	}

	public boolean isSilkTouch() {
		return this.material.isSilkTouch();
	}

	public ToolMaterial getMaterial() {
		return this.material;
	}
}
