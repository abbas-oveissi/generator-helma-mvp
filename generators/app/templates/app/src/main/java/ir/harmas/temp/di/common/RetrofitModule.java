
package <%= appPackage %>.di.common;

import com.google.gson.Gson;

import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Abbas on 26/06/16.
 */

@Module
public class RetrofitModule {


    @Provides
    @Singleton
    @Named("myApiRetrofit")
    public static Retrofit provideRetrofit(@Named("myApiBaseUrl") String baseUrl, Converter.Factory converterFactory, CallAdapter.Factory callAdapterFactory, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(converterFactory)
                .addCallAdapterFactory(callAdapterFactory)
                .client(okHttpClient)
                .build();
    }


    @Singleton
    @Provides
    public static OkHttpClient provideOkHttpClient(HttpLoggingInterceptor loggingInterceptor,
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


    @Provides
    @Singleton
    public static Cache provideCache(@Named("cacheDir") File cacheDir, @Named("cacheSize") long cacheSize) {
        Cache cache = null;

        try {
            cache = new Cache(new File(cacheDir.getPath(), "http-cache"), cacheSize);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return cache;
    }

    @Provides
    @Singleton
    public static CallAdapter.Factory provideRxJavaCallAdapterFactory() {
        return RxJavaCallAdapterFactory.create();
    }


    @Provides
    @Singleton
    public static Converter.Factory provideGsonConverterFactory(Gson gson) {
        return GsonConverterFactory.create(gson);
    }
}
