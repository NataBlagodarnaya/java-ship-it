package ru.yandex.practicum.delivery;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class DeliveryApp<T extends Parcel & Trackable> {

    private static final Scanner scanner = new Scanner(System.in);
    private static List<ParcelBox> allParcels = new ArrayList<>();
    private static String description;
    private static int weight;
    private static String deliveryAddress;
    private static int sendDay;

    private static List<Trackable> trackableItems = new ArrayList<>();

    static final int MAX_STANDARD_BOX_WEIGHT = 50;
    static final int MAX_FRAGILE_BOX_WEIGHT = 25;
    static final int MAX_PERISHABLE_BOX_WEIGHT = 15;

    private static ParcelBox standardBox = new ParcelBox(MAX_STANDARD_BOX_WEIGHT);
    private static ParcelBox fragileBox = new ParcelBox(MAX_FRAGILE_BOX_WEIGHT);
    private static ParcelBox perishableBox = new ParcelBox<>(MAX_PERISHABLE_BOX_WEIGHT);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            showMenu();
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    addParcel();
                    System.out.println("_".repeat(20));//строка разделитель для удобства чтения в консоли
                    break;
                case 2:
                    sendParcels();
                    System.out.println("_".repeat(20));//строка разделитель для удобства чтения в консоли
                    break;
                case 3:
                    calculateCosts();
                    System.out.println("_".repeat(20));//строка разделитель для удобства чтения в консоли
                    break;
                case 4:
                    if (trackableItems.isEmpty()) {
                        System.out.println("Нет отправлений, поддерживающих трекинг!");
                        System.out.println("_".repeat(20));//строка разделитель для удобства чтения в консоли
                        continue;
                    } else {
                        for (Trackable item : trackableItems) {
                            System.out.println("Введите локацию");
                            String newLocation = scanner.nextLine();
                            item.reportStatus(newLocation);
                            System.out.println("_".repeat(20));//строка разделитель для удобства чтения в консоли
                        }
                        break;
                    }
                case 5:
                    inspectBoxByType ();
                    System.out.println("_".repeat(20));//строка разделитель для удобства чтения в консоли
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Неверный ввод! Необходимо ввести цифру от 0 до 5");//нет проверки на int (не изучали)
                    System.out.println("_".repeat(20));//строка разделитель для удобства чтения в консоли
            }
        }
    }

    private static void showMenu() {
        System.out.println("Выберите действие:");
        System.out.println("1 — Добавить посылку");
        System.out.println("2 — Отправить все посылки");
        System.out.println("3 — Посчитать стоимость доставки");
        System.out.println("4 — Отследить посылки, поддерживающие трекинг");
        System.out.println("5 — Показать содержимое выбранной коробки");
        System.out.println("0 — Завершить");
    }

    private static void addParcel() {
        // Подсказка: спросите тип посылки и необходимые поля, создайте объект и добавьте в allParcels
        while (true) { //добавить несколько посылок по очереди или отменить добавление и вернуться в главное меню
            showParcelType();
            int type = Integer.parseInt(scanner.nextLine());
            if (type == 0) {
                System.out.println("Отмена добавления");
                break;
            }
            if (type < 0 || type > 3) {
                System.out.println("Неверный ввод! Необходимо ввести цифру от 0 до 3");//нет проверки на int (не изучали)
                System.out.println("_".repeat(20));//строка разделитель для удобства чтения в консоли
                continue;
            }
            describeParcel();
            if (type == 1) {
                StandardParcel sParcel = new StandardParcel(description, weight, deliveryAddress, sendDay);
                if(!allParcels.contains(standardBox)) {
                    allParcels.add(standardBox);
                }
                standardBox.addParcelToBox(sParcel);
                System.out.println("Стандартная посылка добавлена в список.");
                System.out.println("_".repeat(20));//строка разделитель для удобства чтения в консоли

            } else if (type == 2) {
                FragileParcel fParcel = new FragileParcel(description, weight, deliveryAddress, sendDay);
                if(!allParcels.contains(fragileBox)) {
                    allParcels.add(fragileBox);
                }
                trackableItems.add(fParcel);
                fragileBox.addParcelToBox(fParcel);
                System.out.println("Хрупкая посылка добавлена в список и внесена в перечень отслеживаемых.");
                System.out.println("_".repeat(20));//строка разделитель для удобства чтения в консоли

            } else if (type == 3) {
                System.out.print("Введите срок годности (число дней): ");
                int timeToLive = Integer.parseInt(scanner.nextLine());
                PerishableParcel pParcel = new PerishableParcel(description, weight, deliveryAddress, sendDay, timeToLive);
                if(!allParcels.contains(perishableBox)) {
                    allParcels.add(perishableBox);
                }
                perishableBox.addParcelToBox(pParcel);
                System.out.println("Скоропортящаяся посылка добавлена в список.");
                System.out.println("_".repeat(20));//строка разделитель для удобства чтения в консоли
            }
        }
    }

    private static void showParcelType() {
        System.out.println("Выберите тип посылки для добавления:");
        System.out.println("1 — стандартная");
        System.out.println("2 — хрупкая");
        System.out.println("3 — скоропортящаяся");
        System.out.println("0 — отменить добавление");
    }

    private static void describeParcel() {
        System.out.print("Введите описание посылки: ");
        description = scanner.nextLine();
        while (true) {
            System.out.print("Введите вес посылки (целое число кг ,больше 0): ");
            int scan = Integer.parseInt(scanner.nextLine());
            if (scan <= 0) {
                System.out.println("Неверный ввод! Вес должен быть больше 0");//нет проверки на int (не изучали)
                System.out.println("_".repeat(20));//строка разделитель для удобства чтения в консоли
            } else {
                weight = scan;
                break;
            }
        }
        System.out.print("Введите адрес доставки: ");
        deliveryAddress = scanner.nextLine();
        while (true) {
            System.out.print("Введите день отправки (число от 1 до 30): ");
            int scan = Integer.parseInt(scanner.nextLine());
            if (scan < 1 || scan > 30) {//допускаем что в месяце 30 дней
                System.out.println("Неверный день отправки!");//нет проверки на int (не изучали)
                System.out.println("_".repeat(20));//строка разделитель для удобства чтения в консоли
            } else {
                sendDay = scan;
                break;
            }
        }
    }

    private static void sendParcels() {
        // Пройти по allParcels, вызвать packageItem() и deliver()
        if(allParcels.isEmpty()) {
            System.out.println("В списке нет посылок для отправки!");
            return;
        }
        for (ParcelBox box : allParcels) {
            List <? extends Parcel> parcelBox = box.getParcelBox();
            for (Parcel parcel : parcelBox) {
                parcel.packageItem();
                parcel.deliver();
            }
            System.out.println("_".repeat(20));//строка разделитель для удобства чтения в консоли
        }
        System.out.println("Все посылки отправлены!");
        allParcels.clear();
        standardBox = new ParcelBox(MAX_STANDARD_BOX_WEIGHT);
        fragileBox = new ParcelBox(MAX_FRAGILE_BOX_WEIGHT);
        perishableBox = new ParcelBox<>(MAX_PERISHABLE_BOX_WEIGHT);
    }

    private static void calculateCosts() {
        // Посчитать общую стоимость всех доставок и вывести на экран
        if(allParcels.isEmpty()) {
            System.out.println("В списке нет посылок для расчета!");
            return;
        }
        int totalCost = 0;
        for (ParcelBox box : allParcels) {
            List<? extends Parcel> parcelBox = box.getParcelBox();
            for (Parcel parcel : parcelBox) {
                totalCost += parcel.calculateDeliveryCost();
            }
        }
        System.out.println("Общая стоимость всех доставок: " + totalCost);
    }

    private static void showBoxType() {
        System.out.println("Какую коробку Вы хотите просмотреть?");
        System.out.println("1 - со стандартными посылками.");
        System.out.println("2 - с хрупкими посылками.");
        System.out.println("3 - со скоропортящимися посылками.");
        System.out.println("0 - не просматривать");
    }

    private static void inspectBoxByType () {
        while (true) {
            showBoxType();
            int type = Integer.parseInt(scanner.nextLine());
            if (type == 0) {
                System.out.println("Отмена просмотра");
                break;
            }
            if (type < 0 || type > 3) {
                System.out.println("Неверный ввод! Необходимо ввести цифру от 0 до 3");//нет проверки на int (не изучали)
                System.out.println("_".repeat(20));//строка разделитель для удобства чтения в консоли
                continue;
            }
            if (type == 1) {
                standardBox.getAllParcelsFromBox();
                System.out.println("_".repeat(20));//строка разделитель для удобства чтения в консоли
            }
            if (type == 2) {
                fragileBox.getAllParcelsFromBox();
                System.out.println("_".repeat(20));//строка разделитель для удобства чтения в консоли
            }
            if (type == 3) {
                perishableBox.getAllParcelsFromBox();
                System.out.println("_".repeat(20));//строка разделитель для удобства чтения в консоли
            }
        }
    }
}

/* проверять что посылка не испортится во время доставки - для этого нужно знать срок доставки.
группировать коробки не только по типу посылки, но и по дате отправки
если коробка переполняется нужно создавать еще одну и складывать посылки в новую. Сейчас не понятно эту посылку мы
тогда и в список не должны добавлять и не отправлять?
что делать если 1 посылка больше по весу чем максимально может вместить коробка? - делать еще одну категорию посылок
 - крупногабаритные
 */