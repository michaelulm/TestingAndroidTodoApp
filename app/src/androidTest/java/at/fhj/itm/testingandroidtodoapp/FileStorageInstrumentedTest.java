package at.fhj.itm.testingandroidtodoapp;

import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;

import static junit.framework.Assert.assertEquals;

/**
 * tests FileStorage Class from production flavor,
 * because there exists a runnable implementation for read items from and write items to text file
 * @author Michael Ulm
 */
public class FileStorageInstrumentedTest  {

    @Rule
    public ActivityTestRule<MainActivity> main = new ActivityTestRule<MainActivity>(MainActivity.class);

    ArrayList<String> items;

    private static String testPreDefinedString = "Test String";
    private static String testString = "Hello World!";
    private static String testString2 = "Good Night!";
    private static String testFileName = "instrumented-test.txt";

    /**
     * inits list with one value, but it doesn't write to text file on the device
     */
    @Before
    public void setup(){
        items = new ArrayList<>();
        items.add(testPreDefinedString);

        FileStorage.getInstance().setContext(InstrumentationRegistry.getTargetContext());
        FileStorage.getInstance().setListName(testFileName);
    }

    /**
     * check if file is currently empty at application start
     */
    @Test
    public void testReadEmptyList(){
        assertEquals(0, FileStorage.getInstance().readItems().size());
        // assertEquals(2, FileStorage.getInstance().readItems().size()); // will not work, because empty list is 0
    }

    /**
     * use predefined list, write and read it from text file
     */
    @Test
    public void testReadList(){
        FileStorage.getInstance().writeItems(items);
        ArrayList<String> tmpItems = FileStorage.getInstance().readItems();
        assertEquals(1, tmpItems.size());
        assertEquals(testPreDefinedString, tmpItems.get(0));
    }

    /**
     * adds two other strings to the items list and reload from text file
     */
    @Test
    public void testWriteReadList(){
        // add another two strings
        items.add(testString);
        items.add(testString2);
        // write all three strings to the text file
        FileStorage.getInstance().writeItems(items);
        // read from defined file
        ArrayList<String> tmpItems = FileStorage.getInstance().readItems();
        // the list should contain three elements, now we need to check each value
        assertEquals(3, tmpItems.size());
        assertEquals(testPreDefinedString, tmpItems.get(0));
        assertEquals(testString, tmpItems.get(1));
        assertEquals(testString2, tmpItems.get(2));
    }

    /**
     * delete file = good practice, to start another time with a clear szenario on the same device
     */
    @After
    public void teardown(){
        File dir = InstrumentationRegistry.getTargetContext().getFilesDir();
        File file = new File(dir, testFileName);
        boolean deleted = file.delete();
    }
}