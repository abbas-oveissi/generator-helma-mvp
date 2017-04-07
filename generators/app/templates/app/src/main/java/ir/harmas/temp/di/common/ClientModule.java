package <%= appPackage %>.di.common;


import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by Abbas on 5/17/16.
 */
@Module
public class ClientModule {
    @Singleton
    @Provides
    public OkHttpClient provideOkHttpClient(HttpLoggingInterceptor loggingInterceptor,
                                            @Named("networkTimeoutInSeconds") int networkTimeoutInSeconds,
                                            @Named("isDebug") boolean isDebug,
                                            //@Named("cacheInterceptor") Interceptor cacheInterceptor,
                                            //@Named("offlineInterceptor") Interceptor offlineCacheInterceptor
                                            Cache cache) {

        ////  6/30/16 enable kardan e cache kardan ha
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder()
                //.addNetworkInterceptor(new StethoInterceptor())
                //.addInterceptor(cacheInterceptor)
                //.addInterceptor(offlineCacheInterceptor)
                //.cache(cache)
                .readTimeout(networkTimeoutInSeconds, TimeUnit.SECONDS)
                .connectTimeout(networkTimeoutInSeconds, TimeUnit.SECONDS);

        //show logs if app is in Debug mode
        if (isDebug)
            okHttpClient.addInterceptor(loggingInterceptor);

        return okHttpClient.build();
    }
}
