package redart15.aether_battle.block;

public class AetherBattleBlocks {
	private static boolean init = false;
	private AetherBattleBlocks(){}
	public static void init(){
		if(init) return;
		init = true;
	}
}
