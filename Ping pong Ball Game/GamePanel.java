package ping.pong.ball.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements KeyListener{
    int ballXpos = 70,ballYpos = 50,ballSize = 20;
    int paddleWidth = 20,paddleHeight = 90,leftPaddleXpos = 0,rightPaddleXpos = 815;
    int leftPaddleYpos = 0,rightPaddleYpos = 20;
    int ballYdir = 4,ballXdir = 4;
    Timer timer;
    Rectangle leftPaddleRect,rightPaddleRect,ballRect;
    JLabel lbl1,lbl2;
    int leftScore = 0,rightScore;
    int origPosxRight = 550,origPosyRight = 50;
    int origPosxLeft = 90,origPosyLeft = 150;
    
    GamePanel() {
        this.setLayout(null);
        this.setBackground(Color.BLACK);
        addKeyListener(this);
        timer = new Timer(15 ,(e)->{
            moveBall();
        });
        lbl1 = new JLabel("Score 0");
        lbl1.setBounds(330,30,100,20);
        lbl1.setForeground(Color.YELLOW);
        lbl1.setFont(new Font("Arial",Font.BOLD,20));
        
        lbl2 = new JLabel("Score 0");
        lbl2.setForeground(Color.YELLOW);
        lbl2.setBounds(430,30,100,20);
        lbl2.setFont(new Font("Arial",Font.BOLD,20));
        
        add(lbl1);      add(lbl2);
        
        leftPaddleRect = new Rectangle(leftPaddleXpos,leftPaddleYpos,paddleWidth,paddleHeight); 
        rightPaddleRect = new Rectangle(rightPaddleXpos,rightPaddleYpos,paddleWidth,paddleHeight); 
        ballRect = new Rectangle(ballXpos,ballYpos,ballSize,ballSize);
    }
    public void moveBall(){
        ballXpos += ballXdir;           ballYpos += ballYdir;
        ballRect.setLocation(ballXpos,ballYpos);
        if ( ballYpos <= 0 || ballYpos>=getHeight()-ballSize ) {
            ballYdir = -ballYdir;
        }
        if(ballRect.intersects(leftPaddleRect)){
            ballXdir = -ballXdir;
        }
        if(ballRect.intersects(rightPaddleRect)){
            ballXdir = -ballXdir;
        }
        if(ballXpos<0){
            rightScore+=1;
            lbl2.setText("score : "+rightScore);
            timer.stop();
            placeBall("right");
        }
        if(ballXpos>820){
            leftScore+=1;
            lbl1.setText("score : "+leftScore);
            timer.stop();
            placeBall("left");
        }
        if(leftScore==5 || rightScore==5){
             timer.stop();
            String s;
            if(leftScore==5){
                s="left";
                JOptionPane.showMessageDialog(this,"Red won the Game","Info ! ",JOptionPane.INFORMATION_MESSAGE);
            }else{
                s = "right";
                JOptionPane.showMessageDialog(this,"Blue won the Game","Info ! ",JOptionPane.INFORMATION_MESSAGE);
            }
            int x = JOptionPane.showConfirmDialog(this,
                    "do u want to play again ? ",
                    "Confirm",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
            if(x==JOptionPane.YES_OPTION){
                leftScore=0;    rightScore=0;
                lbl1.setText("Score : 0");
                lbl2.setText("Score : 0");
                placeBall(s);
            }else if(x==JOptionPane.NO_OPTION){
                System.exit(0);
            }else{
                
            }
        }
       
        repaint();
    }
    public void placeBall(String str){
        if(str.equals("right")){
            // place ball in right panel
            ballXpos = origPosxRight;
            ballYpos = origPosyRight;
        }else{
            // place ball in left panel
            ballXpos = origPosxLeft ;
            ballYpos = origPosyLeft;
        }
        repaint();
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.YELLOW);
        g.drawLine(this.getWidth()/2,0,this.getWidth()/2,600);
        g.setColor(Color.WHITE);
        g.fillOval(ballXpos,ballYpos,ballSize,ballSize);
        
        // leftPanel
        g.setColor(Color.RED);
        g.fillRect(leftPaddleXpos,leftPaddleYpos,paddleWidth,paddleHeight);
        
        // rightPanel
        g.setColor(Color.BLUE);
        g.fillRect(rightPaddleXpos,rightPaddleYpos,paddleWidth,paddleHeight);
        
        leftPaddleRect.setBounds(leftPaddleXpos, leftPaddleYpos, paddleWidth, paddleHeight);
        rightPaddleRect.setBounds(rightPaddleXpos, rightPaddleYpos, paddleWidth, paddleHeight);
    }

    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}
    @Override
    public void keyPressed(KeyEvent e) {
        if(!timer.isRunning()){
            timer.start();
        }
        if(e.getKeyCode()==KeyEvent.VK_UP){
            if(rightPaddleYpos>=10){
                rightPaddleYpos-=10;
                rightPaddleRect.setLocation(rightPaddleXpos,rightPaddleYpos);
            }
        }else if(e.getKeyCode()==KeyEvent.VK_DOWN){
            if(rightPaddleYpos<=getHeight()-paddleHeight-5){
                rightPaddleYpos+=10;
                rightPaddleRect.setLocation(rightPaddleXpos,rightPaddleYpos);  
            }

        }else if(e.getKeyCode()==KeyEvent.VK_W){
            if(leftPaddleYpos>=10){
                leftPaddleYpos-=10;
                leftPaddleRect.setLocation(leftPaddleXpos,leftPaddleYpos);
            }
            

        }else if(e.getKeyCode()==KeyEvent.VK_S){
            if(leftPaddleYpos<=getHeight()-paddleHeight-5){
                leftPaddleYpos+=10;
                leftPaddleRect.setLocation(leftPaddleXpos,leftPaddleYpos);
            }

        }
        repaint();
    }
}
