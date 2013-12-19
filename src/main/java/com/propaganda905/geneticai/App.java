package com.propaganda905.geneticai;

import com.propaganda905.geneticai.kernels.Basic;

public class App 
{
    public static void main( String[] args )
    {
        App app = new App();
        app.runKernel();
    }
    
    public void runKernel(){
        Basic kernel = new Basic();
        kernel.setup();
        kernel.run();

        System.out.println( "Result[0]: " + kernel.result[0] );
        kernel.dispose();
    }
}