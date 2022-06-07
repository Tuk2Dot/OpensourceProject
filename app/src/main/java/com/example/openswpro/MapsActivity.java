package com.example.openswpro;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.openswpro.databinding.ActivityMapsBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    JSONArray jsonArray;
    JSONObject jsonObject;
    Node[] graph;

    int target;

    EditText editText1, editText2;
    Button button;

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



        // Constructing a weighted graph
        try {
            jsonArray = ReadingJSON.GetNodes();
        } catch (JSONException e) { e.printStackTrace(); }

        graph = new Node[jsonArray.length() + 1];

        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                graph[i] = new Node(i, target, jsonArray);
            } catch (JSONException e) { e.printStackTrace(); }
        }
    }

    protected void onStart() {
        super.onStart();


    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng cbnu = new LatLng(36.6, 127.4);
        mMap.addMarker(new MarkerOptions().position(cbnu).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(cbnu));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(cbnu, 15));


    }


}