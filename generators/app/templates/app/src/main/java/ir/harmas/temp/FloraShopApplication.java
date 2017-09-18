package <%= appPackage %>;

import android.app.Application;
  import android.app.Activity;

  import javax.inject.Inject;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import dagger.android.support.HasSupportFragmentInjector;
import <%= appPackage %>.di.AndroidModule;
import <%= appPackage %>.di.ApplicationComponent;
import <%= appPackage %>.di.DaggerApplicationComponent;
import timber.log.Timber;
  import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
  import <%= appPackage %>.utils.bases.NoOpHasSupportFragmentInjector;
// helmamvp-needle-import-dagger-fragmentinjector


/**
 * Created by abbas on 6/25/16.
 */
public class <%= appName %>Application extends Application implements  HasActivityInjector,NoOpHasSupportFragmentInjector {

    @Inject
    DispatchingAndroidInjector<Activity> dispatchingActivityInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerApplicationComponent.builder()
                  .androidModule(new AndroidModule(this))
                  .build();
        component.inject(this);


        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/shabnam_m.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    private static ApplicationComponent component;

    public static ApplicationComponent getComponent() {
        return component;
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
      return dispatchingActivityInjector;
    }

    // helmamvp-needle-add-dagger-fragmentinjector


  }
