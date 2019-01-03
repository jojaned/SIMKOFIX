package id.sch.sman3kediri.www.simkofix;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class beranda extends AppCompatActivity {
private TextView nisn,nama,kelas,masuk,pulang;
private EditText etTanggalAwal,etTanggalAkhir;
private String getExtra_nisn;
private Button keuangan, pelanggaran, informasi, nilai, buttonlihatper;
private ListView listViewAbsen;
//private static String URL_POST_PPRESENSI="http://192.168.43.218/SIMKO/viewPresensi.php?nisn=";
private static String URL_POST_PPRESENSI="http://192.168.42.242/SIMKO/viewPresensi.php?nisn=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beranda);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setTitle("   SIMKO SMAN 3 KEDIRI");

        //Deklarasi widget
        nisn = findViewById(R.id.NISN_txt);
        nama = findViewById(R.id.nama_txt);
        kelas = findViewById(R.id.kelas_txt);
        masuk = findViewById(R.id.tanggalmasuk);
        pulang = findViewById(R.id.tanggalpulang);
        buttonlihatper = findViewById(R.id.buttonLihatPertanggal);
        etTanggalAkhir = findViewById(R.id.etTglAkhir);
        etTanggalAwal = findViewById(R.id.etTglAwal);
        listViewAbsen = findViewById(R.id.listViewTgl);

        //getIntent dari phpJason
        nisn.setText(getIntent().getStringExtra("nisn"));
        nama.setText(getIntent().getStringExtra("nama"));
        kelas.setText(getIntent().getStringExtra("kelas"));
        masuk.setText(getIntent().getStringExtra("masuk"));
        pulang.setText(getIntent().getStringExtra("pulang"));

        //deklarasi button

        buttonlihatper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nisnp = getExtra_nisn;
                String etTglAwal = etTanggalAwal.getText().toString().trim();
                String etTglAkhir = etTanggalAkhir.getText().toString().trim();


                LihatAbsenPertanggal(nisnp, etTglAwal,etTglAkhir);


            }
        });

        keuangan = findViewById(R.id.button2);
        keuangan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(beranda.this, keuangan.class);

                intent.putExtra("nisn", getIntent().getStringExtra("nisn"));
                intent.putExtra("nama", getIntent().getStringExtra("nama"));
                intent.putExtra("kelas", getIntent().getStringExtra("kelas"));
                intent.putExtra("masuk", getIntent().getStringExtra("masuk"));
                intent.putExtra("pulang", getIntent().getStringExtra("pulang"));

                startActivity(intent);
            }
        });
        pelanggaran = findViewById(R.id.button3);
        pelanggaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(beranda.this, pelanggaran.class);

                intent.putExtra("nisn", getIntent().getStringExtra("nisn"));
                intent.putExtra("nama", getIntent().getStringExtra("nama"));
                intent.putExtra("kelas", getIntent().getStringExtra("kelas"));
                intent.putExtra("masuk", getIntent().getStringExtra("masuk"));
                intent.putExtra("pulang", getIntent().getStringExtra("pulang"));

                startActivity(intent);
            }
        });
        nilai = findViewById(R.id.button4);
        nilai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(beranda.this, nilai.class);

                intent.putExtra("nisn", getIntent().getStringExtra("nisn"));
                intent.putExtra("nama", getIntent().getStringExtra("nama"));
                intent.putExtra("kelas", getIntent().getStringExtra("kelas"));
                intent.putExtra("masuk", getIntent().getStringExtra("masuk"));
                intent.putExtra("pulang", getIntent().getStringExtra("pulang"));

                startActivity(intent);
            }
        });
        informasi = findViewById(R.id.button5);
        informasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(beranda.this, informasi.class);

                intent.putExtra("nisn", getIntent().getStringExtra("nisn"));
                intent.putExtra("nama", getIntent().getStringExtra("nama"));
                intent.putExtra("kelas", getIntent().getStringExtra("kelas"));
                intent.putExtra("masuk", getIntent().getStringExtra("masuk"));
                intent.putExtra("pulang", getIntent().getStringExtra("pulang"));

                startActivity(intent);

            }
        });

        }

    private void LihatAbsenPertanggal(final String nisnp, final String etTglAwal, final String etTglAkhir) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_POST_PPRESENSI,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                })
        {
            @Override
            protected Map< String, String> getParams() throws AuthFailureError {
                Map< String, String> params = new HashMap<>();
                params.put("nisn", nisnp);
                params.put("tgl_awal", etTglAwal);
                params.put("tgl_akhir", etTglAkhir);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}


