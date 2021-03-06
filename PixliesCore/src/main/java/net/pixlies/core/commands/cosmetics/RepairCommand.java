package net.pixlies.core.commands.cosmetics;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.*;
import lombok.val;
import net.pixlies.core.localization.Lang;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

@CommandAlias("repair")
@CommandPermission("pixlies.cosmetics.repair")
public class RepairCommand extends BaseCommand {

    @Private
    @Default
    @Description("Repairs items")
    public void onRepair(Player player) {
        ItemStack item = player.getInventory().getItemInMainHand();
        if (!(item.getItemMeta() instanceof Damageable meta)) {
            Lang.CANNOT_EDIT_ITEM.send(player);
            return;
        }
        if (meta.getDamage() == 0) {
            Lang.COSMETICS_ALREADY_REPAIRED.send(player);
            return;
        }
        meta.setDamage(0);
        item.setItemMeta(meta);
        Lang.COSMETICS_REPAIR_ITEM.send(player);
    }

    @Subcommand("all|everything|*")
    @Description("Repairs items")
    @CommandPermission("pixlies.cosmetics.repair.all")
    public void onRepairEverything(Player player) {
        val toRepair = new ArrayList<ItemStack>();
        for (val item : player.getInventory()) {
            if (!(item.getItemMeta() instanceof Damageable)) continue;
            toRepair.add(item);
        }
        if (toRepair.isEmpty()) {
            Lang.CANNOT_EDIT_ITEM.send(player);
            return;
        }
        toRepair.forEach(item -> {
            if (item == null) return;
            val meta = (Damageable) item.getItemMeta();
            if (meta == null) return;
            meta.setDamage(0);
            item.setItemMeta(meta);
        });
        Lang.COSMETICS_REPAIR_ITEM.send(player);
    }

    @HelpCommand
    public void onHelp(CommandHelp help) {
        help.showHelp();
    }

}
