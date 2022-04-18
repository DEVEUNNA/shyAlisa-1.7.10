//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\korol\Desktop\Minecraft-Deobfuscator3000-master\1.7.10 stable mappings"!

//Decompiled by Procyon!

package me.asgmax.shyAlisa;

import net.minecraft.util.org.apache.commons.io.*;
import java.net.*;
import org.bukkit.*;
import java.io.*;
import org.bukkit.craftbukkit.libs.com.google.gson.*;
import java.util.*;

public class mf
{
    public static ArrayList<String> readProjectFileLines(final String name) {
        final ArrayList<String> result = new ArrayList<String>();
        final File f1 = new File(shyAlisa.getInstance().getDataFolder(), name);
        if (f1.exists()) {
            return readFileFromDataFolderToArray(name);
        }
        BufferedReader reader = null;
        try {
            final InputStream in = shyAlisa.getInstance().getClass().getResourceAsStream("/" + name);
            reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.isEmpty()) {
                    result.add(line);
                }
            }
        }
        catch (Exception e) {
            shyAlisa.getInstance().log("error reading project file");
            e.printStackTrace();
            return result;
        }
        finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            }
            catch (IOException e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }
    
    public static String readFileFromJarToString(final String name) throws Exception {
        final StringBuilder result = new StringBuilder();
        BufferedReader reader = null;
        try {
            final InputStream in = shyAlisa.getInstance().getClass().getResourceAsStream("/" + name);
            reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.isEmpty()) {
                    result.append(line);
                }
            }
        }
        finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result.toString();
    }
    
    public static String readFile(final String filename) throws Exception {
        final File f1 = new File(shyAlisa.getInstance().getDataFolder(), filename);
        if (f1.exists()) {
            return readFileFromDataFolderToString(filename);
        }
        return readFileFromJarToString(filename);
    }
    
    public static boolean removeFile(final String filename) {
        final File f1 = new File(shyAlisa.getInstance().getDataFolder(), filename);
        return f1.exists() && f1.delete();
    }
    
    public static boolean downloadFileToPluginConfigFolder(final String urlString, final String filename) {
        try {
            final URL url = new URL(urlString);
            final URLConnection conn = url.openConnection();
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:31.0) Gecko/20100101 Firefox/31.0");
            conn.connect();
            final File f1 = new File(shyAlisa.getInstance().getDataFolder(), filename);
            FileUtils.copyInputStreamToFile(conn.getInputStream(), f1);
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    public static boolean downloadFileToUpdateFolder(final String urlString, final String filename) {
        try {
            final URL url = new URL(urlString);
            final URLConnection conn = url.openConnection();
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:31.0) Gecko/20100101 Firefox/31.0");
            conn.connect();
            final File f1 = new File(Bukkit.getServer().getUpdateFolderFile(), filename);
            FileUtils.copyInputStreamToFile(conn.getInputStream(), f1);
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    public static String readFileFromDataFolderToString(final String filename) {
        final File f1 = new File(shyAlisa.getInstance().getDataFolder(), filename);
        String result = null;
        try {
            result = FileUtils.readFileToString(f1, "UTF-8");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public static boolean fileExistsInDataFolder(final String filename) {
        final File f1 = new File(shyAlisa.getInstance().getDataFolder(), filename);
        return f1.exists();
    }
    
    public static ArrayList<String> readFileFromDataFolderToArray(final String filename) {
        final ArrayList<String> result = new ArrayList<String>();
        BufferedReader reader = null;
        try {
            final InputStream in = new FileInputStream(new File(shyAlisa.getInstance().getDataFolder(), filename));
            reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.isEmpty()) {
                    result.add(line);
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        finally {
            if (reader != null) {
                try {
                    reader.close();
                }
                catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
        }
        return result;
    }
    
    public static boolean arrayListContainsIgnoreCase(final ArrayList<String> arr, final String s) {
        for (final String str : arr) {
            if (str.equalsIgnoreCase(s)) {
                return true;
            }
        }
        return false;
    }
    
    public static String readUrlToString(final String link) {
        final StringBuilder result = new StringBuilder();
        try {
            final URL url = new URL(link);
            final URLConnection urlConnection = url.openConnection();
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                result.append(line).append("\n");
            }
            bufferedReader.close();
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return result.toString();
    }
    
    public static int versionCompare(final String str1, final String str2) {
        String[] vals1;
        String[] vals2;
        int i;
        for (vals1 = str1.split("\\."), vals2 = str2.split("\\."), i = 0; i < vals1.length && i < vals2.length && vals1[i].equals(vals2[i]); ++i) {}
        if (i < vals1.length && i < vals2.length) {
            final int diff = Integer.valueOf(vals1[i]).compareTo(Integer.valueOf(vals2[i]));
            return Integer.signum(diff);
        }
        return Integer.signum(vals1.length - vals2.length);
    }
    
    public static Update getLatestPluginUpdateObject() {
        Update update;
        try {
            final String rawjson = readUrlToString(shyAlisa.getInstance().alisa.utilCommandsHandler.updateInfoURL);
            final Gson gson = new Gson();
            update = (Update)gson.fromJson(rawjson, (Class)Update.class);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return update;
    }
    
    public static int getPlayerPlaytime(final String name) {
        return shyAlisa.getInstance().getPlaytime().getDataManager().getDataHandler().getValue("playtime", name);
    }
    
    public static int getDayOfWeek() {
        final Date date = new Date(System.currentTimeMillis());
        final Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(7);
    }
}
