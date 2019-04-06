package me.falconseeker.crashbug;

import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;

public class CrashBug extends JavaPlugin implements CommandExecutor {


	private CrashAPI crashAPI;
	
	@Override
	public void onEnable() {
		this.crashAPI = new CrashAPI(this);
		
		new CrashCommand(this);
	}
	
	@Override
	public void onDisable() {

	}

	public CrashAPI getCrashAPI() {
		return crashAPI;
	}
}