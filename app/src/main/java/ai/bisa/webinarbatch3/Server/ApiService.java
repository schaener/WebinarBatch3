package ai.bisa.webinarbatch3.Server;

/**
 * Created by Asus on 16/01/2018.
 */


import ai.bisa.webinarbatch3.Home.Model.ListBarangModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    /* @GET("kategori/tampilKategori.php/")
     Call<ListKategoriModel> getAllDataKategori();
  @GET("makanan/GaleryMakanan.php/")
    Call<ListMakananModel> getAllDataMakananByKategori_id(@Query("kategori_id") String kategori_id);*/
    @GET("front_UI.php")
    Call<ListBarangModel> getDataBarang(@Query("kategori_id") String kategori_id,
                                        @Query("search") String search, @Query("apicall") String apicall);

}

