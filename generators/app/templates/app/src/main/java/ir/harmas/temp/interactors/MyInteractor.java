package <%= appPackage %>.interactors;

import javax.inject.Inject;
  import okhttp3.ResponseBody;

import <%= appPackage %>.interactors.remote.MyApiService;
import <%= appPackage %>.utils.SchedulerProvider;
import rx.Observable;

/**
 * Created by abbas on 7/10/16.
 */
public class MyInteractor {

    private final MyApiService myApiService;
    private final SchedulerProvider scheduler;

    @Inject
    MyInteractor(MyApiService videoService, SchedulerProvider scheduler) {
        this.myApiService = videoService;
        this.scheduler = scheduler;
    }

    public Observable<ResponseBody> getNavigationDrawerItems() {
        return this.myApiService.getNavigationDrawerItems()
                .subscribeOn(scheduler.backgroundThread())
                .observeOn(scheduler.mainThread());
    }
}
