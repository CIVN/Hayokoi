package xyz.civn.hayokoi;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Hayokoi extends JavaPlugin implements Listener
{
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if (cmd.getName().equalsIgnoreCase("hayokoi"))
		{
			if (args.length == 1)
			{
				if (args[0].equalsIgnoreCase("help") | args[0].equalsIgnoreCase("?"))
				{
					if (!(sender instanceof Player))
					{
						sender.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "[Hayokoi] " + ChatColor.RESET + ChatColor.RED + "You should run this command in game!");
						return true;
					}

					sender.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "[Hayokoi]");
					sender.sendMessage(ChatColor.GOLD + "/hayokoi: You can call other players to you.");
					sender.sendMessage(ChatColor.GOLD + "/hayokoi help: You can see help.");
					return true;
				}

				else if (args[0].equalsIgnoreCase("info"))
				{
					sender.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "[Hayokoi]");
					sender.sendMessage(ChatColor.GOLD + "Author: CIVN");
					sender.sendMessage(ChatColor.GOLD + "Version: 1.1");
					return true;
				}

				return true;

			}

			else if (args.length == 0)
			{
				if (!(sender instanceof Player))
				{
					sender.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "[Hayokoi] " + ChatColor.RESET + ChatColor.RED + "You should run this command in game!");
					return true;
				}

				Player target = (Player) sender;
				Location targetLocation = target.getLocation();

				for (Player players : Bukkit.getOnlinePlayers())
				{
					if (players.equals (target))
					{
						target.sendMessage(ChatColor.GREEN  + "" + ChatColor.BOLD + "[Hayokoi] " + ChatColor.RESET + ChatColor.GOLD + "You said " + ChatColor.BOLD + "" + ChatColor.RED + "Hayokoi" + ChatColor.GOLD + ".");
						continue;
					}

					players.teleport(targetLocation);
					players.sendMessage(ChatColor.GREEN  + "" + ChatColor.BOLD + "[Hayokoi] " + ChatColor.RESET + ChatColor.GOLD + "You were called by " + ChatColor.RED + target.getName());
				}

				return true;
			}

			sender.sendMessage(ChatColor.GREEN  + "" + ChatColor.BOLD + "[Hayokoi] " + ChatColor.RESET + ChatColor.GOLD + ">>> /hayokoi help");
			return true;

		}

		return true;

	}
}