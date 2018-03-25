package architecture;

import java.text.DateFormat;

/**
 * Created by User on 2018-01-13.
 */

public class Message {
    public int id;
    public String text;
    public String insertDate;

    public Message(int id, String text, String insertDate) {
        this.id = id;
        this.text = text;
        this.insertDate = insertDate;
    }
}
