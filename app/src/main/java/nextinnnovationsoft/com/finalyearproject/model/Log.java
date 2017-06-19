package nextinnnovationsoft.com.finalyearproject.model;

/**
 * Created by PT on 5/28/2017.
 */

public class Log {
    private int logId ;
    private String updateRequest ;
    private String status ;
    private String applianceName ;

    public Log(){

    }

    public String getApplianceName() {
        return applianceName;
    }

    public void setApplianceName(String applianceName) {
        this.applianceName = applianceName;
    }

    public int getLogId() {
        return logId;
    }

    public void setLogId(int logId) {
        this.logId = logId;
    }

    public String getUpdateRequest() {
        return updateRequest;
    }

    public void setUpdateRequest(String updateRequest) {
        this.updateRequest = updateRequest;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    private String date  ;

    public Log(String applianceName , String updateRequest, String status, String date, String time) {
        this.applianceName = applianceName ;
        this.updateRequest = updateRequest;
        this.status = status;
        this.date = date;
        this.time = time;
    }

    private String time ;
}
