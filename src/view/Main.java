package view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import engine.Game;

@SuppressWarnings({ "serial", "unused" })
public class Main extends JFrame{
	private StartPage startPage;
	private Game game ;
	private WorldView world;
	
	
	public Main() {
	
	ImageIcon img = new ImageIcon("logofinal.jpg");
	this.setIconImage(img.getImage());
    this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    
    startPage = new StartPage(this);
	startPage.setBounds(0, 0, 2000, 1000);
	this.getContentPane().add(startPage);
	
	
	
	this.setResizable(false);
	this.getContentPane().setLayout(null);
	this.setTitle("Empire");
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	this.setSize(1000,1000);
	this.setVisible(true);

}
	public void viewStart(StartPage s) {
		s.setVisible(false);
			System.out.println((String) s.getGroup().getSelection().getActionCommand());
			try {
				game = new Game(s.getPlayN().getText(),(String) s.getGroup().getSelection().getActionCommand());
				game.getPlayer().setTreasury(5000);
				//game.getPlayer().setFood(0.0);
				
			} catch (IOException e) {
				JOptionPane.showConfirmDialog(this, "Error try again");
			}
			world = new WorldView(this);
			world.setBounds(0, 0, 2000, 1000);
			this.getContentPane().add(world);
			this.world.setVisible(true);
		}
	
	public StartPage getStartPage() {
		return startPage;
	}
	public void setStartPage(StartPage startPage) {
		this.startPage = startPage;
	}
	public WorldView getWorld() {
		return world;
	}
	public void setWorld(WorldView world) {
		this.world = world;
	}
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		Main m = new Main();
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}
	
	
	

}