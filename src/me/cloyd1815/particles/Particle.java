package me.cloyd1815.particles;

import java.util.ArrayList;

import org.bukkit.Effect;
import org.bukkit.World;

import com.sk89q.worldedit.Vector;

public class Particle {
	private Vector minVec, maxVec;
	private Effect effect;
	private World world;
	private String name;
	public static ArrayList<Particle> particles = new ArrayList<Particle>();
	
	public Particle(String name, Vector minVerc, Vector maxVec, World world) {
		effect = Effect.FIREWORKS_SPARK;
		this.setName(name);
		this.world = world;
		this.setMaxVec(maxVec);
		this.setMinVec(minVerc);
		particles.add(this);
	}

	public Vector getMinVec() {
		return minVec;
	}

	public void setMinVec(Vector minVec) {
		this.minVec = minVec;
	}

	public Vector getMaxVec() {
		return maxVec;
	}

	public void setMaxVec(Vector maxVec) {
		this.maxVec = maxVec;
	}

	public Effect getEffect() {
		return effect;
	}

	public void setEffect(Effect effect) {
		this.effect = effect;
	}

	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
