package <%= appPackage %>.features<%= actvitiyPackageName %>;

import android.os.Bundle;

import javax.inject.Inject;

import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;

import butterknife.BindView;
import butterknife.ButterKnife;
import <%= appPackage %>.<%= appName %>Application;
import <%= appPackage %>.R;
import <%= appPackage %>.utils.bases.BaseActivity;

import static android.R.attr.id;

public class <%= activityName %>Activity extends BaseActivity implements <%= activityName %>Contract.View
                  , NavigationView.OnNavigationItemSelectedListener {

    @Inject
    public <%= activityName %>Contract.Presenter mPresenter;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.nav_view)
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_<%= activityNameUnScored %>);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        initNavigationDrawer();
        initToolbar();
        <%= appName %>Application.getComponent().plus(new <%= activityName %>PresenterModule()).inject(this);
    }


    private void initToolbar() {
    }

    private void initNavigationDrawer() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        <% if (isDynamic) { %>
          final Menu menu = navigationView.getMenu();
                  for (int i = 1; i <= 3; i++) {
                      MenuItem menuItem= menu.add("Runtime item "+ i);
                      menuItem.setIcon(R.drawable.ic_menu_camera);
                  }
                  // adding a section and items into it
                  final SubMenu subMenu = menu.addSubMenu("SubMenu Title");
                  for (int i = 1; i <= 2; i++) {
                      MenuItem menuItem=subMenu.add("SubMenu Item " + i);
                      menuItem.setIcon(R.drawable.ic_menu_camera);
                  }

                  for (int i = 0, count = navigationView.getChildCount(); i < count; i++) {
                      final View child = navigationView.getChildAt(i);
                      if (child != null && child instanceof ListView) {
                          final ListView menuView = (ListView) child;
                          final HeaderViewListAdapter adapter = (HeaderViewListAdapter) menuView.getAdapter();
                          final BaseAdapter wrapped = (BaseAdapter) adapter.getWrappedAdapter();
                          wrapped.notifyDataSetChanged();
                      }
                  }
        <% } %>

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

//        if (id == R.id.nav_camera) {
//            // Handle the camera action
//        } else if (id == R.id.nav_gallery) {
//
//        } else if (id == R.id.nav_slideshow) {
//
//        } else if (id == R.id.nav_manage) {
//
//        } else if (id == R.id.nav_share) {
//
//        } else if (id == R.id.nav_send) {
//
//        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
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
