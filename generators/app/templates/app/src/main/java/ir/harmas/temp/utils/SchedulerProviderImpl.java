package <%= appPackage %>.utils;


import javax.inject.Inject;

import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Abbas on 30/04/16.
 */
public class SchedulerProviderImpl implements SchedulerProvider {

    @Inject
    public SchedulerProviderImpl() {
    }

    @Override
    public Scheduler mainThread() {
        return AndroidSchedulers.mainThread();
    }

    @Override
    public Scheduler backgroundThread() {
        return Schedulers.io();
    }

}
