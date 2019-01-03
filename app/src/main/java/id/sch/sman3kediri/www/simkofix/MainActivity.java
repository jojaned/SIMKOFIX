package id.sch.sman3kediri.www.simkofix;

import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {
private EditText nisn,password;
private Button btn_login,btn_gantipass;
private ProgressBar loading;
/*private static String URL_LOGIN="http://117.102.78.43/android_register_login/login.php";*/
//private static String URL_LOGIN="http://192.168.43.218/SIMKO/login.php";
private static String URL_LOGIN="http://117.102.78.43/android_register_login/login.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setTitle("   SIMKO SMAN 3 KEDIRI");

        loading = findViewById(R.id.loading);

     nisn = findViewById(R.id.nisn);
     password =findViewById(R.id.password);
     btn_login = findViewById(R.id.btnlogin);
     btn_gantipass = findViewById(R.id.btnGantiPass);

     //tombol login

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String mnisn = nisn.getText().toString().trim();
                String mpass = password.getText().toString().trim();

                signin(mnisn,mpass);
            }
        });

        btn_gantipass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ubahPassword.class);
                startActivity(intent);
            }
        });


    }

    private void signin(final String username, final String password) {

        loading.setVisibility(View.VISIBLE);
        btn_login.setVisibility(View.GONE);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("login");

                            if(success.equals("99")){
                                for(int i=0; i<jsonArray.length(); i++){

                                    JSONObject object = jsonArray.getJSONObject(i);

                                    String nama = object.getString("xnama").trim();
                                    String kelas = object.getString("xkelas").trim();


                                    Toast.makeText(MainActivity.this,"Login Sukses! \n Namamu : "+nama+"", Toast.LENGTH_SHORT).show();

                                    loading.setVisibility(View.GONE);

                                    Intent intent = new Intent(MainActivity.this, informasiAdmin.class);

                                    intent.putExtra("nama", nama);
                                    intent.putExtra("kelas", kelas);

                                    startActivity(intent);

                                }


                            }else if (success.equals("1")) {

                                for(int i=0; i<jsonArray.length(); i++){

                                    JSONObject object = jsonArray.getJSONObject(i);

                                    String ynisn = object.getString("xnisn").trim();
                                    String nama = object.getString("xnama").trim();
                                    String kelas = object.getString("xkelas").trim();
                                    String masuk = object.getString("xmasuk").trim();
                                    String pulang = object.getString("xpulang").trim();


                                    Toast.makeText(MainActivity.this,"Login Sukses! \n Namamu : "+nama+"\n NIS Anda : "+ynisn, Toast.LENGTH_SHORT).show();

                                    loading.setVisibility(View.GONE);

                                        Intent intent = new Intent(MainActivity.this, beranda.class);

                                        intent.putExtra("nisn", ynisn);
                                        intent.putExtra("nama", nama);
                                        intent.putExtra("kelas", kelas);
                                        intent.putExtra("masuk", masuk);
                                        intent.putExtra("pulang", pulang);

                                        startActivity(intent);

                                }
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            loading.setVisibility(View.GONE);
                            btn_login.setVisibility(View.VISIBLE);
                            Toast.makeText(MainActivity.this, "Error JSON"+e.toString(), Toast.LENGTH_SHORT).show();

                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        loading.setVisibility(View.GONE);
                        btn_login.setVisibility(View.VISIBLE);
                        Toast.makeText(MainActivity.this, "Error JSON"+volleyError.toString(), Toast.LENGTH_SHORT).show();

                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("username", username);
                params.put("password", password);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }


    private void validasi(){



    }

}




