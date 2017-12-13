package org.sandec.firebase.wisata_semarang.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by wakhyudi
 */

public class Wisata {

    @SerializedName("id_wisata")
    private String idWisata;

    @SerializedName("nama_wisata")
    private String namaWisata;

    @SerializedName("gambar_wisata")
    private String gambarWisata;

    @SerializedName("deskripsi_wisata")
    private String deskripsiWisata;

    @SerializedName("alamat_wisata")
    private String alamatWisata;

    @SerializedName("latitude_wisata")
    private String latitudeWisata;

    @SerializedName("longitude_wisata")
    private String longitudeWisata;

    public String getIdWisata() {
        return idWisata;
    }

    public String getNamaWisata() {
        return namaWisata;
    }

    public String getGambarWisata() {
        return gambarWisata;
    }

    public String getDeskripsiWisata() {
        return deskripsiWisata;
    }

    public String getAlamatWisata() {
        return alamatWisata;
    }

    public String getLatitudeWisata() {
        return latitudeWisata;
    }

    public String getLongitudeWisata() {
        return longitudeWisata;
    }
}
