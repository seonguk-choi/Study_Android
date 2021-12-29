package com.example.ex00_servletconn;

import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class AskTask extends AsyncTask<String,String,String> {
    private static final String TAG = "common";
    HttpClient httpClient;//접속을 위한객체
    HttpPost httpPost; //url을 담을 객체
    HttpResponse httpResponse;//Middle <->And //미사용
    HttpEntity httpEntity; //속성을 정의함
    MultipartEntityBuilder builder;//파라메터,파일 등등을 보내기위한 객체
    final String HTTPIP = "http://192.168.0.03";//IP
    final String SVRPATH = "/mid/"; //
    private String postUrl ;

    @Override
    protected String doInBackground(String... strings) {
        postUrl = HTTPIP + SVRPATH + "list.and";
        builder = MultipartEntityBuilder.create();
        builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
        try{
        ArrayList<UserDTO> list = new ArrayList<>();
        list.add(new UserDTO(1,"a","a"));
        list.add(new UserDTO(2,"b","b"));
        list.add(new UserDTO(3,"c","c"));
        list.add(new UserDTO(4,"d","d"));
        list.add(new UserDTO(5,"e","e"));
        Gson gson = new Gson();
        String data = gson.toJson(list);
        // builder.addTextBody("key" , "valueAndroid" , ContentType.create("Multipart/related" , "UTF-8"));
        builder.addTextBody("dto", data, ContentType.create("Multipart/related" , "UTF-8"));
        httpClient = AndroidHttpClient.newInstance("Android");
        httpPost = new HttpPost(postUrl);
        httpPost.setEntity(builder.build());//파라메터를 추가할수있는부분.
        InputStream in = null;
            try {
                in = httpClient.execute(httpPost).getEntity().getContent();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            if(in != null){
                ArrayList<UserDTO> revList
                        = gson.fromJson(new InputStreamReader(in),
                        new TypeToken<List<UserDTO>>() {}.getType());
                for (UserDTO dto : revList ){
                    Log.d(TAG, "testConn: succ" + dto.getUser_id());
                    Log.d(TAG, "testConn: succ" + dto.getUser_msg());
                    Log.d(TAG, "testConn: succ" + dto.getRefid());
                }
                //String data = rtnStr(in);
                //Fragment2.data = data;
                Log.d(TAG, "testConn: succ" + data);
            }else{
                Log.d(TAG, "testConn: fail");
            }
        }catch (Exception e){
             e.printStackTrace();
        }finally {
            if(httpEntity != null) httpEntity =null;
            if(httpResponse != null) httpResponse =null;
            if(httpPost != null) httpPost =null;
            if(httpClient != null) httpClient =null;

    }
        return null;
    }
}
