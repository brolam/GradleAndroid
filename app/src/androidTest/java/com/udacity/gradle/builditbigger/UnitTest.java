package com.udacity.gradle.builditbigger;

import android.support.test.runner.AndroidJUnit4;
import android.util.Log;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertFalse;


/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class UnitTest {
    private static String TAG = "App UnitTest";

    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        //Context appContext = InstrumentationRegistry.getTargetContext();
        GetJokeAsyncTask getJokeAsyncTask = new GetJokeAsyncTask();
        getJokeAsyncTask.doInBackgroundTest();

    }


    /**
     * {@see JokeApiAsyncTask}
     */
    private class GetJokeAsyncTask extends JokeApiAsyncTask{

        public void doInBackgroundTest(){
            //Run the web method on the same Thread.
            String strJoke = doInBackground();
            if ( isException()){
                Assert.fail(String.format("JokeApiAsyncTask returned to an exception: %s", getException().toString()));
            }
            assertFalse("Api getJoke returned to an empty value!", strJoke.length() == 0);
            Log.i(TAG,strJoke);
        }


    }
}
