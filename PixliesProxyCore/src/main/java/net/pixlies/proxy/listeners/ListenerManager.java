package net.pixlies.proxy.listeners;

import com.google.common.collect.ImmutableList;
import net.md_5.bungee.api.plugin.Listener;
import net.pixlies.proxy.Proxy;
import net.pixlies.proxy.listeners.impl.MaintenanceListener;
import net.pixlies.proxy.listeners.impl.ServerKickListener;
import net.pixlies.proxy.listeners.impl.ServerListListener;

public class ListenerManager {

    private final ImmutableList<Listener> listeners = ImmutableList.of(
            new ServerListListener(),
            new MaintenanceListener(),
            new ServerKickListener()
    );

    public void registerAll() {
        listeners.forEach(listener -> Proxy.getInstance().getProxy().getPluginManager().registerListener(Proxy.getInstance(), listener));
    }

}
