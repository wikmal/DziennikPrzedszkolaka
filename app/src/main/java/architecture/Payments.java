package architecture;

/**
 * Created by User on 2017-11-19.
 */

public class Payments {
    public int id;
    public enum Status{
        ZAPŁACONE, NIEZAPŁACONE;
    }
    public String description;
    public float amount;
    public Status status;

    public Payments(int id, String description, float amount) {
        this.id = id;
        this.description = description;
        this.amount = amount;
        status = Status.NIEZAPŁACONE;
    }
}
