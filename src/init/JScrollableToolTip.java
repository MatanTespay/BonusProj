package init;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;

/**
 *The class represents scrollable jEditorPane the that is used in  {@link gui.ViewFlightTickets}} to show {@link core.FlightOrder.FlightTicket}} information

* @author Matan
*/
public class JScrollableToolTip extends JToolTip implements MouseWheelListener {
	
	   private JTextArea tipArea;
	   private JEditorPane jEditorPane;
	    
	    /** Creates a tool tip. */
	    public JScrollableToolTip(final int width, final int height,JButton btn) {
	        setPreferredSize(new Dimension(width, height));
	        setLayout(new BorderLayout());
	    
	       
	        this.jEditorPane = new JEditorPane();
	        jEditorPane.setEditable(false);
	        
	        HTMLEditorKit kit = new HTMLEditorKit();
	        jEditorPane.setEditorKit(kit);
	       
	        
	        LookAndFeel.installColorsAndFont(jEditorPane, 
	                "ToolTip.background",
	                "ToolTip.foreground",
	                "ToolTip.font");
	        
	        JScrollPane scrollpane = new JScrollPane(jEditorPane);
	        
	        
	        StyleSheet styleSheet = kit.getStyleSheet();
	        styleSheet.addRule("body {color:#000; font-family:times; margin: 4px; }");
	        styleSheet.addRule("span {  color:green; margin-left:10px; }");
	        //<p style="color:green;margin-left:20px;">This is a paragraph.</p>
//	        styleSheet.addRule("h2 {color: blue;}");
//	        styleSheet.addRule("h3 {color: #ff0000;}");
	        styleSheet.addRule("pre {font : 10px monaco; color : black; background-color : #fafafa; }");
	        
	        Document doc = kit.createDefaultDocument();
	        jEditorPane.setDocument(doc);
	       
	        
	        
	        scrollpane.setBorder(null);
	        scrollpane.getViewport().setOpaque(false);
	        add(scrollpane);
	     
	        
	    }
	    
	    @Override
	    public void addNotify() {
	        super.addNotify();
	        JComponent comp = getComponent();
	        if (comp != null) {
	            comp.addMouseWheelListener(this);
	        }
	    }
	 
	    @Override
	    public void removeNotify() {
	        JComponent comp = getComponent();
	        if(comp != null) {
	            comp.removeMouseWheelListener(this);
	        } 
	        super.removeNotify();
	    }
	    
	    public void mouseWheelMoved(final MouseWheelEvent e) {
	        JComponent comp = getComponent();
	        if(comp != null) {
	        	jEditorPane.dispatchEvent(new MouseWheelEvent(jEditorPane, 
	                    e.getID(), e.getWhen(), e.getModifiers(),
	                    0, 0, e.getClickCount(), e.isPopupTrigger(),
	                    e.getScrollType(), e.getScrollAmount(), e.getWheelRotation()));
	        }
	    }
	 
	    @Override
	    public void setTipText(final String tipText) {
	        
	    	String oldValue = this.jEditorPane.getText();
	    	jEditorPane.setText(tipText);
	    	jEditorPane.setCaretPosition(0);
	        firePropertyChange("tiptext", oldValue, tipText);
	    	
//	    	String oldValue = this.tipArea.getText();
//	        tipArea.setText(tipText);
//	        tipArea.setCaretPosition(0);
//	        firePropertyChange("tiptext", oldValue, tipText);
	    }
	 
	    @Override
	    public String getTipText() {
	        return jEditorPane == null ? "" : jEditorPane.getText();
	    }
	 
	    @Override
	    protected String paramString() {
	        String tipTextString = (jEditorPane.getText() != null ? jEditorPane.getText() : "");
	 
	        return super.paramString()
	                + ",tipText=" + tipTextString;
	    }
	

}
