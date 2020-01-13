package machine;

public class Machine {

    private Machine() {}

    private static Machine instance;

    public static Machine getInstance() {
        if (instance == null) {
            instance = new Machine();
        }
        return instance;
    }

    private int water =  400;
    private int milk = 540;
    private int coffee = 120;
    private int cups = 9;
    private int money = 550;

    public void displayStatus() {
        System.out.println("The coffee machine has:");
        System.out.println(this.water  + " of water");
        System.out.println(this.milk  + " of milk");
        System.out.println(this.coffee + " of coffee beans");
        System.out.println(this.cups   + " of disposable cups");
        System.out.println(this.money  + " of money");
    }

    public void pourCoffee(CoffeeType coffeeType) {
        try {
            checkResources(coffeeType);
        } catch (NotEnoughResourcesException e) {
            System.out.println(e.getMessage());
            return;
        }
        System.out.println("I have enough resources, making you a coffee!");
        this.water -= coffeeType.getWater();
        this.milk -= coffeeType.getMilk();
        this.coffee -= coffeeType.getCoffee();
        this.money += coffeeType.getPrice();
        cups--;
    }

    public void refillMachine(int water, int milk, int coffee, int cups) {
        this.water += water;
        this.milk += milk;
        this.coffee += coffee;
        this.cups += cups;
    }

    public int takeMoney() {
        int moneyToGive = this.money;
        this.money = 0;
        return moneyToGive;
    }

    private void checkResources(CoffeeType coffeeType) throws NotEnoughResourcesException {
        if (this.water - coffeeType.getWater() < 0) {
            throw new NotEnoughResourcesException("Sorry, not enough water!");
        }
        if (this.milk - coffeeType.getMilk() < 0) {
            throw new NotEnoughResourcesException("Sorry, not enough milk!");
        }
        if (this.coffee - coffeeType.getCoffee() < 0) {
            throw new NotEnoughResourcesException("Sorry, not enough coffee beans!");
        }
        if (this.cups == 0) {
            throw new NotEnoughResourcesException("Sorry, not enough cups!");
        }
    }





    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getWater() {
        return water;
    }

    public void setWater(int water) {
        this.water = water;
    }

    public int getMilk() {
        return milk;
    }

    public void setMilk(int milk) {
        this.milk = milk;
    }

    public int getCoffee() {
        return coffee;
    }

    public void setCoffee(int coffee) {
        this.coffee = coffee;
    }

    public int getCups() {
        return cups;
    }

    public void setCups(int cups) {
        this.cups = cups;
    }
}
