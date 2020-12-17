package ar.edu.unq;

public class Buffer {
    private int N;
    private Tuple[] data;
    private int begin = 0, end = 0;


    public Buffer(int N){
        this.N = N;
        this.data = new Tuple[this.N + 1];
    }
    public synchronized void write ( Tuple o ) {
        while ( isFull ())
            try{
                wait ();
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        data [ begin ] =  o;
        begin = next ( begin );
        notifyAll ();
    }
    public synchronized Tuple read () {
        while ( isEmpty ())
            try {
                wait();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        Tuple result = data [ end ];
        end = next ( end );
        notifyAll ();
        return result ;
    }
    private boolean isEmpty () { return begin == end ; }
    private boolean isFull () { return next ( begin ) == end ; }
    private int next ( int i) { return (i +1) %( N +1); }
}
