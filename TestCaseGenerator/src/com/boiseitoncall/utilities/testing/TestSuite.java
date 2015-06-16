/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boiseitoncall.utilities.testing;

import com.boiseitoncall.utilities.testing.TestCaseAspect;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author robinso3
 */
public class TestSuite {
    private String name;
    private String desc;
    private List<TestCaseAspect> aspects;
    private List<String> testCases;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<TestCaseAspect> getAspects() {
        return aspects;
    }

    public void setAspects(List<TestCaseAspect> aspects) {
        this.aspects = aspects;
    }

    public List<String> getTestCases() {
        return testCases;
    }

    public void setTestCases(List<String> testCases) {
        this.testCases = testCases;
    }
    
    public void addAspect(TestCaseAspect newAspect){
        this.aspects.add(newAspect);
    }
    
    public List<String> getAspectNames(){
        List aspectNames = new ArrayList<>();
        Iterator<TestCaseAspect> i = aspects.listIterator();
        while (i.hasNext()){
            aspectNames.add(i.next().getName());
            }
        return aspectNames;
    }
            
    
}
