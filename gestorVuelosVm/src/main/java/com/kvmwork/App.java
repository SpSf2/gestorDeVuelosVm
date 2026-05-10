package com.kvmwork;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class App {
	public static void main(String[] args) {
	    List<Vuelo> vuelos = new ArrayList<>();
	    
	    // ✅ Pasajeros con constructor normal (record)
	    Pasajero p1 = new Pasajero("Carlos", "Martinez", "Lopez", 
	        LocalDate.of(1998, 3, 14), Genero.MASCULINO);
	    
	    Pasajero p2 = new Pasajero("Ana", "Gomez", "Ruiz", 
	        LocalDate.of(1995, 7, 21), Genero.FEMENINO);
	    
	    Pasajero p3 = new Pasajero("Maria", "Perez", "Garcia", 
	        LocalDate.of(1985, 1, 5), Genero.FEMENINO);
	    
	    Pasajero p4 = new Pasajero("Juan", "Rodriguez", "Sanchez", 
	        LocalDate.of(2000, 11, 22), Genero.MASCULINO);
	    
	    Pasajero p5 = new Pasajero("Lucia", "Lopez", "Martinez", 
	        LocalDate.of(1992, 4, 18), Genero.FEMENINO);
	    
	    Pasajero p6 = new Pasajero("Pedro", "Gonzalez", "Hernandez", 
	        LocalDate.of(1978, 9, 3), Genero.MASCULINO);

	    // ✅ Vuelos con builder (clase normal)
	    Vuelo vuelo1 = Vuelo.builder()
	        .destino("París")
	        .precio(199.99)
	        .fechaSalida(LocalDate.of(2026, 5, 11))
	        .horaSalida(LocalTime.of(8, 30))
	        .fechaLlegada(LocalDate.of(2026, 6, 10))
	        .horaLlegada(LocalTime.of(11, 45))
	        .numeroPlazas(3)
	        .pasajeros(List.of(p1, p2))
	        .build();
	        
	    Vuelo vuelo2 = Vuelo.builder()
	        .destino("Nueva York")
	        .precio(899.50)
	        .fechaSalida(LocalDate.of(2026, 6, 12))
	        .horaSalida(LocalTime.of(14, 0))
	        .fechaLlegada(LocalDate.of(2026, 6, 13))
	        .horaLlegada(LocalTime.of(6, 30))
	        .numeroPlazas(3)
	        .pasajeros(List.of(p3, p4, p5))
	        .build();
	        
	    Vuelo vuelo3 = Vuelo.builder()
	        .destino("Tokio")
	        .precio(1299.99)
	        .fechaSalida(LocalDate.of(2026, 6, 15))
	        .horaSalida(LocalTime.of(23, 0))
	        .fechaLlegada(LocalDate.of(2026, 6, 17))
	        .horaLlegada(LocalTime.of(16, 45))
	        .numeroPlazas(3)
	        .pasajeros(List.of(p6))
	        .build();
	    
	    vuelos.add(vuelo1);
	    vuelos.add(vuelo2);
	    vuelos.add(vuelo3);
	    
	    System.out.println("Vuelos creados:");
	    vuelos.forEach(vuelo -> 
	        System.out.println(vuelo.getDestino() + " (" + 
	            vuelo.getPasajeros().size() + "/" + 
	            vuelo.getNumeroPlazas() + " plazas)")
	    );
	    // Ejercicio preliminar: “Recuperar el vuelo que tiene más pasajeros”.
	    
	    													   // Optional<Vuelo>
	    Optional<Vuelo> vueloConMasPasajeros = vuelos.stream() // contenedor que puede tener un Vuelo o estar vacío.
	    	    .max(Comparator.comparingInt(vuelo -> vuelo.getPasajeros().size()));// vuelo.getPasajeros().size()
// Comparator.comparingInt(...), crea un comparador basado en un entero.            número de pasajeros de ese vuelo.

	    	vueloConMasPasajeros.ifPresent(vuelo ->   // .ifPresent, “si hay resultado, haz esto”.
	    	    System.out.println("\nVuelo con más pasajeros: " + 
	    	        vuelo.getDestino() + " (" + 
	    	        vuelo.getPasajeros().size() + " pasajeros)")  // cuántos pasajeros tiene.
	    	);
	    	
	    // Punto 1: VUELOS QUE TIENEN EL NUMERO DE PLAZAS COMPLETOS
	    	List<Vuelo> vuelosCompletos = vuelos.stream()
	    		    .filter(vuelo -> vuelo.getPasajeros().size() == vuelo.getNumeroPlazas())
	    		    .toList();
/* filtra solo los que cumplen condición. Si la cantidad de pasajeros  =  plazas máximas del vuelo. Agrega a new List*/
	    		System.out.println("\nVuelos con plazas completas:");
	    		vuelosCompletos.forEach(vuelo ->
	    		    System.out.println("- " + vuelo.getDestino() + " (" + 
	    		        vuelo.getPasajeros().size() + "/" + 
	    		        vuelo.getNumeroPlazas() + " plazas)")
	    		);
	     // Punto 2: vuelos con fecha de salida para el día de hoy
	    	List<Vuelo> vuelosDeHoy = vuelos.stream()
	    		    .filter(vuelo -> vuelo.getFechaSalida().isEqual(LocalDate.now()))
	    		    .toList();
/* si vuelos con fecha de salida = fecha actual, agrega a la new List */
	    		System.out.println("Vuelos de hoy:");
	    		vuelosDeHoy.forEach(vuelo ->   // para cada vuelo imprime el Destino y la hora de salida
	    		    System.out.println("- " + vuelo.getDestino() + " a las " + 
	    		        vuelo.getHoraSalida())
	    		);
	     // Punto 3: vuelos cuya duración sea mayor de 10 horas
	    		
	    	List<Vuelo> vuelosLargos = vuelos.stream()
	    		    .filter(vuelo -> ChronoUnit.HOURS.between( //unidad de horas. .between(inicio, fin), calcula diferencia.
	    		        vuelo.getFechaSalida().atTime(vuelo.getHoraSalida()), // añade hora → LocalDateTime.
	    		        vuelo.getFechaLlegada().atTime(vuelo.getHoraLlegada())
	    		    ) > 10)
	    		    .toList();

	    		System.out.println("Vuelos de más de 10 horas:");
	    		vuelosLargos.forEach(vuelo ->
	    		    System.out.println("- " + vuelo.getDestino() + " (" + 
	    		        ChronoUnit.HOURS.between(  
	    		            vuelo.getFechaSalida().atTime(vuelo.getHoraSalida()),
	    		            vuelo.getFechaLlegada().atTime(vuelo.getHoraLlegada())
	    		        ) + " horas)")
	    		);
	    //	Punto 4: pasajeros que han volado más de una vez
	    		
    		Map<Pasajero, Long> repeticiones = vuelos.stream()
    		    .flatMap(vuelo -> vuelo.getPasajeros().stream())	// junta todos los pasajeros de todos los vuelos 
    		    .collect(Collectors.groupingBy(						// en una sola tubería
    		        Function.identity(),            // agrupa por pasajero. usa al propio objeto como clave.
    		        Collectors.counting()           // cuenta cuántas veces aparece cada pasajero.
    		    ));
    		// Lista solo con los pasajeros repetidos.                           
    		List<Pasajero> pasajerosRepetidos = repeticiones.entrySet().stream() // Recorremos el mapa.
    		    .filter(entry -> entry.getValue() > 1)   // Nos quedamos con los que tienen contador mayor que 1.
    		    .map(Map.Entry::getKey)                  // Sacamos solo el pasajero, no el número de veces.
    		    .toList();                               // Guardamos el resultado en una lista.

	    		System.out.println("Pasajeros que han volado más de una vez:");
	    		pasajerosRepetidos.forEach(System.out::println);
	    		
					    /*  Mejor versión compacta
				Si quieres hacerlo en una sola cadena:
				List<Pasajero> pasajerosRepetidos = vuelos.stream()
				    .flatMap(v -> v.getPasajeros().stream())
				    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
				    .entrySet().stream()
				    .filter(e -> e.getValue() > 1)
				    .map(Map.Entry::getKey)
				    .toList();*/
	    		
	    		
	}
	    
}
