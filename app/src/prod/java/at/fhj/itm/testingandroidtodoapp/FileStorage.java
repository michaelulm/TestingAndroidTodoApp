package at.fhj.itm.testingandroidtodoapp;

import android.content.Context;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * reads and write to text file to persist todo list
 * @author Michael Ulm
 */
public class FileStorage {

    private Context context;
    private static FileStorage instance;
    private String listName = "todo.txt";
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
     * defines the list name for easier usage of multiple todo list
     * and also easier unit testing
     * @param listName
     */
    public void setListName(String listName){
        this.listName = listName;
    }

    /**
     * read items from text file
     * @return
     */
    public ArrayList<String> readItems() {
        File todoFile = new File(filesDir, listName);

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
        File todoFile = new File(filesDir, listName);
        try {
            FileUtils.writeLines(todoFile, items);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
