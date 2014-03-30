package me.cloyd1815.particles.util;

import me.cloyd1815.particles.Particle;

import org.bukkit.Location;
import org.bukkit.scheduler.BukkitRunnable;

import com.sk89q.worldedit.Vector;

public class PlayParticles extends BukkitRunnable {
	@Override
	public void run() {
		for (Particle part : Particle.particles) {
			Vector min = part.getMinVec();
			Vector max = part.getMaxVec();
			for (int x = min.getBlockX(); x <= max.getBlockX(); x++) {
				for (int y = min.getBlockY(); y <= max.getBlockY(); y++) {
					for (int z = min.getBlockZ(); z <= max.getBlockZ(); z++) {
						Location loc = new Location(part.getWorld(), x, y, z, 90f, 90f);
						part.getWorld().playEffect(loc, part.getEffect(), 0);
					}
				}
			}
		}
	}
}
