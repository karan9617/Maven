package com.example.win_8.voicemail;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
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
import com.google.android.gms.maps.SupportMapFragment;
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class MapsActivity3 extends FragmentActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {
    LocationRequest mLocationRequest;
    CustomAdapter4 customAdapter4;
    Marker mCurrLocationMarker1;
    TextView cityHead,distanceText,timeText,tempText,wikiText,google_search,resdistance;
    TextView weatherReport,minTemp,maxTemp,humidity,weatherText,cityName,restaurant,restaurant_text;
    GoogleApiClient mGoogleApiClient;
   // ListView rlistView;
    ImageView image,marker,divider23,sunimage,restaurant_image,divider1,divider2,divider3,resMarker;
    double location_lat;
    double location_long;
    Location mLastLocation;
    Marker mCurrLocationMarker;
    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    TextToSpeech t1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        /// rlistView=(ListView)findViewById(R.id.rlistView);
       // rlistView.setVisibility(View.INVISIBLE);
        resMarker=(ImageView)findViewById(R.id.resMarker);
        resMarker.setVisibility(View.INVISIBLE);
        divider1=(ImageView)findViewById(R.id.divider1);
        divider1.setVisibility(View.INVISIBLE);
        divider2=(ImageView)findViewById(R.id.divider2);
        divider2.setVisibility(View.INVISIBLE);
        divider3=(ImageView)findViewById(R.id.divider3);
        divider3.setVisibility(View.INVISIBLE);
        resdistance=(TextView)findViewById(R.id.resdistance);
        resdistance.setVisibility(View.INVISIBLE);
        restaurant=(TextView)findViewById(R.id.restaurant);
        restaurant.setVisibility(View.INVISIBLE);
        google_search=(TextView)findViewById(R.id.google_search);
        google_search.setVisibility(View.INVISIBLE);
        restaurant_text=(TextView)findViewById(R.id.restaurant_text);
        restaurant_text.setVisibility(View.INVISIBLE);
        restaurant_image=(ImageView)findViewById(R.id.restaurant_image);
        restaurant_image.setVisibility(View.INVISIBLE);
        weatherReport=(TextView)findViewById(R.id.weather_report);
        minTemp=(TextView) findViewById(R.id.minTemp);
        cityName=(TextView) findViewById(R.id.cityName);
        marker=(ImageView) findViewById(R.id.marker);
        sunimage=(ImageView) findViewById(R.id.sunimage);
        sunimage.setVisibility(View.INVISIBLE);
        maxTemp=(TextView) findViewById(R.id.maxTemp);
        humidity=(TextView) findViewById(R.id.humidity);
        weatherText=(TextView) findViewById(R.id.weatherText);
        divider23=(ImageView) findViewById(R.id.divider23);
        divider23.setVisibility(View.INVISIBLE);
        weatherReport.setVisibility(View.INVISIBLE);
        minTemp.setVisibility(View.INVISIBLE);
        marker.setVisibility(View.INVISIBLE);
        maxTemp.setVisibility(View.INVISIBLE);
        cityName.setVisibility(View.INVISIBLE);

        humidity.setVisibility(View.INVISIBLE);
        weatherText.setVisibility(View.INVISIBLE);

        cityHead=(TextView) findViewById(R.id.city_head);
        wikiText=(TextView) findViewById(R.id.wikiText);

        distanceText=(TextView) findViewById(R.id.distanceText);
        timeText=(TextView) findViewById(R.id.timeText);
        tempText=(TextView) findViewById(R.id.tempText);
        image=(ImageView) findViewById(R.id.image);
        image.setVisibility(View.INVISIBLE);
        cityHead.setVisibility(View.INVISIBLE);
        wikiText.setVisibility(View.INVISIBLE);

        distanceText.setVisibility(View.INVISIBLE);
        timeText.setVisibility(View.INVISIBLE);
        tempText.setVisibility(View.INVISIBLE);

        // setUpMapIfNeeded();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMapAsync(this);
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
        mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));
    }
    private List<LatLng> decodePoly(String encoded) {

        List<LatLng> poly = new ArrayList<LatLng>();
        int index = 0, len = encoded.length();
        int lat = 0, lng = 0;

        while (index < len) {
            int b, shift = 0, result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lat += dlat;

            shift = 0;
            result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lng += dlng;

            LatLng p = new LatLng((((double) lat / 1E5)),
                    (((double) lng / 1E5)));
            poly.add(p);
        }

        return poly;
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
        mMap.addMarker(markerOptions2);
        // CameraUpdate zoom=CameraUpdateFactory.zoomTo(15);
        CameraUpdate center =
                CameraUpdateFactory.newLatLng(latLng2);
        CameraUpdate zoom2 = CameraUpdateFactory.zoomTo(11);
        mMap.moveCamera(center);
        mMap.animateCamera(zoom2);
        //mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng2));
        markerOptions2.title("Current Position");
        markerOptions2.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
        //  mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng2));
        mCurrLocationMarker1 = mMap.addMarker(markerOptions2);
        Polyline line = mMap.addPolyline(new PolylineOptions()
                .add(new LatLng(location_lat, location_long), new LatLng(home_lat, home_long))
                .width(5)
                .color(Color.RED));
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.mMap = googleMap;
        mMap.setMyLocationEnabled(true);
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                //Location Permission already granted
                buildGoogleApiClient();
                mMap.setMyLocationEnabled(true);
            } else {
                //Request Location Permission
                checkLocationPermission();
            }
        }
        else {
            buildGoogleApiClient();
            mMap.setMyLocationEnabled(true);
        }

    }
    @Override
    public void onConnected(Bundle bundle) {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(3000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);

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
    public void onConnectionSuspended(int i) {}
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    // permission was granted, yay! Do the
                    // location-related task you need to do.
                    if (mGoogleApiClient == null) {
                        buildGoogleApiClient();
                    }
                    mMap.setMyLocationEnabled(true);
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(this, "permission denied", Toast.LENGTH_LONG).show();
                }
                return;
            }
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
                                ActivityCompat.requestPermissions(MapsActivity3.this,
                                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                        MY_PERMISSIONS_REQUEST_LOCATION );
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

    @Override
    public void onLocationChanged(Location location) {
        mLastLocation = location;

        if (mCurrLocationMarker != null) {
            mCurrLocationMarker.remove();
        }
        location_lat=location.getLatitude();
        location_long= location.getLongitude();
        //Place current location marker
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("Current Position");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
        mCurrLocationMarker = mMap.addMarker(markerOptions);
        //move map camera
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(12));
///bakeasf
        //optionally, stop location updates if only current location is needed
        if (mGoogleApiClient != null) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
        }
        String str = getIntent().getExtras().getString("Bhopal");
        String str1 = getIntent().getExtras().getString("Bhopal1");
        String str2 = getIntent().getExtras().getString("Bhopal2");
        String str3 = getIntent().getExtras().getString("Bhopal4");
        String str4 = getIntent().getExtras().getString("Bhopal6");
        String str5 = getIntent().getExtras().getString("Bhopal5");
        String str6 = getIntent().getExtras().getString("Bhopal7");

        /*Geocoder geocoder = new Geocoder(getBaseContext());
        List<Address> addresses;
        try {-
            // Getting a maximum of 3 Address that matches the input
            // text
            addresses = geocoder.getFromLocationName(str, 1);
            if (addresses != null && !addresses.equals(""))
                search(addresses);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(),"Search the location again",Toast.LENGTH_LONG).show();
        }*/
        if(str!=null){
        new ParseURL4().execute(str);
            cityHead.setVisibility(View.VISIBLE);
            distanceText.setVisibility(View.VISIBLE);
            image.setVisibility(View.VISIBLE);
            wikiText.setVisibility(View.VISIBLE);
            timeText.setVisibility(View.VISIBLE);
           marker.setVisibility(View.VISIBLE);
            tempText.setVisibility(View.VISIBLE);
            cityHead.setText(str);
            new ParseURL6().execute(str);
            new ParseURL2().execute(str);
            new ParseURL4().execute(str);
        }
        if(str1!=null){
            String s3=null,s4=null,s5,s6;
            if(str1.contains(" ")){s3=str1.replaceAll(" ","+");s5=str1.replaceAll(" ","-");s6=str1.replaceAll(" ","_");}else{s3=str1;s5=str1;s6=str1;}
            if(str2.contains(" ")){s4=str2.replaceAll(" ","+");}else{s4=str2;}
            cityHead.setVisibility(View.VISIBLE);
            distanceText.setVisibility(View.VISIBLE);
            image.setVisibility(View.VISIBLE);
           wikiText.setVisibility(View.VISIBLE);
            marker.setVisibility(View.VISIBLE);
            timeText.setVisibility(View.VISIBLE);
            tempText.setVisibility(View.VISIBLE);
            cityHead.setText(str1);
          new ParseURL5().execute(s3+" "+s4);
           new ParseURL6().execute(s5);
           new ParseURL2().execute(s6);
        }
        if(str3!=null){
            weatherReport.setVisibility(View.VISIBLE);
            minTemp.setVisibility(View.VISIBLE);

            maxTemp.setVisibility(View.VISIBLE);
            humidity.setVisibility(View.VISIBLE);
            divider23.setVisibility(View.VISIBLE);
            sunimage.setVisibility(View.VISIBLE);

            weatherText.setVisibility(View.VISIBLE);
            cityName.setVisibility(View.VISIBLE);
            new ParseURL61().execute("sfd");
        }
        if(str4!=null){
            weatherReport.setVisibility(View.VISIBLE);
            minTemp.setVisibility(View.VISIBLE);
            maxTemp.setVisibility(View.VISIBLE);
            sunimage.setVisibility(View.VISIBLE);

            humidity.setVisibility(View.VISIBLE);
            divider23.setVisibility(View.VISIBLE);
            weatherText.setVisibility(View.VISIBLE);
            cityName.setVisibility(View.VISIBLE);
            new ParseURL62().execute(str4);
        }
        if(str5!=null){
            weatherReport.setVisibility(View.VISIBLE);
            minTemp.setVisibility(View.VISIBLE);
            maxTemp.setVisibility(View.VISIBLE);
            sunimage.setVisibility(View.VISIBLE);

            humidity.setVisibility(View.VISIBLE);
            divider23.setVisibility(View.VISIBLE);
            weatherText.setVisibility(View.VISIBLE);
            cityName.setVisibility(View.VISIBLE);
            new ParseURL63().execute(str4);
        }
        if(str6!=null){
            restaurant.setVisibility(View.VISIBLE);
            restaurant_text.setVisibility(View.VISIBLE);
            google_search.setVisibility(View.VISIBLE);
            resdistance.setVisibility(View.VISIBLE);
            divider1.setVisibility(View.VISIBLE);
            divider2.setVisibility(View.VISIBLE);
            divider3.setVisibility(View.VISIBLE);
            resMarker.setVisibility(View.VISIBLE);

            restaurant_image.setVisibility(View.VISIBLE);
            // rlistView.setVisibility(View.VISIBLE);
            new ParseURL22().execute("karan");
        }
    }
    public void drawPath(String result) {
        try {
            // Tranform the string into a json object
            final JSONObject json = new JSONObject(result);
            JSONArray routeArray = json.getJSONArray("routes");
            JSONObject routes = routeArray.getJSONObject(0);
            JSONObject overviewPolylines = routes
                    .getJSONObject("overview_polyline");
            String encodedString = overviewPolylines.getString("points");
            List<LatLng> list = decodePoly(encodedString);

            for (int z = 0; z < list.size() - 1; z++) {
                LatLng src = list.get(z);
                LatLng dest = list.get(z + 1);
                Polyline line = mMap.addPolyline(new PolylineOptions()
                        .add(new LatLng(src.latitude, src.longitude),
                                new LatLng(dest.latitude, dest.longitude))
                        .width(8).color(Color.rgb(5,105,232)).geodesic(true));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class ParseURL4 extends AsyncTask<String, Void, Void> {
        public String s, x, y;
        public String url = "https://maps.googleapis.com/maps/api/distancematrix/json?origins=Seattle&destinations=San+Francisco&key=AIzaSyBW-hZrDpdnCYUmRP-C_N0UvQIt4ugUfP4";
        String key = "AIzaSyC91eSS6lv3_dcM_I5LLUJpRYklorc22Us";
        String qry = "Android";
        String arr2,y1;
        StringBuffer bf = new StringBuffer();
        String jsonStr,address,address1,fg;
        String encodedString,encodedString1;
        @Override
        protected Void doInBackground(String... strings) {

            HttpHandler sh = new HttpHandler();
            y = strings[0].toString();
            y1=y.toUpperCase().substring(0,1)+y.toLowerCase().substring(1);
            Geocoder geocoder;
            List<Address> addresses;
            geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());

            try {
                addresses = geocoder.getFromLocation(location_lat, location_long, 1);
                address1=addresses.get(0).getAddressLine(1);
                String h=addresses.get(0).getLocality();
                String h1=addresses.get(0).getAdminArea();


                String[] arr = address1.split(" ");
                arr2 = arr[0]+"+"+arr[1]+"+"+h;


            } catch (IOException e) {
                e.printStackTrace();
            }
            // Making a request to url and getting response
            jsonStr = sh.makeServiceCall("https://maps.googleapis.com/maps/api/directions/json?origin="+arr2+"&destination="+y1+"&mode=transit&key=AIzaSyC46Pacq9qC23slsQdAP4-f7ETF9YkYfz4");
            if (jsonStr != null){
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);
                    JSONArray routeArray = jsonObj.getJSONArray("routes");
                    JSONObject routes = routeArray.getJSONObject(0);
                    JSONArray id = routes.getJSONArray("legs");
                    JSONObject id1=id.getJSONObject(0);
                    JSONObject overviewPolylines = id1
                            .getJSONObject("distance");
                    JSONObject overviewPolylines1 = id1
                            .getJSONObject("duration");
                    encodedString= overviewPolylines.getString("text");
                    encodedString1=overviewPolylines1.getString("text");
                }catch(JSONException e){}
            }

            //  if (jsonStr != null) try {
            // calculating the distance
                /*JSONObject jsonObj = new JSONObject(jsonStr);

                     mobile = phone.getString("text");
                    // tmp hash map for single contact
*/
/*            JSONObject jsonObj = new JSONObject(jsonStr);
            // Getting JSON Array node
            JSONArray contacts = jsonObj.getJSONArray("items");
            for (int i = 0; i < contacts.length(); i++) {

                            Toast.LENGTH_LONG)
                            .show();
                }
            });
        }*/
            return null;
            //}

        }

        @TargetApi(24)
        @Override
        protected void onPostExecute(Void aVoid) {

            super.onPostExecute(aVoid);
            drawPath(jsonStr);
            distanceText.setText(encodedString);timeText.setText(encodedString1);
            Timer buttonTimer = new Timer();
            buttonTimer.schedule(new TimerTask() {

                @Override
                public void run() {
                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            t1.speak(
                                    "The distance is "+ encodedString+" from the current location and the duration is "+encodedString1, TextToSpeech.QUEUE_FLUSH, null);
                        }
                    });
                }
            }, 3000);
            t1 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                @Override
                public void onInit(int status) {
                    if (status != TextToSpeech.ERROR) {
                        t1.setLanguage(Locale.US);
                        t1.setSpeechRate(1.0f);
                    }

                }
            });
            //  text.setText(jsonStr);
            //     Document doc2= Jsoup.parse(x);
            //  String content=doc2.body().text();
            // tex.setText(content);
        }
    }
    class ParseURL5 extends AsyncTask<String, Void, Void> {
        public String s, x, y;
        public String url = "https://maps.googleapis.com/maps/api/distancematrix/json?origins=Seattle&destinations=San+Francisco&key=AIzaSyBW-hZrDpdnCYUmRP-C_N0UvQIt4ugUfP4";
        String key = "AIzaSyC91eSS6lv3_dcM_I5LLUJpRYklorc22Us";
        String qry = "Android";
        String arr2,y1,h;
        StringBuffer bf = new StringBuffer();
        String jsonStr,address,address1,fg="false";
        String encodedString,encodedString1;
        @Override
        protected Void doInBackground(String... strings) {

            HttpHandler sh = new HttpHandler();
            x=strings[0];
            String[] arr12 = x.split(" ");
            Geocoder geocoder;
            List<Address> addresses;
            geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());

            try {
                addresses = geocoder.getFromLocation(location_lat, location_long, 1);
                address1=addresses.get(0).getAddressLine(1);
                 h=addresses.get(0).getLocality();
                String[] arr = address1.split(" ");
                arr2 = arr[0]+"+"+arr[1]+"+"+h;
            } catch (IOException e) {
                e.printStackTrace();
            }
            // Making a request to url and getting response
            jsonStr = sh.makeServiceCall("https://maps.googleapis.com/maps/api/directions/json?origin="+arr12[0]+"&destination="+arr12[1]+"&mode=transit&key=AIzaSyC46Pacq9qC23slsQdAP4-f7ETF9YkYfz4");
            if(!jsonStr.toLowerCase().contains("zero_results")) {
                if (jsonStr != null) {
                    try {
                        JSONObject jsonObj = new JSONObject(jsonStr);
                        JSONArray routeArray = jsonObj.getJSONArray("routes");
                        JSONObject routes = routeArray.getJSONObject(0);
                        JSONArray id = routes.getJSONArray("legs");
                        JSONObject id1 = id.getJSONObject(0);
                        JSONObject overviewPolylines = id1
                                .getJSONObject("distance");
                        JSONObject overviewPolylines1 = id1
                                .getJSONObject("duration");
                        encodedString = overviewPolylines.getString("text");
                        encodedString1 = overviewPolylines1.getString("text");
                    } catch (JSONException e) {
                    }
                }
            }else{
                fg="true";
            }
            //  if (jsonStr != null) try {
            // calculating the distance
                /*JSONObject jsonObj = new JSONObject(jsonStr);
                // Getting JSON Array node
                JSONArray contacts = jsonObj.getJSONArray("items");
                    JSONObject c = contacts.getJSONObject(0);
                    JSONArray id = c.getJSONArray("elements");
                JSONObject c1 = id.getJSONObject(0);
                    // Phone node is JSON Object
                    JSONObject phone = c1.getJSONObject("distance");
                     mobile = phone.getString("text");
                    // tmp hash map for single contact
*/
/*            JSONObject jsonObj = new JSONObject(jsonStr);
            // Getting JSON Array node
            JSONArray contacts = jsonObj.getJSONArray("items");
            for (int i = 0; i < contacts.length(); i++) {
                JSONObject c = contacts.getJSONObject(i);
                String title = c.getString("title");
                String link = c.getString("link");
                String snippet = c.getString("snippet");
                db2.addContact(new Contacts2(counter,title,link,snippet));
                your_array_list.add(new Contacts2(counter,title,link,snippet));
            }
        } catch (final JSONException e) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getApplicationContext(),
                            "Result cannot be found on Google..",
                            Toast.LENGTH_LONG)
                            .show();
                }
            });
        }*/
            return null;
            //}

        }

        @TargetApi(24)
        @Override
        protected void onPostExecute(Void aVoid) {

            super.onPostExecute(aVoid);
            if(fg=="false") {
                drawPath(jsonStr);
                Timer buttonTimer = new Timer();
                distanceText.setText("Distance: " + encodedString);
                timeText.setText("Time Duration: " + encodedString1);
                Typeface custom54 = Typeface.createFromAsset(getAssets(), "fonts/hellight.ttf");
                distanceText.setTypeface(custom54);
                Typeface custom55 = Typeface.createFromAsset(getAssets(), "fonts/hellight.ttf");
                timeText.setTypeface(custom55);
                buttonTimer.schedule(new TimerTask() {

                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                t1.speak(
                                        "The distance is " + encodedString + " and the time duration is " + encodedString1, TextToSpeech.QUEUE_FLUSH, null);
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
            }else{
                Timer buttonTimer = new Timer();
                buttonTimer.schedule(new TimerTask() {

                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                t1.speak(
                                        "Please say cities in same continents. ", TextToSpeech.QUEUE_FLUSH, null);
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
            //  text.setText(jsonStr);
            //     Document doc2= Jsoup.parse(x);
            //  String content=doc2.body().text();
            // tex.setText(content);
        }
    }
    class ParseURL6 extends AsyncTask<String, Void, Void> {
        public String x, s, s1, s2, s3, s4;

        @Override
        protected Void doInBackground(String... strings) {

            try {
                x = strings[0];
                Document doc = Jsoup.connect("http://www.weather-forecast.com/locations/" + x + "/forecasts/latest").get();
                Elements tex2 = doc.select("tr.max-temp-row span"); //for maz temp
                s = tex2.toString();
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
            Document doc3 = Jsoup.parse(s);
            String content2 = doc3.body().text();
          tempText.setText("Temp: "+content2.substring(4, 6) + "\u00b0" + "C "+"\ud83d\udd06");
            Typeface custom51 = Typeface.createFromAsset(getAssets(), "fonts/opensan.ttf");
            tempText.setTypeface(custom51);
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
                tex12 = (tex1.toString().substring(0, 2200)) + "..... (full article in wikipedia)";
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

           wikiText.setText(content);
            Typeface custom52 = Typeface.createFromAsset(getAssets(), "fonts/opensan.ttf");
            wikiText.setTypeface(custom52);
            new LoadImageTask(this).execute("https:" + imgSrc);

            // textMeaning.setText(j);
        }

        public void onImageLoaded(Bitmap bitmap) {

            image.setImageBitmap(bitmap);
        }

        @Override
        public void onError() {
            Toast.makeText(getApplicationContext(), "Error Loading Image !", Toast.LENGTH_SHORT).show();
        }
    }
    class ParseURL61 extends AsyncTask<String, Void, Void> {
        public String x, s, s1, s2, s3, s4,s5,h,jd;
        String content4;
        @Override
        protected Void doInBackground(String... strings) {

            try {
                x = strings[0];
                Geocoder geocoder;
                List<Address> addresses;
                geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
                addresses = geocoder.getFromLocation(location_lat, location_long, 1);
                h=addresses.get(0).getLocality();
                Document doc = Jsoup.connect("http://www.weather-forecast.com/locations/" + h + "/forecasts/latest").get();
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
             content4 = doc5.body().text();

            Document doc6 = Jsoup.parse(s3);
            String content5 = doc6.body().text();
            View v=findViewById(R.id.r);
            v.setBackgroundColor(Color.rgb(36,36,36));
            Document doc7 = Jsoup.parse(s4);
            String content6 = doc7.body().text();
            jd = h.substring(0, 1).toUpperCase() + h.substring(1).toLowerCase();
            cityName.setText(jd);
            maxTemp.setText(content2.substring(4, 6) + "\u00b0" + "C");
            Typeface custom51 = Typeface.createFromAsset(getAssets(), "fonts/hellight.ttf");
            maxTemp.setTypeface(custom51);
            //Mintempheadvalue.setText(content3.substring(4,6)+"\u00b0"+"C");
            minTemp.setText("Min Temp:" + content3.substring(4, 6) + "\u00b0" + "C "+"\ud83d\udd06");
            weatherText.setText(content4.toString());
            Typeface custom57 = Typeface.createFromAsset(getAssets(), "fonts/helmed.ttf");
            weatherText.setTypeface(custom57);
            humidity.setText("Humidity:" + content5.substring(0, 2).toString() + "% "+ "\ud83d\udca7");
            Timer buttonTimer = new Timer();
            buttonTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            t1.speak("Weather in "+jd+" is "+content4.toString().replaceAll("Tue","Tuesday").replaceAll("Wed","Wednesday").replaceAll("Sat","Saturday").replaceAll("Mon","Monday").replaceAll("Thurs","Thursday").replaceAll("Sun","Sunday").replaceAll("Fri","Friday")
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
            // Humidityheadvalue.setText( content5.substring(0,2).toString()+"%");
            // textView.setText(content2);
        }
    }
    class ParseURL63 extends AsyncTask<String, Void, Void> {
        public String x, s, s1, s2, s3, s4,s5,h,jd;
        String content4;
        @Override
        protected Void doInBackground(String... strings) {

            try {
                x = strings[0];
                Geocoder geocoder;
                List<Address> addresses;
                geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
                addresses = geocoder.getFromLocation(location_lat, location_long, 1);
                h=addresses.get(0).getLocality();
                Document doc = Jsoup.connect("http://www.weather-forecast.com/locations/" + h + "/forecasts/latest").get();
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
            content4 = doc5.body().text();

            Document doc6 = Jsoup.parse(s3);
            String content5 = doc6.body().text();
            View v=findViewById(R.id.r);
            v.setBackgroundColor(Color.rgb(36,36,36));
            Document doc7 = Jsoup.parse(s4);
            String content6 = doc7.body().text();
            jd = h.substring(0, 1).toUpperCase() + h.substring(1).toLowerCase();
            cityName.setText(jd);
            maxTemp.setText(content2.substring(4, 6) + "\u00b0" + "C");
            Typeface custom51 = Typeface.createFromAsset(getAssets(), "fonts/hellight.ttf");
            maxTemp.setTypeface(custom51);
            //Mintempheadvalue.setText(content3.substring(4,6)+"\u00b0"+"C");
            minTemp.setText("Min Temp:" + content3.substring(4, 6) + "\u00b0" + "C "+"\ud83d\udd06");
            weatherText.setText(content4.toString());
            Typeface custom57 = Typeface.createFromAsset(getAssets(), "fonts/helmed.ttf");
            weatherText.setTypeface(custom57);
            humidity.setText("Humidity:" + content5.substring(0, 2).toString() + "% "+ "\ud83d\udca7");
            Timer buttonTimer = new Timer();
            buttonTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            t1.speak("You are in "+jd

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
            // Humidityheadvalue.setText( content5.substring(0,2).toString()+"%");
            // textView.setText(content2);
        }
    }
    class ParseURL62 extends AsyncTask<String, Void, Void> {
        public String x, s, s1, s2, s3, s4,s5,h,jd;
        String content4;
        @Override
        protected Void doInBackground(String... strings) {

            try {
                x = strings[0];
                Geocoder geocoder;
                List<Address> addresses;
                geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
                addresses = geocoder.getFromLocation(location_lat, location_long, 1);
                h=addresses.get(0).getLocality();
                Document doc = Jsoup.connect("http://www.weather-forecast.com/locations/" + h + "/forecasts/latest").get();
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

            Document doc3 = Jsoup.parse(s); //max temp row
            String content2 = doc3.body().text();

            Document doc4 = Jsoup.parse(s1);  //min temp row
            String content3 = doc4.body().text();

            Document doc5 = Jsoup.parse(s2); //sentences
            content4 = doc5.body().text();

            Document doc6 = Jsoup.parse(s3);
            String content5 = doc6.body().text(); //humidity

            View v=findViewById(R.id.r);
            v.setBackgroundColor(Color.rgb(36,36,36));
            Document doc7 = Jsoup.parse(s4);
            String content6 = doc7.body().text();

            jd = h.substring(0, 1).toUpperCase() + h.substring(1).toLowerCase();
            cityName.setText(jd);
            int h2=Integer.parseInt(x);
            if(h2==1){
                String gh=content2.replaceAll("C C ","");
                String[] arr = gh.split(" ");
                maxTemp.setText(arr[3]+"\u00b0" + "C"+"\ud83d\udd06");
                Typeface custom51 = Typeface.createFromAsset(getAssets(), "fonts/hellight.ttf");
                maxTemp.setTypeface(custom51);

                String gh1=content3.replaceAll("C C ","");
                String[] arr1 = gh1.split(" ");
                minTemp.setText("Min Temp:"+arr1[3]+"\u00b0" + "C"+"\ud83d\udd06");
                String gh2=content5;
                String[] arr2 = gh2.split(" ");
                humidity.setText("Humidity:"+arr2[3]+"%"+"\ud83d\udca7");
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
                                t1.speak("Weather in "+jd+" is "+content4.toString().replaceAll("Tue","Tuesday").replaceAll("Wed","Wednesday").replaceAll("Sat","Saturday").replaceAll("Mon","Monday").replaceAll("Thurs","Thursday").replaceAll("Sun","Sunday").replaceAll("Fri","Friday")
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
            if(h2==2){
                String gh=content2.replaceAll("C C ","");
                String[] arr = gh.split(" ");
                maxTemp.setText(arr[6]+"\u00b0" + "C"+"\ud83d\udd06");
                Typeface custom51 = Typeface.createFromAsset(getAssets(), "fonts/hellight.ttf");
                maxTemp.setTypeface(custom51);

                String gh1=content3.replaceAll("C C ","");
                String[] arr1 = gh1.split(" ");
                minTemp.setText("Min Temp:"+arr1[6]+"\u00b0" + "C"+"\ud83d\udd06");
                String gh2=content5;
                String[] arr2 = gh2.split(" ");
                humidity.setText("Humidity:"+arr2[6]+"%"+"\ud83d\udca7");
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
                                t1.speak("Weather in "+jd+" is "+content4.toString().replaceAll("Tue","Tuesday").replaceAll("Wed","Wednesday").replaceAll("Sat","Saturday").replaceAll("Mon","Monday").replaceAll("Thurs","Thursday").replaceAll("Sun","Sunday").replaceAll("Fri","Friday")
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
            if(h2==3){
                String gh=content2.replaceAll("C C ","");
                String[] arr = gh.split(" ");
                maxTemp.setText(arr[9]+"\u00b0" + "C "+"\ud83d\udd06");
                Typeface custom51 = Typeface.createFromAsset(getAssets(), "fonts/hellight.ttf");
                maxTemp.setTypeface(custom51);

                String gh1=content3.replaceAll("C C ","");
                String[] arr1 = gh1.split(" ");
                minTemp.setText("Min Temp:"+arr1[9]+"\u00b0" + "C "+"\ud83d\udd06");
                String gh2=content5;
                String[] arr2 = gh2.split(" ");
                humidity.setText("Humidity:"+arr2[9]+"%"+"\ud83d\udca7");
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
                                t1.speak("Weather in "+jd+" is "+content4.toString().replaceAll("Tue","Tuesday").replaceAll("Wed","Wednesday").replaceAll("Sat","Saturday").replaceAll("Mon","Monday").replaceAll("Thurs","Thursday").replaceAll("Sun","Sunday").replaceAll("Fri","Friday")
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
            if(h2==4){
                String gh=content2.replaceAll("C C ","");
                String[] arr = gh.split(" ");
                maxTemp.setText(arr[12]+"\u00b0" + "C "+"\ud83d\udd06");
                Typeface custom51 = Typeface.createFromAsset(getAssets(), "fonts/hellight.ttf");
                maxTemp.setTypeface(custom51);

                String gh1=content3.replaceAll("C C ","");
                String[] arr1 = gh1.split(" ");
                minTemp.setText("Min Temp:"+arr1[12]+"\u00b0" + "C "+"\ud83d\udd06");
                String gh2=content5;
                String[] arr2 = gh2.split(" ");
                humidity.setText("Humidity:"+arr2[12]+"% "+"\ud83d\udca7");
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
                                t1.speak("Weather in "+jd+" is "+content4.toString().replaceAll("Tue","Tuesday").replaceAll("Wed","Wednesday").replaceAll("Sat","Saturday").replaceAll("Mon","Monday").replaceAll("Thurs","Thursday").replaceAll("Sun","Sunday").replaceAll("Fri","Friday")
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
            if(h2==5){
                String gh=content2.replaceAll("C C ","");
                String[] arr = gh.split(" ");
                maxTemp.setText(arr[15]+"\u00b0" + "C "+"\ud83d\udd06");
                Typeface custom51 = Typeface.createFromAsset(getAssets(), "fonts/hellight.ttf");
                maxTemp.setTypeface(custom51);

                String gh1=content3.replaceAll("C C ","");
                String[] arr1 = gh1.split(" ");
                minTemp.setText("Min Temp:"+arr1[15]+"\u00b0" + "C "+"\ud83d\udd06");
                String gh2=content5;
                String[] arr2 = gh2.split(" ");
                humidity.setText("Humidity:"+arr2[15]+"% "+"\ud83d\udca7");
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
                                t1.speak("Weather in "+jd+" is "+content4.toString().replaceAll("Tue","Tuesday").replaceAll("Wed","Wednesday").replaceAll("Sat","Saturday").replaceAll("Mon","Monday").replaceAll("Thurs","Thursday").replaceAll("Sun","Sunday").replaceAll("Fri","Friday")
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
            if(h2==6){
                String gh=content2.replaceAll("C C ","");
                String[] arr = gh.split(" ");
                maxTemp.setText(arr[18]+"\u00b0" + "C "+"\ud83d\udd06");
                Typeface custom51 = Typeface.createFromAsset(getAssets(), "fonts/hellight.ttf");
                maxTemp.setTypeface(custom51);

                String gh1=content3.replaceAll("C C ","");
                String[] arr1 = gh1.split(" ");
                minTemp.setText("Min Temp:"+arr1[18]+"\u00b0" + "C "+"\ud83d\udd06");
                String gh2=content5;
                String[] arr2 = gh2.split(" ");
                humidity.setText("Humidity:"+arr2[18]+"% "+"\ud83d\udca7");
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
                                t1.speak("Weather in "+jd+" is "+content4.toString().replaceAll("Tue","Tuesday").replaceAll("Wed","Wednesday").replaceAll("Sat","Saturday").replaceAll("Mon","Monday").replaceAll("Thurs","Thursday").replaceAll("Sun","Sunday").replaceAll("Fri","Friday")
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
            if(h2==7){
                String gh=content2.replaceAll("C C ","");
                String[] arr = gh.split(" ");
                maxTemp.setText(arr[21]+"\u00b0" + "C "+"\ud83d\udd06");
                Typeface custom51 = Typeface.createFromAsset(getAssets(), "fonts/hellight.ttf");
                maxTemp.setTypeface(custom51);

                String gh1=content3.replaceAll("C C ","");
                String[] arr1 = gh1.split(" ");
                minTemp.setText("Min Temp:"+arr1[21]+"\u00b0" + "C "+"\ud83d\udd06");
                String gh2=content5;
                String[] arr2 = gh2.split(" ");
                humidity.setText("Humidity:"+arr2[21]+"% "+"\ud83d\udca7");
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
                                t1.speak("Weather in "+jd+" is "+content4.toString().replaceAll("Tue","Tuesday").replaceAll("Wed","Wednesday").replaceAll("Sat","Saturday").replaceAll("Mon","Monday").replaceAll("Thurs","Thursday").replaceAll("Sun","Sunday").replaceAll("Fri","Friday")
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
            if(h2==8){
                String gh=content2.replaceAll("C C ","");
                String[] arr = gh.split(" ");
                maxTemp.setText(arr[24]+"\u00b0" + "C "+"\ud83d\udd06");
                Typeface custom51 = Typeface.createFromAsset(getAssets(), "fonts/hellight.ttf");
                maxTemp.setTypeface(custom51);

                String gh1=content3.replaceAll("C C ","");
                String[] arr1 = gh1.split(" ");
                minTemp.setText("Min Temp:"+arr1[24]+"\u00b0" + "C "+"\ud83d\udd06");
                String gh2=content5;
                String[] arr2 = gh2.split(" ");
                humidity.setText("Humidity:"+arr2[24]+"% "+"\ud83d\udca7");
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
                                t1.speak("Weather in "+jd+" is "+content4.toString().replaceAll("Tue","Tuesday").replaceAll("Wed","Wednesday").replaceAll("Sat","Saturday").replaceAll("Mon","Monday").replaceAll("Thurs","Thursday").replaceAll("Sun","Sunday").replaceAll("Fri","Friday")
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
            if(h2==9){
                String gh=content2.replaceAll("C C ","");
                String[] arr = gh.split(" ");
                maxTemp.setText(arr[25]+"\u00b0" + "C "+"\ud83d\udd06");
                Typeface custom51 = Typeface.createFromAsset(getAssets(), "fonts/hellight.ttf");
                maxTemp.setTypeface(custom51);

                String gh1=content3.replaceAll("C C ","");
                String[] arr1 = gh1.split(" ");
                minTemp.setText("Min Temp:"+arr1[25]+"\u00b0" + "C "+"\ud83d\udd06");
                String gh2=content5;
                String[] arr2 = gh2.split(" ");
                humidity.setText("Humidity:"+arr2[25]+"% "+"\ud83d\udca7");
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
                                t1.speak("Weather in "+jd+" is "+content4.toString().replaceAll("Tue","Tuesday").replaceAll("Wed","Wednesday").replaceAll("Sat","Saturday").replaceAll("Mon","Monday").replaceAll("Thurs","Thursday").replaceAll("Sun","Sunday").replaceAll("Fri","Friday")
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
            if(h2==10){
                String gh=content2.replaceAll("C C ","");
                String[] arr = gh.split(" ");
                maxTemp.setText(arr[26]+"\u00b0" + "C "+"\ud83d\udd06");
                Typeface custom51 = Typeface.createFromAsset(getAssets(), "fonts/hellight.ttf");
                maxTemp.setTypeface(custom51);

                String gh1=content3.replaceAll("C C ","");
                String[] arr1 = gh1.split(" ");
                minTemp.setText("Min Temp:"+arr1[26]+"\u00b0" + "C "+"\ud83d\udd06");
                String gh2=content5;
                String[] arr2 = gh2.split(" ");
                humidity.setText("Humidity:"+arr2[26]+"% "+"\ud83d\udca7");
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
                                t1.speak("Weather in "+jd+" is "+content4.toString().replaceAll("Tue","Tuesday").replaceAll("Wed","Wednesday").replaceAll("Sat","Saturday").replaceAll("Mon","Monday").replaceAll("Thurs","Thursday").replaceAll("Sun","Sunday").replaceAll("Fri","Friday")
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
          /*  maxTemp.setText(content2.substring(4, 6) + "\u00b0" + "C");

            Typeface custom51 = Typeface.createFromAsset(getAssets(), "fonts/hellight.ttf");
            maxTemp.setTypeface(custom51);

            //Mintempheadvalue.setText(content3.substring(4,6)+"\u00b0"+"C");
            minTemp.setText("Min Temp:" + content3.substring(4, 6) + "\u00b0" + "C");
            weatherText.setText(content4.toString());
            Typeface custom57 = Typeface.createFromAsset(getAssets(), "fonts/helmed.ttf");
            weatherText.setTypeface(custom57);
            humidity.setText("Humidity:" + content5.substring(0, 2).toString() + "%");
            Timer buttonTimer = new Timer();

            buttonTimer.schedule(new TimerTask() {

                @Override
                public void run() {
                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            t1.speak("Weather in "+jd+" is "+content4.toString().replaceAll("Tue","Tuesday").replaceAll("Wed","Wednesday").replaceAll("Sat","Saturday").replaceAll("Mon","Monday").replaceAll("Thurs","Thursday").replaceAll("Sun","Sunday").replaceAll("Fri","Friday")
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
            });*/
            // Humidityheadvalue.setText( content5.substring(0,2).toString()+"%");
            // textView.setText(content2);
        }

    }
    class ParseURL22 extends AsyncTask<String, Void, Void> implements LoadImageTask.Listener{
        public String s,h;
        @Override
        public void onImageLoaded(Bitmap bitmap) {
            restaurant_image.setImageBitmap(bitmap);
        }
        @Override
        public void onError() {
        }
        List<Address> addresses;
        public String imgSrc;
        public String tex12, tex13,tex14;
        String jsonStr;
        @Override
        protected Void doInBackground(String... strings) {
            try {
                Geocoder geocoder;
                List<Address> addresses;
                geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
                addresses = geocoder.getFromLocation(location_lat, location_long, 1);
               // h=addresses.get(0).getLocality().toLowerCase();
                 h="richmond";
               // String x = strings[0].toString();

                Document doc = Jsoup.connect("https://www.happycow.net/searchmap/?kw=&location="+h+"&vegetarian=true&distance=100&distanceType=km&limit=81").get();
                Elements img = doc.select("div.image.col-md-12 img[src]");
                Elements tex1 = doc.select("div.info");
                Elements tex2 = doc.select("span.distance");
                Elements tex3=doc.select("div.venue-location");
                // Elements tex2 = doc.select("div.thumbcaption");
                imgSrc = img.attr("src");
                tex12 =tex1.toString();
                 tex13=tex2.toString();
                tex14=tex3.toString();
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
            Document doc5 = Jsoup.parse(tex14);
            String content5 = doc5.body().text();
            Document doc2 = Jsoup.parse(tex12);
            String content = doc2.body().text();
            Document doc3 = Jsoup.parse(tex13);
            final String content2 = doc3.body().text();
            if(!imgSrc.contains("http")){
                imgSrc="https:/"+imgSrc;
            }
            resdistance.setText("Distance:" + content2.substring(0, (content2.indexOf("km") + 2)));
            View v=findViewById(R.id.r);
            v.setBackgroundColor(Color.rgb(36,36,36));
            Toast.makeText(getApplicationContext(),imgSrc+"",Toast.LENGTH_LONG).show();
       /*     String[] arr=content.split(" ");
            final StringBuffer str=new StringBuffer();
            str.append("1.");
            for(String a:arr){
                str.append(a+" ");
                if(a.contains("1")||a.contains("0")||a.contains("9")||a.contains("8")||a.contains("7")||a.contains("6")||a.contains("5")||a.contains("4")||a.contains("3")||a.contains("2"))
                {
                    break;
                }
            }*/
            final String fg=content.substring(0,content.indexOf(","));
            Timer buttonTimer = new Timer();
            buttonTimer.schedule(new TimerTask() {

                @Override
                public void run() {
                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            t1.speak("Here is 1 restaurant i have got.. It's near "+fg+" about "+content2.substring(0, (content2.indexOf("km") + 2))
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
            String[] arr3=content5.split(" ");
            Geocoder geocoder = new Geocoder(getBaseContext());
            try {
                    // Getting a maximum of 3 Address that matches the input
                    // text
                    addresses = geocoder.getFromLocationName(arr3[0]+arr3[1], 3);
                if (addresses != null && !addresses.equals(""))
                    search(addresses);
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(),"Search the location again",Toast.LENGTH_LONG).show();
            }
           // customAdapter4=new CustomAdapter4(getApplicationContext(),str.toString(),imgSrc);
         //   rlistView.setAdapter(customAdapter4);
            restaurant_text.setText(fg+","+h);
           new LoadImageTask(this).execute(imgSrc);
            // textMeaning.setText(j);
        }
    }
    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

}