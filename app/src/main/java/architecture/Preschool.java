package architecture;

import java.util.ArrayList;

/**
 * Created by User on 2017-11-19.
 */

public class Preschool extends User {
    public String location;
    public String openingHours;
    public ArrayList<Guide> guideBook = new ArrayList<>();
    public ImageEntity menu;
    public String phone;



    public Preschool(String login, int password , String name, String location, String openingHours, int preschoolId, String phone) {
        super(login, password, 2, name, preschoolId);
        this.location = location;
        this.openingHours = openingHours;
        this.phone = phone;
    }



//--------------------------------------------------------------------------------------------------

}
