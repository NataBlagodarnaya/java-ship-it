package ru.yandex.practicum.delivery;

public abstract class Parcel {
    //добавьте реализацию и другие необходимые классы
    protected String description;
    protected int weight;
    private String deliveryAddress;
    protected int sendDay;
    private int cost;

    public Parcel(String description, int weight, String deliveryAddress, int sendDay, int cost) {
        this.description = description;
        this.weight = weight;
        this.deliveryAddress = deliveryAddress;
        this.sendDay = sendDay;
        this.cost = cost;
    }

    public int getCost() {
        return cost;
    }

    public int getWeight() {
        return weight;
    }

    protected void packageItem() {
        System.out.println("Посылка <<" + description + ">> упакована");
    }

    protected void deliver() {
        System.out.println("Посылка <<" + description + ">> доставлена по адресу " + deliveryAddress);
    }

    public Integer calculateDeliveryCost() { //стоимость доставки
        return weight * getCost();
    }
}
