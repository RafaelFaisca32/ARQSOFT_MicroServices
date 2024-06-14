package gorgeousSandwich.shop.Domain;

public class CreateShopDTO extends ShopDTO {
    public CreateShopDTO(int mondayOpening, int mondayClosing, int tuesdayOpening, int tuesdayClosing, int wednesdayOpening, int wednesdayClosing, int thursdayOpening, int thursdayClosing, int fridayOpening, int fridayClosing, int saturdayOpening, int saturdayClosing, int sundayOpening, int sundayClosing, String name, String managerName, Long managerId) {
        super(mondayOpening, mondayClosing, tuesdayOpening, tuesdayClosing, wednesdayOpening, wednesdayClosing, thursdayOpening, thursdayClosing, fridayOpening, fridayClosing, saturdayOpening, saturdayClosing, sundayOpening, sundayClosing, name, null, managerName, managerId);
    }
}
