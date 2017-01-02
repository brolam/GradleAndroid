package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import com.udacity.gradle.androidlib.ShowJokeActivity;


/**
 * This is the Base Main Activity for shared features to paid and free versions.
 * By Breno Marques 01/01/2017
 */
public class MainActivityBase extends AppCompatActivity {

    View fragment;
    View progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.fragment = this.findViewById(R.id.fragment);
        this.progressBar = this.findViewById(R.id.progressBar);
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
        showJoke();
    }

    protected void showJoke(){
        new GetJokeAsyncTask(this).execute();
    }


    void showProgressBar(boolean show){
        fragment.setVisibility(show? View.GONE : View.VISIBLE);
        progressBar.setVisibility(show?View.VISIBLE : View.GONE );

    }


    private class GetJokeAsyncTask extends JokeApiAsyncTask{
        Context context;

        public GetJokeAsyncTask(Context context){
            this.context = context;
        }

        @Override
        protected void onPostExecute(String strJoke) {
            super.onPostExecute(strJoke);
            try {
                if ( isException() == false ) {
                    ShowJokeActivity.showJoke(context, strJoke);
                } else {
                    Toast.makeText(context, com.udacity.gradle.androidlib.R.string.error_not_show_the_joke,Toast.LENGTH_SHORT).show();
                }

            } finally {
                showProgressBar(false);
            }
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showProgressBar(true);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            showProgressBar(false);
        }

    }

}
