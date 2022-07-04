package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
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
import javax.swing.JTextPane;

import buildings.ArcheryRange;
import buildings.Barracks;
import buildings.Farm;
import buildings.Market;
import buildings.MilitaryBuilding;
import buildings.Stable;
import engine.City;
import engine.Player;
import exceptions.BuildingInCoolDownException;
import exceptions.MaxCapacityException;
import exceptions.MaxLevelException;
import exceptions.MaxRecruitedException;
import exceptions.NotEnoughGoldException;
import units.Archer;
import units.Army;
import units.Cavalry;
import units.Infantry;
import units.Unit;

@SuppressWarnings({ "serial", "unused" })
public class Cairo extends JLabel implements ActionListener {
    private WorldView world;
    private JButton ok;
    private JButton NewBuilding;
    private JButton UpBuilding;
    private JButton RecruitUnit;
    private JButton InitiateArmy;
    private JComboBox <String> ava;
    private JComboBox <String> mine;
    private JComboBox <String> mineEco;
    private String [] v ={"Select Building", "Farm","Market","ArcheryRange","Barracks","Stable"};
    private JLabel back ;
    private JComboBox <String> unit;
    private JComboBox <String> myunit;
    private JComboBox <String> controlarmy;
    private JScrollPane u;
    private  JTextArea x ;
    private JTextArea y;
	private JButton Relocate;
	private JComboBox<String> corr;
	public Cairo (WorldView parentview) {
		
		this.world = parentview;
		this.setBounds(250,50,1750,950);
		//this.setLayout(new GridLayout(4,4));
		//this.setForeground(Color.WHITE);
		//this.setBackground(Color.gray);
		//this.setIcon(new ImageIcon("Cairoview.jpg"));
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		this.setOpaque(true);
		//this.parentview.add(this);
		
	   this.back = new JLabel();
	   this.setIcon(new ImageIcon("cairoview.jpg"));
	   this.add(back);
		
		this.ava = new JComboBox<String> (v);
		ava.setFont(new Font("Serif",Font.BOLD,26));
		ava.setBounds(0,0,200, 60);
		ava.addActionListener(this);
		this.add(ava);
		
		this.y = new JTextArea();
		y.setBounds(1200,0,290,250);
		y.setBackground(Color.gray);
		y.setForeground(Color.WHITE);
		y.setFont(new Font("Serif",Font.BOLD,20));
		y.setBorder(BorderFactory.createLineBorder(Color.black));
		y.setOpaque(true);
		//y.setLineWrap(true);
		y.setEditable(false);
		//this.add(y);
		
		this.x = new JTextArea();
		//x.setBounds(1200,250,290,250);
		x.setBackground(Color.gray);
		x.setForeground(Color.WHITE);
		x.setFont(new Font("Serif",Font.BOLD,20));
		x.setBorder(BorderFactory.createLineBorder(Color.black));
		x.setOpaque(true);
		//x.setLineWrap(true);
		x.setEditable(false);
		//this.add(x);
		
		JScrollPane scroll = new JScrollPane(x,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll.setBounds(1200,250,290,250);
		this.add(scroll);
		
		JScrollPane s = new JScrollPane(y,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		s.setBounds(1200,0,290,250);
		this.add(s);
		
		this.mine = new JComboBox<String> ();
		mine.setFont(new Font("Serif",Font.BOLD,16));
		mine.setBounds(470,0,270, 60);
		mine.addItem(new String("Military buildings"));
		this.add(mine);
		
		this.mineEco = new JComboBox<String> ();
		mineEco.setFont(new Font("Serif",Font.BOLD,16));
		mineEco.setBounds(200,0,270, 60);
		mineEco.addItem(new String("Economical buildings"));
		this.add(mineEco);
		
		
		
		this.NewBuilding = new JButton("New Building");
		NewBuilding.setFont(new Font("Serif",Font.BOLD,16));
		NewBuilding.setBounds(0,60, 200, 60);
		NewBuilding.addActionListener(this);
		this.add(NewBuilding);
		
		this.UpBuilding = new JButton("UpGrade Building");
		UpBuilding.setFont(new Font("Serif",Font.BOLD,16));
		UpBuilding.setBounds(0,120, 200, 60);
		UpBuilding.addActionListener(this);
		this.add(UpBuilding);
		
		this.RecruitUnit = new JButton("Recruit New Unit");
		RecruitUnit.setFont(new Font("Serif",Font.BOLD,16));
		RecruitUnit.setBounds(0,180, 200, 60);
		RecruitUnit.addActionListener(this);
		this.add(RecruitUnit);
		
		this.InitiateArmy = new JButton("Initiate New Army");
		InitiateArmy.setFont(new Font("Serif",Font.BOLD,16));
		InitiateArmy.setBounds(0,240, 200, 60);
		InitiateArmy.addActionListener(this);
		this.add(InitiateArmy);
		
		this.Relocate = new JButton("Relocate Unit");
		Relocate.setFont(new Font("Serif",Font.BOLD,16));
		Relocate.setBounds(0,300, 200, 60);
		Relocate.addActionListener(this);
		this.add(Relocate);
		
		this.ok= new JButton("OK");
		ok.setBounds(1300, 840, 147, 75);
		ok.setFont(new Font("Serif",Font.BOLD,20));
		ok.addActionListener(this);
		this.add(ok);
		
		this.unit = new JComboBox<String>();
		unit.setBounds(740,0,270,60);
		unit.setFont(new Font("Serif",Font.BOLD,16));
		unit.addItem("Select Unit to Recruit");
		unit.addActionListener(this);
		this.add(unit);
		
		
		
		this.myunit = new JComboBox<String>();
		myunit.setBounds(200,60,810,60);
		myunit.setFont(new Font("Serif",Font.BOLD,16));
		myunit.addItem("Select Unit to UpGrade / Relocate / Initiate Army With");
		myunit.addActionListener(this);
		this.add(myunit);
		
		this.controlarmy = new JComboBox<String> ();
		controlarmy.setFont(new Font("Serif",Font.BOLD,16));
		controlarmy.setBounds(200,120,270, 60);
		controlarmy.addItem(new String("Controlled Army in city"));
		controlarmy.addActionListener(this);
		this.add(controlarmy);
		
		this.corr = new JComboBox<String> ();
		corr.setFont(new Font("Serif",Font.BOLD,16));
		corr.setBounds(470,120,270, 60);
		corr.addItem(new String("Corresponding units"));
		corr.addActionListener(this);
		this.add(corr);
		

		
		UpgradeCairo();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()== NewBuilding) {//new Building
			if(ava.getSelectedItem()=="Select")
				JOptionPane.showMessageDialog(this, "Please Choose A Building", "Error", JOptionPane.ERROR_MESSAGE);
		
		    else {
			  try {
				  boolean flag = false;
				  ArrayList<MilitaryBuilding> n = getCairoControl().getMilitaryBuildings();
				  for (int i = 0 ; i<n.size();i++) {
					  if(n.get(i)instanceof ArcheryRange && ava.getSelectedItem().equals("ArcheryRange")) 
						  {   flag= true;
							  JOptionPane.showMessageDialog(this, "You already own this Building", "Error", JOptionPane.ERROR_MESSAGE);
						  }
					  if(n.get(i)instanceof Barracks && ava.getSelectedItem().equals("Barracks"))
					  {   flag= true;
						  JOptionPane.showMessageDialog(this, "You already own this Building", "Error", JOptionPane.ERROR_MESSAGE);
					  }
					  if(n.get(i)instanceof Stable && ava.getSelectedItem().equals("Stable"))
					  {   flag= true;
						  JOptionPane.showMessageDialog(this, "You already own this Building", "Error", JOptionPane.ERROR_MESSAGE);
					  }
				  }
				  if(flag==false){
				  this.world.getParentview().getGame().getPlayer().build((String) ava.getSelectedItem(), "Cairo");
				  
				  if(ava.getSelectedItem().equals("ArcheryRange"))
					  unit.addItem("Archer");
				  if(ava.getSelectedItem().equals("Stable"))
					  unit.addItem("Cavalry");
				  if(ava.getSelectedItem().equals("Barracks"))
					  unit.addItem("Infantry");
				  }
				  UpgradeCairo();
			  }
				
			catch (NotEnoughGoldException e1) {
				JOptionPane.showMessageDialog(this, "Not Enough Gold", "Error", JOptionPane.ERROR_MESSAGE);
			}
			
			System.out.println(ava.getSelectedItem());
		}
		}
	
		if(e.getSource()== ok) { //pressing ok and going back
			this.setVisible(false);
        	this.world.setAllvisible(true);
		    }
		if(e.getSource() == UpBuilding) //Upgrading a building
			UpgradeBuilding();
		if (e.getSource() == RecruitUnit)
	        UpgradeUnit();
		if(e.getSource()==unit) {
			if (unit.getSelectedItem().equals("Archer")) {
				y.removeAll();
				y.setText("Archer Recruitment Cost = 400$ ");
				
			}
			if (unit.getSelectedItem().equals("Infantry")) {
				y.removeAll();
				y.setText("Infantry Recruitment Cost = 500$" );
			}
			if (unit.getSelectedItem().equals("Cavalry")) {
				y.removeAll();
				y.setText("Cavalry Recruitment Cost = 600$" );
			}
		
			
		}
		if(e.getSource()==this.InitiateArmy) {
			initiateArmy();
			City mycity = getCairoControl();
			myunit.removeAllItems();
			myunit.addItem("Select Unit to UpGrade / Relocate/ initiate Army");
            for(int i = 0 ;i<mycity.getDefendingArmy().getUnits().size();i++) {
            	if (mycity.getDefendingArmy().getUnits().get(i) instanceof Archer) {
                myunit.addItem(String.valueOf(mycity.getDefendingArmy().getUnits().get(i)));
				//y.append(String.valueOf("\n"+"Archer"+" Level "+ mycity.getDefendingArmy().getUnits().get(i).getLevel()+" soldier count "+ mycity.getDefendingArmy().getUnits().get(i).getCurrentSoldierCount()+" Max Soldier Count "+mycity.getDefendingArmy().getUnits().get(i).getMaxSoldierCount()));
				}
            	if (mycity.getDefendingArmy().getUnits().get(i) instanceof Cavalry)
					myunit.addItem(String.valueOf(mycity.getDefendingArmy().getUnits().get(i)));
            	if (mycity.getDefendingArmy().getUnits().get(i) instanceof Infantry)
            		myunit.addItem(String.valueOf(mycity.getDefendingArmy().getUnits().get(i)));                    		
			}
			this.world.UpdateArmy();
			this.world.getBattlepane().control();
			
			
		}
		if (e.getSource()==controlarmy) {
			if (controlarmy.getSelectedIndex()>0) {
				ArrayList<Army>a = this.world.getParentview().getGame().getPlayer().getControlledArmies();
				for(int i = 0 ; i < a.size() ; i++) {
					if (String.valueOf(a.get(i)).equals(controlarmy.getSelectedItem())) {
						ArrayList<Unit> u = a.get(i).getUnits();
						y.removeAll();
						y.setText("Corresponding Units"+ "\n");
						corr.removeAllItems();
						corr.addItem("Corresponding Units");
						for(int j = 0 ;j<u.size();j++) {
							corr.addItem(String.valueOf(u.get(j)));
							if(u.get(j) instanceof Archer) {
								y.append("Archer: "+"Level "+ u.get(j).getLevel()+"Curr Count: "+u.get(j).getCurrentSoldierCount()+"\n");
							}
							if(u.get(j) instanceof Cavalry) {
								y.append("Cavalry: "+"Level "+ u.get(j).getLevel()+"Curr Count: "+u.get(j).getCurrentSoldierCount()+"\n");
							}
							if(u.get(j) instanceof Infantry) {
								y.append("Infantry: "+"Level "+ u.get(j).getLevel()+"Curr Count: "+u.get(j).getCurrentSoldierCount()+"\n");
							}
							
						}
					}
				}
			}
		}
		if(e.getSource()==myunit) {
			City mycity = getCairoControl();
			for(int i = 0 ; i<mycity.getDefendingArmy().getUnits().size();i++) {
				if (String.valueOf(myunit.getSelectedItem()).equals(String.valueOf(mycity.getDefendingArmy().getUnits().get(i)))&&(mycity.getDefendingArmy().getUnits().get(i) instanceof Archer)) {
					y.removeAll();
					y.setText(String.valueOf("\n"+"Archer"+" Level "+ mycity.getDefendingArmy().getUnits().get(i).getLevel()+" soldier count "+ mycity.getDefendingArmy().getUnits().get(i).getCurrentSoldierCount()+" Max Soldier Count "+mycity.getDefendingArmy().getUnits().get(i).getMaxSoldierCount()));
					y.repaint();
					y.revalidate();
					this.world.setAllvisible(false);
				}
				if (String.valueOf(myunit.getSelectedItem()).equals(String.valueOf(mycity.getDefendingArmy().getUnits().get(i)))&&(mycity.getDefendingArmy().getUnits().get(i) instanceof Cavalry)) {
					y.removeAll();
					y.setText(String.valueOf("\n"+"Cavalry"+" Level "+ mycity.getDefendingArmy().getUnits().get(i).getLevel()+" soldier count "+ mycity.getDefendingArmy().getUnits().get(i).getCurrentSoldierCount()+" Max Soldier Count "+mycity.getDefendingArmy().getUnits().get(i).getMaxSoldierCount()));
					y.repaint();
					y.revalidate();
					this.world.setAllvisible(false);
				}
				if (String.valueOf(myunit.getSelectedItem()).equals(String.valueOf(mycity.getDefendingArmy().getUnits().get(i)))&&(mycity.getDefendingArmy().getUnits().get(i) instanceof Infantry)) {
					y.removeAll();
					y.setText(String.valueOf("\n"+"Infantry"+" Level "+ mycity.getDefendingArmy().getUnits().get(i).getLevel()+" soldier count "+ mycity.getDefendingArmy().getUnits().get(i).getCurrentSoldierCount()+" Max Soldier Count "+mycity.getDefendingArmy().getUnits().get(i).getMaxSoldierCount()));
					y.repaint();
					y.revalidate();
					this.world.setAllvisible(false);
				}
			}
		}
		if (e.getSource()==ava) {
			label();
			this.world.setAllvisible(false);
		 }
		if (e.getSource()==Relocate) {
			relocate();
			this.world.UpdateArmy();
			this.world.setAllvisible(false);
		}
		
		this.UpgradeCairo();
		world.updateInfo();
		//this.world.setAllvisible(false);
		
	}
public void relocate() {
	if (corr.getSelectedIndex()==0 || controlarmy.getSelectedIndex()==0 || this.world.getAvail().getSelectedIndex()==0) {
		JOptionPane.showMessageDialog(this, "Choose Army With located Units->Choose correspondong unit->Choose relocation Army from stats", "Error", JOptionPane.ERROR_MESSAGE);
	}
	else {
		String av = (String) this.world.getAvail().getSelectedItem();
		String mv = (String) this.controlarmy.getSelectedItem();
		String u = (String) this.corr.getSelectedItem();
		int i = 0 ;
		int j  = 0 ;
		ArrayList <Army> a = this.world.getParentview().getGame().getPlayer().getControlledArmies();
		while ( i< a.size()) {
			if (String.valueOf(a.get(i)).equals(av))
				break;
			i++;
		}
		while(j<a.size()) {
			if(String.valueOf(a.get(j)).equals(mv))
				break;
			j++;
		}
		Unit b = null;
		int k  = 0 ;
		while(k<a.get(j).getUnits().size()) {
			if (String.valueOf(a.get(j).getUnits().get(k)).equals(u)) {
				b=a.get(j).getUnits().get(k);
				break;
			}
			k++;
		}
		try {
			a.get(i).relocateUnit(b);
			controlarmy.removeAllItems();
			controlarmy.addItem("Controlled Army in city");
			ArrayList <Army> a1 = this.world.getParentview().getGame().getPlayer().getControlledArmies();
			for(int i1 = 0 ; i1 < a1.size();i1++) {
				if(a1.get(i1).getCurrentLocation().equals("Cairo")) {
					controlarmy.addItem(String.valueOf(a1.get(i1)));
				}
			
		}} catch (MaxCapacityException e) {
			JOptionPane.showMessageDialog(this, e, "Error", JOptionPane.ERROR_MESSAGE);
		}
		
	}
}
	
	public void label() {
		if (ava.getSelectedIndex()> 0 ) {
			if (ava.getSelectedItem().equals("Farm")) {
				y.removeAll();
				y.setText("Farm: Cost = 1000$ " );
				y.repaint();
				y.revalidate();
				this.world.setAllvisible(false);
				
			}
			else if (ava.getSelectedItem().equals("Market")) {
				y.removeAll();
				y.setText("Market: Cost = 1500$ " );
				y.repaint();
				y.revalidate();
				this.world.setAllvisible(false);
			}
			else if (ava.getSelectedItem().equals("Barracks")) {
				y.removeAll();
				y.setText("Barracks: Cost = 2000$ " );
				y.repaint();
				y.revalidate();
				this.world.setAllvisible(false);
			}
			else if (ava.getSelectedItem().equals("Stable")) {
				y.removeAll();
				y.setText("Stable: Cost = 2500$ " );
				y.repaint();
				y.revalidate();
				this.world.setAllvisible(false);
			}
			else if (ava.getSelectedItem().equals("ArcheryRange")) {
				y.removeAll();
				y.setText("ArcheryRange: Cost = 1500$ " );
				y.repaint();
				y.revalidate();
				this.world.setAllvisible(false);
			}
		}
	}
	public void initiateArmy() {
		if(myunit.getSelectedIndex()==0)
			JOptionPane.showMessageDialog(this, "Please Choose A Unit to Initiate and Army with", "Error", JOptionPane.ERROR_MESSAGE);
		else {
			Player p = this.world.getParentview().getGame().getPlayer();
			City mycity = getCairoControl();
			for(int i = 0 ;i<mycity.getDefendingArmy().getUnits().size();i++) {
				if(String.valueOf(mycity.getDefendingArmy().getUnits().get(i)).equals(myunit.getSelectedItem())) {
					p.initiateArmy(mycity, mycity.getDefendingArmy().getUnits().get(i));
					}
			}
			controlarmy.removeAllItems();
			controlarmy.addItem("Controlled Army in city");
			ArrayList <Army> a = this.world.getParentview().getGame().getPlayer().getControlledArmies();
			for(int i = 0 ; i < a.size();i++) {
				if(a.get(i).getCurrentLocation().equals("Cairo")) {
					controlarmy.addItem(String.valueOf(a.get(i)));
				}
			}
			
		}
		this.world.setAllvisible(false);
	}

	public void UpgradeUnit() {
		if(this.unit.getSelectedIndex()==0)
			JOptionPane.showMessageDialog(this, "Please Choose A Unit to Recruit", "Error", JOptionPane.ERROR_MESSAGE);
		else {
			try {
				this.world.getParentview().getGame().getPlayer().recruitUnit((String) this.unit.getSelectedItem(),"Cairo");
				City mycity = getCairoControl();
				myunit.removeAllItems();
				myunit.addItem("Select Unit to UpGrade / Relocate/ initiate Army");
                for(int i = 0 ;i<mycity.getDefendingArmy().getUnits().size();i++) {
                	if (mycity.getDefendingArmy().getUnits().get(i) instanceof Archer) {
                    myunit.addItem(String.valueOf(mycity.getDefendingArmy().getUnits().get(i)));
					//y.append(String.valueOf("\n"+"Archer"+" Level "+ mycity.getDefendingArmy().getUnits().get(i).getLevel()+" soldier count "+ mycity.getDefendingArmy().getUnits().get(i).getCurrentSoldierCount()+" Max Soldier Count "+mycity.getDefendingArmy().getUnits().get(i).getMaxSoldierCount()));
					}
                	if (mycity.getDefendingArmy().getUnits().get(i) instanceof Cavalry)
    					myunit.addItem(String.valueOf(mycity.getDefendingArmy().getUnits().get(i)));
                	if (mycity.getDefendingArmy().getUnits().get(i) instanceof Infantry)
                		myunit.addItem(String.valueOf(mycity.getDefendingArmy().getUnits().get(i)));                    		
				}
				System.out.print((String) this.unit.getSelectedItem());
			} catch (BuildingInCoolDownException e) {
				JOptionPane.showMessageDialog(this, e, "Error", JOptionPane.ERROR_MESSAGE);
			} catch (MaxRecruitedException e) {
				JOptionPane.showMessageDialog(this, e, "Error", JOptionPane.ERROR_MESSAGE);
			} catch (NotEnoughGoldException e) {
				JOptionPane.showMessageDialog(this, e, "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		
	}

	public void UpgradeCairo() {
		ArrayList<City> City = this.world.getParentview().getGame().getPlayer().getControlledCities();
		  for(int i = 0 ;i<City.size();i++) {
			  if (City.get(i).getName().equals("Cairo")) {
				City Cairo = City.get(i);
				mineEco.removeAllItems();
				mineEco.addItem(new String("Economical buildings"));
				for(int j = 0 ;j<Cairo.getEconomicalBuildings().size();j++) {
					if(Cairo.getEconomicalBuildings().get(j) instanceof Market)
					mineEco.addItem(String.valueOf("Market : Level " + Cairo.getEconomicalBuildings().get(j).getLevel()+" Up Cost=" +Cairo.getEconomicalBuildings().get(j).getUpgradeCost())) ;
					else 
						mineEco.addItem(String.valueOf("Farm : Level " + Cairo.getEconomicalBuildings().get(j).getLevel()+" Up Cost=" +Cairo.getEconomicalBuildings().get(j).getUpgradeCost()));
						
				}
				mine.removeAllItems();
				mine.addItem(new String("Military buildings"));
				for(int j = 0 ;j<Cairo.getMilitaryBuildings().size();j++) {
					if(Cairo.getMilitaryBuildings().get(j) instanceof ArcheryRange)
					mine.addItem(String.valueOf("ArcheryRange : Level " + Cairo.getMilitaryBuildings().get(j).getLevel()+" Up Cost=" +Cairo.getMilitaryBuildings().get(j).getUpgradeCost())) ;
					else if (Cairo.getMilitaryBuildings().get(j) instanceof Barracks)
						mine.addItem(String.valueOf("Barracks:Level " + Cairo.getMilitaryBuildings().get(j).getLevel() +" Up Cost=" +Cairo.getMilitaryBuildings().get(j).getUpgradeCost()));
					else if (Cairo.getMilitaryBuildings().get(j) instanceof Stable)
						mine.addItem(String.valueOf("Stable : Level " + Cairo.getMilitaryBuildings().get(j).getLevel()+" Up Cost=" +Cairo.getMilitaryBuildings().get(j).getUpgradeCost()));
							
				}
			}
			
			
		}
		  City mycity = getCairoControl();
		  x.setText("Defending Army:");
		  if(mycity !=null) {
		  for (int i = 0 ; i< mycity.getDefendingArmy().getUnits().size();i++) {
			  if (mycity.getDefendingArmy().getUnits().get(i) instanceof Archer ) {
				  x.append(String.valueOf("\n"+"Archer " + "Level "+ mycity.getDefendingArmy().getUnits().get(i).getLevel() + " Current: "+mycity.getDefendingArmy().getUnits().get(i).getCurrentSoldierCount() ));
			  }
			  if (mycity.getDefendingArmy().getUnits().get(i) instanceof Cavalry ) {
				  x.append(String.valueOf("\n"+"Cavalry " + "Level "+ mycity.getDefendingArmy().getUnits().get(i).getLevel() + " Current: "+mycity.getDefendingArmy().getUnits().get(i).getCurrentSoldierCount() ));
			  }
			  if (mycity.getDefendingArmy().getUnits().get(i) instanceof Infantry ) {
				  x.append(String.valueOf("\n"+"Infantry " + "Level "+ mycity.getDefendingArmy().getUnits().get(i).getLevel() + " Current: "+mycity.getDefendingArmy().getUnits().get(i).getCurrentSoldierCount() ));
			  }
		  }
			  
		  
		  }
		
	}
	
	public City getCairoControl() {
		ArrayList<City> City = this.world.getParentview().getGame().getPlayer().getControlledCities();
		City Cairo = null;
		  for(int i = 0 ;i<City.size();i++) {
			  if (City.get(i).getName().equals("Cairo")) {
				 Cairo = City.get(i);
			  }
		}
		  return Cairo;
	}
	
	public void UpgradeBuilding() {  //Upgrading a building
		if(mineEco.getSelectedIndex()==0 && mine.getSelectedIndex()==0) {
				JOptionPane.showMessageDialog(this, "Please Choose A Building to Upgarde", "Error", JOptionPane.ERROR_MESSAGE);
			}
		else if (!((String)mine.getSelectedItem()).equals("Military Buildings")) {
				City myCity = getCairoControl();
				//System.out.print(myCity.getName());
				for(int i = 0 ;i< myCity.getMilitaryBuildings().size();i++) {
					if(((String)mine.getSelectedItem()).equals(String.valueOf("ArcheryRange : Level " + myCity.getMilitaryBuildings().get(i).getLevel()+" Up Cost=" +myCity.getMilitaryBuildings().get(i).getUpgradeCost()))&& (myCity.getMilitaryBuildings().get(i)instanceof ArcheryRange))
					{
						try {
							ArcheryRange x = (ArcheryRange) myCity.getMilitaryBuildings().get(i);
							this.world.getParentview().getGame().getPlayer().upgradeBuilding(x);
							UpgradeCairo();
						} catch (NotEnoughGoldException | BuildingInCoolDownException | MaxLevelException e1) {
							if (e1 instanceof NotEnoughGoldException)
								JOptionPane.showMessageDialog(this, "Not Enough Gold", "Error", JOptionPane.ERROR_MESSAGE);
							else if (e1 instanceof BuildingInCoolDownException)
								JOptionPane.showMessageDialog(this, "Building Cooling Down", "Error", JOptionPane.ERROR_MESSAGE);
							else if (e1 instanceof MaxLevelException)
								JOptionPane.showMessageDialog(this, "MaximumLevel Reached", "Error", JOptionPane.ERROR_MESSAGE);
							
					
						}
					}
					else if(((String)mine.getSelectedItem()).equals(String.valueOf("Stable : Level " + myCity.getMilitaryBuildings().get(i).getLevel()+" Up Cost=" +myCity.getMilitaryBuildings().get(i).getUpgradeCost()))&& myCity.getMilitaryBuildings().get(i) instanceof Stable)
					{
						try {
							this.world.getParentview().getGame().getPlayer().upgradeBuilding(myCity.getMilitaryBuildings().get(i));
							UpgradeCairo();
						} catch (NotEnoughGoldException | BuildingInCoolDownException | MaxLevelException e1) {
							if (e1 instanceof NotEnoughGoldException)
								JOptionPane.showMessageDialog(this, "Not Enough Gold", "Error", JOptionPane.ERROR_MESSAGE);
							else if (e1 instanceof BuildingInCoolDownException)
								JOptionPane.showMessageDialog(this, "Building Cooling Down", "Error", JOptionPane.ERROR_MESSAGE);
							else if (e1 instanceof MaxLevelException)
								JOptionPane.showMessageDialog(this, "Maximum Level Reached", "Error", JOptionPane.ERROR_MESSAGE);
						}
					}
					else if(((String)mine.getSelectedItem()).equals(String.valueOf("Barracks:Level " + myCity.getMilitaryBuildings().get(i).getLevel() +" Up Cost=" +myCity.getMilitaryBuildings().get(i).getUpgradeCost()))&& myCity.getMilitaryBuildings().get(i) instanceof Barracks)
					{try {
						this.world.getParentview().getGame().getPlayer().upgradeBuilding(myCity.getMilitaryBuildings().get(i));
					} catch (NotEnoughGoldException e1) {
						JOptionPane.showMessageDialog(this, "Not Enough Gold", "Error", JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					} catch (BuildingInCoolDownException e1) {
						JOptionPane.showMessageDialog(this, "Building Cooling Down", "Error", JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					} catch (MaxLevelException e1) {
						JOptionPane.showMessageDialog(this, "Maximum Level Reached", "Error", JOptionPane.ERROR_MESSAGE);
					}
							
						
					}
				}
			
			}
		 if (!((String)mineEco.getSelectedItem()).equals("Economical buildings")) {
			City myCity = getCairoControl();
			//System.out.print(myCity.getName());
			for(int i = 0 ;i< myCity.getEconomicalBuildings().size();i++) {
				if(((String)mineEco.getSelectedItem()).equals(String.valueOf("Market : Level " + myCity.getEconomicalBuildings().get(i).getLevel()+" Up Cost=" +myCity.getEconomicalBuildings().get(i).getUpgradeCost()))&& (myCity.getEconomicalBuildings().get(i)instanceof Market))
				{
					try {
						Market x = (Market) myCity.getEconomicalBuildings().get(i);
						this.world.getParentview().getGame().getPlayer().upgradeBuilding(x);
						UpgradeCairo();
					} catch (NotEnoughGoldException | BuildingInCoolDownException | MaxLevelException e1) {
						if (e1 instanceof NotEnoughGoldException)
							JOptionPane.showMessageDialog(this, "Not Enough Gold", "Error", JOptionPane.ERROR_MESSAGE);
						else if (e1 instanceof BuildingInCoolDownException)
							JOptionPane.showMessageDialog(this, "Building Cooling Down", "Error", JOptionPane.ERROR_MESSAGE);
						else if (e1 instanceof MaxLevelException)
							JOptionPane.showMessageDialog(this, "Maximum Level Reached", "Error", JOptionPane.ERROR_MESSAGE);
						
				
					}
				}
				else if(((String)mineEco.getSelectedItem()).equals("Farm : Level " + myCity.getEconomicalBuildings().get(i).getLevel()+" Up Cost=" +myCity.getEconomicalBuildings().get(i).getUpgradeCost())&& myCity.getEconomicalBuildings().get(i) instanceof Farm)
				{
					try {
						this.world.getParentview().getGame().getPlayer().upgradeBuilding(myCity.getEconomicalBuildings().get(i));
						UpgradeCairo();
					} catch (NotEnoughGoldException | BuildingInCoolDownException | MaxLevelException e1) {
						if (e1 instanceof NotEnoughGoldException)
							JOptionPane.showMessageDialog(this, "Not Enough Gold", "Error", JOptionPane.ERROR_MESSAGE);
						else if (e1 instanceof BuildingInCoolDownException)
							JOptionPane.showMessageDialog(this, "Building Cooling Down", "Error", JOptionPane.ERROR_MESSAGE);
						else if (e1 instanceof MaxLevelException)
							JOptionPane.showMessageDialog(this, "Maximum Level Reached", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			
			}
		
		}
		    this.UpgradeCairo();
			world.updateInfo();
		
		  
		
		
	}
	
	public WorldView getWorld() {
		return world;
	}

	public void setWorld(WorldView world) {
		this.world = world;
	}

	public JComboBox <String> getUnit() {
		return unit;
	}

	public void setUnit(JComboBox <String> army) {
		this.unit = army;
	}
	
}
