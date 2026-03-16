package ru.yandex.practicum;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.delivery.ParcelBox;
import ru.yandex.practicum.delivery.StandardParcel;

import static org.junit.jupiter.api.Assertions.*;

class AddParcelToBoxTest {

    private ParcelBox box;
    private StandardParcel sParcel1;

    @BeforeEach//Подготовка
    public void beforeEach() {
        box = new ParcelBox(10);
        sParcel1 = new StandardParcel(
                "Стандартная1",
                4,// 4<10
                "Москва",
                12);
    }

    @Test
    public void shouldAddParcelWhenWeightIsUnderLimit (){
        //Исполнение
        box.addParcelToBox(sParcel1);
        //Проверка
        assertTrue(box.getParcelBox().contains(sParcel1));
    }

    @Test
    public void shouldAddParcelWhenWeightIsExactlyEqualToLimit (){
        //Подготовка
        StandardParcel sParcel2 = new StandardParcel(
                "Стандартная2",
                6,// 4+6=10
                "Москва",
                12);
        //Исполнение
        box.addParcelToBox(sParcel1);
        box.addParcelToBox(sParcel2);
        //Проверка
        assertTrue(box.getParcelBox().contains(sParcel1));
        assertTrue(box.getParcelBox().contains(sParcel2));
    }

    @Test
    public void shouldNotAddParcelWhenWeightExceedsLimit (){
        //Подготовка
        StandardParcel sParcel2 = new StandardParcel(
                "Стандартная2",
                10,// 4+10>10
                "Москва",
                12);
        //Исполнение
        box.addParcelToBox(sParcel1);
        box.addParcelToBox(sParcel2);
        //Проверка
        assertTrue(box.getParcelBox().contains(sParcel1));
        assertFalse(box.getParcelBox().contains(sParcel2));
    }

    @Test
    public void shouldNotAddParcelIfWeightParcel1ExceedsLimit (){ /*Если вес самой первой посылки, которую пытаются
    добавить в коробку, уже превышает максимально допустимый для этой коробки*/
        //Подготовка
        StandardParcel sParcel1 = new StandardParcel(
                "Стандартная1",
                15,//15>10
                "Москва",
                12);
        //Исполнение
        box.addParcelToBox(sParcel1);
        //Проверка
        assertFalse(box.getParcelBox().contains(sParcel1));
    }
}