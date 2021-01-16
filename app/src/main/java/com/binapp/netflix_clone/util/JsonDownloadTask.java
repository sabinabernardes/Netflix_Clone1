package com.binapp.netflix_clone.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.binapp.netflix_clone.model.Category;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class JsonDownloadTask extends AsyncTask<String,Void,List<Category>> {


   ProgressDialog dialog;
    private Context context;

    public JsonDownloadTask(Context context) {

        this.context = context;
    }


    //main-thread
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        dialog=ProgressDialog.show(context,"Carregando","",true);
    }

    //thread - background
    @Override
    protected List<Category> doInBackground(String... params) {
        String url = params[0];

        try {
            URL requestUrl = new URL(url);

            HttpsURLConnection urlConnection = (HttpsURLConnection) requestUrl.openConnection();
            urlConnection.setReadTimeout(2000);
            urlConnection.setConnectTimeout(2000);

            int responseCode = urlConnection.getResponseCode();
            if(responseCode>400){
               throw new IOException("Error na comunicação do servidor");
            }
            InputStream inStream = urlConnection.getInputStream();

            BufferedInputStream in = new BufferedInputStream(urlConnection.getInputStream());

            String jsonAsString =toString(in);
            List<Category> categories = getCategories(new JSONObject(jsonAsString));
            in.close();
            return categories;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<Category> getCategories(JSONObject json) throws JSONException {
        List<Category> categories = new ArrayList<>();
        JSONArray categoryArray = json.getJSONArray("category");
        for (int i = 0; i <categoryArray.length(); i++) {
        //categoryArray


        }
        return categories;
    }

    //main-tread
    @Override
    protected void onPostExecute(List<Category> categories) {
        super.onPostExecute(categories);
        dialog.dismiss();
        //listerner
    }
    private String toString(InputStream is) throws IOException {
        byte[] bytes = new byte[1024];
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int lidos;
        while ((lidos = is.read(bytes))>0){
            baos.write(bytes, 0, lidos);
        }
        return new String (baos.toByteArray());
    }
}
