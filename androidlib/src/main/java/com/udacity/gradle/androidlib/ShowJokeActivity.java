package com.udacity.gradle.androidlib;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class ShowJokeActivity extends AppCompatActivity {
    private static final String PARAM_JOKE = "param_joke";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_joke);
        Bundle bundle = getIntent().getExtras();
        String strJoke = bundle != null? bundle.getString(PARAM_JOKE,null): null;
        if ( strJoke != null ){
            ((TextView) findViewById(R.id.textViewShowJoke)).setText(strJoke);
        } else {
            Toast.makeText(this, R.string.error_not_show_the_joke,Toast.LENGTH_SHORT).show();
            this.finish();
        }


    }

    public static void showJoke(Context context, String strJoke){
        Intent intent = new Intent(context,ShowJokeActivity.class);
        intent.putExtra(PARAM_JOKE, strJoke);
        context.startActivity(intent);
    }


}
