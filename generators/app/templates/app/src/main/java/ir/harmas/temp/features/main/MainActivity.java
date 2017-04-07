package <%= appPackage %>.features.main;

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
import <%= appPackage %>.features.main.drawerlayout.NavigationAdapter;
import <%= appPackage %>.features.main.drawerlayout.NavigationDrawerBuilder;
import <%= appPackage %>.features.main.drawerlayout.NavigationItem;
import <%= appPackage %>.utils.bases.BaseActivity;

public class MainActivity extends BaseActivity implements MainActivityContract.View {



    @BindView(R.id.listDrawer)
    ListView mListDrawer;
    @BindView(R.id.layoutDrawer)
    DrawerLayout mLayoutDrawer;
    @BindView(R.id.relativeDrawer)
    LinearLayout mRelativeDrawer;

    @BindView(R.id.viewpager)
    ViewPager viewpager;

    @Inject
    MainActivityContract.Presenter presenter;


    @BindView(R.id.tabanim_tabs)
    TabLayout tabLayout;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private FragmentManager mFragmentManager;
    private Fragment mFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        initNavigationDrawer();
        initToolbar();

        <%= appName %>Application.getComponent().plus(new MainActivityPresenterModule()).inject(this);

    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.onViewAttached(this);
    }

 @Override
    public void onPause() {
        super.onPause();
        presenter.unsubscribe();
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
