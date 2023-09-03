package de.btegermany.terraplusminusbungeebridge.listener;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PluginMessageEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.UUID;

public class PluginMessageListener implements Listener {
    @EventHandler
    public void onPluginMessage(PluginMessageEvent event) {
        if (!event.getTag().equalsIgnoreCase("bungeecord:terraplusminus")) return;
        // get uuid and coordinates from message
        ByteArrayDataInput dataInput = ByteStreams.newDataInput(event.getData());
        String uuid = dataInput.readUTF();
        UUID playerUUID = UUID.fromString(uuid);

        String servername = dataInput.readUTF();
        String coordinates = dataInput.readUTF();

        // Assuming you have a method to get the player by UUID
        ProxiedPlayer player = ProxyServer.getInstance().getPlayer(playerUUID);
        if (player != null) {
            // Move the player to the specified server
            sendMessageToBukkitServer(player,servername, coordinates);
            player.connect(ProxyServer.getInstance().getServerInfo(servername));
        }
    }

    // method to send message via messaging channel to bukkit server
    public void sendMessageToBukkitServer(ProxiedPlayer player, String servername, String coordinates) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(stream);
        try {
            out.writeUTF(player.getUniqueId().toString());
            out.writeUTF(coordinates);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ProxyServer.getInstance().getServers().get(servername).sendData("bungeecord:terraplusminus", stream.toByteArray());
    }


}
