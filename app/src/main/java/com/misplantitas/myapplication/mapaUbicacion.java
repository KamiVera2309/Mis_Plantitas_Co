package com.misplantitas.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.preference.PreferenceManager;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.Polyline;

public class mapaUbicacion extends AppCompatActivity {
    private MapView mapView;
    private FusedLocationProviderClient fusedLocationClient;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    Marker MiMarker;
    IMapController mapController;
    Polyline polyline = new Polyline();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa_ubicacion);

        TextView txt = (TextView) findViewById(R.id.MostrarLocacion);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        Configuration.getInstance().load(getApplicationContext(),
                PreferenceManager.getDefaultSharedPreferences(getApplicationContext()));


        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }

        mapView = findViewById(R.id.mapView);

        // Configura el mapa
        mapView.setTileSource(TileSourceFactory.MAPNIK);
        mapView.setBuiltInZoomControls(true);
        mapView.setMultiTouchControls(true);

        //Coordenadas de Casa Kam
        double casaKamLatitude = -33.50052522139168;
        double casaKamLongitude = -70.74533854232784;

        //Crear objetos GeoPoint para los marcadores
        GeoPoint CasaKamPoint = new GeoPoint(casaKamLatitude, casaKamLongitude);

        //Crear marcadores con títulos y descripciones
        Marker kamiMarker = new Marker(mapView);
        kamiMarker.setPosition(CasaKamPoint);
        kamiMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        kamiMarker.setTitle("Mis Plantitas Co");
        kamiMarker.setSnippet("Maipu, Santiago.");

        //Agregar marcadores al mapa (programador)
        mapView.getOverlays().add(kamiMarker);
        // Verificar y solicitar permisos si es necesario
        if (checkLocationPermission()) {
            requestLocation();
        }

        // Centrar el mapa en Santiago, Chile
        mapController = mapView.getController();
        mapController.setCenter(CasaKamPoint);
        mapController.setZoom(14);

        // Puedes ajustar el nivel de zoom según sea necesario

    }

    private boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // Si no se tienen permisos, solicitarlos en tiempo de ejecución
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
            return false;
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                requestLocation(); // Si se otorgan los permisos, obtener la ubicación
            }
        }
    }

    private void requestLocation() {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location != null) {
                            double latitude = location.getLatitude();
                            double longitude = location.getLongitude();

                            GeoPoint MiPoint = new GeoPoint(latitude,longitude);

                            //Marker De Ubicacion Real
                            MiMarker = new Marker(mapView);
                            MiMarker.setPosition(MiPoint);
                            MiMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
                            MiMarker.setTitle("Mi Ubicacion");
                            MiMarker.setSnippet("estas aquí");
                            mapView.getOverlays().add(MiMarker);
                            mapController.setCenter(MiPoint);
                            mapController.setZoom(18);


                            TextView distanceTextView = findViewById(R.id.MostrarLocacion);
                            double casaKamLatitude = -33.50052522139168;
                            double casaKamLongitude =  -70.74533854232784;

                            //Linea Ubicacion Real y Point
                            GeoPoint  casaKamPoint1 = new GeoPoint(casaKamLatitude, casaKamLongitude);
                            double distance = CalcularDistancia.CalcularDistancia(casaKamPoint1,MiPoint);
                            distanceTextView.setText("Distancia entre la empresa y usted: " +
                                    String.format("%.2f", distance) + " km");
                            polyline.addPoint(casaKamPoint1);
                            polyline.addPoint(MiPoint);
                            polyline.setColor(0xFF0000FF); // Color de la línea (azul en formato ARGB)
                            polyline.setWidth(5);

                        }
                    }
                });
    }
}
