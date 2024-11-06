public class AsusManufacturer extends Company {
    @Override
    public Component createComponent(String type) {

        // this violates the open-close principle
        Component component = null;

        if ("GPU".equals(type)) {
            component = new AsusGpu();
        } else {
            component = new AsusMonitor();
        }
        return component;
    }
}