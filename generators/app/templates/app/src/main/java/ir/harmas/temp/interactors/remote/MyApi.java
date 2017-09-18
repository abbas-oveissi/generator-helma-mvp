package <%= appPackage %>.interactors.remote;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by Abbas on 30/04/16.
 */
public interface MyApi {
    //http://moviesapi.ir/api/v1/movies
    @GET("/api/v1/movies")
    Observable<ResponseBody> getNavigationDrawerItems();
}
