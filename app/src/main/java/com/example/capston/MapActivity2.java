package com.example.capston;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.capston.databinding.ActivityMapBinding;
import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.CameraUpdate;
import com.naver.maps.map.LocationTrackingMode;
import com.naver.maps.map.MapView;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.UiSettings;
import com.naver.maps.map.overlay.Marker;
import com.naver.maps.map.overlay.Overlay;
import com.naver.maps.map.overlay.OverlayImage;
import com.naver.maps.map.util.FusedLocationSource;



import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MapActivity2 extends AppCompatActivity implements OnMapReadyCallback, Overlay.OnClickListener{
    ActivityMapBinding binding;
    List<Address> list = null;
    private MapView mapView;
    private static NaverMap naverMap;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1000;
    private FusedLocationSource locationSource;
    private Geocoder geocoder;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_map);

        // Toolbar 활성화
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        MapView mapView =(MapView)findViewById(R.id.map_view);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);

        locationSource = new FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE);
    }

    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        this.naverMap = naverMap;
        geocoder = new Geocoder(this);
        naverMap.setLocationSource(locationSource);
        naverMap.setLocationTrackingMode(LocationTrackingMode.Follow);
        UiSettings uiSettings = naverMap.getUiSettings();
        uiSettings.setLocationButtonEnabled(true);

        binding.mapButton.setOnClickListener(view -> {
            String str = binding.mapText.getText().toString();
            List<Address> addressList = null;
            try {
                addressList = geocoder.getFromLocationName(
                        str,
                        10);

            } catch (IOException e) {
                e.printStackTrace();
            }

            String []splitStr = addressList.get(0).toString().split(",");
            String address = splitStr[0].substring(splitStr[0].indexOf("\"")+1, splitStr[0].length()-2);

            String latitude = splitStr[10].substring(splitStr[10].indexOf("=")+1);
            String longitude = splitStr[12].substring(splitStr[12].indexOf("=")+1);

            LatLng point = new LatLng(Double.parseDouble(latitude), Double.parseDouble(longitude));
            Marker marker = new Marker();
            marker.setPosition(new LatLng(Double.parseDouble(latitude), Double.parseDouble(longitude)));
            marker.setWidth(100);
            marker.setHeight(100);
            marker.setIcon(OverlayImage.fromResource(R.drawable.marker));
            marker.setMap(naverMap);
            marker.setOnClickListener(this);
            try {
                list = geocoder.getFromLocation(
                        Double.parseDouble(latitude),
                        Double.parseDouble(longitude),
                        10);
            } catch (IOException e){
                e.printStackTrace();
            }
            CameraUpdate cameraUpdate = CameraUpdate.scrollTo(point);
            naverMap.moveCamera(cameraUpdate);
        });

    }

    @Override
    public boolean onClick(@NonNull Overlay overlay) {
        if(overlay instanceof Marker) {
            String str = list.get(0).toString();
            Pattern pattern = Pattern.compile("[\"](.*?)[\"]");
            Matcher matcher = pattern.matcher(str);
            String end_location = null;
            while (matcher.find()) {  // 일치하는 게 있다면
                end_location = matcher.group(1);
                if(matcher.group(1) ==  null)
                    break;
            }
            Intent intent = new Intent();
            intent.putExtra("end_location", end_location);
            setResult(RESULT_OK, intent);
            finish();
            return true;
        }
        return false;
    }
}