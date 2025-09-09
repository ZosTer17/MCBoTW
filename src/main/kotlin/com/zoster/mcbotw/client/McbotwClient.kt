package com.zoster.mcbotw.client

import com.zoster.mcbotw.Mcbotw.Companion.logger
import com.zoster.mcbotw.networking.ModPayloads
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents

class McbotwClient : ClientModInitializer {
    override fun onInitializeClient() {
        logger.info("Prima di chiamare onClientReceive lato client")

        ModPayloads.onClientReceive()

        ClientTickEvents.END_CLIENT_TICK.register { client ->
            val player = client.player
            val yaw = player?.yaw
            val pitch = player?.pitch

            logger.info("yaw: $yaw")
            logger.info("pitch: $pitch")
        }
    }
}
