package gasstation.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GasStationImplTest {
    @InjectMocks
    GasStationImpl gasStation;

    @Test
    public void getGasPumps() {
        gasStation.getGasPumps();

    }

}