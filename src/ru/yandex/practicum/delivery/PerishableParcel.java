package ru.yandex.practicum.delivery;

public class PerishableParcel extends Parcel {
    private int timeToLive;
    private static final int PERISHABLE_COST = 3;

    public PerishableParcel(String description, int weight, String deliveryAddress, int sendDay, int timeToLive) {
        super(description, weight, deliveryAddress, sendDay, PERISHABLE_COST);
        this.timeToLive = timeToLive;
    }

    public Boolean isExpired(int currentDay) { //возвращает логическое значение, указывающее, испортилось ли содержимое посылки.
        return sendDay + timeToLive < currentDay;
    }
}
