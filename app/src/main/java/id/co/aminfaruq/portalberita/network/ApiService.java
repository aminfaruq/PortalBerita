package id.co.aminfaruq.portalberita.network;

import id.co.aminfaruq.portalberita.responseapi.ResponseBerita;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    //TODO 3 merequest data dan mengambil data berita
    @GET("tampil_berita.php")
    Call<ResponseBerita> getAllBerita();
}
