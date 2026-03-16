package ru.yandex.practicum.delivery;

import java.util.ArrayList;
import java.util.List;

public class ParcelBox<T extends Parcel> {
    public List<T> parcelBox = new ArrayList<>();
    int maxBoxWeight;
    int currentBoxWeight;

    public ParcelBox(int maxBoxWeight) {
        this.maxBoxWeight = maxBoxWeight;
    }

    public void addParcelToBox(T parcel) {
        if ((currentBoxWeight + parcel.getWeight()) > maxBoxWeight) {
            System.out.println("Превышен максимально допустимый вес коробки! Посылка не может быть добавлена!");
        } else {
            parcelBox.add(parcel);
            currentBoxWeight += parcel.getWeight();
            System.out.println("Посылка добавлена в коробку");
        }
    }

    public void getAllParcelsFromBox() {
        if (parcelBox.isEmpty()) {
            System.out.println("В коробке нет посылок!");
        } else {
            System.out.println("В коробке лежат посылки: ");
            for (T parcel : parcelBox) {
                System.out.println(parcel.description);
            }
        }
    }

    public List<T> getParcelBox() {
        return parcelBox;
    }

}
