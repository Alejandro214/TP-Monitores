package ar.edu.unq;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PowWorker extends Thread{

    public Buffer buffer;
    public TimeEjecucion timeEjecucion;

    public PowWorker(Buffer buffer,TimeEjecucion timeEjecucion) {
         this.buffer = buffer;
         this.timeEjecucion = timeEjecucion;
    }

    public void run() {
        Tuple lectura_buffer = buffer.read();
        long inicio = lectura_buffer.comienzo;
        long fin = lectura_buffer.fin;
        byte[] cadena = lectura_buffer.cadena;
        int dificultad = lectura_buffer.dificultad;
        while (!Main.nonce_encontrado && (inicio != fin)) {
            byte[] nonce_candidata = PowWorker.toBinary(inicio);
            byte[] string_mas_nonce = concat(cadena, nonce_candidata);
            byte[] result = union(string_mas_nonce);
            if (es_nonce_valido(result, dificultad)) {
                Main.nonce_encontrado = true;
                System.out.println("El nonce hallado es " + nonce_candidata);
            } else inicio++;
        }
        timeEjecucion.end_work_threads();
        timeEjecucion.print_total_time();
    }

        public static byte[] union(byte [] string_mas_nonce){
            byte[] result = new byte[32];
            try {
              result = MessageDigest.getInstance("SHA-256").digest(string_mas_nonce);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            return  result;
        }

    public static Boolean es_nonce_valido(byte[] nonce,int dificultad){
        boolean is_valido = true;
        for(int i =0;dificultad> i;i++){
            is_valido = is_valido && nonce[i] == 0;
        }
        return is_valido;
    }

    public static byte[] concat(byte[] a, byte[]b){
        byte[] c = new byte[a.length + b.length];
        System.arraycopy(a, 0, c, 0, a.length);
        System.arraycopy(b, 0, c, a.length, b.length);
        return  c;
    }

    public static byte[] toBinary(long number) {
        byte[] binary = new byte[32];
        int index = 31;
        long copyOfInput = number;
        while (copyOfInput > 0) {
            if(index >= 0) {
                binary[index--] = (byte) (copyOfInput % 2);
                copyOfInput = copyOfInput / 2;
            }
            else {
                copyOfInput = 0;
            }
        }
        return binary;
    }




}
