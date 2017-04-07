package <%= appPackage %>.features<%= actvitiyPackageName %>;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import <%= appPackage %>.R;

public class <%= listName %>Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<Movie> itemsData;

    public <%= listName %>Adapter(Context mContext, List<Movie> itemsData) {
        this.mContext = mContext;
        this.itemsData = itemsData;
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
    ItemClickListener itemClickListener;
    public interface ItemClickListener {
        void ItemClicked(int position, Movie item);
    }


    public void clear()
    {
        this.itemsData.clear();
        notifyDataSetChanged();
    }
    public void addItem(Movie post)
    {
        this.itemsData.add(post);
        notifyItemInserted(this.itemsData.size()-1);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_<%= listNameUnderScore %>,  parent, false);
        MovieViewHolder vh = new MovieViewHolder(itemLayoutView,mContext,this);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder vh, int position) {
        Movie tempItem=itemsData.get(position);
        ((MovieViewHolder)vh).bind(tempItem);
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public Movie item;
        public Context mcontext;
        public TextView tvMovieTitle;
        private <%= listName %>Adapter movieSearchAdapter;
        public ImageView imPoster;
        public MovieViewHolder(View itemLayoutView,Context context,<%= listName %>Adapter movieSearchAdapter) {
            super(itemLayoutView);
            this.mcontext = context;
            tvMovieTitle = (TextView) itemLayoutView.findViewById(R.id.tvMovieTitle);
            this.movieSearchAdapter = movieSearchAdapter;
            imPoster = (ImageView) itemLayoutView.findViewById(R.id.imPoster);
            itemLayoutView.setOnClickListener(this);

        }

        public void bind(Movie item)
        {
            tvMovieTitle.setText(item.title);
            Picasso.with(mcontext)
                    .load(item.poster)
                    .into(imPoster);
        }


        @Override
        public void onClick(View v) {
            if(movieSearchAdapter.itemClickListener!=null)
            {
                movieSearchAdapter.itemClickListener.ItemClicked(getAdapterPosition(),
                        movieSearchAdapter.itemsData.get(getAdapterPosition()));
            }

        }
    }

    @Override
    public int getItemCount() {
        return itemsData.size();
    }
}
