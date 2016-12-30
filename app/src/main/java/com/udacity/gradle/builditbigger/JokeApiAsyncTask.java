package com.udacity.gradle.builditbigger;

import android.os.AsyncTask;

import com.example.brenomar.myapplication.backend.jokeApi.JokeApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

/**
 * Created by brenomar on 29/12/16.
 */

public abstract class JokeApiAsyncTask extends AsyncTask<Void, Void, String> {
    Exception exception = null;

    public boolean isException(){
        return exception !=null;
    }

    public Exception getException() {
        return exception;
    }

    @Override
    protected String doInBackground(Void... voids) {
        try {
            return getJokeApi().get().execute().getData();
        } catch (Exception e) {
            this.exception = e;
            return new String();
        }
    }

    private static  JokeApi jokeApi = null;
    private JokeApi getJokeApi(){
        if(jokeApi == null) {  // Only do this once
            JokeApi.Builder builder = new JokeApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            jokeApi = builder.build();
        }
        return jokeApi;
    }
}
