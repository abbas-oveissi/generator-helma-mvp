package <%= appPackage %>.features<%= actvitiyPackageName %>;


import java.lang.ref.WeakReference;

import javax.inject.Inject;

import rx.subscriptions.CompositeSubscription;

public class <%= activityName %>Presenter implements <%= activityName %>Contract.Presenter {

    private CompositeSubscription mSubscriptions;
    private WeakReference<<%= activityName %>Contract.View> mtView;

    @Inject
    public <%= activityName %>Presenter() {
        mSubscriptions = new CompositeSubscription();
    }

    @Override
    public void subscribe() {
    }

    @Override
    public void unsubscribe() {
        mSubscriptions.clear();
    }

    @Override
    public void onViewAttached(<%= activityName %>Contract.View view) {
        this.mtView=new WeakReference<>(view);
    }

}
