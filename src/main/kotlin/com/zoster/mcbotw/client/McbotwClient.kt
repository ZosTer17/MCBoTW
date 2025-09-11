package com.zoster.mcbotw.client

import com.zoster.mcbotw.Mcbotw.Companion.logger
import com.zoster.mcbotw.networking.ModPayloads
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents
import net.minecraft.client.MinecraftClient
import net.minecraft.client.render.RenderLayer
import net.minecraft.client.util.ObjectAllocator
import net.minecraft.world.RaycastContext
import kotlin.math.abs

class McbotwClient : ClientModInitializer {
    object Direction {
        var lastYaw = 0f
        var lastPitch = 0f
    }

    override fun onInitializeClient() {
        logger.info("Prima di chiamare onClientReceive lato client")

        ClientTickEvents.END_CLIENT_TICK.register { client ->
            val player = client.player ?: return@register
            val delta = client.renderTickCounter.dynamicDeltaTicks

            with (player) {
                val interYaw = getYaw(delta)
                val interPitch = getPitch(delta)

                /* Controlla, ad ogni tick, se lo yaw e il pitch cambiano valore */
                if (abs(interYaw - Direction.lastYaw) < 0.01f && abs(interPitch - Direction.lastPitch) < 0.01f)
                    return@register

                Direction.lastYaw = interYaw
                Direction.lastPitch = interPitch

                logger.info("yaw: $interYaw")
                logger.info("pitch: $interPitch")
            }
        }

        WorldRenderEvents.AFTER_ENTITIES.register { context ->
            val client = MinecraftClient.getInstance()
            val player = client.player ?: return@register

            player.getCameraPosVec(1F)

            val matrices = context.matrixStack() ?: return@register
            val consumers = context.consumers() ?: return@register

            matrices.push()
            val buffer = consumers.getBuffer(RenderLayer.getSolid())

            val matrix = matrices.peek().positionMatrix

        }

        ModPayloads.onClientReceive()
    }
}
