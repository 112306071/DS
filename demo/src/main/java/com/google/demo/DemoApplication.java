package com.google.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.google.demo.Service.GoogleService;

import java.io.IOException;
@SpringBootApplication
public class DemoApplication 
{
	
    public static void main(String[] args) 
    {
        try 
        {
            /*
             * Using different keyword depends on the last number of your student ID
             * 0,1:Tomato
             * 2,3:Liver
             * 4,5:Pokemon
             * 6,7:Tissue
             * 8,9:Process
             */
        	SpringApplication.run(DemoApplication.class, args);
            System.out.println(new GoogleService("","Tomato").query());
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }
}     









