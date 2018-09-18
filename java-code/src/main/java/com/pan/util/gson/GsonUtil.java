package com.pan.util.gson;

import com.google.gson.Gson;
import com.pan.util.gson.entity.SignalEvent;

/**
 * Created by pan on 2017/12/9.
 */
public class GsonUtil {

    public static void main(String[] args) {

      /*  String json = "{" +
                "trainData:{" +
                    "train_type_no: 03A02,"+
                    "train_type_name: 车型03A02,"+
                    "train_name: 列车0341,"+
                    "train_no: 3330,"+
                    "line_name: 3号线,"+
                    "line_id:3"+
                "}," +
                "c_p1_11_0:1505" +
                "}";*/

        String json = " {"+
            "trainData: {"+
            "train_type_no:03A02,"+
                    "train_type_name:车型03A02,"+
                    "train_name:列车0341,"+
                    "train_no:3330,"+
                    "line_name:3号线,"+
                    "line_id:3"+
        "},"+
            "c_p1_11_0:1505," +"timestamp:9131138331591929993"+"}";
        SignalEvent signalEvent = new Gson().fromJson(json, SignalEvent.class);
        System.out.print(signalEvent.getTrainData());
        System.out.print(signalEvent.getC_p1_11_0());
    }

}


