package <%= appPackage %>.di.common;

import java.io.File;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;

/**
 * Created by Abbas on 26/06/16.
 */

@Module
public class CacheModule {

    @Provides
    @Singleton
    public Cache provideCache(@Named("cacheDir") File cacheDir, @Named("cacheSize") long cacheSize) {
        Cache cache = null;

        try {
            cache = new Cache(new File(cacheDir.getPath(), "http-cache"), cacheSize);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return cache;
    }
}
