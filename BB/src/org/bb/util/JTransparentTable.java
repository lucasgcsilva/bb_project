package org.bb.util;

import java.awt.AlphaComposite;  
import java.awt.Graphics;  
import java.awt.Graphics2D;  
  
import javax.swing.JComponent;  
import javax.swing.JTable;  
  
/** 
* TODO javadoc me! 
*  
* @author Marco Biscaro 
*/  
public class JTransparentTable extends JTable {  
  
    private static final long serialVersionUID = 1L;  
    private float alpha;  
  
    /** 
     * Creates a new instance of JTransparentTable, setting the 
     * <code>alpha</code> value as <code>0.5f</code>. 
     */  
    public JTransparentTable() {  
        super();  
        alpha = 0.5f;  
    }  
  
    /** 
     * Sets the component opaque or transparent. If the parameter is 
     * <code>true</code>, the code has the same effect than: <br> 
     * <code> 
     * <pre> 
     * setAlpha(1.0f); 
     * </pre> 
     * </code> 
     */  
    @Override  
    public void setOpaque(boolean isOpaque) {  
        if (isOpaque) {  
            alpha = 1.0f;  
        }  
    }  
  
    /** 
     * @return true if the alpha value is 1.0f. False otherwise. 
     */  
    @Override  
    public boolean isOpaque() {  
        return alpha == 1f;  
    }  
  
    /** 
     * The getter for alpha. 
     *  
     * @return the alpha value. 
     */  
    public float getAlpha() {  
        return alpha;  
    }  
  
    /** 
     * The setter for alpha. 
     *  
     * @param alpha 
     */  
    public void setAlpha(float alpha) {  
        this.alpha = alpha;  
    }  
  
    /** 
     * Overrides the same method in {@linkplain JComponent} class. This 
     * implementation allows the transparent effect. 
     */  
    @Override  
    public void paintComponent(Graphics g) {  
        Graphics2D g2d = (Graphics2D) g;  
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,  
                alpha));  
        super.paintComponent(g2d);  
    }  
  
}  
