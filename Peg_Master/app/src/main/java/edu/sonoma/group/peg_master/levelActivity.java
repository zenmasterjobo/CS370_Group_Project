package edu.sonoma.group.peg_master;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

public class levelActivity extends AppCompatActivity {

    private int numChests;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //set numChests from the bundle
        numChests = getIntent().getExtras().getInt("numChests");

        String debugmsg = "numChests: " + Integer.toString(numChests);
        //Toast.makeText(this.getApplicationContext(),debugmsg,Toast.LENGTH_LONG).show();

        //use numChests here on a class to represent result from algorithm? might have to do
        //on background thread

        //auto generated
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        BoardFragment bf = new BoardFragment();
        ft.replace(R.id.fragment_container, bf, null);
        ft.commit();
    }

}
