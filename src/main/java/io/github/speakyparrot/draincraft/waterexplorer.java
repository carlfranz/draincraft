package io.github.speakyparrot.draincraft;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

public class waterexplorer {
	
	List<Location> V = new ArrayList<Location>();
	Location start_point;
	List<Location> Z = new ArrayList<Location>();
	
	public boolean linear = false;
	
	
	
	public waterexplorer(Location sp)
	{
		this.start_point = sp;
	}
	
	public List<Location> FindBlocks()
	{
		
		Z.add(start_point);
		
		Date start_time = new Date();
		while(!Z.isEmpty())
		{
			//pesca ultimo nodo
			Location r = pescaUltimo(Z);
			
			List<Location> K;
			K = esploraVicinato(r);
			
			for (Location k : K) {
				if(interessante(k))
				{
					if((!isIn(k,V)) && (!isIn(k,Z)))
					{
						V.add(k);
						Z.add(k);
					}
				}
			}
			int MAX_TIME = 3000;
			if((new Date().getTime() - start_time.getTime()) > MAX_TIME)
			{
				System.out.println("MAX TIME REACHED ("+MAX_TIME+"). Stopping.");
				System.out.println(String.format("v_size: %d z_size: %d",V.size(),Z.size()));
				return null;
			}
			int MAX_NODES = 1200;
			if (V.size() > MAX_NODES) {
				System.out.println("MAX NODES REACHED ("+MAX_NODES+"). Stopping");
				return null;
			}
				
		}
		return V;
	}


	private boolean isIn(Location k, List<Location> Z) {
		for (Location z : Z) {
			if(z.getBlockX() == k.getBlockX()) {
				if (z.getBlockY() == k.getBlockY()) {
					if (z.getBlockZ() == k.getBlockZ()) {
						return true;
					}
				}
			}
		}
		return false;
	}

	private boolean interessante(Location k) {
		Block b = k.getBlock();
		//System.out.println("Block: "+k+" is :"+b.getType()); //DEBUG
		
		if(b.getType() == Material.STATIONARY_WATER)
		{
			//System.out.println("Block: "+k+" is :"+b.getType()); //DEBUG
			return true;
		}
		return false;
	}


	private List<Location> esploraVicinato(Location r) {
		
		List<Location> sixNBH = new ArrayList<Location>();
		if(!linear) {
			//sopra y+1
			sixNBH.add(new Location(r.getWorld(), r.getX(), r.getY()+1, r.getZ()));
			//sotto y-1
			sixNBH.add(new Location(r.getWorld(), r.getX(), r.getY()-1, r.getZ()));
		}
		//laterale dx x+1
		sixNBH.add(new Location(r.getWorld(), r.getX()+1, r.getY(), r.getZ()));
		//laterale sx x-1
		sixNBH.add(new Location(r.getWorld(), r.getX()-1, r.getY(), r.getZ()));
		//front z+1
		sixNBH.add(new Location(r.getWorld(), r.getX(), r.getY(), r.getZ()+1));
		//back z-1
		sixNBH.add(new Location(r.getWorld(), r.getX(), r.getY(), r.getZ()-1));
		return sixNBH;
	}


	private Location pescaUltimo(List<Location> z2) {
		Location tbr;
		tbr = z2.get(z2.size()-1);
		z2.remove(z2.size()-1);
		return tbr;
	}

}
