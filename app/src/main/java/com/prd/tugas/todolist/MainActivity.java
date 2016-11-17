package com.prd.tugas.todolist;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> items;
    public static ArrayAdapter<String> itemsAdapter;
    private ListView lvItems;
    RelativeLayout main;
    LinearLayout add, change;
    View button;
    public int pos1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
        );
        add = (LinearLayout) findViewById(R.id.add_xml);
        add.setVisibility(View.GONE);
        change = (LinearLayout) findViewById(R.id.change_xml);
        change.setVisibility(View.GONE);
        main = (RelativeLayout) findViewById(R.id.main_xml);
        main.setVisibility(View.VISIBLE);
        button = findViewById(R.id.fab);
        button.setVisibility(View.VISIBLE);
        lvItems = (ListView) findViewById(R.id.lvItems);
        items = new ArrayList<String>();
        itemsAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, items);
        lvItems.setAdapter(itemsAdapter);
        items.add("Tugas PRD");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setupListViewListener();


    }
    private void setupListViewListener() {
        lvItems.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapter,
                                                   View item, final int pos, long id) {
                        final AlertDialog dialog2 = new AlertDialog.Builder(lvItems.getContext())
                                .setMessage("Apa yang ingin anda lakukan dengan to do '" + itemsAdapter.getItem(pos) + "'?")
                                .setNegativeButton("Hapus", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        items.remove(pos);
                                        itemsAdapter.notifyDataSetChanged();
                                    }
                                })
                                .setPositiveButton("Ubah", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        main.setVisibility(View.GONE);
                                        button.setVisibility(View.GONE);
                                        change.setVisibility(View.VISIBLE);
                                        Toolbar toolbar2 = (Toolbar) findViewById(R.id.toolbar2);
                                        toolbar2.setTitle("Ubah");
                                        TextView text = (TextView) findViewById(R.id.textubah);
                                        text.setText("Ubah to do " + itemsAdapter.getItem(pos));
                                        pos1 = pos;


                                    }
                                })
                                .create();
                        dialog2.show();

                        return true;
                    }

                });
    }

    public void changeLayout(View v) {
        add.setVisibility(View.VISIBLE);
        main.setVisibility(View.GONE);
        button.setVisibility(View.GONE);
        Toolbar toolbar1 = (Toolbar) findViewById(R.id.toolbar1);
        toolbar1.setTitle("Tambah");

    }

    public void onChange(View v) {
        EditText ChangeText = (EditText) findViewById (R.id.change_name);
        String todo = String.valueOf(ChangeText.getText());
        items.remove(pos1);
        itemsAdapter.insert(todo, pos1);
        change.setVisibility(View.GONE);
        main.setVisibility(View.VISIBLE);
        button.setVisibility(View.VISIBLE);
        ChangeText.setText("");
    }

    public void onAddItem(View v) {
        final EditText taskEditText = (EditText) findViewById (R.id.activity_name);
        String task = String.valueOf(taskEditText.getText());
        itemsAdapter.add(task);
        taskEditText.setText("");
        add.setVisibility(View.GONE);
        main.setVisibility(View.VISIBLE);
        button.setVisibility(View.VISIBLE);
    }
}


