package id.sch.sman3kediri.www.simkofix;

import android.app.VoiceInteractor;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

public class informasiAdmin extends AppCompatActivity {


    private Button keuangan, pelanggaran, informasiadmin, nilai, beranda, kirimInfo;
    private EditText etIsiInfo;
    //private static String URL_tambahinfo="http://192.168.43.218/SIMKO/tambahinfoadmin.php";
    private static String URL_tambahinfo="http://117.102.78.43/android_register_login/tambahinfoadmin.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informasi_admin);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setTitle("   SIMKO SMAN 3 KEDIRI");


        beranda = findViewById(R.id.button2);
        beranda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(informasiAdmin.this, beranda.class));

            }
        });
        keuangan = findViewById(R.id.button2);
        keuangan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(informasiAdmin.this, keuangan.class));

            }
        });
        pelanggaran = findViewById(R.id.button3);
        pelanggaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(informasiAdmin.this, pelanggaran.class));

            }
        });
        nilai = findViewById(R.id.button4);
        nilai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(informasiAdmin.this, nilai.class));

            }
        });
        informasiadmin = findViewById(R.id.button5);
        informasiadmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(informasiAdmin.this, informasi.class));

            }
        });

        kirimInfo = findViewById(R.id.btnKirimInfo);
        kirimInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                etIsiInfo = findViewById(R.id.etInfoAdmin);
                String InfoDariAdmin = etIsiInfo.getText().toString().trim();

                SendInfo(InfoDariAdmin);
            }
        });
    }

    private void SendInfo(final String infoDariAdmin) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_tambahinfo,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(informasiAdmin.this, "Sukses Kirim Informasi", Toast.LENGTH_SHORT).show();

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
                params.put("info", infoDariAdmin);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
