/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cgk.game.system;

/**
 *
 * @author cgmcandrews
 */
public class Asset {
    String fileName;
    Class<?> myClass;
    
    public Asset(String fileName, Class<?> aClass){
        myClass = aClass;
        this.fileName = fileName;
    }
    
    public String getFileName(){
        return fileName;
    }
    
    public Class<?> getAssetClass(){
        return myClass;
    }
}
