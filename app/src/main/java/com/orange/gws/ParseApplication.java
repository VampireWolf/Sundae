package com.orange.gws;

/**
 * Created by a on 30/11/15.
 */
import android.app.Application;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseInstallation;
import com.parse.ParseObject;

public class ParseApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        ParseObject.registerSubclass(Message.class);
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "0AFp1EjN82tWeLl6ZPaHgY3Hq8QLOPHorZAt8a7b", "Wp0FeOoZ1mqT73A4NhZCB8uL7WRAvefxknratdfm");
        ParseInstallation.getCurrentInstallation().saveInBackground();

    }
}
