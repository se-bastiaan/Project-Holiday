package nl.teamone.projectholiday.PackingList;

import java.util.ArrayList;

import nl.teamone.projectholiday.WeatherData.WeatherDay;
import nl.teamone.projectholiday.WeatherData.WeatherPeriod;

/**
 * Created by IAmHeavenly on 26-5-2015.
 */
public class Packing_List {
    private ArrayList<Day> mHoliday;
    private WeatherPeriod mPeriod;
    /**
     * constructor which makes an array list with days
     * @param period
     */
    public Packing_List(WeatherPeriod period){
        mHoliday = new ArrayList<>();
        this.mPeriod = period;
        for (WeatherDay day : mPeriod.getWeatherData()) {
            mHoliday.add(new Day(day.day, day));
        }

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
        for(Day day : mHoliday){
            if(day.getDay()==DayType.mDay1){
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
        for(Day day : mHoliday){
            if(day.getDay()==DayType.mDay2){
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
        for(Day day : mHoliday){
            if(day.getDay()==DayType.mDay3){
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
        for(Day day : mHoliday){
            if(day.getDay()==DayType.mDay4){
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
        for(Day day : mHoliday){
            if(day.getDay()==DayType.mDay5){
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
        for(Day day : mHoliday){
            if(day.getDay()==DayType.mDay6){
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
        for(Day day : mHoliday){
            if(day.getDay()==DayType.mDay7){
                numberOfDays++;
            }
        }
        return numberOfDays;
    }

}
