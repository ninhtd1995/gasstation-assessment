package gasstation.controller.request;

import gasstation.model.GasType;

public class BuyGasRequest {
    private GasType type;
    private double amountInLiters;
    private double maxPricePerLiter;

    public GasType getType() {
        return type;
    }

    public void setType(GasType type) {
        this.type = type;
    }

    public double getAmountInLiters() {
        return amountInLiters;
    }

    public void setAmountInLiters(double amountInLiters) {
        this.amountInLiters = amountInLiters;
    }

    public double getMaxPricePerLiter() {
        return maxPricePerLiter;
    }

    public void setMaxPricePerLiter(double maxPricePerLiter) {
        this.maxPricePerLiter = maxPricePerLiter;
    }
}
