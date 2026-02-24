package redart15.aether_battle.block;

import jamdoggie.betterbattletowers.block.crumbling_stone.BlockLogicCrumbling;
import jamdoggie.betterbattletowers.block.crumbling_stone.BlockLogicSlabCrumbling;
import jamdoggie.betterbattletowers.block.crumbling_stone.BlockLogicStairsCrumbling;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.block.tag.BlockTags;
import net.minecraft.core.sound.BlockSounds;
import redart15.aether_battle.config.AetherBattleConfig;
import teamport.aether.block.AetherBlockTags;
import teamport.aether.block.AetherBlocks;
import turniplabs.halplibe.helper.BlockBuilder;

import static redart15.aether_battle.AetherBattleMod.MOD_ID;

//java:S1104, S1444, S3008
@SuppressWarnings({"java:S1104", "java:S1444", "java:S3008", "java:S1611"})
public class AetherBattleBlocks {
	public static Block<BlockLogicCrumbling> CARVED_STONE_CRUMBLING;
	public static Block<BlockLogicCrumbling> CARVED_STONE_LIGHT_CRUMBLING;
	public static Block<BlockLogicSlabCrumbling> CARVED_STONE_SLAB_CRUMBLING;
	public static Block<BlockLogicStairsCrumbling> CARVED_STONE_STAIR_CRUMBLING;

	public static Block<BlockLogicCrumbling> CARVED_ANGELIC_CRUMBLING;
	public static Block<BlockLogicCrumbling> CARVED_ANGELIC_LIGHT_CRUMBLING;
	public static Block<BlockLogicSlabCrumbling> CARVED_ANGELIC_SLAB_CRUMBLING;
	public static Block<BlockLogicStairsCrumbling> CARVED_ANGELIC_STAIR_CRUMBLING;

	public static Block<BlockLogicCrumbling> CARVED_HELLFIRE_CRUMBLING;
	public static Block<BlockLogicCrumbling	> CARVED_HELLFIRE_LIGHT_CRUMBLING;
	public static Block<BlockLogicSlabCrumbling> CARVED_HELLFIRE_SLAB_CRUMBLING;
	public static Block<BlockLogicStairsCrumbling> CARVED_HELLFIRE_STAIR_CRUMBLING;

	private static boolean init = false;
	private AetherBattleBlocks(){}
	public static void init(){
		if(init) return;
		init = true;
		AetherBattleBlocks.createTrapBlocks();
	}

	private static void createTrapBlocks() {
		BlockBuilder crumblingBlock = (new BlockBuilder(MOD_ID))
			.setBlockSound(BlockSounds.STONE)
			.setHardness(1.5F)
			.setTags(AetherBlockTags.MINEABLE_BY_AETHER_PICKAXE, BlockTags.CHAINLINK_FENCES_CONNECT, BlockTags.CAN_HANG_OFF); // , BlockTags.NOT_IN_CREATIVE_MENU
		BlockBuilder crumblingHellFireBlock = crumblingBlock.setInfiniburn();
		BlockBuilder crumblingSlab = crumblingBlock.setVisualUpdateOnMetadata();
		BlockBuilder crumblingStairs = crumblingSlab.setUseInternalLight();

		CARVED_STONE_CRUMBLING = crumblingBlock.build("crumbling_carved_stone", AetherBattleConfig.nextBlockID(), (b) -> new BlockLogicCrumbling(b, AetherBlocks.CARVED_STONE, Material.stone, 3.0F));
		CARVED_STONE_LIGHT_CRUMBLING = crumblingBlock.setLuminance(10).build("crumbling_carved_stone_light", AetherBattleConfig.nextBlockID(), (b) -> new BlockLogicCrumbling(b, AetherBlocks.CARVED_STONE, Material.stone, 3.0F));
		CARVED_STONE_SLAB_CRUMBLING = crumblingSlab.build("crumbling_slab_carved_stone", AetherBattleConfig.nextBlockID(), (b) -> new BlockLogicSlabCrumbling(b, CARVED_STONE_CRUMBLING, AetherBlocks.SLAB_CARVED_STONE));
		CARVED_STONE_STAIR_CRUMBLING = crumblingStairs.build("crumbling_stairs_carved_stone", AetherBattleConfig.nextBlockID(), (b) -> new BlockLogicStairsCrumbling(b, CARVED_STONE_CRUMBLING, AetherBlocks.SLAB_CARVED_STONE));

		CARVED_ANGELIC_CRUMBLING = crumblingBlock.build("crumbling_carved_angelic", AetherBattleConfig.nextBlockID(), (b) -> new BlockLogicCrumbling(b, AetherBlocks.CARVED_ANGELIC, Material.stone, 3.0F));
		CARVED_ANGELIC_LIGHT_CRUMBLING = crumblingBlock.setLuminance(10).build("crumbling_carved_angelic_light", AetherBattleConfig.nextBlockID(), (b) -> new BlockLogicCrumbling(b, AetherBlocks.CARVED_ANGELIC, Material.stone, 3.0F));
		CARVED_ANGELIC_SLAB_CRUMBLING = crumblingSlab.build("crumbling_slab_carved_angelic", AetherBattleConfig.nextBlockID(), (b) -> new BlockLogicSlabCrumbling(b, CARVED_ANGELIC_CRUMBLING, AetherBlocks.SLAB_CARVED_ANGELIC));
		CARVED_ANGELIC_STAIR_CRUMBLING = crumblingStairs.build("crumbling_stairs_carved_angelic", AetherBattleConfig.nextBlockID(), (b) -> new BlockLogicStairsCrumbling(b, CARVED_ANGELIC_CRUMBLING, AetherBlocks.SLAB_CARVED_ANGELIC));

		CARVED_HELLFIRE_CRUMBLING = crumblingHellFireBlock.build("crumbling_carved_hellfire", AetherBattleConfig.nextBlockID(), (b) -> new BlockLogicCrumbling(b, AetherBlocks.CARVED_ANGELIC, Material.stone, 3.0F));
		CARVED_HELLFIRE_LIGHT_CRUMBLING = crumblingHellFireBlock.setLightOpacity(10).build("crumbling_carved_hellfire_light", AetherBattleConfig.nextBlockID(), (b) -> new BlockLogicCrumbling(b, AetherBlocks.CARVED_ANGELIC, Material.stone, 3.0F));
		CARVED_HELLFIRE_SLAB_CRUMBLING = crumblingSlab.build("crumbling_slab_carved_hellfire", AetherBattleConfig.nextBlockID(), (b) -> new BlockLogicSlabCrumbling(b, CARVED_HELLFIRE_CRUMBLING, AetherBlocks.SLAB_CARVED_HELLFIRE));
		CARVED_HELLFIRE_STAIR_CRUMBLING = crumblingStairs.build("crumbling_stairs_carved_hellfire", AetherBattleConfig.nextBlockID(), (b) -> new BlockLogicStairsCrumbling(b, CARVED_HELLFIRE_CRUMBLING, AetherBlocks.SLAB_CARVED_HELLFIRE));


	}


}
