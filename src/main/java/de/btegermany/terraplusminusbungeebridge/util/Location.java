package de.btegermany.terraplusminusbungeebridge.util;

public class Location {
    private final int x;
    private final int y;
    private final int z;

    public Location(int x, int y, int z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }
}
