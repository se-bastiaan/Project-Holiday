package nl.teamone.projectholiday.PackingList;

/**
 * Created by IAmHeavenly on 26-5-2015.
 */
public class Calculationéh {
    private DayType mDayType;
    private NightType mNightType;
    private RainType mRainType;
    private SunType mSunType;



    public Calculationéh(){
        /*give the day a new value
        calculateSun();
        calculateNight();
        calculateRain();
        then calculate the DayType with the new values
        calculateDay();
        */
    }
    private void calculateRain(int percentage, int mm){
        //calculation to set RainType
        if(mm==0||percentage <30){
            this.mRainType = RainType.mRain1; //no chance of rain
        } else if(30<=percentage && percentage <=60){
            this.mRainType = RainType.mRain2; // little chance of rain
        } else { //meaning 60 <= rain
            this.mRainType = RainType.mRain3; //chance of mossoon
        }
    }
    private void calculateSun(int degrees){
        //calculation to set SunType
        if(degrees < 20) {
            this.mSunType = SunType.mSun1; //no chance of sun
        } else if(20 <= degrees && degrees <= 25) {
            this.mSunType = SunType.mSun2; //little chance of sun
        } else { //meaning 25 <= degrees
            this.mSunType = SunType.mSun3; //chance of drought
        }
    }
    private void calculateNight(int degrees){
        //calculation to set NightType
        if(5<=degrees) {
            this.mNightType = NightType.mNight1; //freezing
        } else if(5 < degrees && degrees >= 10) {
            this.mNightType = NightType.mNight2; //cold
        } else { //meaning degrees > 10
            this.mNightType = NightType.mNight3; //GET RID OF THE SHEEETS
        }
    }
    private void calculateDay(SunType ST, RainType RT){
        //calculation to set DayType
        if(ST==SunType.mSun1){ //no chance of sun
            if(RT==RainType.mRain1) {
                this.mDayType = DayType.mDay5;
            } else if(RT==RainType.mRain2){
                this.mDayType = DayType.mDay3;
            } else if(RT==RainType.mRain3){
                this.mDayType = DayType.mDay1;
            }
        } else if(ST==SunType.mSun2){ //little chance of sun
            if(RT==RainType.mRain1) {
                this.mDayType = DayType.mDay6;
            } else if(RT==RainType.mRain2){
                this.mDayType = DayType.mDay4;
            } else if(RT==RainType.mRain3){
                this.mDayType = DayType.mDay2;
            }
        } else if(ST==SunType.mSun3){ //big chance of sun
            if(RT==RainType.mRain1) {
                this.mDayType = DayType.mDay7;
            } else if(RT==RainType.mRain2){
                this.mDayType = DayType.mDay5;
            } else if(RT==RainType.mRain3){
                this.mDayType = DayType.mDay3;
            }
        }
    }



}























