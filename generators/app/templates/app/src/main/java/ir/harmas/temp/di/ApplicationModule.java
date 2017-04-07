package <%= appPackage %>.di;

import android.content.Context;

import java.io.File;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import <%= appPackage %>.BuildConfig;
import <%= appPackage %>.utils.Constants;
import <%= appPackage %>.utils.SchedulerProvider;
import <%= appPackage %>.utils.SchedulerProviderImpl;

/**
 * Created by abbas on 6/25/16.
 */
@Module
public final class ApplicationModule {
    @Provides
    @Singleton
    @Named("isDebug")
    public boolean provideIsDebug() {
        return BuildConfig.DEBUG;
    }

    @Provides
    @Singleton
    @Named("networkTimeoutInSeconds")
    public int provideNetworkTimeoutInSeconds() {
        return 30;
    }



    @Provides
    @Singleton
    @Named("myApiBaseUrl")
    public String provideMyEndpoint() {
        return Constants.MY_BASE_URL;
    }



    @Provides
    @Singleton
    public SchedulerProvider provideAppScheduler() {
        return new SchedulerProviderImpl();
    }

    @Provides
    @Singleton
    @Named("cacheSize")
    public long provideCacheSize() {
        return 10 * 1024 * 1024; // 10 MB
    }

    @Provides
    @Singleton
    @Named("cacheDir")
    public File provideCacheDir(Context context) {
        return context.getCacheDir();
    }




//    @Provides
//    @Named("isConnected")
//    public boolean provideIsConnected(Context context) {
//        return Utils.isConnected(context);
//    }

}
