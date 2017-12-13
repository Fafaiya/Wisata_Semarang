package org.sandec.firebase.wisata_semarang.helper;

import org.sandec.firebase.wisata_semarang.model.ListWisata;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by wakhyudi on 11/12/17.
 */

public interface ServiceClient {
    @GET("exec")
    Call<ListWisata> getWisata(@Query("sheet") String namaSheet);
}
