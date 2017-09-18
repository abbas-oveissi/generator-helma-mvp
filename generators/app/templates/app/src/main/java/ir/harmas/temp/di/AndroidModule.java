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
    private static final int PRIVATE_MODE=0;

    @Provides
    @Singleton
    public static Context provideContext(<%= appName %>Application application) {
        return application.getApplicationContext();
    }

    @Provides
    @Singleton
    public static Resources provideResources(<%= appName %>Application application) {
        return application.getResources();
    }

    @Provides
    @Singleton
    public static SharedPreferences provideSharedPreferences(<%= appName %>Application application) {
        return PreferenceManager.getDefaultSharedPreferences(application);
    }

    @Provides
    @Named("UserSharedPref")
    @Singleton
    public static SharedPreferences provideUserSharedPreferences(<%= appName %>Application application) {
        return application.getSharedPreferences("UserSettings", PRIVATE_MODE);
    }
}
