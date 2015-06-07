package nl.teamone.projectholiday.algorithm;


import java.util.ArrayList;

import nl.teamone.projectholiday.api.WeatherDay;
import nl.teamone.projectholiday.api.WeatherPeriod;

public class PackingList {
    private ArrayList<Day> mHoliday;
    private Gender mGender;
    private ArrayList<Clothing> mClothingList;

    /**
     * constructor which makes an array list with days
     *
     * @param period
     */
    public PackingList(WeatherPeriod period, Gender gender) {
        mHoliday = new ArrayList<>();
        mGender = gender;
        for (WeatherDay day : period.getWeatherData()) {
            mHoliday.add(new Day(day));
        }
        mClothingList = new ArrayList<>();
        addAllClothing();
    }

    /**
     * counts the number of days with given the day type
     * @param type {@link DayType}
     * @return numberOfDays
     */
    private int countDayTypes(DayType type) {
        int numberOfDays = 0;
        for (Day day : mHoliday) {
            if (day.getDay() == type) {
                numberOfDays++;
            }
        }
        return numberOfDays;
    }

    /**
     * Counter for number of nights with the given type
     * @param type {@link NightType}
     * @return nrOfDays
     */
    private int countNightTypes(NightType type) {
        int nrOfDays = 0;
        for (Day day : mHoliday) {
            if (day.getNight() == type) {
                nrOfDays++;
            }
        }
        return nrOfDays;
    }

    /**
     * Getter for total number of days
     *
     * @return nrOfDays
     */
    private int getTotalDays() {
        int nrOfDays = 0;
        for (Day day : mHoliday) {
            if (day.getDay() == DayType.LEVEL7) {
                nrOfDays++;
            }
        }
        return nrOfDays;
    }

    /**
     * Checks for lowest temperature during the entire holiday
     *
     * @return lowestTemp
     */
    private int lowestTemp() {
        int lowestTemp = 100;
        for (Day day : mHoliday) {
            if (day.getWeatherDay().getTempLow() < 100) {
                lowestTemp = day.getWeatherDay().getTempFeel();
            }
        }
        return lowestTemp;
    }

    /* Clothing add procedures below */

    /**
     * Adds all the different parts to the clothing list
     */
    private void addAllClothing() {
        addPants();
        addTShirts();
        
        if(mGender == Gender.FEMALE)
            addDresses();

        addSweaters();
        addUnderStuff();
        if (countDayTypes(DayType.LEVEL7) + countDayTypes(DayType.LEVEL6) > 0) {
            addSwimmin();
        }
        addCoats();
        addShoes();
        addUmbrella();
        addHeadWear();
    }

    /**
     * Adds pants+shorts
     */
    private void addPants() {
        int nrOfPants = (countDayTypes(DayType.LEVEL1) + countDayTypes(DayType.LEVEL2) + countDayTypes(DayType.LEVEL3) + countDayTypes(DayType.LEVEL4) + countDayTypes(DayType.LEVEL5)) / 3;
        int nrOfShorts = (countDayTypes(DayType.LEVEL6) + countDayTypes(DayType.LEVEL7)) / 3;
        mClothingList.add(new Clothing("pants", nrOfPants));
        mClothingList.add(new Clothing("shorts", nrOfShorts));
    }

    /**
     * Adds long and short sleeved shirts
     */
    private void addTShirts() {
        int nrOfShortSleeve = (countDayTypes(DayType.LEVEL4) + countDayTypes(DayType.LEVEL5) + countDayTypes(DayType.LEVEL6) + countDayTypes(DayType.LEVEL7));
        int nrOfLongSleeve = (countDayTypes(DayType.LEVEL1) + countDayTypes(DayType.LEVEL2) + countDayTypes(DayType.LEVEL3));
        mClothingList.add(new Clothing("short_sleeve_shirts", nrOfShortSleeve));
        mClothingList.add(new Clothing("long_sleeve_shirts", nrOfLongSleeve));
    }

    /**
     * Adds dresses
     */
    private void addDresses() {
        int nrOfDresses = countDayTypes(DayType.LEVEL6) + countDayTypes(DayType.LEVEL7);
        mClothingList.add(new Clothing("dresses", nrOfDresses));

    }

    /**
     * Adds sweaters
     */
    private void addSweaters() {
        int nrOfSweaters = (countDayTypes(DayType.LEVEL1) + countDayTypes(DayType.LEVEL2) + countDayTypes(DayType.LEVEL3) + countDayTypes(DayType.LEVEL4) + countDayTypes(DayType.LEVEL5)) / 3;
        mClothingList.add(new Clothing("sweaters", nrOfSweaters));
    }

    /**
     * Adds socks, underwear and pyjamas
     */
    private void addUnderStuff() {
        int nrOfSocks = (getTotalDays() - countDayTypes(DayType.LEVEL7));
        mClothingList.add(new Clothing("socks", nrOfSocks));
        int nrOfUnderwear = getTotalDays();
        mClothingList.add(new Clothing("underwear", nrOfUnderwear));
        int nrOfThinPJs = countNightTypes(NightType.LEVEL2) / 3;
        mClothingList.add(new Clothing("thin_pyjamas", nrOfThinPJs));
        int nrOfThickPJs = countNightTypes(NightType.LEVEL1) / 3;
        mClothingList.add(new Clothing("thick_pyjamas", nrOfThickPJs));
    }

    /**
     * Adds swimming wear
     */
    private void addSwimmin() {
        mClothingList.add(new Clothing("swimwear", 1));
    }

    /**
     * Adds rain/winter/summer coats
     */
    private void addCoats() {
        if (countDayTypes(DayType.LEVEL1) + countDayTypes(DayType.LEVEL2) > 0) {
            mClothingList.add(new Clothing("rain_coat", 1));
        }
        if (lowestTemp() < 15) {
            mClothingList.add(new Clothing("winter_coat", 1));
        } else {
            mClothingList.add(new Clothing("summer_coat", 1));
        }
    }

    /**
     * Adds boots/sandals/normal shoes/slippers
     */
    private void addShoes() {
        int otherChoiceSelected = 0;
        if (countDayTypes(DayType.LEVEL1) + countDayTypes(DayType.LEVEL2) > 0) {
            mClothingList.add(new Clothing("rubber_boots", 1));
            otherChoiceSelected = 1;
        }
        if (countDayTypes(DayType.LEVEL7) + countDayTypes(DayType.LEVEL6) > 0) {
            mClothingList.add(new Clothing("sandals", 1));
            otherChoiceSelected = 1;
        }
        mClothingList.add(new Clothing("shoes", (2 - otherChoiceSelected)));
        mClothingList.add(new Clothing("slippers", 1));
    }

    /**
     * Adds umbrella
     */
    private void addUmbrella() {
        if (countDayTypes(DayType.LEVEL1) + countDayTypes(DayType.LEVEL2) > 0) {
            mClothingList.add(new Clothing("umbrella", 1));
        }
    }

    /**
     * Adds a pet and/or a hood
     */
    private void addHeadWear() {
        if (countDayTypes(DayType.LEVEL6) + countDayTypes(DayType.LEVEL7) > 0) {
            mClothingList.add(new Clothing("pet", 1));
        }
        if (countDayTypes(DayType.LEVEL1) + countDayTypes(DayType.LEVEL2) > 0) {
            mClothingList.add(new Clothing("hood", 1));
        }
    }

    /**
     * Getter for clothing list
     *
     * @return mClothingList
     */
    public ArrayList<Clothing> getClothingList() {
        return mClothingList;
    }

    @Override
    public String toString() {
        return mClothingList.toString();
    }


}