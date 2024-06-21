package atm;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Jefferson
 */

import javax.swing.*;
import java.awt.*;
import javax.swing.border.Border;

public class DashedBorderLabel extends JLabel {
    // Constants for the dashed border
    private static final int DASH_LENGTH = 5;
    private static final int SPACE_LENGTH = 5;
    private static final BasicStroke DASHED = new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, DASH_LENGTH, new float[]{DASH_LENGTH}, 0.0f);
    
    private Font font;

    // Constructor for the DashedBorderLabel
    public DashedBorderLabel(String text, Font font) {
        super(text);
        this.font = font;
        setBorder(createDashedBorder());
        setFont(font);
        setHorizontalAlignment(SwingConstants.CENTER); // Center align the label text
    }

    // Create a dashed border using the DASHED stroke
    private Border createDashedBorder() {
        return BorderFactory.createStrokeBorder(DASHED);
    }
}

