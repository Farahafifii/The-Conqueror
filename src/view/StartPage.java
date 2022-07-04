package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
//import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

//import com.sun.tools.doclint.Messages.Group;

import engine.Game;

@SuppressWarnings({ "serial", "unused" })
public class StartPage extends JLabel implements ActionListener {
    private JButton start;
    private JRadioButton cairo;
    private JRadioButton sparta;
    private JRadioButton rome;
    private JLabel choose;
    private JLabel player;
    private JTextField playN;
    private Main parentView;
   // private Game game;
    private ButtonGroup group;
    
	
	 public StartPage(Main parentView) {
		   this.parentView = parentView;
		   
		   this.setIcon(new ImageIcon("gamfinal1.png"));
		   
		   start = new JButton();
		   start.setBounds(100, 800, 247, 117);
		   start.setIcon(new ImageIcon("startbutton.jpg"));
		   start.addActionListener(this);
		   this.add(start);
		   
		   cairo = new JRadioButton("Cairo");
		   cairo.setBounds(200, 350, 130, 40);
		   cairo.addActionListener(this);
		   cairo.setActionCommand("Cairo");
		   cairo.setBackground(Color.WHITE);
		   this.add(cairo);
		   
		   rome = new JRadioButton("Rome");
		   rome.setBounds(820, 350, 130, 40);
		   rome.addActionListener(this);
		   rome.setActionCommand("Rome");
		   this.add(rome);
		   
		   sparta = new JRadioButton("Sparta");
		   sparta.setBounds(1420, 350, 130, 40);
		   sparta.setActionCommand("Sparta");
		   sparta.addActionListener(this);
		   this.add(sparta);
		   
		   group = new ButtonGroup();
		   group.add(cairo);
		   group.add(sparta);
		   group.add(rome);
		   
		   choose = new JLabel("CHOOSE YOUR CITY");
		   choose.setBounds(640,150,700,50);
		   choose.setFont(new Font("Serif",Font.BOLD,42));
		   choose.setForeground(Color.WHITE);
		   this.add(choose);
		   
		   player = new JLabel("PLEASE ENTER YOUR NAME");
		   player.setBounds(700,30,300,50);
		   player.setFont(new Font("Serif",Font.BOLD,20));
		   player.setForeground(Color.WHITE);
		   this.add(player);
		   
		   playN = new JTextField();
		   playN.setBounds(700,70,300,50);
		   playN.addActionListener(this);
		   playN.setFont(new Font("Serif",Font.BOLD,22));
		   this.add(playN);
		}	
	 
@Override
public void actionPerformed(ActionEvent e) {
	 		if (e.getSource() == start) {
	 			if (playN.getText().equals("")) {
	 				JOptionPane.showMessageDialog(this, "You must input a name", "Error", JOptionPane.ERROR_MESSAGE);
	 			}
	 			else if (group.getSelection()==null)
	 				JOptionPane.showMessageDialog(this, "You must choose a city", "Error", JOptionPane.ERROR_MESSAGE);
	 			else {
	 				this.parentView.viewStart(this);
	 			}
	 				
	 			}
}
	 
    public JButton getStart() {
		return start;
	}
    public void setStart(JButton start) {
		this.start = start;
	}
    public JRadioButton getCairo() {
		return cairo;
	}
    public void setCairo(JRadioButton cairo) {
		this.cairo = cairo;
	}
    public JRadioButton getSparta() {
		return sparta;
	}
    public void setSparta(JRadioButton sparta) {
		this.sparta = sparta;
	}
    public JRadioButton getRome() {
		return rome;
	}
    public void setRome(JRadioButton rome) {
		this.rome = rome;
	}
    public JLabel getChoose() {
		return choose;
	}
    public void setChoose(JLabel choose) {
		this.choose = choose;
	}
    public JLabel getPlayer() {
		return player;
	}
    public void setPlayer(JLabel player) {
		this.player = player;
	}
    public JTextField getPlayN() {
		return playN;
	}
    public void setPlayN(JTextField playN) {
		this.playN = playN;
	}
    public Main getParentView() {
		return parentView;
	}
    public void setParentView(Main parentView) {
		this.parentView = parentView;
	}
    
    public ButtonGroup getGroup() {
		return group;
	}
    public void setGroup(ButtonGroup group) {
		this.group = group;
	}

	 

}