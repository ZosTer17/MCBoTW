package com.zoster.mcbotw

import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import org.slf4j.LoggerFactory

object Mcbotw : ModInitializer {
    private const val MOD_ID = "mcbotw"
    private val logger = LoggerFactory.getLogger(MOD_ID)

    override fun onInitialize() {
        logger.info("La mod è stata attivata!")

        ServerPlayConnectionEvents.JOIN.register { handler, _, _ ->
            val player = handler.player
            player.inventory.insertStack(0, ItemStack(Items.BLUE_ICE))
        }
    }
}
