package de.btegermany.terraplusminusbungeebridge;

import de.btegermany.terraplusminusbungeebridge.listener.PluginMessageListener;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;

public final class TerraPlusMinusBungeeBridge extends Plugin {

    @Override
    public void onEnable() {
        ProxyServer.getInstance().registerChannel("BungeeCord");
        ProxyServer.getInstance().registerChannel("bungeecord:terraplusminus");
        ProxyServer.getInstance().getPluginManager().registerListener(this, new PluginMessageListener());
        // Plugin startup logic
    }

    @Override
    public void onDisable() {
        // Unregister plugin messaging channel
        ProxyServer.getInstance().getPluginManager().unregisterListeners(this);
        // --------------------------
    }
}
