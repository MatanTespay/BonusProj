/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.awt.Toolkit;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;
import static utils.InputType.YEAR_RANGE;

/**
 *
 * @author asus
 */
public class MyDocFilter extends DocumentFilter {

    InputType type;
    int minYear;
    int maxYear;

    public MyDocFilter(InputType type) {
        this.type = type;
    }

    public MyDocFilter(int minYear, int maxYear) {
        this.type = YEAR_RANGE;
    }

    @Override
    public void insertString(FilterBypass fb, int offset, String string,
            AttributeSet attr) throws BadLocationException {

        Document doc = fb.getDocument();
        StringBuilder sb = new StringBuilder();
        sb.append(doc.getText(0, doc.getLength()));
        sb.insert(offset, string);

        if (test(sb.toString())) {
            super.insertString(fb, offset, string, attr);
        } else {

        }
    }

    private boolean test(String text) {
        if (text.isEmpty()) {
            return true;
        }

        try {
            switch (type) {
                case BYTE:
                    Byte.parseByte(text);
                    break;
                case CHAR1:
                    if (text.length()>1) {
                        Toolkit.getDefaultToolkit().beep();
                        return false;
                    }
                    break;
                case DOUBLE:
                    Double.parseDouble(text);
                    break;
                case FLOAT:
                    Float.parseFloat(text);
                case INT:
                    Integer.parseInt(text);
                    break;
                case LONG:
                    Long.parseLong(text);
                    break;
                case SHORT:
                    Short.parseShort(text);
                    break;
                case TEXT15:
                    if (text.length() > 15) {
                        Toolkit.getDefaultToolkit().beep();
                        return false;
                    }
                case TEXT20:
                    if (text.length() > 20) {
                        Toolkit.getDefaultToolkit().beep();
                        return false;
                    }
                case TEXT26:
                    if (text.length() > 26) {
                        Toolkit.getDefaultToolkit().beep();
                        return false;
                    }
                case TEXT50:
                    if (text.length() > 50) {
                        Toolkit.getDefaultToolkit().beep();
                        return false;
                    }
                case TEXT150:
                    if (text.length() > 150) {
                        Toolkit.getDefaultToolkit().beep();
                        return false;
                    }
//                    char[] chars = text.toCharArray();
//                    for (char c : chars) {
//                        if (!Character.isLetter(c) && c != ' ') {
//                            Toolkit.getDefaultToolkit().beep();
//                            return false;
//                        }
//                    }
                    break;
                case YEAR_RANGE:
                    int year = Integer.parseInt(text);
                    if (year < minYear || year > maxYear) {
                        Toolkit.getDefaultToolkit().beep();
                        return false;
                    }
                    break;
                case NAME:
                    if (!HelperClass.isNameOk(text.trim())) {
                        Toolkit.getDefaultToolkit().beep();
                        return false;
                    }
                    break;
                case PASSWORD:
                    if (!HelperClass.isPasswordOk(text.trim())) {
                        Toolkit.getDefaultToolkit().beep();
                        return false;
                    }
                    if (text.length() > 15) {
                        Toolkit.getDefaultToolkit().beep();
                        return false;
                    }
                    break;
            }
            return true;
        } catch (NumberFormatException e) {
            Toolkit.getDefaultToolkit().beep();
            return false;
        }
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text,
            AttributeSet attrs) throws BadLocationException {

        Document doc = fb.getDocument();
        StringBuilder sb = new StringBuilder();
        sb.append(doc.getText(0, doc.getLength()));
        sb.replace(offset, offset + length, text);

        if (test(sb.toString())) {
            super.replace(fb, offset, length, text, attrs);
        } else {
            // warn the user and don't allow the insert
        }
    }

    @Override
    public void remove(FilterBypass fb, int offset, int length)
            throws BadLocationException {
        Document doc = fb.getDocument();
        StringBuilder sb = new StringBuilder();
        sb.append(doc.getText(0, doc.getLength()));
        sb.delete(offset, offset + length);

        if (test(sb.toString())) {
            super.remove(fb, offset, length);
        } else {
            // warn the user and don't allow the insert
        }
    }
}
