package me.gidonyou.itemrename;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.plugin.java.JavaPlugin;

public class ItemRename extends JavaPlugin implements Listener {

	public void onEnable() {
		System.out.println("Item Rename: Copyright (C) 2014 gidonyou");
		
		getServer().getPluginManager().registerEvents(this, this);
	}

	@EventHandler
	public void onInventoryClicked(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();

		if (!(event.getInventory() instanceof AnvilInventory))
			return;

		InventoryView view = event.getView();
		int rawSlot = event.getRawSlot();
		
		if (rawSlot != view.convertSlot(rawSlot))
			return;

		if (rawSlot == 2) {
			player.sendMessage(view.getItem(1).getType().name());
			if (view.getItem(1).getType() != Material.AIR) {
				event.setCancelled(true);
				player.closeInventory();
				player.sendMessage(ChatColor.RED + "모루로는 아이템이름을 바꿀수밖에 없습니다.");
				return;
			}
		}
	}
}
