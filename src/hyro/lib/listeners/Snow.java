package hyro.lib.listeners;

import hyro.lib.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Snow implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        if(Main.fileConfig.getBoolean("types.christmas.music-play")) Main.musicPlayer.addPlayer(event.getPlayer().getUniqueId());
    }
}
