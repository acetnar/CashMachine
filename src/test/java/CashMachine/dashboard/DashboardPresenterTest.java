package CashMachine.dashboard;

import CashMachine.model.Cash;
import CashMachine.model.CashMachineStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DashboardPresenterTest {

    private DashboardContract.View view;
    private DashboardContract.Presenter presenter;
    private CashMachineStorage machineStorage;

    @BeforeEach
    public void setup() {
        view = mock(DashboardContract.View.class);
        machineStorage = mock(CashMachineStorage.class);
        presenter = new DashboardPresenter(view, machineStorage);
    }

    @Test
    public void getLastCash() {
        when(machineStorage.availableMoney()).thenReturn(Arrays.asList(Cash.BANK_NOTE_50, Cash.BANK_NOTE_20));

        presenter.getCash(50);

        verify(view).onWithdrawalConfirm(Arrays.asList(Cash.BANK_NOTE_50, Cash.BANK_NOTE_20));
    }

    //todo Testy do presentera


}
