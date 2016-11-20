package at.fhj.itm.testingandroidtodoapp;

import android.content.Context;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * reads and write to text file to persist todo list
 */
public class FileStorage {

    private Context context;
    private static FileStorage instance;
    File filesDir;

    private FileStorage () {
    }

    public static FileStorage getInstance () {
        if (FileStorage.instance == null) {
            FileStorage.instance = new FileStorage ();
        }
        return FileStorage.instance;
    }

    /**
     * set Application's Context to Non-Activity Class
     * @param c
     */
    public void setContext(Context c){
        this.context = c;
        filesDir = context.getFilesDir();
    }

    /**
     * read items from text file
     * @return
     */
    public ArrayList<String> readItems() {
        File todoFile = new File(filesDir, "todo.txt");

        ArrayList<String> tmpItems = null;
        try {
            tmpItems = new ArrayList<String>(FileUtils.readLines(todoFile));
        } catch (IOException e) {
            tmpItems = new ArrayList<>();
        }
        return tmpItems;
    }

    /**
     * write items to text file
     * @param items
     */
    public void writeItems(ArrayList<String> items) {
        File todoFile = new File(filesDir, "todo.txt");
        try {
            FileUtils.writeLines(todoFile, items);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
