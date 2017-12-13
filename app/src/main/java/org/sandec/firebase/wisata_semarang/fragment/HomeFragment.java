package org.sandec.firebase.wisata_semarang.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.sandec.firebase.wisata_semarang.R;
import org.sandec.firebase.wisata_semarang.adapter.WisataAdapter;
import org.sandec.firebase.wisata_semarang.helper.ServiceClient;
import org.sandec.firebase.wisata_semarang.helper.ServiceGenerator;
import org.sandec.firebase.wisata_semarang.model.ListWisata;
import org.sandec.firebase.wisata_semarang.model.Wisata;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    //deklarasi
    RecyclerView rvHome;
    List<Wisata>listWisata = new ArrayList<>();
    ProgressDialog pd;
    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        //menghubungkan logic dengan kulitnya
        rvHome = (RecyclerView)view.findViewById(R.id.rv_home);
        //menentukan bentuk recyclerview
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        //memasukan settingan bentuk ke rvHome
        rvHome.setLayoutManager(llm);

        //membuat objek service nya
        ServiceClient service = ServiceGenerator.createService(ServiceClient.class);
        //memilih jenis service yang dibutuhkan
        Call<ListWisata> getListWisata = service.getWisata("semarang");

        //efek loading mengambil data
        pd = new ProgressDialog(getActivity());
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
                //memasukan listWisata ke dalam adapter
                WisataAdapter adapter = new WisataAdapter(getActivity(),listWisata);
                //setting adapter di rvHome sesuai adapter yg terbentuk
                rvHome.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ListWisata> call, Throwable t) {
                Toast.makeText(getActivity(), ""+t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
        return view;
    }

}
