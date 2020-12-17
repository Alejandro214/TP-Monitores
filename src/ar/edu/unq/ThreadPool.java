package ar.edu.unq;

public class ThreadPool {

    Buffer buffer;
    PowWorker[] powWorkers;

    public ThreadPool(Buffer buffer, int powWorkers,TimeEjecucion timeEjecucion){
        this.buffer = buffer;
        this.powWorkers = new PowWorker[powWorkers];
        for (int i = 0; i < powWorkers; i++) {
            this.powWorkers[i] = new PowWorker(buffer,timeEjecucion);
            this.powWorkers[i].start();
        }
    }

}
