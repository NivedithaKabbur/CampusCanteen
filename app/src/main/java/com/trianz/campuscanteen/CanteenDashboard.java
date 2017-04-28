package com.trianz.campuscanteen;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

public class CanteenDashboard extends AppCompatActivity {

    private String TAG = "campus_canteen";
    private RecyclerView recyclerView;
    private CampusCafeAdapter campusCafeAdapter;
    ArrayList<CampusCafe> campusCafeArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canteen_dashboard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        campusCafeArray = new ArrayList<>();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_canteen_dashboard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseObservable firebase = new FirebaseObservable();
        Observable<DataSnapshot> firebaseObservable = firebase.createFirebaseDataObservable();

        firebaseObservable.observeOn(AndroidSchedulers.mainThread()).doOnNext(new Consumer<DataSnapshot>() {
            @Override
            public void accept(DataSnapshot dataSnapshot) throws Exception {

                Log.d(TAG,Double.toString(dataSnapshot.child("cafes").getChildrenCount()));

                String cafe_name = "cafe";
                campusCafeArray.clear();

                for(int i=0; i<dataSnapshot.child("cafes").getChildrenCount(); i++)
                {
                    CampusCafe campusCafe = new CampusCafe();
                    campusCafe.setCafeTag(cafe_name+Integer.toString(i+1));
                    campusCafe.setCafeName(dataSnapshot.child("cafes").child(cafe_name+Integer.toString(i+1)).child("name").getValue().toString());
                    campusCafe.setCafeUrl(dataSnapshot.child("cafes").child(cafe_name+Integer.toString(i+1)).child("cafeURL").getValue().toString());

                    campusCafeArray.add(campusCafe);
                }

                Log.v(TAG, dataSnapshot.child("cafes").child("cafe1").child("items").getValue().toString());
                recyclerViewSetter();
            }
        }).subscribe();

    }

    public void recyclerViewSetter()
    {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        campusCafeAdapter = new CampusCafeAdapter(CanteenDashboard.this, campusCafeArray);
        GridLayoutManager gm = new GridLayoutManager(CanteenDashboard.this, 2);
        recyclerView.setLayoutManager(gm);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(campusCafeAdapter);

    }
}
