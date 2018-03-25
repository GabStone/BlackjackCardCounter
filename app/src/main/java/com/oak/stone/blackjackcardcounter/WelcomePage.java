package com.oak.stone.blackjackcardcounter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;


public class WelcomePage extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_welcome_page);

        final ImageView casinoChip = (ImageView) findViewById(R.id.imageView2);
        final Animation animation = AnimationUtils.loadAnimation(getBaseContext(), R.anim.rotate);
        casinoChip.startAnimation(animation);
    }

    public void spin_chip(View view) {
        final ImageView casinoChip = (ImageView) findViewById(R.id.imageView2);
        final Animation animation = AnimationUtils.loadAnimation(getBaseContext(), R.anim.rotate);
        casinoChip.startAnimation(animation);
    }

    public void help_page(View view) {
        Intent intent = new Intent(this, HelpPage.class);
        startActivity(intent);
    }

    public void startCounting(View view) {
        Intent intent = new Intent(this, CountingPage.class);
        startActivity(intent);
    }

    public void how_to_page(View view) {
        Intent intent = new Intent(this, HowToPage.class);
        startActivity(intent);
    }

    public void aboutPage(View view) {
        Intent intent = new Intent(this, AboutPage.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_welcome_page, menu);
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
