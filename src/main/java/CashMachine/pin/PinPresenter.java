package CashMachine.pin;

public class PinPresenter implements PinContract.Presenter {
    private final PinContract.View view;
    private String pin;
    private final String CORRECT_PIN = "1234";

    public PinPresenter(PinContract.View view) {
        this.view = view;
    }

    @Override
    public void onPinTyping(String pin) {
        this.pin = pin;
        checkPinLength(pin);
        onPinConfirmed(pin);
    }

    private void checkPinLength(String pin) {
        if (pin.length() > 4) {
            view.showTooLongPinError();
            view.disableConfirmButton();
        } else if (pin.length() < 4) {
            view.showTooShortPinError();
            view.disableConfirmButton();
        } else {
            checkIfPinIsDigitsOnly(pin);
        }
    }

    private void checkIfPinIsDigitsOnly(String pin) {
        if (pin.matches("0-9")) {
            view.enableConfirmButton();
        } else {
            view.disableConfirmButton();
            view.showOnlyDigitsError();
        }
    }

    @Override
    public void onPinConfirmed(String pin) {
        if (pin.equals(CORRECT_PIN.trim())) {
            view.enableConfirmButton();
            view.hideError();
            view.correctPin();
        } else {
            view.wrongPin();
        }
    }

}
