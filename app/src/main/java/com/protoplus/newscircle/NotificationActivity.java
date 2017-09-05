package com.protoplus.newscircle;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.protoplus.newscircle.Util.Helper;

public class NotificationActivity extends AppCompatActivity {
    int Notification_id = 001;
    NotificationManager notificationManager;
    Boolean DestroyHeadLine =false;
    PendingIntent notifcationPendingIntent;
    private void getNotificationPendingIntent(){
        Intent intent = new Intent(getApplicationContext(), SplashActivity.class);
        notifcationPendingIntent = PendingIntent.getActivity(getApplicationContext(),0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        //DestroyHeadLine = Helper.HeadLineDestroyed.contains("true"0)?true:false;
        DestroyHeadLine =true;
        //onCreate(new Bundle());
        //onStart();
        //try{
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Notification.Builder mBuilder = new Notification.Builder(this);
        mBuilder.setSmallIcon(R.mipmap.ic_launcher);
        mBuilder.setContentTitle("My first notication ");
        String HeadlineText = "";

        mBuilder.setContentText(HeadlineText.substring(HeadlineText.indexOf("="),HeadlineText.length()-1));
        mBuilder.setAutoCancel(true);
        mBuilder.setPriority(Notification.PRIORITY_MAX);
        getNotificationPendingIntent();
        mBuilder.setContentIntent(notifcationPendingIntent);
        notificationManager.notify(Notification_id, mBuilder.build());
        //}catch(Exception e){e.printStackTrace();}


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_notification, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
