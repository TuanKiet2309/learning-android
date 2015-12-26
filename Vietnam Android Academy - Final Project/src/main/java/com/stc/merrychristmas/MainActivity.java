package com.stc.merrychristmas;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import static android.widget.RelativeLayout.*;


public class MainActivity extends Activity implements View.OnClickListener{


    TextView mTextView1, mTextView2,mTextViewC ;
    ImageView mImageViewB,mImageView1 , mImageView2, mImageView3;
    MediaPlayer mediaPlayer;
    CountDownTimer countDown ;
    private static final String FORMAT = "%03d Days %02d:%02d:%02d";
    long milis1;
    long milis2;
    long milis3;

    Calendar today = Calendar.getInstance();
    int year = today.get(Calendar.YEAR);
    Calendar christmasDay = Calendar.getInstance();
    Calendar tetDay = Calendar.getInstance();

    Animation animation1 = null;
    int flag;
    Boolean finish;
    Typeface mface = null;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView1 = (TextView) findViewById(R.id.tv_MerryChrismas);
        mTextView2 = (TextView) findViewById(R.id.tv_Countdown);
        mTextViewC = (TextView) findViewById(R.id.tv_change);

        mImageViewB = (ImageView) findViewById(R.id.im_background);
        mImageView1 = (ImageView) findViewById(R.id.im_amination);
        mImageView2 = (ImageView) findViewById(R.id.im_phaohoa1);
        mImageView3 = (ImageView) findViewById(R.id.im_phaohoa2);


       // mImageView2.setColorFilter();
        mTextView1.setOnClickListener(this);
        mTextViewC.setOnClickListener(this);

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(ALIGN_PARENT_BOTTOM, mTextView2.getId());
        mImageView1.setLayoutParams(layoutParams);

        christmasDay.set(year, 11, 25, 0, 0, 0);
        tetDay.set(year, 0, 1, 0, 0, 0);
        milis2 = today.getTimeInMillis();
        //Merry Christmas
        if(today.compareTo(tetDay)==1 && today.compareTo(christmasDay)==-1){
            themeChiristmas();
            milis1 = christmasDay.getTimeInMillis();
            flag=1;
        }
        //Happy New Year
        else{
            themeNewYear();
            tetDay.set(year + 1, 0, 1, 0, 0, 0);
            milis1 = tetDay.getTimeInMillis();
            flag=2;
        }


        milis3= milis1 - milis2;

        TranslateAnim(milis3);
        CountDown(milis3);


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }
    private void themeNewYear(){
        int year1=year+1;
        mTextViewC.setText("Merry Christmas");
        mTextView1.setText("Happy New Year " +year1 );
        mface = Typeface.createFromAsset(getAssets(), "FernandoForero Asfalto.otf");
        mImageViewB.setImageResource(R.drawable.backgroundnewyear);
        mTextView2.setTextColor(Color.parseColor("#7FFF00"));
        mImageView1.setImageResource(R.drawable.muala);
        mediaPlayer = MediaPlayer.create(this, R.raw.happynewyear);
        mTextView1.setTypeface(mface);
        mTextView2.setTypeface(mface);
    }
    private void themeChiristmas(){
        mTextViewC.setText("Happy New Year");
        mTextView1.setText("Merry Christmas");
        mface = Typeface.createFromAsset(getAssets(), "UTMEdwardianKT.ttf");
        mImageViewB.setImageResource(R.drawable.backgroundnoel);
        mImageView1.setImageResource(R.drawable.ongnoel);
        mTextView2.setTextColor(Color.parseColor("#000000"));
        mediaPlayer = MediaPlayer.create(this, R.raw.jinglebell);
        mTextView1.setTypeface(mface);
        mTextView2.setTypeface(mface);
    }

    public void TranslateAnim(long milis){
        final TranslateAnimation translateAnimation =
                new TranslateAnimation(
                        Animation.ABSOLUTE, 0, Animation.RELATIVE_TO_PARENT, 1,
                        Animation.ABSOLUTE, 0, Animation.ABSOLUTE, 0);
        translateAnimation.setDuration(milis);
        translateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onAnimationEnd(Animation animation) {
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL, mTextView1.getId());
                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
                mImageView1.setLayoutParams(layoutParams);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        mImageView1.startAnimation(translateAnimation);

    }
    public void CountDown(long milis){

        countDown = new CountDownTimer(milis, 1000) {
            @TargetApi(Build.VERSION_CODES.GINGERBREAD)
            public void onTick(long millisUntilFinished) {
                finish=true;

                mTextView2.setText("" + String.format(FORMAT,
                        TimeUnit.MILLISECONDS.toDays(millisUntilFinished),
                        TimeUnit.MILLISECONDS.toHours(millisUntilFinished) - TimeUnit.DAYS.toHours(
                                TimeUnit.MILLISECONDS.toDays(millisUntilFinished)),
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(
                                TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                                TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
                if(TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished)==20&&flag==2){
                    mediaPlayer.start();
                }

            }

            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            public void onFinish() {
                finish=false;
                if(flag==1)
                    mediaPlayer.start();

                mTextView2.setText("");
                animation1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom);
                animation1.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        mImageView2.startAnimation(animation1);
                        mImageView3.startAnimation(animation1);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                //mImageView.startAnimation(animation1);

                mImageView2.startAnimation(animation1);

                mImageView3.startAnimation(animation1);





            }
        }.start();

    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.stc.merrychristmas/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.stc.merrychristmas/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        mediaPlayer.stop();
        client.disconnect();
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onClick(final View v) {
        switch (v.getId()){
            case R.id.tv_MerryChrismas:
                if(!mediaPlayer.isPlaying()) {
                    milis3 = 25000;
                    countDown.cancel();
                    CountDown(milis3);
                    TranslateAnim(milis3);
                }
                break;
            case R.id.tv_change:
                countDown.cancel();
                if(mediaPlayer.isPlaying()) {
                    if(!finish)animation1.setAnimationListener(null);
                    mImageView2.setVisibility(View.INVISIBLE);
                    mImageView3.setVisibility(View.INVISIBLE);
                    mediaPlayer.stop();
                }



                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                layoutParams.addRule(ALIGN_PARENT_BOTTOM, mTextView2.getId());
                mImageView1.setLayoutParams(layoutParams);

                if(flag==1){
                    themeNewYear();
                    today = Calendar.getInstance();
                    milis2 = today.getTimeInMillis();
                    year = today.get(Calendar.YEAR);
                    tetDay.set(year + 1, 0, 1, 0, 0, 0);
                    milis1 = tetDay.getTimeInMillis();
                    milis3=milis1-milis2;
                    CountDown(milis3);
                    TranslateAnim(milis3);
                    flag=2;
                }
                else {
                    themeChiristmas();
                    today = Calendar.getInstance();
                    milis2 = today.getTimeInMillis();
                    year = today.get(Calendar.YEAR);
                    tetDay.set(year, 0, 1, 0, 0, 0);
                    christmasDay.set(year, 11, 25, 0, 0, 0);
                    if(today.compareTo(tetDay)==1 && today.compareTo(christmasDay)==-1){
                        milis1 = christmasDay.getTimeInMillis();
                    }
                    else
                    {
                        christmasDay.set(year+1, 11, 25, 0, 0, 0);
                        milis1 = christmasDay.getTimeInMillis();
                    }

                    milis3=milis1-milis2;
                    CountDown(milis3);
                    TranslateAnim(milis3);
                    flag=1;
                }
                break;

        }



    }
}

