package org.lastfaith.myapplication;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("DjD7X0AiAUtoQagxpT1DMJxZ08sLPW5d5mmCPBnB")
                // if defined
                .clientKey("luNCOqDGwXlLyUOzkEmv63BsLLZZm6tBHAi1tZ4z")
                .server("https://parseapi.back4app.com/")
                .build()
        );
    }
}
