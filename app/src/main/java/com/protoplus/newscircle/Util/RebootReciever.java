package com.protoplus.newscircle.Util;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.util.Calendar;

/**
 * Created by Aakash on 11/21/2015.
 */
public class RebootReciever extends BroadcastReceiver {
    Intent intent;
    Context context;
    PendingIntent broadcastPendingIntent;
    private void getBroadcastPandingIntent() {
        intent = new Intent(context, AlarmReceiver.class);
        broadcastPendingIntent = PendingIntent.getBroadcast(context, 234324243, intent, 0);
    }
    public void startAlert() {
        System.out.println("_______________________THE START ALERT METHOD IS CALLED AFTER REBOOTING SYSTEM_____________________");
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
    @Override
    public void onReceive(Context context, Intent intent) {
        this.context = context;
        startAlert();
    }
}
