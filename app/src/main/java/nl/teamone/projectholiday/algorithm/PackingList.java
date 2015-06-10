package nl.teamone.projectholiday.algorithm;


import java.util.ArrayList;

import nl.teamone.projectholiday.api.objects.WeatherDay;
import nl.teamone.projectholiday.api.objects.WeatherPeriod;
import retrofit.http.Path;

public class PackingList {
    private ArrayList<Day> mHoliday;
    private ArrayList<Clothing> mClothingList;
    private boolean mDresses;
    private final int allowedChanges = 2;

    /**
     * Constructor which makes an array list with days
     * @param period
     */
    public PackingList(WeatherPeriod period, boolean dresses) {
        mHoliday = new ArrayList<>();
        mDresses = dresses;
        for (WeatherDay day : period.getWeatherData()) {
            mHoliday.add(new Day(day));
        }
        mClothingList = new ArrayList<>();
        addAllClothing();
    }

    /**
     * Counts the number of days with the given day type
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
     * @return nrOfDays
     */
    private int getTotalDays() {
        return mHoliday.size();
    }

    /**
     * Checks for lowest temperature during the entire holiday
     * @return lowestTemp
     */
    private int lowestTemp() {
        int lowestTemp = 100;
        for (Day day : mHoliday) {
            if (day.getWeatherDay().getTemperatureLow() < 100) {
                lowestTemp = day.getWeatherDay().getTemperatureLow();
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

        if (mDresses)
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
        int nrOfPants = (countDayTypes(DayType.LEVEL1) + countDayTypes(DayType.LEVEL2) +
                countDayTypes(DayType.LEVEL3) + countDayTypes(DayType.LEVEL4) +
                countDayTypes(DayType.LEVEL5)) / 3;
        int nrOfShorts = (countDayTypes(DayType.LEVEL6) + countDayTypes(DayType.LEVEL7)) / 3;
        if(nrOfPants!=0) {
            mClothingList.add(new Clothing("pants", nrOfPants));
        }
        if(nrOfShorts!=0) {
            mClothingList.add(new Clothing("shorts", nrOfShorts));
        }
    }

    /**
     * Adds long and short sleeved shirts
     */
    private void addTShirts() {
        int nrOfShortSleeve = ( countDayTypes(DayType.LEVEL5) + countDayTypes(DayType.LEVEL6) +
                countDayTypes(DayType.LEVEL7) );
        int nrOfLongSleeve = ( countDayTypes(DayType.LEVEL1) + countDayTypes(DayType.LEVEL2) +
                countDayTypes(DayType.LEVEL3) + countDayTypes(DayType.LEVEL4) );
        if(nrOfShortSleeve!=0) {
            mClothingList.add(new Clothing("short_sleeve_shirts", nrOfShortSleeve));
        }
        if(nrOfLongSleeve!=0) {
            mClothingList.add(new Clothing("long_sleeve_shirts", nrOfLongSleeve));
        }
    }

    /**
     * Adds dresses
     */
    private void addDresses() {
        int nrOfDresses = countDayTypes(DayType.LEVEL6) + countDayTypes(DayType.LEVEL7);
        if(nrOfDresses!=0){
            mClothingList.add(new Clothing("dresses", nrOfDresses));
        }


    }

    /**
     * Adds sweaters
     */
    private void addSweaters() {
        int nrOfSweaters = (countDayTypes(DayType.LEVEL1) + countDayTypes(DayType.LEVEL2) +
                countDayTypes(DayType.LEVEL3) + countDayTypes(DayType.LEVEL4) +
                countDayTypes(DayType.LEVEL5)) / 3;
        if(nrOfSweaters!=0) {
            mClothingList.add(new Clothing("sweaters", nrOfSweaters));
        }
    }

    /**
     * Adds socks, underwear and pyjamas
     */
    private void addUnderStuff() {
        int nrOfSocks = (getTotalDays() - countDayTypes(DayType.LEVEL7));
        if(nrOfSocks!=0) {
            mClothingList.add(new Clothing("socks", nrOfSocks));
        }
        int nrOfUnderwear = getTotalDays();
        if(nrOfUnderwear!=0) {
            mClothingList.add(new Clothing("underwear", nrOfUnderwear));
        }
        int nrOfThinPJs =countNightTypes(NightType.LEVEL2) / 5;
        if(nrOfThinPJs!=0) {
            mClothingList.add(new Clothing("thin_pyjamas", nrOfThinPJs));
        }
        int nrOfThickPJs = countNightTypes(NightType.LEVEL1) / 5;
        if (nrOfThickPJs!=0) {
            mClothingList.add(new Clothing("thick_pyjamas", nrOfThickPJs));
        }
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
     * @return mClothingList
     */
    public ArrayList<Clothing> getClothingList() {
        return mClothingList;
    }

    /**
     * Checks whether this.clothingList is equal to another clothinglist.
     * @param clothingList The clothinglist to check differences against.
     * @return true if the number of changes <= allowedChanges, false otherwise.
     */
    public boolean equals(ArrayList<Clothing> clothingList) {
        int changes = getDifference(this.getClothingList(), clothingList);
        if (changes > allowedChanges)
            return false;
        return true;
    }

    /**
     * Gets the amount of clothing that differs between two packing lists.
     * @param firstList The first list of clothing.
     * @param secondList The second list of clothing.
     * @return The amount of clothes that differ. >=0; 0 means the lists are identical.
     */
    private static int getDifference(ArrayList<Clothing> firstList, ArrayList<Clothing> secondList) {
        int difference = 0;

        for (Clothing c : firstList) {
            Clothing other = findClothing(c, secondList);
            if (other == null) {
                difference += c.getQuantity();
            } else {
                difference += Math.abs(c.getQuantity() - other.getQuantity());
            }
        }

        for (Clothing c : secondList) {
            Clothing other = findClothing(c, firstList);
            if (other == null) {
                difference += c.getQuantity();
            } else {
                // Already handled in the first for loop, don't need to do it again.
            }
        }

        return difference;
    }

    private static Clothing findClothing(Clothing searchFor, ArrayList<Clothing> list) {
        for (Clothing c : list) {
            if (c.getId().equalsIgnoreCase(searchFor.getId())) {
                return c;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return mClothingList.toString();
    }


}
