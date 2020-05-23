package org.mamadev.teamsurvival.file;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class TeamData {
    public static HashMap<String, ArrayList<UUID>> teams = new HashMap<String, ArrayList<UUID>>();
    public static String Path = "plugins/TeamSurvival" + File.separator + "teams.dat";

    public static void setup(){
        File file = new File(Path);
        new File("plugins/TeleportationControl").mkdir();
        if(file.exists()){
            teams = load();
        }
        if(teams == null){
            teams = new HashMap<String, ArrayList<UUID>>();
        }
    }

    public static void save(){
        File file = new File(Path);
        new File("plugins/TeleportationControl").mkdir();
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try{
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(Path));
            oos.writeObject(teams);
            oos.flush();
            oos.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static HashMap<String, ArrayList<UUID>> load(){
        try{
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(Path));
            Object result = ois.readObject();
            ois.close();
            return (HashMap<String, ArrayList<UUID>>)result;
        }catch(Exception e){
            return null;
        }
    }
}
