package com.example.newlastproject.async;

import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.widget.Toast;

import com.google.gson.Gson;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class AskTest2 extends AsyncTask<String,String,InputStream> {
    private static final String TAG = "common";
    HttpClient httpClient;//접속을 위한객체
    HttpPost httpPost; //url을 담을 객체

    MultipartEntityBuilder builder;//파라메터,파일 등등을 보내기위한 객체
    final String HTTPIP = "http://192.168.0.12";//IP
    final String SVRPATH = "/mid/"; //
    String mapping ;
    private String postUrl ;//

    public ArrayList<String> params ;

    public AskTest2(String mapping ) {
        this.mapping = mapping;
        params =new ArrayList<>();
    }
    //어싱크테스크를 excute(실행) ↓
    @Override
    protected InputStream doInBackground(String... strings) {
        postUrl = HTTPIP + SVRPATH + mapping ;

        builder = MultipartEntityBuilder.create();
        builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
        builder.addTextBody("idx" , params.size()+"" ,
                ContentType.create("Multipart/related" , "UTF-8"));
        for(int i = 0; i < params.size() ; i ++){
            builder.addTextBody("param"+ (i+1) , params.get(i) ,
                    ContentType.create("Multipart/related" , "UTF-8"));
        }

        httpClient = AndroidHttpClient.newInstance("Android");
        httpPost = new HttpPost(postUrl);
        httpPost.setEntity(builder.build());//파라메터를 추가할수있는부분.
        InputStream   in = null;
        try {
            in = httpClient.execute(httpPost).getEntity().getContent();


        } catch (IOException e) {
            e.printStackTrace();
        }
        //rtnString(in);
        return in;
    }
    //DAO , COMMON 공통으로 사용할 클래스로 이동.
    public String rtnString(InputStream inputStream)  {
        try{


        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuilder = new StringBuilder();
        String line = null;
        while( (line = reader.readLine()) != null  ){
            stringBuilder.append( line + "\n");
        }

        return stringBuilder.toString();
        }catch (IOException e){

        }
        return "";
    }


}
