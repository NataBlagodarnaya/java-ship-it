package ru.yandex.practicum;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.delivery.PerishableParcel;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IsExpiredTest {
    private PerishableParcel pParcel;

    @BeforeEach //Подготовка
    public void beforeEach() {
        pParcel = new PerishableParcel(
                "Стандартная",
                4,
                "Москва",
                12,
                7);
    }

    @Test
    public void shouldReturnTrueWhenParcelIsExpired (){
        //Исполнение и проверка, берем 20й день - 1й день когда посылка испортилась (12+7=19)
        assertTrue(pParcel.isExpired(20));
    }

    @Test
    public void shouldReturnFalseWhenParcelIsFresh (){
        //Исполнение и проверка, 12+7=19
        assertFalse(pParcel.isExpired(19));
    }

    @Test
    public void shouldReturnFalseWhenParcelIsFreshCurrentDayBeforeSendDay (){
        //Исполнение и проверка, 12+7=19
        assertFalse(pParcel.isExpired(10));
    }
}