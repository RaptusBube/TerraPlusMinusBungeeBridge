package de.btegermany.terraplusminusbungeebridge.main.version;

import de.btegermany.terraplusminusbungeebridge.listener.BungeePluginMessageListener;
import de.btegermany.terraplusminusbungeebridge.main.TerraPlusMinusProxyBridge;
import de.btegermany.terraplusminusbungeebridge.util.ProxyType;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;

public final class Bungee extends Plugin {

    public Bungee instance;
    public TerraPlusMinusProxyBridge terraPlusMinusProxyBridge;

    @Override
    public void onEnable() {
        this.instance = this;
        this.terraPlusMinusProxyBridge = new TerraPlusMinusProxyBridge(ProxyServer.getInstance().getLogger(), ProxyType.BUNGEECORD);
        ProxyServer.getInstance().registerChannel("BungeeCord");
        ProxyServer.getInstance().registerChannel("terraplusminus:teleportbridge");
        ProxyServer.getInstance().getPluginManager().registerListener(this, new BungeePluginMessageListener());
        terraPlusMinusProxyBridge.successfullyLoaded();
    }

    @Override
    public void onDisable() {
        // Unregister plugin messaging channel
        ProxyServer.getInstance().getPluginManager().unregisterListeners(this);
        // --------------------------
    }

    public Bungee getInstance() {
        return instance;
    }

    public TerraPlusMinusProxyBridge getTerraPlusMinusProxyBridge() {
        return terraPlusMinusProxyBridge;
    }


}
