package com.example.newlastproject.customer;

import android.content.Context;

import com.example.newlastproject.async.AskParam;
import com.example.newlastproject.async.CommonAsk;
import com.example.newlastproject.async.CommonMethod;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {
    CommonAsk service ;
    Context context;
    InputStream in;
    Gson gson = new Gson();
    public ArrayList<CustomerVO> list(){
        service = new CommonAsk("list.cu");
        in = CommonMethod.excuteAsk(service);
        ArrayList<CustomerVO> list
                = gson.fromJson(new InputStreamReader(in), new TypeToken< List<CustomerVO> >(){}.getType());

        return  list;
    }

    public void delete(String id){
        service = new CommonAsk("delete.cu");
        service.params.add(new AskParam("id", id));
        CommonMethod.excuteAsk(service);
    }

    public void edit(CustomerVO vo){
        service = new CommonAsk("update.cu");
        String str = gson.toJson(vo);
        service.params.add(new AskParam("vo", str));
        CommonMethod.excuteAsk(service);
    }

}
