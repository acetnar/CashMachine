package CashMachine.pin;

import CashMachine.BaseSwingScreen;
import CashMachine.wrong.WrongPinScreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PinScreen extends BaseSwingScreen implements PinContract.View {
    private JPasswordField passwordField;
    private final JLabel message;
    private final JButton confirm;

    //todo zamiast null przekaz this - co powinna implementowac ta klasa? - czy dobrze?????
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
        //todo ZROBIONE??? nas�uchuj wpisywania hasla i daj znac presenterowi
        passwordField.addActionListener(e -> presenter.onPinTyping(passwordField.getSelectedText()));
        frame.add(passwordField);

        message = new JLabel();//todo kontrolka do komunikat�w o bledzie
        message.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                presenter.onPinTyping(message.getText());
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        frame.add(message);

        confirm = new JButton("Confirm");
//        confirm.addActionListener(new ActionListener(passwordField) {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                presenter.onPinTyping(passwordField.getSelectedText().);
        // confirm.addActionListener(listener.onCorrectPin(""));

        //todo daj znac presenter o kliknieciu zamiast prosto do listener
        // listener.onCorrectPin();
//                listener.onWrongPin();

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
