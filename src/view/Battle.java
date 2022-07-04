package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import engine.City;
import exceptions.FriendlyCityException;
import exceptions.FriendlyFireException;
import exceptions.TargetNotReachedException;
import units.Archer;
import units.Army;
import units.Cavalry;
import units.Infantry;
import units.Status;
import units.Unit;

@SuppressWarnings("serial")
public class Battle extends JLabel implements ActionListener{
	private WorldView world;
	private int l ;
	private JOptionPane k;
	private JButton ok;
	private JButton co;
	private JButton auto;
	private JComboBox<String> army;
	private JComboBox<String> cities;
	private JComboBox<String> myunits;
	private JTextArea test;
	private JTextArea defend;
	private JTextArea unitcon;
	private JTextArea unitinfo;
	private JLabel statstitle ;
	private JScrollPane s;
	private JScrollPane s1;
	private JComboBox<String> cityunit;
	private JTextArea defendunit;
	private JButton target;
	
	public Battle(WorldView parentview) {
		this.world = parentview;
		this.setBounds(0,50,1750,950);
		this.setIcon(new ImageIcon("battle.jpg"));
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		this.setFont(new Font("Serif",Font.BOLD,42));
		//this.setForeground(Color.WHITE);
		//this.setBackground(Color.gray);
		this.setOpaque(true);
		
		statstitle = new JLabel("START YOUR BATTLE ");
		statstitle.setFont(new Font("Serif",Font.BOLD,32));
		statstitle.setBounds(660,0,400,90);
		statstitle.setForeground(Color.WHITE);
		this.add(statstitle);
		
		k=new JOptionPane();
		
		this.ok= new JButton("OK");
		ok.setBounds(1500, 800, 147, 75);
		ok.setFont(new Font("Serif",Font.BOLD,20));
		ok.addActionListener(this);
		this.add(ok);
		
		this.target= new JButton("Target City");//Target City Button
		target.setBounds(800, 800, 155, 75);
		target.setFont(new Font("Serif",Font.BOLD,20));
		target.addActionListener(this);
		this.add(target);
	
		
		this.co= new JButton("Attack");//Button of attacking unit
		co.setBounds(1200, 800, 147, 75);
		co.setFont(new Font("Serif",Font.BOLD,20));
		co.addActionListener(this);
		this.add(co);
		
		this.auto= new JButton("Auto Resolve ");//Button of attacking unit
		auto.setBounds(1000, 800, 155, 75);
		auto.setFont(new Font("Serif",Font.BOLD,20));
		auto.addActionListener(this);
		this.add(auto);
		
		this.test = new JTextArea();//Battle Log
		test.setEditable(false);
		 s = new JScrollPane(test,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		 s.setBounds(680,120,400,250);
		 s.setBorder(BorderFactory.createTitledBorder("Battle Log"));
		 this.add(s);
		 
		this.army = new JComboBox<String>();//Controlled Army ComboBox
		army.setBounds(0,60,250,60);
		army.addItem("Controlled Armies");
		army.setFont(new Font("Serif",Font.BOLD,20));
		army.addActionListener(this);
		this.add(army);
		
		this.myunits = new JComboBox<String>();
		myunits.setBounds(250,60,250,60);
		myunits.addItem("Select Unit Here");
		myunits.setFont(new Font("Serif",Font.BOLD,20));
		myunits.addActionListener(this);
		this.add(myunits);
		
		this.unitcon = new JTextArea();
		unitcon.setEditable(false);
		JScrollPane s2 = new JScrollPane(unitcon,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		 s2.setBounds(0,120,250,250);
		 s2.setBorder(BorderFactory.createTitledBorder("Army Info:"));
		this.add(s2);
		
		this.unitinfo = new JTextArea();
		unitinfo.setEditable(false);
		JScrollPane s3= new JScrollPane(unitinfo,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		 s3.setBounds(250,120,250,250);
		 s3.setBorder(BorderFactory.createTitledBorder("Selected Unit Info"));
		this.add(s3);
		
		this.defend = new JTextArea(); //Defending City's Army
		 defend.setEditable(false);
	     s1 = new JScrollPane(defend,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		 s1.setBounds(1240,120,250,250);
		 s1.setBorder(BorderFactory.createTitledBorder("City's Defending Army"));
		this.add(s1);
		
		this.cities = new JComboBox<String>();
		cities.setBounds(1240,60,250,60);
		cities.addItem("Available Cities");
		cities.setFont(new Font("Serif",Font.BOLD,20));
		cities.addActionListener(this);
		this.add(cities);

		this.cityunit = new JComboBox<String>();
		cityunit.setBounds(1490,60,250,60);
		cityunit.addItem("Defending Army Units");
		cityunit.setFont(new Font("Serif",Font.BOLD,20));
		cityunit.addActionListener(this);
		this.add(cityunit);
		
		this.defendunit = new JTextArea();
		 defendunit.setEditable(false);
	     JScrollPane s4 = new JScrollPane(defendunit,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		 s4.setBounds(1490,120,250,250);
		 s4.setBorder(BorderFactory.createTitledBorder("Selected Units info: "));
		this.add(s4);
		
		availablecities();
		control();
	}

	@SuppressWarnings("static-access")
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.world.getEndTurn() && this.isVisible()==true) {
			siege();
			ArrayList<City> x =this.world.getParentview().getGame().getAvailableCities();
			for(int i = 0 ; i<x.size();i++) {
				if (x.get(i).getTurnsUnderSiege()==3) {
					l =JOptionPane.YES_NO_OPTION;
					JOptionPane.showConfirmDialog(this,"THIS CITY HAS BEEN UNDER SEIGE FOR 3 TURNS ATTACK OR AUTO RESOLVE?","Warning!!" , l);
					if(JOptionPane.YES_OPTION==l)
						k.showConfirmDialog(this, army, "siege", JOptionPane.DEFAULT_OPTION);
						autoresolve(x.get(i));
					break;	
				}
			}
		}
		if(e.getSource()==ok) {
			this.setVisible(false);
			this.world.setAllvisible(true);
			this.world.setVisible(true);
			this.world.getStats().setEnabled(true);
			this.world.getStats().setVisible(true);
		}
		if (e.getSource()==cities) {
			City y = getCity((String) cities.getSelectedItem());
			System.out.println("tst "+y.getTurnsUnderSiege());
			cityarmy();
			
		}
		if (e.getSource()==army) {
			army();
			loadUnit();
		}
		if (e.getSource()==myunits) {
			loadinfo();
		}
		if(e.getSource()==cityunit) {
			if(cityunit.getSelectedIndex()>0) {
				if(cities.getSelectedIndex()>0) {
					ArrayList<City> v = this.world.getParentview().getGame().getAvailableCities();
					City x = null;
					String c = (String) cities.getSelectedItem();
					 for (int i = 0 ;i<v.size();i++) {
						 if(v.get(i).getName().equals(c))
							 x  = v.get(i);
					 }
					 ArrayList<Unit> u = x.getDefendingArmy().getUnits();
					 String o = (String) this.cityunit.getSelectedItem();
					 Unit g = null;
					 for(int j = 0 ;j<u.size();j++) {
						 if(String.valueOf(u.get(j)).equals(o))
							 g=u.get(j);
					 }
					defendunit.removeAll();
					defendunit.setText("Selected Units info: "+"\n");
					if(g instanceof Archer)
						defendunit.append("Archer : Level " + g.getLevel() + " Curr Sold. Count: "+ g.getCurrentSoldierCount());
					if(g instanceof Infantry)
						defendunit.append("Infantry : Level " + g.getLevel() + " Curr Sold. Count: "+ g.getCurrentSoldierCount());
					if(g instanceof Cavalry)
						defendunit.append("Cavalry : Level " + g.getLevel() + " Curr Sold. Count: "+ g.getCurrentSoldierCount());
				}
				 
 			}
			
			
		}
		
		if (e.getSource()==auto) {
			autoresolve(getCity((String)cities.getSelectedItem()));
		}
		if ( e.getSource()==target) {
			if(army.getSelectedIndex()==0) {
				JOptionPane.showMessageDialog(this, "Please Choose An Army to Target City With", "Error", JOptionPane.ERROR_MESSAGE);
			}
			if(cities.getSelectedIndex()==0)
				JOptionPane.showMessageDialog(this, "Please Choose City to target", "Error", JOptionPane.ERROR_MESSAGE);
			else {
				String x =(String) army.getSelectedItem();
				ArrayList<Army> c= this.world.getParentview().getGame().getPlayer().getControlledArmies();
				Army a = null;
				for(int i = 0 ;i<c.size();i++) {
					if(String.valueOf(c.get(i)).equals(x))
						a=c.get(i);
				}
				this.world.getParentview().getGame().targetCity(a, (String) cities.getSelectedItem());
				City y = getCity((String) cities.getSelectedItem());
				System.out.print(y.getTurnsUnderSiege());
				army();
				loadUnit();
				this.world.UpdateArmy();
				this.world.setAllvisible(false);
				//this.world.setAllvisible(false);
			}
			//siege();
			control();	
		}
		
		
		if(e.getSource()==co) {
			if(this.army.getSelectedIndex()==0||this.myunits.getSelectedIndex()==0) {
				JOptionPane.showMessageDialog(this, "Please Choose the Army with the Preffered unit to attack with then Select Unit", "Error", JOptionPane.ERROR_MESSAGE);
			}
			if(this.cities.getSelectedIndex()==0 || this.cityunit.getSelectedIndex()==0) {	
				JOptionPane.showMessageDialog(this, "Please Choose the City with the Preffered unit to attack with then Select Unit", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else {
				Army mine = getArmy((String) army.getSelectedItem());
				Unit uni = getUnit((String)myunits.getSelectedItem(),mine);
				City attacked = getCity((String)cities.getSelectedItem());
				Unit defend  = defendUnit(attacked);
				if( mine.getDistancetoTarget()==0) {
					try {
						test.append(String.valueOf(uni)+" Count " +uni.getCurrentSoldierCount()+" level " + uni.getLevel()+"\n"+" ATTACKED "+"\n" + String.valueOf(defend)+
								" Count " +defend.getCurrentSoldierCount()+" level " + defend.getLevel());
						int x = uni.getCurrentSoldierCount();
						int y =defend.getCurrentSoldierCount();
						uni.attack(defend);
						int a = uni.getCurrentSoldierCount();
						int b = defend.getCurrentSoldierCount();
						test.append("\n"+String.valueOf(uni) + " Lost " + (x-a) + " Soldiers");
						test.append("\n"+String.valueOf(defend) + " Lost " + (y-b) + " Soldiers");
					} catch (FriendlyFireException e1) {
						JOptionPane.showMessageDialog(this, e1, "Error", JOptionPane.ERROR_MESSAGE);
					}
				
				}
				else  JOptionPane.showMessageDialog(this, "You havn't Reached Yet", "Error", JOptionPane.ERROR_MESSAGE);
				this.world.UpdateArmy();
				//siege();
				control();	
			}
		}
		//seige();
		}

	
public void autoresolve(City s ) {
	Army defend = s.getDefendingArmy();
	Army attack = getArmy((String) army.getSelectedItem());
	if(attack.getDistancetoTarget()>0)
		JOptionPane.showMessageDialog(this,"You Still Didn't Arrive", "Error", JOptionPane.ERROR_MESSAGE);
	test.append("AUTO RESOLVE MODE ENTERED!!!!"+ "\n");
	try {
		
		this.world.getParentview().getGame().autoResolve(attack, defend);
		int x = defend.getUnits().size();
		int y = attack.getUnits().size();
		if(x>y)
			test.append("YOUR ARMY LOST BATTLE");
		else
			test.append("YOUR ARMY WON THE BATTLE");
	} catch (FriendlyFireException e) {
		JOptionPane.showMessageDialog(this,e, "Error", JOptionPane.ERROR_MESSAGE);
	}
		
	}

public void siege() {//Checking if any army has arrived;
	ArrayList<Army> a = this.world.getParentview().getGame().getPlayer().getControlledArmies();
	int i = 0 ;
	//String s = "";
	while(i<a.size()) {
		if(a.get(i).getDistancetoTarget() == 0 &&(!a.get(i).getCurrentLocation().equals(""))    &&   a.get(i).getTarget().equals("")) {
			try {
				this.world.getParentview().getGame().getPlayer().laySiege(a.get(i),getCity(a.get(i).getCurrentLocation()));
				System.out.println(a.get(i).getCurrentLocation());
			} catch (TargetNotReachedException | FriendlyCityException e) {
				JOptionPane.showMessageDialog(this, e, "Error", JOptionPane.ERROR_MESSAGE);
			}
			}
		i++;
	}
	

	//control();
}
	
	
	
public Unit defendUnit(City c ) {
		Unit u = null;
		for ( int i = 0 ;i<c.getDefendingArmy().getUnits().size();i++) {
			if(String.valueOf(c.getDefendingArmy().getUnits().get(i)).equals(cityunit.getSelectedItem()))
				u = c.getDefendingArmy().getUnits().get(i);
		}
		return u;
	}
	
public City getCity(String f) {//Gets the Selected City
		ArrayList<City> h = this.world.getParentview().getGame().getAvailableCities();
		City r = null;
		for(int i = 0 ; i<h.size();i++) {
			if (h.get(i).getName().equals(f))
				r = h.get(i);
		}
		System.out.print(r.getTurnsUnderSiege());
		return r ;
		
	}
	
public Unit getUnit(String u , Army a ) {//Gets Selected Unit from Army 
	Unit g = null;
	for(int i = 0 ; i<a.getUnits().size();i++) {
		if(String.valueOf(a.getUnits().get(i)).equals(u))
			g = a.getUnits().get(i);
	}
	return g;
	
}


public Army getArmy(String a ) {//get Selected Army from Combo box
	ArrayList<Army> ar = this.world.getParentview().getGame().getPlayer().getControlledArmies();
	Army e = null;
	for(int i = 0 ; i<ar.size();i++) {
		if ( String.valueOf(ar.get(i)).equals(a))
			 e = ar.get(i);
	}
	return e ;
			
	
}

public void loadinfo() {//JText Area Of PLayer Units 
		unitinfo.setText("");
		if ( army.getSelectedIndex()>0) {
		ArrayList<Army> a = this.world.getParentview().getGame().getPlayer().getControlledArmies();
		Army p = null;
		for(int i = 0 ;i<a.size();i++) {
			if (String.valueOf(a.get(i)).equals(army.getSelectedItem()))
				p = a.get(i);
		}
		if(myunits.getSelectedIndex()>0) {
		   Unit u = null;
		   for (int j = 0 ; j<p.getUnits().size();j++) {
			  if(String.valueOf(p.getUnits().get(j)).equals(myunits.getSelectedItem()))
				  u = p.getUnits().get(j);
		}
		  if(u instanceof Archer) { 
			  unitinfo.append("Archer: Level "+ u.getLevel()+" Curr Sold.Count: "+u.getCurrentSoldierCount());
		  }
		  if(u instanceof Infantry) { 
			  unitinfo.append("Infantry: Level "+ u.getLevel()+" Curr Sold.Count: "+u.getCurrentSoldierCount());
		  }
		  if(u instanceof Cavalry) { 
			  unitinfo.append("Cavalry: Level "+ u.getLevel()+" Curr Sold.Count: "+u.getCurrentSoldierCount());
		  }
		  
		}
		}
	}
    
public void loadUnit() {//Displays the Units of the Selected Army
    	if (army.getSelectedIndex()>0) {
    		myunits.removeAllItems();
    		myunits.addItem("Select Unit here");
    		String av = (String)army.getSelectedItem();
    		ArrayList<Army> p = this.world.getParentview().getGame().getPlayer().getControlledArmies();
    		Army u =null;
    		for(int i = 0 ;i<p.size();i++) {
    			if ( String.valueOf(p.get(i)).equals(av)) {
    				u = p.get(i);
    				break;
    			}
    		}
    		for(int i = 0 ;i<u.getUnits().size();i++) {
    			myunits.addItem(String.valueOf(u.getUnits().get(i)));
    		}
    	}
    	
    	
    }
	
public void army() {
		unitcon.setText("Army Info:"+ "\n");
		if(army.getSelectedIndex()>0) {
			for(int i = 0 ; i<this.world.getParentview().getGame().getPlayer().getControlledArmies().size();i++) {
				if(army.getSelectedItem().equals(String.valueOf(this.world.getParentview().getGame().getPlayer().getControlledArmies().get(i)))) {
					unitcon.append("Status: "+ this.world.getParentview().getGame().getPlayer().getControlledArmies().get(i).getCurrentStatus() +"\n"+"Current Location: "+
							this.world.getParentview().getGame().getPlayer().getControlledArmies().get(i).getCurrentLocation()+ "\n" +"Max To Hold: " 
					+ this.world.getParentview().getGame().getPlayer().getControlledArmies().get(i).getMaxToHold()+"\n");
					//if (this.world.getParentview().getGame().getPlayer().getControlledArmies().get(i).getCurrentStatus().equals(Status.MARCHING))
						unitcon.append("Target City: "+ this.world.getParentview().getGame().getPlayer().getControlledArmies().get(i).getTarget()+"\n" +
					" Distance To Target: "+this.world.getParentview().getGame().getPlayer().getControlledArmies().get(i).getDistancetoTarget()+"\n");
					if(this.world.getParentview().getGame().getPlayer().getControlledArmies().get(i).getCurrentStatus().equals(Status.BESIEGING)){
						unitcon.append("Target City: "+ this.world.getParentview().getGame().getPlayer().getControlledArmies().get(i).getTarget());
						for ( int c = 0 ; c<this.world.getParentview().getGame().getAvailableCities().size();c++) {
							if(this.world.getParentview().getGame().getAvailableCities().get(c).getName().equals(this.world.getParentview().getGame().getPlayer().getControlledArmies().get(i).getTarget()))
								unitcon.append("\n" + "TurnsUnderSeige: "+this.world.getParentview().getGame().getAvailableCities().get(c).getTurnsUnderSiege() );
						}
						  
						}
					unitcon.append("Corresponding Units : "+ "\n" );
					for ( int j = 0 ; j<this.world.getParentview().getGame().getPlayer().getControlledArmies().get(i).getUnits().size();j++) {
						if (this.world.getParentview().getGame().getPlayer().getControlledArmies().get(i).getUnits().get(j) instanceof Archer) {
							unitcon.append("Archer: " + "Level: "+this.world.getParentview().getGame().getPlayer().getControlledArmies().get(i).getUnits().get(j).getLevel()+
									"Current Count: "+ this.world.getParentview().getGame().getPlayer().getControlledArmies().get(i).getUnits().get(j).getCurrentSoldierCount()+
									"Max Count: "+this.world.getParentview().getGame().getPlayer().getControlledArmies().get(i).getUnits().get(j).getMaxSoldierCount()+"\n");
						}
						if (this.world.getParentview().getGame().getPlayer().getControlledArmies().get(i).getUnits().get(j) instanceof Cavalry) {
							unitcon.append("Cavalry: " + "Level: "+this.world.getParentview().getGame().getPlayer().getControlledArmies().get(i).getUnits().get(j).getLevel()+
									"Current Count: "+ this.world.getParentview().getGame().getPlayer().getControlledArmies().get(i).getUnits().get(j).getCurrentSoldierCount()+
									"Max Count: "+this.world.getParentview().getGame().getPlayer().getControlledArmies().get(i).getUnits().get(j).getMaxSoldierCount()+"\n");
						}
						if (this.world.getParentview().getGame().getPlayer().getControlledArmies().get(i).getUnits().get(j) instanceof Infantry) {
							unitcon.append("Infantry: " + "Level: "+this.world.getParentview().getGame().getPlayer().getControlledArmies().get(i).getUnits().get(j).getLevel()+
									"Current Count: "+ this.world.getParentview().getGame().getPlayer().getControlledArmies().get(i).getUnits().get(j).getCurrentSoldierCount()+
									"Max Count: "+this.world.getParentview().getGame().getPlayer().getControlledArmies().get(i).getUnits().get(j).getMaxSoldierCount()+"\n");
						}
					}
				}
			}
			
			
			
		}
	}
	
public void control() {
		army.removeAllItems();
		army.addItem("Controlled Armies");
		ArrayList<Army> a = this.world.getParentview().getGame().getPlayer().getControlledArmies();
		for(int i = 0 ;i<a.size();i++) {
			army.addItem(String.valueOf(a.get(i)));
		}
	}

public void availablecities() {
		ArrayList<City> w = this.world.getParentview().getGame().getAvailableCities();
		ArrayList<City> p  = this.world.getParentview().getGame().getPlayer().getControlledCities();
		for (int i = 0 ; i<w.size();i++) {
			boolean flag = false;
			for(int j = 0;j<p.size();j++) {
				if (p.get(j) == w.get(i))
					flag = true;
			}
			if (flag==false)
				cities.addItem(w.get(i).getName());
		}
	}
	
public void cityarmy() {
		if(cities.getSelectedIndex()>0) {
			ArrayList<City> w = this.world.getParentview().getGame().getAvailableCities();
			City c = null;
			for(int i = 0 ;i<w.size();i++) {
				if(w.get(i).getName().equals(cities.getSelectedItem()))
					c=w.get(i);
			}
			defend.removeAll();
			defend.setText("");
			cityunit.removeAllItems();
			cityunit.addItem("Defending Army Units");
			for (int j = 0 ; j<c.getDefendingArmy().getUnits().size();j++ ) {
				
				cityunit.addItem(String.valueOf(c.getDefendingArmy().getUnits().get(j)));
				if(c.getDefendingArmy().getUnits().get(j)instanceof Archer )
					defend.append("Archer: "+"Level "+ c.getDefendingArmy().getUnits().get(j).getLevel()+" Current Count: "+c.getDefendingArmy().getUnits().get(j).getCurrentSoldierCount()+"\n");
				if(c.getDefendingArmy().getUnits().get(j)instanceof Cavalry )
					defend.append("Cavalry: "+"Level "+ c.getDefendingArmy().getUnits().get(j).getLevel()+" Current Count: "+c.getDefendingArmy().getUnits().get(j).getCurrentSoldierCount()+"\n");
				if(c.getDefendingArmy().getUnits().get(j)instanceof Infantry )
					defend.append("Infantry: "+"Level "+ c.getDefendingArmy().getUnits().get(j).getLevel()+" Current Count: "+c.getDefendingArmy().getUnits().get(j).getCurrentSoldierCount()+"\n");
				
			}
		}
		
	}

public JComboBox<String> getArmy() {
		return army;
	}

public void setArmy(JComboBox<String> army) {
		this.army = army;
	}

}
