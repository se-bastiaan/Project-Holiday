package nl.teamone.projectholiday.algorithm;

public class Clothing {
    private String mId;
    private int mQuantity;

    /**
     * Constructor for clothing with an id and a quantity
     * @param name
     * @param quantity
     */
    public Clothing(String name, int quantity) {
        mId = name;
        mQuantity = quantity;
    }

    /**
     * Getter for id of cloth
     * @return mId
     */
    public String getId() {
        return mId;
    }

    /**
     * Getter for the quantity
     * @return mQuantity
     */
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
