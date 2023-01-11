package com.example.myapplication;

public class Konfigurasi {
    // IP DIGANTI MENJADI IP SERVER API DISIMPAN
   ////////////////  MODUL SISWA/PD  ////////////////////////////////////////////////////////
    public static final String URL_ADD_PD="http://192.168.1.9/api_sekolah/pd/tambah_pd.php";
    public static final String URL_GET_ALL_PD = "http://192.168.1.9/api_sekolah/pd/tampil_pd.php";
    public static final String URL_GET_EMP_PD = "http://192.168.1.9/api_sekolah/pd/detail_pd.php?nis=";
    public static final String URL_UPDATE_EMP_PD = "http://192.168.1.9/api_sekolah/pd/update_pd.php";
    public static final String URL_DELETE_EMP_PD = "http://192.168.1.9/api_sekolah/pd/hapus_pd.php?nis=";
    //Dibawah ini merupakan script atau perintah untuk mengirim permintaan kedalam Skrip PHP
    public static final String KEY_EMP_ID_PD = "nis";
    public static final String KEY_EMP_NAMA_PD = "namasiswa";
    public static final String KEY_EMP_KELAMIN_PD = "jk";
    public static final String KEY_EMP_ALAMAT_PD = "alamat";
    //JSON Tags
    public static final String TAG_JSON_ARRAY_PD="result";
    public static final String TAG_ID_PD = "nis";
    public static final String TAG_NAMA_PD = "namasiswa";
    public static final String TAG_KELAMIN_PD = "jk";
    public static final String TAG_ALAMAT_PD = "alamat";

    public static final String EMP_ID_PD = "emp_id_pd";

    ////////////////  MODUL GURU  ////////////////////////////////////////////////////////
    public static final String URL_ADD_GURU=
            "http://192.168.1.9/api_sekolah/guru/tambah_guru.php";
    public static final String URL_GET_ALL_GURU =
            "http://192.168.1.9/api_sekolah/guru/tampil_guru.php";
    public static final String URL_GET_EMP_GURU =
            "http://192.168.1.9/api_sekolah/guru/detail_guru.php?kdguru=";
    public static final String URL_UPDATE_EMP_GURU =
            "http://192.168.1.9/api_sekolah/guru/update_guru.php";
    public static final String URL_DELETE_EMP_GURU =
            "http://192.168.1.9/api_sekolah/guru/hapus_guru.php?kdguru=";
    //Dibawah ini merupakan script atau perintah untuk mengirim permintaan kedalam Skrip PHP
    public static final String KEY_EMP_ID_GURU = "kdguru";
    public static final String KEY_EMP_NIP_GURU = "nip";
    public static final String KEY_EMP_NAMA_GURU = "namaguru";
    public static final String KEY_EMP_KELAMIN_GURU = "jk";
    public static final String KEY_EMP_ALAMAT_GURU = "alamat";
    //JSON Tags
    public static final String TAG_JSON_ARRAY_GURU="result";
    public static final String TAG_ID_GURU = "kdguru";
    public static final String TAG_NIP_GURU = "nip";
    public static final String TAG_NAMA_GURU = "namaguru";
    public static final String TAG_KELAMIN_GURU = "jk";
    public static final String TAG_ALAMAT_GURU = "alamat";
    //ID
    //emp merupakan singkatan dari Employee
    public static final String EMP_ID_GURU = "emp_id_guru";


}
