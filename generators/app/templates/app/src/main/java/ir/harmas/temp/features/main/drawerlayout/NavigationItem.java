package <%= appPackage %>.features.main.drawerlayout;

/**
 * Created by Abbas on 26/01/2016.
 */
public class NavigationItem {

    public boolean isSelected=false;
    public static final int SEPRATOR=0;
    public static final int HEADER=1;
    public static final int NORMAL=2;
    public static final int NORMAL_SELECTABLE=3;
    public int type=2;
    public String title;
    public String enTitle;
    public int priority=-1;

    public boolean haveSubItem=false;

    private boolean isTag=false;
    private String identifier;

    public NavigationItem(String title, String enTitle, int type,int mainPriority,int secondPriority) {
        this.title = title;
        this.enTitle=enTitle;
        this.type=type;
        this.priority = (mainPriority*10000)+(secondPriority*100);
    }



    public boolean isTag()
    {
        return isTag;
    }

    public boolean isCategory()
    {
        return !isTag;
    }

    public void setTag(boolean enable){
        isTag=enable;
    }

    public void setCategory(boolean enable){
        isTag=!enable;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }
}
