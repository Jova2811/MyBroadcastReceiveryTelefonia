package com.example.mybroadcastreceiver.Receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.example.mybroadcastreceiver.MainActivity;

public class MyReceiverLlamadas extends BroadcastReceiver {

    private static final String TAG = "Tell";
    public static  boolean flag = false;
    MainActivity mainActivity;
    @Override
    public void onReceive(Context context, Intent intent) {

        mainActivity = new MainActivity();
        TelephonyManager telephony =
                (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
        telephony.listen(new PhoneStateListener(){
            @Override
            public void onCallStateChanged(int state, String incomingNumber) {
                super.onCallStateChanged(state, incomingNumber);
                if (state == TelephonyManager.CALL_STATE_RINGING){
                    if(flag==false){
                        Log.d(TAG, "onCallStateChanged: " + incomingNumber);
                        mainActivity.enviarMensajeLlamada(incomingNumber);

                        flag=true;
                    }else{
                        flag = false;
                    }
                }
            }

        },PhoneStateListener.LISTEN_CALL_STATE);


    }
}
