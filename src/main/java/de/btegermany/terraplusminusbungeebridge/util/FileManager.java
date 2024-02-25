package de.btegermany.terraplusminusbungeebridge.util;

import de.btegermany.terraplusminusbungeebridge.main.TerraPlusMinusProxyBridge;
import de.btegermany.terraplusminusbungeebridge.util.configparser.ServerConfig;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.logging.Logger;

public class FileManager {


    private TerraPlusMinusProxyBridge instance;
    private Logger logger;
    private File configFile;
    private File serverFile;

    private ServerConfig serverConfig;

    public FileManager(TerraPlusMinusProxyBridge instance) throws IOException {
        this.instance = instance;
        this.logger = instance.getLogger();
        loadDefaultFiles();
    }


    public ServerConfig getServerConfig() {
        return serverConfig;
    }

    private void loadDefaultFiles() throws IOException {

        File folder = new File(instance.getJarFolderPath());
        if(!folder.exists()){
            if(!folder.mkdirs()){
                logger.info("Failed to create default Plugin folder - please check your filesystem");
                return;
            }
        }


        configFile = new File(getDataFolder(), "config.yml");
        serverFile = new File(getDataFolder(), "server.yml");

        if (!configFile.exists()) {
            try (InputStream in = getClass().getResourceAsStream("/files/config.yml")) {
                assert in != null;
                Files.copy(in, configFile.toPath());
            } catch (IOException | NullPointerException e) {
                logger.info("SYSTEM: §4Config (config.yml) Fehler");
                throw new IOException();
            }
        }

        if (!serverFile.exists()) {
            try (InputStream in = getClass().getResourceAsStream("/files/server.yml")) {
                assert in != null;
                Files.copy(in, serverFile.toPath());
            } catch (IOException | NullPointerException e) {
                logger.info("SYSTEM: §4Config (server.yml) Fehler");
                throw new IOException();
            }
        }


        try {
            loadFiles();
        } catch (IOException | NullPointerException e) {
            logger.info("SYSTEM: §4Allgemeiner Config Fehler");
            throw new IOException();
        }


    }

    public void loadFiles() throws IOException, NullPointerException {
        if (serverFile.exists()) {
            Yaml yaml = new Yaml(new Constructor(ServerConfig.class, new LoaderOptions()));
            InputStream inputStream = Files.newInputStream(serverFile.toPath());
            serverConfig = yaml.load(inputStream);
            if(serverConfig == null){
                logger.info("Failed to load file server.yml");
            }
        }

    }

    private File getDataFolder(){
        return new File(instance.getJarFolderPath());
    }
}
