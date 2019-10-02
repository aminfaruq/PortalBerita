package id.co.aminfaruq.portalberita;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.co.aminfaruq.portalberita.adapter.AdapterBerita;
import id.co.aminfaruq.portalberita.network.ApiService;
import id.co.aminfaruq.portalberita.network.ConfigRetrofit;
import id.co.aminfaruq.portalberita.responseapi.BeritaItem;
import id.co.aminfaruq.portalberita.responseapi.ResponseBerita;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.rvBerita)
    RecyclerView rvBerita;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        tampilBerita();

    }
//     oke hvuktdbjyvjtfyvbnky

    private void tampilBerita() {
        //buat progress dialog
       final ProgressDialog dialog = ProgressDialog.show(MainActivity.this,"get data berita","loading. . .");

        //TODO Membuat object configretrofit dan api service untuk dapat merequest ke API
        ConfigRetrofit config = new ConfigRetrofit();
        ApiService api = config.service;//klik alt + enter pilih configRetrofit

        // kirim request
        api.getAllBerita().enqueue(new Callback<ResponseBerita>() {
            @Override
            public void onResponse(Call<ResponseBerita> call, Response<ResponseBerita> response) {
                Toast.makeText(MainActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                Log.i("debug sukses",response.message());

                //Cek respon sukses
                if (response.isSuccessful()){
                    //Mematikan progress dialog karena server merespon
                   dialog.dismiss();

                    boolean status = response.body().isStatus();
                    //Apabila response status true
                    if (status){
                        // tampung data body ke dalam variable datasemua
                        ResponseBerita dataSemua = response.body();

                        // Tampung data berita ke dalam variable data berita
                        List<BeritaItem> data_berita = dataSemua.getBerita();

                        //buat object adapter
                        AdapterBerita adapterBerita = new AdapterBerita(MainActivity.this,data_berita);

                        //setting recycle
                        rvBerita.setHasFixedSize(true);
                        //setting style layout recycleview menjadi linear
                        rvBerita.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                        //masukkan adapter ke recycleview
                        rvBerita.setAdapter(adapterBerita);
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBerita> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.i("debug gagal",t.getMessage());
            }
        });
    }
}
