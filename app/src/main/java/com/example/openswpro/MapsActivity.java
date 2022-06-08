package com.example.openswpro;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.openswpro.databinding.ActivityMapsBinding;
import com.google.android.gms.tasks.OnSuccessListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    public static final int DEFAULT_UPDATE_INTERVAL = 30;
    public static final int FAST_UPDATE_INTERVAL = 5;
    private static final int PERMISSIONS_FINE_LOCATION = 99;
    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    JSONArray jsonArray;
    JSONObject jsonObject;
    Node[] graph;

    int target;

    EditText editText1, editText2;
    Button button;

    public FusedLocationProviderClient fusedLocationClient;
    public LocationRequest locationRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        editText1 = (EditText) findViewById(R.id.editText1);
        editText2 = (EditText) findViewById(R.id.editText2);
        button = (Button) findViewById(R.id.button);

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        locationRequest = LocationRequest.create()
                .setInterval(1000 * DEFAULT_UPDATE_INTERVAL)
                .setFastestInterval(1000 * FAST_UPDATE_INTERVAL)
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setMaxWaitTime(100);

        // 그래프 생성
        try {
            jsonArray = ReadingJSON.GetNodes();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        graph = new Node[jsonArray.length() + 1];

        for (int i = 1; i < jsonArray.length() + 1; i++) {
            try {
                graph[i] = new Node(i, target, jsonArray);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        // 현재 위치 노드도 추가 graph[0]
        updateGPS();

    } // end of onCreate()

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case PERMISSIONS_FINE_LOCATION:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    updateGPS();
                }
                else {
                    Toast.makeText(this, "This service requires permission to be granted in order to work properly", Toast.LENGTH_SHORT).show();
                    finish();
                }
        }
    }

    private void updateGPS() {
        // get permissions from the user to track GPS
        // get the current location from the fused client
        // update the graph

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(MapsActivity.this);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            // user provided the permission
            fusedLocationClient .getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    // we got permissions. Put the values of location
                    double x = location.getLongitude();
                    double y = location.getLatitude();
                    Node.Edge closestLink;
                    closestLink = findClosestNode(x, y);

                    Node node_zero = new Node(x, y, closestLink);
                    graph[0] = node_zero;
                }
            });
        }
        else {
            // permissions not granted yet.
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSIONS_FINE_LOCATION);
            }
        }
    }

    private Node.Edge findClosestNode(double x, double y) {
        int min = 1;
        double distance = calculateDistance(x, y, 1);
        Node.Edge edge_value = null;

        for (int i = 1; i < graph.length;) {
            if (distance < calculateDistance(x, y, i++))
                min = i;
        }
        edge_value.weight = distance;
        edge_value.node = min;

        return edge_value;
    }

    private double calculateDistance(double x, double y, int i) {
        return Math.sqrt(Math.pow(x - graph[i].x, 2) + Math.pow(y - graph[i].y, 2));
    }

    private void updateGraph(Location location) {
        // update the graph, route and navigation
        // (double) location.getLatitude()
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {       // getMapAsync()로 지도 객체를 사용할 수 있을 때
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng cbnu = new LatLng(36.6, 127.4);
        mMap.addMarker(new MarkerOptions().position(cbnu).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(cbnu));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(cbnu, 15));

    } // end of onMapReady
}