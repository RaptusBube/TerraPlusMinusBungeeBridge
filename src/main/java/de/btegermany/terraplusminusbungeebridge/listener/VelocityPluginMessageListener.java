package de.btegermany.terraplusminusbungeebridge.listener;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.connection.PluginMessageEvent;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ProxyServer;
import com.velocitypowered.api.proxy.ServerConnection;
import com.velocitypowered.api.proxy.messages.MinecraftChannelIdentifier;
import com.velocitypowered.api.proxy.server.RegisteredServer;
import org.yaml.snakeyaml.events.Event;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

public class VelocityPluginMessageListener {

    public static final MinecraftChannelIdentifier IDENTIFIER = MinecraftChannelIdentifier.from("terraplusminus:teleportbridge");
    private final ProxyServer server;

    public VelocityPluginMessageListener(ProxyServer server) {
        this.server = server;
    }

    @Subscribe
    public void onPluginMessage(PluginMessageEvent event){
        if (!(event.getSource() instanceof ServerConnection)) {
            return;
        }
        ServerConnection backend = (ServerConnection) event.getSource();
        if (event.getIdentifier() != IDENTIFIER) {
            return;
        }
        ByteArrayDataInput dataInput = ByteStreams.newDataInput(event.getData());
        String uuid = dataInput.readUTF();
        UUID playerUUID = UUID.fromString(uuid);

        String servername = dataInput.readUTF().split(",")[0];
        String coordinates = dataInput.readUTF();

        if (server.getPlayer(playerUUID).isPresent() && server.getServer(servername).isPresent()) {
            Player player =  server.getPlayer(playerUUID).get();
            RegisteredServer registeredServer = server.getServer(servername).get();

            player.createConnectionRequest(registeredServer).connect();

            new Thread(() -> {
                while (player.getCurrentServer().isPresent()){
                    continue;
                }

                while (!player.getCurrentServer().isPresent()){
                    continue;
                }
                while (!player.getCurrentServer().get().getServer().getServerInfo().getName().equalsIgnoreCase(registeredServer.getServerInfo().getName())){
                    continue;
                }

                sendMessageToBackendServer(player, registeredServer, coordinates);

            }).start();
        }



    }

    private void sendMessageToBackendServer(Player player, RegisteredServer registeredServer, String coordinates){
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(stream);
        try {
            out.writeUTF(player.getUniqueId().toString());
            out.writeUTF(coordinates);
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        registeredServer.sendPluginMessage(IDENTIFIER, stream.toByteArray());
    }





}
