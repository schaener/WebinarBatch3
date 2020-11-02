package ai.bisa.webinarbatch3.Home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ai.bisa.webinarbatch3.Home.Adapter.BarangAdapter;
import ai.bisa.webinarbatch3.Home.Model.BarangModel;
import ai.bisa.webinarbatch3.Home.Model.ListBarangModel;
import ai.bisa.webinarbatch3.R;
import ai.bisa.webinarbatch3.Server.ApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeActivity extends AppCompatActivity {
    List<BarangModel> listBarangM;
    BarangAdapter barangAdapter;
    private RecyclerView recyclerViewBarang;
    ConnectivityManager conMgr;
    public static final String BASE_URL = "https://tokoelektronikokta.000webhostapp.com/android/Tampil_barang.php/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        recyclerViewBarang =  findViewById(R.id.recycler);
        conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        {
            if (conMgr.getActiveNetworkInfo() != null
                    && conMgr.getActiveNetworkInfo().isAvailable()
                    && conMgr.getActiveNetworkInfo().isConnected()) {
            displayBarang(null);
            } else {
              Toast.makeText(getApplicationContext(), "No Internet Connection",
                        Toast.LENGTH_SHORT).show();
            }
        }
        displayBarang(null);
    }
    public void displayBarang(String search){

        recyclerViewBarang.setVisibility(View.VISIBLE);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //Defining retrofit api service
        ApiService service = retrofit.create(ApiService.class);
        //creating an api call
        Call<ListBarangModel> call = service.getDataBarang("1",search,"tampil_barang");
        //making the call
        call.enqueue(new Callback<ListBarangModel>() {
            @Override
            public void onResponse(Call<ListBarangModel> call, Response<ListBarangModel> response) {
                try {
                    ListBarangModel listbarangModel = response.body();
                    if (listbarangModel.getStatus() == 1) {

                        listBarangM = listbarangModel.getListBarangM();
                        barangAdapter = new BarangAdapter(listBarangM,
                                HomeActivity.this);
                        recyclerViewBarang.setLayoutManager(new GridLayoutManager(
                                HomeActivity.this
                                , 2));
                        recyclerViewBarang.setItemAnimator(new DefaultItemAnimator());
                        recyclerViewBarang.setAdapter(barangAdapter);

                    } else {
                        //  Toast.makeText(Home.this, listbarangModel.getMessage(),Toast.LENGTH_SHORT).show();
                        listBarangM.clear();

                        recyclerViewBarang.setVisibility(View.GONE);

                    }
                }
                catch (Exception ex){
                    ex.printStackTrace();
                   Toast.makeText(getApplicationContext(),"No Connection"
                            ,Toast.LENGTH_SHORT).show();
                }

            }


            @Override
            public void onFailure(Call<ListBarangModel> call, Throwable t) {
                //  Toast.makeText(getApplicationContext(), "No Connection", Toast.LENGTH_LONG).show();

            }
        });

    }

}