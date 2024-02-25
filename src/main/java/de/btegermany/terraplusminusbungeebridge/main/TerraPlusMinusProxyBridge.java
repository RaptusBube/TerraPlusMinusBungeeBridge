package de.btegermany.terraplusminusbungeebridge.main;

import de.btegermany.terraplusminusbungeebridge.util.FileManager;
import de.btegermany.terraplusminusbungeebridge.util.ProxyType;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

public class TerraPlusMinusProxyBridge {
    private final String JAR_PATH = new File(TerraPlusMinusProxyBridge.class.getProtectionDomain().getCodeSource().getLocation().getPath()).getParentFile().getAbsolutePath()+"/";
    private final String JAR_FOLDER_PATH = new File(TerraPlusMinusProxyBridge.class.getProtectionDomain().getCodeSource().getLocation().getPath()).getParentFile().getAbsolutePath()+"/TerraPlusMinusProxyBridge/";

    private final Logger logger;

    private final FileManager fileManager;

    private final TerraPlusMinusProxyBridge instance;

    private boolean successfullyLoad = false;



    public TerraPlusMinusProxyBridge(Logger logger, ProxyType proxyType){
        this.instance = this;
        this.logger = logger;

        logger.info("Detected " + proxyType + "-Proxy");

        try {
            logger.info("Loading files...");
            this.fileManager = new FileManager(this);
            logger.info("Successfully loaded files");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void successfullyLoaded(){
        successfullyLoad = true;
        logger.info("Plugin loaded successfully");
    }


    public boolean isSuccessfullyLoaded() {
        return successfullyLoad;
    }

    public TerraPlusMinusProxyBridge getInstance() {
        return instance;
    }

    public Logger getLogger() {
        return logger;
    }

    public String getJarPath() {
        return JAR_PATH;
    }

    public String getJarFolderPath() {
        return JAR_FOLDER_PATH;
    }

    public FileManager getFileManager() {
        return fileManager;
    }
}
