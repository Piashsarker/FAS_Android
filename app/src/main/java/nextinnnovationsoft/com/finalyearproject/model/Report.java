package nextinnnovationsoft.com.finalyearproject.model;

/**
 * Created by PT on 5/28/2017.
 */

public class Report {
    private int reportId;
    private int temprature;
    private String totalAppliance;
    private int lightIntensity;

    public Report(int temprature, int lightIntensity, String totalAppliance) {
        this.temprature = temprature;
        this.lightIntensity = lightIntensity;
        this.totalAppliance = totalAppliance;
    }

    public Report() {

    }

    public String getTotalAppliance() {
        return totalAppliance;
    }

    public void setTotalAppliance(String totalAppliance) {
        this.totalAppliance = totalAppliance;
    }

    public int getReportId() {
        return reportId;
    }

    public void setReportId(int reportId) {
        this.reportId = reportId;
    }

    public int getTemprature() {
        return temprature;
    }

    public void setTemprature(int temprature) {
        this.temprature = temprature;
    }

    public int getLightIntensity() {
        return lightIntensity;
    }

    public void setLightIntensity(int lightIntensity) {
        this.lightIntensity = lightIntensity;
    }
}
