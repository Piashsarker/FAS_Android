package nextinnnovationsoft.com.finalyearproject;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import nextinnnovationsoft.com.finalyearproject.model.Report;

public class ReportActivity extends AppCompatActivity {

    TextView temprature , lighIntensity , totalAppliance ;
    DatabaseHelper databaseHelper ;
    Context context =ReportActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        databaseHelper = new DatabaseHelper(context);
        temprature = (TextView) findViewById(R.id.temprature);
        lighIntensity = (TextView) findViewById(R.id.lightIntensity);
        totalAppliance = (TextView) findViewById(R.id.total_appliance);
        setValue();

    }


    public void setValue(){
        Report report = databaseHelper.getLastReport();
        if(report!=null){
            temprature.setText(String.valueOf(report.getTemprature())+" C");
            lighIntensity.setText(String.valueOf(report.getLightIntensity()));
            totalAppliance.setText(report.getTotalAppliance());
        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.refresh_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.refresh:
                sendSMS("###");
                setValue();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    public void sendSMS(  String msg) {
        final ProgressDialog progressDialog = new ProgressDialog(ReportActivity.this);
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
