package architecture;

/**
 * Created by User on 2017-11-19.
 */

public class Teacher extends User {
    public int groupId;
    public int preschoolId =0;

    public Teacher(String login, int password, String name, int teacherId, int preschoolId) {
        super(login, password, 1, name, teacherId);
        this.preschoolId = preschoolId;
    }

    //--------------------------------------------------------------------------------------------------

}
