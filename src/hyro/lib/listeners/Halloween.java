package hyro.lib.listeners;

import hyro.lib.Main;
import org.bukkit.Effect;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;

public class Halloween implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        List<String> effects = Main.fileConfig.getStringList("types.halloween.effects");
        effects.forEach((effect) -> {
            PotionEffectType ef = PotionEffectType.getByName(effect);
            event.getPlayer().addPotionEffect(new PotionEffect((ef), 999999, 0, false, false), true);
        });
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        event.getPlayer().getActivePotionEffects().forEach((potion) -> {
            event.getPlayer().removePotionEffect(potion.getType());
        });
    }
}
