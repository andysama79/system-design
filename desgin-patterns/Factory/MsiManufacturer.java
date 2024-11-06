public class MsiManufacturer extends Company {
    @Override
    public Component createComponent(String type) {

        // this violates the open-close principle
        Component component = null;

        if ("GPU".equals(type)) {
            component = new MsiGpu();
        } else {
            component = new MsiMonitor();
        }
        return component;
    }
}
