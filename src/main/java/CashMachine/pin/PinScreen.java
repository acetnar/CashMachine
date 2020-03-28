package CashMachine.pin;

import CashMachine.BaseSwingScreen;
import CashMachine.wrong.WrongPinScreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PinScreen extends BaseSwingScreen implements PinContract.View {
    private JPasswordField passwordField;
    private final JLabel message;
    private final JButton confirm;

    private final PinContract.Presenter presenter = new PinPresenter(this);
    private final ScreenListener listener;

    public PinScreen(ScreenListener listener) {
        this.listener = listener;
        //frame ma dost�p protected
        frame = new JFrame("Insert PIN");
        frame.setSize(400, 300);
        frame.setLayout(new GridLayout(0, 1));

        frame.add(new Label("Pin:"));
        passwordField = new JPasswordField();
        frame.add(passwordField);
        passwordField.addKeyListener(new KeyAdapter() {
            String password = "";

            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == '\b') {
                    if (password.length() != 0) {
                        password = password.substring(0, password.length() - 1);
                        presenter.onPinTyping(password);
                    }
                } else if (password.length() < 10) {
                    password += e.getKeyChar();
                    presenter.onPinTyping(password.trim());
                }
            }
        });


        message = new JLabel();
        frame.add(message);

        confirm = new JButton("Confirm");
        confirm.setEnabled(false);
        confirm.addActionListener(e -> presenter.onPinConfirmed(String.copyValueOf(passwordField.getPassword()).trim()));
        frame.add(confirm);

    }

    @Override
    public void disableConfirmButton() {
        confirm.setVisible(false);
    }

    @Override
    public void enableConfirmButton() {
        confirm.setVisible(true);
    }

    @Override
    public void showTooShortPinError() {
        message.setText("PIN is to short");
    }

    @Override
    public void showTooLongPinError() {
        message.setText("PIN is to long");
    }

    @Override
    public void showOnlyDigitsError() {
        message.setText("PIN can contains only digits");
    }

    @Override
    public void hideError() {
        message.setText("Correct PIN");
    }

    @Override
    public void correctPin() {
        listener.onCorrectPin();
    }

    @Override
    public void wrongPin() {
        listener.onWrongPin();
    }

    public interface ScreenListener {
        //info
        void onCorrectPin();

        void onWrongPin();
    }

}
