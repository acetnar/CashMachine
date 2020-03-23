package CashMachine.dashboard;


import CashMachine.model.CashMachineStorage;
import CashMachine.model.RandomMachineStorage;

/*
TODO * zadania z gwiazdk�!
 */
public class DashboardPresenter implements DashboardContract.Presenter {
    private final DashboardContract.View view;
    private final CashMachineStorage machineStorage;


    //todo dostepne pieniadze w bankomacie
    public DashboardPresenter(DashboardContract.View view, CashMachineStorage machineStorage) {
        this.view = view;
        this.machineStorage = machineStorage;
    }

    @Override
    public void getCash(int value) {
        //todo zakladamy ze user ma nielimitowane konto

        //todo wydaj pieniadze
//        view.onWithdrawalConfirm(Arrays.asList(Cash.BANK_NOTE_50, Cash.BANK_NOTE_20));

        //todo a co jak nie ma opowiednich banknot�w? (user chce 50 a jest 2x20 - za ma�o)
        //todo a co jak nie ma opowiednich banknot�w? (user chce 50 a jest 3x20 - kwota niepodzielna)
    }

    //todo DashboardContract

}
