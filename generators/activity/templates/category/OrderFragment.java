package <%= appPackage %>.features<%= actvitiyPackageName %>;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import <%= appPackage %>..R;

/**
 * Created by abbas.
 */

public class OrderFragment extends Fragment {

    private Activity activity;
    private View rootView;
    private InteractorOrderFragment interactorOrderFragment;
    private String name;

    @BindView(R.id.tvFragmentName)
    TextView tvFragmentName;

    public static OrderFragment newInstance(String name) {
        OrderFragment fragmentProviders=new OrderFragment();
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

        this.activity = getActivity();

        if(!(activity instanceof InteractorOrderFragment))
        {
            throw new IllegalStateException("Activity does not implement InteractorOrderFragment") ;
        }

        else
        {
            interactorOrderFragment = (InteractorOrderFragment)activity ;
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        this.activity = getActivity();

        if(!(activity instanceof InteractorOrderFragment))
        {
            throw new IllegalStateException("Activity does not implement InteractorOrderFragment") ;
        }

        else
        {
            interactorOrderFragment = (InteractorOrderFragment)activity ;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_order, container, false);

        ButterKnife.bind(this,rootView);

        tvFragmentName.setText(name);


        return rootView;
    }


    @Override
    public void onResume()
    {
        super.onResume();
    }


    public interface InteractorOrderFragment  {

        void showOrders(String order) ;

    }
}
