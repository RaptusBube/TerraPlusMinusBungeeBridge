package de.btegermany.terraplusminusbungeebridge;

import de.btegermany.terraplusminusbungeebridge.listener.BungeePluginMessageListener;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;

public final class TerraPlusMinusBungeeBridge extends Plugin {

    @Override
    public void onEnable() {
        ProxyServer.getInstance().registerChannel("BungeeCord");
        ProxyServer.getInstance().registerChannel("terraplusminus:teleportbridge");
        ProxyServer.getInstance().getPluginManager().registerListener(this, new BungeePluginMessageListener());
        // Plugin startup logic
    }

    @Override
    public void onDisable() {
        // Unregister plugin messaging channel
        ProxyServer.getInstance().getPluginManager().unregisterListeners(this);
        // --------------------------
    }
}
