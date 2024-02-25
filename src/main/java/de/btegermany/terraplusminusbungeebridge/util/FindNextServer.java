package de.btegermany.terraplusminusbungeebridge.util;

import de.btegermany.terraplusminusbungeebridge.main.TerraPlusMinusProxyBridge;
import de.btegermany.terraplusminusbungeebridge.util.configparser.ServerConfig;

import java.util.List;
import java.util.Optional;

public class FindNextServer {
    public static Optional<String> getNextServer(TerraPlusMinusProxyBridge instance, Location location){
        List<ServerConfig.Server> servers = instance.getFileManager().getServerConfig().getServers();
        final int x = location.getX();
        final int y = location.getY();
        final int z = location.getZ();
        for(ServerConfig.Server server: servers){
            final int minLatitude = server.getMinLatitudeMC();
            final int maxLatitude = server.getMaxLatitudeMC();
            final int minLongitude = server.getMinLongitudeMC();
            final int maxLongitude = server.getMaxLongitudeMC();
            if(minLatitude != 0 && maxLatitude != 0 && minLongitude != 0 && maxLongitude != 0){
                if(!(minLatitude <= x && maxLatitude >= x && minLatitude <= z && maxLongitude >= z)) continue;
            }
            final int xOffset = server.getXOffset();
            final int yOffset = server.getYOffset();
            final int zOffset = server.getZOffset();
            final int overlappingMinus = server.getOverlappingMinus();
            final int overlappingPlus = server.getOverlappingPlus();
            final int maxMCBuildHeight = server.getMaxMcBuildHeight();
            if(yOffset+overlappingMinus <= y && Math.abs(yOffset)+maxMCBuildHeight-overlappingPlus >= y){
                return Optional.of(server.getName());
            }
        }


        return Optional.empty();
    }
}
