package nl.teamone.projectholiday.PackingList;

import java.util.Date;

/**
 * Created by IAmHeavenly on 26-5-2015.
 */
public class Day {
    private DayType mDayType;

    /**
     * constructor, which immediately sets day type
     * @param date
     */
    public Day(Date date){
        //retrieve weather data for date
        Calculationéh calcs = new Calculationéh();
        mDayType = calcs.getDay();
    }

    /**
     * getter for the day type
     * @return mDayType
     */
    public DayType getter(){
        return mDayType;
    }

}
