package nl.teamone.projectholiday.algorithm;

import nl.teamone.projectholiday.api.objects.WeatherDay;

public class Calculation {
    private DayType mDayType;
    private NightType mNightType;
    private RainType mRainType;
    private SunType mSunType;

    /**
     * Calculates all the types depending on the params
     *
     * @param day
     */
    public Calculation(WeatherDay day) {
        calculateSun(day.getTempFeel());
        calculateNight(day.getTempMean());
        calculateRain(day.getRainPerc(), day.getRainMM());
        calculateDay(mSunType, mRainType);

    }

    /**
     * Calculates rain type, sets mRainType
     *
     * @param percentage
     * @param mm
     */
    private void calculateRain(int percentage, int mm) {
        //calculation to set RainType
        if (mm == 0 || percentage < 30) {
            mRainType = RainType.LEVEL1; //no chance of rain
        } else if (30 <= percentage && percentage <= 60) {
            mRainType = RainType.LEVEL2; // little chance of rain
        } else { //meaning 60 <= rain
            mRainType = RainType.LEVEL3; //chance of mossoon
        }
    }

    /**
     * Calculates sun type, sets mSunType
     *
     * @param degrees
     */
    private void calculateSun(int degrees) {
        //calculation to set SunType
        if (degrees < 20) {
            this.mSunType = SunType.LEVEL1; //no chance of sun
        } else if (20 <= degrees && degrees <= 25) {
            this.mSunType = SunType.LEVEL2; //little chance of sun
        } else { //meaning 25 <= degrees
            this.mSunType = SunType.LEVEL3; //chance of drought
        }
    }

    /**
     * Calculates night type, mNightType
     *
     * @param degrees
     */
    private void calculateNight(int degrees) {
        //calculation to set NightType
        if (5 <= degrees) {
            this.mNightType = NightType.LEVEL1; //freezing
        } else if (5 < degrees && degrees >= 10) {
            this.mNightType = NightType.LEVEL2; //cold
        } else { //meaning degrees > 10
            this.mNightType = NightType.LEVEL3; //GET RID OF THE SHEEETS
        }
    }

    /**
     * Calculates day type depending on sun type and rain type, then sets mDayType
     *
     * @param ST
     * @param RT
     */
    private void calculateDay(SunType ST, RainType RT) {
        //calculation to set DayType
        if (ST == SunType.LEVEL1) { //no chance of sun
            if (RT == RainType.LEVEL1) {
                this.mDayType = DayType.LEVEL5;
            } else if (RT == RainType.LEVEL2) {
                this.mDayType = DayType.LEVEL3;
            } else if (RT == RainType.LEVEL3) {
                this.mDayType = DayType.LEVEL1;
            }
        } else if (ST == SunType.LEVEL2) { //little chance of sun
            if (RT == RainType.LEVEL1) {
                this.mDayType = DayType.LEVEL6;
            } else if (RT == RainType.LEVEL2) {
                this.mDayType = DayType.LEVEL4;
            } else if (RT == RainType.LEVEL3) {
                this.mDayType = DayType.LEVEL2;
            }
        } else if (ST == SunType.LEVEL3) { //big chance of sun
            if (RT == RainType.LEVEL1) {
                this.mDayType = DayType.LEVEL7;
            } else if (RT == RainType.LEVEL2) {
                this.mDayType = DayType.LEVEL5;
            } else if (RT == RainType.LEVEL3) {
                this.mDayType = DayType.LEVEL3;
            }
        }
    }

    /**
     * Getter for mDayType
     *
     * @return mDayType
     */
    public DayType getDay() {
        return mDayType;
    }

    /**
     * Getter for mNightType
     *
     * @return mNightType
     */
    public NightType getNight() {
        return mNightType;
    }
}























