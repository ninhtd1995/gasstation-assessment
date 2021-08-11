package gasstation.service.impl;


import gasstation.exceptions.GasTooExpensiveException;
import gasstation.exceptions.NotEnoughGasException;
import gasstation.model.GasPump;
import gasstation.model.GasPumpPrice;
import gasstation.model.GasType;
import gasstation.service.GasStation;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class GasStationImpl implements GasStation {
    private final String NOT_EXIST_MESSAGE = "Data is not exist";
    private int numberOfSales = 0;
    private int numberOfCancellationsNoGas = 0;
    private int numberOfCancellationsTooExpensive = 0;
    private double totalRevenue = 0;
    List<GasPump> gasPumpList = new ArrayList<>();
    List<GasPumpPrice> gasPumpPriceList = new ArrayList<>();

    @Override
    public void addGasPump(GasPump pump) {
        GasPump currentGasPump = getGasPumpByGasType(pump.getGasType());
        if (currentGasPump == null) {
            gasPumpList.add(pump);
            gasPumpPriceList.add(new GasPumpPrice(pump.getGasType()));
        }
    }

    @Override
    public Collection<GasPump> getGasPumps() {
        return gasPumpList;
    }

    @Override
    public double buyGas(GasType type, double amountInLiters, double maxPricePerLiter) throws NotEnoughGasException, GasTooExpensiveException, NotFoundException {
        GasPump gasPump = getGasPumpByGasType(type);
        if (gasPump == null) {
            throw new NotFoundException(NOT_EXIST_MESSAGE);
        }

        if (gasPump.getRemainingAmount() < amountInLiters) {
            numberOfCancellationsNoGas++;
            throw new NotEnoughGasException();
        }

        if (getPrice(type) > maxPricePerLiter) {
            numberOfCancellationsTooExpensive++;
            throw new GasTooExpensiveException();
        }

        gasPump.pumpGas(amountInLiters);
        totalRevenue += amountInLiters * maxPricePerLiter;
        numberOfSales++;
        return amountInLiters * maxPricePerLiter;
    }

    @Override
    public double getRevenue() {
        return totalRevenue;
    }

    @Override
    public int getNumberOfSales() {
        return numberOfSales;
    }

    @Override
    public int getNumberOfCancellationsNoGas() {
        return numberOfCancellationsNoGas;
    }

    @Override
    public int getNumberOfCancellationsTooExpensive() {
        return numberOfCancellationsTooExpensive;
    }

    @Override
    public double getPrice(GasType type) throws NotFoundException {
        GasPumpPrice gasPumpPrice = getGasPumpPriceByGasType(type);
        if (gasPumpPrice == null) {
            throw new NotFoundException(NOT_EXIST_MESSAGE);
        }
        return gasPumpPrice.getPrice();
    }

    @Override
    public void setPrice(GasType type, double price) throws NotFoundException {
        GasPumpPrice gasPumpPrice = getGasPumpPriceByGasType(type);
        if (gasPumpPrice == null) {
            throw new NotFoundException(NOT_EXIST_MESSAGE);
        }
        gasPumpPrice.setPrice(price);
    }

    private GasPumpPrice getGasPumpPriceByGasType(GasType type) {
        for (GasPumpPrice gasPumpPrice : gasPumpPriceList) {
            if (gasPumpPrice.getGasType().equals(type)) {
                return gasPumpPrice;
            }
        }
        return null;
    }

    private GasPump getGasPumpByGasType(GasType type) {
        for (GasPump gasPump : gasPumpList) {
            if (gasPump.getGasType().equals(type)) {
                return gasPump;
            }
        }
        return null;
    }
}
