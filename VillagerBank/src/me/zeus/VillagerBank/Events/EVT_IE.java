
package me.zeus.VillagerBank.Events;


import me.zeus.VillagerBank.Bank;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;



public class EVT_IE implements Listener
{
    
    
    @EventHandler
    public void onClick(PlayerInteractEntityEvent e)
    {
        Player p = e.getPlayer();
        Entity clicked = e.getRightClicked();
        
        if (!(clicked instanceof Villager))
            return;
        
        Villager v = (Villager) clicked;
        if (!v.getCustomName().contains("Bank"))
            return;
        
        e.setCancelled(true);
        
        if (!p.isSneaking())
        {
            Bank.open(p);
        }
        else
        {
            clicked.remove();
        }
    }
}
