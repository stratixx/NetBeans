/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Simulator.Tools;

/**
 *
 * @author Skrzatt
 */
abstract public class RefreshThread extends Thread {
    
    final private Controls controls;
    private Boolean started;
    private double frequency;
    
    protected class Controls
    {
        private Boolean run;
        private Boolean pause;
        
        public Controls()
        {
            run = false;
            pause = false;
        }
        
        synchronized public Boolean runProcedure( String command, Boolean newState)
        {
            if( command.equals("set") )
                run = newState;
            //run.notifyAll();
            return run;
        }
        
        synchronized public Boolean pauseProcedure( String command, Boolean newState)
        {
            if( command.equals("set") )
                pause = newState;
            //pause.notifyAll();
            return pause;
        }
    }
    
    abstract public void threadProcedure( double deltaT );
    
    public RefreshThread( double newFrequency)
    {
        frequency = newFrequency;
        controls = new Controls();
        started = false;
        this.setDaemon(true);
        this.setName("RefreshThread");
    }
    
    @Override
    public void run()
    {
        long startTime;
        //System.out.println("Simulator.View.RefreshThread.run() runned");
        try
        {    
            //System.out.println("Simulator.View.RefreshThread.run() try start");
            while( controls.runProcedure("get", Boolean.TRUE)==true )
            {
                //System.out.println("Simulator.View.RefreshThread.run() while loop");
                while( controls.pauseProcedure("get", Boolean.TRUE)==true )
                {
                    //System.out.println("Simulator.View.RefreshThread.run() pause wait start");
                    //controls.pause.wait();
                    //System.out.println("Simulator.View.RefreshThread.run() pause wait end");
                };
                //startTime = System.currentTimeMillis();;
                threadProcedure(1/frequency);
                //System.out.println("RefreshThread.run() name: "+this.getName()+" time: "+(System.currentTimeMillis()-startTime));
                Thread.sleep((long)(1000.0/frequency));
            } 
            //System.out.println("Simulator.View.RefreshThread.run() try end");
        }
        catch( Exception e )
        {
            System.out.println("Simulator.View.ViewRefreshThread.run() exception");
            e.printStackTrace();
        }
        System.out.println("Simulator.View.ViewRefreshThread.run() end");
    }
    
        
    synchronized public Boolean startedProcedure( String command, Boolean newState)
    {
        if( command.equals("set") )
            started = newState;
        return started;
    }
    
    public void startThread()
    {
        if( startedProcedure("get", true)==false )
        {
            startedProcedure("set", true);
            controls.runProcedure("set", true);
            controls.pauseProcedure("set", false);
            this.start();
        }
        else if( controls.pauseProcedure("get", true)==true )
        {
            controls.pauseProcedure("set", false);
        }
    }
    
    
    public void pauseThread()
    {
        controls.pauseProcedure("set", true);
    }
    
    
    public void stopThread()
    {
        controls.runProcedure("set", false);
    }
    
    /////////////////////////// Settery i gettery ///////////////////////////
            
    public Controls getControls()
    {
        return this.controls;
    }
}
