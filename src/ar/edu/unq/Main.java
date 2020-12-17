package ar.edu.unq;

import java.util.Scanner;

public class Main {
    public static boolean nonce_encontrado = false;

    public static void main(String[] args) {
        int cantDeThreads = pedirAlUsuarioCantDeThreads();
        int dificultad = pedirAlUsuarioDificultad();
        String cadenaDeCaracteres = pedirAlUsuarioCadena();
        byte[] cadena_transformada = cadenaDeCaracteres.getBytes();
        Buffer buffer = new Buffer(2);
        //Calcular el tiempo
        TimeEjecucion timeEjecucion = new TimeEjecucion(cantDeThreads);
        ThreadPool threadPool = new ThreadPool(buffer, cantDeThreads,timeEjecucion);
        timeEjecucion.set_inicio();
        llenar_bufer(buffer,cadena_transformada,nonce_encontrado,cantDeThreads,dificultad);


    }

    public static void llenar_bufer(Buffer buffer,byte[] cadena_transformada,boolean nonce_encontrado,int cantidad_threads,int dificultad){
        Tuple tuple = new Tuple();
        tuple.cadena = cadena_transformada;
        tuple.dificultad = dificultad;
        tuple.comienzo = 0;
        for(int i=0;cantidad_threads > i;i++){
            tuple.fin      = (long) Math.pow(2,32-(cantidad_threads - i -1)) -1;
            buffer.write(tuple);
            tuple.comienzo = tuple.fin +1;
        }

    }



    private static String pedirAlUsuarioCadena() {
        Scanner entradaEscaner = new Scanner (System.in); //Creación de un objeto Scanner
        System.out.println("Si lo desea, ingrese a continuacion algunos caracteres: ");
        String entradaTeclado = entradaEscaner.nextLine(); //Invocamos un método sobre un objeto Scanner
        return entradaTeclado;
    }

    private static int pedirAlUsuarioDificultad() {
        Scanner entradaEscaner = new Scanner (System.in); //Creación de un objeto Scanner
        int valorSeleccionado;
        do {
            System.out.println("Seleccione nivel de dificultad entre '2' o '3' o '4' golden");
            String entradaTeclado = entradaEscaner.nextLine(); //Invocamos un método sobre un objeto Scanner
            valorSeleccionado = Integer.parseInt(entradaTeclado);
        }
        while(3 != valorSeleccionado && 2 != valorSeleccionado && 4 != valorSeleccionado );
        return valorSeleccionado;
    }

    public static int pedirAlUsuarioCantDeThreads(){
        Scanner entradaEscaner = new Scanner (System.in); //Creación de un objeto Scanner
        System.out.println("Ingrese la cantidad de Threads a utilizar: ");
        String entradaTeclado = entradaEscaner.nextLine(); //Invocamos un método sobre un objeto Scanner
        return Integer.parseInt(entradaTeclado);
    }
}
