package com.propaganda905.geneticai;

import com.amd.aparapi.Kernel;
import com.amd.aparapi.Range;

public class App 
{
    public static void main( String[] args )
    {

        final float[] result = new float[20];

        Kernel kernel = new Kernel(){
            @Override public void run(){
               int i= getGlobalId();
               result[i]=5;
            }
        };
        Range range = Range.create(result.length); 
        kernel.execute(range);
        System.out.println( "Result[0]: " + result[0] );
        kernel.dispose();
    }
}
