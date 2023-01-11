package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;


public class TampilPD extends AppCompatActivity implements AdapterView.OnItemClickListener{
    private ListView listView;
    private String JSON_STRING;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_pd);
        listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(this);
        getJSON();
    }
    private void showEmployee() {
        JSONObject jsonObject = null;
        ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String,
                String>>();
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result =
                    jsonObject.getJSONArray(Konfigurasi.TAG_JSON_ARRAY_PD);
            for (int i = 0; i < result.length(); i++) {
                JSONObject jo = result.getJSONObject(i);
                String id = jo.getString(Konfigurasi.TAG_ID_PD);
                String name = jo.getString(Konfigurasi.TAG_NAMA_PD);
                HashMap<String, String> employees = new HashMap<>();
                employees.put(Konfigurasi.TAG_ID_PD, id);
                employees.put(Konfigurasi.TAG_NAMA_PD, name);
                list.add(employees);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ListAdapter adapter = new SimpleAdapter(
                TampilPD.this, list, R.layout.list_item,
                new String[]{Konfigurasi.TAG_ID_PD, Konfigurasi.TAG_NAMA_PD},
                new int[]{R.id.id, R.id.name});
        listView.setAdapter(adapter);
    }
    private void getJSON() {
        class GetJSON extends AsyncTask<Void, Void, String> {

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(TampilPD.this, "Mengambil Data", "Mohon Tunggu...", false, false);
            }
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING = s;
                showEmployee();
            }
            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequest(Konfigurasi.URL_GET_ALL_PD);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }
    //buat detile siswa saat di klik
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(TampilPD.this, DetilePD.class);
        HashMap<String, String> map = (HashMap) parent.getItemAtPosition(position);
        String empIdPD = map.get(Konfigurasi.TAG_ID_PD).toString();
        intent.putExtra(Konfigurasi.EMP_ID_PD, empIdPD);
        startActivity(intent);
    }
}