import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

class BusTicketBookingSystem9 extends JFrame implements ActionListener {
    private JTextField nameField, seatField;
    private JButton bookButton;
    private JTextArea outputArea;

    private List<Integer> availableSeats;
    private List<Ticket> bookedTickets;

    public BusTicketBookingSystem9() {
        setTitle("Bus Ticket Booking System");
        setLayout(new FlowLayout());

        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField(15);

        JLabel seatLabel = new JLabel("Select Seat (1-10):");
        seatField = new JTextField(5);

        bookButton = new JButton("Book Ticket");
        bookButton.addActionListener(this);

        outputArea = new JTextArea(10, 30);
        outputArea.setEditable(false);

        add(nameLabel);
        add(nameField);
        add(seatLabel);
        add(seatField);
        add(bookButton);
        add(outputArea);

        availableSeats = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            availableSeats.add(i);
        }

        bookedTickets = new ArrayList<>();

        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bookButton) {
            bookTicket();
        }
    }

    private void bookTicket() {
        String name = nameField.getText();
        String seatText = seatField.getText();

        if (name.isEmpty() || seatText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter both name and seat number.");
            return;
        }

        try {
            int seatNumber = Integer.parseInt(seatText);

            if (seatNumber < 1 || seatNumber > 10) {
                JOptionPane.showMessageDialog(this, "Seat number should be between 1 and 10.");
                return;
            }

            if (!availableSeats.contains(seatNumber)) {
                JOptionPane.showMessageDialog(this,
                        "Seat " + seatNumber + " is already booked. Please choose another seat.");
                return;
            }

            Ticket newTicket = new Ticket(name, seatNumber);

            bookedTickets.add(newTicket);

            availableSeats.remove(Integer.valueOf(seatNumber));

            String message = "Ticket booked for " + name + ". Seat Number: " + seatNumber + "\n";
            outputArea.append(message);

            nameField.setText("");
            seatField.setText("");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid seat number. Please enter a valid number.");
        }
    }

    private static class Ticket {
        private String passengerName;
        private int seatNumber;

        public Ticket(String passengerName, int seatNumber) {
            this.passengerName = passengerName;
            this.seatNumber = seatNumber;
        }

        public String getPassengerName() {
            return passengerName;
        }

        public int getSeatNumber() {
            return seatNumber;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BusTicketBookingSystem9());
    }
}
