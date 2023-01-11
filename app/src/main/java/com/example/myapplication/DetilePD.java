package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;


public class DetilePD extends AppCompatActivity implements View.OnClickListener {
//dekalarasi variabel

    private EditText editnis;
    private EditText editnama;
    private Spinner editjk;
    private EditText editalamat;

    private Button buttonUpdate;
    private Button buttonDelete;
    private Button buttonHome;

    private String nis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detile_pd);


        Intent intent = getIntent();

        nis = intent.getStringExtra(Konfigurasi.EMP_ID_PD);

        editnis = (EditText) findViewById(R.id.editnis);
        editnama = (EditText) findViewById(R.id.editnama);
        editjk = (Spinner) findViewById(R.id.editjk);
        editalamat = (EditText) findViewById(R.id.editalamat);

        buttonUpdate = (Button) findViewById(R.id.buttonUpdate);
        buttonDelete = (Button) findViewById(R.id.buttonDelete);
        buttonHome = (Button) findViewById(R.id.buttonHome);

        buttonUpdate.setOnClickListener(this);
        buttonDelete.setOnClickListener(this);
        buttonHome.setOnClickListener(this);

        editnis.setText(nis);

        getEmployee();
    }

    private void getEmployee() {
        class GetEmployee extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(DetilePD.this, "Fetching...", "Wait...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                showEmployee(s);
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(Konfigurasi.URL_GET_EMP_PD, nis);
                return s;
            }
        }
        GetEmployee ge = new GetEmployee();
        ge.execute();
    }

    private void showEmployee(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(Konfigurasi.TAG_JSON_ARRAY_PD);
            JSONObject c = result.getJSONObject(0);
            String name = c.getString(Konfigurasi.TAG_NAMA_PD);
            String desg = c.getString(Konfigurasi.TAG_KELAMIN_PD);
            String sal = c.getString(Konfigurasi.TAG_ALAMAT_PD);

            editnama.setText(name);
            editjk.setSelection(getPosisi(editjk, desg));
            editalamat.setText(sal);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private int getPosisi(Spinner editjk, String desg) {
        for (int i=0; i < editjk.getCount(); i++){
            if (desg.equals(editjk.getItemAtPosition(i))){
                return i;
            }
        }
        return 0;
    }

    private void updateEmployee() {
        //final String nis = editnis.getText().toString().trim();
        final String name = editnama.getText().toString().trim();
        final String jk = editjk.getSelectedItem().toString().trim();
        final String alamat = editalamat.getText().toString().trim();

        class UpdateEmployee extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(DetilePD.this, "Updating...", "Wait...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(DetilePD.this, s, Toast.LENGTH_LONG).show();
                startActivity(new Intent(DetilePD.this, TampilPD.class));
            }

            @Override
            protected String doInBackground(Void... params) {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put(Konfigurasi.KEY_EMP_ID_PD, nis);
                hashMap.put(Konfigurasi.KEY_EMP_NAMA_PD, name);
                hashMap.put(Konfigurasi.KEY_EMP_KELAMIN_PD, jk);
                hashMap.put(Konfigurasi.KEY_EMP_ALAMAT_PD, alamat);


                RequestHandler rh = new RequestHandler();

                String s = rh.sendPostRequest(Konfigurasi.URL_UPDATE_EMP_PD, hashMap);

                return s;
            }
        }

        UpdateEmployee ue = new UpdateEmployee();
        ue.execute();
    }

    private void deleteEmployee() {
        class DeleteEmployee extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(DetilePD.this, "Updating...", "Tunggu...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(DetilePD.this, s, Toast.LENGTH_LONG).show();
                startActivity(new Intent(DetilePD.this, TampilPD.class));
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(Konfigurasi.URL_DELETE_EMP_PD, nis);
                return s;
            }
        }

        DeleteEmployee de = new DeleteEmployee();
        de.execute();
    }

    private void confirmDeleteEmployee() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Apakah Kamu Yakin Ingin Menghapus Data ini?");

        alertDialogBuilder.setPositiveButton("Ya",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        deleteEmployee();
                        startActivity(new Intent(DetilePD.this, TampilPD.class));
                    }
                });

        alertDialogBuilder.setNegativeButton("Tidak",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();


    }

    @Override
    public void onClick(View v) {
        if (v == buttonUpdate) {
            updateEmployee();
        }

        if (v == buttonDelete) {
            confirmDeleteEmployee();
        }
        if (v == buttonHome) {
            startActivity(new Intent(DetilePD.this,Menu.class));
        }

    }
}