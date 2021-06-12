package fr.gonzyui

import fr.gonzyui.utils.ItemBuilder
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent
import org.bukkit.plugin.java.JavaPlugin

public class Main: JavaPlugin(), Listener {

    override fun onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this)
        config.options().copyDefaults(true)
        saveConfig()
    }

    @EventHandler
    fun join(e: PlayerJoinEvent) {
        val p = e.player
        val item = ItemBuilder(Material.COMPASS).setName("§eNavigator").setUnbreakable(true).setLore("§cUse it to navigate into servers").addUnsafeEnchantment(Enchantment.DURABILITY, 150)
        p.inventory.setItem(0, item.toItemStack())
        e.joinMessage = config.getString("join_msg").replace("&", "§").replace("%player%", p.name)
    }

    @EventHandler
    fun quit(e: PlayerQuitEvent) {
        val p = e.player
        e.quitMessage = config.getString("quit_msg").replace("&", "§").replace("%player%", p.name)
    }
}