/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javatest;

/**
 *
 * @author Skrzatt
 */
import java.awt.*;
import javax.swing.*;
 
public class Display extends JFrame {
 
    public Display() {
        super("Hello World");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(275, 350);
        setLayout(new FlowLayout());        
    
	add(new JButton("Przycisk 1"));
	add(new JButton("Przycisk 2"));    
        
        
        setVisible(true);
    }
}
