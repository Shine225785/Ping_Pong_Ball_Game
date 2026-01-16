
package ping.pong.ball.game;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JFrame;

public class PingPongBallGame extends JFrame{
    PingPongBallGame(){
        setBounds(290,20,850,600);
        setTitle("Pink pong game");
        setResizable(false);
        setBackground(Color.BLACK);
        this.getContentPane().setBackground(Color.BLACK);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        this.requestFocus();
        this.setFocusable(true);
        this.requestFocusInWindow();
        GamePanel game = new GamePanel();
        add(game); 
 
        setVisible(true);
        game.setFocusable(true);
        game.requestFocus();
        game.requestFocusInWindow();
    }
    public static void main(String[] args) {
        new PingPongBallGame();
    }
    
}
