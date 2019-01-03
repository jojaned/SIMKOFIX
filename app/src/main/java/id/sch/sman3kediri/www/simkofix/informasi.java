package id.sch.sman3kediri.www.simkofix;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class informasi extends AppCompatActivity {

    private Button keuangan, pelanggaran, nilai, beranda, refresh;
    private TextView nama;


    private String JSON_STRING;
    private ListView listView;
    private static String URL_VIDA="http://117.102.78.43/android_register_login/viewInfoDariAdmin.php";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informasi);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setTitle("   SIMKO SMAN 3 KEDIRI");

/*        nama = findViewById(R.id.nama_txt);
        nama.setText(getIntent().getStringExtra("nama"));*/


        //LISTVIEW
        listView = findViewById(R.id.ListView);
        getJSON();


        refresh = findViewById(R.id.btnRefreshInfo);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getJSON();

            }
        });


        beranda = findViewById(R.id.button);
        beranda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(informasi.this, beranda.class);

                intent.putExtra("nisn", getIntent().getStringExtra("nisn"));
                intent.putExtra("nama", getIntent().getStringExtra("nama"));
                intent.putExtra("kelas", getIntent().getStringExtra("kelas"));
                intent.putExtra("masuk", getIntent().getStringExtra("masuk"));
                intent.putExtra("pulang", getIntent().getStringExtra("pulang"));

                startActivity(intent);

            }
        });
        keuangan = findViewById(R.id.button2);
        keuangan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(informasi.this, keuangan.class);

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
                Intent intent = new Intent(informasi.this, pelanggaran.class);

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
                Intent intent = new Intent(informasi.this, nilai.class);

                intent.putExtra("nisn", getIntent().getStringExtra("nisn"));
                intent.putExtra("nama", getIntent().getStringExtra("nama"));
                intent.putExtra("kelas", getIntent().getStringExtra("kelas"));
                intent.putExtra("masuk", getIntent().getStringExtra("masuk"));
                intent.putExtra("pulang", getIntent().getStringExtra("pulang"));

                startActivity(intent);

            }
        });
    }

    private void getJSON() {

        class GetJSON extends AsyncTask<Void, Void, String>{

            @Override
            protected void onPostExecute(String s) {
                JSON_STRING = s;
                showInfo();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequest(URL_VIDA);
                return s;
            }
        }

        GetJSON gjes = new GetJSON();
        gjes.execute();
    }

    private void showInfo() {
        JSONObject jsonObject = null;
        ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String, String>>();

        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray("result");

            for(int i = 0; i<result.length();i++){

                JSONObject jo = result.getJSONObject(i);
                String date = jo.getString("date");
                String info = jo.getString("info");

                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("date",date);
                hashMap.put("info",info);
                list.add(hashMap);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        ListAdapter adapter = new SimpleAdapter(informasi.this, list, R.layout.list_iteminfo,
                new String[]{"date","info"},
                new int[]{R.id.tvTanggal, R.id.tvInfo});

        listView.setAdapter(adapter);
    }
}
