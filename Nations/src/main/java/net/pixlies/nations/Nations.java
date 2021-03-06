package net.pixlies.nations;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Getter;
import net.pixlies.core.modules.Module;
import net.pixlies.nations.commands.CommandManager;
import net.pixlies.nations.configuration.Config;
import net.pixlies.nations.database.MongoManager;
import net.pixlies.nations.listeners.ListenerManager;
import net.pixlies.nations.handlers.HandlerManager;
import net.pixlies.nations.handlers.RegisterHandlerManager;
import net.pixlies.nations.nations.NationManager;
import net.pixlies.nations.runnables.RunnableManager;

import java.io.File;

@Getter
public class Nations extends Module {

    @Getter private static Nations instance;

    private Config config;

    private HandlerManager handlerManager;
    private ListenerManager listenerManager;
    private CommandManager commandManager;
    private NationManager nationManager;
    private MongoManager mongoManager;
    private RunnableManager runnableManager;

    private final Gson gson = new GsonBuilder()
            .serializeNulls()
            .setPrettyPrinting()
            .create();

    @Override
    public void onLoad() {

        instance = this;

        // CONFIGURATION
        config = new Config(new File(this.getModuleFolder(), "config.yml"), "config.yml");

        // HANDLERS & MANAGERS
        mongoManager = new MongoManager();
        mongoManager.init();
        handlerManager = new HandlerManager();
        nationManager = new NationManager();
        runnableManager = new RunnableManager();
        runnableManager.registerAll();

        new RegisterHandlerManager().registerAllHandlers();

        // LISTENERS
        listenerManager = new ListenerManager();
        listenerManager.registerAllListeners();

        // COMMANDS
        commandManager = new CommandManager();
        commandManager.registerAllCommands();

    }

    @Override
    public void onDrop() {

        // COMMANDS & LISTENERS
        listenerManager.unregisterAllListeners();
        commandManager.unregisterAllCommands();
        nationManager.backupAll();
        runnableManager.unregisterAll();
        instance = null;

    }

}
