package me.kzheart.fiveelements

import me.kzheart.fiveelements.element.getMainElement
import org.bukkit.entity.Player
import taboolib.platform.compat.PlaceholderExpansion


/**
 * @author kzheart
 * @date 2021/11/1 18:19
 */
object ElementPlaceholder : PlaceholderExpansion {

    override val identifier = "elements"
    override fun onPlaceholderRequest(player: Player, args: String): String {
        return when (args) {
            "main_element" -> player.getMainElement().elementName
            else -> "null"
        }
    }
}