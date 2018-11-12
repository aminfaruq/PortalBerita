package id.co.aminfaruq.portalberita.activities;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.co.aminfaruq.portalberita.R;
import id.co.aminfaruq.portalberita.adapter.model.BeritaModel;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_OBJ = "obj";//isi kunci keynya dengan "obj"
    @BindView(R.id.myToolbar)
    Toolbar myToolbar;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.tvTglTerbit)
    TextView tvTglTerbit;
    @BindView(R.id.tvPenulis)
    TextView tvPenulis;
    @BindView(R.id.wvContentBerita)
    TextView wvContentBerita;
    @BindView(R.id.imgFotoBerita)
    ImageView imgFotoBerita;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        //membuat toolbar
        Toolbar toolbar = findViewById(R.id.myToolbar);
        setSupportActionBar(toolbar);

        /*Memasukkan data dari intent ke dalam object Berita model */
        BeritaModel beritaModel = getIntent().getParcelableExtra(EXTRA_OBJ);

        //memasukkan data yang ada di dalam berita model ke dalam variabel baru
        String judul = beritaModel.getJudul();
        String penulis = beritaModel.getPenulis();
        String tglPosting = beritaModel.getTanggalPosting();
        String contentBerita = beritaModel.getIsiBerita();
        String gambar = beritaModel.getGambar();

        //menampilkan judul berita kedalam title action bar
        getSupportActionBar().setTitle(judul);

        //menampilkan penulis dan tanggal posting ke layar
        tvPenulis.setText(penulis);
        tvTglTerbit.setText(tglPosting);

        //menampilkan gambar
        Glide.with(this).load(gambar).into(imgFotoBerita);

        //mengisi isi berita
        wvContentBerita.setText(contentBerita);

    }
}
