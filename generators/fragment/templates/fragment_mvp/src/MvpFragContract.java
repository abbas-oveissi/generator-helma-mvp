package <%= appPackage %>.features<%= fragmentPackageName %>;

import <%= appPackage %>.utils.bases.BasePresenter;
import <%= appPackage %>.utils.bases.BaseView;

/**
 * Created by Abbas on 02/06/2016.
 */
public class <%= fragmentName %>Contract {
    public interface View extends BaseView<Presenter> {
        void showError();


    }

    public interface Presenter extends BasePresenter<View> {
        void getVideoDetailById();

    }
}
