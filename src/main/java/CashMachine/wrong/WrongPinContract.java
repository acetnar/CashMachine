package CashMachine.wrong;

public class WrongPinContract {

    public interface View {
        void confirmMessage();
    }

    public interface Presenter {
        void okClicked();
    }
}
