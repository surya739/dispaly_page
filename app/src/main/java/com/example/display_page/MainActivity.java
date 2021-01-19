package com.example.display_page;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private String price;
    private ActionBar actionBar;
    private ArrayList<MyModel> modelArrayList;
    private MyAdapter myadapter;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        actionBar = getSupportActionBar();

        viewPager = findViewById( R.id.image );

        loadcards();


        final Spinner genres1 = findViewById( R.id.generss );



        final String[] items = new String[]{"Per Day","Per week","Per month"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>( this, android.R.layout.simple_spinner_dropdown_item, items );

        genres1.setAdapter( adapter );

    }

    private void loadcards() {
        modelArrayList = new ArrayList<>();
        modelArrayList.add(new MyModel( R.drawable.photo )  );
        modelArrayList.add(new MyModel( R.drawable.photo1 )  );
        modelArrayList.add(new MyModel( R.drawable.photo2 )  );


        myadapter =  new MyAdapter( this,modelArrayList );
        viewPager.setAdapter( myadapter );
        viewPager.setPadding( 0,0,0,0 );
    }
}