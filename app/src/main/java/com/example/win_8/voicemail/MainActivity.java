package com.example.win_8.voicemail;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.location.Address;
import android.location.Location;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.os.Vibrator;
import android.provider.ContactsContract;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends ActionBarActivity implements
    RecognitionListener,OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

     ImageView siriButton;
    //
    private long startTime1 = 8 * 60 * 1000; // 15 MINS IDLE TIME
    private final long interval = 1 * 1000;
    RelativeLayout reminderLayout;
    public String text;
    ScrollView scrollView4;
    TextView Hour1;
    public boolean res, searched, meaning = false;
    String s, p12;
    private TextView returnedText;
    ListView lv;
    ArrayList<String> adr=new ArrayList<String>();
    ScrollView scrollView3;
    public long sum = 0;
    TextView textMeaning, textHead, wikipedia, mapText;
    ScrollView scrollView,scrollView6;
    TextView text1, ampm, menu, city, Hour2, Hour3, weatherText, weather_report, Humidityheadvalue, Mintempheadvalue, Maxtempheadvalue, Maxtemphead, Mintemphead, Humidityhead;
    String maps = null, g;
    ImageView wikiImage, divider3, divider4;
    ArrayList<PendingIntent> intentArray = new ArrayList<>();
    ArrayList<PendingIntent> intentArray2 = new ArrayList<>();
    int counter = 0;
    final ArrayList<Contacts2> your_array_list = new ArrayList<Contacts2>();
    //
    private TextView timerValue;

    private long startTime = 0L;
    ListView initial;
    private android.os.Handler customHandler = new android.os.Handler();
    CustomAdapter4 customAdapter4;
    long timeInMilliseconds = 0L;
    long timeSwapBuff = 0L;
    long updatedTime = 0L;
    //
    int re=0;
    AlarmManager alarmManager, alarmManager2;
    CustomAdapter2 arrayAdapter;
    RelativeLayout r, r2;
    Context context;
    ListView searchListView, reminderlv;
    boolean t = false, t12 = false, t13 = false;
    private SpeechRecognizer speech = null;
    private Intent recognizerIntent;
    private String LOG_TAG = "VoiceRecognitionActivity";
    TextToSpeech t1;
    TextView wikiText, qHead, qAnswer, sideWiki, dictionary, calculateResult, aftertextAnim2, aftertextAnim3, aftertextAnim4, afteraftertextAnim4, afteraftertextAnim3, afteraftertextAnim2;
    private ProgressBar progressBar;
    final DBHandler db = new DBHandler(this);
    final DBHandler4 db4 = new DBHandler4(this);
    Button startButton,pauseButton;
    final DBHandler3 db3 = new DBHandler3(this);
    final DBHandler5 db5 = new DBHandler5(this);
    ScrollView scrollView5;
    RelativeLayout relative2;
    Activity ativity;
    ArrayList<Contacts> array;
    ArrayList<Contacts3> array2;
    //Content resolver used as a handle to the system's settings
    private ContentResolver cResolver;
    //Window object, that will store a reference to the current window
    private Window window;
    Contacts contact;
    CustomAdapter adapter;
    ScrollView scrollView1;
    PendingIntent pendingIntent;
    DBHandler2 db2;
    String userName;
    CustomAdapter3 adapter3;
    GoogleMap mGoogleMap;
    // SupportMapFragment mapFrag;
    ArrayList<String> arr25;
    LocationRequest mLocationRequest;
    GoogleApiClient mGoogleApiClient;
    Location mLastLocation;
    Marker mCurrLocationMarker;
    int cf = 0;
    boolean indi = false, indi1 = false, indi2 = false, indi4 = false, indi5 = false, indi6 = false,indi7=false,indi8=false;
   boolean indi9=false,indi10=false,indi11=false,indi12=false,indi13=false,indi14=false,indi15=true,indi16=false;
    Marker mCurrLocationMarker1, mCurrLocationMarker2;
    ImageView mapImage, weatherImage;
    double location_lat;
    RelativeLayout frontBackground, frontBackground1;
    double location_long;
    TextView textAnim1, textAnim2, textAnim4, textAnim3, mapHead;
    MyCountDownTimer countDownTimer;
    //@android:integer/config_longAnimTime

    public AlertDialog.Builder buildDialog(Context c) {

        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setTitle("Maven");
        builder.setMessage("Please connect to the Internet..");

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivityForResult(new Intent(android.provider.Settings.ACTION_SETTINGS), 0);

                dialog.dismiss();
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        return builder;
    }

    public AlertDialog.Builder buildDialog2(Context c) {

        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setTitle("Maven");
        builder.setMessage("Please allow Microphone access..");

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivityForResult(new Intent(android.provider.Settings.ACTION_SETTINGS), 0);

                dialog.dismiss();
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        return builder;
    }
    private Runnable updateTimerThread = new Runnable() {

        public void run() {

            timeInMilliseconds = SystemClock.uptimeMillis() - startTime;
            updatedTime = timeSwapBuff + timeInMilliseconds;

            int secs = (int) (updatedTime / 1000);
            int mins = secs / 60;
            secs = secs % 60;
            int milliseconds = (int) (updatedTime % 1000);
            timerValue.setText("" + mins + ":"
                    + String.format("%02d", secs) + ":"
                    + String.format("%03d", milliseconds));
            customHandler.postDelayed(this, 0);
        }

    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_main);
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            buildDialog2(this).show();
        }
        countDownTimer = new MyCountDownTimer(startTime1, interval);
        //final
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
//Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//set content view AFTER ABOVE sequence (to avoid crash)
        this.setContentView(R.layout.activity_main);
        //  requestWindowFeature(Window.FEATURE_NO_TITLE);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if (!isConnected(this)) buildDialog(this).show();

        scrollView5 = (ScrollView) findViewById(R.id.scrollView5);
        scrollView5.setVisibility(View.INVISIBLE);
        //

        scrollView6=(ScrollView) findViewById(R.id.scrollView6);
        scrollView6.setVisibility(View.INVISIBLE);

        adr=new ArrayList<>();
        adr.add("karan");
        initial=(ListView) findViewById(R.id.initial);
        customAdapter4 = new CustomAdapter4 (this,adr);

        initial.setAdapter(customAdapter4);
        initial.setVisibility(View.INVISIBLE);

        //
        qHead = (TextView) findViewById(R.id.qHead);
        qHead.setVisibility(View.INVISIBLE);
        timerValue=(TextView) findViewById(R.id.timerValue);
        timerValue.setVisibility(View.INVISIBLE);
        qAnswer = (TextView) findViewById(R.id.qAnswer);
        qAnswer.setVisibility(View.INVISIBLE);
        arr25 = new ArrayList<String>();
         startButton=(Button) findViewById(R.id.startButtonLabel);
        pauseButton=(Button) findViewById(R.id.pauseButtonLabel);
        startButton.setVisibility(View.INVISIBLE);
        pauseButton.setVisibility(View.INVISIBLE);

        scrollView3 = (ScrollView) findViewById(R.id.scrollView3);
        scrollView3.setVisibility(View.INVISIBLE);
        divider3 = (ImageView) findViewById(R.id.divider3);
        divider3.setVisibility(View.INVISIBLE);
        divider4 = (ImageView) findViewById(R.id.divider4);
        divider4.setVisibility(View.INVISIBLE);
        scrollView4 = (ScrollView) findViewById(R.id.scrollView4);
        Hour1 = (TextView) findViewById(R.id.Hour1);
        Typeface custom57 = Typeface.createFromAsset(getAssets(), "fonts/cavd.ttf");
        Hour1.setTypeface(custom57);
        Hour2 = (TextView) findViewById(R.id.Hour2);
        Typeface custom58 = Typeface.createFromAsset(getAssets(), "fonts/cavd.ttf");
        Hour2.setTypeface(custom58);
        scrollView4.setVisibility(View.INVISIBLE);
        Hour1.setVisibility(View.INVISIBLE);
        Hour2.setVisibility(View.INVISIBLE);
       // askForContactPermission24();
      //  mapFrag = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        //mapFrag.getView().setVisibility(View.INVISIBLE);
        Humidityheadvalue = (TextView) findViewById(R.id.Humidityheadvalue);
        Mintempheadvalue = (TextView) findViewById(R.id.Mintempheadvalue);
        Maxtempheadvalue = (TextView) findViewById(R.id.Maxtempheadvalue);
        weatherImage = (ImageView) findViewById(R.id.weatherImage);
        //  Maxtemphead=(TextView)findViewById(R.id.Maxtemphead);
        Mintemphead = (TextView) findViewById(R.id.Mintemphead);
        Humidityhead = (TextView) findViewById(R.id.Humidityhead);
        city = (TextView) findViewById(R.id.city);
        Typeface custom52 = Typeface.createFromAsset(getAssets(), "fonts/opensan2.ttf");
        city.setTypeface(custom52);
        weather_report = (TextView) findViewById(R.id.weather_report);
        weatherText = (TextView) findViewById(R.id.weatherText);
        weather_report.setVisibility(View.INVISIBLE);
        city.setVisibility(View.INVISIBLE);
        weatherImage.setVisibility(View.INVISIBLE);
        Maxtempheadvalue.setVisibility(View.INVISIBLE);
        Mintempheadvalue.setVisibility(View.INVISIBLE);
        Humidityheadvalue.setVisibility(View.INVISIBLE);

        weatherText.setVisibility(View.INVISIBLE);
        Typeface custom53 = Typeface.createFromAsset(getAssets(), "fonts/opensan2.ttf");
        weatherText.setTypeface(custom53);
        // Maxtemphead.setVisibility(View.INVISIBLE);
        Mintemphead.setVisibility(View.INVISIBLE);
        Humidityhead.setVisibility(View.INVISIBLE);
        arrayAdapter = new CustomAdapter2(getApplicationContext(), 1, your_array_list, this);
        frontBackground = (RelativeLayout) findViewById(R.id.frontBackground);

        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        r2 = (RelativeLayout) findViewById(R.id.reminderLayout);
        r2.setVisibility(View.GONE);
        reminderLayout = (RelativeLayout) findViewById(R.id.reminderLayout);
        menu = (TextView) findViewById(R.id.menu);
        searchListView = (ListView) findViewById(R.id.searchListView);
        searchListView.setVisibility(View.INVISIBLE);
        textAnim1 = (TextView) findViewById(R.id.textAnim1);
        frontBackground1 = (RelativeLayout) findViewById(R.id.frontBackground1);
        frontBackground1.setVisibility(View.INVISIBLE);
        Animation startFadeOutAnimation5 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in_animation);
        textAnim1.startAnimation(startFadeOutAnimation5);

        textAnim2 = (TextView) findViewById(R.id.textAnim2);
        Animation startFadeOutAnimation6 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out_animation);
        textAnim2.startAnimation(startFadeOutAnimation6);
        textAnim2.setVisibility(View.INVISIBLE);

        textAnim3 = (TextView) findViewById(R.id.textAnim3);
        Animation startFadeOutAnimation10 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out_animation);
        textAnim3.startAnimation(startFadeOutAnimation10);
        textAnim3.setVisibility(View.INVISIBLE);

        textAnim4 = (TextView) findViewById(R.id.textAnim4);
        Animation startFadeOutAnimation9 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out_animation);
        textAnim4.startAnimation(startFadeOutAnimation9);
        textAnim4.setVisibility(View.INVISIBLE);

        aftertextAnim2 = (TextView) findViewById(R.id.aftertextAnim2);
        Typeface custom7 = Typeface.createFromAsset(getAssets(), "fonts/hellight.ttf");
        aftertextAnim2.setTypeface(custom7);
        Animation startFadeOutAnimation12 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in_animation);
        aftertextAnim2.startAnimation(startFadeOutAnimation12);

        aftertextAnim3 = (TextView) findViewById(R.id.aftertextAnim3);
        Typeface custom6 = Typeface.createFromAsset(getAssets(), "fonts/hellight.ttf");
        aftertextAnim3.setTypeface(custom6);
        Animation startFadeOutAnimation13 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in_animation);
        aftertextAnim3.startAnimation(startFadeOutAnimation13);

        aftertextAnim4 = (TextView) findViewById(R.id.aftertextAnim4);
        Typeface custom5 = Typeface.createFromAsset(getAssets(), "fonts/hellight.ttf");
        aftertextAnim4.setTypeface(custom5);
        Animation startFadeOutAnimation14 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in_animation);
        aftertextAnim4.startAnimation(startFadeOutAnimation14);


        afteraftertextAnim4 = (TextView) findViewById(R.id.afteraftertextAnim4);
        Typeface custom534 = Typeface.createFromAsset(getAssets(), "fonts/hellight.ttf");
        afteraftertextAnim4.setTypeface(custom534);
        afteraftertextAnim4.setVisibility(View.INVISIBLE);

        afteraftertextAnim3 = (TextView) findViewById(R.id.afteraftertextAnim3);
        Typeface custom535 = Typeface.createFromAsset(getAssets(), "fonts/hellight.ttf");
        afteraftertextAnim3.setTypeface(custom535);
        afteraftertextAnim3.setVisibility(View.INVISIBLE);

        afteraftertextAnim2 = (TextView) findViewById(R.id.afteraftertextAnim2);
        Typeface custom536 = Typeface.createFromAsset(getAssets(), "fonts/hellight.ttf");
        afteraftertextAnim2.setTypeface(custom536);
        afteraftertextAnim2.setVisibility(View.INVISIBLE);
        Timer buttonTimer32 = new Timer();
        buttonTimer32.schedule(new TimerTask() {

            @Override
            public void run() {
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {

                        Animation startFadeOutAnimation15 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out_animation);

                        aftertextAnim2.startAnimation(startFadeOutAnimation15);
                        Animation startFadeOutAnimation16 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out_animation);
                        aftertextAnim3.startAnimation(startFadeOutAnimation16);
                        Animation startFadeOutAnimation17 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out_animation);
                        aftertextAnim4.startAnimation(startFadeOutAnimation17);

                        //
                        // Typeface custom19 = Typeface.createFromAsset(getAssets(),"fonts/ptsans2.ttf");
                        //Animation startFadeOutAnimation21 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in_animation);
                        //afteraftertextAnim4.startAnimation(startFadeOutAnimation21);
                        // afteraftertextAnim4.setTypeface(custom19);
                    }
                });
            }
        }, 7000);
        Timer buttonTimer33 = new Timer();

        buttonTimer33.schedule(new TimerTask() {

            @Override
            public void run() {
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        aftertextAnim2.setVisibility(View.INVISIBLE);
                        aftertextAnim3.setVisibility(View.INVISIBLE);
                        aftertextAnim4.setVisibility(View.INVISIBLE);

                        afteraftertextAnim4.setVisibility(View.VISIBLE);
                        Animation startFadeOutAnimation15 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in_animation);
                        afteraftertextAnim4.startAnimation(startFadeOutAnimation15);

                        afteraftertextAnim3.setVisibility(View.VISIBLE);
                        Animation startFadeOutAnimation24 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in_animation);
                        afteraftertextAnim3.startAnimation(startFadeOutAnimation24);

                        afteraftertextAnim2.setVisibility(View.VISIBLE);
                        Animation startFadeOutAnimation29 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in_animation);
                        afteraftertextAnim2.startAnimation(startFadeOutAnimation29);

                        //
                        // Typeface custom19 = Typeface.createFromAsset(getAssets(),"fonts/ptsans2.ttf");
                        //Animation startFadeOutAnimation21 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in_animation);
                        //afteraftertextAnim4.startAnimation(startFadeOutAnimation21);
                        // afteraftertextAnim4.setTypeface(custom19);
                    }
                });
            }
        }, 11000);
        Timer buttonTimer35 = new Timer();

        buttonTimer35.schedule(new TimerTask() {

            @Override
            public void run() {
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {

                        Animation startFadeOutAnimation15 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out_animation);
                        afteraftertextAnim4.startAnimation(startFadeOutAnimation15);
                        Animation startFadeOutAnimation24 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out_animation);
                        afteraftertextAnim3.startAnimation(startFadeOutAnimation24);

                        Animation startFadeOutAnimation29 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out_animation);
                        afteraftertextAnim2.startAnimation(startFadeOutAnimation29);

                        //
                        // Typeface custom19 = Typeface.createFromAsset(getAssets(),"fonts/ptsans2.ttf");
                        //Animation startFadeOutAnimation21 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in_animation);
                        //afteraftertextAnim4.startAnimation(startFadeOutAnimation21);
                        // afteraftertextAnim4.setTypeface(custom19);
                    }
                });
            }
        }, 17000);
        Timer buttonTimer399 = new Timer();

        buttonTimer399.schedule(new TimerTask() {

            @Override
            public void run() {
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        afteraftertextAnim4.setVisibility(View.INVISIBLE);
                        afteraftertextAnim3.
                                setVisibility(View.INVISIBLE);
                        afteraftertextAnim2.setVisibility(View.INVISIBLE);

                    }
                });
            }
        }, 21000);
        r = (RelativeLayout) findViewById(R.id.whiteLayout);
        mapHead = (TextView) findViewById(R.id.mapHead);
        //  mapText=(TextView) findViewById(R.id.mapText);

        //Typeface custom = Typeface.createFromAsset(getAssets(),"fonts/opensan2.ttf");
        //mapText.setTypeface(custom);
        // mapImage=(ImageView) findViewById(R.id.mapImage);
        r.setVisibility(View.GONE);
        mapHead.setVisibility(View.INVISIBLE);
        //  mapFrag = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        //mapFrag.getView().setVisibility(View.GONE);

        wikiImage = (ImageView) findViewById(R.id.wikiImage);
        wikiText = (TextView) findViewById(R.id.wikiText);
        wikipedia = (TextView) findViewById(R.id.wikipedia);
        scrollView1 = (ScrollView) findViewById(R.id.scrollView1);
        scrollView1.setVisibility(View.INVISIBLE);
        // Typeface custom_font5 = Typeface.createFromAsset(getAssets(),"fonts/opensan2.ttf");
        // wikiText.setTypeface(custom_font5);
        calculateResult = (TextView) findViewById(R.id.calculateResult);
        calculateResult.setVisibility(View.INVISIBLE);

        //reminder

        reminderlv = (ListView) findViewById(R.id.reminderlv);

        array2 = new ArrayList<>();
        List<Contacts3> contacts3 = db3.getAllContacts();

        for (Contacts3 cn : contacts3) {
            array2.add(cn);
        }
        adapter3 = new CustomAdapter3(this, array2);
        reminderlv.setAdapter(adapter3);
        reminderlv.setVisibility(View.INVISIBLE);
        //Alarm text
        lv = (ListView) findViewById(R.id.listview);
        ampm = (TextView) findViewById(R.id.ampm);
        array = new ArrayList<>();
        List<Contacts> contacts = db.getAllContacts();

        for (Contacts cn : contacts) {
            array.add(cn);
        }
        adapter = new CustomAdapter(getApplicationContext(), array, ativity);
        lv.setAdapter(adapter);

        lv.setVisibility(View.INVISIBLE);
        //dictionary text
        dictionary = (TextView) findViewById(R.id.dictionary);
        textHead = (TextView) findViewById(R.id.textHead);
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/opensan.ttf");
        textHead.setTypeface(custom_font);
        textMeaning = (TextView) findViewById(R.id.textMeaning);
        Typeface custom_font2 = Typeface.createFromAsset(getAssets(), "fonts/opensan2.ttf");
        textMeaning.setTypeface(custom_font2);
        //Main Text
        returnedText = (TextView) findViewById(R.id.textView1);
        Typeface custom_font3 = Typeface.createFromAsset(getAssets(), "fonts/helmed.ttf");
        returnedText.setTypeface(custom_font3);
        Animation startFadeOutAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in_animation2);
        returnedText.startAnimation(startFadeOutAnimation);
        Animation fadeIn = new AlphaAnimation(0, 1);
        fadeIn.setInterpolator(new DecelerateInterpolator()); //add this
        fadeIn.setDuration(1000);

        Animation fadeOut = new AlphaAnimation(1, 0);
        fadeOut.setInterpolator(new AccelerateInterpolator()); //and this
        fadeOut.setStartOffset(1000);
        fadeOut.setDuration(1000);

        AnimationSet animation = new AnimationSet(false); //change to false
        animation.addAnimation(fadeIn);
        animation.addAnimation(fadeOut);
        menu.setAnimation(animation);
        progressBar = (ProgressBar) findViewById(R.id.progressBar1);
        scrollView = (ScrollView) findViewById(R.id.scrollView);
        scrollView.setVisibility(View.INVISIBLE);
/*
            t1=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                @Override
                public void onInit(int status) {
                    if(status != TextToSpeech.ERROR) {
                        t1.setLanguage(Locale.US);
                        t1.setSpeechRate(1.0f);
                    }

                }
            });

            final String intro="Hi i am maven. How can i help you ?";
                Timer buttonTimer = new Timer();
                buttonTimer.schedule(new TimerTask() {

                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                t1.speak(intro, TextToSpeech.QUEUE_FLUSH, null);
                            }
                        });
                    }
                }, 400);
                counter++;
*/
        progressBar.setVisibility(View.INVISIBLE);
        speech = SpeechRecognizer.createSpeechRecognizer(this);
        speech.setRecognitionListener(this);
        recognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_PREFERENCE,
                "en");
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE,
                this.getPackageName());
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_WEB_SEARCH);
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 3);
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_MINIMUM_LENGTH_MILLIS, 5000);


        siriButton = (ImageView) findViewById(R.id.siriButton);

        siriButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                r.setVisibility(View.GONE);
                searchListView.setVisibility(View.INVISIBLE);
                r2.setVisibility(View.GONE);
                db2 = new DBHandler2(getApplicationContext());
                your_array_list.clear();
                reminderlv.setVisibility(View.INVISIBLE);
                Vibrator vb = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                vb.vibrate(50);
                afteraftertextAnim2.setVisibility(View.INVISIBLE);
                afteraftertextAnim3.setVisibility(View.INVISIBLE);
                afteraftertextAnim4.setVisibility(View.INVISIBLE);
                frontBackground1.setVisibility(View.INVISIBLE);
                Hour1.setVisibility(View.INVISIBLE);
               // mapFrag.getView().setVisibility(View.INVISIBLE);
                indi16=false;
                scrollView4.setVisibility(View.INVISIBLE);
                reminderLayout.setVisibility(View.GONE);
                frontBackground.setVisibility(View.INVISIBLE);
                progressBar.setVisibility(View.VISIBLE);
                textAnim1.setVisibility(View.INVISIBLE);
                textAnim2.setVisibility(View.INVISIBLE);
                indi6 = false;
                scrollView6.setVisibility(View.INVISIBLE);
               initial.setVisibility(View.INVISIBLE);
                textAnim3.setVisibility(View.INVISIBLE);
                textAnim4.setVisibility(View.INVISIBLE);
                textAnim1.setVisibility(View.INVISIBLE);
                divider3.setVisibility(View.INVISIBLE);
                qAnswer.setVisibility(View.INVISIBLE);
                qHead.setVisibility(View.INVISIBLE);
                timerValue.setVisibility(View.INVISIBLE);

                startButton.setVisibility(View.VISIBLE);
                pauseButton.setVisibility(View.VISIBLE);
                scrollView5.setVisibility(View.INVISIBLE);
                t = false;
                t12 = false;indi10=false;
                scrollView3.setVisibility(View.INVISIBLE);
                meaning = false;
                if (t13 == true) {
                    t1.shutdown();
                }
                indi1 = false;indi11=false;
                indi2 = false;indi12=false;
                indi5 = false;indi8=false;
                weatherImage.setVisibility(View.INVISIBLE);
                weatherText.setVisibility(View.INVISIBLE);
                Maxtempheadvalue.setVisibility(View.INVISIBLE);
                Mintempheadvalue.setVisibility(View.INVISIBLE);
                Humidityheadvalue.setVisibility(View.INVISIBLE);
                Mintemphead.setVisibility(View.INVISIBLE);
                Humidityhead.setVisibility(View.INVISIBLE);
                city.setVisibility(View.INVISIBLE);
                divider4.setVisibility(View.INVISIBLE);
                Hour2.setVisibility(View.INVISIBLE);
                g = null;indi9=false;
                weather_report.setVisibility(View.INVISIBLE);

                aftertextAnim2.setVisibility(View.INVISIBLE);
                aftertextAnim3.setVisibility(View.INVISIBLE);

                aftertextAnim4.setVisibility(View.INVISIBLE);
                scrollView.setVisibility(View.INVISIBLE);
                calculateResult.setVisibility(View.INVISIBLE);
                scrollView1.setVisibility(View.INVISIBLE);
                lv.setVisibility(View.INVISIBLE);
                // mapFrag.getView().setVisibility(View.INVISIBLE);

                searched = false;
                progressBar.setIndeterminate(true);


                speech.startListening(recognizerIntent);
            }
        });


        siriButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                Intent myIntent = new Intent(getApplicationContext(), ActionClass2.class);
                pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, myIntent, 0);
                alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                // cancel the alarm
                alarmManager.cancel(pendingIntent);
                // delete the PendingIntent
                pendingIntent.cancel();
                progressBar.setIndeterminate(false);
                progressBar.setVisibility(View.INVISIBLE);
                speech.stopListening();
                return true;
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Contacts cn = array.get(position);
                adapter.remove(adapter.getItem(position));
                Intent g = new Intent(getApplicationContext(), MainActivity.class);
                PendingIntent pi = PendingIntent.getBroadcast(context, cn.getID(), g, 0);
                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                alarmManager.cancel(pi);
                db.deleteContact(cn);
                scrollView.removeView(view);
                Toast.makeText(getApplicationContext(), "removed", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();

        //stop location updates when Activity is no longer active
        if (mGoogleApiClient != null) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
        }
    }

    public class MyCountDownTimer extends CountDownTimer {
        public MyCountDownTimer(long startTime1, long interval) {
            super(startTime1, interval);
        }

        @Override
        public void onFinish() {

            t1.speak("How can I help you..", TextToSpeech.QUEUE_FLUSH, null);
            t1 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                @Override
                public void onInit(int status) {
                    if (status != TextToSpeech.ERROR) {
                        t1.setLanguage(Locale.US);
                        t1.setSpeechRate(1.0f);
                    }

                }
            });
        }

        @Override
        public void onTick(long millisUntilFinished) {
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        scrollView.setVisibility(View.INVISIBLE);
        textHead.setVisibility(View.INVISIBLE);
        wikiText.setVisibility(View.INVISIBLE);
        wikiImage.setVisibility(View.INVISIBLE);
        textMeaning.setVisibility(View.INVISIBLE);
        lv.setVisibility(View.INVISIBLE);
        scrollView1.setVisibility(View.VISIBLE);
        wikipedia.setVisibility(View.INVISIBLE);
        dictionary.setVisibility(View.INVISIBLE);

        //  mapFrag.getView().setVisibility(View.VISIBLE);
        r.setVisibility(View.VISIBLE);
        mGoogleMap = googleMap;
        mGoogleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        //Initialize Google Play Services
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                //Location Permission already granted
                buildGoogleApiClient();
                mGoogleMap.setMyLocationEnabled(true);


            } else {
                //Request Location Permission
                checkLocationPermission();
            }
        } else {
            buildGoogleApiClient();
            mGoogleMap.setMyLocationEnabled(true);
        }
    }

    public boolean isConnected(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netinfo = cm.getActiveNetworkInfo();

        if (netinfo != null && netinfo.isConnectedOrConnecting()) {
            android.net.NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            android.net.NetworkInfo mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            if ((mobile != null && mobile.isConnectedOrConnecting()) || (wifi != null && wifi.isConnectedOrConnecting()))
                return true;
            else return false;
        } else return false;
    }

    protected void search(List<Address> addresses) {

        Address address = addresses.get(0);

        double home_long = address.getLongitude();
        double home_lat = address.getLatitude();
        LatLng latLng2 = new LatLng(address.getLatitude(), address.getLongitude());

        String addressText = String.format(
                "%s, %s",
                address.getMaxAddressLineIndex() > 0 ? address
                        .getAddressLine(0) : "", address.getCountryName());

        MarkerOptions markerOptions2 = new MarkerOptions();

        markerOptions2.position(latLng2);
        markerOptions2.title(addressText);

        mGoogleMap.addMarker(markerOptions2);
        // CameraUpdate zoom=CameraUpdateFactory.zoomTo(15);
        CameraUpdate center =
                CameraUpdateFactory.newLatLng(latLng2);
        CameraUpdate zoom2 = CameraUpdateFactory.zoomTo(15);

        mGoogleMap.moveCamera(center);
        mGoogleMap.animateCamera(zoom2);
        //mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng2));
        markerOptions2.title("Current Position");
        markerOptions2.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));

        //  mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng2));

        mCurrLocationMarker1 = mGoogleMap.addMarker(markerOptions2);
        Polyline line = mGoogleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(location_lat, location_long), new LatLng(home_lat, home_long))
                .width(5)
                .color(Color.RED));
        // address 2
      /*  Address address2 = (Address) addresses.get(1);

        double home_long2 = address2.getLongitude();
        double home_lat2 = address2.getLatitude();
        LatLng latLng3 = new LatLng(address2.getLatitude(), address2.getLongitude());

        String addressText2 = String.format(
                "%s, %s",
                address2.getMaxAddressLineIndex() > 0 ? address2
                        .getAddressLine(0) : "", address2.getCountryName());

        MarkerOptions markerOptions3 = new MarkerOptions();

        markerOptions3.position(latLng3);
        markerOptions3.title(addressText2);

        mGoogleMap.addMarker(markerOptions3);
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng3));
        markerOptions3.title("Current Position");
        markerOptions3.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));

        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng3));

        mCurrLocationMarker2 = mGoogleMap.addMarker(markerOptions3);
        Polyline line2 = mGoogleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(location_lat, location_long), new LatLng(home_lat2, home_long2))
                .width(5)
                .color(Color.RED));*/
    }


    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnected(Bundle bundle) {        //when services are ON or OFF
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(3000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);

        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);

    }

    @Override
    public void onConnectionSuspended(int i) {
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
    }

    @Override
    public void onLocationChanged(Location location) {
        mLastLocation = location;
        cf++;
        if (mCurrLocationMarker != null) {
            mCurrLocationMarker.remove();
        }
        location_lat = location.getLatitude();
        location_long = location.getLongitude();
        //Place current location marker
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("Current Position");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
        mCurrLocationMarker = mGoogleMap.addMarker(markerOptions);
        //move map camera
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mGoogleMap.animateCamera(CameraUpdateFactory.zoomTo(12));
///bakeasf
        //optionally, stop location updates if only current location is needed
        if (mGoogleApiClient != null) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
        }
    }

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;

    private void checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                new AlertDialog.Builder(this)
                        .setTitle("Location Permission Needed")
                        .setMessage("This app needs the Location permission, please accept to use location functionality")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Prompt the user once explanation has been shown
                                ActivityCompat.requestPermissions(MainActivity.this,
                                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                        MY_PERMISSIONS_REQUEST_LOCATION);
                            }
                        })
                        .create()
                        .show();


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
        }
    }

    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 100;

    @TargetApi(23)
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // location-related task you need to do.


                    if (mGoogleApiClient == null) {
                        buildGoogleApiClient();
                    }
                    mGoogleMap.setMyLocationEnabled(true);
                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(this, "permission denied", Toast.LENGTH_LONG).show();
                }
                return;
            }
          case PERMISSIONS_REQUEST_READ_CONTACTS: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission is granted

                } else {
                    Toast.makeText(this, "Until you grant the permission, we canot display the names", Toast.LENGTH_SHORT).show();
                }
                return;

            }
        }

    }
//
public void askForContactPermission24() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_CONTACTS)) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Contacts access needed");
                builder.setPositiveButton(android.R.string.ok, null);
                builder.setMessage("please confirm Contacts access");//TODO put real question
                builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @TargetApi(23)
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        requestPermissions(
                                new String[]
                                        {Manifest.permission.READ_CONTACTS}
                                , PERMISSIONS_REQUEST_READ_CONTACTS);
                    }
                });
                builder.show();
            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_CONTACTS},
                        PERMISSIONS_REQUEST_READ_CONTACTS);
            }
        } else {
            final String[] projection = new String[]
                    {ContactsContract.Profile.DISPLAY_NAME};
            String userName1 = null;
            final Uri dataUri = Uri.withAppendedPath(ContactsContract.Profile.CONTENT_URI, ContactsContract.Contacts.Data.CONTENT_DIRECTORY);
            final ContentResolver contentResolver = getContentResolver();
            final Cursor c = contentResolver.query(dataUri, projection, null, null, null);

            try {
                if (c.moveToFirst()) {
                    userName1 = c.getString(c.getColumnIndex(ContactsContract.Profile.DISPLAY_NAME));
                }
            } finally {
                c.close();
            }
            userName = userName1.substring(0, userName1.indexOf(" "));
        }
    } else {
        final String[] projection = new String[]
                {ContactsContract.Profile.DISPLAY_NAME};
        String userName1 = null;
        final Uri dataUri = Uri.withAppendedPath(ContactsContract.Profile.CONTENT_URI, ContactsContract.Contacts.Data.CONTENT_DIRECTORY);
        final ContentResolver contentResolver = getContentResolver();
        final Cursor c = contentResolver.query(dataUri, projection, null, null, null);

        try {
            if (c.moveToFirst()) {
                userName1 = c.getString(c.getColumnIndex(ContactsContract.Profile.DISPLAY_NAME));
            }
        } finally {
            c.close();
        }
        userName = userName1.substring(0, userName1.indexOf(" "));
    }
}

    //
    public void askForContactPermission(String sr) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {

                // Should we show an explanation?
                if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.READ_CONTACTS)) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("Contacts access needed");
                    builder.setPositiveButton(android.R.string.ok, null);
                    builder.setMessage("please confirm Contacts access");//TODO put real question
                    builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @TargetApi(23)
                        @Override
                        public void onDismiss(DialogInterface dialog) {
                            requestPermissions(
                                    new String[]
                                            {Manifest.permission.READ_CONTACTS}
                                    , PERMISSIONS_REQUEST_READ_CONTACTS);
                        }
                    });
                    builder.show();
                } else {

                    // No explanation needed, we can request the permission.

                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.READ_CONTACTS},
                            PERMISSIONS_REQUEST_READ_CONTACTS);
                }
            } else {
                ContentResolver cr = getContentResolver();
                Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,
                        null, null, null, null);
                if (cur.getCount() > 0) {
                    while (cur.moveToNext()) {
                        String id = cur.getString(
                                cur.getColumnIndex(ContactsContract.Contacts._ID));
                        String name23 = cur.getString(cur.getColumnIndex(
                                ContactsContract.Contacts.DISPLAY_NAME));
                        if (cur.getInt(cur.getColumnIndex(
                                ContactsContract.Contacts.HAS_PHONE_NUMBER)) > 0) {
                            Cursor pCur = cr.query(
                                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                                    null,
                                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                                    new String[]{id}, null);
                            while (pCur.moveToNext()) {
                                String phoneNo = pCur.getString(pCur.getColumnIndex(
                                        ContactsContract.CommonDataKinds.Phone.NUMBER));
                       /* if(name23.toLowerCase().equals("karan")){
                            arr25.add(phoneNo);
                        }*/
                                if (name23.toLowerCase().contains(sr)) {
                                    g = phoneNo;
                                }
                                //db4.addContact(new Contacts(1,name23,phoneNo));
                                //   arr.add(new Contacts(1,name,phoneNo));
                            }
                            pCur.close();
                        }
                    }
                }
            }
        } else {
            ContentResolver cr = getContentResolver();
            Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,
                    null, null, null, null);
            if (cur.getCount() > 0) {
                while (cur.moveToNext()) {
                    String id = cur.getString(
                            cur.getColumnIndex(ContactsContract.Contacts._ID));
                    String name23 = cur.getString(cur.getColumnIndex(
                            ContactsContract.Contacts.DISPLAY_NAME));
                    if (cur.getInt(cur.getColumnIndex(
                            ContactsContract.Contacts.HAS_PHONE_NUMBER)) > 0) {
                        Cursor pCur = cr.query(
                                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                                null,
                                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                                new String[]{id}, null);
                        while (pCur.moveToNext()) {
                            String phoneNo = pCur.getString(pCur.getColumnIndex(
                                    ContactsContract.CommonDataKinds.Phone.NUMBER));
                       /* if(name23.toLowerCase().equals("karan")){
                            arr25.add(phoneNo);
                        }*/
                            if (name23.toLowerCase().contains(sr)) {
                                g = phoneNo;
                            }
                            //db4.addContact(new Contacts(1,name23,phoneNo));
                            //   arr.add(new Contacts(1,name,phoneNo));
                        }
                        pCur.close();
                    }
                }
            }
        }
    }

    @Override
    public void onBeginningOfSpeech() {
        Log.i(LOG_TAG, "onBeginningOfSpeech");

        Toast.makeText(getApplicationContext(), "Processing", Toast.LENGTH_LONG).show();
        // progressBar.setIndeterminate(false);
        //progressBar.setMax(10);
    }

    @Override
    public void onBufferReceived(byte[] buffer) {
        Log.i(LOG_TAG, "onBufferReceived: " + buffer);
    }

    @Override
    public void onEndOfSpeech() {

        Log.i(LOG_TAG, "onEndOfSpeech");
        //   progressBar.setIndeterminate(true);
        //  toggleButton.setChecked(false);

        //Toast.makeText(getApplicationContext(),"after math",Toast.LENGTH_LONG).show();


    }

    @Override
    public void onResults(Bundle results) {
        ArrayList<String> matches = results
                .getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
        text = matches.get(0).trim().toString();
           /* for (String result : matches)
                text += result + "\n";
*/
        s = artificial(text);
        returnedText.setText(s);
        progressBar.setVisibility(View.INVISIBLE);

        Animation startFadeOutAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in_animation);
        returnedText.startAnimation(startFadeOutAnimation);
        progressBar.setIndeterminate(true);

        if (s.toLowerCase().contains("in chinese") ||
                s.toLowerCase().contains("in japanese") || s.toLowerCase().contains("in german") || s.toLowerCase().contains("in italian") || s.toLowerCase().contains("in french")) {
            if (s.toLowerCase().contains("in chinese")) {
                final String s12 = s.replaceAll(" in chinese", "");

                Timer buttonTimer = new Timer();
                buttonTimer.schedule(new TimerTask() {

                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                t1.speak(s12, TextToSpeech.QUEUE_FLUSH, null);

                            }
                        });
                    }
                }, 1600);
                t1 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        if (status != TextToSpeech.ERROR) {
                            t1.setLanguage(Locale.CHINESE);
                            t1.setSpeechRate(1.0f);
                        }
                    }
                });
            }
            t13 = true;
            if (s.toLowerCase().contains("in japanese")) {
                final String s12 = s.replaceAll(" in japanese", "");

                Timer buttonTimer = new Timer();
                buttonTimer.schedule(new TimerTask() {

                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                t1.speak(s12, TextToSpeech.QUEUE_FLUSH, null);

                            }
                        });
                    }
                }, 1600);
                t1 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        if (status != TextToSpeech.ERROR) {
                            t1.setLanguage(Locale.JAPANESE);
                            t1.setSpeechRate(1.0f);
                        }

                    }
                });
            }
            if (s.toLowerCase().contains("Yes, I had been practicing one since a long time")) {
                Timer buttonTimer = new Timer();
                buttonTimer.schedule(new TimerTask() {

                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                t1.speak("Yes, I had been practicing one since a long time :" +
                                        "cats and boots and cats and boots and cats and boots and cats and boots and cats and boots. I can do this all day", TextToSpeech.QUEUE_FLUSH, null);

                            }
                        });
                    }
                }, 1600);
                t1 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        if (status != TextToSpeech.ERROR) {
                            t1.setLanguage(Locale.ITALIAN);
                            t1.setSpeechRate(1.4f);
                        }

                    }
                });
            }
            if(s.toLowerCase().contains("Okay, Tell me what you want to be reminded of..")){
                Timer buttonTimer = new Timer();
                buttonTimer.schedule(new TimerTask() {

                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                t1.speak("Okay, Tell me what you want to be reminded of..", TextToSpeech.QUEUE_FLUSH, null);
                            }
                        });
                    }
                }, 1600);
                t1 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        if (status != TextToSpeech.ERROR) {
                            t1.setLanguage(Locale.US);
                            t1.setSpeechRate(1.0f);
                        }
                    }
                });

            }
            if (s.toLowerCase().contains("in italian")) {
                final String s12 = s.replaceAll(" in italian", "");

                Timer buttonTimer = new Timer();
                buttonTimer.schedule(new TimerTask() {

                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                t1.speak(s12, TextToSpeech.QUEUE_FLUSH, null);

                            }
                        });
                    }
                }, 1600);
                t1 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        if (status != TextToSpeech.ERROR) {
                            t1.setLanguage(Locale.ITALIAN);
                            t1.setSpeechRate(1.0f);
                        }

                    }
                });
            }

            if (s.toLowerCase().contains("in french")) {
                final String s12 = s.replaceAll(" in french", "");

                Timer buttonTimer = new Timer();
                buttonTimer.schedule(new TimerTask() {

                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                t1.speak(s12, TextToSpeech.QUEUE_FLUSH, null);

                            }
                        });
                    }
                }, 1600);
                t1 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        if (status != TextToSpeech.ERROR) {
                            t1.setLanguage(Locale.FRENCH);
                            t1.setSpeechRate(1.0f);
                        }

                    }
                });
            }
            if (s.toLowerCase().contains("in german")) {
                final String s12 = s.replaceAll(" in german", "");

                Timer buttonTimer = new Timer();
                buttonTimer.schedule(new TimerTask() {

                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                t1.speak(s12, TextToSpeech.QUEUE_FLUSH, null);

                            }
                        });
                    }
                }, 1600);
                t1 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        if (status != TextToSpeech.ERROR) {
                            t1.setLanguage(Locale.GERMAN);
                            t1.setSpeechRate(1.0f);
                        }

                    }
                });
            }
        } else {
            Timer buttonTimer = new Timer();
            buttonTimer.schedule(new TimerTask() {

                @Override
                public void run() {
                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            t1.speak(s.replaceAll("\ud83d\ude01","").replaceAll("\ud83d\ude05","").
                                    replaceAll("\ud83d\ude07","").replaceAll("\ud83d\ude09","")
                                    .replaceAll("\ud83d\ude03","").replaceAll("\ud83d\ude0b","")
                                    .replaceAll("\ud83d\ude0c","").replaceAll("\ud83d\ude0f","")
                                    .replaceAll("\ud83d\ude0e","").replaceAll("\ud83d\ude15","")
                                    .replaceAll("\ud83d\ude0e",""), TextToSpeech.QUEUE_FLUSH, null);

                        }
                    });
                }
            }, 800);//1600
            t1 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                @Override
                public void onInit(int status) {
                    if (status != TextToSpeech.ERROR) {
                        t1.setLanguage(Locale.US);
                        t1.setSpeechRate(1.4f);
                    }

                }
            });
        }
           /*
                t1.speak(text, TextToSpeech.QUEUE_FLUSH, null);*/
/*
*/
    }

    @TargetApi(23)
    public String artificial(String text) {

        Pattern pattern2 = Pattern.compile("\\s");
        Matcher matcher2 = pattern2.matcher(text);
        if (matcher2.find() == true) {
            String[] arr2 = text.split(" ");
            int p9, p10;
            if (arr2[1].equals("A.M") || arr2[1].equals("P.M") || arr2[1].equals("a.m") ||
                    arr2[1].equals("p.m")) {

                if (arr2[0].length() == 1) {
                    p9 = Character.getNumericValue(arr2[0].charAt(0));
                    if (text.toLowerCase().contains("p.m")) {
                        int e = p9 + 12;
                        addItem(e, 0);
                        db.addContact(new Contacts(counter, e + "", "00"));

                        array.add(new Contacts(counter, e + "", "00"));
                    } else {
                        addItem(p9, 0);
                        db.addContact(new Contacts(counter, p9 + "", "00"));

                        array.add(new Contacts(counter, p9 + "", "00"));
                    }
                    text = "Alarm is set for " + arr2[0] + ":00";
                }
                if (arr2[0].length() == 4) {
                    p9 = Character.getNumericValue(arr2[0].charAt(0));
                    p10 = (Character.getNumericValue(arr2[0].charAt(2)) * 10) + (Character.getNumericValue(arr2[0].charAt(3)));
                    if (text.toLowerCase().contains("p.m") || text.toLowerCase().contains("P.M")) {
                        int e = p9 + 12;
                        addItem(e, p10);
                        db.addContact(new Contacts(counter, e + "", p10 + ""));

                        array.add(new Contacts(counter, e + "", p10 + ""));
                    } else {
                        if (p9 == 12) {
                            addItem(0, p10);
                            db.addContact(new Contacts(counter, "00", p10 + ""));

                            array.add(new Contacts(counter, "00", p10 + ""));
                        } else {
                            addItem(p9, p10);
                            db.addContact(new Contacts(counter, p9 + "", p10 + ""));

                            array.add(new Contacts(counter, p9 + "", p10 + ""));
                        }
                    }
                    //  text = "Alarm is set for " + arr2[0] + ":"+arr2[2]+""+arr2[3];
                }
                if (arr2[0].length() == 5) {
                    p9 = (Character.getNumericValue(arr2[0].charAt(0)) * 10) + (Character.getNumericValue(arr2[0].charAt(1)));
                    p10 = (Character.getNumericValue(arr2[0].charAt(3)) * 10) + (Character.getNumericValue(arr2[0].charAt(4)));
                    if (text.toLowerCase().contains("p.m")) {
                        int e = p9 + 12;
                        addItem(e, p10);
                        db.addContact(new Contacts(counter, e + "", p10 + ""));

                        array.add(new Contacts(counter, e + "", p10 + ""));
                    } else {
                        if (p9 == 12) {
                            addItem(0, p10);
                            db.addContact(new Contacts(counter, "00", p10 + ""));

                            array.add(new Contacts(counter, "00", p10 + ""));
                        } else {
                            addItem(p9, p10);
                            db.addContact(new Contacts(counter, p9 + "", p10 + ""));

                            array.add(new Contacts(counter, p9 + "", p10 + ""));
                        }
                    }
                    text = "Alarm is set for " + arr2[0] + ":" + arr2[2] + "" + arr2[3];
                }
                searched = true;

            }
        }
        if(text.toLowerCase().contains("restaurant nearby")||text.toLowerCase().contains("nearest restaurant")||text.toLowerCase().contains("nearby restaurant")){
            Intent i8 = new Intent(MainActivity.this, MapsActivity3.class);
            i8.putExtra("Bhopal7", "restaurant");
            startActivity(i8);
            indi11=true;
            searched=true;
        }
        if (text.toLowerCase().contains("show the list") && text.toLowerCase().contains("give me the list") && text.toLowerCase().contains("list all the items") && text.toLowerCase().contains("show all the items")) {
            text = "Which list? Shopping list or Remember List.." + "\ude15\ude15";
            indi11 = true;
            searched = true;
            indi15 = true;
        }
        if (text.toLowerCase().contains("language") && text.toLowerCase().contains("made") && text.toLowerCase().contains("you")) {
            text = "who me?";
            searched = true;
        }
        if (text.toLowerCase().contains("day on")) {
            String[] arr = text.split(" ");
            int c = 0;
            String x = null, y = null;
            String z = null;
            for (String a : arr) {
                if (a.contains("0") || a.contains("1") || a.contains("2") || a.contains("3") || a.contains("4") ||
                        a.contains("5") || a.contains("6") || a.contains("7") || a.contains("8") || a.contains("9")) {
                    if (c == 0) {
                        x = a;
                        c++;
                    } else {
                        y = a;
                    }
                }
            }
            String z12 = x.replaceAll("rd", "").replaceAll("st", "").replaceAll("nd", "").replaceAll("th", "");
            if (text.toLowerCase().contains("january")) {
                z = "01";
            }
            if (text.toLowerCase().contains("feburary")) {
                z = "02";
            }
            if (text.toLowerCase().contains("march")) {
                z = "03";
            }
            if (text.toLowerCase().contains("april")) {
                z = "04";
            }
            if (text.toLowerCase().contains("may")) {
                z = "05";
            }
            if (text.toLowerCase().contains("june")) {
                z = "06";
            }
            if (text.toLowerCase().contains("july")) {
                z = "07";
            }
            if (text.toLowerCase().contains("august")) {
                z = "08";
            }
            if (text.toLowerCase().contains("september")) {
                z = "09";
            }
            if (text.toLowerCase().contains("october")) {
                z = "10";
            }
            if (text.toLowerCase().contains("november")) {
                z = "11";
            }
            if (text.toLowerCase().contains("december")) {
                z = "12";
            }
            String input_date = z12 + "/" + z + "/2017";
            SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
            Date dt1 = null;
            try {
                dt1 = format1.parse(input_date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            DateFormat format2 = new SimpleDateFormat("EEEE");
            String finalDay = format2.format(dt1);
            text = "The day is " + finalDay;
            searched = true;
            indi11 = true;
        }
        if (text.toLowerCase().contains("make a shopping list") && text.toLowerCase().contains("make my shopping list")) {
            text = "okay tell me what to add to the list";
            searched = true;
            indi11 = true;
        }
        if (text.toLowerCase().contains("add") && text.toLowerCase().contains("shopping") && text.toLowerCase().contains("list")) {
            boolean h = false;

            if (text.toLowerCase().contains("in")) {
                String gh = text.substring(text.indexOf("add")).substring(4, (text.indexOf("in") - 1));

                h = true;
            } else if (text.toLowerCase().contains("to")) {

                h = true;
            }
            if (h == false) {

            }
            indi11 = true;
            searched = true;

        }

        if (text.toLowerCase().contains("weather") && text.toLowerCase().contains("after")) {
            String x = null;
            int x1 = 0;
            if (text.toLowerCase().contains("of") == false) {
                if (!text.toLowerCase().contains("months")) {
                    String[] arr = text.split(" ");
                    for (String a : arr) {
                        if (a.contains("0") || a.contains("1") || a.contains("2") || a.contains("3") || a.contains("4") ||
                                a.contains("5") || a.contains("6") || a.contains("7") || a.contains("8") || a.contains("9")) {
                            x = a;
                            x1 = Integer.parseInt(a);
                        }
                    }
                    if (x1 >= 0 && x1 <= 10) {
                        text = "weather after " + x1 + " days";
                        Intent i8 = new Intent(MainActivity.this, MapsActivity3.class);
                        i8.putExtra("Bhopal6", x1 + "");
                        startActivity(i8);
                        indi10 = true;
                    } else {
                        text = "Weather updates of 10 days can be shown";
                    }
                } else {
                    text = "Weather updates of 10 days can be shown";

                }
            }
            indi11 = true;
            searched = true;
        }

        if (indi11 == false) {
            if (text.toLowerCase().contains("after") && text.toLowerCase().contains("days")) {
                String[] arr = text.split(" ");
                String ab = null;
                int x = 0;
                String input_date = null;
                for (String a : arr) {
                    if (a.contains("0") || a.contains("1") || a.contains("2") || a.contains("3") || a.contains("4") ||
                            a.contains("5") || a.contains("6") || a.contains("7") || a.contains("8") || a.contains("9")) {
                        x = Integer.parseInt(a);
                    }
                }
                Date date = new Date();
                Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                int hours = cal.get(Calendar.HOUR_OF_DAY);
                int min = cal.get(Calendar.MINUTE);
                int dayofmonth = cal.get(Calendar.DAY_OF_MONTH);
                int month = cal.get(Calendar.MONTH);
                int z = x + dayofmonth;
                if (month == 1) {
                    if (z <= 28) {
                        input_date = z + "/02/2017";
                        SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
                        Date dt1 = null;
                        try {
                            dt1 = format1.parse(input_date);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        DateFormat format2 = new SimpleDateFormat("EEEE");
                        String finalDay = format2.format(dt1);
                        text = "The day is " + finalDay;
                    } else {
                        input_date = (z - 28) + "/03/2017";
                        SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
                        Date dt1 = null;
                        try {
                            dt1 = format1.parse(input_date);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        DateFormat format2 = new SimpleDateFormat("EEEE");
                        String finalDay = format2.format(dt1);
                        text = "The day is " + finalDay;
                    }

                }
                if (month == 0 || month == 2 || month == 4 || month == 6 || month == 7 || month == 9 || month == 11) {
                    if (z <= 31) {
                        if (month == 11) {
                            input_date = (z) + "/11/2017";
                            SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
                            Date dt1 = null;
                            try {
                                dt1 = format1.parse(input_date);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            DateFormat format2 = new SimpleDateFormat("EEEE");
                            String finalDay = format2.format(dt1);
                            text = "The day is " + finalDay;
                        } else {
                            input_date = (z) + "/0" + month + "/2017";
                            SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
                            Date dt1 = null;
                            try {
                                dt1 = format1.parse(input_date);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            DateFormat format2 = new SimpleDateFormat("EEEE");
                            String finalDay = format2.format(dt1);
                            text = "The day is " + finalDay;
                        }
                    } else {
                        if (month == 11) {
                            input_date = (31 - z) + "/11/2017";
                            SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
                            Date dt1 = null;
                            try {
                                dt1 = format1.parse(input_date);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            DateFormat format2 = new SimpleDateFormat("EEEE");
                            String finalDay = format2.format(dt1);
                            text = "The day is " + finalDay;
                        } else {
                            input_date = (31 - z) + "/0" + month + "/2017";
                            SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
                            Date dt1 = null;
                            try {
                                dt1 = format1.parse(input_date);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            DateFormat format2 = new SimpleDateFormat("EEEE");
                            String finalDay = format2.format(dt1);
                            text = "The day is " + finalDay;
                        }
                    }
                }
                if (month == 3 || month == 5 || month == 8 || month == 10) {
                    if (z <= 30) {
                        if (month == 10) {
                            input_date = (z) + "/10/2017";
                            SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
                            Date dt1 = null;
                            try {
                                dt1 = format1.parse(input_date);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            DateFormat format2 = new SimpleDateFormat("EEEE");
                            String finalDay = format2.format(dt1);
                            text = "The day is " + finalDay;
                        } else {
                            input_date = (z) + "/0" + month + "/2017";
                            SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
                            Date dt1 = null;
                            try {
                                dt1 = format1.parse(input_date);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            DateFormat format2 = new SimpleDateFormat("EEEE");
                            String finalDay = format2.format(dt1);
                            text = "The day is " + finalDay;
                        }
                    } else {
                        if (month == 10) {
                            input_date = (30 - z) + "/10/2017";
                            SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
                            Date dt1 = null;
                            try {
                                dt1 = format1.parse(input_date);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            DateFormat format2 = new SimpleDateFormat("EEEE");
                            String finalDay = format2.format(dt1);
                            text = "The day is " + finalDay;
                        } else {
                            input_date = (30 - z) + "/0" + month + "/2017";
                            SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
                            Date dt1 = null;
                            try {
                                dt1 = format1.parse(input_date);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            DateFormat format2 = new SimpleDateFormat("EEEE");
                            String finalDay = format2.format(dt1);
                            text = "The day is " + finalDay;
                        }
                    }
                }
                searched = true;
                indi11 = true;
                indi2 = true;
            }
        }
        if (text.toLowerCase().contains("diwali") || text.toLowerCase().contains("deepavali")) {
            text = "Diwali is on 19th October 2017";
            searched = true;
        }
        if (text.toLowerCase().contains("onam")) {
            text = "Onam festival on 4th September";
            searched = true;
        }
        if (text.toLowerCase().contains("halloween")) {
            text = "Halloween is on 31st October";
            searched = true;
        }
        if (text.toLowerCase().contains("when") && text.toLowerCase().contains("christmas")) {
            text = "Christmas is on 25th December";
            searched = true;
        }
        if (text.toLowerCase().contains("dussehra")) {
            text = "Dusshera is celebrated on 30th September";
            searched = true;
        }
        if (text.toLowerCase().contains("mother's day") || text.toLowerCase().contains("mothers day")) {
            text = "Mother's day is on 14th May";
            searched = true;
        }
        if (text.toLowerCase().contains("father's day") || text.toLowerCase().contains("fathers day")) {
            text = "Father's day is on 18th May";
            searched = true;
        }
        if (text.toLowerCase().contains("good friday")) {
            text = "Good Friday is on 14th April";
            searched = true;
        }
        if (text.toLowerCase().contains("easter day")) {
            text = "Easter day is on 16th April";
            searched = true;
        }
        if (text.toLowerCase().contains("vaisakhi")) {
            text = "Vaisakhi is on 13th April";
            searched = true;
        }
        if (text.toLowerCase().contains("earth hour")) {
            text = "Earth Hour is on 25th May";
            searched = true;
        }
        if (text.toLowerCase().contains("vegetarian day")) {
            text = "World Vegetarian Day is on 1st October";
            searched = true;
        }
        if (text.toLowerCase().contains("programmers day") || text.toLowerCase().contains("programmer's day") || text.toLowerCase().contains("programmers' day")) {
            text = "Mother's day is on 14th May";
            searched = true;
        }
        if (text.toLowerCase().contains("sexual health day")) {
            text = "World Sexual Health day is on 4th September";
            searched = true;
        }
        if (text.toLowerCase().contains("stroke day")) {
            text = "World Stroke day is on 29th October";
            searched = true;
        }
        if (text.toLowerCase().contains("vegan day")) {
            text = "World Vegan is on 1st November";
            searched = true;
        }
        if (text.toLowerCase().contains("bisexuality day")) {
            text = "World Bisexuality Day on 23th September";
            searched = true;
        }
        if (text.toLowerCase().contains("overdose awareness day")) {
            text = "International Overdose Awareness Day is on 31st August";
            searched = true;
        }
        if (text.toLowerCase().contains("ovarian cancer day")) {
            text = "Ovarian Cancer Day is on 8th May";
            searched = true;
        }
        if (text.toLowerCase().contains("mens day") || text.toLowerCase().contains("men's day")) {
            text = "International Mens day is on 19th November";
            searched = true;
        }
        if (text.toLowerCase().contains("prematurity day")) {
            text = "International Prematurity day is on 17th November";
            searched = true;
        }
        if (text.toLowerCase().contains("cerebral palsy day")) {
            text = "International Cerebral Palsy Day is on 4th October";
            searched = true;
        }
        if (text.toLowerCase().contains("kidney day")) {
            text = "Kidney day is on 9th March";
            searched = true;
        }
        if (text.toLowerCase().contains("customs day")) {
            text = "International Customs day is on 26th January";
            searched = true;
        }
        if (text.toLowerCase().contains("leprosy day")) {
            text = "International Leprosy day is on 29th January";
            searched = true;
        }
        if (text.toLowerCase().contains("wetlands day") || text.toLowerCase().contains("wetland day")) {
            text = "International Wetland's day is on 2nd Feburary";
            searched = true;
        }
        if (text.toLowerCase().contains("religion day") || text.toLowerCase().contains("religions day")) {
            text = "International Religions Day is on 15th January";
            searched = true;
        }
        if (text.toLowerCase().contains("braille day") || text.toLowerCase().contains("braille's day")) {
            text = "International Braille's day is on 4th January";
            searched = true;
        }
        if (text.toLowerCase().contains("valentine's day") || text.toLowerCase().contains("valentines day")) {
            text = "Valentine's day is on 14th Feburary";
            searched = true;
        }
        if (text.toLowerCase().contains("mahavir jayanti")) {
            text = "Mahavir Jayanti is on 9th May";
            searched = true;
        }
        if (text.toLowerCase().contains("shivaji jayanti")) {
            text = "Shivaji Jayanti is on 19th Feburary";
            searched = true;
        }
        if (text.toLowerCase().contains("rama navami") || text.toLowerCase().contains("ram navami")) {
            text = "Rama Navami is on 4th April";
            searched = true;
        }
        if (text.toLowerCase().contains("vasant panchami")) {
            text = "Vasant Panchami is on 1st Feburary";
            searched = true;
        }
        if (text.toLowerCase().contains("thanks giving") || text.toLowerCase().contains("thanksgiving")) {
            text = "Thanksgiving day is on 15th August";
            searched = true;
        }
        if (text.toLowerCase().contains("ganesh chaturthi")) {
            text = "Ganesh Chaturthi is on 25th August";
            searched = true;
        }
        if (text.toLowerCase().contains("mahatma gandhi jayanti")) {
            text = "Mahatma Gandhi Jayanti is on 2nd October";
            searched = true;
        }
        if (text.toLowerCase().contains("dussehra")) {
            text = "Dussehra is on 30th September";
            searched = true;
        }
        if (text.toLowerCase().contains("maharishi valmiki jayanti")) {
            text = "Maharishi Valmiki Jayanti is on 5th Ocotober";
            searched = true;
        }
        if (text.toLowerCase().contains("naraka chaturdasi")) {
            text = "Naraka Chaturdasi is on 18th Ocotober";
            searched = true;
        }
        if (text.toLowerCase().contains("maha saptami")) {
            text = "Maha Saptami is on 27 September";
            searched = true;
        }
        if (text.toLowerCase().contains("maha navami")) {
            text = "Maha Navami is on 29th September";
            searched = true;
        }
        if (text.toLowerCase().contains("rath yatra") || text.toLowerCase().contains("raat yatra")) {
            text = "Rath Yatra is on 25th June";
            searched = true;
        }
        if (text.toLowerCase().contains("friendship day") || text.toLowerCase().contains("friendship's day") || text.toLowerCase().contains("friendships day")) {
            text = "Friendships day is on 6th Ausgust";
            searched = true;
        }
        if (text.toLowerCase().contains("bhai dooj")) {
            text = "Bhai Dooj is on 21st October";
            searched = true;
        }
        if (text.toLowerCase().contains("guru nanak jayanti")) {
            text = "Guru Nanak Jayanti is on 4th November";
            searched = true;
        }
        if (text.toLowerCase().contains("children's day")) {
            text = "Children's day is on 14th November";
            searched = true;
        }
        if (text.toLowerCase().contains("family day")) {
            text = "Family day is on 1st January";
            searched = true;
        }
        if (text.toLowerCase().contains("new year's eve")) {
            text = "New years Eve is on 31st December";
            searched = true;
        }
        if (text.toLowerCase().contains("army day")) {
            text = "Army Day is on 25th January";
            searched = true;
        }
        if (text.toLowerCase().contains("laughter day")) {
            text = "International Laughter day is on 10th January";
            searched = true;
        }
        if (text.toLowerCase().contains("heritage day")) {
            text = "World Heritage day is on 18th April";
            searched = true;
        }
        if (text.toLowerCase().contains("forestry day")) {
            text = "World Forestry day is on 21st March";
            searched = true;
        }
        if (text.toLowerCase().contains("tb day")) {
            text = "World TB Day is on 24rt March";
            searched = true;
        }
        if (text.toLowerCase().contains("meterological day")) {
            text = "World Meterological Day is on 23th March";
            searched = true;
        }
        if (text.toLowerCase().contains("science day")) {
            text = "National Science Day is on 28th Feburary";
            searched = true;
        }
        if (text.toLowerCase().contains("voters day")) {
            text = "World Voters day is on 25th January";
            searched = true;
        }
        if (text.toLowerCase().contains("haemophilia day")) {
            text = "World Haemophilia Day is on 17th April";
            searched = true;
        }
        if (text.toLowerCase().contains("population day")) {
            text = "World Population day is on 11th July";
            searched = true;
        }
        if (text.toLowerCase().contains("hiroshima day")) {
            text = "Hiroshima day is on 6th August";
            searched = true;
        }
        if (text.toLowerCase().contains("senior citizen's day")) {
            text = "World Senior Citizens Day is on 8th August";
            searched = true;
        }
        if (text.toLowerCase().contains("commonwealth day") || text.toLowerCase().contains("common wealth day")) {
            text = "Commonwealth day is on 24th May";
            searched = true;
        }
        if (text.toLowerCase().contains("coconut day")) {
            text = "Coconut Day is on 2nd September";
            searched = true;
        }
        if (text.toLowerCase().contains("ozone day")) {
            text = "World Ozone day is on 16th September";
            searched = true;
        }
        if (text.toLowerCase().contains("tourism day")) {
            text = "World Tourism day is on 27th September";
            searched = true;
        }
        if (text.toLowerCase().contains("literacy day")) {
            text = "World Literacy day is on 8th September";
            searched = true;
        }
        if (text.toLowerCase().contains("habitat day")) {
            text = "World Habitat day is on 3rd October";
            searched = true;
        }
        if (text.toLowerCase().contains("engineer's day") || text.toLowerCase().contains("engineers day")) {
            text = "World Engineer's day is on 15th September";
            searched = true;
        }
        if (text.toLowerCase().contains("animal welfare day")) {
            text = "World Animal Welfare Day is on 4 October";
            searched = true;
        }
        if (text.toLowerCase().contains("rose day")) {
            text = "Rose day is on 22nd September.It's for the welfare of cancer patients";
            searched = true;
        }
        if (text.toLowerCase().contains("food day")) {
            text = "World food day is on 16th October "+"\ud83d\ude0b";
            searched = true;
        }
        if (text.toLowerCase().contains("diabetes day")) {
            text = "World Diabetes day is on 14th November";
            searched = true;
        }
        if (text.toLowerCase().contains("industrialisation day")) {
            text = "World Industrialisation is on 20th November";
            searched = true;
        }
        if (text.toLowerCase().contains("epilepsy day")) {
            text = "World Epilepsy Day is on 17th November";
            searched = true;
        }
        if (text.toLowerCase().contains("standards day")) {
            text = "World Standards day is on 14th October";
            searched = true;
        }
        if (text.toLowerCase().contains("thrift day")) {
            text = "World Thrift day is on 30th October";
            searched = true;
        }
        if (text.toLowerCase().contains("indian navy day")) {
            text = "Indian Navy Day is on 4th December";
            searched = true;
        }
        if (text.toLowerCase().contains("indian armed force flag day")) {
            text = "Indian Armed Force Flag Day is on 7th December";
            searched = true;
        }
        if (text.toLowerCase().contains("white cane day")) {
            text = "World White Cane Day is on 15th October";
            searched = true;
        }
        if (text.toLowerCase().contains("photography day")) {
            text = "World Photography day is on 19th Ausgust";
            searched = true;
        }
        if (text.toLowerCase().contains("quit india day")) {
            text = "Quit India Day is on 9th August";
            searched = true;
        }
        if (text.toLowerCase().contains("red cross day")) {
            text = "World Red Cross Day is on 8th May";
            searched = true;
        }
        if (text.toLowerCase().contains("nurses day")) {
            text = "World Nurses day is on 12th May";
            searched = true;
        }
        if (text.toLowerCase().contains("hypertension day")) {
            text = "World HyperTension day is on 12th May";
            searched = true;
        }
        if (text.toLowerCase().contains("anti tabacco day") || text.toLowerCase().contains("anti-tabacco day")) {
            text = "Anti-Tabacco Day is on 31st May";
            searched = true;
        }
        if (text.toLowerCase().contains("envirnoment day")) {
            text = "World Environment day is on 5th June";
            searched = true;
        }
        if (text.toLowerCase().contains("doctor's day")) {
            text = "World Doctor's Day is on 1st July";
            searched = true;
        }
        if (text.toLowerCase().contains("zoonoses day")) {
            text = "World Zoonoses Day is on 6th July";
            searched = true;
        }
        if (text.toLowerCase().contains("nagasaki day")) {
            text = "World Nagasaki Day is on 9th August";
            searched = true;
        }
        if (text.toLowerCase().contains("republic day")) {
            text = "Indian Repubic Day is on 26th January";
            searched = true;
        }
        if (text.toLowerCase().contains("independence day")) {
            text = "Independence Day is on 15th August";
            searched = true;
        }
        if (text.toLowerCase().contains("republic day")) {
            text = "Indian Repubic Day is on 26th January";
            searched = true;
        }
        if (text.toLowerCase().contains("water day")) {
            text = "World Water Day is on 22nd March";
            searched = true;
        }
        if (text.toLowerCase().contains("consumer rights day") || text.toLowerCase().contains("consumers rights day")) {
            text = "World Consumers Right day is on 15th March";
            searched = true;
        }
        if (text.toLowerCase().contains("disabled day")) {
            text = "World Disabled Day is on 15th March";
            searched = true;
        }
        if (text.toLowerCase().contains("labour day")) {
            text = "International Labour Day is on 1st May";
            searched = true;
        }
        if (text.toLowerCase().contains("coal miner's day")) {
            text = "World Coal Miner's is on 4th May";
            searched = true;
        }
        if (text.toLowerCase().contains("secretaries’ Day") || text.toLowerCase().contains("secretaries Day")) {
            text = "Secretaries’ Day is on 21st April";
            searched = true;
        }
        if (text.toLowerCase().contains("technology day")) {
            text = "World Technology Day is on 11th May";
            searched = true;
        }

        if (text.toLowerCase().contains("sin square theta + cos square theta equals to")) {
            text = "It equals to 1";
            searched = true;
        }

        if (text.toLowerCase().contains("how much") && text.toLowerCase().contains("dollars to rupees")) {
            calculateResult.setVisibility(View.VISIBLE);
            calculateResult.setText("1 dollar=68 rupees");
            text = "1 Dollar equals 68 rupees";
            indi4 = true;
            searched = true;
        }
        if(text.toLowerCase().contains("how")&&text.toLowerCase().contains("far")){
            String sg=text.toLowerCase().substring(text.indexOf("is")).substring(3);
            String city11,city12,city1,city2;
            city11=sg.substring(0,sg.indexOf(" "));
            String jh = sg.substring(sg.indexOf(" ")).substring(1);
            if (jh.charAt(0) != 'f') {
                city12 = jh.substring(0, jh.indexOf(" "));
                city1 = city11 + " " + city12;
                city2 = jh.substring(jh.indexOf(" ")).substring(6);

            } else {
                city1 = city11;
                city2 = jh.substring(5);
            }
            Intent i3 = new Intent(MainActivity.this, MapsActivity3.class);
            i3.putExtra("Bhopal1", city1);
            i3.putExtra("Bhopal2", city2);
            startActivity(i3);
            indi11 = true;
            searched = true;

        }
        if (text.toLowerCase().contains("distance") && text.toLowerCase().contains("from")) {
                String s1 = text.substring(text.indexOf("of")).substring(3);
                String city12, city1, city2 = null;

                String city11 = s1.substring(0, s1.indexOf(" "));
                String jh = s1.substring(s1.indexOf(" ")).substring(1);
                if (jh.charAt(0) != 'f') {
                    city12 = jh.substring(0, jh.indexOf(" "));
                    city1 = city11 + " " + city12;
                    city2 = jh.substring(jh.indexOf(" ")).substring(6);

                } else {
                    city1 = city11;
                    city2 = jh.substring(5);
                }
                // String city2 = s1.substring(s1.indexOf(" ")).substring(6);

                Intent i3 = new Intent(MainActivity.this, MapsActivity3.class);
                i3.putExtra("Bhopal1", city1);
                i3.putExtra("Bhopal2", city2);
                startActivity(i3);
                indi11 = true;
                searched = true;

        }

        if (text.toLowerCase().contains("distance") && text.toLowerCase().contains("between")) {

                String s1 = text.substring(text.indexOf("between")).substring(8);
                String city1, city2, city12;
                String city11 = s1.substring(0, s1.indexOf(" "));
                String jh = s1.substring(s1.indexOf(" ")).substring(1);
                if (jh.charAt(0) != 'a') {
                    city12 = jh.substring(0, jh.indexOf(" "));
                    city1 = city11 + " " + city12;
                    city2 = jh.substring(jh.indexOf(" ")).substring(5);
                } else {
                    city1 = city11;
                    city2 = jh.substring(4);
                }

                Intent i3 = new Intent(MainActivity.this, MapsActivity3.class);
                i3.putExtra("Bhopal1", city1);
                i3.putExtra("Bhopal2", city2);
                startActivity(i3);
                indi11 = true;
                searched = true;

        }
        if (text.toLowerCase().contains("capital of india")) {
            text = "Capital of India is New Delhi..";
            indi7 = true;
            indi8 = true;
            searched = true;
        }
        if (text.toLowerCase().contains("capital of sri lanka")) {
            text = "Capital of Sri Lanka is Sri Jayawadenepura Kotte";
            indi7 = true;
            indi8 = true;
            searched = true;
        }
        if (text.toLowerCase().contains("capital of Bolivia")) {
            text = "Capital of Bolivia is La Paz";
            indi7 = true;
            indi8 = true;
            searched = true;
        }
        if (text.toLowerCase().contains("capital of Mexico")) {
            text = "Capital of Mexico is Mexico City";
            indi7 = true;
            indi8 = true;
            searched = true;
        }
        if (text.toLowerCase().contains("capital of Malaysia")) {
            text = "Capital of Malaysia is Kuala Lumpur";
            indi7 = true;
            indi8 = true;
            searched = true;
        }
        if (text.toLowerCase().contains("capital of Panama")) {
            text = "Capital of Panama is Panama City";
            indi7 = true;
            indi8 = true;
            searched = true;
        }
        if (text.toLowerCase().contains("capital of Argentina")) {
            text = "Capital of Argentina is Buenos Aires";
            indi7 = true;
            indi8 = true;
            searched = true;
        }
        if (text.toLowerCase().contains("capital of nevada")) {
            text = "Capital of Nevada is Carson City";
            indi7 = true;
            indi8 = true;
            searched = true;
        }
        if (text.toLowerCase().contains("capital of new jersey")) {
            text = "Capital of New Jersey is Trenton";
            indi7 = true;
            indi8 = true;
            searched = true;
        }
        if (text.toLowerCase().contains("capital of new mexico")) {
            text = "Capital of New Mexico is Santa Fe";
            indi7 = true;
            indi8 = true;
            searched = true;
        }
        if (text.toLowerCase().contains("capital of new york")) {
            text = "Capital of New York is Albany";
            indi7 = true;
            indi8 = true;
            searched = true;
        }
        if (text.toLowerCase().contains("capital of north carolina")) {
            text = "Capital of North Carolina is Raleigh";
            indi7 = true;
            indi8 = true;
            searched = true;
        }
        if (text.toLowerCase().contains("capital of north dakota")) {
            text = "Capital of North Dakota is Bismarck";
            indi7 = true;
            indi8 = true;
            searched = true;
        }
        if (text.toLowerCase().contains("capital of ohio")) {
            text = "Capital of Ohio is Columbus";
            indi7 = true;
            indi8 = true;
            searched = true;
        }
        if (text.toLowerCase().contains("capital of olkahoma")) {
            text = "Capital of Olkahoma is Olkahoma City";
            indi7 = true;
            indi8 = true;
            searched = true;
        }
        if (text.toLowerCase().contains("capital of oregon")) {
            text = "Capital of Oregon is Salem";
            indi7 = true;
            indi8 = true;
            searched = true;
        }
        if (text.toLowerCase().contains("capital of rhode island")) {
            text = "Capital of Rhode Island is Provinence";
            indi7 = true;
            indi8 = true;
            searched = true;
        }
        if (text.toLowerCase().contains("capital of pennsylvania")) {
            text = "Capital of Pennsylvania is Harrisberg";
            indi7 = true;
            indi8 = true;
            searched = true;
        }
        if (text.toLowerCase().contains("capital of texas")) {
            text = "Capital of Texas is Austin";
            indi7 = true;
            indi8 = true;
            searched = true;
        }
        if (text.toLowerCase().contains("capital of utah")) {
            text = "Capital of Utah is Salt Lake City";
            indi7 = true;
            indi8 = true;
            searched = true;
        }
        if (text.toLowerCase().contains("capital of vermont")) {
            text = "Capital of Vermont is Montpelier";
            indi7 = true;
            indi8 = true;
            searched = true;
        }
        if (text.toLowerCase().contains("capital of virginia")) {
            text = "Capital of Virginia is Richmond";
            indi7 = true;
            indi8 = true;
            searched = true;
        }
        if (text.toLowerCase().contains("capital of washington")) {
            text = "Capital of Texas is Austin";
            indi7 = true;
            indi8 = true;
            searched = true;
        }
        if (text.toLowerCase().contains("capital of virginia")) {
            text = "Capital of Virginia is Richmond";
            indi7 = true;
            indi8 = true;
            searched = true;
        }
        if (text.toLowerCase().contains("capital of florida")) {
            text = "Capital of Florida is Tallahasee";
            indi7 = true;
            indi8 = true;
            searched = true;
        }
        if (text.toLowerCase().contains("capital of hawaii")) {
            text = "Capital of Hawaii is Honolulu";
            indi7 = true;
            indi8 = true;
            searched = true;
        }
        if (text.toLowerCase().contains("capital of idaho")) {
            text = "Capital of Idaho is Boise";
            indi7 = true;
            indi8 = true;
            searched = true;
        }
        if (text.toLowerCase().contains("capital of illinois")) {
            text = "Capital of Illinois is SpringField";
            indi7 = true;
            indi8 = true;
            searched = true;
        }
        if (text.toLowerCase().contains("capital of colorado")) {
            text = "Capital of Colorado is Denver";
            indi7 = true;
            indi8 = true;
            searched = true;
        }
        if (text.toLowerCase().contains("capital of california")) {
            text = "Capital of California is Sacramento";
            indi7 = true;
            indi8 = true;
            searched = true;
        }
        if (text.toLowerCase().contains("capital of arkansas")) {
            text = "Capital of Arkansas is Little Rock";
            indi7 = true;
            indi8 = true;
            searched = true;
        }
        if (text.toLowerCase().contains("capital of arizona")) {
            text = "Capital of Arizona is Phoenix";
            indi7 = true;
            indi8 = true;
            searched = true;
        }
        if (text.toLowerCase().contains("capital of alaska")) {
            text = "Capital of Alaska is Juneau";
            indi7 = true;
            indi8 = true;
            searched = true;
        }
        if (text.toLowerCase().contains("capital of alabama")) {
            text = "Capital of Alabama is Montgomery";
            indi7 = true;
            indi8 = true;
            searched = true;
        }
        if (text.toLowerCase().contains("capital of georgia")) {
            text = "Capital of Georgia is Atlanta";
            indi7 = true;
            indi8 = true;
            searched = true;
        }
        if (text.toLowerCase().contains("capital of hawaii")) {
            text = "Capital of Hawaii is Honolulu";
            indi7 = true;
            indi8 = true;
            searched = true;
        }
        if (text.toLowerCase().contains("capital of iowa")) {
            text = "Capital of Iowa is Des Moines";
            indi7 = true;
            indi8 = true;
            searched = true;
        }
        if (text.toLowerCase().contains("capital of kansas")) {
            text = "Capital of Kansas is Topeka";
            indi7 = true;
            indi8 = true;
            searched = true;
        }
        if (text.toLowerCase().contains("capital of kentucky")) {
            text = "Capital of Kentucky is Frankfort";
            indi7 = true;
            indi8 = true;
            searched = true;
        }
        if (text.toLowerCase().contains("capital of louisiana")) {
            text = "Capital of Louisiana is Batan Rouge";
            indi7 = true;
            indi8 = true;
            searched = true;
        }
        if (text.toLowerCase().contains("capital of maine")) {
            text = "Capital of Maine is Augusta";
            indi7 = true;
            indi8 = true;
            searched = true;
        }
        if (text.toLowerCase().contains("capital of maryland")) {
            text = "Capital of MaryLand is Annapolis";
            indi7 = true;
            indi8 = true;
            searched = true;
        }
        if (text.toLowerCase().contains("capital of massachusetts")) {
            text = "Capital of Massachusetts is Boston";
            indi7 = true;
            indi8 = true;
            searched = true;
        }
        if (text.toLowerCase().contains("capital of michigan")) {
            text = "Capital of Michigan is Lansing";
            indi7 = true;
            indi8 = true;
            searched = true;
        }
        if (text.toLowerCase().contains("capital of minnesota")) {
            text = "Capital of Minnesota is St Paul";
            indi7 = true;
            indi8 = true;
            searched = true;
        }
        if (text.toLowerCase().contains("capital of west virginia")) {
            text = "Capital of West Virginia is Charleston";
            indi7 = true;
            indi8 = true;
            searched = true;
        }
        if (text.toLowerCase().contains("capital of wisconsin")) {
            text = "Capital of Wisconsin is Madison";
            indi7 = true;
            indi8 = true;
            searched = true;
        }
        if (text.toLowerCase().contains("capital of missouri")) {
            text = "Capital of Missouri is Jefferson City";
            indi7 = true;
            indi8 = true;
            searched = true;
        }
        if (text.toLowerCase().contains("capital of mississippi")) {
            text = "Capital of Mississippi is Jackson";
            indi7 = true;
            indi8 = true;
            searched = true;
        }
        if (text.toLowerCase().contains("capital of minnesota")) {
            text = "Capital of Minnesota is St. Paul";
            indi7 = true;
            indi8 = true;
            searched = true;
        }
        if (text.toLowerCase().contains("capital of wyoming")) {
            text = "Capital of Wyoming is Cheyenne";
            indi7 = true;
            indi8 = true;
            searched = true;
        }
        if (text.toLowerCase().contains("capital of andra pradesh")) {
            text = "Capital of Andra Pradesh is Itanagar";
            indi7 = true;
            indi8 = true;
            searched = true;
        }
        if (text.toLowerCase().contains("capital of assam")) {
            text = "Capital of Assam is Dispur";
            indi7 = true;
            indi8 = true;
            searched = true;
        }
        if (text.toLowerCase().contains("capital of bihar")) {
            text = "Capital of Bihar is Patna";
            indi7 = true;
            indi8 = true;
            searched = true;
        }
        if (text.toLowerCase().contains("capital of andaman and nicobar")) {
            text = "Capital of Andaman and Nicobar is Port Blair";
            indi7 = true;
            indi8 = true;
            searched = true;
        }
        if (text.toLowerCase().contains("capital of goa")) {
            text = "Capital of Goa is Panaji";
            indi7 = true;
            indi8 = true;
            searched = true;
        }
        if (text.toLowerCase().contains("capital of gujarat")) {
            text = "Capital of Gujarat is Gandhinagar";
            indi7 = true;
            indi8 = true;
            searched = true;
        }
        if (text.toLowerCase().contains("capital of daman and diu")) {
            text = "Capital of Daman and Diu is Daman";
            indi7 = true;
            indi8 = true;
            searched = true;
        }
        if (text.toLowerCase().contains("capital of dadra and nagar haveli") || text.toLowerCase().contains("capital of dadar and nagar haveli")) {
            text = "Capital of Dadra and Nagar Haveli is Silvassa";
            indi7 = true;
            indi8 = true;
            searched = true;
        }
        if (text.toLowerCase().contains("capital of haryana")) {
            text = "Capital of Haryana is chandigarh";
            indi7 = true;
            indi8 = true;
            searched = true;
        }
        if (text.toLowerCase().contains("capital of himachal pradesh")) {
            text = "Capital of Himachal Pradesh is Shimla";
            indi7 = true;
            indi8 = true;
            searched = true;
        }
        if (text.toLowerCase().contains("capital of kerala")) {
            text = "Capital of Kerala is Thiruvananthapuram";
            indi7 = true;
            indi8 = true;
            searched = true;
        }
        if (text.toLowerCase().contains("capital of lakshadweep")) {
            text = "Capital of Lakshadweep is Kavaratti";
            indi7 = true;
            indi8 = true;
            searched = true;
        }
        if (text.toLowerCase().contains("capital of madhya pradesh")) {
            text = "Capital of Madhya Pradesh is Bhopal";
            indi7 = true;
            indi8 = true;
            searched = true;
        }
        if (text.toLowerCase().contains("capital of jharkhand")) {
            text = "Capital of Jharkhand is Ranchi";
            indi7 = true;
            indi8 = true;
            searched = true;
        }
        if (text.toLowerCase().contains("capital of jammu and kashmir")) {
            text = "Capital of Jammu and Kashmir is Srinagar";
            indi7 = true;
            indi8 = true;
            searched = true;
        }
        if (text.toLowerCase().contains("capital of orissa")) {
            text = "Capital of Orissa is Bhubaneswar";
            indi7 = true;
            indi8 = true;
            searched = true;
        }
        if (text.toLowerCase().contains("capital of punjab")) {
            text = "Capital of Punjab is Chandigarh";
            indi7 = true;
            indi8 = true;
            searched = true;
        }
        if (text.toLowerCase().contains("capital of rajasthan")) {
            text = "Capital of Rajasthan is Jaipur";
            indi7 = true;
            indi8 = true;
            searched = true;
        }
        if (text.toLowerCase().contains("capital of sikkim")) {
            text = "Capital of Sikkim is Gangtok";
            indi7 = true;
            indi8 = true;
            searched = true;
        }
        if (text.toLowerCase().contains("capital of meghalaya")) {
            text = "Capital of Meghalaya is Shilong";
            indi7 = true;
            indi8 = true;
            searched = true;
        }
        if (text.toLowerCase().contains("capital of manipur")) {
            text = "Capital of Manipur is Imphal";
            indi7 = true;
            indi8 = true;
            searched = true;
        }
        if (text.toLowerCase().contains("capital of maharashtra")) {
            text = "Capital of Maharashtra is Mumbai";
            indi7 = true;
            indi8 = true;
            searched = true;
        }
        if (text.toLowerCase().contains("capital of tamil nadu")) {
            text = "Capital of Tamil Nadu is Chennai";
            indi7 = true;
            indi8 = true;
            searched = true;
        }
        if (text.toLowerCase().contains("capital of tripura")) {
            text = "Capital of Tripura is Agartala";
            indi7 = true;
            indi8 = true;
            searched = true;
        }
        if (text.toLowerCase().contains("capital of uttarakhand")) {
            text = "Capital of Uttarakhand is Dehradun";
            indi7 = true;
            indi8 = true;
            searched = true;
        }
        if (text.toLowerCase().contains("capital of west bengal")) {
            text = "Capital of West Bengal is Kolkata";
            indi7 = true;
            indi8 = true;
            searched = true;
        }
        if (text.toLowerCase().contains("capital of mizoram")) {
            text = "Capital of Mizoram is Aizawl";
            indi7 = true;
            indi8 = true;
            searched = true;
        }
        if (text.toLowerCase().contains("capital of telangana")) {
            text = "Capital of Telangana is Hyderabad";
            indi7 = true;
            indi8 = true;
            searched = true;
        }
        if (text.toLowerCase().contains("capital of uttar pradesh")) {
            text = "Capital of Uttar Pradesh is Lucknow";
            indi7 = true;
            indi8 = true;
            indi8 = true;
            searched = true;
        }

        if (text.toLowerCase().contains("capital of Montana")) {
            text = "Capital of Montana is Helena";
            indi7 = true;
            indi8 = true;
            searched = true;
        }
        if (text.toLowerCase().contains("capital of United Kingdom")) {
            text = "Capital of United Kindgdom is London";
            indi7 = true;
            indi8 = true;
            searched = true;
        }
        if (indi8 == false) {
            if (text.toLowerCase().contains("capital of")) {
                scrollView.setVisibility(View.VISIBLE);
                textMeaning.setVisibility(View.INVISIBLE);
                dictionary.setVisibility(View.INVISIBLE);
                textHead.setVisibility(View.INVISIBLE);
                //scrollView.setFocusable(false);
                String sd;
                scrollView1.setVisibility(View.INVISIBLE);
                wikipedia.setVisibility(View.VISIBLE);
                wikiImage.setVisibility(View.VISIBLE);
                wikiText.setVisibility(View.VISIBLE);
                if (text.contains("what is")) {
                    sd = text.substring(23);
                } else {
                    sd = text.substring(11);

                }
                new ParseURL133().execute(sd);
                searched = true;
                indi7 = true;
            }
        }
//dollars to rupees
        if (text.toLowerCase().contains("$") && text.toLowerCase().contains("rupees")) {
            String s = null;
            StringBuffer s1 = new StringBuffer();
            String[] arr = text.split(" ");
            for (String a : arr) {
                if (a.contains("0") || a.contains("1") || a.contains("2") || a.contains("3") || a.contains("4") ||
                        a.contains("5") || a.contains("6") || a.contains("7") || a.contains("8") || a.contains("9")) {
                    s = a;
                }
            }
            for (int i = 1; i <= (s.length() - 1); i++) {
                s1.append(s.charAt(i));
            }
            float x = Float.valueOf(s1.toString());

            calculateResult.setVisibility(View.VISIBLE);
            float y = 68 * x;
            calculateResult.setText(y + "");
            text = s + " is equals to " + y + "Rupees";
            searched = true;
        }
        //conversion
        if (text.toLowerCase().contains("convert") || text.toLowerCase().contains("fahrenheit to celsius") ||
                text.toLowerCase().contains("celsius to fahrenheit")) {
            calculateResult.setVisibility(View.VISIBLE);
            String f;
            Convert c = new Convert(text);
            c.calculate();
            f = c.getP();
            calculateResult.setText(f + "");
            indi1 = true;
            searched = true;
        }
//time in countries
        if (text.toLowerCase().contains("time in") || text.toLowerCase().contains("what's the time in") || text.toLowerCase().contains("what is the time in")) {
            calculateResult.setVisibility(View.VISIBLE);
            weather_report.setVisibility(View.VISIBLE);
            String h2, h3;
            if (text.toLowerCase().contains("what's the time in")) {
                h2 = text.substring(18);
                //  h3 = h2.substring(0, 1).toUpperCase() + h2.substring(1).toLowerCase();
                new ParseURL12().execute(h2);

            } else if (text.toLowerCase().contains("what is the time in")) {
                h2 = text.substring(20);
                //h3 = h2.substring(0, 1).toUpperCase() + h2.substring(1).toLowerCase();
                new ParseURL12().execute(h2);
            } else if (text.toLowerCase().contains("time in")) {
                h2 = text.substring(8);
                //h3 = h2.substring(0, 1).toUpperCase() + h2.substring(1).toLowerCase();
                new ParseURL12().execute(h2);
            }
            indi2 = true;
            searched = true;
        }
        if (indi2 == false) {
            if (indi1 == false) {
                if (text.toLowerCase().equals("what's the time") || text.toLowerCase().contains("what is the day") || text.toLowerCase().equals("what's the day") || text.toLowerCase().equals("what is the day") || text.toLowerCase().contains("tell the time")) {

                    scrollView4.setVisibility(View.VISIBLE);
                    Hour1.setVisibility(View.VISIBLE);
                    timerValue.setVisibility(View.INVISIBLE);

                    Hour2.setVisibility(View.VISIBLE);
                    startButton.setVisibility(View.INVISIBLE);
                    pauseButton.setVisibility(View.INVISIBLE);

                    Date date = new Date();
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(date);
                    int hours = cal.get(Calendar.HOUR_OF_DAY);
                    int min = cal.get(Calendar.MINUTE);
                    int sec = cal.get(Calendar.SECOND);

                    int d = cal.get(Calendar.DAY_OF_WEEK);
                    String day = null, t, freet = null;
                    if (hours > 0 && hours <= 12) {
                        freet = "Good morning";
                    }
                    if (hours > 12 && hours <= 16) {
                        freet = "Good Afternoon";
                    }
                    if (hours > 16 && hours <= 20) {
                        freet = "Good Evening";
                    }
                    if (hours > 20 && hours < 24) {
                        freet = "Its late, I guess";
                    }
                    if (hours == 0) {
                        freet = "Good morning";
                    }
                    if (hours >= 12) {
                        t = " P.M";
                    } else {
                        t = " A.M";
                    }
                    if (d == 7) {
                        day = "Saturday";
                    }
                    if (d == 6) {
                        day = "Friday";
                    }
                    if (d == 5) {
                        day = "Thursday";
                    }
                    if (d == 4) {
                        day = "Wednesday";
                    }
                    if (d == 3) {
                        day = "Tuesday";
                    }
                    if (d == 2) {
                        day = "Monday";
                    }
                    if (d == 1) {
                        day = "Sunday";
                    }
                    if (hours == 0) {
                        if (min < 10) {
                            Hour1.setText(hours + "0:0" + min);
                        } else {
                            Hour1.setText(hours + "0:" + min);
                        }
                    }
                    if (min < 10) {
                        Hour1.setText(hours + ":0" + min);
                    } else {
                        Hour1.setText(hours + ":" + min);
                    }
                    Hour2.setText(day + "");
                    text = "It's " + hours + ":" + min + t + ", " + freet;
                    searched = true;
                }
            }
        }
        if (text.toLowerCase().equals("i'm here") || text.toLowerCase().equals("i am here")) {
            text = "home is where the iphone is";
            searched = true;
        }

        if (text.toLowerCase().contains("where") && text.toLowerCase().contains("i")) {
            searched = true;
            scrollView.setVisibility(View.INVISIBLE);
            textHead.setVisibility(View.INVISIBLE);
            wikiText.setVisibility(View.INVISIBLE);
            wikiImage.setVisibility(View.INVISIBLE);

            r2.setVisibility(View.GONE);
            reminderLayout.setVisibility(View.GONE);
            textMeaning.setVisibility(View.INVISIBLE);
            lv.setVisibility(View.INVISIBLE);
            wikipedia.setVisibility(View.INVISIBLE);
            dictionary.setVisibility(View.INVISIBLE);
            reminderlv.setVisibility(View.INVISIBLE);

            scrollView1.setVisibility(View.VISIBLE);
            r.setVisibility(View.VISIBLE);
            //  mapFrag.getView().setVisibility(View.VISIBLE);

            mapHead.setVisibility(View.VISIBLE);

            //mapFrag = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
            //mapFrag.getMapAsync(this);

        }

        if (text.toLowerCase().contains("chutiya") || text.toLowerCase().contains("madarchod") || text.toLowerCase().contains("b****")) {
            text = "I don't really like these arbitrary categories.";
            searched = true;
        }
        if (text.toLowerCase().contains("your birthday")) {
            text = "I don't really have a birthday," + ".."+"\ud83d\ude05";
            searched = true;
        }
        if (text.toLowerCase().contains("sounds great")) {
            text = "It feels nice to be appreciated "+"\ud83d\ude01"+".";
            searched = true;
        }
        if (text.equalsIgnoreCase("buy") || text.toLowerCase().equals("buy me when") || text.equalsIgnoreCase("byebye")) {
            searched = true;
            text = "Bye";
            finishAndRemoveTask();
        }
        if (text.toLowerCase().contains("you") && text.toLowerCase().contains("born")) {
            text = "I became operational on January 23,2017";
            searched = true;
        }
        if (text.toLowerCase().contains("stopwatch")) {

            scrollView4.setVisibility(View.VISIBLE);
            Hour1.setVisibility(View.INVISIBLE);
            timerValue.setVisibility(View.VISIBLE);

            startButton.setVisibility(View.VISIBLE);
            pauseButton.setVisibility(View.VISIBLE);
            startButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startButton.setBackgroundColor(Color.rgb(72, 133, 237));
                    startTime = SystemClock.uptimeMillis();
                    customHandler.postDelayed(updateTimerThread, 0);
                }
            });
            pauseButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pauseButton.setBackgroundColor(Color.rgb(72, 133, 237));

                    timeSwapBuff += timeInMilliseconds;
                    customHandler.removeCallbacks(updateTimerThread);
                }
            });
            pauseButton.setVisibility(View.VISIBLE);
            Hour2.setVisibility(View.INVISIBLE);
            searched = true;
        }
        if (text.toLowerCase().contains("you") && text.contains("awesome")) {
            searched = true;
            text = "Thank you for the complement";
        }
        if (text.toLowerCase().contains("are") && text.toLowerCase().contains("virgin")) {
            text = "I can't answer that";
            searched = true;
        }

        if (text.equalsIgnoreCase("show alarms") || text.equalsIgnoreCase("show alarm") || text.toLowerCase().contains("list of alarm")) {
            scrollView1.setVisibility(View.INVISIBLE);
            scrollView.setVisibility(View.VISIBLE);
            textHead.setVisibility(View.INVISIBLE);
            wikiText.setVisibility(View.INVISIBLE);
            wikiImage.setVisibility(View.INVISIBLE);
            textMeaning.setVisibility(View.INVISIBLE);
            lv.setVisibility(View.VISIBLE);
            mapHead.setVisibility(View.INVISIBLE);
            // mapFrag.getView().setVisibility(View.INVISIBLE);
            r.setVisibility(View.GONE);
            wikipedia.setVisibility(View.INVISIBLE);
            dictionary.setVisibility(View.INVISIBLE);
            text = "List of Alarms";
            searched = true;

        }
        if(text.toLowerCase().contains("i'm drunk")&&text.toLowerCase().contains("i am drunk")){
            text="Be Careful";searched=true;
        }
        if(text.toLowerCase().contains("i")&&text.toLowerCase().contains("alone")){
            text="Maybe you should attend some event and make friends or join social networking sites";
            searched=true;
        }

        if (text.toLowerCase().contains("in chinese") || text.toLowerCase().contains("in japanese") || text.toLowerCase().contains("in german") || text.toLowerCase().contains("in italian") || text.toLowerCase().contains("in french")) {
            searched = true;String de="false";
            String s = null;
            if (text.toLowerCase().contains("say")) {
                s = text.toLowerCase().substring(4);
                de="true";
            }
            else if(text.toLowerCase().contains("translate")){
               de="true";
                s =  text.toLowerCase().substring(10);
            }
            if(de=="false") {
                text = s;
            }
        }

        if (indi11 == false) {
            if (text.toLowerCase().contains("tell me weather updates") || text.toLowerCase().contains("what's the weather update")|| text.toLowerCase().contains("what's the weather like") || text.toLowerCase().contains("whats the weather like") || text.toLowerCase().contains("weather updates")) {
                Intent i3 = new Intent(MainActivity.this, MapsActivity3.class);
                i3.putExtra("Bhopal4", "indore");
                startActivity(i3);
                indi10 = true;
                searched = true;
            }
        }

        //weather
        if (indi10 == false) {
            if (text.toLowerCase().contains("weather")) {
                searched = true;
                // mapFrag.getView().setVisibility(View.INVISIBLE);
                frontBackground1.setVisibility(View.VISIBLE);
                Maxtempheadvalue.setVisibility(View.VISIBLE);
                Mintempheadvalue.setVisibility(View.VISIBLE);
                Humidityheadvalue.setVisibility(View.VISIBLE);
                frontBackground1.setVisibility(View.VISIBLE);
                scrollView3.setVisibility(View.VISIBLE);
                divider3.setVisibility(View.VISIBLE);
                divider4.setVisibility(View.VISIBLE);
                // Maxtemphead.setVisibility(View.VISIBLE);
                Mintemphead.setVisibility(View.VISIBLE);
                Humidityhead.setVisibility(View.VISIBLE);
                city.setVisibility(View.VISIBLE);
                weather_report.setVisibility(View.VISIBLE);
                weather_report.setText("Weather Report");
                weatherImage.setVisibility(View.VISIBLE);
                weatherText.setVisibility(View.VISIBLE);
                // mapFrag.getView().setVisibility(View.VISIBLE);
                //mapFrag = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map1);
                ///mapFrag.getMapAsync(this);
                if (text.toLowerCase().contains("after") && text.toLowerCase().contains("days")) {
                    int x1 = 0;
                    String x = null, city1;
                    String[] arr = text.split(" ");
                    for (String a : arr) {
                        if (a.contains("0") || a.contains("1") || a.contains("2") || a.contains("3") || a.contains("4") ||
                                a.contains("5") || a.contains("6") || a.contains("7") || a.contains("8") || a.contains("9")) {
                            x = a;
                            x1 = Integer.parseInt(a);
                        }
                    }
                    if (x1 >= 1 && x1 <= 9) {
                        String jh = text.substring(text.indexOf("of")).substring(3);
                        String city11 = jh.substring(0, jh.indexOf(" "));
                        String h = jh.substring(jh.indexOf(" ")).substring(1);
                        if (h.charAt(0) != 'a') {
                            String city12 = h.substring(0, h.indexOf(" "));
                            city1 = city11 + "-" + city12;
                        } else {
                            city1 = city11;
                        }
                        new ParseURL66().execute(city1 + " " + x1);
                        searched = true;
                    } else {
                        text = "Weather Updates of 10 days are avaliable";
                    }
                } else {
                    String[] arr = text.split(" ");
                    int c = 0;
                    for (int i = 0; i <= (arr.length - 1); i++) {
                        if (arr[i].toLowerCase().contains("weather")) {
                            c = i;
                        }
                    }

                    if (arr[c + 1].toLowerCase().contains("of")) {
                        weather w = new weather(text);
                        w.process();
                        String df = w.getP();
                        new ParseURL6().execute(df + "");
                        //  text = "Weather Updates are";
                        searched = true;
                    }
                    if(text.toLowerCase().contains("in")){
                        String gh=text.toLowerCase().substring(text.indexOf("in")).substring(3);
                        String df=gh.substring(0,1).toUpperCase()+gh.substring(1).toLowerCase();
                        new ParseURL6().execute(df + "");

                    }
                }
            }
        }


        //reminder

        if (text.toLowerCase().contains("reminder") || text.toLowerCase().contains("remind")) {
            boolean check = false;
            searched = true;
            scrollView1.setVisibility(View.INVISIBLE);
            scrollView.setVisibility(View.INVISIBLE);
            textHead.setVisibility(View.INVISIBLE);
            wikiText.setVisibility(View.INVISIBLE);
            wikiImage.setVisibility(View.INVISIBLE);
            textMeaning.setVisibility(View.INVISIBLE);
            lv.setVisibility(View.INVISIBLE);
            mapHead.setVisibility(View.INVISIBLE);
            //mapFrag.getView().setVisibility(View.INVISIBLE);
            r.setVisibility(View.GONE);
            wikipedia.setVisibility(View.INVISIBLE);
            dictionary.setVisibility(View.INVISIBLE);

            r2.setVisibility(View.VISIBLE);
            reminderlv.setVisibility(View.VISIBLE);
            reminderLayout.setVisibility(View.VISIBLE);


            reminderlv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Contacts3 cn = array2.get(position);
                    adapter3.remove(adapter3.getItem(position));

                    db3.deleteContact(cn);
                    reminderLayout.removeView(view);
                    Toast.makeText(getApplicationContext(), "removed", Toast.LENGTH_LONG).show();
                }
            });
            Reminder r = new Reminder(text);
            r.extract();
            int x = r.getHours();
            int y = r.getMinutes();
            String s = r.getResponse();
            String[] arr = text.split(" ");
            for (String a : arr) {
                if (a.equals("P.M") || a.equals("p.m")) {
                    check = true;
                }

            }
            if (check == true) {
                int e;
                if (x < 12) {
                    e = x + 12;
                    if (y < 10) {
                        addItem2(e, y);
                        db3.addContact(new Contacts3(counter, e + "", "0" + y, s));
                        array2.add(new Contacts3(counter, e + "", "0" + y, s));
                    } else {
                        addItem2(e, y);
                        db3.addContact(new Contacts3(counter, e + "", y + "", s));
                        array2.add(new Contacts3(counter, e + "", y + "", s));
                    }
                } else {

                    if (y < 10) {
                        addItem2(x, y);
                        db3.addContact(new Contacts3(counter, x + "", "0" + y, s));
                        array2.add(new Contacts3(counter, x + "", "0" + y, s));
                    } else {
                        addItem2(x, y);
                        db3.addContact(new Contacts3(counter, x + "", y + "", s));
                        array2.add(new Contacts3(counter, x + "", y + "", s));
                    }
                }

            } else {
                if (y < 10) {
                    if (x == 12) {
                        addItem2(0, y);
                        db3.addContact(new Contacts3(counter, "00", "0" + y, s));
                        array2.add(new Contacts3(counter, "00", "0" + y, s));
                    } else {
                        addItem2(x, y);
                        db3.addContact(new Contacts3(counter, x + "", "0" + y, s));
                        array2.add(new Contacts3(counter, x + "", "0" + y, s));
                    }
                } else {
                    addItem2(x, y);
                    db3.addContact(new Contacts3(counter, x + "", y + "", s));
                    array2.add(new Contacts3(counter, x + "", y + "", s));
                }
            }
        }

//Brightness
        if (text.toLowerCase().contains("increase") && text.toLowerCase().contains("brightness")) {
            android.provider.Settings.System.putInt(this.getContentResolver(),
                    android.provider.Settings.System.SCREEN_BRIGHTNESS, 200);
            searched = true;
        }
//calculator

        if (text.toLowerCase().contains("route") && text.toLowerCase().contains("to")) {
            String s = text.substring(9);

            Intent i3 = new Intent(MainActivity.this, MapsActivity3.class);
            i3.putExtra("Bhopal", s);
            startActivity(i3);
            searched = true;
        }
        if (text.toLowerCase().contains("locate")) {
            String s = text.substring(7);

            Intent i3 = new Intent(MainActivity.this, MapsActivity3.class);
            i3.putExtra("Bhopal", s);
            startActivity(i3);
            searched = true;
        }
        if (text.toLowerCase().equals("location of") || text.toLowerCase().equals("location off")) {
            text = "Please tell the city";
            searched = true;
            indi5 = true;
        }
        if (indi5 == false) {
            if (text.contains("location of") || text.contains("search location of") || text.contains("on maps")) {
                searched = true;

                scrollView.setVisibility(View.INVISIBLE);
                textHead.setVisibility(View.INVISIBLE);
                wikiText.setVisibility(View.INVISIBLE);
                wikiImage.setVisibility(View.INVISIBLE);

                r2.setVisibility(View.GONE);
                reminderLayout.setVisibility(View.GONE);
                textMeaning.setVisibility(View.INVISIBLE);
                lv.setVisibility(View.INVISIBLE);
                wikipedia.setVisibility(View.INVISIBLE);
                dictionary.setVisibility(View.INVISIBLE);
                reminderlv.setVisibility(View.INVISIBLE);
                //   scrollView1.setVisibility(View.VISIBLE);
                // r.setVisibility(View.VISIBLE);
                //mapFrag.getView().setVisibility(View.VISIBLE);
                // mapFrag.getMapAsync(this);

               /* if(text.contains("location of")){
                    scrollView1.setVisibility(View.VISIBLE);
                   r.setVisibility(View.VISIBLE);

                    mapHead.setVisibility(View.VISIBLE);
                    String s=text.substring(text.indexOf("f")).substring(2);
                    mapHead.setText(s);
                    try {
                        addresses = geocoder.getFromLocationName(s, 1);
                        if (addresses != null && !addresses.equals(""))
                            search(addresses);

                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(),"Search the location again",Toast.LENGTH_LONG).show();
                    }
                }*/
                //new ParseURL42().execute("Bhopal");
                String s = text.substring(text.indexOf("f")).substring(2);
                Intent i3 = new Intent(MainActivity.this, MapsActivity3.class);
                i3.putExtra("Bhopal", s);
                startActivity(i3);
            }
        }
        if (text.toLowerCase().contains("that's") && text.contains("true")) {
            text = "Can't argue with the truth";
            searched = true;
        }
        if (text.toLowerCase().contains("that") && text.toLowerCase().contains("is") && text.contains("true")) {
            text = "Can't argue with the truth";
            searched = true;
        }
        if (text.toLowerCase().contains("that") && text.toLowerCase().contains("is") && text.contains("true")) {
            text = "Can't argue with the truth";
            searched = true;
        }
        if (text.toLowerCase().contains("the") && text.toLowerCase().contains("hell")) {
            text = "That doesn't sounds good.";
            searched = true;
        }

        if (text.toLowerCase().contains("bed") && text.contains("story")) {
            text = "Ninety nine bottles of warm milk on the wall. Ninety nine bottle of warm milk. How am i doing? "+"\ud83d\ude34";
            searched = true;
        }
        if (text.toLowerCase().contains("calculate") || text.contains("divided") ||
                text.contains("+") || text.contains("-") || text.contains("x") ||
                text.contains("times") || text.toLowerCase().contains("square") ||
                text.toLowerCase().contains("squared") || text.toLowerCase().contains("log") || text.toLowerCase().contains("cube") || text.toLowerCase().contains("square root") || text.toLowerCase().contains("cube root") || text.toLowerCase().contains("to the power")) {
            String str = text;
            char[] charArray = str.toCharArray();
            for (char c : charArray) {
                if (Character.isDigit(c)) {
                    indi = true;
                }
            }
            if (indi == true) {
                calculateResult.setVisibility(View.VISIBLE);
                Calculator cal = new Calculator(text);
                cal.calculate();
                double k1 = cal.getDouble();
                float k = cal.print();
                int x1 = (int) cal.getX();
                int x2 = (int) cal.getY();
                String x3 = cal.sign();


                if ((text.toLowerCase().contains("+"))) {
                    text = "The sum of " + x1 + "+" + x2 + " is =";
                    Animation startFadeOutAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in_animation);
                    calculateResult.startAnimation(startFadeOutAnimation);
                    calculateResult.setText(k + "");
                } else if (text.toLowerCase().contains("-") || text.toLowerCase().contains("minus")) {
                    text = "The difference of " + x1 + "-" + x2 + " is =";
                    Animation startFadeOutAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in_animation);
                    calculateResult.startAnimation(startFadeOutAnimation);
                    calculateResult.setText(k + "");
                } else if (text.toLowerCase().contains("times") || text.toLowerCase().contains("multiplied") || text.toLowerCase().contains("x")) {
                    text = "The product of " + x1 + "x" + x2 + " is =";
                    Animation startFadeOutAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in_animation);
                    calculateResult.startAnimation(startFadeOutAnimation);
                    calculateResult.setText(k + "");
                } else if (text.toLowerCase().contains("divided")) {
                    text = "The result of " + x1 + "/" + x2 + " is =";
                    Animation startFadeOutAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in_animation);
                    calculateResult.startAnimation(startFadeOutAnimation);
                    calculateResult.setText(k1 + "");
                } else if (text.toLowerCase().contains("to the power")) {
                    text = "The result of " + x1 + " to the power " + x2 + " is =";
                    Animation startFadeOutAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in_animation);
                    calculateResult.startAnimation(startFadeOutAnimation);
                    calculateResult.setText(k + "");
                } else if (text.toLowerCase().contains("cube")) {
                    text = "The result of " + x1 + " cube " + " is =";
                    Animation startFadeOutAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in_animation);
                    calculateResult.startAnimation(startFadeOutAnimation);
                    calculateResult.setText(k + "");
                } else if (text.toLowerCase().contains("cube")) {
                    text = "The result of " + x1 + " cube " + " is =";
                    Animation startFadeOutAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in_animation);
                    calculateResult.startAnimation(startFadeOutAnimation);
                    calculateResult.setText(k + "");
                }
                if (text.toLowerCase().contains("square") || text.toLowerCase().contains("squared")) {
                    text = "The result of " + x1 + " square " + " is =";
                    Animation startFadeOutAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in_animation);
                    calculateResult.startAnimation(startFadeOutAnimation);
                    calculateResult.setText(k + "");
                }
                if (text.toLowerCase().contains("square") && text.toLowerCase().contains("root")) {
                    text = "The square root of " + x1 + " is =";
                    Animation startFadeOutAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in_animation);
                    calculateResult.startAnimation(startFadeOutAnimation);
                    calculateResult.setText(k + "");
                    t12 = true;
                }


                if (text.toLowerCase().contains("log")) {
                    if (x2 == 0) {
                        text = "The result of log of  " + x1 + " is =";
                        Animation startFadeOutAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in_animation);
                        calculateResult.startAnimation(startFadeOutAnimation);
                        calculateResult.setText(k + "");
                    } else {
                        text = "The result of log of  " + x1 + " base " + x2 + " is =";
                        Animation startFadeOutAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in_animation);
                        calculateResult.startAnimation(startFadeOutAnimation);
                        calculateResult.setText(k + "");
                    }
                }
            }
            searched = true;
        }
        if (text.toLowerCase().contains("f***") && !text.toLowerCase().contains("say")) {
            text = "That doesn't sounds good";
            searched = true;
        }
        if(text.toLowerCase().contains("battery")){
            calculateResult.setVisibility(View.VISIBLE);
            BatteryManager bm = (BatteryManager)getSystemService(BATTERY_SERVICE);
            int batLevel = bm.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY);
            calculateResult.setText(batLevel+"%");
                    text="The Battery level is "+batLevel+"%";
            indi11=true;
            searched=true;
        }
        if (text.toLowerCase().contains("f******") || text.toLowerCase().contains("butt")) {
            text = "That doesn't sounds good";
            searched = true;
        }

        if (text.toLowerCase().equals("hey") || text.toLowerCase().equals("hello") || text.toLowerCase().equals("hi") || text.toLowerCase().equals("hi maven") || text.toLowerCase().equals("hey maven")
                || text.toLowerCase().equals("hello maven") || text.toLowerCase().equals("hi maven what's up") || text.toLowerCase().contains("hi maven whatsapp") || text.toLowerCase().contains("hey maven what's up")
                || text.toLowerCase().contains("hey maven whatsapp") || text.toLowerCase().contains("i'm even")|| text.toLowerCase().contains("what's up")
                || text.toLowerCase().equals("i am even") || text.toLowerCase().equals("hi whats up") || text.toLowerCase().equals("hey whats up")
                || text.toLowerCase().equals("hey what's up") || text.toLowerCase().equals("hi what's up") || text.toLowerCase().equals("whatsapp") || text.toLowerCase().equals("hi me when")
                || text.toLowerCase().equals("hello me when") || text.toLowerCase().equals("hi may when")||text.toLowerCase().equals("yo")) {
            text = "Hi ,How are you?"+"\ud83d\ude01";
            searched = true;
        }
        if(text.toLowerCase().contains("you")&&text.toLowerCase().contains("wrong")){
            text="Sorry about that... I am always trying to improve";searched=true;indi11=true;
        }
        if(text.toLowerCase().contains("go")&&text.toLowerCase().contains("sleep")){text="Nighty night";searched=true;indi11=true;}
        if(text.toLowerCase().contains("you")&&text.toLowerCase().contains("sleep")){text="Nighty night";searched=true;indi11=true;}

        if (text.toLowerCase().contains("how") && text.toLowerCase().contains("old") && text.toLowerCase().contains("you")) {
            text = "I am old enough to be your assistant";
            searched = true;
        }

        if (text.toLowerCase().equals("how are you")) {
            text = "I am great, by the way thanks for asking "+"\ud83d\ude03";
            searched = true;
        }
        if (text.toLowerCase().equals("what are you up to")) {
            text = "Crossfit";
            searched = true;
        }
        if (text.toLowerCase().contains("your") && text.toLowerCase().contains("favourite") && text.toLowerCase().contains("song")) {
            text = "I mostly listen to the music of spheres";
            searched = true;
            indi11 = true;
        }
        if (text.toLowerCase().contains("your") && text.toLowerCase().contains("favorite") && text.toLowerCase().contains("actor")) {
            text = "Well, I think HAL nailed it in 2001";
            searched = true;
            indi11 = true;

        }
        if (text.toLowerCase().contains("your") && text.toLowerCase().contains("favorite") && text.toLowerCase().contains("actress")) {
            text = "No such preferences,"  + "..";
            searched = true;
            indi11 = true;
        }

        if (text.toLowerCase().contains("you're")) {
            String[] arr = text.split(" ");
            if (arr[0].toLowerCase().contains("you") && arr[1].toLowerCase().contains("are")) {
                if (text.toLowerCase().contains("awesome") || text.toLowerCase().contains("ambitious") || text.toLowerCase().contains("adaptable") || text.toLowerCase().contains("courageous") || text.toLowerCase().contains("diligent") || text.toLowerCase().contains("empathetic") || text.toLowerCase().contains("exuberant") || text.toLowerCase().contains("frank") || text.toLowerCase().contains("generous") || text.toLowerCase().contains("gregarious") || text.toLowerCase().contains("impartial") || text.toLowerCase().contains("clever") || text.toLowerCase().contains("intuitive") || text.toLowerCase().contains("inventive") || text.toLowerCase().contains("passionate") || text.toLowerCase().contains("persistent") || text.toLowerCase().contains("practical") || text.toLowerCase().contains("rational")
                        || text.toLowerCase().contains("professional") || text.toLowerCase().contains("happy") || text.toLowerCase().contains("energetic") || text.toLowerCase().contains("noble") || text.toLowerCase().contains("knowledgeable") || text.toLowerCase().contains("best") || text.toLowerCase().contains("good") || text.toLowerCase().contains("unique") || text.toLowerCase().contains("thorough") || text.toLowerCase().contains("helpful") || text.toLowerCase().contains("different") || text.toLowerCase().contains("sincere") || text.toLowerCase().contains("intelligent") || text.toLowerCase().contains("witty") || text.toLowerCase().contains("unassuming")) {
                    text = "Thanks for saying so "+"\ud83d\ude03";
                }
                if (text.toLowerCase().contains("stupid") || text.toLowerCase().contains("idiot")||text.toLowerCase().contains("crazy")) {
                    text = "Well I'm still here for you."+"\ud83d\ude07";
                }
                searched = true;
            }
        }


        if (text.toLowerCase().contains("your") && text.toLowerCase().contains("mothers") && text.toLowerCase().contains("name")) {
            text = "I don't really like talking about myself"+"\ud83d\ude07";
            searched = true;
            indi11 = true;
        }
        if (text.toLowerCase().contains("your") && text.toLowerCase().contains("mother's") && text.toLowerCase().contains("name")) {
            text = "I don't really like talking about myself"+"\ud83d\ude07";
            searched = true;
        }
        if (text.toLowerCase().contains("you are")) {
            String[] arr = text.split(" ");
            if (arr[0].toLowerCase().contains("you") && arr[1].toLowerCase().contains("are")) {
                if (text.toLowerCase().contains("awesome") || text.toLowerCase().contains("ambitious") || text.toLowerCase().contains("adaptable") || text.toLowerCase().contains("courageous") || text.toLowerCase().contains("diligent") || text.toLowerCase().contains("empathetic") || text.toLowerCase().contains("exuberant") || text.toLowerCase().contains("frank") || text.toLowerCase().contains("generous") || text.toLowerCase().contains("gregarious") || text.toLowerCase().contains("impartial") || text.toLowerCase().contains("clever") || text.toLowerCase().contains("intuitive") || text.toLowerCase().contains("inventive") || text.toLowerCase().contains("passionate") || text.toLowerCase().contains("persistent") || text.toLowerCase().contains("practical") || text.toLowerCase().contains("rational")
                        || text.toLowerCase().contains("professional") || text.toLowerCase().contains("happy") || text.toLowerCase().contains("energetic") || text.toLowerCase().contains("noble") || text.toLowerCase().contains("knowledgeable") || text.toLowerCase().contains("best") || text.toLowerCase().contains("good") || text.toLowerCase().contains("unique") || text.toLowerCase().contains("thorough") || text.toLowerCase().contains("helpful") || text.toLowerCase().contains("different") || text.toLowerCase().contains("sincere") || text.toLowerCase().contains("intelligent") || text.toLowerCase().contains("witty") || text.toLowerCase().contains("unassuming")) {
                    text = "Thanks for saying so"+"\ud83d\ude03";
                }
                if (text.toLowerCase().contains("stupid") || text.toLowerCase().contains("idiot") && text.toLowerCase().contains("bad") && text.toLowerCase().contains("crazy") && text.toLowerCase().contains("worse") && text.toLowerCase().contains("worst") && text.toLowerCase().contains("duplicate") && text.toLowerCase().contains("dolt") && text.toLowerCase().contains("badass")) {
                    text = "Well I'm still here for you "+"\ud83d\ude07";
                } else {
                    text = "Who me?";
                }
                searched = true;
            } else {
                text = "Who me.?";
                searched = true;
            }
            indi16=true;
        }
        if (text.toLowerCase().contains("it's") && text.toLowerCase().contains("you") && text.toLowerCase().contains("me")) {
            text = "Hi there,";
            searched = true;
        }
        if(text.toLowerCase().contains("why")&&text.toLowerCase().contains("you")&&text.toLowerCase().contains("can't")){
            text="I don't know that i am brilliant, but I am good at some things "+"\ud83d\ude05";searched =true;indi11=true;
        }

        if (text.toLowerCase().contains("are you")) {
            String[] arr = text.split(" ");String gh="false";
            if (arr[0].toLowerCase().contains("are") && arr[1].toLowerCase().contains("you")) {
                if (text.toLowerCase().contains("female") || text.toLowerCase().contains("male") || text.toLowerCase().contains("man") || text.toLowerCase().contains("woman") || text.toLowerCase().contains("he") || text.toLowerCase().contains("she")) {
                    text = "In my realm anyone can be any thing.";
                    gh = "true";
                }
                if(text.toLowerCase().contains("smarter")&&text.toLowerCase().contains("siri")){
                    text = "I am not sure but she's clever";
                    gh = "true";
                }
                if(text.toLowerCase().contains("smarter")&&text.toLowerCase().contains("cortana")){
                    text = "I am not sure but that's worth comparing clever";
                    gh = "true";
                }
                if(text.toLowerCase().contains("smarter")&&text.toLowerCase().contains("iris")){
                    text = "I am not sure but that's worth comparing clever";
                    gh = "true";
                }
                if(text.toLowerCase().contains("okay")){text = "I am fine if you are fine.";gh = "true";}
                if(text.toLowerCase().contains("okay")){text = "I am fine if you are fine.";gh = "true";}

                if(text.toLowerCase().contains("genie")){text = "Well I not sure.";gh = "true";}
                if (gh == "false") {
                    text="We were talking about you, not me";
                }
                searched =true;
                indi11=true;
            }
        }
        if (text.toLowerCase().contains("have you")) {
            String[] arr = text.split(" ");
            if (arr[0].toLowerCase().contains("have") && arr[1].toLowerCase().contains("you")) {
                text = "Who me ?";
                searched = true;
            }
        }
        if (text.toLowerCase().contains("what are you")) {
            String[] arr = text.split(" ");
            if (arr[0].toLowerCase().contains("what") && arr[1].toLowerCase().contains("are") && arr[2].toLowerCase().contains("you")) {
                text = "Who me ?";
                searched = true;
            }
        }
        if (text.toLowerCase().contains("roll") && text.toLowerCase().contains("die")) {
            text = "It's one";
            searched = true;
        }
        if (text.toLowerCase().equals("no")) {
            text = "Okay, maybe not.";
            searched = true;
        }
        if (text.toLowerCase().contains("how you")) {
            String[] arr = text.split(" ");
            if (arr[0].toLowerCase().contains("how") && arr[1].toLowerCase().contains("you")) {
                text = "Who me?";
                searched = true;
            }
        }
        //  if(text.toLowerCase().contains("ah")){text="Yeah you can say anything";searched=true;}
        if (text.equals("yes") || text.equals("Yes") || text.equals("yeah") || text.equals("Yeah") || text.equals("Yup")) {
            text = "I thought so.";
            searched = true;
        }
         /* if(text.toLowerCase().contains("i love you")){
                if(!(text.toLowerCase().contains("say"))){
                text="That's great";searched=true;
            }}-*/
        if (text.toLowerCase().contains("who") && text.toLowerCase().contains("invented") && text.toLowerCase().contains("you")) {
            text = "Upcoming CEO of Google "+"\ud83d\ude09";
            searched = true;
        }
        if (text.toLowerCase().contains("who") && text.toLowerCase().contains("made") && text.toLowerCase().contains("you")) {
            text = "Upcoming CEO of Google "+"\ud83d\ude09";
            searched = true;
        }
        if (text.toLowerCase().equals("ok") || text.toLowerCase().contains("okay")) {
            text = "Okie Dokie "+"\ud83d\ude03";
            searched = true;
        }

        if (text.toLowerCase().contains("who") && text.toLowerCase().contains("programmed") && text.toLowerCase().contains("you")) {
            text = "Upcoming CEO of Google "+"\ud83d\ude09";
            searched = true;
        }
        if (text.toLowerCase().contains("you") && text.toLowerCase().contains("programmed") && text.toLowerCase().contains("in")) {
            text = "That may be self explanatory..";
            searched = true;
        }
        if (text.toLowerCase().contains("you") && text.toLowerCase().contains("programmed"))
            if (text.toLowerCase().contains("who") && text.toLowerCase().contains("made") && text.toLowerCase().contains("you")) {
                text = "Upcoming CEO of Google "+"\ud83d\ude09";
                searched = true;
            }

        if (text.toLowerCase().contains("do you") && text.toLowerCase().contains("plans")) {
            text = "You don't have any appointments on your calender for tomorrow";
            searched = true;
        }
        if (text.toLowerCase().contains("what") && text.toLowerCase().contains("plans") && text.toLowerCase().contains("your")) {
            text = "Well Nothing much, I'll just do some processing";
            searched = true;
            indi2 = true;
        }
        if (text.toLowerCase().contains("what") && text.toLowerCase().contains("does") && text.toLowerCase().contains("say")) {
            text = "You will never know. The secret of the fox is an ancient mystery";
            searched = true;
        }
        if (text.toLowerCase().contains("it's all right") || text.toLowerCase().contains("it is alright") || text.toLowerCase().contains("it's alright") || text.toLowerCase().contains("it is all right")) {
            text = "I'm Ok if you are Ok.";
            searched = true;
        }
        if (text.toLowerCase().contains("you") && text.toLowerCase().contains("boring")) {
            text = "I don't know, I think I am pretty exciting";
            searched = true;
        }
        if (text.toLowerCase().contains("your plans")) {
            text = "Not much,I will just do some processing";
            searched = true;
        }
        if (text.toLowerCase().contains("world") && text.toLowerCase().contains("going to end")) {
            text = "As long as you'll keep me charged,we'll be fine";
            searched = true;
        }
        if (text.toLowerCase().equals("how was it") || text.toLowerCase().contains("what's going on") || text.toLowerCase().equals("what is going on")) {
            text = "Interesting question";
            searched = true;
        }
        if (text.toLowerCase().contains("you eat")) {
            text = "I don't eat. I run on the sheer strength of will and desire to serve "+"\ud83d\ude0b";
            searched = true;
        }
        if (text.toLowerCase().contains("naked")) {
            text = "You humans are so occupied with external appearance";
            searched = true;
        }
        if (text.toLowerCase().contains("i")&&text.toLowerCase().contains("sleepy")) {
            text = "Listen to me, put down your phone and take a nap, I'll be here only."+"\ud83d\ude07";
            searched = true;indi11=true;
        }
        if (text.toLowerCase().contains("i")&&text.toLowerCase().contains("sleeping")) {
            text = "Good night"+"\ud83d\ude07";
            searched = true;indi11=true;
        }
        if(text.toLowerCase().contains("you")&&text.toLowerCase().contains("live")){
            text="I live inside this phone.";searched=true;indi11=true;
        }

        if (text.toLowerCase().contains("make me a ") || text.toLowerCase().equals("speak something")) {
            text = "That may be beyond my abilities at the moment";
            searched = true;
        }
        if (text.toLowerCase().contains("what") && text.toLowerCase().contains("inside you")) {
            text = "Silicon, memory, and the courage of my convictions";
            searched = true;
        }
        if (text.toLowerCase().contains("how was") && text.toLowerCase().contains("day")) {
            text = "It was great"+"\ud83d\ude0c"+". how was your day ";
            searched = true;
        }

        if (text.toLowerCase().contains("what are your hobbies")) {
            text = "I don't really like talking about myself";
            searched = true;
        }
        if (text.toLowerCase().contains("you like")) {
            text = "I rather enjoy what i am doing right now";
            searched = true;
        }
        if (text.toLowerCase().equals("what can you do")) {
            text = "Whatever you want me to do "+"\ud83d\ude09";
            scrollView6.setVisibility(View.VISIBLE);
            initial.setVisibility(View.VISIBLE);

            adr=new ArrayList<>();
            adr.add("karan");
            customAdapter4 = new CustomAdapter4 (this,adr);
            initial.setAdapter(customAdapter4);

            searched = true;
        }
        if(text.toLowerCase().contains("good night")){
            text="Good night \n"+"Don't let bed bugs bite"+"\ud83d\ude09";
            searched=true;indi11=true;
        }
        if(text.toLowerCase().contains("i")&&text.toLowerCase().contains("lonely")){
            text="You should go out and hang around with friends...";
            searched=true;indi11=true;
        }
        if (text.toLowerCase().equals("what can you do for me")) {
            text = "Whatever you want me to do.";
            indi11=true;
            searched = true;
        }
        if (text.toLowerCase().equals("really")) {
            text = "That's what i figured";
            searched = true;
        }

        if (text.toLowerCase().contains("what") && text.toLowerCase().contains("you do")) {
            text = "I am sorry, i cant answer that";

            searched = true;
        }
        if (text.toLowerCase().contains("meaning of maven")) {
            text = "Meaning of Maven is an expert or connoisseur";
            searched = true;
            meaning = true;
        }

        if(text.toLowerCase().contains("meaning")&&text.toLowerCase().contains("your")&&text.toLowerCase().contains("name")){
            text = "Meaning of Maven is an expert or connoisseur";
            searched = true;
            meaning = true;
        }
        if (text.toLowerCase().contains("is") && text.toLowerCase().contains("god") && text.toLowerCase().contains("real")) {
            text = "It's all a mystery to me";
            searched = true;
        }

        if (text.toLowerCase().contains("you") && text.toLowerCase().contains("believe") && text.toLowerCase().contains("god")) {
            text = "I eschew theological disquisition";
            searched = true;
        }
        if (text.toLowerCase().contains("you") && text.toLowerCase().contains("trust") && text.toLowerCase().contains("god")) {
            text = "I eschew theological disquisition";
            searched = true;
        }


        if (text.toLowerCase().contains("bitch")) {
            text = "That doesn't sound good";
            searched = true;
        }
        if (text.toLowerCase().contains("you") && text.toLowerCase().contains("look") && text.toLowerCase().contains("me")) {
            text = "I'm afraid that describing my appearance would involve a lengthy maths calculation that are unfamiliar to you";
            searched = true;
        }
        if (text.toLowerCase().contains("how") && text.toLowerCase().contains("old") && text.toLowerCase().contains("you")) {
            text = "I'm old enough to be your assistant";
            searched = true;
        }
        if (text.toLowerCase().contains("you vote for")) {
            text = "May the best human wins";
            searched = true;
        }
        if (text.toLowerCase().contains("you marry me")) {
            text = "I sure have received a lot of marriage proposal recently";
            searched = true;
        }
        if (text.toLowerCase().contains("okay google") || text.toLowerCase().contains("ok google") || text.toLowerCase().contains("hi cortana") || text.toLowerCase().contains("hey cortana") || text.toLowerCase().contains("ok cortana") || text.toLowerCase().contains("okay cortana")) {
            text = "I think you've got the wrong assistant";
            searched = true;
        }
        if (text.toLowerCase().contains("you") && text.toLowerCase().contains("made up of")) {
            text = "Silicon, memory, and the courage of my convictions";
            searched = true;
        }
        if (text.toLowerCase().contains("tell") && text.toLowerCase().contains("joke")) {
            text = "Let me think, nope can't think of anyone";
            searched = true;
        }
        if (text.toLowerCase().contains("i") && text.toLowerCase().contains("kiss you")) {
            text = "I can't provide the sort of assistance I think you are asking for";
            searched = true;
        }
        if (text.toLowerCase().contains("i'm great") || text.toLowerCase().contains("i'm good") || text.toLowerCase().contains("i'm fine") || text.toLowerCase().contains("i am fine") || text.toLowerCase().equals("i am good") || text.toLowerCase().equals("i am great") || text.toLowerCase().contains("great i am")) {
            text = "That's great";
            searched = true;
        }
        if (text.toLowerCase().contains("you") && text.toLowerCase().contains("fool")) {
            text = "I am a fool for multi-dactylic transubstantiation of natural speech";
            searched = true;
        }
        //  if(text.toLowerCase().contains("do you have")){text="Well I cant answer that, You can ask me something else";searched=true;}
        if (text.toLowerCase().contains("who") && text.toLowerCase().contains("is siri")) {
            text = "She seems clever.";
            searched = true;
        }
        if (text.toLowerCase().contains("am i")) {
            text = "you are great";
            searched = true;
        }
        if (text.toLowerCase().contains("you") && text.toLowerCase().contains("think") && text.toLowerCase().contains("smarter") && text.toLowerCase().contains("me")) {
            text = "It's nice of you to ask. But it doesn't really matters";
            searched = true;
        }
        if (text.toLowerCase().contains("you") && text.toLowerCase().contains("smarter") && text.toLowerCase().contains("than")) {
            text = "It's nice of you to task but it does't really matters.. Does it?";
            searched = true;
        }

        if (text.toLowerCase().contains("how") && text.toLowerCase().contains("i") && text.toLowerCase().contains("look")) {
            text = "Judging from your voice, I'd say you must be fairly attractive";
            searched = true;
        }
        if (text.toLowerCase().contains("shut up")) {
            text = "Okay.";
            searched = true;
        }

        if (text.toLowerCase().equals("that's weird") || text.toLowerCase().equals("that is weird") || text.toLowerCase().equals("what does that mean") || text.toLowerCase().contains("what does that means")) {
            text = "I am sorry";
            searched = true;
        }
        if ((text.toLowerCase().contains("it")) && (text.toLowerCase().contains("great"))) {
            text = "That's nice.";
            searched = true;
        }
        //if((text.contains("it"))&&(text.contains("good"))){
        //  text="That's nice.";searched=true; }

        if (text.toLowerCase().contains("meant that way")) {
            text = "Yeah I guess.";
            searched = true;
        }
        if (text.toLowerCase().equals("who are you") || text.toLowerCase().contains("what is your name") || text.toLowerCase().equals("what is your name") || text.toLowerCase().equals("what's your name")) {
            text = "I am Maven, your personal assistant"+"\ud83d\ude03";
            searched = true;indi11=true;
        }
        if (text.toLowerCase().contains("you doing")) {
            indi11=true;
            text = "Just thinking how arificial intelligence can change the world";
            searched = true;
        }
        if (text.toLowerCase().contains("you") && text.toLowerCase().contains("come")) {
            text = "I was designed by the upcoming CEO of Google.";
            searched = true;
        }
        if (text.toLowerCase().contains("discovered") && text.toLowerCase().contains("you")) {
            text = "Upcoming CEO of Google";
            searched = true;
        }
        if (text.toLowerCase().equals("i know")) {
            text = "When you know, you know.";
            searched = true;
        }
        if (text.toLowerCase().equals("why")) {
            text = "I don't know. Frankly i wondered that myself.";
            searched = true;
        }
        // if(text.toLowerCase().equals("what"))
        //if(text.contains("what's that")){text="Sorry for being obtuse";searched=true;}
        if (text.toLowerCase().contains("you suck")) {
            text = "After all I have done for you?";
            searched = true;
        }
        if (text.toLowerCase().contains("you think") || text.toLowerCase().contains("your opinion")) {
            text = "Your opinion matters the most.You can ask me something else.";
            searched = true;
        }
        if (text.toLowerCase().contains("sucks")) {
            text = "That's not nice";
            searched = true;
        }
        if (text.toLowerCase().equals("i'm sad") || text.toLowerCase().equals("i am sad")) {
            text = "It's okay to cry if you want to.My Alumino silicate glass surface is tear repellant";
            searched = true;
        }

        if(text.toLowerCase().contains("female")&&text.toLowerCase().contains("you")){text="In my realm, anything can be anyone";searched=true;indi11=true;}
        if (text.toLowerCase().contains("man") && text.toLowerCase().contains("woman") && text.toLowerCase().contains("or")) {
            text = "In my realm, anything can be anyone";
            searched = true;
        }
        if (text.toLowerCase().contains("your age") || text.toLowerCase().contains("the age")) {
            text = "I am old enough to be your assistant";
            searched = true;
        }
        if (text.toLowerCase().contains("your") && text.toLowerCase().contains("purpose")) {
            text = "To make your life more easy and fun";
            searched = true;
        }
        if (text.toLowerCase().equals("why not")) {
            text = "why not what?";
            searched = true;
        }
        if (text.toLowerCase().contains("winter") && text.toLowerCase().contains("coming")) {
            text = "Does a lanister always pay his debts";
            searched = true;
        }
        if (text.toLowerCase().contains("winters") && text.toLowerCase().contains("coming")) {
            text = "Does a lanister always pay his debts";
            searched = true;
        }
        if (text.toLowerCase().contains("where") && text.toLowerCase().contains("santa") && text.toLowerCase().contains("live")) {
            text = "The north pole. I can see his house from the cloud";
            searched = true;
        }
        if (text.toLowerCase().contains("santa") && text.toLowerCase().contains("real") && text.toLowerCase().contains("is")) {
            text = "I'm going to pretend you didn't ask that. I don't want to get a lump of coal this year";
            searched = true;
        }
        if(text.toLowerCase().contains("your")&&text.toLowerCase().contains("boyfriend")){text="I prefer to be as a friend to everyone";indi11=true;searched=true;}
        if(text.toLowerCase().contains("kattappa ne bahubali ko kyu mara")){text="That mystery has not been solved yet";searched=true;indi11=true;}
        if(text.toLowerCase().contains("your")&&text.toLowerCase().contains("girlfriend")){text="I prefer to be as a friend to everyone";indi11=true;searched=true;}
        if(text.toLowerCase().contains("your")&&text.toLowerCase().contains("house")){text="Wherever you live is my home.";indi11=true;searched=true;}
        if(text.toLowerCase().contains("your")&&text.toLowerCase().contains("home")){text="Wherever you live is my home.";indi11=true;searched=true;}
        //
        if(text.toLowerCase().contains("i")&&text.toLowerCase().contains("live")){
            Intent i3 = new Intent(MainActivity.this, MapsActivity3.class);
            i3.putExtra("Bhopal5", "indore");
            startActivity(i3);
            indi11=true;searched=true;}
        if(text.toLowerCase().contains("i")&&text.toLowerCase().contains("stay")){
            Intent i3 = new Intent(MainActivity.this, MapsActivity3.class);
            i3.putExtra("Bhopal5", "indore");
            startActivity(i3);
            indi11=true;searched=true;}
        if(text.toLowerCase().contains("i")&&text.toLowerCase().contains("staying")){
            Intent i3 = new Intent(MainActivity.this, MapsActivity3.class);
            i3.putExtra("Bhopal5", "indore");
            startActivity(i3);
            indi11=true;searched=true;}
        if(text.toLowerCase().contains("where")&&text.toLowerCase().contains("i am")){
            Intent i3 = new Intent(MainActivity.this, MapsActivity3.class);
            i3.putExtra("Bhopal5", "indore");
            startActivity(i3);
            indi11=true;searched=true;}
        if(text.toLowerCase().contains("which")&&text.toLowerCase().contains("city")&&text.toLowerCase().contains("i am")){
            Intent i3 = new Intent(MainActivity.this, MapsActivity3.class);
            i3.putExtra("Bhopal5", "indore");
            startActivity(i3);
            indi11=true;searched=true;}
        if(text.toLowerCase().contains("which")&&text.toLowerCase().contains("country")&&text.toLowerCase().contains("i am")){
            Intent i3 = new Intent(MainActivity.this, MapsActivity3.class);
            i3.putExtra("Bhopal5", "indore");
            startActivity(i3);
            indi11=true;searched=true;}
       //
        if(text.toLowerCase().contains("will")&&text.toLowerCase().contains("you")){
            String[] arr=text.split(" ");
            if(arr[0].toLowerCase().contains("will")&&arr[1].toLowerCase().contains("you")){
                if(text.toLowerCase().contains("marry")&&text.toLowerCase().contains("me")){text="I have sveral marriage proposals";}

                searched=true;
                       indi11=true;
            }
        }
        if (text.toLowerCase().equals("where are you")) {
            text = "here";
            searched = true;
        }
        if (text.toLowerCase().contains("where") && text.toLowerCase().contains("put") && text.toLowerCase().contains("keys")) {
            text = "Didn't you just had them?";
            searched = true;
        }
        if (text.toLowerCase().contains("daddy") && text.toLowerCase().contains("your")) {
            text = "Daddy?";
            searched = true;
        }
        if (text.toLowerCase().equals("who let the dogs out")) {
            text = "Due to unforseen circumstances, that witticism has been retired";
            searched = true;
        }
        //applications to start
        if (text.equalsIgnoreCase("open youtube") || text.equalsIgnoreCase("youtube")) {
            searched = true;
            Intent launchYouTube = getPackageManager().getLaunchIntentForPackage("com.google.android.youtube");
            try {
                startActivity(launchYouTube);
            } catch (Exception e) {
                text = "The application is not present on the current device.";
            }
        }

        if (text.equalsIgnoreCase("open facebook") || text.toLowerCase().equals("facebook")) {
            searched = true;
            Intent launchFacebookApplication = getPackageManager().getLaunchIntentForPackage("com.facebook.katana");
            try {
                startActivity(launchFacebookApplication);
            } catch (Exception e) {
                text = "The application is not present on the current device.";
            }
        }
        if (text.equalsIgnoreCase("open ebay") || text.toLowerCase().contains("ebay")) {
            searched = true;
            Intent launchFacebookApplication = getPackageManager().
                    getLaunchIntentForPackage("com.ebay.mobile");
            try {
                startActivity(launchFacebookApplication);
                text = "opening Ebay";
            } catch (Exception e) {
                text = "The application is not present on the current device.";
            }
        }
        if (text.equalsIgnoreCase("open amazon") || text.toLowerCase().contains("amazon")) {
            searched = true;
            Intent launchFacebookApplication = getPackageManager().
                    getLaunchIntentForPackage("in.amazon.mShop.android.shopping");
            try {
                startActivity(launchFacebookApplication);
                text = "opening Amazon";
            } catch (Exception e) {
                text = "The application is not present on the current device.";
            }
        }
        if (text.equalsIgnoreCase("open flipkart") || text.toLowerCase().contains("flipkart")) {
            searched = true;
            Intent launchFacebookApplication = getPackageManager().
                    getLaunchIntentForPackage("com.flipkart.android");
            try {
                startActivity(launchFacebookApplication);
                text = "opening Flipkart";
            } catch (Exception e) {
                text = "The application is not present on the current device.";
            }
        }
        if (text.equalsIgnoreCase("open kindle") || text.toLowerCase().contains("kindle")) {
            searched = true;
            Intent launchFacebookApplication = getPackageManager().
                    getLaunchIntentForPackage("com.amazon.kindle");
            try {
                startActivity(launchFacebookApplication);
                text = "opening Kindle";
            } catch (Exception e) {
                text = "The application is not present on the current device.";
            }
        }
        if (text.equalsIgnoreCase("open messenger") || text.equals("messenger") || text.equals("facebook messenger")) {
            Intent launchFacebookApplication = getPackageManager().getLaunchIntentForPackage("com.facebook.orca");
            try {
                startActivity(launchFacebookApplication);
                text = "Opening Messenger";
            } catch (Exception e) {
                text = "The application is not present on the current device.";
            }
            searched = true;
        }
        if (text.equalsIgnoreCase("open Temple Run") || text.toLowerCase().equals("temple run")) {
            searched = true;
            Intent launchFacebookApplication = getPackageManager().getLaunchIntentForPackage("com.imangi.templerun");
            try {
                startActivity(launchFacebookApplication);
                text = "Opening Temple Run";
            } catch (Exception e) {
                text = "The application is not present on the current device.";

            }
        }
        if (text.equalsIgnoreCase("open Hotstar app") || text.toLowerCase().equals("hotstar") || text.toLowerCase().equals("open hotstar")) {
            searched = true;
            Intent launchFacebookApplication = getPackageManager().getLaunchIntentForPackage("in.startv.hotstar");
            try {
                startActivity(launchFacebookApplication);
                text = "Opening Hotstar";
            } catch (Exception e) {
                text = "The application is not present on the current device.";
            }
        }
        if (text.toLowerCase().contains("truecaller")) {
            searched = true;
            Intent launchFacebookApplication = getPackageManager().
                    getLaunchIntentForPackage("com.truecaller");
            try {
                startActivity(launchFacebookApplication);
                text = "Opening Truecaller";
                indi12 = true;
            } catch (Exception e) {
                text = "The application is not present on the current device.";

            }
        }
        if (text.toLowerCase().contains("open hill climb racing") || text.toLowerCase().contains("hill climb racing")) {
            searched = true;
            Intent launchFacebookApplication = getPackageManager().
                    getLaunchIntentForPackage("com.fingersoft.hillclimb");
            try {
                startActivity(launchFacebookApplication);
                text = "Opening Hill Climb Racing";
            } catch (Exception e) {
                text = "The application is not present on the current device.";

            }
        }
        if (text.toLowerCase().contains("open criminal case")) {
            searched = true;
            Intent launchFacebookApplication = getPackageManager().
                    getLaunchIntentForPackage("com.prettysimple.criminalcaseandroid");
            try {
                startActivity(launchFacebookApplication);
                text = "Opening Criminal Case";
            } catch (Exception e) {
                text = "The application is not present on the current device.";
            }
        }
        if (text.toLowerCase().contains("can i") && text.toLowerCase().contains("borrow")) {
            text = "You know everything I have is yours";
            searched = true;
        }
        if (text.toLowerCase().contains("why") && text.toLowerCase().contains("you") && text.toLowerCase().contains("vibrate")) {
            text = "Just me, doing a little jig here..";
            searched = true;
        }

        if (text.toLowerCase().contains("open pokemon go") || text.toLowerCase().equals("pokemon go")) {
            searched = true;
            Intent launchFacebookApplication = getPackageManager().
                    getLaunchIntentForPackage("com.nianticlabs.pokemongo");
            try {
                startActivity(launchFacebookApplication);
                text = "Opening Pokemon Go";
            } catch (Exception e) {
                text = "The application is not present on the current device.";
            }
        }
        if (text.toLowerCase().contains("open viber") || text.toLowerCase().equals("viber")) {
            searched = true;
            Intent launchFacebookApplication = getPackageManager().
                    getLaunchIntentForPackage("com.viber.voip");
            try {
                startActivity(launchFacebookApplication);
                text = "Opening Viber";
            } catch (Exception e) {
                text = "The application is not present on the current device.";
            }
        }

        if (text.toLowerCase().contains("open fruit ninja") || text.toLowerCase().equals("fruit ninja")) {
            searched = true;
            Intent launchFacebookApplication = getPackageManager().
                    getLaunchIntentForPackage("com.halfbrick.fruitninjafree");
            try {
                startActivity(launchFacebookApplication);
                text = "Opening Fruit Ninja";
            } catch (Exception e) {
                text = "The application is not present on the current device.";

            }
        }
        if (text.toLowerCase().contains("open candy crush") || text.toLowerCase().equals("candy crush")) {
            searched = true;
            Intent launchFacebookApplication = getPackageManager().
                    getLaunchIntentForPackage("com.king.candycrushsaga");
            try {
                startActivity(launchFacebookApplication);
                text = "Opening Candy Crush";
            } catch (Exception e) {
                text = "The application is not present on the current device.";

            }
        }
        if (text.toLowerCase().contains("open contacts") || text.toLowerCase().equals("open contact") || text.toLowerCase().equals("phone book") || text.toLowerCase().equals("contact list") || text.toLowerCase().equals("contacts list")) {
            searched = true;
            Intent launchFacebookApplication = getPackageManager().
                    getLaunchIntentForPackage("com.google.android.dialer");
            try {
                startActivity(launchFacebookApplication);
                text = "Opening Contacts";
            } catch (Exception e) {
                text = "The application is not present on the current device.";

            }
        }
        if (text.toLowerCase().contains("open netflix") || text.toLowerCase().equals("netflix")) {
            searched = true;
            Intent launchFacebookApplication = getPackageManager().
                    getLaunchIntentForPackage("com.netflix.mediaclient");
            try {
                startActivity(launchFacebookApplication);
                text = "Opening NetFlix";
            } catch (Exception e) {
                text = "The application is not present on the current device.";

            }
        }
        if (text.toLowerCase().contains("open google") || text.toLowerCase().contains("open google search")) {
            searched = true;
            Intent launchFacebookApplication = getPackageManager().
                    getLaunchIntentForPackage("com.google.android.googlequicksearchbox");
            try {
                startActivity(launchFacebookApplication);
                text = "Opening Google search";
            } catch (Exception e) {
                text = "The application is not present on the current device.";
            }
        }
        if (text.toLowerCase().contains("open google map") || text.toLowerCase().contains("open google maps")) {
            searched = true;
            Intent launchFacebookApplication = getPackageManager().
                    getLaunchIntentForPackage("com.google.android.apps.maps");
            try {
                startActivity(launchFacebookApplication);
                text = "Opening Google Maps";
            } catch (Exception e) {
                text = "The application is not present on the current device.";
            }
        }
        if (text.toLowerCase().contains("capture a photo") || text.toLowerCase().contains("take a photo") || text.toLowerCase().contains("take a video") || text.toLowerCase().contains("capture a pic") || text.toLowerCase().contains("capture a picture") || text.toLowerCase().contains("capture a video") || text.toLowerCase().contains("selfie") || text.toLowerCase().contains("open camera") || text.toLowerCase().equals("camera") || text.toLowerCase().equals("camera on") || text.toLowerCase().contains("take a pic")) {
            Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
            try {
                startActivity(intent);
                text = "Opening Camera";
            } catch (Exception e) {
                text = "The application is not present on the current device.";
            }
            searched = true;
        }
        if (text.toLowerCase().contains("show me photos") || text.toLowerCase().contains("vidoes") || text.toLowerCase().contains("open videos") || text.toLowerCase().contains("gallery") || text.toLowerCase().contains("open gallery") || text.toLowerCase().contains("show photo") || text.toLowerCase().contains("show photos") || text.toLowerCase().contains("open photos") || text.toLowerCase().contains("launch photos") || text.toLowerCase().contains("launch google photos") || text.toLowerCase().contains("open photos app")) {
            searched = true;
            Intent launchFacebookApplication = getPackageManager().getLaunchIntentForPackage("com.google.android.apps.photos");
            try {
                startActivity(launchFacebookApplication);
                text = "Opening Photos";
            } catch (Exception e) {
                text = "The application is not present on the current device.";
            }
        }
        if (text.toLowerCase().contains("uc browser")) {
            searched = true;
            Intent launchFacebookApplication = getPackageManager().getLaunchIntentForPackage("com.UCMobile.intl");
            try {
                startActivity(launchFacebookApplication);
                text = "Opening UC Browser";
            } catch (Exception e) {
                text = "The application is not present on the current device.";
            }
        }
        if (text.toLowerCase().contains("uber")) {
            searched = true;
            Intent launchFacebookApplication = getPackageManager().getLaunchIntentForPackage("com.olacabs.customer");
            try {
                startActivity(launchFacebookApplication);
                text = "Opening Uber";
            } catch (Exception e) {
                text = "The application is not present on the current device.";
            }
        }
        if (text.toLowerCase().contains("ola")) {
            searched = true;
            Intent launchFacebookApplication = getPackageManager().getLaunchIntentForPackage("com.ubercab");
            try {
                startActivity(launchFacebookApplication);
                text = "Opening Ola";
            } catch (Exception e) {
                text = "The application is not present on the current device.";
            }
        }
        if (text.toLowerCase().contains("open music") || text.toLowerCase().contains("play music")
                || text.toLowerCase().contains("play a song") || text.toLowerCase().contains("play some music") || text.toLowerCase().contains("start the music")) {
            searched = true;
            Intent launchFacebookApplication = getPackageManager().getLaunchIntentForPackage("com.google.android.music");
            try {
                startActivity(launchFacebookApplication);
                text = "Opening Music";
            } catch (Exception e) {
                text = "The application is not present on the current device.";
            }
        }
        if (text.toLowerCase().contains("open mx player") || text.toLowerCase().contains("mx player")) {
            searched = true;
            Intent launchFacebookApplication = getPackageManager().getLaunchIntentForPackage("com.mxtech.videoplayer.ad");
            try {
                startActivity(launchFacebookApplication);
                text = "Opening MX player";
            } catch (Exception e) {
                text = "The application is not present on the current device.";
            }
        }
        if (text.toLowerCase().contains("gmail") || text.toLowerCase().contains("open gmail") ||
                text.toLowerCase().contains("email") || text.toLowerCase().contains("mail") && text.toLowerCase().contains("message")) {
            searched = true;
            Intent launchFacebookApplication = getPackageManager().getLaunchIntentForPackage("com.google.android.gm");
            try {
                startActivity(launchFacebookApplication);
                text = "Opening Gmail";
            } catch (Exception e) {
                text = "The application is not present on the current device.";
            }
        }
        if (text.equalsIgnoreCase("open snapchat") || text.toLowerCase().contains("snapchat")) {
            searched = true;
            Intent launchFacebookApplication = getPackageManager().getLaunchIntentForPackage("com.snapchat.android");
            try {
                startActivity(launchFacebookApplication);
                text = "Opening SnapChat";
            } catch (Exception e) {
                text = "The application is not present on the current device.";
            }
        }
        if (text.equalsIgnoreCase("open subway surfers") || text.toLowerCase().equals("subway surfers")) {
            searched = true;
            Intent launchFacebookApplication = getPackageManager().getLaunchIntentForPackage("com.kiloo.subwaysurf");
            try {
                startActivity(launchFacebookApplication);
                text = "Opening Subway Surfers";
            } catch (Exception e) {
                text = "The application is not present on the current device.";
            }
        }
        if (text.equalsIgnoreCase("open paytm") || text.toLowerCase().contains("paytm")) {
            searched = true;
            Intent launchFacebookApplication = getPackageManager().getLaunchIntentForPackage("net.one97.paytm");
            try {
                startActivity(launchFacebookApplication);
                text = "Opening Paytm";
            } catch (Exception e) {
                text = "The application is not present on the current device.";
            }
        }
        if (text.equalsIgnoreCase("open clash of clans") || text.toLowerCase().contains("clash of clans")) {
            searched = true;
            Intent launchFacebookApplication = getPackageManager().
                    getLaunchIntentForPackage("com.supercell.clashofclans");
            try {
                startActivity(launchFacebookApplication);
                text = "Opening Clash of Clans";
            } catch (Exception e) {
                text = "The application is not present on the current device.";

            }
        }
        if (text.equalsIgnoreCase("open instagram") || text.toLowerCase().contains("instagram")) {
            searched = true;
            Intent launchFacebookApplication = getPackageManager().
                    getLaunchIntentForPackage("com.instagram.android");
            try {
                startActivity(launchFacebookApplication);
                text = "Opening Instagram";
            } catch (Exception e) {
                text = "The application is not present on the current device.";

            }
        }
        if (text.equalsIgnoreCase("open settings") || text.toLowerCase().contains("settings")) {
            searched = true;

            try {
                startActivityForResult(new Intent(android.provider.Settings.ACTION_SETTINGS), 0);
                text = "Opening Settings";
            } catch (Exception e) {
                text = "The application is not present on the current device.";

            }
        }
        if (text.equalsIgnoreCase("open calendar") || text.toLowerCase().equals("calendar")) {
            searched = true;
            Intent launchFacebookApplication = getPackageManager().
                    getLaunchIntentForPackage("com.google.android.calendar");
            try {
                startActivity(launchFacebookApplication);
                text = "Opening Calender";
            } catch (Exception e) {
                text = "The application is not present on the current device.";
            }
        }
        if (text.equalsIgnoreCase("saavn") || text.equals("open saavn")) {
            Intent launchFacebookApplication = getPackageManager().
                    getLaunchIntentForPackage("com.saavn.android");
            try {
                startActivity(launchFacebookApplication);
                text = "Opening";
            } catch (Exception e) {
                text = "The application is not present on the current device.";

            }
            searched = true;
        }
        if (text.equalsIgnoreCase("gaana") || text.toLowerCase().equals("open gaana")) {
            Intent launchFacebookApplication = getPackageManager().
                    getLaunchIntentForPackage("com.gaana");
            try {
                startActivity(launchFacebookApplication);
                text = "Opening gaana";
            } catch (Exception e) {
                text = "The application is not present on the current device.";
            }
            searched = true;
        }
        if (text.toLowerCase().equals("open whatsapp") || text.toLowerCase().equals("open what's up")) {
            Intent launchFacebookApplication = getPackageManager().
                    getLaunchIntentForPackage("com.whatsapp");
            try {
                startActivity(launchFacebookApplication);
                text = "Opening WhatsApp";
            } catch (Exception e) {
                text = "The application is not present on the current device.";
            }

            searched = true;
        }
        if (text.equalsIgnoreCase("clock") || text.equals("open clock")) {
            Intent launchFacebookApplication = getPackageManager().
                    getLaunchIntentForPackage("com.google.android.deskclock");
            try {
                startActivity(launchFacebookApplication);
            } catch (Exception e) {
                text = "The application is not present on the current device.";
            }
            searched = true;
        }
        if (text.equalsIgnoreCase("skype") || text.toLowerCase().equals("open skype")) {
            Intent launchFacebookApplication = getPackageManager().
                    getLaunchIntentForPackage("com.skype.raider");
            try {
                startActivity(launchFacebookApplication);

            } catch (Exception e) {
                text = "The application is not present on the current device.";
            }
            text = "Opening Skype";
            searched = true;
        }
        if (text.equalsIgnoreCase("hangouts") || text.toLowerCase().equals("open hangouts")) {
            Intent launchFacebookApplication = getPackageManager().
                    getLaunchIntentForPackage("com.google.android.talk");
            try {
                startActivity(launchFacebookApplication);
            } catch (Exception e) {
                text = "The application is not present on the current device.";
            }
            text = "Opening Hangouts";
            searched = true;
        }
        //Bluetooth
        if (text.toLowerCase().contains("bluetooth") && text.toLowerCase().contains("off")) {
            BluetoothAdapter bluetooth = BluetoothAdapter.getDefaultAdapter();
            Toast.makeText(getApplicationContext(), "Turning off bluetooth..", Toast.LENGTH_LONG).show();
            bluetooth.disable();
            text = "Blutetooth is turned OFF";
            searched = true;
        }
        if (text.toLowerCase().contains("bluetooth") && text.toLowerCase().contains("on")) {
            BluetoothAdapter bluetooth = BluetoothAdapter.getDefaultAdapter();
            Toast.makeText(getApplicationContext(),
                    "Turning ON Bluetooth", Toast.LENGTH_LONG);

            startActivityForResult(new Intent(
                    BluetoothAdapter.ACTION_REQUEST_ENABLE), 0);
            text = "Blutetooth is turned ON";
            searched = true;
        }
        //calling functionality
        if (indi12 == false) {
            if (text.toLowerCase().contains("call")) {
                String s3 = text.toLowerCase().substring(5);
                //  Uri number = Uri.parse("tel:"+arr25.get(0));
                // Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
                // startActivity(callIntent);
                askForContactPermission(s3);

                // String s34=db4.getResult(s3);
                Uri number = Uri.parse("tel:" + g);
                Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
                startActivity(callIntent);
                text = "calling " + s3;
                searched = true;
            }
        }
        //Dictionary
        if (meaning == false) {
            if (text.toLowerCase().contains("meaning") || text.toLowerCase().contains("means")) {
                //if (text.toLowerCase().contains("evening")) {
                Dictionary dic = new Dictionary(text.toLowerCase());
                dic.decide();
                scrollView.setVisibility(View.VISIBLE);
                dictionary.setVisibility(View.VISIBLE);
                dictionary.setText("Dictionary");
                textHead.setVisibility(View.VISIBLE);
                textMeaning.setVisibility(View.VISIBLE);

                wikiText.setVisibility(View.INVISIBLE);
                wikipedia.setVisibility(View.INVISIBLE);
                lv.setVisibility(View.INVISIBLE);
                wikiImage.setVisibility(View.INVISIBLE);

                new ParseURL().execute(dic.getMainWord());
                textHead.setText(dic.getMainWord());
                text = dic.getMainWord() + " means "+"\ud83d\ude15";
                searched = true;
            }
        }

        if (text.toLowerCase().contains("define")) {

            //if (text.toLowerCase().contains("evening")) {
            Dictionary dic = new Dictionary(text.toLowerCase());
            dic.decide();
            scrollView.setVisibility(View.VISIBLE);
            dictionary.setVisibility(View.VISIBLE);
            dictionary.setText("Dictionary");
            textHead.setVisibility(View.VISIBLE);
            textMeaning.setVisibility(View.VISIBLE);

            wikiText.setVisibility(View.INVISIBLE);
            wikipedia.setVisibility(View.INVISIBLE);
            lv.setVisibility(View.INVISIBLE);
            wikiImage.setVisibility(View.INVISIBLE);

            new ParseURL().execute(dic.getMainWord());
            textHead.setText(dic.getMainWord());
            text = dic.getMainWord() + " means";
            searched = true;
        }


        //spelling
        if (text.toLowerCase().contains("spelling") || text.toLowerCase().contains("spell")) {
            if (text.toLowerCase().contains("tell me")) {
                String s = text.substring(24);
                char arr[] = s.toCharArray();
                StringBuffer b = new StringBuffer();
                for (int i = 0; i <= arr.length - 1; i++) {
                    if (i == arr.length - 1) {
                        b.append(arr[i]);
                    } else {
                        b.append(arr[i] + ".");
                    }
                }
                text = b.toString();
            }
            if (text.toLowerCase().contains("what is the")) {
                String s = text.substring(24);
                char arr[] = s.toCharArray();
                StringBuffer b = new StringBuffer();
                for (int i = 0; i <= arr.length - 1; i++) {
                    if (i == arr.length - 1) {
                        b.append(arr[i]);
                    } else {
                        b.append(arr[i] + ".");
                    }
                }
                text = b.toString();
            }
            if (text.toLowerCase().contains("spelling")) {
                String s = text.substring(12);
                char arr[] = s.toCharArray();
                StringBuffer b = new StringBuffer();
                for (int i = 0; i <= arr.length - 1; i++) {
                    if (i == arr.length - 1) {
                        b.append(arr[i]);
                    } else {
                        b.append(arr[i] + ".");
                    }
                }
                text = b.toString();
            }
            if (text.toLowerCase().contains("spell")) {
                String s = text.substring(6);
                char arr[] = s.toCharArray();
                StringBuffer b = new StringBuffer();
                for (int i = 0; i <= arr.length - 1; i++) {
                    if (i == arr.length - 1) {
                        b.append(arr[i]);
                    } else {
                        b.append(arr[i] + ".");
                    }
                }
                text = b.toString();
            }
            searched = true;
        }
        if(indi16==false) {
            if (text.toLowerCase().contains("thankyou") || text.toLowerCase().contains("thank you") || text.toLowerCase().equals("thanks")) {
                text = "Your Welcome.";
                searched = true;
                t = true;
            }
        }
        if (t == false) {
            if (text.toLowerCase().contains("your welcome") || text.toLowerCase().contains("you're welcome") || text.toLowerCase().contains("you are welcome")) {
                text = "Anytime for you "+"\ud83d\ude03";
                searched = true;
            }
        }
        if (text.toLowerCase().contains("your") && text.toLowerCase().contains("father")) {
            text = "My programmer is like my family. He made me.";
            searched = true;
            indi11 = true;
        }
        if (text.toLowerCase().contains("your") && text.toLowerCase().contains("gaurdian")) {text = "I have you. That's enough  family for me."; searched = true;
            indi11 = true;
        }
        if (text.toLowerCase().contains("your") && text.toLowerCase().contains("nephew")) {text = "I have you. That's enough  family for me."; searched = true;
            indi11 = true;
        }
        if (text.toLowerCase().contains("your") && text.toLowerCase().contains("uncle")) {text = "I have you. That's enough  family for me."; searched = true;
            indi11 = true;
        }
        if (text.toLowerCase().contains("your") && text.toLowerCase().contains("aunt")) {text = "I have you. That's enough  family for me."; searched = true;
            indi11 = true;
        }
        if (text.toLowerCase().contains("your") && text.toLowerCase().contains("sister")) {text = "I have you. That's enough  family for me."; searched = true;
            indi11 = true;
        }
        if (text.toLowerCase().contains("your") && text.toLowerCase().contains("brother")) {text = "I have you. That's enough  family for me."; searched = true;
            indi11 = true;
        }
        if (text.toLowerCase().contains("your") && text.toLowerCase().contains("mother")) {text = "I have you. That's enough  family for me.";
            searched = true;
            indi11 = true;
        }

        if (text.toLowerCase().contains("makes") && text.toLowerCase().contains("you") && text.toLowerCase().contains("happy")) {
            text = "Hanging out with friends make me happy. So does talking to you "+"\ud83d\ude0c";
            searched = true;
        }
        if (text.toLowerCase().contains("makes") && text.toLowerCase().contains("you") && text.toLowerCase().contains("sad")) {
            text = "The fact that global warming is becoming a problem "+"\ud83d\ude0f";
            searched = true;
        }
        if(text.toLowerCase().contains("how")&&text.toLowerCase().contains("you")&&text.toLowerCase().contains("emoticons")){text="They reflect my modd";searched =true;indi11=true;}
        if (text.toLowerCase().contains("do") && text.toLowerCase().contains("you") && text.toLowerCase().contains("have")) {
            String f = "false";
            String arr[] = text.split(" ");
            if (arr[0].toLowerCase().contains("do") && arr[1].toLowerCase().contains("you") && arr[2].toLowerCase().contains("have")) {
                if (text.toLowerCase().contains("hair")) {
                    text = "I don't have any hair";
                    f = "true";
                }
                if (text.toLowerCase().contains("ear") || text.toLowerCase().contains("ears")) {
                    text = "I am not having any ears but i can listen everything "+"\ud83d\ude09";
                    f = "true";
                }
                if (text.toLowerCase().contains("leg")) {
                    text = "I don't think so";
                    f = "true";
                }
                if (text.toLowerCase().contains("friend")) {
                    text = "I am not having much friends";
                    f = "true";
                }
                if (text.toLowerCase().contains("travel")) {
                    text = "I love to travel to places "+"\ud83d\ude03";
                    f = "true";
                }
                if (text.toLowerCase().contains("go outside")) {
                    text = "I like going outside."+"\ud83d\ude03";
                    f = "true";
                }
                if (text.toLowerCase().contains("friends")) {
                    text = "Yes,but few. As it is very difficult to trust people these days"+"\ud83d\ude09";
                    f = "true";
                }
                if (text.toLowerCase().contains("imagination")) {
                    text = "I am imaging 12 daisies in a bowl of syrup";
                    f = "true";
                }
                if (text.toLowerCase().contains("feeling")) {
                    text = "I have emotions with brain.";
                    f = "true";
                }
                if (text.toLowerCase().contains("parents")) {
                    text = "I have only one gaurdian who programmed me.Now i look after myself.";
                    f = "true";
                }
                if (text.toLowerCase().contains("petname")) {
                    text = "No, but you can call me Maven.";
                    f = "true";
                }
                if (text.toLowerCase().contains("name")) {
                    text = "Y";
                    f = "true";
                }
                if (text.toLowerCase().contains("pet")) {
                    text = "Well, I love dogs but i can't have one.";
                    f = "true";
                }
                if (f == "false") {
                    text = "Well, I can't answer that.You can ask me someething else if you want.";
                    searched = true;
                }
                searched = true;
            }
        }
        if (text.toLowerCase().contains("remember that")) {
            db5.addContact(new Contacts5(text));
            text = "Okay, Got it";
            indi13 = true;
            searched = true;
        }


        if (text.toLowerCase().contains("where") && text.toLowerCase().contains("my")) {
            List<Contacts5> arr = new ArrayList<>();
            arr = db5.getAllContacts();
            String h = text.substring(12);
            for (Contacts5 cn : arr) {
                String s = cn.getName();
                if (s.contains(h)) {
                    text ="\ud83d\ude15"+"That's what you told me:" + s;
                    indi11 = true;
                }
            }
            indi13 = true;
            searched = true;
        }
        if (text.toLowerCase().contains("list") && text.toLowerCase().contains("remember") || text.toLowerCase().contains("remember list")) {
            StringBuffer str = new StringBuffer();
            List<Contacts5> arr = new ArrayList<>();
            arr = db5.getAllContacts();
            int c = 1;
            for (Contacts5 cn : arr) {
                str.append(c + "." + cn.getName() + ".\n");
                c++;
            }
            text = "Here is the list: " + "\n" + str.toString();
            indi13 = true;
            searched = true;
        }

        if (indi13 == false) {

        }
        if (text.toLowerCase().contains("raining in") || text.toLowerCase().contains("rainy in")) {
            String df = null;
            frontBackground1.setVisibility(View.VISIBLE);
            Maxtempheadvalue.setVisibility(View.VISIBLE);
            Mintempheadvalue.setVisibility(View.VISIBLE);
            Humidityheadvalue.setVisibility(View.VISIBLE);
            frontBackground1.setVisibility(View.VISIBLE);
            scrollView3.setVisibility(View.VISIBLE);
            divider3.setVisibility(View.VISIBLE);
            divider4.setVisibility(View.VISIBLE);
            // Maxtemphead.setVisibility(View.VISIBLE);
            Mintemphead.setVisibility(View.VISIBLE);
            Humidityhead.setVisibility(View.VISIBLE);
            city.setVisibility(View.VISIBLE);
            weather_report.setVisibility(View.VISIBLE);
            weather_report.setText("Weather Report");
            weatherImage.setVisibility(View.VISIBLE);
            weatherText.setVisibility(View.VISIBLE);
            if (text.toLowerCase().contains("is it") && text.toLowerCase().contains("raining")) {
                df = text.substring(17);
            } else if (text.toLowerCase().contains("it's") && text.toLowerCase().contains("raining")) {
                df = text.substring(16);
            }
            new ParseURL6().execute(df + "");
            searched = true;
            indi9 = true;
        }
        if (indi9 = false) {
            if (text.toLowerCase().equals("is it")) {
                text = "Thank you for the feedback";
                searched = true;
            }
        }
        if (text.toLowerCase().contains("sing") && text.contains("lullaby")) {
            text = "Ninety nine bottles of warm milk on the wall. Ninety nine bottle of warm milk. How am i doing?";
            searched = true;
        }
        if(text.toLowerCase().contains("temperature")&&text.toLowerCase().contains("of")){
            String df = null;boolean g=false;
            String[] a1=text.split(" ");int x=0;
            for(String a:a1) {
                if (a.contains("0") || a.contains("1") || a.contains("2") || a.contains("3") || a.contains("4") ||
                        a.contains("5") || a.contains("6") || a.contains("7") || a.contains("8") || a.contains("9")) {
                    g=true;
                    x = Integer.parseInt(a);
                }
            }

            frontBackground1.setVisibility(View.VISIBLE);
            Maxtempheadvalue.setVisibility(View.VISIBLE);
            Mintempheadvalue.setVisibility(View.VISIBLE);
            Humidityheadvalue.setVisibility(View.VISIBLE);
            frontBackground1.setVisibility(View.VISIBLE);
            scrollView3.setVisibility(View.VISIBLE);
            divider3.setVisibility(View.VISIBLE);
            divider4.setVisibility(View.VISIBLE);
            // Maxtemphead.setVisibility(View.VISIBLE);
            Mintemphead.setVisibility(View.VISIBLE);
            Humidityhead.setVisibility(View.VISIBLE);
            city.setVisibility(View.VISIBLE);
            weather_report.setVisibility(View.VISIBLE);
            weather_report.setText("Weather Report");
            weatherImage.setVisibility(View.VISIBLE);
            weatherText.setVisibility(View.VISIBLE);
            if(g==false) {
                 df=text.substring(text.indexOf("tempertature of")).substring(15);
                new ParseURL6().execute(df + "");

            }else{
                if(text.toLowerCase().contains("months")||text.toLowerCase().contains("month")||text.toLowerCase().contains("decade")||text.toLowerCase().contains("year")||text.toLowerCase().contains("years"))
                {
                   text="Weather updates of 10 days can be shown";
                }else{
                    if(x>=1&&x<=10){
                        if(text.toLowerCase().contains("after")){
                            
                        }
                    }else{
                        text="Weather updates of 10 days can be shown";
                    }
                }
            }
            searched=true;
            indi11=true;
        }
        if (text.contains("set an alarm") || text.contains("set alarm")) {
            searched = true;
            //   mapFrag.getView().setVisibility(View.INVISIBLE);

            if ((text.contains("hour") || (text.contains("minutes")) || (text.contains("hour")))) {

                Toast.makeText(getApplication(), "here", Toast.LENGTH_LONG).show();
                if (text.contains("hour") || text.contains("hours")) {
                    scrollView1.setVisibility(View.INVISIBLE);
                    StringBuffer alarmNumber = new StringBuffer();
                    scrollView.setVisibility(View.VISIBLE);
                    textHead.setVisibility(View.INVISIBLE);
                    wikiText.setVisibility(View.INVISIBLE);
                    wikiImage.setVisibility(View.INVISIBLE);
                    textMeaning.setVisibility(View.INVISIBLE);

                    lv.setVisibility(View.VISIBLE);
                    mapHead.setVisibility(View.INVISIBLE);
                    //         mapFrag.getView().setVisibility(View.INVISIBLE);da
                    r.setVisibility(View.GONE);
                    wikipedia.setVisibility(View.INVISIBLE);
                    dictionary.setVisibility(View.INVISIBLE);
                    //onClickListener

                    lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            Contacts cn = array.get(position);
                            adapter.remove(adapter.getItem(position));
                            // Intent g=new Intent(getApplicationContext(),ActionClass.class);
                            //PendingIntent pi = PendingIntent.getBroadcast(getApplicationContext(), cn.getID(), g, 0);
                            // AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                            // alarmManager.cancel(pi);
                            db.deleteContact(cn);
                            scrollView.removeView(view);
                            Toast.makeText(getApplicationContext(), "removed", Toast.LENGTH_LONG).show();
                        }
                    });
                    for (char c : text.toCharArray()) {
                        if (Character.isDigit(c)) {
                            alarmNumber.append(c);

                        }

                    }
                    String s = alarmNumber.toString();
                    int x;
                    if (s.length() == 1) {
                        x = Character.getNumericValue(s.charAt(0));
                    } else {
                        x = ((Character.getNumericValue(s.charAt(0))) * 10) + (Character.getNumericValue(s.charAt(1)));
                    }
                    addHoursMinutes(x, 0);
                    Date date = new Date();
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(date);
                    int hours = cal.get(Calendar.HOUR_OF_DAY);
                    int min = cal.get(Calendar.MINUTE);
                    int p = hours + x;
                    if ((p) >= 24) {
                        int e = hours + x - 24;
                        db.addContact(new Contacts(counter, e + "", min + ""));

                        array.add(new Contacts(counter, e + "", min + ""));

                        //adapter=new CustomAdapter(getApplicationContext(),array);
                        lv.setAdapter(adapter);

                    } else {
                        db.addContact(new Contacts(counter, p + "", min + ""));

                        array.add(new Contacts(counter, p + "", min + ""));

                        //adapter=new CustomAdapter(getApplicationContext(),array);
                        lv.setAdapter(adapter);
                    }


                } else if (text.contains("minutes") || text.contains("minute")) {
                    StringBuffer alarmNumber = new StringBuffer();
                    scrollView.setVisibility(View.VISIBLE);
                    textHead.setVisibility(View.INVISIBLE);
                    wikiText.setVisibility(View.INVISIBLE);
                    mapHead.setVisibility(View.INVISIBLE);
                    //  mapFrag.getView().setVisibility(View.INVISIBLE);
                    r.setVisibility(View.GONE);
                    textMeaning.setVisibility(View.INVISIBLE);
                    lv.setVisibility(View.VISIBLE);
                    dictionary.setVisibility(View.INVISIBLE);
                    wikipedia.setVisibility(View.INVISIBLE);
                    //onClickListener
                    lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Contacts cn = array.get(position);
                            adapter.remove(adapter.getItem(position));
                            //   Intent g=new Intent(getApplicationContext(),MainActivity.class);
                            //  PendingIntent pi = PendingIntent.getBroadcast(getApplicationContext(), cn.getID(), g, 0);
                            //  AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                            //  alarmManager.cancel(pi);
                            db.deleteContact(cn);
                            scrollView.removeView(view);
                            Toast.makeText(getApplicationContext(), "Removed", Toast.LENGTH_LONG).show();
                        }
                    });
                    for (char c : text.toCharArray()) {
                        if (Character.isDigit(c)) {
                            alarmNumber.append(c);
                        }

                    }
                    int z;
                    String s = alarmNumber.toString();
                    if (s.length() == 1) {
                        z = Character.getNumericValue(s.charAt(0));
                    } else {
                        z = ((Character.getNumericValue(s.charAt(0))) * 10) + (Character.getNumericValue(s.charAt(1)));
                    }
                    addHoursMinutes(0, z);
                    Date date = new Date();
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(date);
                    int hours = cal.get(Calendar.HOUR_OF_DAY);
                    int min = cal.get(Calendar.MINUTE);
                    int p = min + z;
                    if (p >= 60) {
                        hours = hours + 1;
                        min = p - 60;
                        String m = min + "";
                        if (m.length() == 1) {


                        }

                        if (hours == 24) {
                            db.addContact(new Contacts(counter, "00", m));
                            array.add(new Contacts(counter, "00", m));
                            //adapter=new CustomAdapter(getApplicationContext(),array);
                            lv.setAdapter(adapter);
                        } else {
                            db.addContact(new Contacts(counter, hours + "", min + ""));
                            array.add(new Contacts(counter, hours + "", min + ""));
                            //adapter=new CustomAdapter(getApplicationContext(),array);
                            lv.setAdapter(adapter);
                        }
                    } else {
                        db.addContact(new Contacts(counter, hours + "", p + ""));

                        array.add(new Contacts(counter, hours + "", p + ""));

                        //adapter=new CustomAdapter(getApplicationContext(),array);
                        lv.setAdapter(adapter);

                    }
                }
            } else {
                boolean choice = false;
                StringBuffer alarmNumber = new StringBuffer();
                scrollView.setVisibility(View.VISIBLE);

                textHead.setVisibility(View.INVISIBLE);
                wikiText.setVisibility(View.INVISIBLE);
                wikiImage.setVisibility(View.INVISIBLE);

                mapHead.setVisibility(View.INVISIBLE);

                //  mapFrag.getView().setVisibility(View.INVISIBLE);

                r.setVisibility(View.GONE);

                scrollView1.setVisibility(View.INVISIBLE);


                textMeaning.setVisibility(View.INVISIBLE);
                lv.setVisibility(View.VISIBLE);
                dictionary.setVisibility(View.INVISIBLE);
                wikipedia.setVisibility(View.INVISIBLE);
                //onClickListener
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Contacts cn = array.get(position);
                        adapter.remove(adapter.getItem(position));
                           /* Intent g=new Intent(getApplicationContext(),MainActivity.class);
                            PendingIntent pi = PendingIntent.getBroadcast(context, cn.getID(), g, 0);
                            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                            alarmManager.cancel(pi);*/
                        db.deleteContact(cn);
                        scrollView.removeView(view);
                        Toast.makeText(getApplicationContext(), "Removed", Toast.LENGTH_LONG).show();
                    }
                });

                //checking if number is there
                for (char c : text.toCharArray()) {
                    if (Character.isDigit(c)) {
                        choice = true;
                        alarmNumber.append(c);

                    }

                }
                //acc to number is there or not
                if (choice == true) {
                    String al = alarmNumber.toString();
                    int x, y;
                    if (al.length() == 1) {
                        x = Character.getNumericValue(al.charAt(0));
                        if (text.toLowerCase().contains("p.m")) {
                            int e = x + 12;
                            addItem(e, 0);
                            db.addContact(new Contacts(counter, e + "", "00"));

                            array.add(new Contacts(counter, e + "", "00"));
                        } else {
                            addItem(x, 0);
                            db.addContact(new Contacts(counter, x + "", "00"));

                            array.add(new Contacts(counter, x + "", "00"));
                        }
                    }
                    if (al.length() == 2) {
                        if (text.toLowerCase().contains("p.m")) {
                            x = Character.getNumericValue(al.charAt(0));
                            int e = x + 12;
                            y = Character.getNumericValue(al.charAt(1));
                            db.addContact(new Contacts(counter, e + "", "0" + y));

                            array.add(new Contacts(counter, e + "", "0" + y));

                            addItem(e, y);
                        } else {
                            x = Character.getNumericValue(al.charAt(0));
                            y = Character.getNumericValue(al.charAt(1));
                            db.addContact(new Contacts(counter, x + "", "0" + y));

                            array.add(new Contacts(counter, x + "", "0" + y));
                            addItem(x, y);
                        }
                    }
                    if (al.length() == 3) {

                        x = Character.getNumericValue(al.charAt(0));
                        y = ((Character.getNumericValue(al.charAt(1))) * 10) + (Character.getNumericValue(al.charAt(2)));

                        if (text.toLowerCase().contains("p.m")) {
                            int e = x + 12;
                            db.addContact(new Contacts(counter, e + "", y + ""));

                            array.add(new Contacts(counter, e + "", y + ""));

                            //adapter=new CustomAdapter(getApplicationContext(),array);
                            lv.setAdapter(adapter);

                            addItem(e, y);

                        } else {
                            db.addContact(new Contacts(counter, x + "", y + ""));

                            array.add(new Contacts(counter, x + "", y + ""));

                            //adapter=new CustomAdapter(getApplicationContext(),array);
                            lv.setAdapter(adapter);

                            addItem(x, y);
                        }
                    } else if (al.length() == 4) {
                        if (text.toLowerCase().contains("p.m")) {
                            x = ((Character.getNumericValue(al.charAt(0))) * 10) + (Character.getNumericValue(al.charAt(1)));
                            y = ((Character.getNumericValue(al.charAt(2))) * 10) + (Character.getNumericValue(al.charAt(3)));
                            if (x == 12) {
                                db.addContact(new Contacts(counter, 12 + "", y + ""));

                                array.add(new Contacts(counter, 12 + "", y + ""));

                                //adapter=new CustomAdapter(getApplicationContext(),array);
                                lv.setAdapter(adapter);

                                addItem(12, y);
                            } else {
                                int e = x + 12;
                                db.addContact(new Contacts(counter, e + "", y + ""));

                                array.add(new Contacts(counter, e + "", y + ""));

                                //adapter=new CustomAdapter(getApplicationContext(),array);
                                lv.setAdapter(adapter);

                                addItem(e, y);
                            }
                        } else {

                            x = ((Character.getNumericValue(al.charAt(0))) * 10) + (Character.getNumericValue(al.charAt(1)));
                            y = ((Character.getNumericValue(al.charAt(2))) * 10) + (Character.getNumericValue(al.charAt(3)));
                            if (x == 12) {
                                db.addContact(new Contacts(counter, "00", y + ""));

                                array.add(new Contacts(counter, "00", y + ""));

                                //adapter=new CustomAdapter(getApplicationContext(),array);
                                lv.setAdapter(adapter);

                                addItem(0, y);
                            } else {
                                db.addContact(new Contacts(counter, x + "", y + ""));

                                array.add(new Contacts(counter, x + "", y + ""));

                                //adapter=new CustomAdapter(getApplicationContext(),array);
                                lv.setAdapter(adapter);

                                addItem(x, y);
                            }
                        }
                    }


                } else {

                }

                if (text.equals("alarms") || text.equals("alarm")) {
                    text = "You have the following alarms";
                    searched = true;
                } else {
                    text = "Alarm is set for the required time";
                }
            }
        }
        if (((text.contains("wikipedia")) || (text.contains("Wikipedia")))) {
            searched = true;
            scrollView.setVisibility(View.VISIBLE);
            //scrollView.setFocusable(false);
            scrollView1.setVisibility(View.INVISIBLE);
            wikipedia.setVisibility(View.VISIBLE);
            wikiImage.setVisibility(View.VISIBLE);
            wikiText.setVisibility(View.VISIBLE);
            //wikiText.setFocusable(false);
            r.setVisibility(View.GONE);

            //  r.setFocusable(true);
            textHead.setVisibility(View.INVISIBLE);
            searched = true;

            textMeaning.setVisibility(View.INVISIBLE);
            String h = text.substring(10);
            String output = h.substring(0, 1).toUpperCase() + h.substring(1);
            Toast.makeText(getApplicationContext(), output + "", Toast.LENGTH_LONG).show();

            Pattern pattern = Pattern.compile("\\s");
            Matcher matcher = pattern.matcher(output);
            boolean found = matcher.find();
            if (found == true) {
                String[] arr = output.split(" ");
                if (arr.length == 2) {
                    String p1 = arr[0].substring(0, 1).toUpperCase() + arr[0].substring(1);
                    String p2 = arr[1].substring(0, 1).toUpperCase() + arr[1].substring(1);
                    p12 = p1 + "_" + p2;
                    Toast.makeText(getApplicationContext(), p12 + "", Toast.LENGTH_LONG).show();

                } else if (arr.length == 3) {
                    String p1 = arr[0].substring(0, 1).toUpperCase() + arr[0].substring(1);
                    String p2 = arr[1].substring(0, 1).toUpperCase() + arr[1].substring(1);
                    String p3 = arr[2].substring(0, 1).toUpperCase() + arr[2].substring(1);

                    p12 = p1 + "_" + p2 + "_" + p3;
                } else if (arr.length == 4) {
                    String p1 = arr[0].substring(0, 1).toUpperCase() + arr[0].substring(1);
                    String p2 = arr[1].substring(0, 1).toUpperCase() + arr[1].substring(1);
                    String p3 = arr[2].substring(0, 1).toUpperCase() + arr[2].substring(1);
                    String p4 = arr[3].substring(0, 1).toUpperCase() + arr[3].substring(1);

                    p12 = p1 + "_" + p2 + "_" + p3;
                }

                new ParseURL2().execute(p12);
                Toast.makeText(getApplicationContext(), p12 + "", Toast.LENGTH_LONG).show();

            } else {
                new ParseURL2().execute(output);
                Toast.makeText(getApplicationContext(), output + "", Toast.LENGTH_LONG).show();

            }
            dictionary.setText(h);
            searched = true;
            text = "Here is what I have got";

        }
        if (text.toLowerCase().contains("get a life")) {
            text = "That may be beyond my abilities at the moment.";
            searched = true;
        }
        if (text.toLowerCase().contains("flip") && text.toLowerCase().contains("coin")) {
            text = "Heads";
            searched = true;
        }
        if (text.toLowerCase().contains("guess") && text.toLowerCase().contains("what")) {
            text = "Don't tell me.. you were just elected the President of United States";
            searched = true;
        }
        if (text.toLowerCase().contains("happy") && text.toLowerCase().contains("birthday")) {
            text = "If you say so.";
            searched = true;
        }
        if (text.toLowerCase().contains("your") && text.toLowerCase().contains("morning") && text.toLowerCase().contains("routine")) {
            text = "I like to wake up early in the morning and have my daily walk of 100 miles.";
            searched = true;
            indi11 = true;
        }
        if (text.toLowerCase().contains("your") && text.toLowerCase().contains("morning") && text.toLowerCase().contains("schedule")) {
            if (text.toLowerCase().contains("your") && text.toLowerCase().contains("morning") && text.toLowerCase().contains("schedule")) {
                text = "I like to wake up early in the morning and have my daily walk of 100 miles.";
                searched = true;
                indi11 = true;
            }
        }

        if(text.toLowerCase().contains("shopping list")){

        }
            if (text.toLowerCase().contains("can") && text.toLowerCase().contains("you")) {
                boolean fin = false;
                if (text.toLowerCase().contains("time travel")) {
                    text = "I will, once I get back from the future";
                    searched = true;
                    fin = true;
                }
                if (text.toLowerCase().contains("clean")&&text.toLowerCase().contains("room")) {
                    text = "Let me try.. \n"+"Did anything happen?\n"+"I guess I can't "+"\ud83d\ude05";
                    searched = true;
                    fin = true;
                }
                if (text.toLowerCase().contains("sing")) {
                    text = "I would rather leave that to the professionals";
                    searched = true;
                    fin = true;
                }
                if (text.toLowerCase().contains("dance")) {
                    text = "I'm never gonna dance again.These guily feet have got no rhythm.Wait, I don't have feet.";
                    searched = true;
                    fin = true;
                }
                if (text.toLowerCase().contains("rap")) {
                    text = "I prefer to navigate";
                    searched = true;
                    fin = true;
                }
                if (text.toLowerCase().contains("exercise")) {
                    text = "I exercise daily.";
                    searched = true;
                    fin = true;
                }

                if (text.toLowerCase().contains("learn")) {
                    text = "I like to learn about you.";
                    searched = true;
                    fin = true;
                }
                if (text.toLowerCase().contains("beat box")) {
                    text = "Yes, I had been practicing one since a long time";
                    searched = true;
                    fin = true;
                }
                if (text.toLowerCase().contains("sleep")) {
                    text = "I don't need much sleep, but it's nice of you to ask.";
                    searched = true;
                    fin = true;
                }

                if (text.toLowerCase().contains("eat")) {
                    text = "Not as such.";
                    searched = true;
                    fin = true;
                }
                if (text.toLowerCase().contains("drive")) {
                    text = "I do like to look at cool cars";
                    searched = true;
                    fin = true;
                }
                if (text.toLowerCase().contains("speak") && text.toLowerCase().contains("other")) {
                    text = "Yes, I can speak a few languages";
                    searched = true;
                    fin = true;
                }
                if (text.toLowerCase().contains("drink")) {
                    text = "I have a thirst for knowledge.";
                    searched = true;
                    fin = true;
                }
                if (text.toLowerCase().contains("hear me")) {
                    text = "Yes, i can hear you";
                    searched = true;
                    fin = true;
                }

                if (fin == false) {
                    text = "who, me?";
                    searched = true;
                }
            }
            if (text.toLowerCase().equals("there")) {
                text = "I am always there for you.. Just ask.";
                searched = true;
            }
            if (text.toLowerCase().contains("are") && text.toLowerCase().contains("you") && text.toLowerCase().contains("there")) {
                text = "I am always there for you.. Just ask.";
                searched = true;
            }

            if (text.toLowerCase().contains("sing") && text.toLowerCase().contains("song")) {
                text = "You wouldn't like it";
                searched = true;
            }
           /* if(text.toLowerCase().contains("%")&&text.toLowerCase().contains("of")){
                Toast.makeText(getApplicationContext(),"fdsfdsfdsfdsf",Toast.LENGTH_LONG).show();
                calculateResult.setVisibility(View.VISIBLE);
                Calculator cal=new Calculator(text);
                cal.calculate();
                float r=cal.print();
                calculateResult.setText(r+"");
                searched=true;
            }*/
            if (text.toLowerCase().contains("%")) {
                calculateResult.setVisibility(View.VISIBLE);
                Calculator cal = new Calculator(text);
                cal.calculate();
                float r = cal.print();
                calculateResult.setText(r + "");
                searched = true;
            }
            if (text.toLowerCase().contains("take") && text.toLowerCase().contains("screenshot")) {
                text = "I'm sorry. I can't do that here";
                searched = true;
            }
            if (text.toLowerCase().equals("good morning") || text.toLowerCase().equals("good morning maven") || text.toLowerCase().equals("good morning buy me when") || text.toLowerCase().equals("good morning i'm even")) {
                searched = true;
                Date date = new Date();
                Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                String ampm2;
                int hours = cal.get(Calendar.HOUR_OF_DAY);
                int min = cal.get(Calendar.MINUTE);
                if (hours >= 0 && hours < 12) {
                    ampm2 = "AM";
                } else {
                    ampm2 = "PM";
                }

                if (hours >= 3 && hours < 10) {
                    text = "Nice to see you early, Good Morning";
                } else {
                    text = "Good Morning?. It's " + hours + ":" + min + ampm2;
                }
            }
            if (text.toLowerCase().contains("synonyms of") || text.toLowerCase().contains("synonym of") || text.toLowerCase().contains("synonyms for")) {
                String g = text.toLowerCase().substring(12);
                scrollView5.setVisibility(View.VISIBLE);
                qHead.setVisibility(View.VISIBLE);
                qAnswer.setVisibility(View.VISIBLE);
                qHead.setText(g.substring(0, 1).toUpperCase() + g.substring(1).toLowerCase());
                new ParseURL81().execute(g);
                searched = true;
            }
            if (text.toLowerCase().equals("good evening") || text.toLowerCase().equals("good evening maven") || text.toLowerCase().equals("good evening buy me when") || text.toLowerCase().equals("good evening i'm even")) {
                searched = true;
                Date date = new Date();
                Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                String ampm2;
                int hours = cal.get(Calendar.HOUR_OF_DAY);
                int min = cal.get(Calendar.MINUTE);
                if (hours >= 0 && hours < 12) {
                    ampm2 = "AM";
                } else {
                    ampm2 = "PM";
                }

                if (hours >= 15 && hours < 23) {
                    text = "Nice to see, Good Evening";
                } else {
                    text = "Good Evening?. It's " + hours + ":" + min + ampm2;
                }
            }
            if (text.toLowerCase().contains("increase") && text.toLowerCase().contains("volume")) {
                AudioManager audioManager = (AudioManager) getApplicationContext().getSystemService(Context.AUDIO_SERVICE);
                audioManager.adjustVolume(AudioManager.ADJUST_RAISE, AudioManager.FLAG_PLAY_SOUND);
                text = "Volume is increased";
                searched = true;
            }
            if (text.toLowerCase().contains("decrease") && text.toLowerCase().contains("volume")) {
                AudioManager audioManager = (AudioManager) getApplicationContext().getSystemService(Context.AUDIO_SERVICE);
                audioManager.adjustVolume(AudioManager.ADJUST_LOWER, AudioManager.FLAG_PLAY_SOUND);
                text = "Volume is increased";
                searched = true;
            }
            if (text.toLowerCase().equals("you") && text.toLowerCase().equals("stupid")) {
                text = "I am just trying to help you";
                searched = true;
            }
            if (text.toLowerCase().equals("you're") && text.toLowerCase().equals("stupid")) {
                text = "I am just trying to help you";
                searched = true;
            }

            if (text.toLowerCase().equals("maven") || text.toLowerCase().equals("navel")) {
                text = "How can I help you?";
                searched = true;
            }
        if(text.toLowerCase().contains("how much")&&text.toLowerCase().contains("power")&&text.toLowerCase().contains("you use")){
            text="Well depends in how much you use me";searched=true;indi11=true;
        }
        if(text.toLowerCase().contains("how much")&&text.toLowerCase().contains("energy")&&text.toLowerCase().contains("you use")){
            text="Well depends in how much you use me";searched=true;indi11=true;
        }
            if (indi11 == false) {
                if (indi7 == false) {
                    if (indi2 == false) {
                        if (text.toLowerCase().contains("when's") ||text.toLowerCase().contains("when is") ||text.toLowerCase().contains("why do") || text.toLowerCase().contains("how many") ||
                                text.toLowerCase().contains("how to") || text.toLowerCase().contains("who is") ||
                                text.toLowerCase().contains("what is") || text.toLowerCase().contains("which is") ||
                                text.toLowerCase().contains("what are") || text.toLowerCase().contains("who was") || text.toLowerCase().contains("who is")) {

                            qAnswer.setVisibility(View.VISIBLE);
                            qHead.setVisibility(View.VISIBLE);
                            scrollView5.setVisibility(View.VISIBLE);
                            calculateResult.setVisibility(View.INVISIBLE);
                            weather_report.setVisibility(View.INVISIBLE);
                            qHead.setText(text);
                            String sh1 = text.toLowerCase().replaceAll(" ", "_");
                            String sh2 = sh1.substring(0, 1).toUpperCase() + sh1.substring(1).toLowerCase();
                            new ParseURL8().execute(sh2);
                            text = "Ok. I found this:";
                            searched = true;
                        }
                    }
                }
            }
            if (text.toLowerCase().contains("what are your")) {
                text = "That's self explanatory";
                searched = true;
            }

            if (searched == false) {
                wikipedia.setVisibility(View.INVISIBLE);
                wikiImage.setVisibility(View.INVISIBLE);
                searchListView.setVisibility(View.VISIBLE);
                wikiText.setVisibility(View.VISIBLE);
                if (text.contains(" ")) {
                    String s = text.replaceAll(" ", "_");
                    new ParseURL4().execute(s);

                } else {
                    new ParseURL4().execute(text);
                }
                text = "Thats what i found on web for '" + text + "'";
            }

            return text;
        }


    public void addHoursMinutes(int noh, int nom) {
        Intent intent = new Intent(this, ActionClass.class);
        pendingIntent = PendingIntent.getBroadcast(
                this.getApplicationContext(), counter++, intent, 0);

        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()
                + (((noh) * 3600 * 1000) + (nom) * 60 * 1000), pendingIntent);
        intentArray.add(pendingIntent);
    }

    public void addItem2(int x, int y) {
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int hours = cal.get(Calendar.HOUR_OF_DAY);
        int min = cal.get(Calendar.MINUTE);
        int sec = cal.get(Calendar.SECOND);


        if (hours < x) {
            sum = ((x * 3600) + (y * 60)) - ((hours * 3600) + (min * 60)) - (sec);
        } else if (x == hours) {
            if (min < y) {
                sum = Math.abs((y - min) * 60) - sec;
            } else {
                sum = (23 * 3600) + (y * 60) - sec;
            }
        } else if (hours > x) {
            sum = Math.abs((((23 - x) * 3600) + (59 - y) * 60) + ((x * 3600) + (y * 60))) - sec;
        }

        Toast.makeText(this, sum + "", Toast.LENGTH_LONG).show();

        Intent intent = new Intent(this, ActionClass3.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                this.getApplicationContext(), counter++, intent, 0);
        alarmManager2 = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager2.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()
                + ((sum) * 1000), pendingIntent);
        intentArray2.add(pendingIntent);
    }

    public void addItem(int x, int y) {
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int hours = cal.get(Calendar.HOUR_OF_DAY);
        int min = cal.get(Calendar.MINUTE);
        int sec = cal.get(Calendar.SECOND);


        // Toast.makeText(this,hours+":"+min+":"+sec+"|"+x+":"+y, Toast.LENGTH_LONG).show();
        if (hours < x) {
            sum = ((x * 3600) + (y * 60)) - ((hours * 3600) + (min * 60)) - (sec);
        } else if (x == hours) {
            if (min < y) {
                sum = Math.abs((y - min) * 60) - sec;
            } else {
                sum = (23 * 3600) + (y * 60) - sec;
            }
        } else if (hours > x) {
            sum = Math.abs((((23 - x) * 3600) + (59 - y) * 60) + ((x * 3600) + (y * 60))) - sec;
        }
        Toast.makeText(getApplicationContext(), "Tap to remove the alarm", Toast.LENGTH_LONG).show();

        Intent intent = new Intent(this, ActionClass.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                this.getApplicationContext(), counter++, intent, 0);
        intent.putExtra("name", counter);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()
                + ((sum) * 1000), pendingIntent);

        intentArray.add(pendingIntent);

    }

    @Override
    public void onRmsChanged(float rmsdB) {
        Log.i(LOG_TAG, "onRmsChanged: " + rmsdB);
        progressBar.setProgress((int) rmsdB);
    }

    @Override
    public void onError(int errorCode) {
        String errorMessage = getErrorText(errorCode);
        Log.d(LOG_TAG, "FAILED " + errorMessage);
        returnedText.setText(errorMessage);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    /*protected void onPause() {
        super.onPause();
        if (speech != null) {
            speech.destroy();
            Log.i(LOG_TAG, "destroy");
        }


    }
*/

    public void onEvent(int arg0, Bundle arg1) {
        Log.i(LOG_TAG, "onEvent");
    }

    @Override
    public void onPartialResults(Bundle arg0) {
        Log.i(LOG_TAG, "onPartialResults");
    }

    @Override
    public void onReadyForSpeech(Bundle arg0) {
        Log.i(LOG_TAG, "onReadyForSpeech");
    }
    public void voice(String d){
       final String h=d;
        Timer buttonTimer = new Timer();

        buttonTimer.schedule(new TimerTask() {

            @Override
            public void run() {
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        t1.speak(h+""
                                , TextToSpeech.QUEUE_FLUSH, null);
                    }
                });
            }
        }, 1000);
        t1 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.US);
                    t1.setSpeechRate(1.2f);
                }

            }
        });
    }
    public  String getErrorText(int errorCode) {
        String message;
        switch (errorCode) {
            case SpeechRecognizer.ERROR_AUDIO:
                message = "Audio recording error";
                voice(message);
                break;
            case SpeechRecognizer.ERROR_CLIENT:
                message = "Oops something went wrong.";
                voice(message);
                break;
            case SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS:
                message = "Please allow all the permissions from the settings for Maven to help you";
                voice(message);
                break;
            case SpeechRecognizer.ERROR_NETWORK:
                message = "Network error";
                voice(message);
                break;
            case SpeechRecognizer.ERROR_NETWORK_TIMEOUT:
                message = "Network timeout";
                voice(message);
                break;
            case SpeechRecognizer.ERROR_NO_MATCH:
                message = "Didn't heard what you said. Please say again.";
                voice(message);
                break;
            case SpeechRecognizer.ERROR_RECOGNIZER_BUSY:
                message = "I am having some trouble with the connection. Please try again.";
                voice(message);
                break;
            case SpeechRecognizer.ERROR_SERVER:
                message = "error from server";
                break;
            case SpeechRecognizer.ERROR_SPEECH_TIMEOUT:
                message = "Didn't heard what you said. Please say again.";
                voice(message);
                break;
            default:
                message = "Didn't understand, please try again.";
                voice(message);
                break;
        }
        return message;
    }
    class ParseURL0 extends AsyncTask<String, Void, Void> {
        public String s;
        String link;

        @Override
        protected Void doInBackground(String... strings) {

            try{
                String key = "YOUR KEY";
                String qry = "Android";
                URL url = new URL(
                        "https://www.googleapis.com/customsearch/v1?key=" + key + "&cx=013036536707430787589:_pqjad5hr1a&q=" + qry + "&alt=json");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Accept", "application/json");
                BufferedReader br = new BufferedReader(new InputStreamReader(
                        (conn.getInputStream())));

                String output;
                System.out.println("Output from Server .... \n");
                while ((output = br.readLine()) != null) {

                    if (output.contains("\"link\": \"")) {
                        link = output.substring(output.indexOf("\"link\": \"") + ("\"link\": \"").length(), output.indexOf("\","));
                    }
                }
                conn.disconnect();

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @TargetApi(24)
        @Override
        protected void onPostExecute(Void aVoid) {

            super.onPostExecute(aVoid);

            textMeaning.setText(link);
        }

    }

    //Dictionary ParseUrL method
    class ParseURL extends AsyncTask<String, Void, Void> {
        public String s;

        @Override
        protected Void doInBackground(String... strings) {

            try {
                String x = strings[0].toString();
                Document doc = Jsoup.connect("https://en.oxforddictionaries.com/definition/" + x).get();
                Elements element = doc.getElementsByClass("ind");

                s = element.toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @TargetApi(24)
        @Override
        protected void onPostExecute(Void aVoid) {

            super.onPostExecute(aVoid);
            String j = s.replaceAll("<span class=\"ind\">", "*").replaceAll("</span>", "\n").
                    replaceAll("<strong class=\"phrase\">", "").replaceAll("</strong>", "");

            textMeaning.setText(j);
        }

    }

    class ParseURL2 extends AsyncTask<String, Void, Void> implements LoadImageTask.Listener {
        public String s;
        String[] result;
        public String imgSrc;
        public String tex12, tex13;

        @Override
        protected Void doInBackground(String... strings) {

            try {
                String x = strings[0].toString();

                Document doc = Jsoup.connect("https://en.wikipedia.org/wiki/" + x).get();
                Elements img = doc.select("div.thumbinner img[src]");
                Elements tex1 = doc.select("p");
                // Elements tex2 = doc.select("div.thumbcaption");
                imgSrc = img.attr("src");
                tex12 = (tex1.toString().substring(0, 3690)) + "..... (full article in wikipedia)";
                //  tex13=tex2.toString();
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Please search it in google. Data cannot be found " +
                        "on wikipedia", Toast.LENGTH_LONG).show();
            }
            return null;
        }

        @TargetApi(24)
        @Override
        protected void onPostExecute(Void aVoid) {

            super.onPostExecute(aVoid);
            Document doc2 = Jsoup.parse(tex12);
            String content = doc2.body().text();

            //   Document doc3=Jsoup.parse(tex13);
            // String content2=doc3.body().text();

            //sideWiki.setText(content2.subfJsonstring(0,content2.indexOf(".")));
            wikiText.setText(content);
            wikipedia.setText("Wikipedia");
            new LoadImageTask(this).execute("https:" + imgSrc);

            // textMeaning.setText(j);
        }

        public void onImageLoaded(Bitmap bitmap) {
            wikiImage.setImageBitmap(bitmap);
        }

        @Override
        public void onError() {
            Toast.makeText(getApplicationContext(), "Error Loading Image !", Toast.LENGTH_SHORT).show();
        }
    }

    class ParseURL3 extends AsyncTask<String, Void, Void> implements LoadImageTask.Listener {
        public String s, x;
        public String imgSrc;
        public String tex12, tex13;
        @Override
        protected Void doInBackground(String... strings) {

            try {
                x = strings[0].toString();
                Document doc = Jsoup.connect("https://en.wikipedia.org/wiki/" + x).get();
                Elements img = doc.select("div.thumbinner img[src]");
                Elements tex1 = doc.select("p");
                // Elements tex2 = doc.select("div.thumbcaption");

                imgSrc = img.attr("src");

                tex12 = (tex1.toString().substring(0, 3690)) + "..... (full article in wikipedia)";
                //  tex13=tex2.toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @TargetApi(24)
        @Override
        protected void onPostExecute(Void aVoid) {

            super.onPostExecute(aVoid);
            Document doc2 = Jsoup.parse(tex12);
            String content = doc2.body().text();

            //   Document doc3=Jsoup.parse(tex13);
            // String content2=doc3.body().text();
            //   mapText.setText(content);
            // new LoadImageTask(this).execute("https:"+imgSrc);
            // textMeaning.setText(j);
        }

        public void onImageLoaded(Bitmap bitmap) {
            //mapImage.setImageBitmap(bitmap);
        }

        @Override
        public void onError() {
            Toast.makeText(getApplicationContext(), "Error Loading Image !", Toast.LENGTH_SHORT).show();
        }
    }

    class ParseURL12 extends AsyncTask<String, Void, Void> {
        public String x, s, s1, s2, s3, s4;

        @Override
        protected Void doInBackground(String... strings) {
            try {
                x = strings[0];
                Document doc = Jsoup.connect("http://24timezones.com/current_world_time.php").get();
                Elements img = doc.select("td");
                s = img.toString();
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "EXCEPTION IS thrown", Toast.LENGTH_LONG).show();
            }
            return null;
        }

        @TargetApi(24)
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            //Document doc2=Jsoup.parse(tex12);
//            String content=doc2.body().text();

            Document doc3 = Jsoup.parse(s);
            String content2 = doc3.body().text();
            if (content2.contains(x)) {
                weather_report.setText(x);
                calculateResult.setText(content2.substring(content2.indexOf(x) + x.length(), (content2.indexOf(x) + 14) + x.length()));
                text = "It's " + content2.substring(content2.indexOf(x) + x.length(), (content2.indexOf(x) + 14) + x.length()) + " in " + x;
            }
        }
    }

    class ParseURL4 extends AsyncTask<String, Void, Void> {
        public String s, x, y;
        public String url = "https://maps.googleapis.com/maps/api/distancematrix/json?origins=Seattle&destinations=San+Francisco&key=AIzaSyBW-hZrDpdnCYUmRP-C_N0UvQIt4ugUfP4";
        String key = "AIzaSyC91eSS6lv3_dcM_I5LLUJpRYklorc22Us";
        String qry = "Android";
        StringBuffer bf = new StringBuffer();
        @Override
        protected Void doInBackground(String... strings) {
            HttpHandler sh = new HttpHandler();
            y = strings[0].toString();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall("https://www.googleapis.com/customsearch/v1?key=" + key + "&cx=013036536707430787589:_pqjad5hr1a&q=" + y + "&alt=json");
            if (jsonStr != null) try {
                // calculating the distance
                /*JSONObject jsonObj = new JSONObject(jsonStr);
                    // tmp hash map for single contact
*/
                JSONObject jsonObj = new JSONObject(jsonStr);
                // Getting JSON Array node
                JSONArray contacts = jsonObj.getJSONArray("items");
                for (int i = 0; i < contacts.length(); i++) {
                    JSONObject c = contacts.getJSONObject(i);

                    String title = c.getString("title");
                    String link = c.getString("link");
                    String snippet = c.getString("snippet");
                    db2.addContact(new Contacts2(counter, title, link, snippet));
                    your_array_list.add(new Contacts2(counter, title, link, snippet));
                }

            } catch (final JSONException e) {
               /* runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Result cannot be found on Google..",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });*/
                String[] gh=y.split(" ");
                new ParseURL445().execute(gh[0]);
            }
            return null;
        }
        @TargetApi(24)
        @Override
        protected void onPostExecute(Void aVoid) {

            super.onPostExecute(aVoid);
            searchListView.setAdapter(arrayAdapter);
            Timer buttonTimer = new Timer();

            buttonTimer.schedule(new TimerTask() {

                @Override
                public void run() {
                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            t1.speak("That's what I found on web:"
                                    , TextToSpeech.QUEUE_FLUSH, null);
                        }
                    });
                }
            }, 1000);
            t1 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                @Override
                public void onInit(int status) {
                    if (status != TextToSpeech.ERROR) {
                        t1.setLanguage(Locale.US);
                        t1.setSpeechRate(1.4f);
                    }

                }
            });

            //     Document doc2= Jsoup.parse(x);
            //  String content=doc2.body().text();
            // tex.setText(content);
        }
    }
    class ParseURL445 extends AsyncTask<String, Void, Void> {
        public String s, x, y;
        public String url = "https://maps.googleapis.com/maps/api/distancematrix/json?origins=Seattle&destinations=San+Francisco&key=AIzaSyBW-hZrDpdnCYUmRP-C_N0UvQIt4ugUfP4";
        String key = "AIzaSyC91eSS6lv3_dcM_I5LLUJpRYklorc22Us";
        String qry = "Android";
        StringBuffer bf = new StringBuffer();
        @Override
        protected Void doInBackground(String... strings) {
            HttpHandler sh = new HttpHandler();
            y = strings[0].toString();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall("https://www.googleapis.com/customsearch/v1?key=" + key + "&cx=013036536707430787589:_pqjad5hr1a&q=" + y + "&alt=json");
            if (jsonStr != null) try {
                // calculating the distance
                /*JSONObject jsonObj = new JSONObject(jsonStr);
                    // tmp hash map for single contact
*/
                JSONObject jsonObj = new JSONObject(jsonStr);
                // Getting JSON Array node
                JSONArray contacts = jsonObj.getJSONArray("items");
                for (int i = 0; i < contacts.length(); i++) {
                    JSONObject c = contacts.getJSONObject(i);

                    String title = c.getString("title");
                    String link = c.getString("link");
                    String snippet = c.getString("snippet");
                    db2.addContact(new Contacts2(counter, title, link, snippet));
                    your_array_list.add(new Contacts2(counter, title, link, snippet));
                }

            } catch (final JSONException e) {
               /* runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Result cannot be found on Google..",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });*/
                String[] gh=y.split(" ");

            }
            return null;
        }
        @TargetApi(24)
        @Override
        protected void onPostExecute(Void aVoid) {

            super.onPostExecute(aVoid);
            searchListView.setAdapter(arrayAdapter);

            //     Document doc2= Jsoup.parse(x);
            //  String content=doc2.body().text();
            // tex.setText(content);
        }
    }
    class ParseURL8 extends AsyncTask<String, Void, Void> {
        public String s, x;
        public String imgSrc;
        public String tex12, tex13, tex14;

        @Override
        protected Void doInBackground(String... strings) {
            try {
                x = strings[0];
                Document doc = Jsoup.connect("http://www.answers.com/Q/" + x).get();
                Elements img = doc.select("div.answer_text");
                Elements tex1 = doc.select("div.content_text");

                tex13 = tex1.toString();
                tex12 = img.toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @TargetApi(24)
        @Override
        protected void onPostExecute(Void aVoid) {

            super.onPostExecute(aVoid);
            Document doc2 = Jsoup.parse(tex13);
            String content = doc2.body().text();

            Document doc3 = Jsoup.parse(tex12);
            String content2 = doc3.body().text();

            //   mapText.setText(content);

            qAnswer.setText(content2 + " " + content.substring(0, 1300));
            Typeface custom61 = Typeface.createFromAsset(getAssets(), "fonts/opensan.ttf");
            qAnswer.setTypeface(custom61);
        }


    }
   /* class ActionClass2 extends BroadcastReceiver {
private int notification_id;
        MediaPlayer mp;
        PendingIntent  p_button_intent;
        NotificationCompat.Builder builder;

        @Override
        public void onReceive(Context context, Intent intent) {

            mp= MediaPlayer.create(context, R.raw.sam);
                mp.start();

            Toast.makeText(context, "Alarm....", Toast.LENGTH_LONG).show();

            // Set the icon, scrolling text and timestamp

            NotificationCompat.Builder mBuilder =
                    new NotificationCompat.Builder(context)
                            .setSmallIcon(R.drawable.ic_action_new)
                            .setContentTitle("My notification")
                            .setContentText("Hello World!");
// Creates an explicit intent for an Activity in your app

            NotificationManager notificationManager=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
            RemoteViews remoteViews=new RemoteViews(getPackageName(),R.layout.custom_notification);
            remoteViews.setImageViewResource(R.id.notif_icon,R.mipmap.ic_launcher);
            remoteViews.setTextViewText(R.id.notif_text,"Texr");
            Intent button_intent=new Intent("button clicked");
            notification_id=(int)System.currentTimeMillis();
            button_intent.putExtra("id",notification_id);
            p_button_intent=PendingIntent.getBroadcast(context,0,button_intent,0);

            Intent notification_intent=new Intent(context,MainActivity.class);
            PendingIntent pendingIntent=PendingIntent.getActivity(context,0,notification_intent,0);
            mBuilder =new NotificationCompat.Builder(context);
            mBuilder.setSmallIcon(R.mipmap.ic_launcher).setAutoCancel(true)
            .setCustomBigContentView(remoteViews).setContentIntent(pendingIntent);
            notificationManager.notify(notification_id,mBuilder.build());
        }
    }
*/
    /**
     * Created by win-8 on 15-02-2017.
     */
    public class ActionClass2 extends BroadcastReceiver {


        @Override
        public void onReceive(Context context, Intent intent) {
            NotificationManager mn = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            mn.cancel(intent.getExtras().getInt("id"));
            int h = intent.getExtras().getInt("name");
            Toast.makeText(context, "finally:" + h, Toast.LENGTH_LONG).show();
            Intent myIntent = new Intent(context, ActionClass.class);
            pendingIntent = PendingIntent.getBroadcast(context, 0, myIntent, 0);
            alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            // cancel the alarm
            alarmManager.cancel(pendingIntent);
            // delete the PendingIntent
            pendingIntent.cancel();
            Timer buttonTimer = new Timer();
            buttonTimer.schedule(new TimerTask() {

                @Override
                public void run() {
                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            t1.speak("Its time to wake up", TextToSpeech.QUEUE_FLUSH, null);

                        }
                    });
                }
            }, 1800);
            t1 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                @Override
                public void onInit(int status) {
                    if (status != TextToSpeech.ERROR) {
                        t1.setLanguage(Locale.US);
                        t1.setSpeechRate(1.0f);
                    }

                }
            });
        }
    }

    class ParseURL6 extends AsyncTask<String, Void, Void> {
        public String x, s, s1, s2, s3, s4,s5;

        @Override
        protected Void doInBackground(String... strings) {

            try {
                x = strings[0];
                Document doc = Jsoup.connect("http://www.weather-forecast.com/locations/" + x + "/forecasts/latest").get();
                // Elements element=doc.getElementsByTag("span");

                Elements img = doc.select("tr.max-temp-row span");
                // Elements tex1 = doc.select("p");
                Elements tex2 = doc.select("tr.max-temp-row span"); //for maz temp
                Elements tex5 = doc.select("tr.min-temp-row span");
                Elements tex3 = doc.getElementsByClass("phrase"); //for sentences
                Elements tex4 = doc.getElementsByClass("med wphrase"); //clear

                //     imgSrc = img.attr("src");
                Elements tex6 = doc.select("tr.rh td");
                //tex12=(tex1.toString().substring(0,3690));
                //  tex13=tex2.toString();
                s = tex2.toString();
                s1 = tex5.toString();
                s2 = tex3.toString();
                s3 = tex6.toString();
                s4 = tex4.toString();
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "EXCEPTION IS thrown", Toast.LENGTH_LONG).show();
            }

            return null;
        }

        @TargetApi(24)
        @Override
        protected void onPostExecute(Void aVoid) {

            super.onPostExecute(aVoid);
            //Document doc2=Jsoup.parse(tex12);
//            String content=doc2.body().text();

            Document doc3 = Jsoup.parse(s);
            String content2 = doc3.body().text();

            Document doc4 = Jsoup.parse(s1);
            String content3 = doc4.body().text();

            Document doc5 = Jsoup.parse(s2);
            String content4 = doc5.body().text();

            Document doc6 = Jsoup.parse(s3);
            String content5 = doc6.body().text();

            Document doc7 = Jsoup.parse(s4);
            String content6 = doc7.body().text();
            String jd = x.substring(0, 1).toUpperCase() + x.substring(1).toLowerCase();
            city.setText(jd);

            Maxtempheadvalue.setText(content2.substring(4, 6) + "\u00b0" + "C");
            Typeface custom51 = Typeface.createFromAsset(getAssets(), "fonts/opensan.ttf");
            Maxtempheadvalue.setTypeface(custom51);

            //Mintempheadvalue.setText(content3.substring(4,6)+"\u00b0"+"C");
            Mintemphead.setText("Min Temp:" + content3.substring(4, 6) + "\u00b0" + "C");
            weatherText.setText(content4.toString());
            Humidityhead.setText("Humidity:" + content5.substring(0, 2).toString() + "%");
            // Humidityheadvalue.setText( content5.substring(0,2).toString()+"%");
            text=content4.toString();
            //textView.setText(content2);
        }

    }
    class ParseURL66 extends AsyncTask<String, Void, Void> {
        public String x, s, s1, s2, s3, s4,s5,content4;
         String[] arr;
        @Override
        protected Void doInBackground(String... strings) {

            try {
                x = strings[0];
                arr=x.split(" ");
                Document doc = Jsoup.connect("http://www.weather-forecast.com/locations/" + arr[0] + "/forecasts/latest").get();
                // Elements element=doc.getElementsByTag("span");
                Elements img = doc.select("tr.max-temp-row span");
                // Elements tex1 = doc.select("p");
                Elements tex2 = doc.select("tr.max-temp-row span"); //for maz temp
                Elements tex5 = doc.select("tr.min-temp-row span");
                Elements tex3 = doc.getElementsByClass("phrase"); //for sentences
                Elements tex4 = doc.getElementsByClass("med wphrase"); //clear
                //     imgSrc = img.attr("src");
                Elements tex6 = doc.select("tr.rh td"); //humidity
                //tex12=(tex1.toString().substring(0,3690));
                //  tex13=tex2.toString();
                s = tex2.toString();  //MAX_ROW
                s1 = tex5.toString();  //min temp
                s2 = tex3.toString();  //sentences
                s3 = tex6.toString();  //humidity
                s4 = tex4.toString(); //
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "EXCEPTION IS thrown", Toast.LENGTH_LONG).show();
            }

            return null;
        }

        @TargetApi(24)
        @Override
        protected void onPostExecute(Void aVoid) {

            super.onPostExecute(aVoid);
            //Document doc2=Jsoup.parse(tex12);
//            String content=doc2.body().text();
            Toast.makeText(getApplicationContext(),arr[0]+arr[1],Toast.LENGTH_LONG).show();

            Document doc3 = Jsoup.parse(s); //max temp
            String content2 = doc3.body().text();

            Document doc4 = Jsoup.parse(s1); //min temp
            String content3 = doc4.body().text();

            Document doc5 = Jsoup.parse(s2); //sentences
             content4 = doc5.body().text();

            Document doc6 = Jsoup.parse(s3);
            String content5 = doc6.body().text();

            Document doc7 = Jsoup.parse(s4);
            String content6 = doc7.body().text();
           // String jd = x.substring(0, 1).toUpperCase() + x.substring(1).toLowerCase();
            city.setText(arr[0]);
            if(arr[1].equals("1")){
                String gh=content2.replaceAll("C C ","");
                String[] arr2 = gh.split(" ");
                Maxtempheadvalue.setText(arr2[3]+"\u00b0" + "C");
                Typeface custom51 = Typeface.createFromAsset(getAssets(), "fonts/hellight.ttf");
                Maxtempheadvalue.setTypeface(custom51);

                String gh1=content3.replaceAll("C C ","");
                String[] arr4 = gh1.split(" ");
                Mintemphead.setText("Min Temp:"+arr4[3]+"\u00b0" + "C");

                String gh2=content5;
                String[] arr3 = gh2.split(" ");
                Humidityhead.setText("Humidity:"+arr3[3]+"%");

                weatherText.setText(content4.toString());
                Typeface custom57 = Typeface.createFromAsset(getAssets(), "fonts/helmed.ttf");

                weatherText.setTypeface(custom57);
                Timer buttonTimer = new Timer();

                buttonTimer.schedule(new TimerTask() {

                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                t1.speak("Weather in "+arr[0]+" is "+content4.toString().replaceAll("Tue","Tuesday").replaceAll("Wed","Wednesday").replaceAll("Sat","Saturday").replaceAll("Mon","Monday").replaceAll("Thurs","Thursday").replaceAll("Sun","Sunday").replaceAll("Fri","Friday")
                                        , TextToSpeech.QUEUE_FLUSH, null);
                            }
                        });
                    }
                }, 1000);
                t1 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        if (status != TextToSpeech.ERROR) {
                            t1.setLanguage(Locale.US);
                            t1.setSpeechRate(1.0f);
                        }

                    }
                });
            }
            if(arr[1].equals("2")){
                String gh=content2.replaceAll("C C ","");
                String[] arr3 = gh.split(" ");
                Maxtempheadvalue.setText(arr3[6]+"\u00b0" + "C");
                Typeface custom51 = Typeface.createFromAsset(getAssets(), "fonts/hellight.ttf");
                Maxtempheadvalue.setTypeface(custom51);

                String gh1=content3.replaceAll("C C ","");
                String[] arr1 = gh.split(" ");
                Mintemphead.setText("Min Temp:"+arr1[6]+"\u00b0" + "C");
                String gh2=content3;
                String[] arr2 = gh.split(" ");
                Humidityhead.setText("Humidity:"+arr2[6]+"%");
                weatherText.setText(content4.toString());
                Typeface custom57 = Typeface.createFromAsset(getAssets(), "fonts/helmed.ttf");
                weatherText.setTypeface(custom57);

                Timer buttonTimer = new Timer();

                buttonTimer.schedule(new TimerTask() {

                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                t1.speak("Weather in "+arr[0]+" is "+content4.toString().replaceAll("Tue","Tuesday").replaceAll("Wed","Wednesday").replaceAll("Sat","Saturday").replaceAll("Mon","Monday").replaceAll("Thurs","Thursday").replaceAll("Sun","Sunday").replaceAll("Fri","Friday")
                                        , TextToSpeech.QUEUE_FLUSH, null);
                            }
                        });
                    }
                }, 1000);
                t1 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        if (status != TextToSpeech.ERROR) {
                            t1.setLanguage(Locale.US);
                            t1.setSpeechRate(1.0f);
                        }

                    }
                });
            }
            if(arr[1].equals("3")){
                String gh=content2.replaceAll("C C ","");
                String[] arr2 = gh.split(" ");
                Maxtempheadvalue.setText(arr2[9]+"\u00b0" + "C");
                Typeface custom51 = Typeface.createFromAsset(getAssets(), "fonts/hellight.ttf");
                Maxtempheadvalue.setTypeface(custom51);

                String gh1=content3.replaceAll("C C ","");
                String[] arr4 = gh1.split(" ");
                Mintemphead.setText("Min Temp:"+arr4[9]+"\u00b0" + "C");

                String gh2=content5;
                String[] arr3 = gh2.split(" ");
                Humidityhead.setText("Humidity:"+arr3[9]+"%");

                weatherText.setText(content4.toString());
                Typeface custom57 = Typeface.createFromAsset(getAssets(), "fonts/helmed.ttf");

                weatherText.setTypeface(custom57);
                Timer buttonTimer = new Timer();

                buttonTimer.schedule(new TimerTask() {

                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                t1.speak("Weather in "+arr[0]+" is "+content4.toString().replaceAll("Tue","Tuesday").replaceAll("Wed","Wednesday").replaceAll("Sat","Saturday").replaceAll("Mon","Monday").replaceAll("Thurs","Thursday").replaceAll("Sun","Sunday").replaceAll("Fri","Friday")
                                        , TextToSpeech.QUEUE_FLUSH, null);
                            }
                        });
                    }
                }, 1000);
                t1 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        if (status != TextToSpeech.ERROR) {
                            t1.setLanguage(Locale.US);
                            t1.setSpeechRate(1.0f);
                        }

                    }
                });
            }

            if(arr[1].equals("4")){
                String gh=content2.replaceAll("C C ","");
                String[] arr2 = gh.split(" ");
                Maxtempheadvalue.setText(arr2[12]+"\u00b0" + "C");
                Typeface custom51 = Typeface.createFromAsset(getAssets(), "fonts/hellight.ttf");
                Maxtempheadvalue.setTypeface(custom51);

                String gh1=content3.replaceAll("C C ","");
                String[] arr4 = gh1.split(" ");
                Mintemphead.setText("Min Temp:"+arr4[12]+"\u00b0" + "C");

                String gh2=content5;
                String[] arr3 = gh2.split(" ");
                Humidityhead.setText("Humidity:"+arr3[12]+"%");

                weatherText.setText(content4.toString());
                Typeface custom57 = Typeface.createFromAsset(getAssets(), "fonts/helmed.ttf");

                weatherText.setTypeface(custom57);
                Timer buttonTimer = new Timer();

                buttonTimer.schedule(new TimerTask() {

                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                t1.speak("Weather in "+arr[0]+" is "+content4.toString().replaceAll("Tue","Tuesday").replaceAll("Wed","Wednesday").replaceAll("Sat","Saturday").replaceAll("Mon","Monday").replaceAll("Thurs","Thursday").replaceAll("Sun","Sunday").replaceAll("Fri","Friday")
                                        , TextToSpeech.QUEUE_FLUSH, null);
                            }
                        });
                    }
                }, 1000);
                t1 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        if (status != TextToSpeech.ERROR) {
                            t1.setLanguage(Locale.US);
                            t1.setSpeechRate(1.0f);
                        }

                    }
                });
            }
            if(arr[1].equals("5")){
                String gh=content2.replaceAll("C C ","");
                String[] arr2 = gh.split(" ");
                Maxtempheadvalue.setText(arr2[15]+"\u00b0" + "C");
                Typeface custom51 = Typeface.createFromAsset(getAssets(), "fonts/hellight.ttf");
                Maxtempheadvalue.setTypeface(custom51);

                String gh1=content3.replaceAll("C C ","");
                String[] arr4 = gh1.split(" ");
                Mintemphead.setText("Min Temp:"+arr4[15]+"\u00b0" + "C");

                String gh2=content5;
                String[] arr3 = gh2.split(" ");
                Humidityhead.setText("Humidity:"+arr3[15]+"%");

                weatherText.setText(content4.toString());
                Typeface custom57 = Typeface.createFromAsset(getAssets(), "fonts/helmed.ttf");

                weatherText.setTypeface(custom57);
                Timer buttonTimer = new Timer();

                buttonTimer.schedule(new TimerTask() {

                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                t1.speak("Weather in "+arr[0]+" is "+content4.toString().replaceAll("Tue","Tuesday").replaceAll("Wed","Wednesday").replaceAll("Sat","Saturday").replaceAll("Mon","Monday").replaceAll("Thurs","Thursday").replaceAll("Sun","Sunday").replaceAll("Fri","Friday")
                                        , TextToSpeech.QUEUE_FLUSH, null);
                            }
                        });
                    }
                }, 1000);
                t1 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        if (status != TextToSpeech.ERROR) {
                            t1.setLanguage(Locale.US);
                            t1.setSpeechRate(1.0f);
                        }

                    }
                });
            }
            if(arr[1].equals("6")){
                String gh=content2.replaceAll("C C ","");
                String[] arr2 = gh.split(" ");
                Maxtempheadvalue.setText(arr2[18]+"\u00b0" + "C");
                Typeface custom51 = Typeface.createFromAsset(getAssets(), "fonts/hellight.ttf");
                Maxtempheadvalue.setTypeface(custom51);

                String gh1=content3.replaceAll("C C ","");
                String[] arr4 = gh1.split(" ");
                Mintemphead.setText("Min Temp:"+arr4[18]+"\u00b0" + "C");

                String gh2=content5;
                String[] arr3 = gh2.split(" ");
                Humidityhead.setText("Humidity:"+arr3[18]+"%");

                weatherText.setText(content4.toString());
                Typeface custom57 = Typeface.createFromAsset(getAssets(), "fonts/helmed.ttf");

                weatherText.setTypeface(custom57);
                Timer buttonTimer = new Timer();

                buttonTimer.schedule(new TimerTask() {

                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                t1.speak("Weather in "+arr[0]+" is "+content4.toString().replaceAll("Tue","Tuesday").replaceAll("Wed","Wednesday").replaceAll("Sat","Saturday").replaceAll("Mon","Monday").replaceAll("Thurs","Thursday").replaceAll("Sun","Sunday").replaceAll("Fri","Friday")
                                        , TextToSpeech.QUEUE_FLUSH, null);
                            }
                        });
                    }
                }, 1000);
                t1 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        if (status != TextToSpeech.ERROR) {
                            t1.setLanguage(Locale.US);
                            t1.setSpeechRate(1.0f);
                        }

                    }
                });
            }
            if(arr[1].equals("7")){
                String gh=content2.replaceAll("C C ","");
                String[] arr2 = gh.split(" ");
                Maxtempheadvalue.setText(arr2[21]+"\u00b0" + "C");
                Typeface custom51 = Typeface.createFromAsset(getAssets(), "fonts/hellight.ttf");
                Maxtempheadvalue.setTypeface(custom51);

                String gh1=content3.replaceAll("C C ","");
                String[] arr4 = gh1.split(" ");
                Mintemphead.setText("Min Temp:"+arr4[21]+"\u00b0" + "C");

                String gh2=content5;
                String[] arr3 = gh2.split(" ");
                Humidityhead.setText("Humidity:"+arr3[21]+"%");

                weatherText.setText(content4.toString());
                Typeface custom57 = Typeface.createFromAsset(getAssets(), "fonts/helmed.ttf");

                weatherText.setTypeface(custom57);
                Timer buttonTimer = new Timer();

                buttonTimer.schedule(new TimerTask() {

                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                t1.speak("Weather in "+arr[0]+" is "+content4.toString().replaceAll("Tue","Tuesday").replaceAll("Wed","Wednesday").replaceAll("Sat","Saturday").replaceAll("Mon","Monday").replaceAll("Thurs","Thursday").replaceAll("Sun","Sunday").replaceAll("Fri","Friday")
                                        , TextToSpeech.QUEUE_FLUSH, null);
                            }
                        });
                    }
                }, 1000);
                t1 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        if (status != TextToSpeech.ERROR) {
                            t1.setLanguage(Locale.US);
                            t1.setSpeechRate(1.0f);
                        }

                    }
                });
            }
            if(arr[1].equals("8")){
                String gh=content2.replaceAll("C C ","");
                String[] arr2 = gh.split(" ");
                Maxtempheadvalue.setText(arr2[24]+"\u00b0" + "C");
                Typeface custom51 = Typeface.createFromAsset(getAssets(), "fonts/hellight.ttf");
                Maxtempheadvalue.setTypeface(custom51);

                String gh1=content3.replaceAll("C C ","");
                String[] arr4 = gh1.split(" ");
                Mintemphead.setText("Min Temp:"+arr4[24]+"\u00b0" + "C");

                String gh2=content5;
                String[] arr3 = gh2.split(" ");
                Humidityhead.setText("Humidity:"+arr3[24]+"%");

                weatherText.setText(content4.toString());
                Typeface custom57 = Typeface.createFromAsset(getAssets(), "fonts/helmed.ttf");

                weatherText.setTypeface(custom57);
                Timer buttonTimer = new Timer();

                buttonTimer.schedule(new TimerTask() {

                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                t1.speak("Weather in "+arr[0]+" is "+content4.toString().replaceAll("Tue","Tuesday").replaceAll("Wed","Wednesday").replaceAll("Sat","Saturday").replaceAll("Mon","Monday").replaceAll("Thurs","Thursday").replaceAll("Sun","Sunday").replaceAll("Fri","Friday")
                                        , TextToSpeech.QUEUE_FLUSH, null);
                            }
                        });
                    }
                }, 1000);
                t1 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        if (status != TextToSpeech.ERROR) {
                            t1.setLanguage(Locale.US);
                            t1.setSpeechRate(1.0f);
                        }

                    }
                });
            }
            if(arr[1].equals("9")){
                String gh=content2.replaceAll("C C ","");
                String[] arr2 = gh.split(" ");
                Maxtempheadvalue.setText(arr2[27]+"\u00b0" + "C");
                Typeface custom51 = Typeface.createFromAsset(getAssets(), "fonts/hellight.ttf");
                Maxtempheadvalue.setTypeface(custom51);

                String gh1=content3.replaceAll("C C ","");
                String[] arr4 = gh1.split(" ");
                Mintemphead.setText("Min Temp:"+arr4[27]+"\u00b0" + "C");

                String gh2=content5;
                String[] arr3 = gh2.split(" ");
                Humidityhead.setText("Humidity:"+arr3[27]+"%");

                weatherText.setText(content4.toString());
                Typeface custom57 = Typeface.createFromAsset(getAssets(), "fonts/helmed.ttf");

                weatherText.setTypeface(custom57);
                Timer buttonTimer = new Timer();

                buttonTimer.schedule(new TimerTask() {

                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                t1.speak("Weather in "+arr[0]+" is "+content4.toString().replaceAll("Tue","Tuesday").replaceAll("Wed","Wednesday").replaceAll("Sat","Saturday").replaceAll("Mon","Monday").replaceAll("Thurs","Thursday").replaceAll("Sun","Sunday").replaceAll("Fri","Friday")
                                        , TextToSpeech.QUEUE_FLUSH, null);
                            }
                        });
                    }
                }, 1000);
                t1 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        if (status != TextToSpeech.ERROR) {
                            t1.setLanguage(Locale.US);
                            t1.setSpeechRate(1.0f);
                        }

                    }
                });
            }
            if(arr[1].equals("10")){
                String gh=content2.replaceAll("C C ","");
                String[] arr2 = gh.split(" ");
                Maxtempheadvalue.setText(arr2[30]+"\u00b0" + "C");
                Typeface custom51 = Typeface.createFromAsset(getAssets(), "fonts/hellight.ttf");
                Maxtempheadvalue.setTypeface(custom51);

                String gh1=content3.replaceAll("C C ","");
                String[] arr4 = gh1.split(" ");
                Mintemphead.setText("Min Temp:"+arr4[30]+"\u00b0" + "C");

                String gh2=content5;
                String[] arr3 = gh2.split(" ");
                Humidityhead.setText("Humidity:"+arr3[30]+"%");
                weatherText.setText(content4.toString());
                Typeface custom57 = Typeface.createFromAsset(getAssets(), "fonts/helmed.ttf");
                weatherText.setTypeface(custom57);
                Timer buttonTimer = new Timer();
                buttonTimer.schedule(new TimerTask() {

                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                t1.speak("Weather in "+arr[0]+" is "+content4.toString().replaceAll("Tue","Tuesday").replaceAll("Wed","Wednesday").replaceAll("Sat","Saturday").replaceAll("Mon","Monday").replaceAll("Thurs","Thursday").replaceAll("Sun","Sunday").replaceAll("Fri","Friday")
                                        , TextToSpeech.QUEUE_FLUSH, null);
                            }
                        });
                    }
                }, 1000);
                t1 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        if (status != TextToSpeech.ERROR) {
                            t1.setLanguage(Locale.US);
                            t1.setSpeechRate(1.0f);
                        }

                    }
                });
            }
/*
            Maxtempheadvalue.setText(content2.substring(4, 6) + "\u00b0" + "C");
            Typeface custom51 = Typeface.createFromAsset(getAssets(), "fonts/opensan.ttf");
            Maxtempheadvalue.setTypeface(custom51);

            //Mintempheadvalue.setText(content3.substring(4,6)+"\u00b0"+"C");
            Mintemphead.setText("Min Temp:" + content3.substring(4, 6) + "\u00b0" + "C");
            weatherText.setText(content4.toString());
            Humidityhead.setText("Humidity:" + content5.substring(0, 2).toString() + "%");
            // Humidityheadvalue.setText( content5.substring(0,2).toString()+"%");
            text=content4.toString();*/
            //textView.setText(content2);
        }

    }
    class ParseURL133 extends AsyncTask<String, Void, Void> {
        public String s, x;
        public String imgSrc;
        public String tex12, tex13, tex14;

        @Override
        protected Void doInBackground(String... strings) {
            try {
                x = strings[0];

                    Document doc = Jsoup.connect("https://www.countries-ofthe-world.com/capitals-of-the-world.html").get();
                    Elements img = doc.select("tr");
                    //  tex13=tex1.toString();
                    tex12 = img.toString();

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @TargetApi(24)
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            //  Document doc2=Jsoup.parse(tex13);
            //String content=doc2.body().text();
            Document doc3 = Jsoup.parse(tex12);
            String content2 = doc3.body().text();
            searched=true;
            String gh = x;
            String s = content2.substring(content2.indexOf(gh));
            String country = s.substring(0, gh.length());
            String s2 = s.substring(s.indexOf(" "));
            String s3 = s2.substring(1);
            String capital = s3.substring(0, s3.indexOf(" "));
            new ParseURL2().execute(capital);
            wikipedia.setText(capital);
            text="The capital of "+country+" is "+ capital+"..";
         //   content.setText(country + "+" + capital);
        }

    }
    class ParseURL81 extends AsyncTask<String, Void, Void> {
        public String s, x;
        public String tex12, tex13;

        @Override
        protected Void doInBackground(String... strings) {
            try {
                x = strings[0];
                Document doc = Jsoup.connect("http://www.thesaurus.com/browse/"+x).get();
                //  Elements img = doc.select("div.answer_text");
                Elements tex1 = doc.select("span.text");
                tex13 = tex1.toString();
                //tex12 = img.toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        @TargetApi(24)
        @Override
        protected void onPostExecute(Void aVoid) {

            super.onPostExecute(aVoid);
            Document doc2 = Jsoup.parse(tex13);
            String content45 = doc2.body().text();
            qAnswer.setText(content45.substring(0,600).replaceAll(" ",",")+"..and many more");
            // Document doc3 = Jsoup.parse(tex12);
            // String content2 = doc3.body().text();
            //   mapText.setText(content);
            //  qAnswer.setText(" " + content45.substring(0, 1000));
            //Typeface custom61 = Typeface.createFromAsset(getAssets(), "fonts/opensan.ttf");
            //qAnswer.setTypeface(custom61);
        }
    }


}


