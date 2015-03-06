/**
 * JRaD 2015
 * @author Pablo Puñal Pereira <pablo@punyal.com>
 * @version 0.1
 */

package com.punyal.jrad.core.test;

import com.punyal.jrad.*;
import com.punyal.jrad.core.*;
import com.punyal.jrad.core.radius.*;


public class MessageTester{
    
    public static void main(String[] args){
        System.out.println("# Test (START)");
        Request test = Request.newAccounting();
        System.out.print(Utils.messagePrint(test));        
        System.out.println("# Test (END)");
    }
    
}