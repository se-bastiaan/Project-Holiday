package nl.teamone.projectholiday.algorithm;

public class Clothing {
    private String mId;
    private int mQuantity;

    public Clothing(String name, int quantity) {
        mId = name;
        mQuantity = quantity;
    }

    public String getId() {
        return mId;
    }

    public int getQuantity() {
        return mQuantity;
    }

    @Override
    public String toString() {
        String str = "";
        if (mQuantity > 1) {
            str = mId + ": " + mQuantity + " times.\n";
        } else if (mQuantity == 1) {
            str = mId + ": " + mQuantity + " time.\n";
        }
        return str;
    }
}
