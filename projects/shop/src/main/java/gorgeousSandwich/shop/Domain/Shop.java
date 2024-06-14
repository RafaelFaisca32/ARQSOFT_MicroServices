package gorgeousSandwich.shop.Domain;

import gorgeousSandwich.shop.Shared.domain.valueobjects.Name;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    private DaySchedule monday;
    @OneToOne(cascade = CascadeType.ALL)
    private DaySchedule tuesday;
    @OneToOne(cascade = CascadeType.ALL)
    private DaySchedule wednesday;
    @OneToOne(cascade = CascadeType.ALL)
    private DaySchedule thursday;
    @OneToOne(cascade = CascadeType.ALL)
    private DaySchedule friday;
    @OneToOne(cascade = CascadeType.ALL)
    private DaySchedule saturday;
    @OneToOne(cascade = CascadeType.ALL)
    private DaySchedule sunday;

    @Embedded
    private Name name;

    @Embedded
    private Manager manager;

    protected Shop(Long id, DaySchedule monday, DaySchedule tuesday, DaySchedule wednesday, DaySchedule thursday, DaySchedule friday, DaySchedule saturday, DaySchedule sunday, Name name, Manager manager) {
        this.id = id;
        this.monday = monday;
        this.tuesday = tuesday;
        this.wednesday = wednesday;
        this.thursday = thursday;
        this.friday = friday;
        this.saturday = saturday;
        this.sunday = sunday;
        this.name = name;
        this.manager = manager;
    }
}
