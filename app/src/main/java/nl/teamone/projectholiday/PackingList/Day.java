package nl.teamone.projectholiday.PackingList;

import java.util.Date;

/**
 * Created by IAmHeavenly on 26-5-2015.
 */
public class Day {
    private Calculationéh calcs;
    /**
     * constructor, which immediately sets day type
     * @param date
     */
    public Day(Date date){
        //retrieve weather data for date
        /*
        calcs = new Calculationéh();
        mDayType = calcs.getDay();
        */
    }

    /**
     * getter for the day type
     * @return mDayType
     */
    public DayType getDay(){
        return calcs.getDay();
    }

    /**
     * getter for the night type
     * @return mNightType
     */
    public NightType getNight(){
        return calcs.getNight();
    }
}
