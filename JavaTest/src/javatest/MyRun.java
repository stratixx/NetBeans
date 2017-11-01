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

public class MyRun implements Runnable {
 
    private int id;
 
    public MyRun(int id) {
        this.id = id;
    }
 
    @Override
    public void run() {
        while(true) {
            System.out.println("Watek "+id);
            try {
                //usypiamy wątek na 100 milisekund
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}