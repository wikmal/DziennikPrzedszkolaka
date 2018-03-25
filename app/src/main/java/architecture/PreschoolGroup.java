package architecture;

import com.weteam.weteam.dziennikprzedszkolaka.StartActivity;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * Created by User on 2017-11-19.
 */

public class PreschoolGroup {
    public String name;
    public int preschoolGroupId;
    static public ArrayList<Parent> parents = new ArrayList<>();
    public int teacherId;
    public PriorityQueue<Calendar> calendar = new PriorityQueue<>();
    public Schedule schedule;
    static public ArrayList<Announcement> announcements = new ArrayList<>();

    public PreschoolGroup(String name, int preschoolGroupId, int teacherId) {
        this.name = name;
        this.preschoolGroupId = preschoolGroupId;
        this.teacherId = teacherId;
    }

//--------------------------------------------------------------------------------------------------


}
