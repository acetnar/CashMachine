package CashMachine.pin;

public class PinPresenter implements PinContract.Presenter {
    private final PinContract.View view;

    public PinPresenter(PinContract.View view) {
        this.view = view;
    }

    @Override
    public void onPinTyping(String pin) {
        //todo funkcja wywolywana w trakcie pisania
        //todo przycisk confirm jest dostepny tylko gdy użytkownik wpisał 4 cyfry

    }

    @Override
    public void onPinConfirmed(String pin) {
        //todo sprawdz czy wprowadzono pin 1234
    }

}
