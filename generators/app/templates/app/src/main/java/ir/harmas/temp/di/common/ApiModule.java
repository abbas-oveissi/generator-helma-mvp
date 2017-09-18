package <%= appPackage %>.di.common;


import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import <%= appPackage %>.interactors.remote.MyApi;
import <%= appPackage %>.interactors.remote.MyApiService;
import <%= appPackage %>.interactors.remote.MyApiServiceImpl;
import retrofit2.Retrofit;

/**
 * Created by Abbas on 5/17/16.
 */
@Module
public class ApiModule {

    @Provides
    @Singleton
    public MyApiService provideApiService(@Named("myApiRetrofit") Retrofit retrofit) {
        return new MyApiServiceImpl(retrofit.create(MyApi.class));
    }

}
