package fr.gonzyui.utils

import org.bukkit.Color
import org.bukkit.DyeColor
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.LeatherArmorMeta
import org.bukkit.inventory.meta.SkullMeta
import java.lang.ClassCastException
import java.util.*

class ItemBuilder {

    private lateinit var `is`: ItemStack

    constructor(`is`: ItemStack) {
        this.`is` = `is`;
    }

    constructor(material: Material?, amount: Int = 1) {
        `is` = ItemStack(material!!, amount)
    }

    constructor(material: Material?, amount: Int, meta: Short) {
        `is` = ItemStack(material!!, amount, meta)
    }

    fun clone(): ItemBuilder {
        return ItemBuilder(`is`)
    }

    fun setDurability(durability: Short): ItemBuilder {
        `is`.durability = durability
        return this
    }

    fun setName(name: String?): ItemBuilder {
        val im = `is`.itemMeta
        im!!.setDisplayName(name)
        `is`.itemMeta = im
        return this
    }

    fun addUnsafeEnchantment(enchantment: Enchantment?, level: Int): ItemBuilder {
        `is`.addUnsafeEnchantment(enchantment!!, level)
        return this
    }

    fun removeEnchantment(enchantment: Enchantment?): ItemBuilder {
        `is`.removeEnchantment(enchantment!!)
        return this
    }

    fun setSkullOwner(owner: String?): ItemBuilder {
        try {
            val im = `is`.itemMeta as SkullMeta
            im!!.owner = owner
            `is`.itemMeta = im
        } catch (e: ClassCastException) {
        }
        return this
    }

    fun addEnchantment(enchantment: Enchantment?, level: Int): ItemBuilder {
        val im = `is`.itemMeta
        im!!.addEnchant(enchantment!!, level, true)
        `is`.itemMeta = im
        return this
    }

    fun setUnbreakable(unbreakable: Boolean): ItemBuilder {
        val im = `is`.itemMeta
        im!!.spigot().isUnbreakable = unbreakable
        `is`.itemMeta = im
        return this
    }

    fun setLore(vararg lore: String?): ItemBuilder {
        val im = `is`.itemMeta
        im!!.lore = Arrays.asList(*lore)
        `is`.itemMeta = im
        return this
    }

    fun setWoolColor(color: DyeColor): ItemBuilder {
        if (`is`.type != Material.WOOL) return this
        `is`.durability = color.woolData.toShort()
        return this
    }

    fun setClayColor(color: DyeColor): ItemBuilder {
        if (`is`.type != Material.STAINED_CLAY) return this
        `is`.durability = color.dyeData.toShort()
        return this
    }

    fun setGlassColor(color: DyeColor): ItemBuilder {
        if (`is`.type != Material.STAINED_GLASS_PANE) return this
        `is`.durability = color.dyeData.toShort()
        return this
    }

    fun setCarpetColor(color: DyeColor): ItemBuilder {
        if (`is`.type != Material.CARPET) return this
        `is`.durability = color.dyeData.toShort()
        return this
    }

    fun setLeatherArmorColor(color: Color?): ItemBuilder {
        try {
            val im = `is`.itemMeta as LeatherArmorMeta
            im!!.setColor(color)
            `is`.itemMeta = im
        } catch (e: ClassCastException) {
        }
        return this
    }

    fun toItemStack(): ItemStack {
        return `is`
    }
}