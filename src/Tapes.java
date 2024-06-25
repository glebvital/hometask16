import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Tapes extends JFrame implements MouseListener, ActionListener{
    private int labelY = 60;
    private JTextField currentLabel;
    JTextField text = new JTextField();
    JTextField tutorial = new JTextField("       click on tape to edit, if you'd to delete tape, edit it so it is empty and left the tape");
    JButton exitButton = new JButton("X");

    Tapes(){
        setLayout(null);
        setSize(600,600);
        setResizable(false);
        ui();
        setVisible(true);
    }
    private void ui(){
        text.setBounds(50,10,400,50);
        add(text);
        JButton button = new JButton("add");
        button.setBounds(470,10,100,50);
        button.addActionListener(this);
        add(button);

        JButton openTutorial = new JButton("?");
        openTutorial.setBounds(500,150,50,50);
        openTutorial.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                add(tutorial);
                repaint();
            }
        });
        add(openTutorial);
        tutorial.setEditable(false);
        tutorial.setBackground(Color.WHITE);
        tutorial.setBounds(50,100,450,300);
        tutorial.add(exitButton);
        exitButton.setBounds(400,0,50,50);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(exitButton);
                remove(tutorial);
                repaint();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!text.getText().isEmpty()) {
                JTextField label = new JTextField(text.getText().trim());
                label.setEditable(false);
                label.setBorder(BorderFactory.createEmptyBorder());
                label.setBounds(10, labelY, label.getText().length() * 10, 50);
                label.addMouseListener(this);
                label.setOpaque(true);
                add(label);
                labelY += 55;
                text.setText("");
                if (labelY>600){
                    setSize(600,getHeight()+60);
                }
                repaint();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (currentLabel!=null){
            currentLabel.setForeground(Color.BLUE);
            currentLabel.setEditable(true);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
            currentLabel = (JTextField) e.getSource();
            currentLabel.setForeground(Color.RED);
            repaint();

    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (!currentLabel.getText().isEmpty()){
            currentLabel.setSize(currentLabel.getText().length()*10,50);
            currentLabel.setForeground(Color.BLACK);
            currentLabel.setEditable(false);
            repaint();
            currentLabel = null;
        }else {
            labelY-=55;
            currentLabel.setBackground(Color.GRAY);
            currentLabel.setEditable(false);
            remove(currentLabel);
            currentLabel = null;
            if (labelY<getHeight()&&labelY>600){
                setSize(600,getHeight()-60);
            }
        }

    }
}
