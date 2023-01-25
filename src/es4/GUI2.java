/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es4;

/*
Creare un gioco in cui vi sia un tabellone di 10x10 bottoni.
Premendo su ciascuno di essi questo scompare, lasciando visualizzare un valore sottostante (casuale, compreso tra 1 e 99).
Quando saranno apparsi 3 valori dispari, il gioco terminerà.
Occorre visualizzare un contatore dei bottoni scomparsi.
Occorre creare un bottone di reset che faccia ricominciare il gioco.
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author gabriel.ricaldone
 */
public class GUI2 extends JFrame {

    boolean game = true;
    int contPari = 0;
    JButton[][] A = new JButton[6][6];
    JLabel[][] Testo = new JLabel[6][6];
    JButton riprova = new JButton("Riprova");
    JButton esci = new JButton("Esci");
    GestBottone S1 = new GestBottone();
    JLabel testoPari = new JLabel("0");

    GUI2() {
        initComponents();
    }

    private void initComponents() {
        this.setLayout(null);
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {

                A[i][j] = new JButton(":)");
                Testo[i][j] = new JLabel(String.valueOf((int) (Math.random() * 98 + 1)));
                A[i][j].addActionListener(S1);
                this.add(Testo[i][j]);
                Testo[i][j].setBounds(10 + (45 * j), 10 + (45 * i), 40, 30);
                this.add(A[i][j]);
                Testo[i][j].setVisible(false);
                A[i][j].setBounds(10 + (45 * j), 10 + (45 * i), 40, 30);
            }
        }
        testoPari.setBounds(300, 150, 100, 30);
        riprova.setBounds(300, 100, 100, 20);
        esci.setBounds(300, 200, 100, 20);
        riprova.addActionListener(S1);
        esci.addActionListener(S1);
        this.add(riprova);
        this.add(esci);
        this.add(testoPari);
        this.setLocation(200, 100);
        this.setSize(500, 400);
    }

    public static void main(String args[]) {

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI2().setVisible(true);
            }
        });
    }

    class GestBottone implements ActionListener {

        public void actionPerformed(ActionEvent E) {
            int j = 0, i, n = 0;

            if (game) {
                for (i = 0; i < 6; i++) {
                    for (j = 0; j < 6; j++) {
                        if (E.getSource() == A[i][j]) {
                            A[i][j].setVisible(false);
                            Testo[i][j].setVisible(true);
                            if (Integer.parseInt(Testo[i][j].getText()) % 2 == 1) {
                                game = false;
                            } else {
                                contPari++;
                                testoPari.setText(Integer.toString(contPari));
                            }
                        }
                    }
                }
            }

            if (E.getSource() == riprova) {
                for (i = 0; i < 6; i++) {
                    for (j = 0; j < 6; j++) {
                        A[i][j].setVisible(true);
                        Testo[i][j].setVisible(false);
                        Testo[i][j].setText(Integer.toString((int) (Math.random() * 98 + 1)));
                        

                    }
                }
                contPari = 0;
                testoPari.setText(Integer.toString(contPari));
                game = true;
            }

            if (E.getSource() == esci) {
                System.exit(0);
            }

        }
    }
}
