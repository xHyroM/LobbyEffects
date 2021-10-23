package hyro.lib.listeners;

import hyro.lib.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

public class Global implements Listener {
    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if(Main.fileConfig.getBoolean("global.disable-damage")) event.setCancelled(true);
    }

    @EventHandler
    public void onFood(FoodLevelChangeEvent event) {
        if(event.getEntity() instanceof Player) {
            if(Main.fileConfig.getBoolean("global.disable-hunger")) {
                event.setCancelled(true);
                ((Player) event.getEntity()).getPlayer().setFoodLevel(20);
            }
        }
    }

    @EventHandler
    public void onWeather(WeatherChangeEvent event) {
        if(Main.fileConfig.getBoolean("global.disable-weather-change")) event.setCancelled(true);
    }
}
