package com.zoster.mcbotw.mixin;

import com.zoster.mcbotw.networking.cryonis.CryonisHudS2CPayload;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.block.Block;
import net.minecraft.item.Items;
import net.minecraft.network.packet.c2s.play.UpdateSelectedSlotC2SPacket;
import net.minecraft.registry.Registries;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayNetworkHandler.class)
public abstract class ServerPlayNetworkHandlerMixin {
    @Shadow
    public ServerPlayerEntity player;

    @Inject(method = "onUpdateSelectedSlot", at = @At("HEAD"))
    private void onUpdateSelectedSlot(UpdateSelectedSlotC2SPacket packet, CallbackInfo ci) {
        if (player.getServer() == null || Thread.currentThread() != player.getServer().getThread())
            return;

        final var player = this.player;
        final var item = player.getInventory().getStack(packet.getSelectedSlot()).getItem();

        if (item == Items.BLUE_ICE) {
            final var blockId = Registries.BLOCK.getRawId(Block.getBlockFromItem(item));
            final var payload = new CryonisHudS2CPayload(blockId);

            ServerPlayNetworking.send(player, payload);
        }
    }
}