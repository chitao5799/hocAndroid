package mtha.fithou.myorderfood;

public class Food {
    String id;
    String name;
    String des;
    String price;
    String amount;
    byte[] picture;

    public Food() {
    }

    public Food(String id, String name, String des,
                String price, String amount, byte[] picture) {
        this.id = id;
        this.name = name;
        this.des = des;
        this.price = price;
        this.amount = amount;
        this.picture = picture;
    }
}
