package edu.sonoma.group.peg_master;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Map;

public class levelActivity extends AppCompatActivity {

    private int numChests;

    private GameFactory gf;
    private Board board = new Board();
    private BoardFragment bf;
    private Key kih;
    @Override
    protected void onStart(){
        super.onStart();
        setup();
        updateGraphics();
    }

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
        bf = new BoardFragment();
        ft.replace(R.id.fragment_container, bf, null);
        ft.commit();
    }

    private void setup(){
        Chest c1 = new Chest();
        Chest c2 = new Chest();
        Chest c3 = new Chest();
        Key k1 = new Key(1);
        Key k2 = new Key(2);
        Key k3 = new Key(3);
        Key g = new Key(-1);
        kih = new Key(-1);
        c1.setLeftKey(k2);
        c2.setLeftKey(k3);
        c3.setLeftKey(k1);
        ArrayList<Chest> _board = new ArrayList<Chest>();
        _board.add(c1);
        _board.add(c2);
        _board.add(c3);
        board.setBoard(_board);
        Map<String, ArrayList<ImageButton>> BoardMap = bf.getBoardButtonMap();
        ArrayList<ImageButton> buttons = BoardMap.get("Row2");
        ImageButton chest1 = buttons.get(0);
        chest1.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Key temp = board.getChestAt(1).makeMove(kih);
                kih = new Key(Integer.parseInt(temp.getNumber()));
                updateGraphics();
                Log.d("b1", "Button 1 pressed");
                Toast.makeText(getApplicationContext(), "New kih:" + kih.getNumber(), Toast.LENGTH_LONG).show();
            }
        });
        ImageButton chest2 = buttons.get(1);
        chest2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Key temp = board.getChestAt(2).makeMove(kih);
                kih = temp;
                updateGraphics();
                Log.d("b2", "Button 2 pressed");
                Toast.makeText(getApplicationContext(), "New kih:" + kih.getNumber(), Toast.LENGTH_LONG).show();
            }
        });
        ImageButton chest3 = buttons.get(2);
        chest3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Key temp = board.getChestAt(3).makeMove(kih);
                kih = temp;
                updateGraphics();
                Log.d("b3", "Button 3 pressed");
                Toast.makeText(getApplicationContext(), "New kih:" + kih.getNumber(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void updateGraphics() {
        ArrayList<TextView> tVs = bf.getBoardTextViews();

        Map<String, ArrayList<ArrayList<TextView>>> chestKeys = bf.getChestKeysMap();
        ArrayList<ArrayList<TextView>> temp = new ArrayList<ArrayList<TextView>>();
        temp = chestKeys.get("Row2");
        // Chest 1 drawing logic
        if (board.getChestAt(1).getLeftKey() != null) {
            if (!(board.getChestAt(1).getLeftKey().getNumber().equals("-1"))) {
                temp.get(0).get(0).setText(board.getChestAt(1).getLeftKey().getNumber());
                temp.get(0).get(0).setBackgroundColor(Color.parseColor("#e40e0e"));
            } else {
                temp.get(0).get(0).setBackgroundColor(Color.parseColor("#10d313"));
                temp.get(0).get(0).setText("");
            }
            temp.get(0).get(0).setVisibility(View.VISIBLE);
            temp.get(0).get(1).setVisibility(View.INVISIBLE);
        } else if (board.getChestAt(1).getRightKey() != null) {
            if (!(board.getChestAt(1).getRightKey().getNumber().equals("-1"))) {
                temp.get(0).get(0).setText(board.getChestAt(1).getRightKey().getNumber());
                temp.get(0).get(0).setBackgroundColor(Color.parseColor("#e40e0e"));
            } else {
                temp.get(0).get(0).setBackgroundColor(Color.parseColor("#10d313"));
                temp.get(0).get(0).setText("");
            }
            temp.get(0).get(1).setVisibility(View.VISIBLE);
            temp.get(0).get(0).setVisibility(View.INVISIBLE);
        }
        // Chest 2 Drawing logic
        if (board.getChestAt(2).getLeftKey() != null) {
            if (!(board.getChestAt(2).getLeftKey().getNumber().equals("-1"))) {
                temp.get(1).get(0).setText(board.getChestAt(2).getLeftKey().getNumber());
                temp.get(1).get(0).setBackgroundColor(Color.parseColor("#e40e0e"));
            } else {
                temp.get(1).get(0).setBackgroundColor(Color.parseColor("#10d313"));
                temp.get(1).get(0).setText("");
            }
            temp.get(1).get(0).setVisibility(View.VISIBLE);
            temp.get(1).get(1).setVisibility(View.INVISIBLE);
        } else if (board.getChestAt(2).getRightKey() != null) {
            if (!(board.getChestAt(2).getRightKey().getNumber().equals("-1"))) {
                temp.get(1).get(0).setText(board.getChestAt(2).getRightKey().getNumber());
                temp.get(1).get(0).setBackgroundColor(Color.parseColor("#e40e0e"));
            } else {
                temp.get(1).get(0).setBackgroundColor(Color.parseColor("#10d313"));
                temp.get(1).get(0).setText("");
            }
            temp.get(1).get(1).setVisibility(View.VISIBLE);
            temp.get(1).get(0).setVisibility(View.INVISIBLE);
        }
        // Chest 3 drawing logic
        if (board.getChestAt(3).getLeftKey() != null) {
            if (!(board.getChestAt(3).getLeftKey().getNumber().equals("-1"))) {
                temp.get(2).get(0).setText(board.getChestAt(3).getLeftKey().getNumber());
                temp.get(2).get(0).setBackgroundColor(Color.parseColor("#e40e0e"));
            } else {
                temp.get(2).get(0).setBackgroundColor(Color.parseColor("#10d313"));
                temp.get(2).get(0).setText("");
            }
            temp.get(2).get(0).setVisibility(View.VISIBLE);
            temp.get(2).get(1).setVisibility(View.INVISIBLE);
        } else if (board.getChestAt(3).getRightKey() != null) {
            if (!(board.getChestAt(3).getRightKey().getNumber().equals("-1"))) {
                temp.get(2).get(0).setText(board.getChestAt(3).getRightKey().getNumber());
                temp.get(2).get(0).setBackgroundColor(Color.parseColor("#e40e0e"));
            } else {
                temp.get(2).get(0).setBackgroundColor(Color.parseColor("#10d313"));
                temp.get(2).get(0).setText("");
            }
            temp.get(2).get(1).setVisibility(View.VISIBLE);
            temp.get(2).get(0).setVisibility(View.INVISIBLE);
        }
    }

        // Index 18 is left slot of chest 2-1

        // e40e0e is red
        // 10d313 is green
        /*
        if (board.getChestAt(1).getLeftKey() != null){
            if (board.getChestAt(1).getLeftKey().getNumber() != "-1") {
                tVs.get(18).setBackgroundColor(Color.parseColor("#e40e0e"));
                tVs.get(18).setText(board.getChestAt(1).getLeftKey().getNumber());
            } else {
                tVs.get(18).setBackgroundColor(Color.parseColor("10d313"));
                tVs.get(18).setText("");
            }
            tVs.get(19).setVisibility(View.INVISIBLE);
        }
        else if (board.getChestAt(1).getRightKey() != null){
            if (board.getChestAt(1).getRightKey().getNumber() != "-1") {
                tVs.get(19).setBackgroundColor(Color.parseColor("#e40e0e"));
                tVs.get(19).setText(board.getChestAt(1).getLeftKey().getNumber());
            } else {
                tVs.get(19).setBackgroundColor(Color.parseColor("10d313"));
                tVs.get(19).setText("");
            }
            tVs.get(18).setVisibility(View.INVISIBLE);
        }

        //tVs.get(19).setText(board.getChestAt(1).getRightKey().getNumber());
        tVs.get(20).setText(board.getChestAt(2).getLeftKey().getNumber());
        tVs.get(20).setBackgroundColor(Color.parseColor("#e40e0e"));
        tVs.get(21).setVisibility(View.INVISIBLE);
        //tVs.get(21).setText(board.getChestAt(2).getRightKey().getNumber());
        tVs.get(22).setText(board.getChestAt(3).getLeftKey().getNumber());
        tVs.get(22).setBackgroundColor(Color.parseColor("#e40e0e"));
        tVs.get(23).setVisibility(View.INVISIBLE);
        //tVs.get(23).setText(board.getChestAt(3).getRightKey().getNumber());

        TextView chest1L = (TextView) bf.getView().findViewById(R.id.chest2_1left);
        TextView chest1R = (TextView) bf.getView().findViewById(R.id.chest2_1right);
        TextView chest2L = (TextView) bf.getView().findViewById(R.id.chest2_2left);
        TextView chest2R = (TextView) bf.getView().findViewById(R.id.chest2_2right);
        TextView chest3L = (TextView) bf.getView().findViewById(R.id.chest2_3left);
        TextView chest3R = (TextView) bf.getView().findViewById(R.id.chest2_3right);
        chest1L.setText(board.getChestAt(1).getLeftKey().getNumber());
        chest1R.setText(board.getChestAt(1).getRightKey().getNumber());
        chest2L.setText(board.getChestAt(2).getLeftKey().getNumber());
        chest2R.setText(board.getChestAt(2).getRightKey().getNumber());
        chest3L.setText(board.getChestAt(3).getLeftKey().getNumber());
        chest3R.setText(board.getChestAt(3).getRightKey().getNumber());
        */
}
