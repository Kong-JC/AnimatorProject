package com.example.layoutanimation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView _listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _listView = (ListView) findViewById(R.id.listView);

        List<String> list = new ArrayList<String>();
        for (int i = 1; i <= 20; i++) {
            list.add("极客学院" + i);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        _listView.setAdapter(adapter);

        LayoutAnimationController lac = new LayoutAnimationController(AnimationUtils.loadAnimation(this, R.anim.item));
        lac.setOrder(LayoutAnimationController.ORDER_NORMAL);
        _listView.setLayoutAnimation(lac);

        _listView.startLayoutAnimation();
    }
}
