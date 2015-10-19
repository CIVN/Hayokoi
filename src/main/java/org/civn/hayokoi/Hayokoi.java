package org.civn.hayokoi;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Hayokoi extends JavaPlugin implements Listener
{
	public static void onPlayerTeleport (PlayerTeleportEvent event)
	{
		Player p = event.getPlayer();

		p.getWorld().playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 5, 1);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if (cmd.getName().equalsIgnoreCase("hayokoi"))
		{
			if (!(sender instanceof Player))
			{
				sender.sendMessage(ChatColor.RED + "[Hayokoi] このコマンドはゲーム内から実行してください！");
				return false;
			}

			if (args.length == 1)
			{
				if (args[0].equalsIgnoreCase("help"))
				{
					sender.sendMessage(ChatColor.GOLD + "[Hayokoi]");
					sender.sendMessage(ChatColor.GOLD + "/hayokoi call : You can call other players to you.");
					sender.sendMessage(ChatColor.GOLD + "/hayokoi help : You can see help for this plugin.");
					return true;
				}

				if (args[0].equalsIgnoreCase("call"))
				{
					Player target = (Player) sender;
					Location targetLocation = target.getLocation();

					for (Player players : Bukkit.getOnlinePlayers())
					{
						if (players.equals (target))
						{
							target.sendMessage(ChatColor.GOLD + "[Hayokoi] You said " + ChatColor.GOLD + ChatColor.ITALIC + "" + "\"HAYOKOI\"" + ChatColor.GOLD + ".");
							continue;
						}

						players.teleport(targetLocation);
						players.sendMessage(ChatColor.GOLD + "[Hayokoi] You were called by" + ChatColor.RED + target.getName());
					}

					return true;

				}
			}

			else
			{
				sender.sendMessage(ChatColor.GOLD + "[Hayokoi]");
				sender.sendMessage(ChatColor.GOLD + "Author : CIVN");
				sender.sendMessage(ChatColor.GOLD + "Version : 1.0");
				return true;
			}
		}

		return false;

	}
}