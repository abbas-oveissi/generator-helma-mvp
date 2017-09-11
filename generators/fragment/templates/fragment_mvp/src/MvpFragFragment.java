package <%= appPackage %>.features<%= fragmentPackageName %>;

  import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import butterknife.ButterKnife;
import <%= appPackage %>.<%= appName %>Application;
import <%= appPackage %>.R;

/**
 * Created by abbas.
 */

public class <%= fragmentName %>Fragment extends Fragment implements <%= fragmentName %>Contract.View{

    @Inject
    public <%= fragmentName %>Contract.Presenter mPresenter;

    private Activity activity;
    private View rootView;
    private Interactor<%= fragmentName %>Fragment interactor<%= fragmentName %>Fragment;
    private String name;

    public static <%= fragmentName %>Fragment newInstance(String name) {
        <%= fragmentName %>Fragment fragmentProviders=new <%= fragmentName %>Fragment();
        Bundle args = new Bundle();
        args.putString("name", name);
        fragmentProviders.setArguments(args);
        return  fragmentProviders;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Bundle args = getArguments();
            name = args.getString("name");
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        //inject
        KobraApplication.getComponent().plus(new <%= fragmentName %>PresenterModule()).inject(this);

        if (context instanceof Activity){
            this.activity=(Activity) context;
        }

        if (!(activity instanceof Interactor<%= fragmentName %>Fragment)) {
            throw new IllegalStateException("Activity does not implement Interactor<%= fragmentName %>Fragment");
        } else {
            interactor<%= fragmentName %>Fragment = (Interactor<%= fragmentName %>Fragment) activity;
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_<%= fragmentNameUnScored %>, container, false);
        ButterKnife.bind(this,rootView);

        //atachview
        mPresenter.onViewAttached(this);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        //unsubscribe
        mPresenter.unsubscribe();
    }

    @Override
    public void showError() {

    }


    public interface Interactor<%= fragmentName %>Fragment {

        void setToolbarTitle(String title);
    }


}
