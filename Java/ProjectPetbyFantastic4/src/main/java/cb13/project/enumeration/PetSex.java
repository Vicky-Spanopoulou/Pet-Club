/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cb13.project.enumeration;

/**
 *
 * @author tsepe
 */
public enum PetSex {
    MALE,FEMALE,OTHER;
    
    public static PetSex[] getAll(){
        return PetSex.values();
    }
}
