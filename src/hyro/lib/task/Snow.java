package hyro.lib.task;

import hyro.lib.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

import java.util.concurrent.ThreadLocalRandom;

public class Snow implements Runnable {
    private final Integer snowCount;
    private final Double snowRange;

    public Snow() {
        this.snowCount = Main.fileConfig.getInt("types.christmas.count");
        this.snowRange = Main.fileConfig.getDouble("types.christmas.range");
    }

    @Override
    public void run() {
        for(Player p : Bukkit.getOnlinePlayers()) {
            for(int i = 0; i < this.snowCount; i++) {
                Location location = p.getLocation().add(
                        ThreadLocalRandom.current().nextDouble(-this.snowRange, this.snowRange),
                        ThreadLocalRandom.current().nextDouble(0.0D, this.snowRange),
                        ThreadLocalRandom.current().nextDouble(-this.snowRange, this.snowRange)
                );
                p.spawnParticle(Particle.FIREWORKS_SPARK, location, 1, 0.0D, 0.0D, 0.0D, 0.0D);
            }
        }
    }
}
