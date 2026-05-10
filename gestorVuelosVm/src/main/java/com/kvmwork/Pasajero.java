/* Record Pasajero (con orden natural)  
Qué es un record
Un record es un tipo especial de Java (desde Java 14) que sirve para guardar datos inmutables de forma súper compacta.

Genera automáticamente: campos final privados, constructor con todos los parámetros, getters con el mismo nombre que los campos,

equals, hashCode, toString útiles.  Es inmutable por diseño: una vez creado, no se puede cambiar.*/


package com.kvmwork;

import java.time.LocalDate;
import java.util.Comparator;

public record Pasajero(  //esto crea un record Pasajero inmutable con esos campos.
    String nombre,              // Estos son los componentes del record.  cada uno genera un campo final privado,
    String primerApellido,      // un getter public con el mismo nombre, aparecen en el constructor y en toString.
    String segundoApellido,
    LocalDate fechaNacimiento,
    Genero genero
) implements Comparable<Pasajero> {  // dice que este record puede compararse consigo mismo.
                                // El enunciado pide orden natural por nombre y apellidos → implemento Comparable.
    @Override
    public int compareTo(Pasajero otro) { // esto define cómo se compara un pasajero con otro
        // Primero por nombre
        int cmpNombre = this.nombre.compareTo(otro.nombre); //.compareTo(...), compara dos strings alfabéticamente.
        if (cmpNombre != 0) return cmpNombre;              // si el nombre ya decide el orden, para ahí.
 //int, tipo de dato entero.     cmpNombre, nombre de variable que yo elegí; significa “comparación de nombre”.
 /*  negativo si este nombre < otro nombre,  cero si son iguales,  positivo si este nombre > otro nombre*/
 /** calcula si mi nombre va antes, después o es igual que el nombre del otro pasajero, y guárdalo en una 
  * variable llamada cmpNombre*/       
                                    // Luego por primerApellido         
        int cmpPrimerApellido = this.primerApellido.compareTo(otro.primerApellido);//si los nombres eran iguales,
        if (cmpPrimerApellido != 0) return cmpPrimerApellido;                      // compara primerApellido
        
        // Finalmente por segundoApellido
        return this.segundoApellido.compareTo(otro.segundoApellido);
    }

}