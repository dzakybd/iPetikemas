package id.ac.its.ipetikemas;


import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.akexorcist.googledirection.DirectionCallback;
import com.akexorcist.googledirection.GoogleDirection;
import com.akexorcist.googledirection.constant.AvoidType;
import com.akexorcist.googledirection.model.Direction;
import com.akexorcist.googledirection.util.DirectionConverter;
import com.github.anastr.speedviewlib.ProgressiveGauge;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 */
public class TrackFragment extends Fragment implements
        OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {
    GoogleMap mGoogleMap;
    LocationRequest mLocationRequest;
    GoogleApiClient mGoogleApiClient;
    Marker marker_truck;
    MarkerOptions markerOptions;
    boolean locsetted = false;
    @BindView(R.id.map)
    MapView mMapView;
    @BindView(R.id.truck)
    ImageView truck;
    @BindView(R.id.noitv)
    TextView noitv;
    @BindView(R.id.nama)
    TextView nama;
    @BindView(R.id.speedView)
    ProgressiveGauge speedView;
    @BindView(R.id.container)
    ImageView container;
    @BindView(R.id.nocontainer)
    TextView nocontainer;
    @BindView(R.id.blok)
    TextView blok;
    @BindView(R.id.jarak)
    TextView jarak;
    @BindView(R.id.durasi)
    TextView durasi;
    Unbinder unbinder;
    String string_nama,string_noitv,string_nocon,string_blok;

    public TrackFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_track, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        mMapView.onCreate(savedInstanceState);
        mMapView.getMapAsync(this);
        string_nama="Andi";
        string_nocon="L123";
        string_noitv="A1";
        string_blok="L";
        blok.setText(string_blok);
        nama.setText(string_nama);
        noitv.setText(string_noitv);
        nocontainer.setText(string_nocon);
        return rootView;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            buildGoogleApiClient();
            //mGoogleMap.setMyLocationEnabled(true);
        }
    }


    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {
        if (marker_truck != null) {
            marker_truck.remove();
        }
        speedView.speedTo(location.getSpeed());
        markerOptions = new MarkerOptions();
        markerOptions.position(new LatLng(location.getLatitude(), location.getLongitude()));
        //markerOptions.title();
        markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_truck));
        marker_truck = mGoogleMap.addMarker(markerOptions);
        mapsettedupdate(location.getLatitude(), location.getLongitude(), location.getLatitude() + 0.005, location.getLongitude() + 0.005);
//        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(location.getLatitude(), location.getLongitude())));
        if(!locsetted){
            locsetted = true;
            markerOptions = new MarkerOptions();
            markerOptions.position(new LatLng(location.getLatitude() + 0.005, location.getLongitude() + 0.005));
//            markerOptions.title(nama);
            markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_hook));
            mGoogleMap.addMarker(markerOptions);
            mapsetted(location.getLatitude(), location.getLongitude(), location.getLatitude() + 0.005, location.getLongitude() + 0.005);
        }

    }

    public void mapsettedupdate(double lat, double lng, double lat2, double lng2) {
        LatLng awal = new LatLng(lat, lng);
        LatLng tujuan = new LatLng(lat2, lng2);
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        builder.include(awal);
        builder.include(tujuan);
        GoogleDirection.withServerKey(getResources().getString(R.string.googlegeneralkey))
                .from(awal)
                .to(tujuan)
                .avoid(AvoidType.TOLLS)
                .avoid(AvoidType.FERRIES)
                .execute(new DirectionCallback() {
                    @Override
                    public void onDirectionSuccess(Direction direction, String rawBody) {
                        if (direction.isOK()) {
                            String distance = direction.getRouteList().get(0).getLegList().get(0).getDistance().getText();
                            String duration = direction.getRouteList().get(0).getLegList().get(0).getDuration().getText();
                            jarak.setText(distance);
                            durasi.setText(duration);
                        } else {
                            Log.d("mapse", rawBody);
                        }
                    }

                    @Override
                    public void onDirectionFailure(Throwable t) {
                        Log.d("mapse", t.toString());
                    }
                });
        LatLngBounds bounds = builder.build();
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, 50));
    }

    public void mapsetted(double lat, double lng, double lat2, double lng2) {
        LatLng awal = new LatLng(lat, lng);
        LatLng tujuan = new LatLng(lat2, lng2);
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        builder.include(awal);
        builder.include(tujuan);
        GoogleDirection.withServerKey(getResources().getString(R.string.googlegeneralkey))
                .from(awal)
                .to(tujuan)
                .avoid(AvoidType.TOLLS)
                .avoid(AvoidType.FERRIES)
                .execute(new DirectionCallback() {
                    @Override
                    public void onDirectionSuccess(Direction direction, String rawBody) {
                        if (direction.isOK()) {
                            ArrayList<LatLng> directionPositionList = direction.getRouteList().get(0).getLegList().get(0).getDirectionPoint();
                            String distance = direction.getRouteList().get(0).getLegList().get(0).getDistance().getText();
                            String duration = direction.getRouteList().get(0).getLegList().get(0).getDuration().getText();
                            jarak.setText(distance);
                            durasi.setText(duration);
                            mGoogleMap.addPolyline(DirectionConverter.createPolyline(getActivity(), directionPositionList, 5, ResourcesCompat.getColor(getResources(), R.color.colorPrimary, null)));
                        } else {
                            Log.d("mapse", rawBody);
                        }
                    }

                    @Override
                    public void onDirectionFailure(Throwable t) {
                        Log.d("mapse", t.toString());
                    }
                });
        LatLngBounds bounds = builder.build();
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, 50));
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mGoogleApiClient != null) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
        }
        mMapView.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mMapView.onSaveInstanceState(outState);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
