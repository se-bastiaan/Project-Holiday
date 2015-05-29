package nl.teamone.projectholiday.PackingList;

/**
 * Created by Gebruiker on 28-5-2015.
 */
public class Clothing {
    private String id_name;
    private int quantity;
    public Clothing(String name, int quantity){
        this.id_name = name;
        this.quantity = quantity;
    }
    @Override
    public String toString(){
        String str = "";
        if(quantity>1) {
            str = id_name + ": " + quantity + " times.\n";
        } else if(quantity == 1){
            str =  id_name + ": " + quantity + " time.\n";
        }
        return str;
    }
}
