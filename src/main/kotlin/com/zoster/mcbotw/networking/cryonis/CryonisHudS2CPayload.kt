package com.zoster.mcbotw.networking.cryonis

import com.zoster.mcbotw.Mcbotw.Companion.logger
import com.zoster.mcbotw.Mcbotw.Companion.MOD_ID
import net.minecraft.network.RegistryByteBuf
import net.minecraft.network.codec.PacketCodec
import net.minecraft.network.codec.PacketCodecs
import net.minecraft.network.packet.CustomPayload
import net.minecraft.util.Identifier

data class CryonisHudS2CPayload(val blockId: Int) : CustomPayload {
    init {
        logger.info("Nella classe vera e propria del cryonisHudPayload")
    }

    companion object {
        init {
            logger.info("Sono nell'init del CryonisHudS2CPayload")
        }

        private val CRYONIS_HUD_ID: Identifier = Identifier.of(MOD_ID, "cryonis_hud")
        val ID = CustomPayload.Id<CryonisHudS2CPayload>(CRYONIS_HUD_ID)
        val CODEC: PacketCodec<RegistryByteBuf, CryonisHudS2CPayload> =
            PacketCodec.tuple(PacketCodecs.INTEGER, CryonisHudS2CPayload::blockId, ::CryonisHudS2CPayload)
    }

    override fun getId(): CustomPayload.Id<out CustomPayload> {
        return ID
    }
}