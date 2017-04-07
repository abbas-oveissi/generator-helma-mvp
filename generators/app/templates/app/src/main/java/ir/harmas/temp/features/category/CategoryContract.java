package <%= appPackage %>.features.category;

import <%= appPackage %>.utils.bases.BasePresenter;
import <%= appPackage %>.utils.bases.BaseView;


/**
 * Created by Abbas on 02/06/2016.
 */
public class CategoryContract {
    public interface View extends BaseView<Presenter> {
        void showError();

    }

    public interface Presenter extends BasePresenter<CategoryContract.View> {
        void getVideoDetailById();
    }
}
