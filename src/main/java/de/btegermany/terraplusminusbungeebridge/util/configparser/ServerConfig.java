package de.btegermany.terraplusminusbungeebridge.util.configparser;

import java.util.List;

public class ServerConfig {

    public ServerConfig(List<Server> servers){
        this.servers = servers;

    }
    private final List<Server> servers;


    public List<Server> getServers() {
        return servers;
    }

    public class Server {

        public Server(String name, int xOffset, int yOffset, int zOffset, int maxMcBuildHeight, int overlappingPlus, int overlappingMinus, int minLatitudeMC, int maxLatitudeMC, int minLongitudeMC, int maxLongitudeMC, boolean newHighLimit){
            this.name = name;
            this.xOffset = xOffset;
            this.yOffset = yOffset;
            this.zOffset = zOffset;
            this.maxMcBuildHeight = maxMcBuildHeight;
            this.overlappingPlus = overlappingPlus;
            this.overlappingMinus = overlappingMinus;
            this.minLatitudeMC = minLatitudeMC;
            this.maxLatitudeMC = maxLatitudeMC;
            this.minLongitudeMC = minLongitudeMC;
            this.maxLongitudeMC = maxLongitudeMC;
            this.newHighLimit = newHighLimit;
        }

        private String name;

        private final int xOffset;
        private final int yOffset;
        private final int zOffset;
        private final int maxMcBuildHeight;
        private final int overlappingPlus;
        private final int overlappingMinus;

        private final int minLatitudeMC;
        private final int maxLatitudeMC;
        private final int minLongitudeMC;
        private final int maxLongitudeMC;
        private final boolean newHighLimit;


        public String getName() {
            return name;
        }

        public int getXOffset() {
            return xOffset;
        }

        public int getYOffset() {
            return yOffset;
        }

        public int getZOffset() {
            return zOffset;
        }

        public int getMaxMcBuildHeight() {
            return maxMcBuildHeight;
        }

        public int getOverlappingPlus() {
            return overlappingPlus;
        }

        public int getOverlappingMinus() {
            return overlappingMinus;
        }

        public int getMinLatitudeMC() {
            return minLatitudeMC;
        }

        public int getMaxLatitudeMC() {
            return maxLatitudeMC;
        }

        public int getMinLongitudeMC() {
            return minLongitudeMC;
        }

        public int getMaxLongitudeMC() {
            return maxLongitudeMC;
        }

        public boolean isNewHighLimit() {
            return newHighLimit;
        }
    }
}
