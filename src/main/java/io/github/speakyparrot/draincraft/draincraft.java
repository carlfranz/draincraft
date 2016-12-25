package io.github.speakyparrot.draincraft;


/*MVN:
 * compile jar
 */
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import org.bukkit.plugin.java.JavaPlugin;

public final class draincraft extends JavaPlugin implements CommandExecutor 
{	
	@Override
	public void onDisable()
	{
		getLogger().info("onDisable has been invoked!");
	}
	
	@Override
	public void onEnable() {
		getLogger().info("onEnable has been invoked !");
		//getServer().getPluginManager().registerEvents(this, this);
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label,String[] args)
	{
		if (cmd.getName().equalsIgnoreCase("draincraft"))
		{
			if(sender instanceof Player) {
				Player player = (Player) sender;
				
				Location loc = player.getLocation();
				System.out.println("Start Location is: "+loc.toString());
				
				waterexplorer wt = new waterexplorer(loc);
				
				if(args[0] != null)	{
				if (args[0].equals("linear")) {
						wt.linear = true;
						sender.sendMessage("Linear MODE!");
				}}
				
				List<Location> water_blocks = wt.FindBlocks();
				
				if(water_blocks == null)
				{
					sender.sendMessage("Too much water blocks!");
					return false;
				}
				else {
					long blocks = water_blocks.size();
					for (Location wb : water_blocks) {
						Block b = wb.getBlock();
						b.setType(Material.AIR);
					}
					sender.sendMessage("Successfully changed "+blocks+" blocks!");
				}
				//b.setType(Material.IRON_ORE);
				return true;
			} else
			{
				sender.sendMessage("You must be a player!");
				return false;
			}
		}
		return false;
	}
}
