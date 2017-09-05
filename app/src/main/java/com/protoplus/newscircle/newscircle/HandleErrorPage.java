package com.protoplus.newscircle.newscircle;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.protoplus.newscircle.R;
import com.protoplus.newscircle.SplashActivity;
import com.protoplus.newscircle.Util.Helper;

import org.w3c.dom.Text;

public class HandleErrorPage extends AppCompatActivity {

    @Override
    protected void onRestart() {
        Helper.setSubActivityUI(this);
        super.onRestart();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Helper.setSubActivityUI(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handle_error_page);
        TextView tv = (TextView) findViewById(R.id.ErrorTextstr);
        Typeface tf = Typeface.createFromAsset(getAssets(), Helper.LatoBlack);
        tv.setTypeface(tf);
        AppCompatButton retry = (AppCompatButton) findViewById(R.id.Retry);
        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(HandleErrorPage.this, SplashActivity.class);
                startActivity(it);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_handle_error_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
