package com.example.ex00_servletconn;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Gson gson = new Gson();
        UserDTO dto = new UserDTO(10,"a","c");
        ArrayList<String> list = new ArrayList<>();
        list.add(gson.toJson(dto));

        AskTest2 askTest2 = new AskTest2("dto.and" , list);
        try {
           InputStream in = askTest2.execute().get();
            UserDTO fromDto = gson.fromJson(new InputStreamReader(in), UserDTO.class);
            String aa = new String("d");
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}