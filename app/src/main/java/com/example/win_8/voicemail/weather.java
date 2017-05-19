package com.example.win_8.voicemail;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by win-8 on 21-02-2017.
 */
public class weather {

    String text,s,p;boolean checked=false,checked1=false;
    public weather(String text){
        this.text=text;
    }
    public void process(){
        checked=false;
        //checked1=false;
        if(text.toLowerCase().contains("weather")&&text.toLowerCase().contains("reports")) {
            checked = true;

            if (text.toLowerCase().contains("get")) {
                s = text.substring(23);
                Pattern pattern2 = Pattern.compile("\\s");
                Matcher matcher2 = pattern2.matcher(s);
                if (matcher2.find() == true) {
                    String s6 = s.replaceAll(" ", "-");
                    p = s6;
                } else {
                    p = s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
                }
            } else {
                s = text.substring(19);
                Pattern pattern2 = Pattern.compile("\\s");
                Matcher matcher2 = pattern2.matcher(s);
                if (matcher2.find() == true) {
                    String s6 = s.replaceAll(" ", "-");

                    p = s6;
                } else {
                    p = s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
                }
            }

        }
        if(checked==false) {
            if (text.toLowerCase().contains("weather")) {
                if (text.toLowerCase().contains("of")) {
                    s = text.substring(11);
                    Pattern pattern2 = Pattern.compile("\\s");
                    Matcher matcher2 = pattern2.matcher(s);
                    if (matcher2.find() == true) {
                        String s6 = s.replaceAll(" ", "-");

                        p = s6;
                    } else {
                        p = s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
                    }
                } else {
                    s = text.substring(8);
                    Pattern pattern2 = Pattern.compile("\\s");
                    Matcher matcher2 = pattern2.matcher(s);
                    if (matcher2.find() == true) {
                        String s6 = s.replaceAll(" ", "-");

                        p = s6;
                    } else {
                        p = s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
                    }
                }
            }
            if(text.toLowerCase().equals("get weather updates")){

            }
            if (text.toLowerCase().contains("updates")) {
                if (text.toLowerCase().contains("get")) {
                    s = text.substring(23);
                    p = s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
                } else {
                    s = text.substring(19);
                    p = s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
                }
            }
            if (text.toLowerCase().contains("what's") && text.toLowerCase().contains("weather") && text.toLowerCase().contains("in")) {
                s = text.substring(22);
                Pattern pattern2 = Pattern.compile("\\s");
                Matcher matcher2 = pattern2.matcher(s);
                if (matcher2.find() == true) {
                    String s6 = s.replaceAll(" ", "-");
                    p = s6;
                } else {
                 p = s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
                }
            }

            if (text.toLowerCase().contains("what") && text.toLowerCase().contains("is") && text.toLowerCase().contains("weather")) {
                s = text.substring(23);
                Pattern pattern2 = Pattern.compile("\\s");
                Matcher matcher2 = pattern2.matcher(s);
                if (matcher2.find() == true) {
                    String s6 = s.replaceAll(" ", "-");
                    p = s6;
                } else {
                    p = s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
                }
            }
        }
    }

    public String getP(){
        return p;
    }
/*
    @Override
    public void onConnected(Bundle bundle) {
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
    public void onLocationChanged(Location location) {
        mLastLocation = location;
        cf++;
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

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

    }
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
    }*/
}
