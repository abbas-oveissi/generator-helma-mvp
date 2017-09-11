package <%= appPackage %>.features<%= actvitiyPackageName %>;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.support.v7.widget.Toolbar;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import <%= appPackage %>.<%= appName %>Application;
import <%= appPackage %>.R;
import <%= appPackage %>.features<%= actvitiyPackageName %>.drawerlayout.NavigationAdapter;
import <%= appPackage %>.features<%= actvitiyPackageName %>.drawerlayout.NavigationDrawerBuilder;
import <%= appPackage %>.features<%= actvitiyPackageName %>.drawerlayout.NavigationItem;
import <%= appPackage %>.utils.bases.BaseActivity;

public class <%= activityName %>Activity extends BaseActivity implements <%= activityName %>Contract.View {



    @BindView(R.id.listDrawer)
    ListView mListDrawer;
    @BindView(R.id.layoutDrawer)
    DrawerLayout mLayoutDrawer;
    @BindView(R.id.relativeDrawer)
    LinearLayout mRelativeDrawer;

    @BindView(R.id.viewpager)
    ViewPager viewpager;

    @Inject
<%= activityName %>Contract.Presenter mPresenter;


    @BindView(R.id.tabanim_tabs)
    TabLayout tabLayout;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private FragmentManager mFragmentManager;
    private Fragment mFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        <%= appName %>Application.getComponent().plus(new <%= activityName %>PresenterModule()).inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_<%= activityNameUnScored %>);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        mPresenter.onViewAttached(this);

        initNavigationDrawer();
        initToolbar();



    }

    @Override
    public void onStart() {
      super.onStart();
      mPresenter.subscribe();
    }

    @Override
    public void onStop() {
      super.onStop();
      mPresenter.unsubscribe();
    }


    private void initToolbar() {
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (!mLayoutDrawer.isDrawerOpen(Gravity.RIGHT)) {
                    mLayoutDrawer.openDrawer(Gravity.RIGHT);
                }
            }
        });
    }


    @Override
    public void onBackPressed() {
        if (mLayoutDrawer.isDrawerOpen(Gravity.RIGHT)) {
            mLayoutDrawer.closeDrawer(Gravity.RIGHT);
        }
        else
        {
            super.onBackPressed();
        }
    }


    NavigationAdapter mNavigationAdapter;
    private void initNavigationDrawer() {

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mLayoutDrawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mLayoutDrawer.setDrawerListener(toggle);
        toggle.syncState();


        if (mListDrawer != null) {
            List<NavigationItem> mList= NavigationDrawerBuilder.buildList(this);
            mNavigationAdapter = new NavigationAdapter(this, mList);
        }
        mListDrawer.setAdapter(mNavigationAdapter);
        mListDrawer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mLayoutDrawer.closeDrawer(mRelativeDrawer);
                setFragmentList(position);
            }
        });
    }



    private void setFragmentList(int posicao){
        mFragmentManager = getSupportFragmentManager();
        NavigationItem nia=mNavigationAdapter.getItem(posicao);
        if(nia.enTitle.equals("about"))
        {
            return;
        }
        else if(nia.enTitle.equals("home"))
        {
            return;
        }
    }

    @Override
    public void showError() {

    }


}
