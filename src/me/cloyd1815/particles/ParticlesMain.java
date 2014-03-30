package me.cloyd1815.particles;

import me.cloyd1815.particles.commands.MainCommand;
import me.cloyd1815.particles.util.Util;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;

public class ParticlesMain extends JavaPlugin {
	private static ParticlesMain plugin;

	@Override
	public void onEnable() {
		plugin = this;
		getCommand("part").setExecutor(new MainCommand());
		Util.loadConfig();
	}
	
	@Override
	public void onDisable() {
		
	}
	
	public static WorldEditPlugin getWorldEdit() {
	    Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("WorldEdit");
	    
	    if (plugin == null || !(plugin instanceof WorldEditPlugin)) {
	    	Bukkit.getLogger().severe("[Particle] Couldn't find WorldEdit! Error! Plugin will shut down now.");
	    	Bukkit.getPluginManager().disablePlugin(plugin);
	        return null;
	    }
	 
	    return (WorldEditPlugin) plugin;
	}
	
	public static JavaPlugin getPlugin() {
		JavaPlugin plugin = ParticlesMain.plugin;
		return plugin;
	}
}
