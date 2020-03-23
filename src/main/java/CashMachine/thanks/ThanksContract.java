package CashMachine.thanks;




import CashMachine.model.Cash;

import java.util.List;

public class ThanksContract {

    public interface View {
        void showMeTheMoney(List<Cash> withdrawal);

        void confirmMessage();
    }

    public interface Presenter {
        void init();
        void okClicked();
    }
}
