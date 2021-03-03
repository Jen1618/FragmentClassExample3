package com.example.fragmentexample3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    private boolean twoPane = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // if we can find the second fragment in the layout
        // that means we have 2 panes -> landscape
        if (findViewById(R.id.fragmentContainer_land_second) != null){
            twoPane = true;
        }
        // loading in whether 1 or 2 fragments baased on this boolean value

        if (!twoPane){
            loadFragment(new FirstFragment(), R.id.fragmentContainer_first);
            Button button = findViewById(R.id.button_personality);
            button.setOnClickListener( v ->  launchActivity(v));
        }

        else {
            loadFragment(new FirstFragment(), R.id.fragmentContainer_land_first);
            loadFragment(new SecondFragment(), R.id.fragmentContainer_land_second);
        }

    }

    public void loadFragment(Fragment fragment, int id){
        FragmentManager fragmentManager = getSupportFragmentManager();
        // create a fragment transaction to begin the transaction and replace the fragment
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //replacing the placeholder - fragmentContainerView with the fragment that is passed as parameter
        fragmentTransaction.replace(id, fragment);
        fragmentTransaction.commit();
    }

    public void launchActivity(View view){
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }
}