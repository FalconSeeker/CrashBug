package me.falconseeker.crashbug;

import java.util.ArrayList;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CrashCommand implements CommandExecutor {
	
	private CrashAPI api;
	private CrashBug main;
	
	public CrashCommand(CrashBug api) {
		this.api = api.getCrashAPI();
		this.main = api;
		
		api.getCommand("crash").setExecutor(this);
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (!(sender instanceof Player)) return false;
		
		Player p = (Player) sender;
		
		if (!p.hasPermission("falconseeker.crashclient")) {
			p.sendMessage("no permission");
			return true;
		}
		
		if (cmd.getName().equalsIgnoreCase("crash")) {
		if (args.length == 1) {
			String victimName = args[0];
			
			ArrayList<Player> possibleMatches = new ArrayList<Player>();
			for (Player pl : main.getServer().getOnlinePlayers()) {
				if (pl.getName().contains(victimName)) {
					possibleMatches.add(pl);
				}
			}
			
			if (possibleMatches.size() != 1) {
				sender.sendMessage("I found more than one player with a similar name.");
				return true;
			}
			else {
				api.crashPlayer(possibleMatches.get(0));
				return true;
				}
			}
		}
		
		sender.sendMessage("You must specify a player name!");
		return false;
	}
}
