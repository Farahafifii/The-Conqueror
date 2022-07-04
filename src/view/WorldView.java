package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import units.Archer;
import units.Army;
import units.Cavalry;
import units.Infantry;
import units.Status;

@SuppressWarnings({ "serial", "unused" })
public class WorldView  extends JLabel implements ActionListener{
	private Main parentview;
	private JLabel info ;
	private JLabel stats ;
	private JLabel statstitle ;
	private JLabel player;
	private JLabel control;
	private JComboBox <String> avail;
	private JLabel turn;
	private JLabel gold;
	private JLabel food;
	private JButton endTurn;
	private JButton rome;
	private JButton cairo;
	private JButton sparta;
	private Romene romepane;
	private Cairo cairopane;
	private Sparta spartapane;
	private JLabel seigeLable;
	private JTextArea army;
	private JLabel won;
	private JTextArea status;
	private JButton battle;
	private Battle battlepane;
	private JTextField text;
 	private JComboBox<String> e;
	int c =0;
	
	public WorldView (Main parentView) {
		
		
		this.parentview=parentView;
		this.setIcon(new ImageIcon("map.jpg"));
		
		romepane = new Romene(this);
		this.add(romepane);
		romepane.setVisible(false);
		
		cairopane = new Cairo(this);
		this.add(cairopane);
		cairopane.setVisible(false);
		
		spartapane = new Sparta(this);
		this.add(spartapane);
		spartapane.setVisible(false);
		
		battlepane = new Battle(this);
		this.add(battlepane);
		battlepane.setVisible(false);
		
		
		
		info = new JLabel();
		info.setLayout(new GridLayout(1,5));
	    info.setBounds(0,0,1750,50);
	    info.setBorder(BorderFactory.createLineBorder(Color.black));
		info.setFont(new Font("Serif",Font.BOLD,42));
		info.setForeground(Color.WHITE);
		info.setBackground(Color.gray);
		info.setOpaque(true);
		
		this.add(info);
		
		stats = new JLabel();
		//stats.setLayout(new GridLayout(6,1));
	    stats.setBounds(0,50,250,950);
	    stats.setBorder(BorderFactory.createLineBorder(Color.black));
		stats.setFont(new Font("Serif",Font.BOLD,42));
		stats.setForeground(Color.WHITE);
		stats.setBackground(Color.gray);
		stats.setOpaque(true);
		this.add(stats);
		//available
		statstitle = new JLabel("Your Stats :");
		statstitle.setFont(new Font("Serif",Font.BOLD,26));
		statstitle.setBounds(0,0,250,60);
		statstitle.setForeground(Color.WHITE);
		stats.add(statstitle);
		
		avail = new JComboBox <String> ();
		avail.setFont(new Font("Serif",Font.BOLD,26));
		avail.setBounds(0,60,250,60);
		avail.addActionListener(this);
		stats.add(avail);
		
		this.status = new JTextArea();
		status.setBounds(0,120,250,240);
		status.setBackground(Color.gray);
		status.setForeground(Color.WHITE);
		status.setFont(new Font("Serif",Font.BOLD,20));
		status.setBorder(BorderFactory.createLineBorder(Color.black));
		status.setOpaque(true);
		//status.setLineWrap(true);
		status.setEditable(false);
		//stats.add(status);
		
		JScrollPane scroll = new JScrollPane(status,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll.setBounds(0,120,250,240);
		stats.add(scroll);
		
		
		
		this.army = new JTextArea();
		//army.setBounds(0,360,250,440);
		army.setBackground(Color.gray);
		army.setForeground(Color.WHITE);
		army.setFont(new Font("Serif",Font.BOLD,20));
		army.setBorder(BorderFactory.createLineBorder(Color.black));
		army.setOpaque(true);
		//army.setLineWrap(true);
		army.setEditable(false);
		stats.add(army);
		
		JScrollPane s = new JScrollPane(army,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		s.setBounds(0,360,250,300);
		stats.add(s);
		
		String [] b = {"Available Cities","Cairo","Sparta","Rome"};
		e = new JComboBox<String>(b);
		e.setFont(new Font("Serif",Font.BOLD,26));
		e.setForeground(Color.WHITE);
		e.setBounds(0,700,250,80);
		stats.add(e);
		
		battle = new JButton("Enter Battle View");
		battle.setFont(new Font("Serif",Font.BOLD,20));
		battle.setBounds(0,820,250,60);
		battle.addActionListener(this);
		stats.add(battle);
		
		control = new JLabel("Controlled Cities");
		control.setBounds(750,100,300,50);
		control.setFont(new Font("Serif",Font.BOLD,42));
		control.setForeground(Color.white);
		control.setBackground(Color.black);
		control.setOpaque(true);
		this.add(control);
		
		player = new JLabel();
		player.setFont(new Font("Serif",Font.BOLD,26));
		player.setForeground(Color.WHITE);
		info.add(player);
		
		turn = new JLabel();
		turn.setFont(new Font("Serif",Font.BOLD,26));
		turn.setForeground(Color.WHITE);
		info.add(turn);
		
		gold = new JLabel();
		gold.setFont(new Font("Serif",Font.BOLD,26));
		gold.setForeground(Color.WHITE);
		info.add(gold);
		
		food = new JLabel();
		food.setFont(new Font("Serif",Font.BOLD,26));
		food.setForeground(Color.WHITE);
		food.setBackground(Color.black);
		info.add(food);
		
		endTurn = new JButton("End Turn");
		endTurn.setFont(new Font("Serif",Font.BOLD,26));
	    endTurn.addActionListener(this);
		info.add(endTurn);
		
		
		
		
        rome = new JButton();
		rome.setBounds(500,350,138,138);
		rome.setIcon(new ImageIcon("Rome.jpg"));
		rome.addActionListener(this);
		this.add(rome);
		rome.setVisible(false);
		rome.setEnabled(false);
		
		cairo = new JButton();
		cairo.setBounds(1150,550,138,138);
		cairo.setIcon(new ImageIcon("cairo.jpg"));
		cairo.addActionListener(this);
		this.add(cairo);
		cairo.setVisible(false);
		cairo.setEnabled(false);
		
		sparta = new JButton();
		sparta.setBounds(1350,300,138,138);
		sparta.setIcon(new ImageIcon("sparta.jpg"));
		sparta.addActionListener(this);
		this.add(sparta);
		sparta.setVisible(false);
		sparta.setEnabled(false);
		
		updateInfo();
		checkVisible(true);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==endTurn) {
			this.parentview.getGame().endTurn();
			if(this.battlepane.isVisible()==false && this.romepane.isVisible()==false && this.cairopane.isVisible()==false&&this.spartapane.isVisible()==false)
				checkVisible(true);
			checkGameOver();
			this.battlepane.siege();
			//checkVisible(true);
			
		}
		if (e.getSource() == rome) {
			this.romepane.setVisible(true);
			setAllvisible(false);
		}
		if (e.getSource() == cairo) {
			this.cairopane.setVisible(true);
			setAllvisible(false);
		}
		if (e.getSource() == sparta) {
			this.spartapane.setVisible(true);
			setAllvisible(false);
		}
		if(e.getSource()==battle) {
			if(romepane.isVisible()==true || cairopane.isVisible()==true|| spartapane.isVisible()==true) {
				JOptionPane.showMessageDialog(this, "Please Press Ok before entering battle view", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else{
			this.battlepane.setVisible(true);
			stats.setEnabled(false);
			stats.setVisible(false);
			setAllvisible(false);
			}
		}
		if(e.getSource()==avail) {
			upgradestats();
		}
		//this.UpdateArmy();
		this.battlepane.army();
		this.battlepane.loadUnit();
		updateInfo();
	}
	public void setAllvisible(boolean b) {
		checkVisible(b);
		this.repaint();
		this.revalidate();
	}
	private void checkVisible(boolean b) {
		for(int i = 0 ; i<this.parentview.getGame().getPlayer().getControlledCities().size();i++) {
			if(this.parentview.getGame().getPlayer().getControlledCities().get(i).getName().equalsIgnoreCase("Rome"))
			{   rome.setEnabled(b);
				rome.setVisible(b);
				rome.setOpaque(b);
			}
			else
				{
				rome.setEnabled(false);
				rome.setVisible(false);
				rome.setOpaque(false);
				}
			if(this.parentview.getGame().getPlayer().getControlledCities().get(i).getName().equalsIgnoreCase("Cairo"))
			{    cairo.setEnabled(b);
				cairo.setVisible(b);
				cairo.setOpaque(b);
			}
			else
				{
				cairo.setEnabled(false);
				cairo.setVisible(false);
				cairo.setOpaque(false);
				}
			if(this.parentview.getGame().getPlayer().getControlledCities().get(i).getName().equalsIgnoreCase("Sparta"))
				{ sparta.setEnabled(b);
				sparta.setVisible(b);
				sparta.setOpaque(b);
				
				}
			else 
				{sparta.setEnabled(false);
				sparta.setVisible(false);
				sparta.setOpaque(false);
				}
		}
		//this.repaint();
		this.revalidate();
		
		
	}
	public void updateInfo() {
		player.setText("Name:"+ this.parentview.getGame().getPlayer().getName());
		gold.setText("Treasury:"+ this.parentview.getGame().getPlayer().getTreasury() + "$" );
		turn.setText("Turn:"+this.parentview.getGame().getCurrentTurnCount());
		food.setText("food:"+ this.parentview.getGame().getPlayer().getFood());
		upgradestats();
	
		
	}
	public void UpdateArmy() {
		avail.removeAllItems();
		ArrayList<Army> armies = this.getParentview().getGame().getPlayer().getControlledArmies();
		avail.addItem("Player's Controlled Armies");
		for(int i = 0 ; i< armies.size();i++) {
			avail.addItem(String.valueOf(armies.get(i)));
		}
		this.battlepane.siege();
		this.battlepane.control();
	}
	
	public void checkGameOver( ) {
		if(this.getParentview().getGame().isGameOver()) {
		    won = new JLabel ("",SwingConstants.CENTER);
			//won.setText(;
			won.setBounds(0,0,2000,1000);
			won.setFont(new Font("Serif",Font.BOLD,62));
			won.setForeground(Color.WHITE);
			won.setBackground(Color.gray);
			won.setOpaque(true);
			if (this.getParentview().getGame().getPlayer().getControlledCities().size()==this.getParentview().getGame().getAvailableCities().size()) {
				System.out.print("game over test");
				won.setText("Game Over You Won");
				this.removeAll();
				
				this.add(won);
			
		}  if (this.getParentview().getGame().getCurrentTurnCount() >= this.getParentview().getGame().getMaxTurnCount()) {
			System.out.print("game over test");
			won.setText("Game Over You Lost");
			this.removeAll();
			
			this.add(won);
		}
		this.removeAll();
		this.revalidate();
		this.repaint();
		this.add(won);
		
		}
		}
	
	public void upgradestats() {
		status.setText("Army Info:"+ "\n");
		if(avail.getSelectedIndex()>0) {
			for(int i = 0 ; i<this.parentview.getGame().getPlayer().getControlledArmies().size();i++) {
				if(avail.getSelectedItem().equals(String.valueOf(this.parentview.getGame().getPlayer().getControlledArmies().get(i)))) {
					status.append("Status: "+ this.parentview.getGame().getPlayer().getControlledArmies().get(i).getCurrentStatus() +"\n"+"Current Location: "+
							this.parentview.getGame().getPlayer().getControlledArmies().get(i).getCurrentLocation()+ "\n" +"Max To Hold: " 
					+ this.parentview.getGame().getPlayer().getControlledArmies().get(i).getMaxToHold()+"\n");
					//if (this.parentview.getGame().getPlayer().getControlledArmies().get(i).getCurrentStatus().equals(Status.MARCHING))
						status.append("Target City: "+ this.parentview.getGame().getPlayer().getControlledArmies().get(i).getTarget()+"\n" +
					"Distance To Target: "+this.parentview.getGame().getPlayer().getControlledArmies().get(i).getDistancetoTarget());
					if(this.parentview.getGame().getPlayer().getControlledArmies().get(i).getCurrentStatus().equals(Status.BESIEGING)){
						status.append("Target City: "+ this.parentview.getGame().getPlayer().getControlledArmies().get(i).getTarget());
						for ( int c = 0 ; c<this.getParentview().getGame().getAvailableCities().size();c++) {
							if(this.getParentview().getGame().getAvailableCities().get(c).getName().equals(this.parentview.getGame().getPlayer().getControlledArmies().get(i).getTarget()))
								status.append("\n" + "TurnsUnderSeige: "+this.getParentview().getGame().getAvailableCities().get(c).getTurnsUnderSiege() );
						}
						  
						}
					army.setText("Corresponding Units : "+ "\n" );
					for ( int j = 0 ; j<this.parentview.getGame().getPlayer().getControlledArmies().get(i).getUnits().size();j++) {
						if (this.parentview.getGame().getPlayer().getControlledArmies().get(i).getUnits().get(j) instanceof Archer) {
							army.append("Archer: " + "Level: "+this.parentview.getGame().getPlayer().getControlledArmies().get(i).getUnits().get(j).getLevel()+
									"Current Count: "+ this.parentview.getGame().getPlayer().getControlledArmies().get(i).getUnits().get(j).getCurrentSoldierCount()+
									"Max Count: "+this.parentview.getGame().getPlayer().getControlledArmies().get(i).getUnits().get(j).getMaxSoldierCount()+"\n");
						}
						if (this.parentview.getGame().getPlayer().getControlledArmies().get(i).getUnits().get(j) instanceof Cavalry) {
							army.append("Cavalry: " + "Level: "+this.parentview.getGame().getPlayer().getControlledArmies().get(i).getUnits().get(j).getLevel()+
									"Current Count: "+ this.parentview.getGame().getPlayer().getControlledArmies().get(i).getUnits().get(j).getCurrentSoldierCount()+
									"Max Count: "+this.parentview.getGame().getPlayer().getControlledArmies().get(i).getUnits().get(j).getMaxSoldierCount()+"\n");
						}
						if (this.parentview.getGame().getPlayer().getControlledArmies().get(i).getUnits().get(j) instanceof Infantry) {
							army.append("Infantry: " + "Level: "+this.parentview.getGame().getPlayer().getControlledArmies().get(i).getUnits().get(j).getLevel()+
									"Current Count: "+ this.parentview.getGame().getPlayer().getControlledArmies().get(i).getUnits().get(j).getCurrentSoldierCount()+
									"Max Count: "+this.parentview.getGame().getPlayer().getControlledArmies().get(i).getUnits().get(j).getMaxSoldierCount()+"\n");
						}
					}
				}
			}
			
		}
	}
	
	public Main getParentview() {
		return parentview;
	}
	public void setParentview(Main parentview) {
		this.parentview = parentview;
	}
	public JLabel getInfo() {
		return info;
	}
	public void setInfo(JLabel info) {
		this.info = info;
	}
	public JLabel getStats() {
		return stats;
	}
	public void setStats(JLabel stats) {
		this.stats = stats;
	}
	public JLabel getStatstitle() {
		return statstitle;
	}
	public void setStatstitle(JLabel statstitle) {
		this.statstitle = statstitle;
	}
	public JLabel getPlayer() {
		return player;
	}
	public void setPlayer(JLabel player) {
		this.player = player;
	}
	public JLabel getControl() {
		return control;
	}
	public void setControl(JLabel control) {
		this.control = control;
	}
	public JComboBox<String> getAvail() {
		return avail;
	}
	public void setAvail(JComboBox<String> avail) {
		this.avail = avail;
	}
	public JLabel getTurn() {
		return turn;
	}
	public void setTurn(JLabel turn) {
		this.turn = turn;
	}
	public JLabel getGold() {
		return gold;
	}
	public void setGold(JLabel gold) {
		this.gold = gold;
	}
	public JLabel getFood() {
		return food;
	}
	public void setFood(JLabel food) {
		this.food = food;
	}
	public JButton getEndTurn() {
		return endTurn;
	}
	public void setEndTurn(JButton endTurn) {
		this.endTurn = endTurn;
	}
	public JButton getRome() {
		return rome;
	}
	public void setRome(JButton rome) {
		this.rome = rome;
	}
	public JButton getCairo() {
		return cairo;
	}
	public void setCairo(JButton cairo) {
		this.cairo = cairo;
	}
	public JButton getSparta() {
		return sparta;
	}
	public void setSparta(JButton sparta) {
		this.sparta = sparta;
	}
	public JButton getBattle() {
		return battle;
	}
	public void setBattle(JButton battle) {
		this.battle = battle;
	}
	
	public Battle getBattlepane() {
		return battlepane;
	}

	public void setBattlepane(Battle battlepane) {
		this.battlepane = battlepane;
	}
}
