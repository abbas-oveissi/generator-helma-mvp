package <%= appPackage %>.features.category;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import <%= appPackage %>.<%= appName %>Application;
import <%= appPackage %>.R;
import <%= appPackage %>.pojo.Category;
import <%= appPackage %>.utils.bases.BaseActivity;
import <%= appPackage %>.utils.customview.LoadingLayout;
import android.support.v7.widget.Toolbar;

import static android.R.attr.id;

public class CategoryActivity extends BaseActivity implements CategoryContract.View {


    @Inject
    public CategoryContract.Presenter mPresenter;

    @BindView(R.id.loading)
    LoadingLayout loading;



    CategoryAdapter mAdapter;
    @BindView(R.id.rvCat)
    RecyclerView rvCat;
    private String video_url;


    @BindView(R.id.toolbar)
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);



//        if (savedInstanceState != null) {
//            // Restore value of members from saved state
//            id = savedInstanceState.getInt("save_state_video_id");
//            name = savedInstanceState.getString("save_state_name");
//            video_url = savedInstanceState.getString("save_state_video_url");
//        }
//
//
//        if(getIntent().getExtras()!=null)
//        {
//            id=getIntent().getIntExtra("video_id",-1);
//            name=getIntent().getStringExtra("video_name");
//        }

        <%= appName %>Application.getComponent().plus(new CategoryPresenterModule()).inject(this);
        initRecyclerview();
        initToolbar();

    }


    private void initToolbar() {
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
      getSupportActionBar().setDisplayShowHomeEnabled(true);
    }


    public void initRecyclerview()
    {
        mAdapter=new CategoryAdapter(this,null);
        rvCat.setLayoutManager(new LinearLayoutManager(CategoryActivity.this,LinearLayoutManager.HORIZONTAL,false));
        rvCat.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new CategoryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Category video) {

            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the user's current game state

        savedInstanceState.putInt("save_state_video_id", id);
        savedInstanceState.putString("save_state_video_url", video_url);

        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }



    @Override
    public void showError() {
        loading.setState(LoadingLayout.STATE_SHOW_Error);

    }


    @Override
    public void onStart() {
        super.onStart();
        mPresenter.onViewAttached(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.subscribe();
    }



    @Override
       public void onPause() {
           super.onPause();
           mPresenter.unsubscribe();
       }

}
