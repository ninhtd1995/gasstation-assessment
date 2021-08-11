package gasstation.model;

public class GasPumpPrice {
    private final GasType gasType;
    private double price;

    public GasPumpPrice(GasType gasType) {
        this.gasType = gasType;
    }

    public GasPumpPrice(GasType gasType, double price) {
        this.gasType = gasType;
        this.price = price;
    }

    public GasType getGasType() {
        return gasType;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
