package de.btegermany.terraplusminusbungeebridge;

import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.proxy.ProxyServer;
import com.velocitypowered.api.proxy.messages.MinecraftChannelIdentifier;
import de.btegermany.terraplusminusbungeebridge.listener.VelocityPluginMessageListener;
import org.slf4j.Logger;
import sun.print.ProxyPrintGraphics;

@Plugin(id = "terraplusminusvelocitybridge", name = "TerraPlusMinusVelocityBridge", version = "1.0.0", url = "https://bte-germany.de", authors = "RaptusBube")
public class TerraPlusMinusVelocityBridge {

    private final ProxyServer server;
    private final Logger logger;

    @Inject
    public TerraPlusMinusVelocityBridge(ProxyServer server, Logger logger) {
        this.server = server;
        this.logger = logger;
    }

    @Subscribe
    public void onProxyInitialize(ProxyInitializeEvent event){
        server.getEventManager().register(this, new VelocityPluginMessageListener(server));
        server.getChannelRegistrar().register(VelocityPluginMessageListener.IDENTIFIER);

        logger.info("Success");
    }
}


