package nextinnnovationsoft.com.finalyearproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import nextinnnovationsoft.com.finalyearproject.model.Appliance;
import nextinnnovationsoft.com.finalyearproject.model.Log;
import nextinnnovationsoft.com.finalyearproject.model.Report;

/**
 * Created by PT on 5/27/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME = "fas_database";
    private static final int DATABASE_VERSION = 24 ;

    private static final String TABLE_APPLIANCE = "applianceTable";
    private static final String TABLE_REPORT = "reportTable";
    private static final String TABLE_LOG = "logTable";

    private static final String COLUMN_APPLIANCE_ID = "appliance_id";
    private static final String COLUMN_APPLIANCE_NAME = "appliance_name";
    private static final String COLUMN_APPLIANCE_CURRENT_STATUS = "current_status";

    private static final String COLUMN_REPORT_ID = "report_id";
    private static final String COLUMN_REPORT_TEMPRATURE= "temprature";
    private static final String COLUMN_REPORT_LIGHT_INTENSITY = "light_intensity";
    private static final String COLUMN_REPORT_TOTAL_APPLIANCE = "total_appliance";

    private static final String COLUMN_LOG_ID = "log_id";
    private static final String COLUMN_LOG_UPDATE_REQUEST = "update_request";

    private static final String COLUMN_LOG_STATUS = "log_status";
    private static final String COLUMN_DATE = "date";
    private static final String COLUMN_TIME = "time";

    private static final String CREATE_TABLE_APPLIANCE = "CREATE TABLE " + TABLE_APPLIANCE + " ("
            + COLUMN_APPLIANCE_ID + " INTEGER PRIMARY KEY,"
            + COLUMN_APPLIANCE_NAME + " TEXT,"
            + COLUMN_APPLIANCE_CURRENT_STATUS + " TEXT"
            + ")";
    private static final String CREATE_TABLE_REPORT = "CREATE TABLE " + TABLE_REPORT + " ("
            + COLUMN_REPORT_ID + " INTEGER PRIMARY KEY,"
            + COLUMN_REPORT_TEMPRATURE + " INTEGER,"
            + COLUMN_REPORT_LIGHT_INTENSITY + " INTEGER,"
            + COLUMN_REPORT_TOTAL_APPLIANCE+ " TEXT" +
            ")";
    private static final String CREATE_TABLE_LOG = "CREATE TABLE " + TABLE_LOG + " ("
            + COLUMN_LOG_ID + " INTEGER PRIMARY KEY,"
            + COLUMN_APPLIANCE_NAME + " TEXT,"
            + COLUMN_LOG_UPDATE_REQUEST + " TEXT,"
            + COLUMN_LOG_STATUS + " TEXT,"
            + COLUMN_DATE + " TEXT,"
            + COLUMN_TIME + " TEXT"
            + ")";





    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        this.context = context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_APPLIANCE);
        db.execSQL(CREATE_TABLE_REPORT);
        db.execSQL(CREATE_TABLE_LOG);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_APPLIANCE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REPORT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOG);
        // Create tables again
        onCreate(db);
    }

    public Boolean addAppliance(Appliance appliance) {
        Boolean aBoolean = false;
        try {
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(COLUMN_APPLIANCE_NAME, appliance.getName());
            values.put(COLUMN_APPLIANCE_CURRENT_STATUS, appliance.getCurrentStatus());


            // Inserting Row
            aBoolean = db.insert(TABLE_APPLIANCE, null, values) > 0;
            db.close(); // Closing database connection
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return aBoolean;
    }

    public int updateAppilanceStatus(int id , String status){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_APPLIANCE_CURRENT_STATUS, status);

        // updating row
        return db.update(TABLE_APPLIANCE, values, COLUMN_APPLIANCE_ID + " = ?",
                new String[]{String.valueOf(id)});
    }

    public Boolean addReport(Report report) {
        Boolean aBoolean = false;
        try {
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(COLUMN_REPORT_TEMPRATURE, report.getTemprature());
            values.put(COLUMN_REPORT_LIGHT_INTENSITY, report.getLightIntensity());
            values.put(COLUMN_REPORT_TOTAL_APPLIANCE, report.getTotalAppliance());

            // Inserting Row
            aBoolean = db.insert(TABLE_REPORT, null, values) > 0;
            db.close(); // Closing database connection
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return aBoolean;
    }

    public Boolean addLog(Log log) {
        Boolean aBoolean = false;
        try {
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(COLUMN_APPLIANCE_NAME, log.getApplianceName());
            values.put(COLUMN_LOG_UPDATE_REQUEST, log.getUpdateRequest());
            values.put(COLUMN_LOG_STATUS, log.getStatus());
            values.put(COLUMN_DATE, log.getDate());
            values.put(COLUMN_TIME,log.getTime());

            // Inserting Row
            aBoolean = db.insert(TABLE_LOG, null, values) > 0;
            db.close(); // Closing database connection
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return aBoolean;
    }

   public String getApplianceCurrentStatus(int applianceId){
       String returnString = ""; // Your default if none is found

       String query = new String("SELECT " + COLUMN_APPLIANCE_CURRENT_STATUS + " FROM " + TABLE_APPLIANCE + " WHERE " + COLUMN_APPLIANCE_ID + " = '" + applianceId + "'");

       SQLiteDatabase db = this.getWritableDatabase();

       Cursor cursor = db.rawQuery(query, null);

       if (cursor.moveToFirst()) {
           do {
               returnString = cursor.getString(0);
           } while (cursor.moveToNext());
       }
       cursor.close();

       return returnString;
   }

   public Report getLastReport(){

       String selectQuery = "SELECT * FROM " + TABLE_REPORT+" ORDER BY "+COLUMN_REPORT_ID+" DESC LIMIT 1";


       SQLiteDatabase db = this.getReadableDatabase();
       Cursor cursor = db.rawQuery(selectQuery, null);
       Report report = new Report();
       // looping through all rows and adding to list
       if (cursor.moveToFirst()) {
           do {
               report.setReportId(cursor.getInt(cursor.getColumnIndex(COLUMN_REPORT_ID)));
               report.setTemprature(cursor.getInt(cursor.getColumnIndex(COLUMN_REPORT_TEMPRATURE)));
               report.setLightIntensity(cursor.getInt(cursor.getColumnIndex(COLUMN_REPORT_LIGHT_INTENSITY)));
               report.setTotalAppliance(cursor.getString(cursor.getColumnIndex(COLUMN_REPORT_TOTAL_APPLIANCE)));
           } while (cursor.moveToNext());
       }
       cursor.close();
     return report ;
   }

   public ArrayList<Log> getAllLog(){
       ArrayList<Log> logs = new ArrayList<>();
       // Select All Query
       String selectQuery = "SELECT * FROM " + TABLE_LOG;


       SQLiteDatabase db = this.getReadableDatabase();
       Cursor cursor = db.rawQuery(selectQuery, null);

       // looping through all rows and adding to list
       if (cursor.moveToFirst()) {
           do {
               Log log = new Log();

               log.setLogId(cursor.getInt(cursor.getColumnIndex(COLUMN_LOG_ID)));
               log.setApplianceName(cursor.getString(cursor.getColumnIndex(COLUMN_APPLIANCE_NAME)));
               log.setUpdateRequest(cursor.getString(cursor.getColumnIndex(COLUMN_LOG_UPDATE_REQUEST)));
               log.setStatus(cursor.getString(cursor.getColumnIndex(COLUMN_LOG_STATUS)));
               log.setDate(cursor.getString(cursor.getColumnIndex(COLUMN_DATE)));
               log.setTime(cursor.getString(cursor.getColumnIndex(COLUMN_TIME)));

               // Adding contact to list
               logs.add(log);
           } while (cursor.moveToNext());
       }
       cursor.close();

       // return client list
       return logs;
   }
    public ArrayList<Appliance> getAllAppliance(){
        ArrayList<Appliance> appliances = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_APPLIANCE;


        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Appliance appliance = new Appliance();

                appliance.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_APPLIANCE_ID)));
                appliance.setName(cursor.getString(cursor.getColumnIndex(COLUMN_APPLIANCE_NAME)));
                appliance.setCurrentStatus(cursor.getString(cursor.getColumnIndex(COLUMN_APPLIANCE_CURRENT_STATUS)));


                // Adding contact to list
                appliances.add(appliance);
            } while (cursor.moveToNext());
        }
        cursor.close();

        // return client list
        return appliances;
    }

}
