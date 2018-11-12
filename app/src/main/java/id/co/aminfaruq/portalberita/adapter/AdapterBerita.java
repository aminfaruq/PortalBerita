package id.co.aminfaruq.portalberita.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.co.aminfaruq.portalberita.R;
import id.co.aminfaruq.portalberita.activities.DetailActivity;
import id.co.aminfaruq.portalberita.adapter.model.BeritaModel;
import id.co.aminfaruq.portalberita.network.ConfigRetrofit;
import id.co.aminfaruq.portalberita.responseapi.BeritaItem;

public class AdapterBerita extends RecyclerView.Adapter<AdapterBerita.ViewHolder> {
    //TODO 4 membuat variable untuk menampung data yang di butuhkan
    Context context;
    List<BeritaItem> berita;



    public AdapterBerita(Context context, List<BeritaItem> berita) {
        this.context = context;
        this.berita = berita;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.berita_item, viewGroup, false);//lakukan butterknife lalu pindahkan hasil butter knifenya ke method view holder

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        //Menampilkan data ke layar
        viewHolder.tvJudulBerita.setText(berita.get(i).getJudulBerita());
        viewHolder.tvPenulis.setText(berita.get(i).getPenulis());
        viewHolder.tvTglTerbit.setText(berita.get(i).getTanggalPosting());

        //Mengambil alamat gambar
        final String urlGambarBerita = ConfigRetrofit.API_URL + "images/" + berita.get(i).getFoto();

        //menampilkan gambar ke layar dengan glide
        Glide.with(context).load(urlGambarBerita).into(viewHolder.ivGambarBerita);

        //membuat onclik untuk dapat mengklik item
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //membuat object berita model untuk dapat mengisi data ke dalam object berita model
                BeritaModel beritaModel = new BeritaModel();

                //mengisi data ke dalam object
                beritaModel.setJudul(berita.get(i).getJudulBerita());
                beritaModel.setPenulis(berita.get(i).getPenulis());
                beritaModel.setTanggalPosting(berita.get(i).getTanggalPosting());
                beritaModel.setIsiBerita(berita.get(i).getIsiBerita());
                beritaModel.setGambar(urlGambarBerita);

                //berpindah halaman menggunakan intent
                Intent intent = new Intent(context, DetailActivity.class);

                //Memasukkan object berita model yang  ada isinya ke dalam put Extra intent untuk di kirim
                intent.putExtra(DetailActivity.EXTRA_OBJ,beritaModel);//klik alt + enter lalu pilih make constrants

                //menjalankan intent
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return berita.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvJudulBerita)
        TextView tvJudulBerita;
        @BindView(R.id.ivGambarBerita)
        ImageView ivGambarBerita;
        @BindView(R.id.tvTglTerbit)
        TextView tvTglTerbit;
        @BindView(R.id.tvPenulis)
        TextView tvPenulis;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
