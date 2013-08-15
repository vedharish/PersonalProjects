package com.vedharish.rsstosms;

import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

public class SendSMSActivity extends Activity {
    Button buttonSendButton;
    EditText editTextPhoneNumber;
    EditText editTextMessage;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_sms);
        
        buttonSendButton = (Button) findViewById(R.id.sendButton);
        editTextPhoneNumber = (EditText) findViewById(R.id.txtPhoneNumber);
        editTextMessage = (EditText) findViewById(R.id.txtMessage);
        
        String SENT = "SMS_SENT";
        String DELIVERED = "SMS_DELIVERED";
        
//      final PendingIntent sentPI = PendingIntent.getBroadcast(this, 0, new Intent(SENT), 0);
//	    final PendingIntent deliveredPI = PendingIntent.getBroadcast(this, 0, new Intent(DELIVERED), 0);
	    
	    
        buttonSendButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				String stringPhoneNumber = editTextPhoneNumber.getText().toString();
				String stringMessage = editTextMessage.getText().toString();
				
				if(stringPhoneNumber.length()>0 && stringMessage.length()>0){
					RetrieveURLContent urlContent = new RetrieveURLContent();
				    urlContent.execute(stringMessage, stringPhoneNumber, null, null);
				}else{
					Toast.makeText(getBaseContext(), "Please enter both phone number and message", Toast.LENGTH_SHORT).show();
				}
			}
		});
    }

	public void createToast(String message){
		Toast.makeText(getBaseContext(), message, Toast.LENGTH_LONG).show();
	}
}