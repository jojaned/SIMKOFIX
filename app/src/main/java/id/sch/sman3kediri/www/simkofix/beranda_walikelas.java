package id.sch.sman3kediri.www.simkofix;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class beranda_walikelas extends AppCompatActivity {
    private TextView nisn, nama, kelas, masuk, pulang;
    private EditText etTanggalAwal, etTanggalAkhir;

    private Button keuangan, pelanggaran, informasi, nilai;
    private ListView listViewAbsen;
    //private static String URL_POST_PPRESENSI="http://192.168.43.218/SIMKO/viewPresensi.php?nisn=";
    private static String URL_VIEW_SIA = "http://117.102.78.43/android_register_login/viewAbsen4WK.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beranda_wk);
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
        //buttonlihatper = findViewById(R.id.buttonLihatPertanggal);
        etTanggalAkhir = findViewById(R.id.etTglAkhir);
        etTanggalAwal = findViewById(R.id.etTglAwal);
        listViewAbsen = findViewById(R.id.listViewTgl);

        //getIntent dari phpJason
        nisn.setText(getIntent().getStringExtra("nisn"));
        nama.setText(getIntent().getStringExtra("nama"));
        kelas.setText(getIntent().getStringExtra("kelas"));
        masuk.setText(getIntent().getStringExtra("masuk"));
        pulang.setText(getIntent().getStringExtra("pulang"));

        //deklarasi button untuk di hide
        keuangan = findViewById(R.id.button2);
        pelanggaran = findViewById(R.id.button3);
        nilai = findViewById(R.id.button4);
        informasi = findViewById(R.id.button5);
        //hide
        keuangan.setVisibility(View.GONE);
        pelanggaran.setVisibility(View.GONE);
        nilai.setVisibility(View.GONE);
        informasi.setVisibility(View.GONE);




    }

}