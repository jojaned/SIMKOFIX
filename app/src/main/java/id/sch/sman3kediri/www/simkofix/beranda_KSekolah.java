package id.sch.sman3kediri.www.simkofix;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;



import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.mikephil.charting.utils.ViewPortHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class MyValueFormatter implements IValueFormatter{

    private DecimalFormat mFormat;

    public MyValueFormatter(){
        mFormat = new DecimalFormat("###,###,##0.0");
    }

    @Override
    public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
        return mFormat.format(value) + "  %";
    }
}

public class beranda_KSekolah extends AppCompatActivity {
private TextView nisn,nama,kelas,masuk,pulang;
private String getExtra_nisn;

private Button keuangan, pelanggaran, informasi, nilai;
//private static String URL_POST_PPRESENSI="http://192.168.43.218/SIMKO/viewPresensi.php?nisn=";
private static String URL_POST_PPRESENSI="http://117.102.78.43/android_register_login/viewPresensiKS.php";

private static String TAG = "beranda_KSekolah";

//ambil array jsonObject
private float jfmasuk, jfabsen;
private float[] yData = {.04F,.96F};
private String[] xData = {"% Absen","% Masuk"};
/*public float[] yData = {};
public String[] xData = {};*/
PieChart pieChart;
private String JSON_STRING;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beranda_ks);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setTitle("   SIMKO SMAN 3 KEDIRI");

        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray("result");

            JSONObject jo = result.getJSONObject(1);
            String jdatang = jo.getString("jumlahp");

            int idatang = Integer.parseInt(jdatang);
            int itotal = 900;
            float fabsen = (idatang-itotal)/itotal;
            float fmasuk = itotal/itotal;

            final float [] yData = {fabsen, fmasuk};
        } catch (JSONException e) {
            e.printStackTrace();
        }



        //final String [] xData = {};


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

        Log.d(TAG, "onCreate: starting to create chart");

        pieChart = findViewById(R.id.idPieChart);


        pieChart.setRotationEnabled(true);
        pieChart.setUsePercentValues(true);
        pieChart.setHoleRadius(25f);
        pieChart.setTransparentCircleAlpha(0);
        pieChart.setCenterText("Pie Chart");
        pieChart.setCenterTextSize(10);

        addDataset();
        
        //test Membuat script utk menampilkan isi
        pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                Log.d(TAG, "onValueSelected: " + e.toString());
                Log.d(TAG, "onValueSelected: " + h.toString());

                int pos1 = e.toString().indexOf("(sum) :");
                String persentase = e.toString().substring(pos1 +7);

                for(int i = 0; i<yData.length; i++){
                    if(yData[i] == Float.parseFloat(persentase)){
                        pos1 = i;
                        break;
                    }
                }

                String presensi = xData[pos1 + 1];
                Toast.makeText(beranda_KSekolah.this, " Persentase dari " + presensi + "/n" + "berjumlah : " + persentase + "%", Toast.LENGTH_LONG);
            }

            @Override
            public void onNothingSelected() {

            }
        });


    }

    private void addDataset() {
        Log.d(TAG, "Mulai addDataSet");

        //angka masukkan
        ArrayList<PieEntry> yEntrys = new ArrayList<>();

        //nama atau label
        ArrayList<String> xEntrys = new ArrayList<>();


        for(int i = 0; i<yData.length; i++){

            yEntrys.add(new PieEntry(yData[i], i));
        }

        for(int i =1; i<xData.length; i++){
            xEntrys.add(xData[i]);

        }


        //create data set
        PieDataSet pieDataSet = new PieDataSet(yEntrys, "Persentase Presensi");
        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueTextSize(12);

        //add warna ke SLICE
        ArrayList<Integer> warna = new ArrayList<>();
        warna.add(Color.GRAY);
        warna.add(Color.BLUE);
        warna.add(Color.RED);
        warna.add(Color.GREEN);
        warna.add(Color.CYAN);
        warna.add(Color.YELLOW);
        warna.add(Color.MAGENTA);

        pieDataSet.setColors(warna);

        //add LEGEND
        Legend legend = pieChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setPosition(Legend.LegendPosition.LEFT_OF_CHART);

        //create pie data object
        PieData pieData = new PieData(pieDataSet);
        pieData.setValueFormatter(new MyValueFormatter());
        pieChart.setData(pieData);
        pieChart.invalidate();

    }
}


