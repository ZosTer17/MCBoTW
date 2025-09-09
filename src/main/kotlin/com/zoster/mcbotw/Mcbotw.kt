package com.zoster.mcbotw

import com.zoster.mcbotw.events.ModEvents
import com.zoster.mcbotw.networking.ModPayloads
import net.fabricmc.api.ModInitializer
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class Mcbotw : ModInitializer {
    companion object {
        const val MOD_ID = "mcbotw"
        val logger: Logger get() = LoggerFactory.getLogger(MOD_ID)
    }

    override fun onInitialize() {
        logger.info("La mod è stata attivata!")

        ModEvents.register()

        ModPayloads.registerS2CPackets()
    }
}
