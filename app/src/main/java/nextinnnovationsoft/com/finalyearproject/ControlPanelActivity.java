package nextinnnovationsoft.com.finalyearproject;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import nextinnnovationsoft.com.finalyearproject.model.Log;

public class ControlPanelActivity extends AppCompatActivity {

    Switch lightOneSwitch , lightTwoSwitch , lightThreeSwitch , humiditySensorSwitch , lightSensorSwitch ;
    Context context = ControlPanelActivity.this;
    DatabaseHelper databaseHelper ;
    Button btnHumiditySensor , btnLightSensor ;
    String currentDate ;
    String currentTime ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control_panel);

        databaseHelper = new DatabaseHelper(context);
        lightOneSwitch = (Switch) findViewById(R.id.light_one_switch);
        lightTwoSwitch = (Switch) findViewById(R.id.light_two_switch);
        lightThreeSwitch = (Switch) findViewById(R.id.fan_switch);
        humiditySensorSwitch = (Switch) findViewById(R.id.humiditySensorSwitch);
        lightSensorSwitch = (Switch) findViewById(R.id.light_sensor_switch);
        btnHumiditySensor = (Button) findViewById(R.id.btnHumiditySensor);
        btnLightSensor = (Button) findViewById(R.id.btnLightSensor);


        Calendar c = Calendar.getInstance();
        System.out.println("Current time => "+c.getTime());

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("HH:mm:ss");

        currentDate = simpleDateFormat.format(c.getTime());
        currentTime = simpleTimeFormat.format(c.getTime());



        if(databaseHelper.getApplianceCurrentStatus(1).equals("ON")){
            lightOneSwitch.setChecked(true);
        }
        else{
            lightOneSwitch.setChecked(false);
        }

        if(databaseHelper.getApplianceCurrentStatus(2).equals("ON")){
            lightTwoSwitch.setChecked(true);
        }
        else{
            lightTwoSwitch.setChecked(false);
        }

        if(databaseHelper.getApplianceCurrentStatus(3).equals("ON")){
            lightThreeSwitch.setChecked(true);
        }
        else{
            lightThreeSwitch.setChecked(false);
        }

        if(databaseHelper.getApplianceCurrentStatus(4).equals("ON")){
            humiditySensorSwitch.setChecked(true);
            btnHumiditySensor.setText("ON");
        }
        else{
            btnHumiditySensor.setText("OFF");
            humiditySensorSwitch.setChecked(false);
        }

        if(databaseHelper.getApplianceCurrentStatus(5).equals("ON")){
            lightSensorSwitch.setChecked(true);
            btnLightSensor.setText("ON");
        }
        else{
            lightSensorSwitch.setChecked(false);
            btnLightSensor.setText("OFF");
        }



        lightOneSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){
                    sendSMS("#a1" );
                    databaseHelper.updateAppilanceStatus(1,"ON");
                    databaseHelper.addLog(new Log("LED 1","ON","ON",currentDate,currentTime));
                }
                else{
                    sendSMS("#a0");
                    databaseHelper.updateAppilanceStatus(1,"OFF");
                    databaseHelper.addLog(new Log("LED 1","OFF","OFF",currentDate,currentTime));
                }

            }
        });

        lightTwoSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    sendSMS("#d1");
                    databaseHelper.updateAppilanceStatus(2,"ON");
                    databaseHelper.addLog(new Log("LED 2","ON","ON",currentDate,currentTime));
                }
                else{
                    sendSMS("#d0");
                    databaseHelper.updateAppilanceStatus(2,"OFF");
                    databaseHelper.addLog(new Log("LED 2","OFF","OFF",currentDate,currentTime));
                }
            }
        });

        lightThreeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    sendSMS("#e1");
                    databaseHelper.updateAppilanceStatus(3,"ON");
                    databaseHelper.addLog(new Log("LED 3","ON","ON",currentDate,currentTime));
                }
                else{
                    sendSMS("#e0");
                    databaseHelper.updateAppilanceStatus(3,"OFF");
                    databaseHelper.addLog(new Log("LED 3","OFF","OFF",currentDate,currentTime));
                }
            }
        });

        humiditySensorSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    sendSMS("#h1");
                    databaseHelper.updateAppilanceStatus(4,"ON");
                    databaseHelper.addLog(new Log("Humidity Sensor","ON","ON",currentDate,currentTime));
                    btnHumiditySensor.setText("ON");
                }
                else{
                    sendSMS("#h0");
                    databaseHelper.updateAppilanceStatus(4,"OFF");
                    databaseHelper.addLog(new Log("Humidity Sensor","OFF","OFF",currentDate,currentTime));
                    btnHumiditySensor.setText("OFF");
                }
            }
        });
        lightSensorSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    sendSMS("#l1");
                    databaseHelper.updateAppilanceStatus(5,"ON");
                    databaseHelper.addLog(new Log("Light Sensor","ON","ON",currentDate,currentTime));
                    btnLightSensor.setText("ON");
                }
                else{
                    sendSMS("#l0");
                    databaseHelper.updateAppilanceStatus(5,"OFF");
                    databaseHelper.addLog(new Log("Light Sensor","OFF","OFF",currentDate,currentTime));
                    btnLightSensor.setText("OFF");
                }
            }
        });


    }


    public void sendSMS(  String msg) {
        final ProgressDialog progressDialog = new ProgressDialog(ControlPanelActivity.this);
        progressDialog.setTitle("Sending....");
        progressDialog.setMessage("Command Sending");

        progressDialog.show();
        final String message = msg ;
        try {
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage("+8801636450307", null, message, null, null);
                    Toast.makeText(getApplicationContext(), "Command Send Successfully!",
                            Toast.LENGTH_LONG).show();
                    progressDialog.dismiss();
                }
            }, 2000);


        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(),ex.getMessage().toString(),
                    Toast.LENGTH_LONG).show();
            ex.printStackTrace();
        }


    }
}

