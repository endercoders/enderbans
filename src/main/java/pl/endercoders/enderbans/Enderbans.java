package pl.endercoders.enderbans;

import org.bukkit.plugin.java.JavaPlugin;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import pl.endercoders.enderbans.commands.BanCommand;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public final class EnderBans extends JavaPlugin {

    @Override
    public void onEnable() {
        try {
            getConfig().options().copyDefaults(true);
            saveDefaultConfig();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Yaml yaml = new Yaml(new Constructor(BannedPlayers.class, new LoaderOptions()));
            BannedPlayers data = yaml.load( new FileInputStream(new File("database.yml")));
            data.players();
            System.out.println(data);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        // Komendy
        new BanCommand(this, getConfig());
    }
}
