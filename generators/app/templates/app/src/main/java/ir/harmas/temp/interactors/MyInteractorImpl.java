package <%= appPackage %>.interactors;

import javax.inject.Inject;

import <%= appPackage %>.interactors.remote.MyApiService;
import <%= appPackage %>.utils.SchedulerProvider;
import rx.Observable;

/**
 * Created by abbas on 7/10/16.
 */
public class MyInteractorImpl implements MyInteractor {

    private final MyApiService myApiService;
    private final SchedulerProvider scheduler;

    @Inject
    MyInteractorImpl(MyApiService videoService, SchedulerProvider scheduler) {
        this.myApiService = videoService;
        this.scheduler = scheduler;
    }

    @Override
    public Observable<String> getNavigationDrawerItems() {
        return this.myApiService.getNavigationDrawerItems()
                .subscribeOn(scheduler.backgroundThread())
                .observeOn(scheduler.mainThread());
    }
}
