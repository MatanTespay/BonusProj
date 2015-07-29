package utils;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

/**
 * ColorComboBox is a GUI widget for selecting colors. It is a non-editable
 * drop-down combo box, and each entry prints the color name along with a
 * rectangle displaying the color.
 *
 * <P>
 * The default constructor populates the combo box with the "basic" colors
 * defined as constants of class <code>java.awt.Color</code>. Using
 * <code>ColorComboBox(boolean)</code>, an "extended" color list may be
 * selected.
 *
 * <P>
 * A future enhancement will allow a "custom" color entry to be added, which
 * will bring up the full-blown Swing color chooser dialog box to allow the user
 * to specify any color via HSB or RGB.
 *
 * <P>
 * <small>Software developed for the BaBar Detector at the SLAC B-Factory.
 * <br>Copyright &copy; 1998 California Institute of Technology.</small>
 *
 * @see	PlotFormatTabPanel
 *
 * @version	$Id: ColorComboBox.java,v 1.3 2000/05/19 17:43:41 serbo Exp $
 *
 * @author	Alex Samuel	(Apr 98; originator)
 */
public class ColorComboBox extends JComboBox {
// initializers & constructors

    /**
     * Creates a color combo box. If extended colors is selected, the combo box
     * is populated with a longer list of colors. Otherwise, the basic Java
     * colors in <code>java.awt.Color</code> are used.
     *
     */
    public ColorComboBox() {
        super(E_Colors.values());

        for (E_Colors color : E_Colors.values()) {
            _colors.add(color.getColor());
            _colorNames.add(color.name());
        }

        setEditable(false);
        setRenderer(new Renderer());
        _font = new Font("Dialog", 0, 10);

        setColor(_colors.get(0));

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                _currentColor = ((E_Colors) getSelectedItem()).getColor();
            }
        });
    }

// accessors
    /**
     * Returns the currently selected color.
     *
     * @return	the currently selected color
     */
    public Color getColor() {
        return (Color) getSelectedItem();
    }
    // public Color getColor()			{ return _currentColor; }

    /**
     * Sets the color selection to the specified color. If the color specified
     * is not a color obtained from a color combo box, the color is set instead
     * to the first color in the box.
     *
     * @param	color	the color to be selected
     */
    public void setColor(Color color) {
        _currentColor = (colorToIndex(color) == -1) ? _colors.get(0) : color;
        setSelectedItem(_currentColor);
    }

// helpers
    protected int colorToIndex(Color color) {
        for (int i = 0; i < _colors.size(); i++) {
            if (color.equals(_colors.get(i))) {
                return i;
            }
        }

        return -1;
    }

// data members
    protected Color _currentColor;
    protected Font _font;

    protected ArrayList<Color> _colors = new ArrayList<>();
    protected ArrayList<String> _colorNames = new ArrayList<>();

// inner classes
    protected class Renderer implements ListCellRenderer {

        @Override
        public Component getListCellRendererComponent(JList list,
                Object value, int index, boolean isSelected, boolean cellHasFocus) {
            return new ColorLabel(((E_Colors) value).getColor(), isSelected, !isEnabled());
        }
    }

    protected class ColorLabel extends JPanel {

        protected Color _color;
        protected boolean _isSelected;
        protected boolean _isDisabled;

        public ColorLabel(Color color, boolean isSelected, boolean isDisabled) {
            _color = color;
            _isSelected = isSelected;
            _isDisabled = isDisabled;

            this.setOpaque(true);
            this.setBackground(_isSelected ? Color.yellow
                    : ColorComboBox.this.getBackground());
        }

        @Override
        public void paint(Graphics g) {
            g.setColor(_isSelected
                    ? UIManager.getColor("ComboBox.selectedBackground")
                    : UIManager.getColor("ComboBox.background"));
            Rectangle r = this.getBounds();
            g.fillRect(0, 0, r.width, r.height);

            g.setFont(_font);
            FontMetrics metrics = g.getFontMetrics();

            if (_isDisabled) {
                g.setColor(UIManager.getColor("Label.disabled"));
            } else {
                g.setColor(ColorComboBox.this.getForeground());
            }

            int index = colorToIndex(_color);
            String colorName = (index == -1) ? "(unknown)"
                    : _colorNames.get(index);
            g.drawString(colorName, 2, metrics.getHeight());

            g.setColor(_color);
            g.fillRect(60, 2, 36, 11);
            g.setColor(ColorComboBox.this.getForeground());
            g.drawRect(60, 2, 36, 11);
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(102, 16);
        }
    }
}
