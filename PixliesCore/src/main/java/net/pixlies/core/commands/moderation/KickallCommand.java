package net.pixlies.core.commands.moderation;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.*;
import lombok.val;
import net.pixlies.core.Main;
import net.pixlies.core.localization.Lang;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

@CommandAlias("kickall")
@CommandPermission("pixlies.moderation.kickall")
public class KickallCommand extends BaseCommand {

    private static final Main instance = Main.getInstance();

    @Default
    @Description("Kick all players")
    @CommandCompletion("@empty")
    public void onKickAll(CommandSender sender, @Optional String reason) {
        val kickMessage = Objects.requireNonNullElseGet(reason, () -> instance.getConfig().getString("moderation.defaultReason", ""));
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (p != sender && !p.hasPermission("pixlies.moderation.kickall.bypass")) {
                Lang.MODERATION_KICKALL_MESSAGE.kickPlayer(sender, "%PLAYER%;" + sender.getName(), "%REASON%;" + kickMessage);
            }
        }
        Lang.MODERATION_ISSUED_KICKALL.broadcast("%PLAYER%;" + sender.getName(), "%REASON%;" + reason);
    }

}