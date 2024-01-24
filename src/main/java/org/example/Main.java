package org.example;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFPicture;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFPictureData;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        try {
            // Путь к вашему .docx файлу
            String filePath = "C:\\Users\\Fripo\\Desktop\\JAVA\\Lesozavodsk\\Obraz.docx";

            // Создаем объект FileInputStream для чтения файла
            FileInputStream fis = new FileInputStream(filePath);

            // Создаем объект XWPFDocument
            XWPFDocument document = new XWPFDocument(fis);

            // Заменяем текст
            replaceText(document, "000", "123");                                                             // номер договораа
            replaceText(document, "11", "14");                                                               // число
            replaceText(document, "августа", "декабря");                                                      // месяц
            replaceText(document, "Игнатюк", "Ткачук");                                                   // Ф
            replaceText(document, "Виктор", "Алексей");                                                       // И
            replaceText(document, "Георгиевич", "Васильевич");                                             // О
            replaceText(document, "Суперпупер", "действующий");                                              // действующая | действующий
            replaceText(document, "Диджей", "05 03");                                                        // серия паспорта
            replaceText(document, "437576", "952371");                                                       // номер паспорта
            replaceText(document, "Кириллица", "Лесозаводским ГОВД Приморского края от 23.07.2004г");
            // 1 вариант - Лесозаводским ГОВД Приморского края от 04.07.2001г
            // 2 вариант - Отделением УФМС Росси по Приморскому краю в Лесозаводском городском округе от 05.05.2016г
            // 3 вариант - УМВД России по Приморскому краю от 02.08.2019г
            replaceText(document, "021В139335577", "021В267708178");                                          // счетчик
            replaceText(document, "Дуперсупер", "именуемый");                                                 //именуемая | именуемый
            replaceText(document, "Чкалова", "Карьерная");                                                       // Улица
            replaceText(document, "47", "2");                                                               // Номер дома




            // Сохраняем изменения
            FileOutputStream fos = new FileOutputStream(new File("C:\\Users\\Fripo\\Desktop\\JAVA\\Lesozavodsk\\Gotovoe\\Ткачук.docx"));
            document.write(fos);

            // Закрываем потоки
            fos.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void replaceText(XWPFDocument doc, String oldText, String newText) {
        // Перебираем параграфы документа
        for (XWPFRun run : doc.getParagraphs().stream().flatMap(paragraph -> paragraph.getRuns().stream())
                .toArray(XWPFRun[]::new)) {
            String text = run.getText(0);

            // Если текст содержит искомую подстроку, заменяем её
            if (text != null && text.contains(oldText)) {
                text = text.replace(oldText, newText);
                run.setText(text, 0);
            }
        }
    }
}