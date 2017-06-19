package nextinnnovationsoft.com.finalyearproject;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import nextinnnovationsoft.com.finalyearproject.model.Appliance;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private LinearLayout controlPanelLayout, seeReportLayout, userLogLayout;
    private Intent intent;
    DatabaseHelper databaseHelper ;
    Context context = HomeActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        initializeViews();
        databaseHelper = new DatabaseHelper(context);
        controlPanelLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(HomeActivity.this, ControlPanelActivity.class);
                startActivity(intent);
            }
        });
        seeReportLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(HomeActivity.this, ReportActivity.class);
                startActivity(intent);
            }
        });

        userLogLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(HomeActivity.this, LogActivity.class);
                startActivity(intent);
            }
        });
        int size = databaseHelper.getAllAppliance().size();
       if(size==0){
           databaseHelper.addAppliance(new Appliance("LED 1","OFF"));
           databaseHelper.addAppliance(new Appliance("LED 2","OFF"));
           databaseHelper.addAppliance(new Appliance("LED 3","OFF"));
           databaseHelper.addAppliance(new Appliance("Humidity Sensor","OFF"));
           databaseHelper.addAppliance(new Appliance("Light Sensor","OFF"));
       }


    }

    private void initializeViews() {

        controlPanelLayout = (LinearLayout) findViewById(R.id.layout_control_panel);
        seeReportLayout = (LinearLayout) findViewById(R.id.layout_report);
        userLogLayout = (LinearLayout) findViewById(R.id.layout_user_log);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_control_panel) {
            // Handle the camera action

            intent = new Intent(HomeActivity.this, ControlPanelActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_report) {
            intent = new Intent(HomeActivity.this, ReportActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_user_log) {
            intent = new Intent(HomeActivity.this, LogActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_exit) {
            alertDialog();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void alertDialog() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Closing Activity")
                .setMessage("Are you sure you want to close application")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }

                })
                .setNegativeButton("No", null)
                .show();
    }

}
