package com.zoster.mcbotw.events

import com.zoster.mcbotw.Mcbotw.Companion.logger
import net.fabricmc.fabric.api.event.player.UseBlockCallback
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents
import net.minecraft.text.Text
import net.minecraft.util.ActionResult

object ModEvents {
    fun register() {
        logger.info("Registrando gli eventi...")

        onPlayerJoin()
        onCryonisUse()
    }

    private fun onPlayerJoin() {
        ServerPlayConnectionEvents.JOIN.register { handler, _, _ ->
            val player = handler.player
            logger.info("${player.name} è entrato!")
            // player.inventory.setStack(0, ItemStack(Items.BLUE_ICE))
        }
    }

    private fun onCryonisUse() {
        UseBlockCallback.EVENT.register { player, world, hand, blockHit ->
            player.sendMessage(Text.of { "metodo attivato: onCryonisUse" }, true)
            ActionResult.SUCCESS
        }
    }
}