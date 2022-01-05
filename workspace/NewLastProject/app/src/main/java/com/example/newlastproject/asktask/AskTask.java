package com.example.newlastproject.asktask;

import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;

<<<<<<< HEAD
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
=======
import java.io.IOException;
import java.io.InputStream;
>>>>>>> 9b6d72f32d8996b3f771726d73ffd51f54a019ec
import java.util.ArrayList;

public class AskTask extends AsyncTask<String,String,InputStream> {
    private static final String TAG = "common";
    HttpClient httpClient;//접속을 위한객체
    HttpPost httpPost; //url을 담을 객체
    MultipartEntityBuilder builder;//파라메터,파일 등등을 보내기위한 객체
<<<<<<< HEAD
    final String HTTPIP = "http://192.168.0.12";//IP
    final String SVRPATH = "/middle/"; //
    String mapping ;
    private String postUrl ;

    public ArrayList<String> params;
=======
    final String HTTPIP = "http://192.168.0.3";//IP
    final String SVRPATH = "/middle/"; //
    String mapping ;
    private String postUrl ;
    ArrayList<String> params ;

>>>>>>> 9b6d72f32d8996b3f771726d73ffd51f54a019ec

    public AskTask(String mapping) {
        this.mapping = mapping;
    }

<<<<<<< HEAD
    //어싱크테스크를 excute(실행)
=======
>>>>>>> 9b6d72f32d8996b3f771726d73ffd51f54a019ec
    @Override
    protected InputStream doInBackground(String... strings) {
        //반복되는 소스가 어디서부터 어디까지 생기고
        //어떻게하면 줄일수있을까?
        //무조건 InputStream으로 무조건 return을 받음.

        postUrl = HTTPIP + SVRPATH + mapping;
        builder = MultipartEntityBuilder.create();
        builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
        //===========================================

<<<<<<< HEAD
        builder.addTextBody("idx" , params.size()+"" ,
                ContentType.create("Multipart/related" , "UTF-8"));
        for (int i = 0 ; i<params.size() ; i ++){
        builder.addTextBody("param" + (i+1) , params.get(i) ,
                ContentType.create("Multipart/related" , "UTF-8"));
        }
=======
        /*for (int i = 0 ; i<params.size() ; i ++){
        builder.addTextBody("param" + (i+1) , params.get(i) ,
                ContentType.create("Multipart/related" , "UTF-8"));
        }*/
>>>>>>> 9b6d72f32d8996b3f771726d73ffd51f54a019ec
        httpClient = AndroidHttpClient.newInstance("Android");
        //============================================================
        //conn , ps<-// ps( sql ) , ps.setInt , ps.setString
        httpPost = new HttpPost(postUrl);
        httpPost.setEntity(builder.build());//파라메터를 추가할수있는부분.
        InputStream   in = null;
        try {
            in = httpClient.execute(httpPost).getEntity().getContent();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return in;
    }
<<<<<<< HEAD

    //DAO, COMMON 공통으로 사용할 클래스로 이동.
    public String rtnString(InputStream inputStream){
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

=======
>>>>>>> 9b6d72f32d8996b3f771726d73ffd51f54a019ec
}
