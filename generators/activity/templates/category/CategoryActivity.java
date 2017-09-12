package <%= appPackage %>.features<%= actvitiyPackageName %>;

import android.os.Bundle;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import <%= appPackage %>.<%= appName %>Application;
import <%= appPackage %>.R;
import <%= appPackage %>.utils.bases.BaseActivity;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import <%= appPackage %>.utils.AdvancedEndlessRecyclerOnScrollListener;
import <%= appPackage %>.utils.customview.LoadingLayout;
import timber.log.Timber;
import java.util.ArrayList;
import java.util.List;
import static android.R.attr.id;

public class <%= activityName %>Activity extends BaseActivity implements <%= activityName %>Contract.View <% if (haveFrag) { %>, <%= fragmentName %>Fragment.Interactor<%= fragmentName %>Fragment <% } %>{

    @Inject
    public <%= activityName %>Contract.Presenter mPresenter;

    @BindView(R.id.toolbar)
    Toolbar toolbar;


    <% if (haveList) { %>
    @BindView(R.id.rv<%= listName %>)
    RecyclerView rv<%= listName %>;
    @BindView(R.id.loadinglayout)
    LoadingLayout loadinglayout;
    AdvancedEndlessRecyclerOnScrollListener advancedEndlessRecyclerOnScrollListener;
    public int current_page=1;
    <%= listName %>Adapter movieAdapter;
    <% } %>

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        <%= appName %>Application.getComponent().plus(new <%= activityName %>PresenterModule()).inject(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_<%= activityNameUnScored %>);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        mPresenter.onViewAttached(this);



        <% if (haveList) { %>
        initToolbar();
         initRecyclerview();


        //        mPresenter.attachView(this);
        //        mPresenter.getMoviesByTitle(title,1);
        //        current_page++;

        <% } %>


        <% if (haveFrag) { %>
        show<%= fragmentName %>Fragment();

                <% } %>
    }

        <% if (haveFrag) { %>
        <%= fragmentName %>Fragment childFragment;
        private void show<%= fragmentName %>Fragment()
        {
            childFragment=<%= fragmentName %>Fragment.newInstance("test1");
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container,childFragment)
                    //.addToBackStack(fragmentProviders.getClass().getName())
                    .commit();
        }


    @Override
    public void showOrders(String order) {
        Timber.d("Show string variable from fragment");
    }

        <% } %>


   <% if (haveList) { %>
  private void initRecyclerview()
    {
        loadinglayout.setState(LoadingLayout.STATE_SHOW_DATA);
        loadinglayout.setListener(new LoadingLayout.onErrorClickListener() {
            @Override
            public void onClick() {
                // handle error on click
            }
        });


        rv<%= listName %>.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        movieAdapter=new <%= listName %>Adapter(this, new ArrayList<Movie>());
        movieAdapter.addItem(new Movie(1,"A1","https://avatars2.githubusercontent.com/u/12421949?v=3&s=88"));
        movieAdapter.addItem(new Movie(1,"A2","https://avatars2.githubusercontent.com/u/12421949?v=3&s=88"));
        movieAdapter.addItem(new Movie(1,"A3","https://avatars2.githubusercontent.com/u/12421949?v=3&s=88"));
        movieAdapter.addItem(new Movie(1,"A4","https://avatars2.githubusercontent.com/u/12421949?v=3&s=88"));
        movieAdapter.addItem(new Movie(1,"A5","https://avatars2.githubusercontent.com/u/12421949?v=3&s=88"));
        movieAdapter.addItem(new Movie(1,"A6","https://avatars2.githubusercontent.com/u/12421949?v=3&s=88"));
        movieAdapter.addItem(new Movie(1,"A7","https://avatars2.githubusercontent.com/u/12421949?v=3&s=88"));
        movieAdapter.addItem(new Movie(1,"A8","https://avatars2.githubusercontent.com/u/12421949?v=3&s=88"));
        movieAdapter.addItem(new Movie(1,"A9","https://avatars2.githubusercontent.com/u/12421949?v=3&s=88"));
        movieAdapter.addItem(new Movie(1,"A10","https://avatars2.githubusercontent.com/u/12421949?v=3&s=88"));
        movieAdapter.addItem(new Movie(1,"A11","https://avatars2.githubusercontent.com/u/12421949?v=3&s=88"));
        movieAdapter.addItem(new Movie(1,"A12","https://avatars2.githubusercontent.com/u/12421949?v=3&s=88"));
        movieAdapter.addItem(new Movie(1,"A12","https://avatars2.githubusercontent.com/u/12421949?v=3&s=88"));
        movieAdapter.addItem(new Movie(1,"A13","https://avatars2.githubusercontent.com/u/12421949?v=3&s=88"));
        movieAdapter.addItem(new Movie(1,"A14","https://avatars2.githubusercontent.com/u/12421949?v=3&s=88"));
        movieAdapter.addItem(new Movie(1,"A15","https://avatars2.githubusercontent.com/u/12421949?v=3&s=88"));
        movieAdapter.addItem(new Movie(1,"A16","https://avatars2.githubusercontent.com/u/12421949?v=3&s=88"));
        movieAdapter.setItemClickListener(new <%= listName %>Adapter.ItemClickListener() {
            @Override
            public void ItemClicked(int position, Movie item) {
                // handle item click
            }
        });
        rv<%= listName %>.setAdapter(movieAdapter);
        movieAdapter.notifyDataSetChanged();
        advancedEndlessRecyclerOnScrollListener=new AdvancedEndlessRecyclerOnScrollListener((LinearLayoutManager) rv<%= listName %>.getLayoutManager()) {
            @Override
            public void onLoadMore() {
                // send request to server
                // dont forget to set loading false!!!!
                Timber.d("request to load page %d",current_page);
                current_page++;

            }
        };
        rv<%= listName %>.addOnScrollListener(advancedEndlessRecyclerOnScrollListener);

    }

    public void showMoreMovies(List<Movie> movies) {
        advancedEndlessRecyclerOnScrollListener.setLoading(false);
        for(Movie p:movies)
        {
            movieAdapter.addItem(p);
        }
    }
  <% } %>

    private void initToolbar() {
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
      getSupportActionBar().setDisplayShowHomeEnabled(true);
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

}
