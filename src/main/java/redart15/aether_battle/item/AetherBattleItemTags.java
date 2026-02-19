package redart15.aether_battle.item;

import net.minecraft.core.data.tag.Tag;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.tag.ItemTags;
import redart15.aether_battle.AetherBattleMod;

import java.lang.reflect.Field;

public class AetherBattleItemTags {

	private static boolean init = false;
	private AetherBattleItemTags(){}
	public static void init(){
		if(init) return;
		init = true;
		AetherBattleItemTags.initTags();
	}

	private static void initTags(){
		for(Field field : AetherBattleItemTags.class.getDeclaredFields()) {
			if (field.getType().equals(Tag.class)) {
				try {
					Tag<Item> tag = (Tag)field.get(null);
					ItemTags.TAG_LIST.add(tag);
				} catch (Exception e) {
					AetherBattleMod.LOGGER.error("Failed to add tag '{}'!", field.getName(), e);
				}
			}
		}
	}
}
