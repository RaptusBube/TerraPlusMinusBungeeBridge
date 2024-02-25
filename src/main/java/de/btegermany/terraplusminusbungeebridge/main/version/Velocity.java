package de.btegermany.terraplusminusbungeebridge.main.version;

import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.proxy.ProxyServer;
import de.btegermany.terraplusminusbungeebridge.listener.VelocityPluginMessageListener;
import de.btegermany.terraplusminusbungeebridge.main.TerraPlusMinusProxyBridge;
import de.btegermany.terraplusminusbungeebridge.util.ProxyType;

import java.util.logging.Logger;

@Plugin(id = "terraplusminusvelocitybridge", name = "TerraPlusMinusVelocityBridge", version = "1.0.0", url = "https://bte-germany.de", authors = "RaptusBube")
public class Velocity {

    private final ProxyServer server;
    private final Logger logger;
    public TerraPlusMinusProxyBridge terraPlusMinusProxyBridge;

    @Inject
    public Velocity(ProxyServer server, Logger logger) {
        this.server = server;
        this.logger = logger;
    }

    @Subscribe
    public void onProxyInitialize(ProxyInitializeEvent event){
        this.terraPlusMinusProxyBridge = new TerraPlusMinusProxyBridge(logger, ProxyType.VELOCITY);
        server.getEventManager().register(this, new VelocityPluginMessageListener(server));
        server.getChannelRegistrar().register(VelocityPluginMessageListener.IDENTIFIER);
        terraPlusMinusProxyBridge.successfullyLoaded();
    }

    public TerraPlusMinusProxyBridge getTerraPlusMinusProxyBridge() {
        return terraPlusMinusProxyBridge;
    }
}


