package nl.teamone.projectholiday.PackingList;


import java.util.ArrayList;

import nl.teamone.projectholiday.WeatherData.WeatherDay;
import nl.teamone.projectholiday.WeatherData.WeatherPeriod;

/**
 * Created by IAmHeavenly on 26-5-2015.
 */
public class PackingList {
    private ArrayList<Day> mHoliday;
    private WeatherPeriod mPeriod;
    private ArrayList<Clothing> ClothingList;
    /**
     * constructor which makes an array list with days
     * @param period
     */
    public PackingList(WeatherPeriod period){
        this.mHoliday = new ArrayList<>();
        this.mPeriod = period;
        for (WeatherDay day : mPeriod.getWeatherData()) {
            mHoliday.add(new Day(day));
        }
        this.ClothingList = new ArrayList<>();
        addAllClothing();
    }

    //getters
    /**
     * counts the number of days with the day type "day1"
     * @return numberOfDays
     */
    private int getDay1(){
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
    private int getDay2(){
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
    private int getDay3(){
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
    private int getDay4(){
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
    private int getDay5(){
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
    private int getDay6(){
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
    private int getDay7(){
        int numberOfDays=0;
        for(Day day : mHoliday){
            if(day.getDay()==DayType.mDay7){
                numberOfDays++;
            }
        }
        return numberOfDays;
    }
    /**
     * Getter for total number of days
     * @return nrOfDays
     */
    private int getTotalDays(){
        int nrOfDays = 0;
        for(Day day : mHoliday){
            if(day.getDay()==DayType.mDay7){
                nrOfDays++;
            }
        }
        return nrOfDays;
    }

    /**
     * Counter for number of days with night type 1
     * @return nrOfDays
     */
    private int getNight1(){
        int nrOfDays = 0;
        for(Day day:mHoliday){
            if(day.getNight()==NightType.mNight1){
                nrOfDays++;
            }
        }
        return nrOfDays;
    }

    /**
     * Counter for number of days with night type 2
     * @return nrOfDays
     */
    private int getNight2(){
        int nrOfDays = 0;
        for(Day day:mHoliday){
            if(day.getNight()==NightType.mNight2){
                nrOfDays++;
            }
        }
        return nrOfDays;
    }

    /**
     * Counter for number of days with night type 3
     * @return nrOfDays
     */
    private int getNight3(){
        int nrOfDays = 0;
        for(Day day:mHoliday){
            if(day.getNight()==NightType.mNight3){
                nrOfDays++;
            }
        }
        return nrOfDays;
    }
    /**
     * Checks for lowest temperature during the entire holiday
     * @return lowestTemp
     */
    private int lowestTemp(){
        int lowestTemp = 100;
        for(Day day:mHoliday){
            if(day.wDay.mTemperatureLow<100){
                lowestTemp = day.wDay.getTempFeel();
            }
        }
        return lowestTemp;
    }
    //adding the clothing

    /**
     * Adds all the different parts to the clothing list
     */
    private void addAllClothing(){
        addPants();
        addTShirts();
        //TODO: get a way to find out if user is male/female in order to use  "addDresses()"

        addSweaters();
        addUnderStuff();
        if(getDay7()+getDay6() > 0){addSwimmin();}
        addCoats();
        addShoes();
    }

    /**
     * Adds pants+shorts
     */
    private void addPants(){
        int nrOfPants = (getDay1()+getDay2()+getDay3()+getDay4()+getDay5()) / 3;
        int nrOfShorts = (getDay6()+getDay7()) / 3;
        this.ClothingList.add(new Clothing("Pants", nrOfPants));
        this.ClothingList.add(new Clothing("Shorts", nrOfShorts));
    }

    /**
     * Adds long and short sleeved shirts
     */
    private void addTShirts(){
        int nrOfShortSleeve = (getDay4()+getDay5()+getDay6()+getDay7());
        int nrOfLongSleeve = (getDay1()+getDay2()+getDay3());
        this.ClothingList.add(new Clothing ("Short sleeved shirts", nrOfShortSleeve));
        this.ClothingList.add(new Clothing("Long sleeved shirts", nrOfLongSleeve));
    }

    /**
     * Adds dresses
     */
    private void addDresses(){
        int nrOfDresses = getDay6()+getDay7();
        this.ClothingList.add(new Clothing("Dresses", nrOfDresses));

    }

    /**
     * Adds sweaters
     */
    private void addSweaters(){
        int nrOfSweaters = (getDay1()+getDay2()+getDay3()+getDay4()+getDay5())/3;
        this.ClothingList.add(new Clothing("Sweaters/Hoodies", nrOfSweaters));
    }

    /**
     * Adds socks, underwear and pyjamas
     */
    private void addUnderStuff(){
        int nrOfSocks = (getTotalDays() - getDay7());
        this.ClothingList.add(new Clothing("Socks", nrOfSocks));
        int nrOfUnderwear = getTotalDays();
        this.ClothingList.add(new Clothing("Underwear", nrOfUnderwear));
        int nrOfThinPJs = getNight2()/3;
        this.ClothingList.add(new Clothing("Thin pyjamas", nrOfThinPJs));
        int nrOfThickPJs = getNight1()/3;
        this.ClothingList.add(new Clothing("Thick pyjamas", nrOfThickPJs));
    }

    /**
     * Adds swimming wear
     */
    private void addSwimmin(){
        this.ClothingList.add(new Clothing("Swimwear", 1));
    }

    /**
     * Adds rain/winter/summer coats
     */
    private void addCoats(){
        if(getDay1()+getDay2()>0){
            this.ClothingList.add(new Clothing("Rain coat", 1));
        }
        if(lowestTemp()<15){
            this.ClothingList.add(new Clothing("Winter coat", 1));
        } else {
            this.ClothingList.add(new Clothing("Summer coat", 1));
        }
    }

    /**
     * Adds boots/sandals/normal shoes
     */
    private void addShoes(){
        int otherChoiceSelected = 0;
        if(getDay1()+getDay2()>0){
            this.ClothingList.add(new Clothing("Rubber boots", 1));
            otherChoiceSelected = 1;
        }
        if(getDay7()+getDay6()>0){
            this.ClothingList.add(new Clothing("Sandals", 1));
            otherChoiceSelected = 1;
        }
        this.ClothingList.add(new Clothing("Pair of shoes", (2-otherChoiceSelected) ));
    }





















}
