package id.sch.sman3kediri.www.simkofix;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class pelanggaran extends AppCompatActivity {

    private TextView nisn,nama,kelas,masuk,pulang;
    private Button keuangan, beranda, informasi, nilai;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pelanggaran);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setTitle("   SIMKO SMAN 3 KEDIRI");


        nisn = findViewById(R.id.NISN_txt);
        nama = findViewById(R.id.nama_txt);
        kelas = findViewById(R.id.kelas_txt);
        masuk = findViewById(R.id.tanggalmasuk);
        pulang = findViewById(R.id.tanggalpulang);

        nisn.setText(getIntent().getStringExtra("nisn"));
        nama.setText(getIntent().getStringExtra("nama"));
        kelas.setText(getIntent().getStringExtra("kelas"));



        keuangan = findViewById(R.id.button2);
        keuangan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(pelanggaran.this, keuangan.class);

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
                Intent intent = new Intent(pelanggaran.this, nilai.class);

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
                Intent intent = new Intent(pelanggaran.this, informasi.class);

                intent.putExtra("nisn", getIntent().getStringExtra("nisn"));
                intent.putExtra("nama", getIntent().getStringExtra("nama"));
                intent.putExtra("kelas", getIntent().getStringExtra("kelas"));
                intent.putExtra("masuk", getIntent().getStringExtra("masuk"));
                intent.putExtra("pulang", getIntent().getStringExtra("pulang"));

                startActivity(intent);

            }
        });
        beranda = findViewById(R.id.button);
        beranda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(pelanggaran.this, beranda.class);

                intent.putExtra("nisn", getIntent().getStringExtra("nisn"));
                intent.putExtra("nama", getIntent().getStringExtra("nama"));
                intent.putExtra("kelas", getIntent().getStringExtra("kelas"));
                intent.putExtra("masuk", getIntent().getStringExtra("masuk"));
                intent.putExtra("pulang", getIntent().getStringExtra("pulang"));

                startActivity(intent);

            }
        });
    }
}
