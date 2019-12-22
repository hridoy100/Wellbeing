package com.example.healthapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RecyclerViewAdapter.OnItemClickListener  {

    static final String TAG = "MainActivity";

    ArrayList<String> mNames = new ArrayList<>();
    ArrayList<String> mImageUrls = new ArrayList<>();

    RecyclerView bankJobView;

    AlertDialog.Builder builder;
    Intent intent;

    public ProgressDialog progressDialog;


    private boolean haveNetworkConnection() {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bankJobView = (RecyclerView) findViewById(R.id.recycleView_single);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");

        builder = new AlertDialog.Builder(this);
        builder.setTitle("ইন্টারনেট অফ");
        builder.setMessage("এপটি ব্যাবহারের জন্য ইন্টারনেট অন করুন");
        builder.setCancelable(false);

        builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                SystemClock.sleep(3000);
                startActivity(intent);
            }
        });

        builder.setNeutralButton("Turn On", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Thread t = new Thread() {
                    @Override
                    public void run() {
                        try {
                            //check if connected!
                            while (!isConnected(MainActivity.this)) {
                                //Wait to connect
                                Thread.sleep(500);
                            }
                        } catch (Exception e) {
                        }
                    }
                };

                t.start();
                //progressDialog.show();
//                SystemClock.sleep(2000);
                //progressDialog.dismiss();
//                Intent mStartActivity = new Intent(MainActivity.this, WebViewPage.class);
//                int mPendingIntentId = 123456;
//                PendingIntent mPendingIntent = PendingIntent.getActivity(MainActivity.this, mPendingIntentId, mStartActivity,
//                        PendingIntent.FLAG_CANCEL_CURRENT);
//                AlarmManager mgr = (AlarmManager) MainActivity.this.getSystemService(Context.ALARM_SERVICE);
//                mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 2000, mPendingIntent);
//                System.exit(0);

                return;
            }
        });

        /*if(!haveNetworkConnection()){
            builder.show();
        }else*/
        {
            initImageBitmaps();
        }
    }

    public static boolean isConnected(Context context) {
        WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        wifi.setWifiEnabled(true);
//        ConnectivityManager connectivityManager = (ConnectivityManager)
//                context.getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo networkInfo = null;
//        if (connectivityManager != null) {
//            networkInfo = connectivityManager.getActiveNetworkInfo();
//        }
        if (wifi==null) return false;
        if (wifi.getWifiState()!=wifi.WIFI_STATE_ENABLED) return false;
        return true;
    }

    void initImageBitmaps() {
        //mImageUrls.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTLCINLcwcG0xWtc73CfeEjnOM0oi_yRG9BTmMjQf60DljywHYD");

        mImageUrls.add("https://lh3.googleusercontent.com/i587cPKf15RK3GGQ5aKqLascEW5fvNgCuSxbbFqYc1DDZtiXzPX-1Xsf5KXsQbc03A=s180");
        mNames.add("শিশু স্বাস্থ্য");

        mImageUrls.add("https://lh3.googleusercontent.com/GU5ifX8vaXc9SnGyQRC3PLxj8G9mHBfvZuDEDJ42zdTtj7JgR6Q4WAGmQcDZGXkStQ=w300");
        mNames.add("গর্ভাবস্থার স্বাস্থ্য পরিচর্যা");

        mImageUrls.add("https://icons-for-free.com/iconfiles/png/512/education+fitness+food+health+nutrition+icon-1320195162679216086.png");
        mNames.add("পুষ্টি তথ্য");

        mImageUrls.add("https://static.thenounproject.com/png/332689-200.png");
        mNames.add("রোগবালাই");

//        mImageUrls.add("https://cdn0.iconfinder.com/data/icons/medical-health-care-8/256/Health_Test-512.png");
//        mNames.add("ডায়াবেটিস");

        mImageUrls.add("https://cdn3.iconfinder.com/data/icons/medical-and-healthcare-flat-colored-volume-1/64/balanced-diet-512.png");
        mNames.add("ডায়েট");

        /*
        mImageUrls.add("https://previews.123rf.com/images/vectorstockcompany/vectorstockcompany1809/vectorstockcompany180901509/108988263-cancer-icon-cancer-concept-symbol-design-vector-illustration.jpg");
        mNames.add("ক্যান্সার");

        mImageUrls.add("https://cdn5.vectorstock.com/i/1000x1000/19/49/heart-attack-risk-logo-icon-design-vector-26811949.jpg");
        mNames.add("হৃদরোগ");

        mImageUrls.add("https://image.flaticon.com/icons/png/512/1615/premium/1615836.png");
        mNames.add("যৌন স্বাস্থ্য");
         */

        mImageUrls.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQDxkgFRSLvdwl-ofmprBCeO32Y75Q-nGcFl9A_flciFw2BZL7M&s");
        mNames.add("অন্যান্য");

        initRecyclerView();
    }

    void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recycleView_single);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(getApplicationContext(),mNames, mImageUrls, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onItemClick(int i) {
        intent = new Intent(getApplicationContext(), WebViewPage.class);
        intent.putExtra("type", mNames.get(i));
        if(!haveNetworkConnection()){
            builder.show();

        }
        else {
            startActivity(intent);
        }

//        Intent intent = new Intent(getApplicationContext(), WebViewPage.class);
//        intent.putExtra("type", mNames.get(i));
//        startActivity(intent);
    }


    public void setMobileDataState(boolean mobileDataEnabled)
    {
        try
        {
            TelephonyManager telephonyService = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);

            Method setMobileDataEnabledMethod = telephonyService.getClass().getDeclaredMethod("setDataEnabled", boolean.class);

            if (null != setMobileDataEnabledMethod)
            {
                setMobileDataEnabledMethod.invoke(telephonyService, mobileDataEnabled);
            }
        }
        catch (Exception ex)
        {
            Log.e(TAG, "Error setting mobile data state", ex);
        }
    }

    public boolean getMobileDataState()
    {
        try
        {
            TelephonyManager telephonyService = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);

            Method getMobileDataEnabledMethod = telephonyService.getClass().getDeclaredMethod("getDataEnabled");

            if (null != getMobileDataEnabledMethod)
            {
                boolean mobileDataEnabled = (Boolean) getMobileDataEnabledMethod.invoke(telephonyService);

                return mobileDataEnabled;
            }
        }
        catch (Exception ex)
        {
            Log.e(TAG, "Error getting mobile data state", ex);
        }

        return false;
    }


}
