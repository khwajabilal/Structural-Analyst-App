package com.example.khwajabilal.structural_a8.Beam;


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.Serializable;

/**
 *
 * @author Kamran
 * Class to store member data.
 */
public class Member implements Serializable {
    //coordinates of near end
    private Joint N;
    //coordinates of far end
    private Joint F;
    //modulus of elasticity
    private double E=1;
    //cross sectional area
    private double I=1;
    //Length 
    private double L=1;

    public double getE() {
        return E;
    }

    public void setE(double E) {
        this.E = E;
    }

    public double getI() {
        return I;
    }

    public void setI(double I) {
        this.I = I;
    }

    public double getL() {
        return L;
    }

    public void setL(double L) {
        this.L = L;
    }
    
    
    public Member(){
        
    }
    public Member(Joint near, Joint far){
        this.N=near;
        this.F=far;
        
    } 
    

    public Joint getN() {
        return N;
    }

    public void setN(Joint N) {
        this.N = N;
    }

    public Joint getF() {
        return F;
    }

    public void setF(Joint F) {
        this.F = F;
    }
    
    public String toString(){
        return(this.getN().getX()+","+this.getN().getY()
                +","+this.getF().getX()+","+this.getF().getY());
         
    }
}
