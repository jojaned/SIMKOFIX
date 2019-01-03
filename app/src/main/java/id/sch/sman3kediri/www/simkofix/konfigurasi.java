package id.sch.sman3kediri.www.simkofix;

public class konfigurasi {

    //pengalamatan skrip php view
    //private static String URL_GET_PPRESENSI="http://192.168.43.218/SIMKO/viewPresensi.php?nisn=";
    private static String URL_GET_KEUANGAN_SISWA="http://192.168.43.218/SIMKO/viewKuanganSiswa.php";
    private static String URL_GET_DPEMBAYARANSPP="http://192.168.43.218/SIMKO/viewPembayaran_tb_spp_bayar_d.php";
    private static String URL_GET_MPEMBAYARANSPP="http://192.168.43.218/SIMKO/viewPembayaran_tb_spp_bayar_m.php";
    /*private static String URL_GET_PPRESENSI="http://192.168.43.218/SIMKO/.php";*/

    //pengiriman permintaan ke skrip php
    public static final String KEY_TGLPILIH = "tglpilih";
    /*public static final String KEY_TGLAKHIR = "tglakhir";*/

    //JSON tags
    public static final String TAG_JSON_ARRAY = "result";
    public static final String TAG_NISN = "nisn";
    public static final String TAG_NAMA = "nama";
    public static final String TAG_TGLPILIH = "tglpilih";
    /*public static final String TAG_TGLAKHIR = "tglakhir";*/

    //sn
    public static final String NISN = "nisn";
}
