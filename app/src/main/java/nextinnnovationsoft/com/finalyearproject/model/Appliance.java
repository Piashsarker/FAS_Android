package nextinnnovationsoft.com.finalyearproject.model;

/**
 * Created by PT on 5/28/2017.
 */

public class Appliance {

    private int id ;
    private String name ;
    private String currentStatus ;

    public Appliance(){

    }
    public Appliance( String name, String currentStatus) {

        this.name = name;
        this.currentStatus = currentStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(String currentStatus) {
        this.currentStatus = currentStatus;
    }
}
