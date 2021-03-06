package net.pixlies.proxy.commands.impl.staff;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.*;
import net.md_5.bungee.api.CommandSender;

/**
 * Edit maintenance mode settings.
 * @author Dynmie
 */
@CommandAlias("maintenance")
@CommandPermission("pixlies.maintenance")
public class MaintenanceCommand extends BaseCommand {

    @Subcommand("add")
    @CommandCompletion("@players")
    public void onAdd(CommandSender sender) {
        // TODO
    }

    @Subcommand("remove")
    @CommandCompletion("@players")
    public void onRemove(CommandSender sender) {
        // TODO
    }

    @Subcommand("list")
    @CommandCompletion("@players")
    public void onList(CommandSender sender) {
        // TODO
    }

    @Subcommand("enable")
    @CommandCompletion("@players")
    public void onEnable(CommandSender sender) {
        // TODO
    }

    @Subcommand("disable")
    @CommandCompletion("@players")
    public void onDisable(CommandSender sender) {
        // TODO
    }

    @Default
    @HelpCommand
    public void onHelp(CommandHelp help) {
        help.showHelp();
    }

}
