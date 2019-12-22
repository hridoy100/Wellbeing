package com.example.healthapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.transition.TransitionManager;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class WebViewPage extends AppCompatActivity {
    TextView titleView;
    WebView webViewElement;
    private static final String TAG = "WebViewPage";

    TextView step1roundedView;
    TextView step2roundedView;
    TextView step3roundedView;
    TextView step1text;
    TextView step2text;
    TextView step3text;

    TextView[] stepTexts = new TextView[3];

   ImageView prev, next;
    int currentStep = 1;

    int maxSteps;
    String type;

    LinearLayout step1, step2, step3;
    public ProgressDialog progressDialog;
    private Map<String, Boolean> mLoadedUrls = new HashMap<>();

    ArrayList<String> urls = new ArrayList<String>();


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AdBlocker.init(this);

//        setContentView(R.layout.tabview_webpage);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");

        type = getIntent().getStringExtra("type");
        
//        Toast.makeText(getApplicationContext(), "type: "+type, Toast.LENGTH_LONG).show();

        if(type.equals(Constants.childHealth)){
            maxSteps = Database.childHealth.size();
        }
        else if(type.equals(Constants.pregnantHealth)){
            maxSteps = Database.pregnantHealth.size();
        }
        else if(type.equals(Constants.nutrition)){
            maxSteps = Database.nutrition.size();
        }
        else if(type.equals(Constants.disease)){
            maxSteps = Database.disease.size();
        }
//        else if(type.equals(Constants.diabetes)){
//            maxSteps = Database.diabetes.size();
//        }
        else if(type.equals(Constants.diet)){
            maxSteps = Database.diet.size();
        }
        /*else if(type.equals(Constants.cancer)){
            maxSteps = Database.cancer.size();
        }
        else if(type.equals(Constants.heartDisease)){
            maxSteps = Database.heartDisease.size();
        }
        else if(type.equals(Constants.sexualHealth)){
            maxSteps = Database.sexualHealth.size();
        }*/
        else if(type.equals(Constants.others)){
            maxSteps = Database.others.size();
        }

        if(maxSteps==1) {
            setContentView(R.layout.onetabview_webpage);
            step1roundedView = (TextView) findViewById(R.id.step1roundText);

            step1text = (TextView) findViewById(R.id.step1Text);

        }
        else if(maxSteps==2){
            setContentView(R.layout.twotabview_webpage);
            step1roundedView = (TextView) findViewById(R.id.step1roundText);
            step2roundedView = (TextView) findViewById(R.id.step2roundText);

            step1text = (TextView) findViewById(R.id.step1Text);
            step2text = (TextView) findViewById(R.id.step2Text);
        }
        else {
            setContentView(R.layout.tabview_webpage);
            step1 = findViewById(R.id.step1);
            step2 = findViewById(R.id.step2);
            step3 = findViewById(R.id.step3);
            step1roundedView = (TextView) findViewById(R.id.step1roundText);
            step2roundedView = (TextView) findViewById(R.id.step2roundText);
            step3roundedView = (TextView) findViewById(R.id.step3roundText);

            step1text = (TextView) findViewById(R.id.step1Text);
            step2text = (TextView) findViewById(R.id.step2Text);
            step3text = (TextView) findViewById(R.id.step3Text);

        }

        if(maxSteps>3){
            prev = findViewById(R.id.previousPageImg);
            next = findViewById(R.id.nextPageImg);
        }

        titleView = (TextView) findViewById(R.id.title);
        webViewElement = (WebView) findViewById(R.id.webview);

//        step1roundedView = (TextView) findViewById(R.id.step1roundText);
//        step2roundedView = (TextView) findViewById(R.id.step2roundText);
//        step3roundedView = (TextView) findViewById(R.id.step3roundText);

//        step1text = (TextView) findViewById(R.id.step1Text);
//        step2text = (TextView) findViewById(R.id.step2Text);
//        step3text = (TextView) findViewById(R.id.step3Text);

//        goBack = (TextView) findViewById(R.id.goBack);
//        continueNext = (Button) findViewById(R.id.continueToNext);
        titleView.setText(type);
        webViewElement.getSettings().setJavaScriptEnabled(true);

//        if(maxSteps==1){
//            step2roundedView.setVisibility(View.INVISIBLE);
//            step2text.setVisibility(View.INVISIBLE);
//
//            step3roundedView.setVisibility(View.INVISIBLE);
//            step3text.setVisibility(View.INVISIBLE);
//        }
//        else if(maxSteps==2){
//            step3roundedView.setVisibility(View.INVISIBLE);
//            step3text.setVisibility(View.INVISIBLE);
//        }

        checkCurrentStep();



        step1text.setTextColor(Color.parseColor("#000000"));
        step1roundedView.setTextColor(Color.parseColor("#FFFFFF"));
        step1roundedView.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#000000")));

        if(maxSteps>3) {
            prev.setEnabled(false);
            prev.setVisibility(View.INVISIBLE);

            prev.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    currentStep--;
                    if (currentStep < 1)
                        currentStep = 1;
                    checkCurrentStep();
                }
            });

            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    currentStep++;
                    if (currentStep > maxSteps)
                        currentStep = maxSteps;
                    checkCurrentStep();
                }
            });
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    void checkCurrentStep(){
        String url;
        int sleepValue = 400;
        progressDialog.show();

        SingleElement element = new SingleElement("null", "null", "null");
//        webViewElement.setWebViewClient(new WebViewClient());

        ArrayList<SingleElement> tempArray = new ArrayList<>();

        webViewElement.setWebViewClient(new WebViewClient() {
            private Map<String, Boolean> loadedUrls = new HashMap<>();

            @TargetApi(Build.VERSION_CODES.HONEYCOMB)
            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
                boolean ad;
                if (!loadedUrls.containsKey(url)) {
                    ad = AdBlocker.isAd(url);
                    loadedUrls.put(url, ad);
                } else {
                    ad = loadedUrls.get(url);
                }
                return ad ? AdBlocker.createEmptyResource() :
                        super.shouldInterceptRequest(view, url);
            }
        });

        if(type.equals(Constants.childHealth)){
            element = Database.childHealth.get(currentStep-1);
            webViewElement.loadUrl(element.link);

            tempArray = Database.childHealth;

        }
        else if(type.equals(Constants.pregnantHealth)){
            element = Database.pregnantHealth.get(currentStep-1);
            webViewElement.loadUrl(element.link);
            tempArray = Database.pregnantHealth;

        }
        else if(type.equals(Constants.nutrition)){
            element = Database.nutrition.get(currentStep-1);
            webViewElement.loadUrl(element.link);
            tempArray = Database.nutrition;
        }
        else if(type.equals(Constants.disease)){
            element = Database.disease.get(currentStep-1);
            webViewElement.loadUrl(element.link);
            tempArray = Database.disease;
        }
//        else if(type.equals(Constants.diabetes)){
//            element = Database.diabetes.get(currentStep-1);
//            webViewElement.loadUrl(element.link);
//        }
        else if(type.equals(Constants.diet)){
            element = Database.diet.get(currentStep-1);
            webViewElement.loadUrl(element.link);
            tempArray = Database.diet;
        }
        /*else if(type.equals(Constants.cancer)){
            element = Database.cancer.get(currentStep-1);
            webViewElement.loadUrl(element.link);
        }
        else if(type.equals(Constants.heartDisease)){
            element = Database.heartDisease.get(currentStep-1);
            webViewElement.loadUrl(element.link);
        }
        else if(type.equals(Constants.sexualHealth)){
            element = Database.sexualHealth.get(currentStep-1);
            webViewElement.loadUrl(element.link);
        }*/
        else if(type.equals(Constants.others)){
            element = Database.others.get(currentStep-1);
            webViewElement.loadUrl(element.link);
            tempArray = Database.others;
        }



        for (int i=0; i<maxSteps; i++){
            if(maxSteps==1){
                step1text.setText(tempArray.get(i).getTitle());
            }
            else if(maxSteps==2){
                step1text.setText(tempArray.get(i).getTitle());
                i++;
                step2text.setText(tempArray.get(i).getTitle());
            }
            else if(maxSteps>2){
                step1text.setText(tempArray.get(i).getTitle());
                i++;
                step2text.setText(tempArray.get(i).getTitle());
                i++;
                step3text.setText(tempArray.get(i).getTitle());
            }
        }


        webViewElement.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                if (progress >= 50) {
                    //do your task
                    progressDialog.dismiss();
                }
            }
        });



        if(currentStep==maxSteps && maxSteps>3) {
            next.setEnabled(false);
            next.setVisibility(View.INVISIBLE);
        }
        else if(currentStep<maxSteps && maxSteps>3) {
            next.setEnabled(true);
            next.setVisibility(View.VISIBLE);
        }


        switch (currentStep){
            case 1:
//                url = "https://www.prothomalo.com/lifestyle-doctor";
////                url = if();
//                webViewElement.loadUrl(url);

                SystemClock.sleep(sleepValue);

                step1text.setText(element.getTitle());

                step1text.setTextColor(Color.parseColor("#000000"));
                step1roundedView.setTextColor(Color.parseColor("#FFFFFF"));
                step1roundedView.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#000000")));

//                goBack.setEnabled(false);
//                goBack.setVisibility(View.INVISIBLE);

                if(maxSteps>1) {
                    step2text.setTextColor(Color.parseColor("#9daba6"));
                    step2roundedView.setTextColor(Color.parseColor("#9daba6"));
                    step2roundedView.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FFFFFF")));

                    step2text.setText(tempArray.get(1).getTitle());
                }

                if(maxSteps>2) {
                    step3text.setTextColor(Color.parseColor("#9daba6"));
                    step3roundedView.setTextColor(Color.parseColor("#9daba6"));
                    step3roundedView.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FFFFFF")));

                    step3text.setText(tempArray.get(2).getTitle());
                }
                if(maxSteps>3){
                    prev.setVisibility(View.INVISIBLE);
                    prev.setEnabled(false);
                }

                break;
            case 2:
//                url = "https://m.bdnews24.com/bn/section/health/";
//                webViewElement.loadUrl(url);

                SystemClock.sleep(sleepValue);

                step1text.setText(tempArray.get(0).getTitle());

                step2text.setText(element.getTitle());

                step2text.setTextColor(Color.parseColor("#000000"));
                step2roundedView.setTextColor(Color.parseColor("#FFFFFF"));
                step2roundedView.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#000000")));

//                goBack.setEnabled(true);
//                goBack.setVisibility(View.VISIBLE);

                step1text.setTextColor(Color.parseColor("#9daba6"));
                step1roundedView.setTextColor(Color.parseColor("#9daba6"));
                step1roundedView.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FFFFFF")));

                if(maxSteps>2) {
                    step3text.setTextColor(Color.parseColor("#9daba6"));
                    step3roundedView.setTextColor(Color.parseColor("#9daba6"));
                    step3roundedView.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FFFFFF")));

                    step3text.setText(tempArray.get(2).getTitle());
                }

                break;
            case 3:
//                url = "https://www.thedailystar.net/health";
//                webViewElement.loadUrl(url);
//
                SystemClock.sleep(sleepValue);

                step1text.setText(tempArray.get(0).getTitle());
                step2text.setText(tempArray.get(1).getTitle());

                step3text.setText(element.getTitle());

                step3text.setTextColor(Color.parseColor("#000000"));
                step3roundedView.setTextColor(Color.parseColor("#FFFFFF"));
                step3roundedView.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#000000")));

                step1text.setTextColor(Color.parseColor("#9daba6"));
                step1roundedView.setTextColor(Color.parseColor("#9daba6"));
                step1roundedView.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FFFFFF")));

                step2text.setTextColor(Color.parseColor("#9daba6"));
                step2roundedView.setTextColor(Color.parseColor("#9daba6"));
                step2roundedView.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FFFFFF")));

//                step1text.setText("Page 1");
//                step2text.setText("Page 2");
//                step3text.setText("Page 3");

                step1roundedView.setText("1");
                step2roundedView.setText("2");
                step3roundedView.setText("3");

                break;

            case 4:
//                url = "https://www.kalerkantho.com/online/prescription";
//                webViewElement.loadUrl(url);
                prev.setEnabled(true);
                prev.setVisibility(View.VISIBLE);

                SystemClock.sleep(sleepValue);

                step1text.setText(tempArray.get(1).getTitle());
                step2text.setText(tempArray.get(2).getTitle());

                step3text.setText(element.getTitle());

                step1roundedView.setText("2");
                step2roundedView.setText("3");
                step3roundedView.setText("4");

            break;
            case 5:
//                url = "https://healthnews.com.bd/";
//                webViewElement.loadUrl(url);

                SystemClock.sleep(sleepValue);

                step1text.setText(tempArray.get(2).getTitle());
                step2text.setText(tempArray.get(3).getTitle());

                step3text.setText(element.getTitle());

                step1roundedView.setText("3");
                step2roundedView.setText("4");
                step3roundedView.setText("5");
            break;
            case 6:
//                url = "https://www.bd-pratidin.com/health-tips";
//                webViewElement.loadUrl(url);

                SystemClock.sleep(sleepValue);

                step1text.setText(tempArray.get(3).getTitle());
                step2text.setText(tempArray.get(4).getTitle());
                step3text.setText(element.getTitle());

                step1roundedView.setText("4");
                step2roundedView.setText("5");
                step3roundedView.setText("6");
            break;
            case 7:
//                url = "https://doctor.ndtv.com/bengali/news";
//                webViewElement.loadUrl(url);

                SystemClock.sleep(sleepValue);

                step1text.setText(tempArray.get(4).getTitle());
                step2text.setText(tempArray.get(5).getTitle());
                step3text.setText(element.getTitle());

                step1roundedView.setText("5");
                step2roundedView.setText("6");
                step3roundedView.setText("7");
            break;
            /*
            case 8:
//                url = "http://blog.ehaspatal.com/";
//                webViewElement.loadUrl(url);

                SystemClock.sleep(sleepValue);

                step1text.setText("Page 6");
                step2text.setText("Page 7");
                step3text.setText("Page 8");

                step1roundedView.setText("6");
                step2roundedView.setText("7");
                step3roundedView.setText("8");
            break;
            case 9:
//                url = "https://www.sastobd.com/";
//                webViewElement.loadUrl(url);

                SystemClock.sleep(sleepValue);

                step1text.setText("Page 7");
                step2text.setText("Page 8");
                step3text.setText("Page 9");

                step1roundedView.setText("7");
                step2roundedView.setText("8");
                step3roundedView.setText("9");
            break;
            case 10: url = "https://www.sasthabangla.com/";
                webViewElement.loadUrl(url);

                SystemClock.sleep(sleepValue);

                step1text.setText("Page 8");
                step2text.setText("Page 9");
                step3text.setText("Page 10");

                step1roundedView.setText("8");
                step2roundedView.setText("9");
                step3roundedView.setText("10");
            break;*/
            default:
                url = "https://www.prothomalo.com/lifestyle-doctor";
                webViewElement.loadUrl(url);
                SystemClock.sleep(sleepValue);

            break;
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void step1Clicked(View view){
        currentStep = Integer.valueOf(step1roundedView.getText().toString());
        checkCurrentStep();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void step2Clicked(View view){
        currentStep = Integer.valueOf(step2roundedView.getText().toString());
        checkCurrentStep();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void step3Clicked(View view){
        currentStep = Integer.valueOf(step3roundedView.getText().toString());
        checkCurrentStep();
    }

    public void onBackClicked(View view){
        finish();
    }
}
