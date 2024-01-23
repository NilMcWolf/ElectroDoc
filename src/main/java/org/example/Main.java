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
            String filePath = "C:\\Users\\dan_v\\Downloads\\Obrazets__kopia.docx";

            // Создаем объект FileInputStream для чтения файла
            FileInputStream fis = new FileInputStream(filePath);

            // Создаем объект XWPFDocument
            XWPFDocument document = new XWPFDocument(fis);

            // Заменяем текст
            replaceText(document, "000", "123");
            replaceText(document, "Игнатюк", "Шматюк");
            replaceText(document, "августа", "хуявгуста");

            // Сохраняем изменения
            FileOutputStream fos = new FileOutputStream(new File("C:\\Users\\dan_v\\Downloads\\ready.docx"));
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