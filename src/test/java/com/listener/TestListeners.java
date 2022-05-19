package com.listener;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

/**
Created By @Umesh Joshi
 **/

public class TestListeners implements ITestListener {

    @Override
    public void onTestStart(ITestResult result){
        System.out.println("Test started"+result.getName());
    }
    @Override
    public void onTestSuccess(ITestResult result){
        System.out.println("Test Sucessfully exceuted"+result.getName());
    }
    @Override
    public void onTestFailure(ITestResult result){
        System.out.println("Test Failed"+result.getName());
    }
    @Override
    public void onTestSkipped(ITestResult result){
        System.out.println("Test Skipped"+result.getName());
    }
    @Override
    public void onFinish(ITestContext context){
        System.out.println("All Test exceution fininshed "+context.getSuite().getAllMethods());
    }

}
