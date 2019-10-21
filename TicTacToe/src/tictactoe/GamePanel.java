
package tictactoe;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

/*
 * GamePanel.java
 *
 * This class implements a Panel made of a matrix of labels
 * Those labels includes the separators and also the clickable labels
*/

public class GamePanel extends JPanel{

    private JLabel[][] _labels;
    private GameControl control;
    private int turn;
    
    JLabel winLine;
    JPanel board;
    
    public GamePanel(){
        
        control = new GameControl();
        _labels = new JLabel[5][5];
        turn = 0;
        
        board = new JPanel();
        board.setBounds(5, 5, 300, 300);
        
        UIManager.put("Label.disabledForeground",Color.BLACK);
        
        board.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                _labels[i][j] = new JLabel("");
                _labels[i][j].setOpaque(true);
                
                if(i % 2 != 0 && j % 2 != 0){
                    _labels[i][j].setIcon(new ImageIcon(getClass().getResource("/images/separatorCenter.png")));
                    _labels[i][j].setPreferredSize(new Dimension(15, 15));
                }
                else if(i % 2 == 0 && j % 2 != 0){
                    _labels[i][j].setIcon(new ImageIcon(getClass().getResource("/images/separatorUp.png")));
                    if((j == 3 || j == 1) && i == 2)
                        _labels[i][j].setIcon(new ImageIcon(getClass().getResource("/images/separatorStandingCenter.png")));
                    if(i == 4)
                        _labels[i][j].setIcon(new ImageIcon(getClass().getResource("/images/separatorDown.png")));
                    _labels[i][j].setPreferredSize(new Dimension(15, 90));
                }
                else if(i % 2 != 0 && j % 2 == 0){
                    _labels[i][j].setIcon(new ImageIcon(getClass().getResource("/images/separatorLeft.png")));
                    if((i == 3 || i == 1) && j == 2)
                        _labels[i][j].setIcon(new ImageIcon(getClass().getResource("/images/separatorLyingCenter.png")));
                    if(j == 4)
                        _labels[i][j].setIcon(new ImageIcon(getClass().getResource("/images/separatorRight.png")));
                    _labels[i][j].setPreferredSize(new Dimension(90, 15));
                }
                else{
                    _labels[i][j].setHorizontalAlignment(JLabel.CENTER);
                    _labels[i][j].setFont(new Font("Comic Sans MS", Font.BOLD, 60));
                    _labels[i][j].setPreferredSize(new Dimension(90, 90));
                    _labels[i][j].addMouseListener(new Click());
                }
                
                c.gridx = j;
                c.gridy = i;
                board.add(_labels[i][j], c);
            }
        }
        
        winLine = new JLabel();
        winLine.setBounds(5, 5, 300, 300);
        
        winLine.setVisible(false);
        
        setPreferredSize(new Dimension(300, 300));
        setLayout(null);
        add(winLine);
        add(board);
        
    }
    
    class Click implements MouseListener {
        
        @Override
        public void mousePressed(MouseEvent e) {
            JLabel label = (JLabel) e.getComponent();
            
            if(label.isEnabled()){
                label.setEnabled(false);
                
                String mark;
                if(turn % 2 == 0) mark = "X";
                else mark = "O";
                
                label.setText(mark);
                
                if(mark == "X") TicTacToe.changeTurn("Turn: O");
                else TicTacToe.changeTurn("Turn: X");
                

                if(label == _labels[0][0]) control.insert(0, 0, mark);
                if(label == _labels[0][2]) control.insert(0, 1, mark);
                if(label == _labels[0][4]) control.insert(0, 2, mark);
                if(label == _labels[2][0]) control.insert(1, 0, mark);
                if(label == _labels[2][2]) control.insert(1, 1, mark);
                if(label == _labels[2][4]) control.insert(1, 2, mark);
                if(label == _labels[4][0]) control.insert(2, 0, mark);
                if(label == _labels[4][2]) control.insert(2, 1, mark);
                if(label == _labels[4][4]) control.insert(2, 2, mark);

                //control.print();
                testGameOver();
                
                turn++;
            }
        }
        
        //ignore other events:
        @Override
        public void mouseClicked(MouseEvent e) {}

        @Override
        public void mouseReleased(MouseEvent e) {}

        @Override
        public void mouseEntered(MouseEvent e) {}

        @Override
        public void mouseExited(MouseEvent e) {}
    }
    
    void reset(){
        for(int i = 0; i < 5; i += 2)
            for(int j = 0; j < 5; j += 2){
                _labels[i][j].setText("");
                _labels[i][j].setEnabled(true);
            }
        
        winLine.setVisible(false);
        control.clean();
    }
    
    void lockGame(){
        for(int i = 0; i < 5; i += 2)
            for(int j = 0; j < 5; j += 2){
                _labels[i][j].setEnabled(false);
            }
        
        String winner;
        if(turn % 2 == 0) winner = "X";
        else winner = "O";
        
        TicTacToe.changeTurn("Winner: " + winner);
    }
    
    void printWinLine(String line){
        
        winLine.setIcon(new ImageIcon(getClass().getResource(line)));
        
        winLine.setVisible(true);
    }
    
    void testGameOver(){
        if(control.checkLine1Win()){
            printWinLine("/images/win/horizontalWin1.png");
            lockGame();
        }
        else if(control.checkLine2Win()){
            printWinLine("/images/win/horizontalWin2.png");
            lockGame();
        }
        else if(control.checkLine3Win()){
            printWinLine("/images/win/horizontalWin3.png");
            lockGame();
        }
        else if(control.checkColumn1Win()){
            printWinLine("/images/win/verticalWin1.png");
            lockGame();
        }
        else if(control.checkColumn2Win()){
            printWinLine("/images/win/verticalWin2.png");
            lockGame();
        }
        else if(control.checkColumn3Win()){
            printWinLine("/images/win/verticalWin3.png");
            lockGame();
        }
        else if(control.checkDiagonal1Win()){
            printWinLine("/images/win/diagonalWin1.png");
            lockGame();
        }
        else if(control.checkDiagonal2Win()){
            printWinLine("/images/win/diagonalWin2.png");
            lockGame();
        }
        else if(control.checkDraw()) TicTacToe.changeTurn("DRAW!");
    }
    
}
