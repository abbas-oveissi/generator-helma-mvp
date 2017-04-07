package <%= appPackage %>.features.category;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import <%= appPackage %>.R;
import <%= appPackage %>.pojo.Category;

/**
 * Created by abbas on 7/7/16.
 */
public class CategoryAdapter extends RecyclerView.Adapter {

    Context context;
    public List<Category> mlist;

    public CategoryAdapter(Context context, List<Category> mlist) {
        this.context = context;
        this.mlist=mlist==null?new ArrayList<Category>():mlist;
    }

    public interface OnItemClickListener
    {
        public void onItemClick(Category video);
    }
    public OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
    private OnItemClickListener onItemClickListener;


    @Override
    public int getItemCount() {
        return mlist.size();
    }


    public void addItem(Category v)
    {
        mlist.add(v);
        notifyItemInserted(mlist.size()-1);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_video, parent, false);
        return new Holder(v,this);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ((Holder)holder).bind();

    }

    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvName;
        ImageView imThumbnail;
        View item;
        private CategoryAdapter parent;

        public Holder(View itemView,CategoryAdapter parent) {
            super(itemView);
            this.parent = parent;
            this.imThumbnail=(ImageView)itemView.findViewById(R.id.imVideoThumbnail);
            this.tvName=(TextView)itemView.findViewById(R.id.tvName);
            this.item=itemView;
        }


        public void bind()
        {
            Category mv=parent.mlist.get(getAdapterPosition());

//            if(mv.getmTitle()!=null)
//                tvName.setText(mv.getmTitle());
//
//            if(mv.getmThumbnail()!=null)
//                Picasso.with(parent.context).load(mv.getmThumbnail()).into(imThumbnail);

            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            if(parent.getOnItemClickListener()!=null)
                parent.getOnItemClickListener().onItemClick(parent.mlist.get(getAdapterPosition()));
        }
    }
}
