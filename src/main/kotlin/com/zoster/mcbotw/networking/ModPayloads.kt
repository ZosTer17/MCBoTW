package com.zoster.mcbotw.networking

import com.zoster.mcbotw.Mcbotw.Companion.logger
import com.zoster.mcbotw.networking.cryonis.CryonisHudS2CPayload
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry
import net.minecraft.registry.Registries

object ModPayloads {
    fun registerC2SPackets() {

    }

    fun registerS2CPackets() {
        PayloadTypeRegistry.playS2C().register(CryonisHudS2CPayload.ID, CryonisHudS2CPayload.CODEC)
    }

    fun onClientReceive() {
        ClientPlayNetworking.registerGlobalReceiver(CryonisHudS2CPayload.ID) { payload, context ->
            context.client().execute {
                val blockId = payload.blockId
                val block = Registries.BLOCK.get(blockId)

                logger.info("Cryonis selezionato: $block")
            }
        }
    }
}