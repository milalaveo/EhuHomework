package homeworks.Generics.entity;

public class Service extends Item {
    private Euro totalCost;
    private int numOfUsers;

    public Service(String name, Euro totalCost, int numOfUsers) {
        super(name);
        this.totalCost = totalCost;
        this.numOfUsers = numOfUsers;
    }

    @Override
    public Euro calculatePrice() {
        return this.totalCost.divideBy(this.numOfUsers);
    }
}