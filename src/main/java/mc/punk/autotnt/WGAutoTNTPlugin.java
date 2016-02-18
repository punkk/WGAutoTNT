package mc.punk.autotnt;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.*;

import com.mewin.WGCustomFlags.WGCustomFlagsPlugin;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.flags.StateFlag;


public class WGAutoTNTPlugin extends JavaPlugin {
	
	private WGCustomFlagsPlugin customPlugin;
	private Plugin wgCustomFlagsPlugin;
	private WorldGuardPlugin wgPlugin;
	private TNTListener listener;
	
	public static StateFlag TNT_FLAG = new StateFlag("auto-tnt", false);

    @Override
    public void onEnable() {  	  	
    	wgCustomFlagsPlugin = getServer().getPluginManager().getPlugin("WGCustomFlags");
    	wgPlugin = (WorldGuardPlugin) getServer().getPluginManager().getPlugin("WorldGuard");
    	
    	if (wgPlugin == null || !(wgPlugin instanceof WorldGuardPlugin) || !wgPlugin.isEnabled()) {
    		getLogger().warning("Could not find or load WorldGuard Plugin");
    		getServer().getPluginManager().disablePlugin(this);
    		return;
    	} else {
    		customPlugin = (WGCustomFlagsPlugin) wgCustomFlagsPlugin;
    	}
    	
    	if (wgCustomFlagsPlugin == null || !(wgCustomFlagsPlugin instanceof WGCustomFlagsPlugin) || !wgCustomFlagsPlugin.isEnabled()) {
    		getLogger().warning("Could not find or load WorldGuard Custom Flags Plugin");
    		getServer().getPluginManager().disablePlugin(this);
    		return;
    	} else {
    		customPlugin = (WGCustomFlagsPlugin) wgCustomFlagsPlugin;
    	}
    	
    	listener = new TNTListener(this, wgPlugin);
    	
    	getServer().getPluginManager().registerEvents(listener, this);
    	
    	customPlugin.addCustomFlag(TNT_FLAG);
    	
    	getLogger().info("AutoTNT Loaded!");	
    }	
}
