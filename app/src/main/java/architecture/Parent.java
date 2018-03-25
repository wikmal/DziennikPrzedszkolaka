package architecture;


import java.util.ArrayList;

/**
 * Created by User on 2017-11-19.
 */

public class Parent extends User {
    public int preschoolGroupId;
    public ArrayList<Payments> payments = new ArrayList<>();
    public ArrayList<ImageEntity> drawingsGallery = new ArrayList<>();
    public ArrayList<Message> messages = new ArrayList<>();

    public Parent(String login, int password,  String name, int preschoolGroupId, int parentId) {
        super(login, password, 0, name, parentId);
        this.preschoolGroupId = preschoolGroupId;
    }
}
