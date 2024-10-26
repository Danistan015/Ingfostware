/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

/**
 *
 * @author DANIELA SOTO
 */
public class Persona {
    private String Id_Persona;
    private String Nombre;

    public Persona(String Id_Persona, String Nombre) {
        this.Id_Persona = Id_Persona;
        this.Nombre = Nombre;
    }

    public String getId_Persona() {
        return Id_Persona;
    }

    public void setId_Persona(String Id_Persona) {
        this.Id_Persona = Id_Persona;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }
    
    
}
