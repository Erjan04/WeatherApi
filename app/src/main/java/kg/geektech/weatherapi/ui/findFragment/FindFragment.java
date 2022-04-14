package kg.geektech.weatherapi.ui.findFragment;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.MarkerOptions;

import org.jetbrains.annotations.NotNull;

import dagger.hilt.android.AndroidEntryPoint;
import kg.geektech.weatherapi.R;
import kg.geektech.weatherapi.base.BaseFragment;
import kg.geektech.weatherapi.databinding.FragmentFindBinding;

@AndroidEntryPoint
public class FindFragment extends BaseFragment<FragmentFindBinding> implements GoogleMap.OnMyLocationClickListener, GoogleMap.OnMyLocationButtonClickListener, OnMapReadyCallback, LocationListener {

    private GoogleMap map;
    private final String[] perms = new String[]{
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
    };
    private LocationManager manager;

    private void getCurrentLocation() {
        if (ActivityCompat.checkSelfPermission
                (requireActivity(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission
                (requireActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            manager.requestLocationUpdates(
                    LocationManager.NETWORK_PROVIDER,
                    0,
                    0,
                    this
            );
            map.setMyLocationEnabled(true);
        }
    }

    @Override
    protected FragmentFindBinding bind() {
        return FragmentFindBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void setupObservers() {

    }

    @Override
    protected void setupListeners() {

    }

    @Override
    protected void setupUI() {
        manager = (LocationManager) requireActivity().getSystemService(Context.LOCATION_SERVICE);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.fragment);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {

    }

    @Override
    public void onLocationChanged(@NonNull Location location) {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        initListenerMarker();
        map.setOnMyLocationButtonClickListener(this);
        map.setOnMyLocationClickListener(this);
        getCurrentLocation();
    }


    private void initListenerMarker() {
        ActivityCompat.requestPermissions(requireActivity(), perms, 1);
        map.setOnMapClickListener(latLng -> {
            MarkerOptions marker = new MarkerOptions();
            marker.position(latLng);
            map.clear();
            map.addMarker(marker);
            map.animateCamera(CameraUpdateFactory.newCameraPosition(
                    CameraPosition.builder().zoom(5f).target(latLng).build()
            ));
            map.setOnMarkerClickListener(marker1 -> {
                navController.navigate(FindFragmentDirections.actionFindFragmentToWeatherFragment(
                        String.valueOf(latLng.latitude), String.valueOf(latLng.longitude)
                ));
                return false;
            });
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull @NotNull String[] permissions, @NonNull @NotNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode != 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                getCurrentLocation();
            } else {
                ActivityCompat.requestPermissions(requireActivity(), permissions, 1);
            }
        }
    }

    @Override
    public boolean onMyLocationButtonClick() {
        Toast.makeText(requireContext(), "MyLocation button clicked", Toast.LENGTH_SHORT).show();
        return false;
    }

    @Override
    public void onMyLocationClick(@NonNull Location location) {
        Toast.makeText(requireContext(), "Current location:\n" + location, Toast.LENGTH_SHORT).show();
    }
}