package <%= appPackage %>.di.common;

import dagger.Module;

/**
 * Created by Abbas on 26/06/16.
 */
@Module
public class InterceptorModule {
    private static final String CACHE_CONTROL = "Cache-Control";

//    @Singleton
//    @Provides
//    @Named("cacheInterceptor")
//    public Interceptor provideCacheInterceptor() {
//        return chain -> {
//            Response response = chain.proceed(chain.request());
//
//            CacheControl cacheControl = new CacheControl.Builder()
//                    .maxAge(2, TimeUnit.MINUTES)
//                    .build();
//
//            return response.newBuilder()
//                    .header(CACHE_CONTROL, cacheControl.toString())
//                    .build();
//        };
//    }


//    @Singleton
//    @Provides
//    @Named("offlineInterceptor")
//    public Interceptor provideOfflineCacheInterceptor(@Named("isConnected") boolean isConnected) {
//        return chain -> {
//            Request request = chain.request();
//
//            if (!isConnected) {
//                CacheControl cacheControl = new CacheControl.Builder()
//                        .maxStale(7, TimeUnit.DAYS)
//                        .build();
//
//                request = request.newBuilder()
//                        .cacheControl(cacheControl)
//                        .build();
//            }
//
//            return chain.proceed(request);
//        };
//    }
}
