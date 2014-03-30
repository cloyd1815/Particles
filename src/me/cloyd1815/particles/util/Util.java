package me.cloyd1815.particles.util;

import me.cloyd1815.particles.Particle;
import me.cloyd1815.particles.ParticlesMain;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;

import com.sk89q.worldedit.Vector;

public class Util {

	public static void loadConfig() {
		FileConfiguration fc = ConfigurationAPI.getConfig(
				ParticlesMain.getPlugin(), "particles.yml");
		if (fc.getConfigurationSection("particles") != null) {
			for (String keys : fc.getConfigurationSection("particles").getKeys(
					false)) {
				String path = "particles." + keys;
				World world = Bukkit.getWorld(fc.getString(path + ".world"));
				int maxVectorX = fc.getInt(path + ".maxVectorX");
				int maxVectorY = fc.getInt(path + ".maxVectorY");
				int maxVectorZ = fc.getInt(path + ".maxVectorZ");

				int minVectorX = fc.getInt(path + ".minVectorX");
				int minVectorY = fc.getInt(path + ".minVectorY");
				int minVectorZ = fc.getInt(path + ".minVectorZ");

				Vector minV = new Vector(minVectorX, minVectorY, minVectorZ);
				Vector maxV = new Vector(maxVectorX, maxVectorY, maxVectorZ);
				new Particle(keys, minV, maxV, world);
			}
		}
		new PlayParticles().runTaskTimer(ParticlesMain.getPlugin(), 0, 5);
	}
	
	public static void removeParticle(String name) {
		FileConfiguration fc = ConfigurationAPI.getConfig(
				ParticlesMain.getPlugin(), "particles.yml");
		fc.set("particles." + name, null);
		ConfigurationAPI.saveConfig(ParticlesMain.getPlugin(), "particles.yml");
		Particle.particles.remove(name);
	}
}
