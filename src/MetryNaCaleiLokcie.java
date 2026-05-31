import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class MetryNaCaleiLokcie {
    public static void main(String[] args) {
        //Tworzymy okienko 
        JFrame ramka = new JFrame("Przelicznik metrów");
        ramka.setSize(700,80);
        ramka.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Tworzymy i dodajemy do okna panel 
        JPanel glownyPanel = new JPanel();
        ramka.add(glownyPanel);
        //na utworzonym panelu dodajemy:
        //etykietę 
        JLabel metryLabel = new JLabel("Metry:");
        glownyPanel.add(metryLabel);
        //pole tekstowe do wprowadzania długości w mwtrach 
        JTextField metry = new JTextField(10);
        glownyPanel.add(metry);
        //przycisk do przeliczania metrów na cale 
        JButton caleButton = new JButton("Przelicz na cale");
        glownyPanel.add(caleButton);
        //przycisk do przeliczania metrów na łokcie 
        JButton lokcieButton = new JButton("Przelicz na łokcie");
        glownyPanel.add(lokcieButton);
        //etykietę, w której zaprezentujemy wynik przeliczenia 
        JLabel wynik = new JLabel("Wynik:");
        glownyPanel.add(wynik);
        //pokazujemy oknienko 
        ramka.setVisible(true);

        //UWAGA: definiujemy tylko jednego słuchacza zdarzeń 
        //Będzie nasłuchiwał obu przycisków, 
        //to który przycisk zostanie wybrany rozpozna odczytując tekst 
        //wybranego przycisku za pomocą polecenia e.getActionCommand(); 
        ActionListener sluchaczObuPrzyciskow = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Float metryFloat = 0F; //zmienna do przechowywania metrów
                String wynikStr = ""; //tu będziemy wpisywać wyświetlane w polu wynik teksty
                //odczytanie wprowadzonej wartości w pole metry 
                //i próba zamiany tej wartości na liczbę 
                try{
                    String metryStr  = metry.getText();
                    metryFloat = Float.parseFloat(metryStr);
                    //jeżeli w polu metry nie wprowadzono liczby
                    // podczas próby konwersji na liczbę
                    //pojawi się wyjątek NumberFormatException
                }catch (NumberFormatException ex){
                    //w polu wynik wyświetlamy komunikat o błedzie 
                    wynikStr = "Metry to liczba rzeczywista! Popraw wartość!";
                    wynik.setText(wynikStr);
                    wynik.setForeground(Color.RED);
                    //czyścimy źle wprowadzoną wartość w polu metry 
                    metry.setText("");
                    //ustawiamy kursor (fokus) w polu metry 
                    //tak aby użytkownik mógł od razu wprowadzić nową wartość 
                    metry.requestFocus();
                    //był bład więc dalej nic nie liczymy tylko 
                    //kończymy działanie metody za pomocą return 
                    return;
                }
                //sprawdzamy który przycisk został wybrany 
                //e.getActionCommand() odczytuje napis na przycisku 
                String polecenie = e.getActionCommand();
                wynik.setForeground(Color.BLACK);
                //jezeli wybrano przycisk z napisem "Przelicz na cale" 
                if (polecenie.equals("Przelicz na cale")) {
                    float cale = metryFloat * 39.3701F; //przeliczmy na cale 
                    //tworzymy napis prezentowany w polu wynik 
                    wynikStr = String.format("%.2f metrów to %.2f cali", metryFloat, cale);
                    //prezentujemy wynik
                    wynik.setText(wynikStr);

                }
                //jezeli wybrano przycisk z napisem "Przelicz na łokcie" 
                if (polecenie.equals("Przelicz na łokcie")) {
                    float lokcie = metryFloat * 1.72F;//przeliczamy na łokcie 
                    //tworzymy napis prezentowany w polu wynik 
                    wynikStr = String.format("%.2f metrów to %.2f łokci", metryFloat, lokcie);
                    //prezentujemy wynik 
                    wynik.setText(wynikStr);
                }
            }
        };
        //UWAGA: do obu przycisków dodajemy tego samego słuchzacza zdarzeń 
        caleButton.addActionListener(sluchaczObuPrzyciskow);
        lokcieButton.addActionListener(sluchaczObuPrzyciskow);
    }
}