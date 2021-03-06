package org.sandec.firebase.wisata_semarang.helper;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by wakhyudi on 11/12/17.
 */

public class ServiceGenerator {
    private static final String BASE_URL = "https://script.google.com/macros/s/AKfycby-jbgwKHEvnMbXo3yaW86EulRpsP59AAY2_p5CEDsjfIHqtXMK/";
    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory
                    (GsonConverterFactory.create())
            .build();

    public static <S> S createService(Class<S> serviceClass) {
        return retrofit.create(serviceClass);
    }


}
