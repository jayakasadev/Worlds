package dev.kasa.worlds;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import lombok.Data;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ParseAnalytics.trackAppOpenedInBackground(getIntent());

        ParseObject gameScore = new ParseObject("GameScore");
        gameScore.put("score", 1997);
        gameScore.put("playerName", "Jaya Kasa");
        gameScore.put("cheatMode", false);
        gameScore.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if(e == null) Log.i("Parse Result", "Success");
                else Log.i("Parse Result", e.getLocalizedMessage());
            }
        });

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ParseQuery<ParseObject> query = ParseQuery.getQuery("GameScore");
        query.getInBackground(gameScore.getObjectId(), new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                if(e == null) Log.i("Found", object.toString());
                else Log.i("Not Found", e.getLocalizedMessage());
            }
        });
    }
}
