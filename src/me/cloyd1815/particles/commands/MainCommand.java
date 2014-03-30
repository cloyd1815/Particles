package me.cloyd1815.particles.commands;

import me.cloyd1815.particles.Particle;
import me.cloyd1815.particles.ParticlesMain;
import me.cloyd1815.particles.util.ConfigurationAPI;
import me.cloyd1815.particles.util.Util;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;

import com.sk89q.worldedit.bukkit.selections.Selection;

public class MainCommand implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command cmd,
			String commandLabel, String[] args) {
		if (commandLabel.equalsIgnoreCase("part")) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				if (player.hasPermission(new Permission("admin"))) {
					if (args.length == 1) {
						if (args[0].equalsIgnoreCase("list")) {
							for (Particle part : Particle.particles) {
								player.sendMessage(part.getName());
							}
							return false;
						}
					}
					if (args.length == 2) {
						if (args[0].equalsIgnoreCase("add")) {
							Selection sel = ParticlesMain.getWorldEdit()
									.getSelection(player);
							if (sel == null) {
								player.sendMessage(ChatColor.DARK_RED
										+ "Make a selection first!");
								return false;
							}
							new Particle(args[1], sel.getNativeMinimumPoint(),
									sel.getNativeMaximumPoint(),
									player.getWorld());
							FileConfiguration fc = ConfigurationAPI.getConfig(
									ParticlesMain.getPlugin(), "particles.yml");
							fc.set("particles." + args[1], null);
							fc.set("particles." + args[1] + ".world", player
									.getWorld().getName());
							fc.set("particles." + args[1] + ".maxVectorX", sel
									.getNativeMaximumPoint().getBlockX());
							fc.set("particles." + args[1] + ".maxVectorY", sel
									.getNativeMaximumPoint().getBlockY());
							fc.set("particles." + args[1] + ".maxVectorZ", sel
									.getNativeMaximumPoint().getBlockZ());

							fc.set("particles." + args[1] + ".minVectorX", sel
									.getNativeMinimumPoint().getBlockX());
							fc.set("particles." + args[1] + ".minVectorY", sel
									.getNativeMinimumPoint().getBlockY());
							fc.set("particles." + args[1] + ".minVectorZ", sel
									.getNativeMinimumPoint().getBlockZ());
							ConfigurationAPI.saveConfig(
									ParticlesMain.getPlugin(), "particles.yml");
							player.sendMessage("Created!");
						} else if (args[0].equalsIgnoreCase("remove")) {
							for (Particle part : Particle.particles) {
								if (part.getName().equalsIgnoreCase(args[1])) {
									Util.removeParticle(part.getName());
									player.sendMessage("Removed " + args[1]);
									return true;
								}
							}
							player.sendMessage(ChatColor.RED + "Particle not found!");
						}
					} else {
						player.sendMessage(ChatColor.DARK_RED
								+ "/part add name");
						player.sendMessage(ChatColor.BLUE
								+ "Plugin designed by _FullMetal14_. Coded by Sockmonkey.");
					}
				} else {
					player.sendMessage(ChatColor.RED + "No permission!");
				}
			}
		}
		return false;
	}
}
