/*
 * 
 */
package com.boiseitoncall.utilities.npigen;

import java.util.ArrayList;
import java.util.List;
import org.kohsuke.args4j.*;

/**
 *
 * @author 
 */
public class Args4JOptionsOptionsBean {
    
    @Option(name="-processraw",usage="ingest the >5GB CMS.org NPPES archive")
    private boolean processraw = true;

    @Option(name="-f",usage="Find an NPI")
    private boolean find = true;


    @Option(name="-h",usage="usage can have new lines in it\n and also it can be long")
    private int num;

    // receives other command line parameters than options
    @Argument
    private List arguments = new ArrayList();

}
