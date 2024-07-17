import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class UserInputApp {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Введите данные в произвольном порядке (Фамилия Имя Отчество ДатаРождения НомерТелефона Пол):");
            String input = scanner.nextLine();

            String[] inputArray = input.split(" ");
            if (inputArray.length != 6) {
                throw new IllegalArgumentException("Недостаточно данных или лишние данные.");
            }

            String lastName = inputArray[0];
            String firstName = inputArray[1];
            String middleName = inputArray[2];
            String birthDateStr = inputArray[3];
            long phoneNumber = Long.parseLong(inputArray[4]);
            char gender = inputArray[5].charAt(0);

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
            Date birthDate = dateFormat.parse(birthDateStr);

            String fileName = lastName + ".txt";

            File directory = new File("user_data");
            if (!directory.exists()) {
                directory.mkdir();
            }

            File userFile = new File(directory, fileName);
            BufferedWriter writer = new BufferedWriter(new FileWriter(userFile, true));

            String data = lastName + firstName + middleName + " " + dateFormat.format(birthDate) + " " + phoneNumber + gender;

            writer.write(data);
            writer.newLine();
            writer.close();

            System.out.println("Данные успешно записаны в файл " + fileName);
        } catch (IllegalArgumentException e) {
            System.err.println("Ошибка: " + e.getMessage());
        } catch (ParseException | NumberFormatException e) {
            System.err.println("Ошибка: Неверный формат данных.");
        } catch (IOException e) {
            System.err.println("Ошибка при записи в файл: " + e.getMessage());
        }
    }
}
