package architecture;

/**
 * Created by User on 2017-11-19.
 */

public class ImageEntity {
    private int id;
    private String name;
   private byte[] image;

    public ImageEntity(int id, String name, byte[] image) {
        this.name = name;
        this.image = image;
        this.id=id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public byte[] getImage() {
        return image;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
