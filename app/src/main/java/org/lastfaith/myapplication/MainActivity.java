package org.lastfaith.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button btn;
    private TextView tv;
    private Button getDataButton;
    private String allKickBoxers;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.button);
        final EditText name = findViewById(R.id.editText);
        final EditText speed = findViewById(R.id.editText2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    final ParseObject kickBoxer = new ParseObject("KickBoxer");
                    kickBoxer.put("name", name.getText().toString());
                    kickBoxer.put("KickSpeed", Integer.parseInt(speed.getText().toString()));
                    kickBoxer.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null) {
                                FancyToast.makeText(MainActivity.this, kickBoxer.get("name") + " is saved", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();

                            } else {
                                FancyToast.makeText(MainActivity.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                            }
                        }
                    });
                } catch (Exception e) {
                    FancyToast.makeText(MainActivity.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                }
            }
        });
        tv = findViewById(R.id.textView);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseQuery<ParseObject> parseQuery = ParseQuery.getQuery("KickBoxer");
                parseQuery.getInBackground("UAphLOPxzM", new GetCallback<ParseObject>() {
                    @Override
                    public void done(ParseObject object, ParseException e) {
                        tv.setText("Name is "+object.get("name") + ". and Punch speed id " + object.get("KickSpeed"));
                    }
                });
            }
        });
        getDataButton = findViewById(R.id.btnGetData);
        getDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allKickBoxers = "";
                ParseQuery<ParseObject> queryAll = ParseQuery.getQuery("KickBoxer");
                queryAll.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {
                        if(e==null){
                            if(objects.size()>0){
                                for(ParseObject kickboxer : objects){
                                    allKickBoxers=allKickBoxers+kickboxer.get("name")+"\n";
                                    FancyToast.makeText(MainActivity.this, allKickBoxers, FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();

                                }
                            }
                        }
                    }
                });
            }
        });
    }
}
