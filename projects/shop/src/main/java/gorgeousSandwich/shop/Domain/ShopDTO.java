package gorgeousSandwich.shop.Domain;

public class ShopDTO {
    public Long id;
    public int mondayOpening;
    public int mondayClosing;
    public int tuesdayOpening;
    public int tuesdayClosing;

    public int wednesdayOpening;
    public int wednesdayClosing;
    public int thursdayOpening;
    public int thursdayClosing;
    public int fridayOpening;
    public int fridayClosing;
    public int saturdayOpening;
    public int saturdayClosing;
    public int sundayOpening;
    public int sundayClosing;
    public String name;

    public String managerName;

    public Long managerId;

    public ShopDTO(int mondayOpening, int mondayClosing, int tuesdayOpening, int tuesdayClosing, int wednesdayOpening,
                   int wednesdayClosing, int thursdayOpening, int thursdayClosing, int fridayOpening, int fridayClosing,
                   int saturdayOpening, int saturdayClosing, int sundayOpening, int sundayClosing, String name, Long id, String managerName, Long managerId) {
        this.mondayOpening = mondayOpening;
        this.mondayClosing = mondayClosing;
        this.tuesdayOpening = tuesdayOpening;
        this.tuesdayClosing = tuesdayClosing;
        this.wednesdayOpening = wednesdayOpening;
        this.wednesdayClosing = wednesdayClosing;
        this.thursdayOpening = thursdayOpening;
        this.thursdayClosing = thursdayClosing;
        this.fridayOpening = fridayOpening;
        this.fridayClosing = fridayClosing;
        this.saturdayOpening = saturdayOpening;
        this.saturdayClosing = saturdayClosing;
        this.sundayOpening = sundayOpening;
        this.sundayClosing = sundayClosing;
        this.name = name;
        this.id = id;
        this.managerId = managerId;
        this.managerName=managerName;
    }

    @Override
    public String toString() {
        return "ShopDTO{" +
                "id='" + id + '\'' +
                ", mondayOpening=" + mondayOpening +
                ", mondayClosing=" + mondayClosing +
                ", tuesdayOpening=" + tuesdayOpening +
                ", tuesdayClosing=" + tuesdayClosing +
                ", wednesdayOpening=" + wednesdayOpening +
                ", wednesdayClosing=" + wednesdayClosing +
                ", thursdayOpening=" + thursdayOpening +
                ", thursdayClosing=" + thursdayClosing +
                ", fridayOpening=" + fridayOpening +
                ", fridayClosing=" + fridayClosing +
                ", saturdayOpening=" + saturdayOpening +
                ", saturdayClosing=" + saturdayClosing +
                ", sundayOpening=" + sundayOpening +
                ", sundayClosing=" + sundayClosing +
                ", name='" + name + '\'' +
                ", managerName='" + managerName + '\'' +
                ", managerId='" + managerId + '\'' +
                '}';
    }
}
