
package me.zeus.VillagerBank.Commands;


import me.zeus.VillagerBank.VillagerBank;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;



public class CMD_Cash implements CommandExecutor
{
    
    
    @Override
    public boolean onCommand(CommandSender s, Command arg1, String arg2, String[] args)
    {
        if (!s.hasPermission("VillagerBank.Admin"))
            return false;
        
        if (args.length == 1)
            s.sendMessage("§8[§2ExodusBank§8] §eBalance: " + (VillagerBank.getInstance().getMoney().containsKey(args[0]) ? VillagerBank.getInstance().getMoney().get(args[0]) : 0));
        if (args.length == 3)
        {
            if (args[0].equals("set"))
            {
                VillagerBank.getInstance().getMoney().put(args[1], Integer.parseInt(args[2]));
                s.sendMessage("§8[§2ExodusBank§8] §Set " + args[1] + " balance to " + args[2]);
            }
            else if (args[0].equals("add"))
            {
                VillagerBank.getInstance().getMoney().put(args[1], VillagerBank.getInstance().getMoney().get(args[0]) + Integer.parseInt(args[2]));
                s.sendMessage("§8[§2ExodusBank§8] §aAdded " + args[2] + " to " + args[1]);
            }
            else if (args[0].equals("remove"))
            {
                VillagerBank.getInstance().getMoney().put(args[1], VillagerBank.getInstance().getMoney().get(args[0]) - Integer.parseInt(args[2]));
                s.sendMessage("§8[§2ExodusBank§8] §aRemoved " + args[2] + " from " + args[1]);
            }
        }
        
        
        return false;
    }
    
}
