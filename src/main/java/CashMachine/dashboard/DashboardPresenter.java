package CashMachine.dashboard;


import CashMachine.model.Cash;
import CashMachine.model.CashMachineStorage;
import CashMachine.model.RandomMachineStorage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class DashboardPresenter implements DashboardContract.Presenter {
    private final DashboardContract.View view;
    private final CashMachineStorage machineStorage;

    public DashboardPresenter(DashboardContract.View view, CashMachineStorage machineStorage) {
        this.view = view;
        this.machineStorage = machineStorage;
    }

    @Override
    public void getCash(String value) {
        String amount = value.trim();
        if (amount.equals("0") || amount.length() == 0) {
            view.zeroCashError();
            view.disableConfirmButton();
        } else if (amount.matches("0-9")) {
            view.showOnlyDigitsError();
            view.disableConfirmButton();
        } else {
            view.enableConfirmButton();
            view.hideError();
            view.onWithdrawalConfirm(Arrays.asList(Cash.BANK_NOTE_50, Cash.BANK_NOTE_20));
        }


        //todo a co jak nie ma opowiednich banknot�w? (user chce 50 a jest 2x20 - za ma�o)
        //todo a co jak nie ma opowiednich banknot�w? (user chce 50 a jest 3x20 - kwota niepodzielna)
    }

    @Override
    public void insertCash(int value) {
        List<Cash> cash = machineStorage.availableMoney();
        int totalAmount = getTotalAmount(cash);
        totalAmount += value;
        
    }

    @Override
    public boolean isCoverageOnAccount(int value) {
        List<Cash> cash = machineStorage.availableMoney();
        int totalAmount = getTotalAmount(cash);
        return totalAmount - value >= 0;
    }

    private int getTotalAmount(List<Cash> cash) {
        int count = 0;
        for (Cash cash2 : cash)
            count += cash2.getWorth();
        return count;

    }

    @Override
    public void onCashConfirmation(int value) {
        if (isMultiplicitalyOfTheValueConfirmed(value)) {
            if (isCoverageOnAccount(value)) {
                List<Cash> cashToWithdraw = withdrawCash(value);
                view.onWithdrawalConfirm(cashToWithdraw);
            } else {
                view.lackOfFoundsOnWithdrawalError();
            }
        } else {
            view.onCashMultiplicityError();
        }

    }

    private List<Cash> withdrawCash(int value) {
        List<Cash> machineCash = machineStorage.availableMoney();
        List<Cash> cashToWithdraw = new ArrayList<>();

        machineCash.sort(Comparator.comparingInt(Cash::getWorth).reversed());

        withdrawCalculator(value, cashToWithdraw, machineCash);

        return cashToWithdraw;
    }

    private void withdrawCalculator(int value, List<Cash> toWithdraw, List<Cash> machineCash) {

        int totalOfWithdrawValue = 0;

        for (int i = 0; i < machineCash.size(); i++) {
            if (machineCash.get(i).getWorth() <= value && machineCash.get(i).getWorth() + totalOfWithdrawValue <= value){
                toWithdraw.add(machineCash.get(i));
                totalOfWithdrawValue = getTotalAmount(toWithdraw);
                if (totalOfWithdrawValue == value){
                    break;
                } else if (machineCash.get(i).getWorth() + machineCash.get(i + 1).getWorth() > value) {
                    toWithdraw.remove(machineCash.get(i));
                    totalOfWithdrawValue = getTotalAmount(toWithdraw);
                }
            }
        }

        System.out.println(totalOfWithdrawValue);

    }

    private boolean isMultiplicitalyOfTheValueConfirmed(int value) {
        return value % 10 == 0;
    }

    @Override
    public void onCheckBalanceConfirmation() {
        int balance = getTotalAmount(machineStorage.availableMoney());
        view.onCheckBalanceConfirm(balance);
    }

}
