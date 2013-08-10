
package me.zeus.VillagerBank;


import me.zeus.VillagerBank.Resources.IconMenu;
import me.zeus.VillagerBank.Resources.IconMenu.OptionClickEvent;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;



public class Bank
{
    
    
    public static void open(final Player p)
    {
        //!f
        IconMenu menu = new IconMenu("§2Bank", 9, new IconMenu.OptionClickEventHandler()
        {
            @Override
            public void onOptionClick(OptionClickEvent event)
            {
                transact(event.getPosition(), p);
                event.setWillDestroy(true);
                return;
            }
        }, VillagerBank.getInstance())
        .setOption(0, new ItemStack(Material.IRON_INGOT), "§8[§2ExodusBank§8] §aWithdraw $1")
        .setOption(1, new ItemStack(Material.IRON_INGOT), "§8[§2ExodusBank§8] §aWithdraw $10")
        .setOption(2, new ItemStack(Material.IRON_INGOT), "§8[§2ExodusBank§8] §aWithdraw $100")
        .setOption(3, new ItemStack(Material.IRON_INGOT), "§8[§2ExodusBank§8] §aWithdraw $1000")
        .setOption(4, new ItemStack(Material.DIAMOND), "§8[§2ExodusBank§8] §aBalance")
        .setOption(5, new ItemStack(Material.GOLD_INGOT), "§8[§2ExodusBank§8] §aDeposit $1")
        .setOption(6, new ItemStack(Material.GOLD_INGOT), "§8[§2ExodusBank§8] §aDeposit $10")
        .setOption(7, new ItemStack(Material.GOLD_INGOT), "§8[§2ExodusBank§8] §aDeposit $100")
        .setOption(8, new ItemStack(Material.GOLD_INGOT), "§8[§2ExodusBank§8] §aDeposit $1000");
        //f
        menu.open(p);
    }
    
    
    
    public static void transact(int pos, Player p)
    {
        if (pos == 0 || pos == 1 || pos == 2 || pos == 3 || pos == 4)
            if (!VillagerBank.getInstance().getMoney().containsKey(p.getName()))
            {
                p.sendMessage("§8[§2ExodusBank§8] §cYour bank account has no money.");
                return;
            }
        
        switch (pos)
        {
            case 0:
                withdraw(p, 1);
                break;
            case 1:
                withdraw(p, 10);
                break;
            case 2:
                withdraw(p, 100);
                break;
            case 3:
                withdraw(p, 1000);
                break;
            case 4:
                p.sendMessage("§8[§2ExodusBank§8] §aYou have: $§e" + VillagerBank.getInstance().getMoney().get(p.getName()));
                break;
            case 5:
                deposit(p, 1);
                break;
            case 6:
                deposit(p, 10);
                break;
            case 7:
                deposit(p, 100);
                break;
            case 8:
                deposit(p, 1000);
                break;
        }
    }
    
    
    
    private static void withdraw(Player p, int amt)
    {
        if (VillagerBank.getInstance().getMoney().get(p.getName()) >= amt)
        {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "eco give " + p.getName() + " " + amt);
            VillagerBank.getInstance().getMoney().put(p.getName(), VillagerBank.getInstance().getMoney().get(p.getName()) - amt);
        }
        else
        {
            p.sendMessage("§8[§2ExodusBank§8] §cYou do not have that much in the bank!");
            return;
        }
    }
    
    
    
    private static void deposit(Player p, int amt)
    {
        if (VillagerBank.getInstance().getEconomy().getBalance(p.getName()) >= amt)
        {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "eco take " + p.getName() + " " + amt);
            if (!VillagerBank.getInstance().getMoney().containsKey(p.getName()))
            {
                VillagerBank.getInstance().getMoney().put(p.getName(), amt);
            }
            else
            {
                VillagerBank.getInstance().getMoney().put(p.getName(), VillagerBank.getInstance().getMoney().get(p.getName()) + amt);
            }
        }
        else
        {
            p.sendMessage("§8[§2ExodusBank§8] §cYou do not have that much in your wallet!");
            return;
        }
    }
    
}
