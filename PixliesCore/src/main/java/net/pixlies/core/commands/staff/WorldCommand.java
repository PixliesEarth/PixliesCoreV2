package net.pixlies.core.commands.staff;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.*;
import net.pixlies.core.localization.Lang;
import net.pixlies.core.utils.TextUtils;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Command to teleport between worlds.
 * @author dynmie
 */
@CommandAlias("world")
@CommandPermission("pixlies.staff.world")
public class WorldCommand extends BaseCommand {

    @Default
    @Private
    @CommandCompletion("@worlds")
    @Syntax("<world>")
    public void onWorld(Player player, World world) {
        Location location = world.getSpawnLocation();
        player.teleport(location);
        Lang.STAFF_TELEPORT_SELF_TO_TARGET.send(player, "%TARGET%;" + TextUtils.getLocationFormatted(location));
    }

    @CommandPermission("pixlies.staff.world.others")
    @CommandCompletion("@players @worlds")
    public void onWorld(CommandSender sender, Player player, World world) {
        Location location = world.getSpawnLocation();
        player.teleport(location);
        Lang.STAFF_TELEPORT_PLAYER_TO_TARGET.send(sender, "%PLAYER%;" + player.getName(),
                "%TARGET%;" + TextUtils.getLocationFormatted(location));
    }

    @HelpCommand
    public void onHelp(CommandHelp help) {
        help.showHelp();
    }

}
