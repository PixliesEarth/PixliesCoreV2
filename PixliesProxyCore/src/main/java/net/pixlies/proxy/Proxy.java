package net.pixlies.proxy;

import lombok.Getter;
import net.md_5.bungee.api.plugin.Plugin;
import net.pixlies.proxy.commands.CommandManager;
import net.pixlies.proxy.config.Config;
import net.pixlies.proxy.database.MongoManager;
import net.pixlies.proxy.handlers.HandlerManager;
import net.pixlies.proxy.handlers.RegisterHandlerManager;
import net.pixlies.proxy.listeners.ListenerManager;
import net.pixlies.proxy.localization.Lang;
import net.pixlies.proxy.runnables.RunnableManager;
import net.pixlies.proxy.utils.FileUtils;

import java.io.File;

@Getter
public class Proxy extends Plugin {

    @Getter private static Proxy instance;

    private MongoManager mongoManager;
    private CommandManager commandManager;
    private ListenerManager listenerManager;
    private HandlerManager handlerManager;

    private Config config;
    private Config settingsConfig;

    @Override
    public void onEnable() {

        // INSTANCE
        instance = this;

        // CONFIGS
        config = new Config(new File(getDataFolder().getAbsolutePath() + "/config.yml"), "config.yml");
        settingsConfig = new Config(new File(getDataFolder().getAbsolutePath() + "/settings.yml"), "settings.yml");

        // LANGUAGE
        FileUtils.saveResource("languages/LANG_ENG.yml", false);
        Lang.init();

        // MANAGERS & HANDLERS
        mongoManager = new MongoManager();
        mongoManager.init();
        handlerManager = new HandlerManager();
        new RegisterHandlerManager().registerAll();
        new RunnableManager().runAll();

        // COMMANDS
        commandManager = new CommandManager();
        commandManager.registerAll();

        // LISTENERS
        listenerManager = new ListenerManager();
        listenerManager.registerAll();

    }

    @Override
    public void onDisable() {
        instance = null;
    }

}
