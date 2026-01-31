package redart15.aether_battle.block;

public class AetherBattleBlockDetails {
	private static boolean init = false;
	private AetherBattleBlockDetails(){}
	public static void init(){
		if(init) return;
		init = true;
	}
}
