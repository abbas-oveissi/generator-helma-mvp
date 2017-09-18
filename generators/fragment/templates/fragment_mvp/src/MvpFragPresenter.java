package <%= appPackage %>.features<%= fragmentPackageName %>;


  import java.lang.ref.WeakReference;

import javax.inject.Inject;
  import okhttp3.ResponseBody;

import <%= appPackage %>.interactors.MyInteractor;
import <%= appPackage %>.interactors.remote.error.GeneralApiException;
import rx.Observer;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;
import timber.log.Timber;

public class <%= fragmentName %>Presenter implements <%= fragmentName %>Contract.Presenter {

    private CompositeSubscription mSubscriptions;
    private WeakReference<<%= fragmentName %>Contract.View> mtView;
    private MyInteractor myInteractor;

    @Inject
    public <%= fragmentName %>Presenter(MyInteractor myInteractor) {
        this.myInteractor = myInteractor;
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
    public void onViewAttached(<%= fragmentName %>Contract.View view) {
        this.mtView=new WeakReference<>(view);
    }

    public void getVideoDetailById() {
        Subscription subscription =
                myInteractor.getNavigationDrawerItems()
                        .subscribe(new Observer<ResponseBody>() {
                            @Override
                            public void onCompleted() {
                                Timber.d("onCompleted");
                            }

                            @Override
                            public void onError(Throwable e) {
                                Timber.e("onError");
                                e.printStackTrace();
                                if (e instanceof GeneralApiException) {
                                    GeneralApiException generalApiException = (GeneralApiException) e;
                                    Timber.e(generalApiException.message());
                                }
                            }

                            @Override
                            public void onNext(ResponseBody s) {
                                Timber.d("onNext");
                            }
                        });
        mSubscriptions.add(subscription);
    }

}
