
package me.zeus.VillagerBank.Data;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;



public class Data
{
    
    
    @SuppressWarnings({ "unchecked" })
    public static HashMap<String, Integer> load(File f)
    {
        try
        {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
            HashMap<String, Integer> h = (HashMap<String, Integer>) ois.readObject();
            ois.close();
            return h;
        }
        catch (IOException | ClassNotFoundException ioe)
        {
            ioe.printStackTrace();
        }
        return null;
    }
}
