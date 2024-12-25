/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package shoppingoline.Utils;

import java.awt.Image;
import java.net.URL;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author ngocanh
 */
public class ActionUtils {

    public void showPanel(JPanel parentPanel, JPanel chillPanel) {
        parentPanel.removeAll();
        parentPanel.add(chillPanel);
        parentPanel.validate();
        chillPanel.validate();
    }

    public void fillImageOnline(String url, JLabel jlabel, int h, int w) {
        try {
            ImageIcon imageIcon = new ImageIcon(new URL(url));
            Image img = imageIcon.getImage();

            Image resizedImg = img.getScaledInstance(h, w, Image.SCALE_SMOOTH);
            imageIcon = new ImageIcon(resizedImg);
            jlabel.setIcon(imageIcon);
            jlabel.setHorizontalAlignment(SwingConstants.CENTER);
        } catch (Exception e) {

        }
    }
//100000000 =>1,000,000

    public String doubleToDecimal(double num) {
        NumberFormat currentLocale = NumberFormat.getInstance();
        return currentLocale.format(num);

    }

    public LocalDate strToDate(String date, String pattern) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern(pattern));
    }

    public String dateToStr(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

}
