
public class Singleton {

    private static volatile Singleton instance; // volatile ensures multiple threads will be able to handle the Singleton instance correctly
    // everytime we acess this variable we need to read it directly from the main memory
    @SuppressWarnings({"unused", "FieldMayBeFinal"})
    private String data;

    private Singleton(String data) {
        this.data = data;
    }

    //!1) nothing in this code prevents two threades from accessing this piece of code at the same time
    public static Singleton getInstance(String data) {
        Singleton result = instance; // micro-optz; avoid direct reads from memory
        // the usage of such a local variable can imporve the method overall performance by as much as 40%
        if (result == null) { // this will limit synchronization to the rare case of constructing a new instance of this field
            // this is double-checking wrapping
            //! a shared variable stored in the memory can reference a partially constructed object (language dependent)
            /*
             * simply  put, consider two threads, A and B. initially, A will acquire lock and create an instance
             * even when the instance is being created, it is not null
             * during this time, B might want to use the instance even though it is not fully initialized
             * thread B will not wait for initialization and use a partially initialized instance
             * this will result in a crash
             */
            synchronized (Singleton.class) { // to counter point !1); this is thread-safe implementation
                result = instance;
                // however, this adds unncessary overhead
                // can be solved by applying the double-check wrapping idiom
                /*
                 * everytime a thread tries to access this, it will have to wait for it turn 
                 */
                if (result == null) { // to guarantee the same instance return
                    instance = result = new Singleton(data);
                }
            }
        }

        return result;
    }
}
