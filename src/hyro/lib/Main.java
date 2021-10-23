package hyro.lib;

import com.xxmicloxx.NoteBlockAPI.NoteBlockAPI;
import com.xxmicloxx.NoteBlockAPI.model.Playlist;
import com.xxmicloxx.NoteBlockAPI.model.RepeatMode;
import com.xxmicloxx.NoteBlockAPI.model.Song;
import com.xxmicloxx.NoteBlockAPI.songplayer.RadioSongPlayer;
import com.xxmicloxx.NoteBlockAPI.utils.NBSDecoder;
import hyro.lib.listeners.Global;
import hyro.lib.listeners.Halloween;
import hyro.lib.task.Snow;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class Main extends JavaPlugin {
    public static File file;
    public static Plugin instance;
    public static FileConfiguration fileConfig;
    public static RadioSongPlayer musicPlayer;

    @Override
    public void onEnable() {
        instance = this;

        File f = new File(getDataFolder().getPath() + "/music");
        if (!f.exists()) {
            saveResource("music/0.nbs", true);
            saveResource("music/1.nbs", true);
            saveResource("music/2.nbs", true);
            saveResource("music/3.nbs", true);
            saveResource("music/4.nbs", true);
            saveResource("music/5.nbs", true);
        }

        saveDefaultConfig();
        createConfig();

        loadType();

        getLogger().info("§cLobbyEffects by Hyro has been §aenabled §8(§c0.1.0§8)");
        getServer().getPluginManager().registerEvents(new Global(), this);
    }

    @Override
    public void onDisable() {
        getLogger().info("§cNexusBlock by Hyro has been disabled §8(§c0.1.0§8)");
    }

    private void createConfig() {
        this.file = new File(getDataFolder(), "config.yml");
        if (!this.file.exists()) {
            this.file.getParentFile().mkdirs();
            saveResource("config.yml", false);
        }

        this.fileConfig = (FileConfiguration)new YamlConfiguration();
        try {
            this.fileConfig.load(this.file);
        } catch (IOException | org.bukkit.configuration.InvalidConfigurationException e) {}
    }

    private void loadType() {
        String type = fileConfig.getString("selected-type");

        if(type.equalsIgnoreCase("christmas")) {
            Long ticks = (fileConfig.getLong("types.christmas.every")) * 20L;
            getServer().getScheduler().runTaskTimerAsynchronously((Plugin)this, new Snow(), ticks, ticks);

            if(fileConfig.getBoolean("types.christmas.music-play")) {
                getServer().getPluginManager().registerEvents(new hyro.lib.listeners.Snow(), this);
                Playlist playlist = new Playlist(NBSDecoder.parse(new File(getDataFolder().getPath()+"/music/1.nbs")));
                for(int i = 1; i <= 5; i++) {
                    playlist.add(NBSDecoder.parse(new File(getDataFolder().getPath()+"/music/"+i+".nbs")));
                    continue;
                }

                this.musicPlayer = new RadioSongPlayer(playlist);
                for(Player p : Bukkit.getOnlinePlayers()) {
                    this.musicPlayer.addPlayer(p.getUniqueId());
                }

                this.musicPlayer.setPlaying(true);
                this.musicPlayer.setRepeatMode(RepeatMode.ALL);
            }
        }

        if(type.equalsIgnoreCase("halloween")) {
            getServer().getPluginManager().registerEvents(new Halloween(), this);
        }
    }
}
