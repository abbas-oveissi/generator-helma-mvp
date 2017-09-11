package <%= appPackage %>.features<%= actvitiyPackageName %>;

import <%= appPackage %>.utils.bases.BasePresenter;
import <%= appPackage %>.utils.bases.BaseView;


/**
 * Created by Abbas on 02/06/2016.
 */
public class <%= activityName %>Contract {
    public interface View extends BaseView<Presenter> {
        void showError();

    }

    public interface Presenter extends BasePresenter<<%= activityName %>Contract.View> {
        void getVideoDetailById();
    }
}
