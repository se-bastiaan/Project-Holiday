package nl.teamone.projectholiday.PackingList;

import java.util.Date;

import nl.teamone.projectholiday.WeatherData.WeatherDay;

/**
 * Created by IAmHeavenly on 26-5-2015.
 */
public class Day {
    private Calculationéh calcs;
    public WeatherDay wDay;
    /**
     * constructor, which immediately sets day type
     * @param wDay
     */
    public Day(WeatherDay wDay){
        this.calcs = new Calculationéh(wDay);
        this.wDay = wDay;
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
