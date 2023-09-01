package pl.endercoders.enderbans.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import pl.endercoders.enderbans.BannedPlayer;
import pl.endercoders.enderbans.helpers.ChatHelper;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class BanCommand implements CommandExecutor {

    private final JavaPlugin plugin;
    private final FileConfiguration config; // uzyj teraz config do pobierania wartosci z configu

    public BanCommand(JavaPlugin plugin, FileConfiguration config) { // Konstruktor z którego pobierzemy takie informacje jak plugin oraz config
        this.plugin = plugin;
        this.config = config;
        plugin.getCommand("ban").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("Ta komenda wymaga bycia graczem!");
            return false;
        }

        Player player = (Player) sender;

        // Jezeli chcesz, aby komenda miała permisje to zostaw to i ustaw pod siebie w przeciwnym razie to usuń ten warunek.

        if(!player.hasPermission("testpermisja")) {
            player.sendMessage(ChatHelper.colored("&cNie posiadasz wymaganych permisji!"));
            return false;
        }

        // Kod
        Player target = Bukkit.getPlayer(args[0]);
        String reason = args.length > 1 ? String.join(" ", args).substring(args[0].length() + 1) : "Nie podano powodu.";
        target.kickPlayer(reason);
        new BannedPlayer(target.getName(), reason, LocalDateTime.now().toEpochSecond(ZoneOffset.UTC), sender.getName());

        return true;
    }
}
