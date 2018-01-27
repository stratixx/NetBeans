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
    private long frequency;
    
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
        
        public void setRun( Boolean newRun )
        {
            runProcedure("set", newRun);
        }
        
        public Boolean isRun()
        {
            return runProcedure("get", true);
        }
        
        public void setPaused( Boolean newPause )
        {
            pauseProcedure("set", newPause);
        }
        
        public Boolean isPaused()
        {
            return pauseProcedure("get", true);
        }
    }
    
    abstract public void threadProcedure( long currTime );
    
    public RefreshThread( long newFrequency)
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
        long calculationTime = 0;
        long currTime;
        //System.out.println("Simulator.View.RefreshThread.run() runned");
        try
        {    
            //System.out.println("Simulator.View.RefreshThread.run() try start");
            while( controls.isRun() )
            {
                //System.out.println("Simulator.View.RefreshThread.run() while loop");
                while( controls.isPaused() )
                    Thread.sleep(10);
                
                currTime = System.currentTimeMillis();
                threadProcedure(currTime);
                calculationTime = System.currentTimeMillis() - currTime;
                //System.out.println("RefreshThread.run() name: "+this.getName()+" time: "+(System.currentTimeMillis()-startTime));
                if( ((1000/frequency)-calculationTime)>0 )                
                    Thread.sleep((1000/frequency)-calculationTime);
            } 
            //System.out.println("Simulator.View.RefreshThread.run() try end");
        }
        catch( Exception e )
        {
            System.out.println("Simulator.View.ViewRefreshThread.run() exception");
            e.printStackTrace();
        }
        System.out.println("Simulator.View.ViewRefreshThread.run() "+this.getName()+": end");
    }
    
        
    synchronized public Boolean startedProcedure( String command, Boolean newState)
    {
        if( command.equals("set") )
            started = newState;
        return started;
    }
    
    
    public void startThread()
    {
        if( isStarted()==false )
        {
            setStarted(true);
            controls.setRun(true);
            this.start();
        }
        else if( controls.pauseProcedure("get", true)==true )
        {
            controls.pauseProcedure("set", false);
        }
    }
    
    
    public void pauseThread()
    {
        controls.setPaused(true);
    }
    
    
    public void stopThread()
    {
        controls.setRun(false);
    }
    
    /////////////////////////// Settery i gettery ///////////////////////////
            
    public Controls getControls()
    {
        return this.controls;
    }
        
    public Boolean isStarted()
    {
        return startedProcedure("get", true);
    }

    private void setStarted( Boolean newStarted )
    {
        startedProcedure("set", newStarted);
    }
}
