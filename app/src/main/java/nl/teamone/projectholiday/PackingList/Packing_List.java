package nl.teamone.projectholiday.PackingList;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by IAmHeavenly on 26-5-2015.
 */
public class Packing_List {
    private ArrayList<Day> Holiday;

    /**
     * constructoer which makes an arraylist with days
     * @param start
     * @param end
     */
    public Packing_List(Date start, Date end ){
        int difference = end.getDate() - start.getDate();
        // TODO: add all days in between start and end to Holiday list
        /*
        for(int i = 0; i < difference; i++){
            Day newDay = new Day(start.getDay()+difference,
                    start.getMonth()+difference,
                    start.getYear());

            Holiday.add(new Day(start));
        }*/

    }
    //Getters will be used in the end product, depending on the clothing,
    //the clothes will call on different typings and combine them to
    //finally, use the number in a calculation to print an endresult

    /**
     * counts the number of days with the day type "day1"
     * @return numberOfDays
     */
    public int getDay1(){
        int numberOfDays=0;
        for(Day day : Holiday){
            if(day.getter()==DayType.mDay1){
                numberOfDays++;
            }
        }
        return numberOfDays;
    }

    /**
     * counts the number of days with the day type "day2"
     * @return numberOfDays
     */
    public int getDay2(){
        int numberOfDays=0;
        for(Day day : Holiday){
            if(day.getter()==DayType.mDay2){
                numberOfDays++;
            }
        }
        return numberOfDays;
    }

    /**
     * counts the number of days with the day type "day3"
     * @return numberOfDays
     */
    public int getDay3(){
        int numberOfDays=0;
        for(Day day : Holiday){
            if(day.getter()==DayType.mDay3){
                numberOfDays++;
            }
        }
        return numberOfDays;
    }

    /**
     * counts the number of days with the day type "day4"
     * @return numberOfDays
     */
    public int getDay4(){
        int numberOfDays=0;
        for(Day day : Holiday){
            if(day.getter()==DayType.mDay4){
                numberOfDays++;
            }
        }
        return numberOfDays;
    }

    /**
     * counts the number of days with the day type "day5"
     * @return numberOfDays
     */
    public int getDay5(){
        int numberOfDays=0;
        for(Day day : Holiday){
            if(day.getter()==DayType.mDay5){
                numberOfDays++;
            }
        }
        return numberOfDays;
    }

    /**
     * counts the number of days with the day type "day6"
     * @return numberOfDays
     */
    public int getDay6(){
        int numberOfDays=0;
        for(Day day : Holiday){
            if(day.getter()==DayType.mDay6){
                numberOfDays++;
            }
        }
        return numberOfDays;
    }

    /**
     * counts the number of days with the day type "day7"
     * @return numberOfDays
     */
    public int getDay7(){
        int numberOfDays=0;
        for(Day day : Holiday){
            if(day.getter()==DayType.mDay7){
                numberOfDays++;
            }
        }
        return numberOfDays;
    }

}
