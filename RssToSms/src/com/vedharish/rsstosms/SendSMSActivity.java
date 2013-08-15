package com.vedharish.rsstosms;

import android.os.AsyncTask;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.app.Activity;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

public class SendSMSActivity extends Activity {
    Button buttonSendButton;
    EditText editTextPhoneNumber;
    EditText editTextMessage;
    
    String SENT = "SMS_SENT";
	String DELIVERED = "SMS_DELIVERED";
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_sms);
        
        buttonSendButton = (Button) findViewById(R.id.sendButton);
        editTextPhoneNumber = (EditText) findViewById(R.id.txtPhoneNumber);
        editTextMessage = (EditText) findViewById(R.id.txtMessage);
        
        buttonSendButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				String stringPhoneNumber = editTextPhoneNumber.getText().toString();
				String stringMessage = editTextMessage.getText().toString();
				
				if(stringPhoneNumber.length()>0 && stringMessage.length()>0){
					Object[] inputObjectArray = {stringPhoneNumber, stringMessage};
					SendSMS sendSMS = new SendSMS();
					sendSMS.execute(inputObjectArray);
				}else{
					Toast.makeText(getBaseContext(), "Please enter both phone number and message", Toast.LENGTH_SHORT).show();
				}
			}
		});
        
        registerReceiver(new sentBroadcastReceiver(), new IntentFilter(SENT));
        registerReceiver(new deliveredBroadcastReceiver(), new IntentFilter(DELIVERED));
    }
	public void createToast(String message){
		Toast.makeText(getBaseContext(), message, Toast.LENGTH_LONG).show();
	}
	
	
	public class SendSMS extends AsyncTask<Object, Integer, Void> {
		ProgressDialog progressDialog = new ProgressDialog(SendSMSActivity.this);
		
		@Override
		protected void onPreExecute() {
			progressDialog.setMessage("Sending Message...");
			progressDialog.setIndeterminate(false);
			progressDialog.setMax(100);
			progressDialog.setProgress(0);
			progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			progressDialog.setCancelable(false);
			progressDialog.show();
			super.onPreExecute();
		}
		@Override
		protected Void doInBackground(Object... inputObjectArray) {
			PendingIntent sentIntent = PendingIntent.getBroadcast(SendSMSActivity.this, 0, new Intent(SENT), 0);
			publishProgress(1/4*100);
			PendingIntent deliveredIntent = PendingIntent.getBroadcast(SendSMSActivity.this, 0, new Intent(DELIVERED), 0);
			publishProgress(2/4*100);
			SmsManager smsManager = SmsManager.getDefault();
			publishProgress(3/4*100);
			smsManager.sendTextMessage((String) inputObjectArray[0], null, (String) inputObjectArray[1], sentIntent, deliveredIntent);
			publishProgress(4/4*100);
			return null;
		}
		@Override
		protected void onProgressUpdate(Integer... values) {
			super.onProgressUpdate(values);
			progressDialog.setProgress(values[0]);
		}
		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			progressDialog.dismiss();
		}
	}
	public class sentBroadcastReceiver extends BroadcastReceiver {
		@Override
		public void onReceive(Context arg0, Intent arg1) {
			switch(getResultCode()){
				case Activity.RESULT_OK:
					SendSMSActivity.this.createToast("SMS sent");
					break;
				case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                	SendSMSActivity.this.createToast("Generic failure");
                    break;
                case SmsManager.RESULT_ERROR_NO_SERVICE:
                	SendSMSActivity.this.createToast("No service");
                    break;
                case SmsManager.RESULT_ERROR_NULL_PDU:
                	SendSMSActivity.this.createToast("Null PDU");
                    break;
                case SmsManager.RESULT_ERROR_RADIO_OFF:
                	SendSMSActivity.this.createToast("Radio off");
                    break;
			}
		}
	}
	public class deliveredBroadcastReceiver extends BroadcastReceiver {
		@Override
		public void onReceive(Context arg0, Intent arg1) {
			switch (getResultCode()) {
				case Activity.RESULT_OK:
		          	SendSMSActivity.this.createToast("SMS delivered");
		            break;
		        case Activity.RESULT_CANCELED:
		          	SendSMSActivity.this.createToast("SMS not delivered");
		            break;
		      }
		}
	}
}