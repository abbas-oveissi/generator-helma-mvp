package <%= appPackage %>.features<%= actvitiyPackageName %>.drawerlayout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import <%= appPackage %>.R;


public class NavigationAdapter extends BaseAdapter {
    private final Context mcontext;
    public final List<NavigationItem> mList;
    public NavigationAdapter(Context context,List<NavigationItem> list) {
        this.mList = list;
        this.mcontext = context;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public NavigationItem getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return 3;
    }

    @Override
    public int getItemViewType(int position) {
        return getItem(position).type;
    }

    @Override
    public boolean isEnabled(int position) {
        return !(getItem(position).haveSubItem);
    }


    public void setSelectedItem(int pos)
    {

        for(NavigationItem ni:mList)
        {
            ni.isSelected=false;
        }

        mList.get(pos).isSelected=true;
        notifyDataSetChanged();
    }

    class ViewHolder {
        public TextView title;
        public ImageView icon;
        public View viewNavigation;
        public ViewHolder(){
        }
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        NavigationItem item = mList.get(position);
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            int layout=-1;
            switch (item.type)
            {
                case NavigationItem.HEADER:
                    layout= R.layout.drawer_header;
                    break;
                case NavigationItem.NORMAL:
                    layout= R.layout.drawer_item;
                    break;
                case NavigationItem.SEPRATOR:
                    layout=R.layout.drawer_seprator;
                    break;
            }
            convertView = LayoutInflater.from(mcontext).inflate(layout, null);
            holder.title = (TextView) convertView.findViewById(R.id.title);
            holder.icon = (ImageView) convertView.findViewById(R.id.icon);
            holder.viewNavigation = convertView;
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        if(item.type== NavigationItem.SEPRATOR) {
            return convertView;
        }


        if (holder.title != null){


            if(item.haveSubItem)
            {
                holder.title.setTextColor(mcontext.getResources().getColor(R.color.nav_drawer_have_sub_text));
            }
            else
            {
                holder.title.setTextColor(mcontext.getResources().getColor(R.color.nav_drawer_normal_text));
            }


            holder.title.setText(item.title);

            if(item.isSelected)
                holder.title.setTextColor(mcontext.getResources().getColor(R.color.nav_drawer_selected_text));



        }




        return convertView;
    }

}
