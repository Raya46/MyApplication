package com.example.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.HashMap;

public class TambahPD extends AppCompatActivity {
    //deklarasi variabel
    private EditText editTextName, editTextnis;
    private Spinner editTextKelamin;
    private EditText editTextAlamat;
    private Button buttonAdd;
    private Button buttonView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_pd);

        editTextnis = (EditText) findViewById(R.id.editTextnis);
        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextKelamin = (Spinner) findViewById(R.id.editTextKelamin);
        editTextAlamat = (EditText) findViewById(R.id.editTextAlamat);
        buttonAdd = (Button) findViewById(R.id.buttonAdd);
        buttonView = (Button) findViewById(R.id.buttonView);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String nis = editTextnis.getText().toString().trim();
                final String name = editTextName.getText().toString().trim();
                final String desg = editTextKelamin.getSelectedItem().toString().trim();
                final String sal = editTextAlamat.getText().toString().trim();
                class AddEmployee extends AsyncTask<Void, Void, String> {
                    ProgressDialog loading;
                    @Override
                    protected void onPreExecute() {
                        super.onPreExecute();
                        loading = ProgressDialog.show(TambahPD.this,
                                "Menambahkan...", "Tunggu...", false, false);
                    }
                    @Override
                    protected void onPostExecute(String s) {
                        super.onPostExecute(s);
                        loading.dismiss();
                        Toast.makeText(TambahPD.this, s,
                                Toast.LENGTH_LONG).show();
                    }
                    @Override
                    protected String doInBackground(Void... v) {
                        HashMap<String, String> params = new HashMap<>();
                        params.put(Konfigurasi.KEY_EMP_ID_PD, nis);
                        params.put(Konfigurasi.KEY_EMP_NAMA_PD, name);
                        params.put(Konfigurasi.KEY_EMP_KELAMIN_PD, desg);
                        params.put(Konfigurasi.KEY_EMP_ALAMAT_PD, sal);
                        RequestHandler rh = new RequestHandler();
                        String res = rh.sendPostRequest(Konfigurasi.URL_ADD_PD,
                                params);
                        return res;
                    }
                }
                AddEmployee ae = new AddEmployee();
                ae.execute();
            }
        });
        buttonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TambahPD.this,TampilPD.class));
            }
        });
    }

}