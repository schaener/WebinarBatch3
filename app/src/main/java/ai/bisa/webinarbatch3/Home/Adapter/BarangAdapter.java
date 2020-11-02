package ai.bisa.webinarbatch3.Home.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;

import java.util.List;

import ai.bisa.webinarbatch3.Home.Model.BarangModel;
import ai.bisa.webinarbatch3.R;
import ai.bisa.webinarbatch3.function.Script_Function;


public class BarangAdapter extends RecyclerView.Adapter<BarangAdapter.ViewHolder> {
    private List<BarangModel> listBarangModel;
    protected Cursor cursor;
    private Context context;

    Script_Function function = new Script_Function();

    public BarangAdapter(List<BarangModel> listBarangModel, Context context) {
        this.listBarangModel = listBarangModel;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.barang_item
                , parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final BarangModel model = listBarangModel.get(position);

        final Intent intent = ((Activity) context).getIntent();

        holder.nama_barang.setText(model.getNama_barang());
        holder.stok.setText("Stok : " + model.getStok());
        holder.harga.setText(function.formatRupiah.format(model.getHarga()));
        Glide.with(context)
                .load(function.path + model.getGambar())
                .into(holder.gambar_barang);
    }

    @Override
    public int getItemCount() {
        return listBarangModel.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView harga, nama_barang, stok;
        ImageView gambar_barang;

        public ViewHolder(View itemView) {
            super(itemView);
            harga = itemView.findViewById(R.id.TVharga);
            nama_barang = itemView.findViewById(R.id.TVbarang);
            stok = itemView.findViewById(R.id.TVstok);

            gambar_barang = itemView.findViewById(R.id.imgbarang);
        }
    }

}
