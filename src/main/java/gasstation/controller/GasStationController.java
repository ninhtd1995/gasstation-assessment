package gasstation.controller;

import gasstation.controller.request.BuyGasRequest;
import gasstation.controller.request.SetPriceRequest;
import gasstation.exceptions.GasTooExpensiveException;
import gasstation.exceptions.NotEnoughGasException;
import gasstation.model.GasPump;
import gasstation.model.GasType;
import gasstation.service.GasStation;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class GasStationController {
    @Autowired
    GasStation gasStation;

    @GetMapping("/gasPumps")
    public ResponseEntity<Collection<GasPump>> getGasPumps(){
        return new ResponseEntity<>(gasStation.getGasPumps(), HttpStatus.OK);
    }

    @GetMapping("/gasPump/getPrice/{gasType}")
    public ResponseEntity<Double> getGasPumpPrice(@PathVariable("gasType")GasType type) throws NotFoundException {
        return new ResponseEntity<>(gasStation.getPrice(type), HttpStatus.OK);
    }

    @PostMapping("/gasPumps")
    public ResponseEntity<Collection<GasPump>> addGasPumps(@RequestBody GasPump gasPump) throws NotFoundException {
        gasStation.addGasPump(gasPump);
        return new ResponseEntity<>(gasStation.getGasPumps(), HttpStatus.OK);
    }

    @PostMapping("/gasPumps/setPrice")
    public ResponseEntity<Collection<GasPump>> setPrice(@RequestBody SetPriceRequest setPriceRequest) throws NotFoundException {
        gasStation.setPrice(GasType.valueOf(setPriceRequest.getGasType()), setPriceRequest.getPrice());
        return new ResponseEntity<>(gasStation.getGasPumps(), HttpStatus.OK);
    }

    @PostMapping("/buyGas")
    public ResponseEntity<Double> addGasPumps(@RequestBody BuyGasRequest buyGasRequest) throws NotFoundException, NotEnoughGasException, GasTooExpensiveException {
        return new ResponseEntity<>(gasStation.buyGas(buyGasRequest.getType(), buyGasRequest.getAmountInLiters(), buyGasRequest.getMaxPricePerLiter()), HttpStatus.OK);
    }

    @GetMapping("/getRevenue")
    public ResponseEntity<Double> getRevenue(){
        return new ResponseEntity<>(gasStation.getRevenue(), HttpStatus.OK);
    }

    @GetMapping("/getNumberOfSales")
    public ResponseEntity<Integer> getNumberOfSales(){
        return new ResponseEntity<>(gasStation.getNumberOfSales(), HttpStatus.OK);
    }

    @GetMapping("/getNumberOfCancellationsNoGas")
    public ResponseEntity<Integer> getNumberOfCancellationsNoGas(){
        return new ResponseEntity<>(gasStation.getNumberOfCancellationsNoGas(), HttpStatus.OK);
    }

    @GetMapping("/getNumberOfCancellationsTooExpensive")
    public ResponseEntity<Integer> getNumberOfCancellationsTooExpensive(){
        return new ResponseEntity<>(gasStation.getNumberOfCancellationsTooExpensive(), HttpStatus.OK);
    }

}
