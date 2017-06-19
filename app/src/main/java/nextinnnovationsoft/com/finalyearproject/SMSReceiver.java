package nextinnnovationsoft.com.finalyearproject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

import nextinnnovationsoft.com.finalyearproject.model.Report;

/**
 * Created by PT on 5/28/2017.
 */

public class SMSReceiver extends BroadcastReceiver {
    String senderNumber="" ;
    String msg = "";
    DatabaseHelper databaseHelper ;
    @Override
    public void onReceive(Context context, Intent intent) {

        databaseHelper = new DatabaseHelper(context);
        Bundle bundle = intent.getExtras();
        SmsMessage[] smsm = null;
        String sms_str ="";
        if (bundle != null)
        {
            // Get the SMS message
            Object[] pdus = (Object[]) bundle.get("pdus");
            smsm = new SmsMessage[pdus.length];
            for (int i=0; i<smsm.length; i++){
                smsm[i] = SmsMessage.createFromPdu((byte[])pdus[i]);
                senderNumber += smsm[i].getOriginatingAddress();
                msg+= smsm[i].getMessageBody().toString();
                msg+= "\r\n";
            }

        }

        Log.v("ReceiveSMS",msg.toString());

        if(senderNumber.equals("+8801636450307")){
            abortBroadcast();

            String[] msgPiece = msg.split("_");
            int temprature = Integer.parseInt(msgPiece[0]);
            int lighIntesity = Integer.parseInt(msgPiece[1]);
            String totalAppliance= msgPiece[2];
            databaseHelper.addReport(new Report(temprature,lighIntesity,totalAppliance));
        }
    }
}
