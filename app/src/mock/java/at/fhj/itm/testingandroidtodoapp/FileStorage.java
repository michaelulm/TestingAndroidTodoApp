package at.fhj.itm.testingandroidtodoapp;

import android.content.Context;

import java.util.ArrayList;

/**
 * mock object of FileStorage, for easier testing with espresso we are using product flavors with gradle build
 * @author Michael Ulm
 */
public class FileStorage {

    private static FileStorage instance;
    private ArrayList<String> tmpItems = new ArrayList<>();

    private FileStorage () {

    }

    public static FileStorage getInstance () {
        if (FileStorage.instance == null) {
            FileStorage.instance = new FileStorage ();
        }
        return FileStorage.instance;
    }

    /**
     * returns temporary stored items from memory
     * @return
     */
    public ArrayList<String> readItems() {
        return tmpItems;
    }

    /**
     * temporary store items in memory
     * @param items
     */
    public void writeItems(ArrayList<String> items) {
        tmpItems = new ArrayList<>();
        for(String item : items){
            tmpItems.add(item);
        }
    }

    /**
     * only a mock method without function, because not needed at the moment
     * @param c
     */
    public void setContext(Context c){
    }

    /**
     * only a mock method without function, because not needed at the moment
     * @param listName
     */
    public void setListName(String listName){
    }
}
