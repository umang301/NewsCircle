package com.protoplus.newscircle;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.protoplus.newscircle.Bean.SportChildSceneBean;
import com.protoplus.newscircle.Util.Helper;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Aakash on 10/28/2015.
 */
public class MyAlarmService extends Service {
    int Notification_id;
    MediaPlayer mp;
    Bitmap icon;
    int web_index, cat_index;
    SportChildSceneBean temp_bean;
    Context context = this;
    NotificationManager notificationManager;
    Notification.Builder mBuilder;
    SharedPreferences mPrefs;

    private PendingIntent getNotificationPendingIntent(SportChildSceneBean sport_bean) {
        Intent intent = new Intent(getApplicationContext(), SplashActivity.class);
        System.out.println("..............>In pending Intent the intent is : " + intent + ".....> and bean obj is " + sport_bean);
        intent.putExtra("ItemObject", sport_bean);
        intent.putExtra("NotificationFlag", true);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent notifcationPendingIntent = PendingIntent.getActivity(getApplicationContext(), Notification_id, intent, PendingIntent.FLAG_ONE_SHOT);
        return notifcationPendingIntent;
    }

    public void getNotifications() {

        Helper.WriteHeadLineData(mPrefs, web_index, cat_index, temp_bean.getImageLink() + "&&&&" + temp_bean.getTitle());

        notificationManager = (NotificationManager) this.getSystemService(this.getApplicationContext().NOTIFICATION_SERVICE);
        mBuilder = new Notification.Builder(this);
        mBuilder.setSmallIcon(R.drawable.ic_white_notif_icon);
        mBuilder.setAutoCancel(true);
        new LoadImageFromURL().execute(temp_bean);

    }

    private class LoadImageFromURL extends AsyncTask<SportChildSceneBean, String, String> {
        SportChildSceneBean sport_bean;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(SportChildSceneBean... params) {
            try {
                icon = BitmapFactory.decodeResource(getResources(), R.drawable.default_banner);
                sport_bean = params[0];
                if (sport_bean.mainImageLink == null && sport_bean.imageLink == null) {
                    mBuilder.setStyle(new Notification.BigPictureStyle(mBuilder).bigPicture(icon));
                } else {
                    if (sport_bean.imageLink == null) {
                        sport_bean.imageLink = sport_bean.mainImageLink;
                    }
                    System.out.println("__________________Hear I am Setting Image Link that is : " + params[0].getImageLink());
                    Notification.BigPictureStyle bigPictureStyle = new Notification.BigPictureStyle(mBuilder);
                    bigPictureStyle.bigPicture(BitmapFactory.decodeStream((InputStream) new URL(params[0].getImageLink()).getContent()));
                    bigPictureStyle.setSummaryText(sport_bean.getTitle());
                    mBuilder.setStyle(bigPictureStyle);
                    mBuilder.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));

                }
            } catch (Exception urlex) {
                Notification.BigPictureStyle bigPictureStyle = new Notification.BigPictureStyle(mBuilder);
                bigPictureStyle.bigPicture(icon);
                bigPictureStyle.setSummaryText(sport_bean.getTitle());
                mBuilder.setStyle(bigPictureStyle);
            }
            return "";
        }

        @Override
        protected void onPostExecute(String string) {
            super.onPostExecute(string);
            mBuilder.setContentTitle(sport_bean.getCategory());
            mBuilder.setContentText(sport_bean.getTitle());
            int currentapiVersion = android.os.Build.VERSION.SDK_INT;
            if (currentapiVersion >= android.os.Build.VERSION_CODES.LOLLIPOP){
                mBuilder.setColor(0xff0066cc);
            }
            mBuilder.setContentIntent(getNotificationPendingIntent(sport_bean));
            notificationManager.notify(Notification_id++, mBuilder.build());
            Helper.setNotificationCounter(mPrefs, Notification_id);
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {


        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @SuppressWarnings("static-access")
    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        try {
            mPrefs = getSharedPreferences(Helper.MyPREFERENCES, MODE_PRIVATE);
            System.out.println("..............The Sharedpreferences is " + mPrefs);
            temp_bean = (SportChildSceneBean) intent.getSerializableExtra("NotificationData");
            Bundle bundle = intent.getExtras();
            web_index = bundle.getInt("web_index");
            cat_index = bundle.getInt("cat_index");
            System.out.println("_____________The Temp bean is ::::: " + temp_bean);
            if (temp_bean != null) {
                System.out.println("...........line 120..>THe temp bean is : " + temp_bean);
                Notification_id = Helper.getNotificationCounter(mPrefs);
                getNotifications();
            }

        } catch (Exception e) {
            Log.d("AlarmServiceError", "The error occured in alarm service ");
            e.printStackTrace();
        }


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
