package at.fhj.itm.testingandroidtodoapp;

import android.content.Context;

import java.util.ArrayList;

/**
 * mock object of FileStorage, for easier testing with espresso we are using product flavors with gradle build
 * @author Michael Ulm
 */
public class FileStorage {

    private static FileStorage instance;

    private FileStorage () {

    }

    public static FileStorage getInstance () {
        if (FileStorage.instance == null) {
            FileStorage.instance = new FileStorage ();
        }
        return FileStorage.instance;
    }

    /**
     * just send some mock objects back for testing
     * @return
     */
    public ArrayList<String> readItems() {

        ArrayList<String> tmpItems = new ArrayList<>();

        tmpItems.add("Prepare Awesome Lesson");
        tmpItems.add("Activate Online Visibility of Moodle");
        tmpItems.add("Talk to students");

        return tmpItems;
    }

    /**
     * only a mock method without function, because not needed at the moment
     * @param items
     */
    public void writeItems(ArrayList<String> items) {
        // we do not write to the file system, it's just a mock method
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
