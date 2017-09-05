package com.protoplus.newscircle.Util;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.protoplus.newscircle.Bean.SportChildSceneBean;
import com.protoplus.newscircle.ContextData;
import com.protoplus.newscircle.DrawerElements.activity.HeadLine.HeadLines;
import com.protoplus.newscircle.DrawerElements.activity.NewsPaperList.AsiaAgeNewsFragment;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Aakash on 10/31/2015.
 */
public class AlarmReceiver extends BroadcastReceiver{
    SharedPreferences mPrefs;
    IGetNotification iGetNotification;
    Intent intent;
    Context context;
    Boolean stop = false;

    PendingIntent broadcastPendingIntent;
    private void getBroadcastPandingIntent() {
        intent = new Intent(context, AlarmReceiver.class);
        broadcastPendingIntent = PendingIntent.getBroadcast(context, 234324243, intent, 0);
    }
    public void startAlert() {
        //System.out.println("_______________________THE START ALERT METHOD IS CALLED");
        int i = 5;
        int interval = 1000 * 60;
        getBroadcastPandingIntent();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + (i * 1000), broadcastPendingIntent);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                interval, broadcastPendingIntent);
    }
    public void restartAlert() {
        //System.out.println("_______________________THE STOP ALERT METHOD IS CALLED");
        getBroadcastPandingIntent();
        /*AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);*/
        boolean alarmUp = (PendingIntent.getBroadcast(context, 234324243,
                intent,
                PendingIntent.FLAG_NO_CREATE) != null);

        if (alarmUp)
        {
            Log.d("myTag", "____________________________Alarm is already active");
        }else{
            Log.d("myTag1", "____________________________Alarm is not active");
            startAlert();
        }
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        Helper.selected_item = "NewsTopics";
        if(!Helper.isAppOpen){
            //System.out.println("______ Alarm Receiver _______");
            Helper.fromReceiver = true;
            this.context = context;
            //restartAlert();
            Helper helper = Helper.getHelperInstance();
            Class classInstance = Helper.class;
            Field urlVariable;
            iGetNotification = HeadLines.getHeadLineInstance();
            iGetNotification.getNotificationData(context,"",0,"Headline");
            mPrefs = context.getSharedPreferences(Helper.MyPREFERENCES, context.MODE_PRIVATE);
            try{
                ArrayList<String> favSiteList = Helper.getFavSiteAr(mPrefs);
                //System.out.println("_________THE FAVORITE SITE LIST IS ________________ "+favSiteList+" ..Size is : "+favSiteList.size());

                if(favSiteList!=null){
                    for (String siteName: favSiteList) {
                        //System.out.println("___THE SITE NAMES ARE _____ " + siteName);
                        String category = siteName.split("#")[0];
                        String DeccanSite = siteName.split("#")[0];

                        int cat_index = Helper.Category_ar_list.indexOf(category);
                        if(category.contains("LifeStyle") || category.contains("Science")){
                            category = category.split(" ")[0];
                        }
                        if(category.contains(" News")){
                            //System.out.println(">>>>>>>>>>    The Category is  ::: "+category);
                            category=category.replace(" News","");
                        }

                        //System.out.println("**************the category index of category : "+category+" :_: "+cat_index+"" +
                                //"************************************");

                        category = category.replace(" ","");

                        String website = siteName.split("#")[1].replace(" ","").replace("-","_");
                        //System.out.println(" Site _____ "+website + " category __________"+category);
                        if(category.equals("National") && website.equals("IndianExpress")){
                            stop = true;
                        }
                        //Hear i am trying to set URL String of Helper using Website and category
                        String curUrlStr;
                        if(website.equals(Helper.Firstpost) || website.equals(Helper.IBNLive)){
                            curUrlStr = category;
                        }
                        else if(website.equals(Helper.DeccanHerald)){
                            curUrlStr = ""+Helper.getDeccanHeraldID(DeccanSite);
                        }
                        else{
                            urlVariable = classInstance.getDeclaredField(website+category+"URL");
                            //System.out.println("...............>>>> The url variable is : " + urlVariable);
                            curUrlStr = ((String)urlVariable.get(helper)).split(",")[0];

                        }

                        Helper.fromReceiver = true;
                        String className = "com.protoplus.newscircle.DrawerElements.activity.NewsPaperList."+website+"NewsFragment";

                        if(className.contains("NewsNews")){className=className.replace("NewsNews","News");}

                         if(stop){
                             stop = false;
                         }
                        else{
                             iGetNotification = (IGetNotification)Class.forName(className).newInstance();
                             System.out.println(" _______## __## ____ The Category is : "+DeccanSite);
                             iGetNotification.getNotificationData(context,curUrlStr,cat_index,DeccanSite);
                         }

                    }
                }
                else{
                    //System.out.println("_____THE SITE LIST IS NULL");
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }


    }
}
