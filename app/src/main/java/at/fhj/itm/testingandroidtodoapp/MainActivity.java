package at.fhj.itm.testingandroidtodoapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * MainActivity of ToDo List Testing Sample
 * Author Michael Ulm
 */
public class MainActivity extends Activity {

    private ArrayList<Item> items;
    private ArrayAdapter<Item> itemsAdapter;
    private ListView lvItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // set application context to singleton
        FileStorage.getInstance().setContext(getApplicationContext());

        // now create ListView
        setupListView();

        // now add items from file
        readItems();

        // Setup remove listener method call
        setupListViewListener();
    }

    /**
     * adds new item to ToDo List and removes current text from ToDo List
     * @param v
     */
    public void onAddItem(View v) {
        EditText etNewItem = (EditText) findViewById(R.id.etNewItem);
        String itemText = etNewItem.getText().toString();
        itemsAdapter.add(new Item(itemText));
        etNewItem.setText("");
        writeItems();
    }

    /**
     * defines ListView and setup ArrayAdapter
     */
    private void setupListView(){
        lvItems = (ListView) findViewById(R.id.lvItems);
        items = new ArrayList<>();
        itemsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        lvItems.setAdapter(itemsAdapter);
        // add some Items to ToDo list
        // items.add(new Item("IMS Testing Todo"));
        // ..
    }

    /**
     *     Attaches a long click listener to the listview
     */
    private void setupListViewListener() {
        lvItems.setOnItemClickListener(
                new AdapterView.OnItemClickListener(){

                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int pos, long id) {
                        EditText etNewItem = (EditText) findViewById(R.id.etNewItem);
                        //etNewItem.setText(items.get(pos).toString());
                    }
                }
        );
        lvItems.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapter, View item, int pos, long id) {
                        // Remove the item within array at position
                        //items.remove(pos);
                        Item tmpItem = itemsAdapter.getItem(pos);
                        itemsAdapter.remove(tmpItem);
                        // Refresh the adapter
                        itemsAdapter.notifyDataSetChanged();
                        // Return true consumes the long click event (marks it handled)
                        return true;
                    }

                });
    }

    /**
     * read current Items from FileStorage
     */
    private void readItems() {
        ArrayList<String> tmpItems = FileStorage.getInstance().readItems();
        for (String item : tmpItems) {
            itemsAdapter.add(new Item(item));
        }
    }

    /**
     * write current Items back to the FileStorage
     */
    private void writeItems() {
        ArrayList<String> tmpItems = new ArrayList<>();
        for(int i = 0; i < itemsAdapter.getCount(); i++){
            tmpItems.add(itemsAdapter.getItem(i).toString());
        }
        FileStorage.getInstance().writeItems(tmpItems);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /**
     * Item for ListView, could also contain other data
     */
    public static class Item {
        private final String value;
        public Item(String value) {
            this.value = value;
        }
        public String toString() {
            return value;
        }
    }
}
