package CashMachine.dashboard;



import CashMachine.model.Cash;

import java.util.List;

public class DashboardContract {

    public interface View {
        void onWithdrawalConfirm(List<Cash> money);
    }

    public interface Presenter {
        void getCash(int value);
        //todo interackja między ożyszkodnikiem a presenterem
        //todo zadbaj o testy!
    }
}
