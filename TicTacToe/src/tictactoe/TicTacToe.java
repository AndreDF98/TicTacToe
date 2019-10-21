
package tictactoe;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/*
 * TicTacToe.java
 *
 * This class implements the main frame
 * It includes a "New Game" button and the game panel
 */

public class TicTacToe extends JFrame {
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new TicTacToe();
            }
        });
    }
    
    JButton btn;
    JPanel board;
    GamePanel game;
    JLabel winLine;
    static JLabel turn;
    
    public TicTacToe() {
        btn = new JButton();
        btn.setText("New Game");
        btn.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        btn.setFocusPainted(false);
        btn.addActionListener(new Restart());
        
        game = new GamePanel();
        game.setBounds(0, 0, 300, 300);
        
        turn = new JLabel("Turn: X");
        turn.setFont(new Font("Comic Sans MS", Font.BOLD, 40));
        turn.setHorizontalAlignment(JLabel.CENTER);
        turn.setPreferredSize(new Dimension(300,50));
        
        JPanel content = new JPanel();
        content.setLayout(new BorderLayout());
        content.add(btn, BorderLayout.NORTH);
        content.add(game, BorderLayout.CENTER);
        content.add(turn, BorderLayout.SOUTH);
        
        setContentPane(content);
        setTitle("Tic Tac Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }
    
    class Restart implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            game.reset();
        }
    }
    
    public static void changeTurn(String t){
        turn.setText(t);
    }
    
}
