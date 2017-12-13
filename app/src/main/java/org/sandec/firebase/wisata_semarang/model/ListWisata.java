package org.sandec.firebase.wisata_semarang.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by wakhyudi
 */

public class ListWisata {

    @SerializedName("semarang")
    private List<Wisata> listWisataSemarang;

    public List<Wisata> getListWisataSemarang() {
        return listWisataSemarang;
    }
}
