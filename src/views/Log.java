package views;

import java.io.FileWriter;
import java.io.IOException;

public class Log {
    static final String filename = "./logs/Log.txt";

    public static void out(String text){
        out(text,true);
    }

    public static void out(String text,boolean append){
        try (FileWriter writer = new FileWriter(filename,append)) {
            writer.write(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
