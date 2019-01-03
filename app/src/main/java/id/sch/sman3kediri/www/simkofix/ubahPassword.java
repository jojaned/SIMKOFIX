package id.sch.sman3kediri.www.simkofix;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class ubahPassword extends AppCompatActivity {
private EditText nisn,passwordlama,passwordBaru,konfirmasipassbaru;
private Button btn_ubahpass;
private ProgressBar loading;
/*private static String URL_LOGIN="http://117.102.78.43/android_register_login/login.php";*/
//private static String URL_LOGIN="http://192.168.43.218/SIMKO/login.php";
private static String URL_UBAHPASS="http://117.102.78.43/android_register_login/ubahPassword.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubahpassword);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setTitle("   SIMKO SMAN 3 KEDIRI");

        loading = findViewById(R.id.loading);

     nisn = findViewById(R.id.nisn);
     passwordlama =findViewById(R.id.passwordLama);
     passwordBaru = findViewById(R.id.passwordBaru);
     konfirmasipassbaru = findViewById(R.id.konfirmpasswordBaru);

     btn_ubahpass = findViewById(R.id.btnubah);

     //tombol ubahPass

        btn_ubahpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String mnisn = nisn.getText().toString().trim();
                String mpass = passwordlama.getText().toString().trim();
                String mpassB = passwordBaru.getText().toString().trim();
                String mkpassB = konfirmasipassbaru.getText().toString().trim();

                if(mnisn.isEmpty() || mpass.isEmpty() || mpassB.isEmpty() || mkpassB.isEmpty()){
                    Toast.makeText(ubahPassword.this, "Periksa apakah ada kolom belum terisi", Toast.LENGTH_SHORT).show();

                }else if(mpassB.equals(mkpassB) ){
                    changepass(mnisn, mpass, mkpassB);
                }else{
                    Toast.makeText(ubahPassword.this, "Periksa apakah kolom password baru sesuai dengan konfirmasinya ", Toast.LENGTH_SHORT).show();
                }

                //changepass(mnisn,mkpassB);
            }
        });
    }

    private void changepass( final String username, final String passwordLama, final String passwordbaru) {

        loading.setVisibility(View.VISIBLE);
        btn_ubahpass.setVisibility(View.GONE);


        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_UBAHPASS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("ubah");
                            for(int i=0; i<jsonArray.length(); i++){

                                String success = jsonArray.getString(Integer.parseInt("success"));
                                if(success.equals("1")){
                                    Toast.makeText(ubahPassword.this,"Ubah Password Sukses! \n Silakan Sign In", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(ubahPassword.this, MainActivity.class);
                                    startActivity(intent);

                                }

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            loading.setVisibility(View.GONE);
                            Toast.makeText(ubahPassword.this, "Error JSON"+e.toString(), Toast.LENGTH_SHORT).show();
                        }



                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        loading.setVisibility(View.GONE);
                        btn_ubahpass.setVisibility(View.VISIBLE);
                        Toast.makeText(ubahPassword.this, "Error JSON"+volleyError.toString(), Toast.LENGTH_SHORT).show();

                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("username", username);
                params.put("oldpassword", passwordLama);
                params.put("newpassword", passwordbaru);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }


    private void validasi(){



    }

}




