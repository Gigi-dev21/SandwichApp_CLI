package User.Classes;

abstract class Ingredient {
    protected String name;
    protected double price;

    public Ingredient(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String toString() {
        return name + " ($" + price + ")";
    }
}

// Premium Ingredient types
class Meat extends Ingredient {
    public Meat(String name, double price) {
        super(name, price);
    }
}

class Cheese extends Ingredient {
    public Cheese(String name, double price) {
        super(name, price);
    }
}

// Regular Toppings
class Topping extends Ingredient {
    public Topping(String name) {
        super(name, 0); // regular toppings are free
    }
}

// Sauces (usually free)
class Sauce extends Ingredient {
    public Sauce(String name) {
        super(name, 0);
    }
}

// Sides
class Side extends Ingredient {
    public Side(String name) {
        super(name, 0);
    }
}

// Bread
class Bread extends Ingredient {
    public Bread(String name, double price) {
        super(name, price);
    }
}




