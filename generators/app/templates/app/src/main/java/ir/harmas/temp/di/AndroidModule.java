package <%= appPackage %>.di;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.preference.PreferenceManager;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import <%= appPackage %>.<%= appName %>Application;

/**
 * Created by Abbas on 22/05/16.
 */
@Module
public class AndroidModule {
    private <%= appName %>Application weatherApplication;
    int PRIVATE_MODE = 0;


    public AndroidModule(<%= appName %>Application weatherApplication) {
        this.weatherApplication = weatherApplication;
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return weatherApplication.getApplicationContext();
    }

    @Provides
    @Singleton
    public Resources provideResources() {
        return weatherApplication.getResources();
    }

    @Provides
    @Singleton
    public SharedPreferences provideSharedPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(weatherApplication);
    }

    @Provides
    @Named("UserSharedPref")
    @Singleton
    public SharedPreferences provideUserSharedPreferences() {
        return weatherApplication.getSharedPreferences("UserSettings", PRIVATE_MODE);
    }
}
