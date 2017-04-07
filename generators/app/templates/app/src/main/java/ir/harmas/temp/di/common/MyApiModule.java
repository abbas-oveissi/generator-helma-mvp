package <%= appPackage %>.di.common;


import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import <%= appPackage %>.interactors.remote.MyApi;
import <%= appPackage %>.interactors.remote.MyApiService;
import <%= appPackage %>.interactors.remote.MyApiServiceImpl;
import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by Abbas on 5/17/16.
 */
@Module
public class MyApiModule {

    @Provides
    @Singleton
    public MyApiService provideWeatherApiService(@Named("myApiRetrofit") Retrofit retrofit) {
        return new MyApiServiceImpl(retrofit.create(MyApi.class));
    }

    @Provides
    @Singleton
    @Named("myApiRetrofit")
    public Retrofit provideRetrofit(@Named("myApiBaseUrl") String baseUrl, Converter.Factory converterFactory, CallAdapter.Factory callAdapterFactory, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(converterFactory)
                .addCallAdapterFactory(callAdapterFactory)
                .client(okHttpClient)
                .build();
    }


}
