package ar.edu.unq;

public class TimeEjecucion {
    long conteo_threads = 0;
    long Tfin = 0;
    long Tinicio = 0;

    public TimeEjecucion(long conteo_threads){
        this.conteo_threads = conteo_threads;
    }
    public void print_total_time(){
        if (conteo_threads == 0){
            Tfin =  System.currentTimeMillis();
            long tiempo = Tfin - Tinicio; //Calculamos los milisegundos de diferencia
            System.out.println("Tiempo de ejecucion en milisegundos: " + tiempo);
        }
    }

    public void set_inicio(){
        Tinicio = System.currentTimeMillis();
    }
    public long actual_time(){
        Tfin = System.currentTimeMillis();
        return Tfin - Tinicio;
    }
    public void end_work_threads(){
        System.out.println("Soy un thread power termine en el tiempo " + this.actual_time() );
        conteo_threads -=1;
    }

}
