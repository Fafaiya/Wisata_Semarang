package org.sandec.firebase.wisata_semarang.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.sandec.firebase.wisata_semarang.R;
import org.sandec.firebase.wisata_semarang.helper.ServiceClient;
import org.sandec.firebase.wisata_semarang.helper.ServiceGenerator;
import org.sandec.firebase.wisata_semarang.model.ListWisata;
import org.sandec.firebase.wisata_semarang.model.Wisata;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PetaWisataActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peta_wisata);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    List<Wisata> listWisata = new ArrayList<>();
    ProgressDialog pd;

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(true);

        //membuat objek service nya
        ServiceClient service = ServiceGenerator.createService(ServiceClient.class);
        //memilih jenis service yang dibutuhkan
        Call<ListWisata> getListWisata = service.getWisata("semarang");

        //efek loading mengambil data
        pd = new ProgressDialog(PetaWisataActivity.this);
        pd.setMessage("load data from server");
        pd.show();

        //mengirim request dan menerima respon dari server
        getListWisata.enqueue(new Callback<ListWisata>() {
            @Override
            public void onResponse(Call<ListWisata> call, Response<ListWisata> response) {
                //menghilangkan efek loading
                pd.dismiss();
                //menyimpan respon server ke listWisata
                listWisata = response.body().getListWisataSemarang();
                //menampilkan semua marker
                for (int i = 0; i < listWisata.size(); i++) {
                    //parsing data dari string menjadi Double
                    Double latWisata = Double.valueOf(listWisata.get(i).getLatitudeWisata());
                    Double lngWisata = Double.valueOf(listWisata.get(i).getLongitudeWisata());

                    LatLng lokasiwisata = new LatLng(latWisata, lngWisata);
                    mMap.addMarker(new MarkerOptions().position(lokasiwisata)
                            .title(listWisata.get(i).getNamaWisata())
                    );
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(lokasiwisata, 13));
                }

            }

            @Override
            public void onFailure(Call<ListWisata> call, Throwable t) {
                Toast.makeText(PetaWisataActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}
