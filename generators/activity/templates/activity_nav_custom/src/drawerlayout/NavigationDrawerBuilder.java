package <%= appPackage %>.features<%= actvitiyPackageName %>.drawerlayout;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import <%= appPackage %>.R;

/**
 * Created by abbas on 10/22/16.
 */

public class NavigationDrawerBuilder {
    public static List<NavigationItem> buildList(Context context)
    {
        List<NavigationItem> mList = new ArrayList<NavigationItem>();


        NavigationItem tempNav = new NavigationItem(context.getResources().getString(R.string.menu_home)
                ,"home", NavigationItem.HEADER,1,0);
        mList.add(tempNav);

        tempNav = new NavigationItem("","sep", NavigationItem.SEPRATOR,1,1);
        mList.add(tempNav);

//
//        tempNav = new NavigationItem("حدول لیگ های معتبر"
//                ,"leages", NavigationItem.HEADER,2,1);
//        mList.add(tempNav);
//
//        tempNav = new NavigationItem("","sep", NavigationItem.SEPRATOR,2,2);
//        mList.add(tempNav);




        tempNav = new NavigationItem(context.getResources().getString(R.string.menu_newspapers)
                ,"newspapers", NavigationItem.HEADER,4,0);
        mList.add(tempNav);


        tempNav = new NavigationItem("","sep", NavigationItem.SEPRATOR,4,1);
        mList.add(tempNav);


        tempNav = new NavigationItem(context.getResources().getString(R.string.menu_about)
                ,"about", NavigationItem.HEADER,8,0);
        mList.add(tempNav);


        tempNav = new NavigationItem("","sep", NavigationItem.SEPRATOR,8,1);
        mList.add(tempNav);


        tempNav= new NavigationItem("خبرها"
                ,"adv_galery", NavigationItem.HEADER,15,0);
        tempNav.haveSubItem=true;
        mList.add(tempNav);

        tempNav= new NavigationItem("داخلی"
                ,"iran_news", NavigationItem.NORMAL,15,5);
        tempNav.setTag(false);
        tempNav.setIdentifier("iran");
        mList.add(tempNav);


        tempNav= new NavigationItem("خارجی"
                ,"foriegn_news", NavigationItem.NORMAL,15,10);
        tempNav.setTag(false);
        tempNav.setIdentifier("england");
        mList.add(tempNav);
        tempNav= new NavigationItem("غیر فوتبالی"
                ,"other_news", NavigationItem.NORMAL,15,10);
        tempNav.setTag(false);
        tempNav.setIdentifier("england");
        mList.add(tempNav);

        tempNav = new NavigationItem("","sep", NavigationItem.SEPRATOR,15,99);
        mList.add(tempNav);


        tempNav= new NavigationItem(context.getResources().getString(R.string.cat_header)
                ,"adv_galery", NavigationItem.HEADER,20,0);
        tempNav.haveSubItem=true;
        mList.add(tempNav);

        tempNav= new NavigationItem("ایران"
                ,"iran", NavigationItem.NORMAL,20,5);
        tempNav.setTag(false);
        tempNav.setIdentifier("iran");
        mList.add(tempNav);


        tempNav= new NavigationItem("انگلیس"
                ,"england", NavigationItem.NORMAL,20,10);
        tempNav.setTag(false);
        tempNav.setIdentifier("england");
        mList.add(tempNav);


        tempNav= new NavigationItem("اسپانیا"
                ,"spain", NavigationItem.NORMAL,20,15);
        tempNav.setTag(false);
        tempNav.setIdentifier("spain");
        mList.add(tempNav);


        tempNav= new NavigationItem("آلمان"
                ,"germany", NavigationItem.NORMAL,20,20);
        tempNav.setTag(false);
        tempNav.setIdentifier("germany");
        mList.add(tempNav);

        tempNav = new NavigationItem("","sep", NavigationItem.SEPRATOR,20,99);
        mList.add(tempNav);


        tempNav= new NavigationItem(context.getResources().getString(R.string.tag_header)
                ,"adv_galery", NavigationItem.HEADER,25,0);
        tempNav.haveSubItem=true;

        mList.add(tempNav);

        tempNav= new NavigationItem("کلیپ های ثانیه ای"
                ,"secondClip", NavigationItem.NORMAL,25,5);
        tempNav.setTag(true);
        tempNav.setIdentifier("933349");
        mList.add(tempNav);
        tempNav= new NavigationItem(context.getResources().getString(R.string.menu_perspolis)
                ,"perspolis", NavigationItem.NORMAL,25,10);
        tempNav.setTag(true);
        tempNav.setIdentifier("18");
        mList.add(tempNav);

        tempNav= new NavigationItem(context.getResources().getString(R.string.menu_esteghlal)
                ,"esteghlal", NavigationItem.NORMAL,25,15);
        tempNav.setTag(true);
        tempNav.setIdentifier("20");
        mList.add(tempNav);

        tempNav= new NavigationItem("تراکتورسازی"
                ,"tractorsazi", NavigationItem.NORMAL,25,20);
        tempNav.setTag(true);
        tempNav.setIdentifier("8");
        mList.add(tempNav);

        tempNav = new NavigationItem("","sep", NavigationItem.SEPRATOR,25,99);
        mList.add(tempNav);

        return mList;
    }
}
