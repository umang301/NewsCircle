package com.protoplus.newscircle.DrawerElements.adapter;

/**
 * Created by Aakash on 29/07/15.
 */

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.protoplus.newscircle.DrawerElements.model.NavDrawerItem;
import com.protoplus.newscircle.R;
import com.protoplus.newscircle.Util.AlarmReceiver;
import com.protoplus.newscircle.Util.Helper;

import java.util.Calendar;
import java.util.Collections;
import java.util.List;


public class NavigationDrawerAdapter extends RecyclerView.Adapter<NavigationDrawerAdapter.MyViewHolder> {
    List<NavDrawerItem> data = Collections.emptyList();
    private LayoutInflater inflater;
    Typeface tf;
    public static int selected_item = 0;
    private Context context;
    SharedPreferences mPrefs;
    Boolean notification_status ;

    int viewType;
    PendingIntent broadcastPendingIntent;

    private void getBroadcastPandingIntent() {
        Intent intent = new Intent(context.getApplicationContext(), AlarmReceiver.class);
        broadcastPendingIntent = PendingIntent.getBroadcast(context.getApplicationContext(), 234324243, intent, 0);
    }
    public void stopAlert() {
        System.out.println("_______________________THE STOP ALERT METHOD IS CALLED");
        getBroadcastPandingIntent();
        AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        manager.cancel(broadcastPendingIntent);
    }

    public void startAlert() {
        System.out.println("_______________________THE START ALERT METHOD IS CALLED");
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
    public NavigationDrawerAdapter(Context context, List<NavDrawerItem> data) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;
        tf = Typeface.createFromAsset(context.getAssets(), Helper.LatoBlack);
        mPrefs = context.getSharedPreferences(Helper.MyPREFERENCES, Context.MODE_PRIVATE);
        notification_status = Helper.getNotificationStatus(mPrefs);
    }

    public void delete(int position) {
        data.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.nav_drawer_row, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        this.viewType = viewType;
        return holder;
    }

    @Override
    public int getItemViewType(int position) {
        switch (position){
            case 0:return 0;
            case 1:return 0;
            case 2:return 2;
            case 3:return 1;
            case 4:return 0;
            case 5:return 0;
            case 6:return 0;
        }
        return 0;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        NavDrawerItem current = data.get(position);
        holder.title.setText(current.getTitle());
        holder.item_icon.setImageResource(current.getImageId());
        if(viewType == 1){
            holder.relativeLayout.setBackgroundColor(Color.rgb(112, 169, 226));
            RelativeLayout.LayoutParams params= new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.MATCH_PARENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT
            );


            holder.relativeLayout.setLayoutParams(params);
            holder.drawerItemArrow.setVisibility(View.GONE);
            holder.item_icon.setVisibility(View.GONE);
            /*holder.title.setTextSize(context.getResources().getDimension(R.dimen.dp5));*/
            RelativeLayout.LayoutParams txt_params = (RelativeLayout.LayoutParams)holder.title.getLayoutParams();
            txt_params.setMargins(20, 0, 0, 0);
            if(android.os.Build.VERSION.SDK_INT> Build.VERSION_CODES.JELLY_BEAN){
                txt_params.setLayoutDirection(RelativeLayout.CENTER_IN_PARENT);
            }
            holder.title.setLayoutParams(txt_params);
            holder.underline.setVisibility(View.GONE);
        }
        if(viewType == 2){
            holder.drawerItemArrow.setVisibility(View.GONE);
            holder.underline.setVisibility(View.GONE);
            holder.notification_switch.setVisibility(View.VISIBLE);
            if(notification_status){
                holder.notification_switch.setBackground(context.getResources().getDrawable(R.drawable.toggle_on));
                holder.notification_switch.setTag("on");
            }else{
                holder.notification_switch.setBackground(context.getResources().getDrawable(R.drawable.toggle_off));
                holder.notification_switch.setTag("off");
            }
            holder.notification_switch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(v.getTag().equals("on")){
                        Helper.setNotificationStatus(mPrefs, false);
                        stopAlert();
                        holder.notification_switch.setBackground(context.getResources().getDrawable(R.drawable.toggle_off));
                        holder.notification_switch.setTag("off");
                    }
                    else{
                        Helper.setNotificationStatus(mPrefs, true);
                        startAlert();
                        holder.notification_switch.setBackground(context.getResources().getDrawable(R.drawable.toggle_on));
                        holder.notification_switch.setTag("on");
                    }
                }
            });
        }
        if(position == selected_item)
        {
            holder.title.setTextColor(Color.rgb(0,108,171));
            holder.item_icon.setColorFilter(Color.rgb(0,108,171));
        }
        else
        {
            holder.title.setTextColor(Color.BLACK);
            holder.item_icon.setColorFilter(Color.BLACK);
        }

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder  {
        TextView title;
        ImageView item_icon;
        RelativeLayout relativeLayout;
        ImageView drawerItemArrow;
        LinearLayout underline;
        Button notification_switch;
        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            title.setTypeface(tf);
            item_icon = (ImageView) itemView.findViewById(R.id.item_icon);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.background);
            drawerItemArrow = (ImageView) itemView.findViewById(R.id.drawer_item_arrow);
            underline = (LinearLayout) itemView.findViewById(R.id.underline);
            notification_switch = (Button) itemView.findViewById(R.id.notification_switch);
        }

    }
}
