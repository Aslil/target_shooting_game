import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

class fire {
    private int x;
    private int y;

    public fire(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    

        
    }

   
public class Game extends JPanel implements KeyListener, ActionListener{ //implement(interface) , interfacede bulunan tüm metodları Game classı kullanmak zorunda. 
    Timer timer = new Timer(1,this);
    private int time_taken = 0;
    private int fire_spent = 0;
    
    private BufferedImage image;
    private ArrayList<fire> fires = new ArrayList<fire>();
    
    
    private int fireY = 1;
    private int ballX= 0;
    private int balldX = 2;
    private int dragonX= 0;
    private int dspaceX = 20; 
    
    public boolean control(){
        for(fire fire : fires){
            if(new Rectangle(fire.getX(), fire.getY(),10,20).intersects(new Rectangle(ballX,0,20,20))){
               return true;
        }
        }
       return false; 
        
    }
        
    
    public Game() {
        try {
            image = ImageIO.read(new FileImageInputStream(new File("dragon.png")));
        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
      setBackground(Color.black);  
      
      timer.start(); 
   
    }

    @Override
    public void paint(Graphics g) {
        time_taken+=5;
        super.paint(g); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        g.setColor(Color.red);
        g.fillOval(ballX, 0, 20, 20);
        
        g.drawImage(image, dragonX, 440, image.getWidth()/10, image.getWidth()/10, this);
        
        for (fire fire : fires){
            if(fire.getY()<0){
                fires.remove(fire);
            }
        }
        g.setColor(Color.red);
        for (fire fire : fires){
            g.fillRect(fire.getX(), fire.getY(), 10, 20);
        }
        if(control()){
            timer.stop();
            String message = "You Won! \n"+
                              "fire spent: " +fire_spent +
                    "\ntime spent: "+ time_taken / 1000.0;
            JOptionPane.showMessageDialog(this, message);
           
        }
    }

    @Override
    public void repaint() {
        super.repaint(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int c = e.getKeyCode();
        if(c== KeyEvent.VK_LEFT){
            if(dragonX <= 0){
                dragonX = 0;
            }
            else{
                dragonX -= dspaceX;
            }
            
        }
        else if( c== KeyEvent.VK_RIGHT){
            if(dragonX>= 670){
                dragonX = 670;
            }
            else{
                dragonX += dspaceX;
            }
        }
        else if(c== KeyEvent.VK_SPACE){
             fires.add(new fire(dragonX+75,460));
             
             fire_spent++;
        }
                
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        for(fire fire : fires){
            
            fire.setY(fire.getY()-fireY);
            
            
        }
        
        ballX += balldX;
        
        if(ballX >= 770){   
            balldX = -balldX;
        }
        
        if(ballX<= 0){
            balldX = -balldX;
        }
        
        repaint();
    }

   
    
    
}