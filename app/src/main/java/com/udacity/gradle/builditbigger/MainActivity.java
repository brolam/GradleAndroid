package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


//import com.example.Jokes;
import com.udacity.gradle.androidlib.ShowJokeActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void tellJoke(View view) {
        //
        //Toast.makeText(this, Jokes.get(), Toast.LENGTH_SHORT).show();
        //ShowJokeActivity.showJoke(this, Jokes.get());
        new GetJokeAsyncTask(this).execute();
    }

    private class GetJokeAsyncTask extends JokeApiAsyncTask{
        Context context;

        public GetJokeAsyncTask(Context context){
            this.context = context;
        }

        @Override
        protected void onPostExecute(String strJoke) {
            super.onPostExecute(strJoke);
            if ( isException() == false ) {
                ShowJokeActivity.showJoke(context, strJoke);
            } else {
                Toast.makeText(context, com.udacity.gradle.androidlib.R.string.error_not_show_the_joke,Toast.LENGTH_SHORT).show();
            }

        }
    }

}
