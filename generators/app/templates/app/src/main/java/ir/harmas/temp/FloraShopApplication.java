package <%= appPackage %>;

import android.app.Application;


import <%= appPackage %>.di.AndroidModule;
import <%= appPackage %>.di.ApplicationComponent;
import <%= appPackage %>.di.DaggerApplicationComponent;
import timber.log.Timber;


/**
 * Created by abbas on 6/25/16.
 */
public class <%= appName %>Application extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerApplicationComponent.builder()
                .androidModule(getAndroidModule())
                .build();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    private static ApplicationComponent component;

    public static ApplicationComponent getComponent() {
        return component;
    }

    protected AndroidModule getAndroidModule() {
        return new AndroidModule(this);
    }


}
