package com.vedharish.rsstosms;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.telephony.SmsManager;

public class SendSMS extends AsyncTask<Object, Void, Void>{
	@Override
	protected Void doInBackground(Object... inputArgumentsArray) {
		String SENT = "SMS_SENT";
        String DELIVERED = "SMS_DELIVERED";
        
//        final SendSMSActivity sendSMSActivity = new SendSMSActivity();
//        sendSMSActivity.registerReceiver(new BroadcastReceiver(){
//            @Override
//            public void onReceive(Context arg0, Intent arg1) {
//                switch (getResultCode()) {
//                    case Activity.RESULT_OK:
//                    	sendSMSActivity.createToast("SMS sent");
//                        break;
//                    case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
//                    	sendSMSActivity.createToast("Generic failure");
//                        break;
//                    case SmsManager.RESULT_ERROR_NO_SERVICE:
//                    	sendSMSActivity.createToast("No service");
//                        break;
//                    case SmsManager.RESULT_ERROR_NULL_PDU:
//                    	sendSMSActivity.createToast("Null PDU");
//                        break;
//                    case SmsManager.RESULT_ERROR_RADIO_OFF:
//                    	sendSMSActivity.createToast("Radio off");
//                        break;
//                }
//            }
//
//        }, new IntentFilter(SENT));
//	 
//        sendSMSActivity.registerReceiver(new BroadcastReceiver(){
//            @Override
//            public void onReceive(Context arg0, Intent arg1) {
//                switch (getResultCode()) {
//                    case Activity.RESULT_OK:
//                    	sendSMSActivity.createToast("SMS delivered");
//                        break;
//                    case Activity.RESULT_CANCELED:
//                    	sendSMSActivity.createToast("SMS not delivered");
//                        break;                        
//                }
//            }
//        }, new IntentFilter(DELIVERED));
        
//        PendingIntent sentIntent = PendingIntent.getService(context, requestCode, intent, flags);
        
		SmsManager smsManager = SmsManager.getDefault();
//        smsManager.sendTextMessage((String) inputArgumentsArray[0], null, (String) inputArgumentsArray[1], (PendingIntent) inputArgumentsArray[2], (PendingIntent) inputArgumentsArray[3]);
        smsManager.sendTextMessage((String) inputArgumentsArray[0], null, (String) inputArgumentsArray[1], null, null);

        return null;
	}
}