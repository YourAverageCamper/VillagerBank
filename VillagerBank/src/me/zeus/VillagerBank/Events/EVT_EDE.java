
package me.zeus.VillagerBank.Events;


import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;



public class EVT_EDE implements Listener
{
    
    
    @EventHandler
    public void onDamage(EntityDamageEvent e)
    {
        if (!(e instanceof Villager))
            return;
        e.setCancelled(true);
    }
}
