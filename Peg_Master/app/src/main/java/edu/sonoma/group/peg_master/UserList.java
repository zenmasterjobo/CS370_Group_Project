package edu.sonoma.group.peg_master;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by student on 4/14/16.
 */
public class UserList extends AppCompatActivity{
    private List<String> allUsers;
    private ListView userList;



    @Override
    protected void onCreate(Bundle savedInstanceState){

        userList = (ListView)findViewById(R.id.listView);

        //populate allUsers
        //allUsers = Arrays.asList(getIntent().getExtras().getStringArray("allUsers"));

        //array adapter to display into listview
        //ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,allUsers);
        //userList.setAdapter(arrayAdapter);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.userlist);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width *.8),(int)(height*.5));

    }

    public void addUser(View V)
    {
        //create alert box to type in a string for new user name
        AlertDialog.Builder inputName = new AlertDialog.Builder(this);

        inputName.setTitle("Add User");

        //set edit text view to get user input
        final EditText input = new EditText(this);
        inputName.setView(input);

        inputName.setPositiveButton("Add",new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog,int whichButton){
                String userName = input.getText().toString();
                //Toast.makeText(getApplicationContext(), userName, Toast.LENGTH_SHORT).show();
                Intent returnIntent = new Intent();
                returnIntent.putExtra("name",userName);
                setResult(Activity.RESULT_OK,returnIntent);
                finish();

            }
        //String userName = input;
    });

        inputName.show();

    }

}
