package com.alex.hidebottomnavigation;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.Switch;

import com.alex.hidebottomnavigation.Adapter.MyAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    CoordinatorLayout rootlayout;
    List<String> mStrings = new ArrayList<>();
    RecyclerView recycler_item;
    BottomNavigationView btv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rootlayout = (CoordinatorLayout)findViewById(R.id.rootLayout);
        recycler_item = (RecyclerView)findViewById(R.id.item_recycler);
        btv = (BottomNavigationView)findViewById(R.id.bottom_nav);

        btv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.message:
                        Snackbar.make(rootlayout, "rtrtrtrtrt", Snackbar.LENGTH_LONG)
                                .show();
                    break;
                }
                return true;
            }
        });
        
        genData();
    }

    private void genData() {
        for (int i=0; i<100; i++) {
            mStrings.add(new StringBuilder("This is item ").append(i+1).toString());
            MyAdapter adapter = new MyAdapter(this, mStrings);
            recycler_item.setAdapter(adapter);
        }
    }
}
