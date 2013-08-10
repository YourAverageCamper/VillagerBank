
package me.zeus.VillagerBank.Commands;


import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;



public class CMD_Bank implements CommandExecutor
{
    
    
    @Override
    public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] args)
    {
        if (!(sender instanceof Player))
            return false;
        if (!sender.hasPermission("ExodusBank.Create"))
        {
            sender.sendMessage("§8[§2ExodusBank§8] §cYou don't have permission to create a bank!");
            return false;
        }
        Player p = (Player) sender;
        Villager v = (Villager) p.getWorld().spawnEntity(p.getTargetBlock(null, 50).getLocation().add(0, 1, 0), EntityType.VILLAGER);
        v.setMaxHealth(Double.MAX_VALUE);
        v.setHealth(v.getMaxHealth());
        v.setCustomName("§2$ Bank $");
        v.setCustomNameVisible(true);
        v.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, Integer.MAX_VALUE, 127));
        p.sendMessage("§8[§2ExodusBank§8] §aCreated a bank!");
        return false;
    }
    
}
