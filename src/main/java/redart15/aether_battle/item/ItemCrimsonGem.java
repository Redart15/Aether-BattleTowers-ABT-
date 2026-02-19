package redart15.aether_battle.item;

import com.mojang.nbt.tags.CompoundTag;
import net.minecraft.core.Global;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.Mob;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.world.World;
import redart15.aether_battle.effect.AetherBattleEffects;
import sunsetsatellite.catalyst.effects.api.effect.EffectStack;
import sunsetsatellite.catalyst.effects.api.effect.IHasEffects;
import teamport.aether.item.accessory.IAccessoryEffects;
import teamport.aether.item.accessory.ItemTrinket;

import java.util.ArrayList;
import java.util.List;

public class ItemCrimsonGem extends ItemTrinket implements IAccessoryEffects {
	public static final int COOLDOWN = 6 * Global.TICKS_PER_SECOND;

	public ItemCrimsonGem(String translationKey, String namespaceId, int id, String name) {
		super(translationKey, namespaceId, id, name);
	}

	@Override
	public void inventoryTick(ItemStack itemstack, World world, Entity entity, int slotId, boolean flag) {
		if (!(entity instanceof Player)) {
			return;
		}
		Player player = (Player)entity;
		CompoundTag tag = itemstack.getData();
		if (slotId < player.inventory.mainInventory.length || slotId - player.inventory.mainInventory.length < 6) {
			tag.putInt("time", 0);
			return;
		}
		int time = advanceTime(tag);
		if (time <= COOLDOWN) {
			return;
		}
		tag.putInt("time", 0);
		List<Entity> entityList = world.getLoadedEntityList();
		for (Entity victom : new ArrayList<>(entityList)) {
			if (victom.distanceTo(player) >= 16
				|| !(victom instanceof IHasEffects)
				|| !(victom instanceof Mob)
//				|| victom == player
			) {
				continue;
			}
			EffectStack stack = new EffectStack((IHasEffects<?>) victom, AetherBattleEffects.blood_letting, 1);
			stack.start(((IHasEffects<?>) victom).getContainer());
			AetherBattleEffects.add(((IHasEffects<?>) victom).getContainer(), stack);
		}
	}

	private static int advanceTime(CompoundTag tag) {
		int time = tag.getInteger("time");
		tag.putInt("time", ++time);
		return time;
	}

	public void removeEffect(Player player, ItemStack accessory) {
		CompoundTag tag = accessory.getData();
		tag.putInt("time", 0);
	}
}
