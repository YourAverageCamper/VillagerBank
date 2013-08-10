
package me.zeus.VillagerBank;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

import me.zeus.VillagerBank.Commands.CMD_Bank;
import me.zeus.VillagerBank.Commands.CMD_Cash;
import me.zeus.VillagerBank.Data.Data;
import me.zeus.VillagerBank.Events.EVT_IE;
import net.milkbowl.vault.economy.Economy;

import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;



public class VillagerBank extends JavaPlugin
{
    
    
    private static VillagerBank instance;
    Economy economy;
    File rootDir;
    File moneyFile;
    Map<String, Integer> moneys;
    
    
    
    @Override
    public void onEnable()
    {
        // instance
        instance = this;
        
        // root dir
        rootDir = new File(getDataFolder() + "");
        if (!rootDir.exists())
            rootDir.mkdir();
        
        // load moneys
        moneyFile = new File(getDataFolder() + "/money.dat");
        if (!moneyFile.exists())
        {
            moneys = new HashMap<String, Integer>();
            try
            {
                moneyFile.createNewFile();
                try
                {
                    ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(moneyFile));
                    oos.writeObject(moneys);
                    oos.close();
                }
                catch (IOException e)
                {
                    getLogger().info("Error saving money!");
                }
            }
            catch (IOException e)
            {
                getLogger().info("Error!");
            }
        }
        else
        {
            moneys = Data.load(moneyFile);
        }
        
        // setup eco
        setupEconomy();
        
        getServer().getPluginManager().registerEvents(new EVT_IE(), this);
        getCommand("bank").setExecutor(new CMD_Bank());
        getCommand("cash").setExecutor(new CMD_Cash());
    }
    
    
    
    @Override
    public void onDisable()
    {
        instance = null;
        try
        {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(moneyFile));
            oos.writeObject(moneys);
            oos.close();
        }
        catch (IOException e)
        {
            getLogger().info("Error saving money!");
        }
    }
    
    
    
    public Map<String, Integer> getMoney()
    {
        return moneys;
    }
    
    
    
    public static VillagerBank getInstance()
    {
        return instance;
    }
    
    
    
    private boolean setupEconomy()
    {
        RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
        if (economyProvider != null)
            economy = economyProvider.getProvider();
        return (economy != null);
    }
    
    
    
    public Economy getEconomy()
    {
        return economy;
    }
    
}
