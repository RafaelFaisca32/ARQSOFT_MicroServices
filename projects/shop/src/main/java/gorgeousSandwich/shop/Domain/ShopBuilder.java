package gorgeousSandwich.shop.Domain;

import gorgeousSandwich.shop.Shared.domain.valueobjects.Schedule;
import gorgeousSandwich.shop.Shared.domain.valueobjects.Name;
import gorgeousSandwich.shop.Shared.exceptions.BusinessRuleViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShopBuilder {
    private static final Logger LOGGER = LoggerFactory.getLogger(ShopBuilder.class);
    private Long id;
    private int mondayOpening;
    private int mondayClosing;
    private int tuesdayOpening;
    private int tuesdayClosing;

    private int wednesdayOpening;
    private int wednesdayClosing;
    private int thursdayOpening;
    private int thursdayClosing;
    private int fridayOpening;
    private int fridayClosing;
    private int saturdayOpening;
    private int saturdayClosing;
    private int sundayOpening;
    private int sundayClosing;
    private String name;
    private String managerName;
    private Long managerId;


    public ShopBuilder() {
        mondayOpening = -1;
        mondayClosing = -1;
        tuesdayOpening = -1;
        tuesdayClosing = -1;
        wednesdayOpening = -1;
        wednesdayClosing = -1;
        thursdayOpening = -1;
        thursdayClosing = -1;
        fridayOpening = -1;
        fridayClosing = -1;
        saturdayOpening = -1;
        saturdayClosing = -1;
        sundayOpening = -1;
        sundayClosing = -1;
        name = null;
        id = null;
        managerName = null;
        managerId = null;
    }

    public ShopBuilder withMondayOpening(int openingHour) {
        this.mondayOpening = openingHour;
        return this;
    }

    public ShopBuilder withMondayClosing(int openingHour) {
        this.mondayClosing = openingHour;
        return this;
    }

    public ShopBuilder withTuesdayOpening(int openingHour) {
        this.tuesdayOpening = openingHour;
        return this;
    }

    public ShopBuilder withTuesdayClosing(int openingHour) {
        this.tuesdayClosing = openingHour;
        return this;
    }

    public ShopBuilder withWednesdayOpening(int openingHour) {
        this.wednesdayOpening = openingHour;
        return this;
    }

    public ShopBuilder withWednesdayClosing(int openingHour) {
        this.wednesdayClosing = openingHour;
        return this;
    }

    public ShopBuilder withThursdayOpening(int openingHour) {
        this.thursdayOpening = openingHour;
        return this;
    }

    public ShopBuilder withThursdayClosing(int openingHour) {
        this.thursdayClosing = openingHour;
        return this;
    }

    public ShopBuilder withFridayOpening(int openingHour) {
        this.fridayOpening = openingHour;
        return this;
    }

    public ShopBuilder withFridayClosing(int openingHour) {
        this.fridayClosing = openingHour;
        return this;
    }

    public ShopBuilder withSaturdayOpening(int openingHour) {
        this.saturdayOpening = openingHour;
        return this;
    }

    public ShopBuilder withSaturdayClosing(int openingHour) {
        this.saturdayClosing = openingHour;
        return this;
    }

    public ShopBuilder withSundayOpening(int openingHour) {
        this.sundayOpening = openingHour;
        return this;
    }

    public ShopBuilder withSundayClosing(int openingHour) {
        this.sundayClosing = openingHour;
        return this;
    }

    public ShopBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public Shop build() throws BusinessRuleViolationException {
        try {
            return new Shop(this.id, new DaySchedule(Schedule.of(mondayOpening, mondayClosing)),
                    new DaySchedule(Schedule.of(tuesdayOpening, tuesdayClosing)), new DaySchedule(Schedule.of(wednesdayOpening, wednesdayClosing)),
                    new DaySchedule(Schedule.of(thursdayOpening, thursdayClosing)), new DaySchedule(Schedule.of(fridayOpening, fridayClosing)),
                    new DaySchedule(Schedule.of(saturdayOpening, saturdayClosing)), new DaySchedule(Schedule.of(sundayOpening, sundayClosing)), Name.of(name),
                    new Manager(managerName, managerId));
        } catch (Exception e) {
            LOGGER.error("Could not build Shop", e);
            throw new BusinessRuleViolationException("Could not build shop", e);
        }
    }

    public ShopBuilder withId(Long id) {
        this.id = id;
        return this;
    }


    public ShopBuilder withManagerName(String managerName) {
        this.managerName = managerName;
        return this;
    }

    public ShopBuilder withManagerId(Long managerId) {
        this.managerId = managerId;
        return this;
    }
}
