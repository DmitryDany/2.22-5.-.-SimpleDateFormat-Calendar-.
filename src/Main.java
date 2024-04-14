
import java.text.ParseException;
import java.util.*;
import java.text.SimpleDateFormat;

public class Main {
    public static void main(String[] args) {
        int countDays = 45;
        Scanner in = new Scanner(System.in);
        System.out.println("Введите дату в формате dd.MM.yyyy:");
        String dataTextIn = in.nextLine();
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        Date dateFormat = new Date();
        try {
            dateFormat = formatter.parse(dataTextIn);
        }  catch (ParseException e) { //реагирует только на разделитель
            System.out.println("Введён неверный формат даты!");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateFormat);
        calendar.add(Calendar.DATE,countDays);
        System.out.println("Дата после увеличения на 45 дней: " + formatter.format(calendar.getTime()));

        calendar.setTime(dateFormat);
        calendar.clear(Calendar.MONTH);
        calendar.set(Calendar.DATE, 1);
        System.out.println("Дата после сдвига на начало года: " + formatter.format(calendar.getTime()));

        int businessDays = 10;
        calendar.setTime(dateFormat);
        List<Integer> noBusinessDays = Arrays.asList(
          Calendar.SATURDAY,
          Calendar.SUNDAY
        );
        for (int i = 0; i < businessDays;){
            calendar.add(Calendar.DAY_OF_MONTH,1);
            if (!noBusinessDays.contains(calendar.get(Calendar.DAY_OF_WEEK))) {
                i++;
            }
        }
        System.out.println("Дата после увеличения на 10 рабочих дней: " + formatter.format(calendar.getTime()));

        System.out.println("Введите вторую дату в формате dd.MM.yyyy: ");
        String dataSecondTextIn = in.nextLine();
        Date dateSecondFormat = new Date();
        try {
            dateSecondFormat = formatter.parse(dataSecondTextIn);
        }  catch (ParseException e) { //реагирует только на разделитель
            System.out.println("Введён неверный формат даты!");
        }
        int businessDaysBetween = 0;
        calendar.setTime(dateFormat);
        if (dateFormat.before(dateSecondFormat)){
            while (dateFormat.before(dateSecondFormat)){
                if (!noBusinessDays.contains(calendar.get(Calendar.DAY_OF_WEEK))) {
                    businessDaysBetween++;
                }
                calendar.add(Calendar.DATE,1);
                dateFormat = calendar.getTime();
            }
        } else {
            while (dateFormat.after(dateSecondFormat)) {
                if (!noBusinessDays.contains(calendar.get(Calendar.DAY_OF_WEEK))) {
                    businessDaysBetween++;
                }
                calendar.add(Calendar.DATE, -1);
                dateFormat = calendar.getTime();
            }
        }
        System.out.println("Количество рабочих дней между введенными датами:" + businessDaysBetween);
        in.close();
    }
}