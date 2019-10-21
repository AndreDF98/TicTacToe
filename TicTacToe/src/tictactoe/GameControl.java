
package tictactoe;

public class GameControl {
    
    int[][] matrix = new int[3][3];
    
    public GameControl(){
        clean();
    }
    
    void insert(int i, int j, String mark){
        
        if(mark == "X") matrix[i][j] = 1; // 1 is for X
        else matrix[i][j] = 2; // 2 is for O
        
    }
    
    boolean checkLine1Win(){
        //check for X
        if(matrix[0][0] == 1 && matrix[0][1] == 1 && matrix[0][2] == 1) return true;
        //check for O
        if(matrix[0][0] == 2 && matrix[0][1] == 2 && matrix[0][2] == 2) return true;
        
        return false;
    }
    boolean checkLine2Win(){
        
        if(matrix[1][0] == 1 && matrix[1][1] == 1 && matrix[1][2] == 1) return true;
        if(matrix[1][0] == 2 && matrix[1][1] == 2 && matrix[1][2] == 2) return true;
        
        return false;
    }
    boolean checkLine3Win(){
        
        if(matrix[2][0] == 1 && matrix[2][1] == 1 && matrix[2][2] == 1) return true;
        if(matrix[2][0] == 2 && matrix[2][1] == 2 && matrix[2][2] == 2) return true;
        
        return false;
    }
    
    boolean checkColumn1Win(){
        
        if(matrix[0][0] == 1 && matrix[1][0] == 1 && matrix[2][0] == 1) return true;
        if(matrix[0][0] == 2 && matrix[1][0] == 2 && matrix[2][0] == 2) return true;
        
        return false;
    }
    
    boolean checkColumn2Win(){
        
        if(matrix[0][1] == 1 && matrix[1][1] == 1 && matrix[2][1] == 1) return true;
        if(matrix[0][1] == 2 && matrix[1][1] == 2 && matrix[2][1] == 2) return true;
        
        return false;
    }
    
    boolean checkColumn3Win(){
        
        if(matrix[0][2] == 1 && matrix[1][2] == 1 && matrix[2][2] == 1) return true;
        if(matrix[0][2] == 2 && matrix[1][2] == 2 && matrix[2][2] == 2) return true;
        
        return false;
    }
    
    boolean checkDiagonal1Win(){
        
        if(matrix[0][0] == 1 && matrix[1][1] == 1 && matrix[2][2] == 1) return true;
        if(matrix[0][0] == 2 && matrix[1][1] == 2 && matrix[2][2] == 2) return true;
        
        return false;
    }
    
    boolean checkDiagonal2Win(){
        
        if(matrix[2][0] == 1 && matrix[1][1] == 1 && matrix[0][2] == 1) return true;
        if(matrix[2][0] == 2 && matrix[1][1] == 2 && matrix[0][2] == 2) return true;
        
        return false;
    }
    
    boolean checkDraw(){
        
        int count = 0; //count the number of labels marked
        
        for(int i = 0; i < 3; i++)
            for(int j = 0; j < 3; j++)
                if(matrix[i][j] != 0) count++;
        
        if(count == 9) return true;
        
        return false;
    }
    
    void clean(){
        for(int i = 0; i < 3; i++)
            for(int j = 0; j < 3; j++)
                matrix[i][j] = 0;
    }
    
    void print(){
        for(int i = 0; i < 3; i++)
            System.out.print(matrix[i][0] + " " + matrix[i][1] + " " + matrix[i][2] + "\n");
    }
    
}
