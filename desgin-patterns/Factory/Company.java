public abstract class Company {
    public Gpu assembleGpu() {
        Gpu gpu = createGpu();
        gpu.assemble();
        return gpu;
    }

    public abstract Component creaComponent(String type); // factory method
}
