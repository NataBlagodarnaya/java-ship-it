package ru.yandex.practicum;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.delivery.FragileParcel;
import ru.yandex.practicum.delivery.PerishableParcel;
import ru.yandex.practicum.delivery.StandardParcel;

import static org.junit.jupiter.api.Assertions.*;

public class DeliveryCostTest {

    @Test
    public void calculateDeliveryCost_StandardParcel_ReturnsCorrectValue() {
        //Подготовка
        StandardParcel sParcel = new StandardParcel(
                "Стандартная",
                4,
                "Москва",
                12);
        //Исполнение и проверка
        assertEquals(8, sParcel.calculateDeliveryCost());//вес 4 * базовая стоимость единицы 2
    }

    @Test
    public void calculateDeliveryCost_FragileParcel_ReturnsCorrectValue() {
        //Подготовка
        FragileParcel fParcel = new FragileParcel(
                "Хрупкая",
                4,
                "Москва",
                12);
        //Исполнение и проверка
        assertEquals(16, fParcel.calculateDeliveryCost()); //вес 4 * базовая стоимость единицы 4
    }

    @Test
    public void calculateDeliveryCost_PerishableParcel_ReturnsCorrectValue() {
        //Подготовка
        PerishableParcel pParcel = new PerishableParcel(
                "Скоропортящаяся",
                4,
                "Москва",
                12,
                7);
        //Исполнение и проверка
        assertEquals(12, pParcel.calculateDeliveryCost());//вес 4 * базовая стоимость единицы 3
    }
}
