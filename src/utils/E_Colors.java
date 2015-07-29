/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.awt.Color;

public enum E_Colors {

    Black(35, 31, 32),
    Brown(176, 96, 15),
    Dark_blue(27, 63, 148),
    Dark_gray(76,87,95),
    Gray(149, 156, 161),
    Green(0, 132, 61),
    Light_blue(6, 157, 220),
    Light_green(132, 184, 25),
    Orange(244, 128, 38),
    Pink(243, 134, 160),
    Purple(151, 1, 94),
    Red(238, 46, 34),
    Turquoise(132, 205, 188),
    Yellow(254, 209, 5);

    private final int r;
    private final int g;
    private final int b;

    private E_Colors(final int r, final int g, final int b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public int getRed() {
        return r;
    }

    public int getGreen() {
        return g;
    }

    public int getBlue() {
        return b;
    }

    public String getRGB() {
        return r + ", " + g + ", " + b;
    }

    //Or even these
    public Color getColor() {
        return new Color(r, g, b);
    }

    public String getName(int r, int g, int b) {
        for (E_Colors color : E_Colors.values()) {
            if (color.getRed() == r && color.getGreen() == g && color.getBlue() == b) {
                return color.name();
            }
        }
        return null;
    }
}
