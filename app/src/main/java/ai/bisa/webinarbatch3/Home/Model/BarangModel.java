package ai.bisa.webinarbatch3.Home.Model;

import com.google.gson.annotations.SerializedName;

public class BarangModel {
    @SerializedName("nama_barang")
    public String nama_barang;
    @SerializedName("barang_id")
    public String barang_id;
    @SerializedName("spesifikasi")
    public String spesifikasi;
    @SerializedName("stok")
    public String stok;
    @SerializedName("gambar")
    public String gambar;
    @SerializedName("harga")
    public int harga;

    public BarangModel(String nama_barang, String barang_id, String spesifikasi, String stok, String gambar, int harga) {
        this.nama_barang = nama_barang;
        this.barang_id = barang_id;
        this.spesifikasi = spesifikasi;
        this.stok = stok;
        this.gambar = gambar;
        this.harga = harga;
    }

    public String getNama_barang() {
        return nama_barang;
    }

    public void setNama_barang(String nama_barang) {
        this.nama_barang = nama_barang;
    }

    public String getBarang_id() {
        return barang_id;
    }

    public void setBarang_id(String barang_id) {
        this.barang_id = barang_id;
    }

    public String getSpesifikasi() {
        return spesifikasi;
    }

    public void setSpesifikasi(String spesifikasi) {
        this.spesifikasi = spesifikasi;
    }

    public String getStok() {
        return stok;
    }

    public void setStok(String stok) {
        this.stok = stok;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }
}
