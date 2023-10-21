package StonePuzzle;

import javax.swing.*;
import java.awt.event.*;
import java.util.Random;

public class MainFrame extends JFrame implements KeyListener {

    int[][] data={{1,2,3,4},
            {5,6,7,8},
            {9,10,11,12},
            {13,14,15,0}};
    int[][] win={{1,2,3,4},
            {5,6,7,8},
            {9,10,11,12},
            {13,14,15,0}};

    int count=0;

    int row=0;
    int column=0;

    public MainFrame(){

        this.addKeyListener(this);

        initFrame();

        initData();

        paintView() ;

        setVisible(true);
    }

    public void initFrame(){
        setSize(514,595);
        setTitle("StonePuzzleGame v1.0");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        setLocationRelativeTo(null);
        setLayout(null);
    }

    public void paintView(){

        getContentPane().removeAll();

        if(victory()){
            JLabel imgwin=new JLabel(new ImageIcon("src\\StonePuzzle\\Images\\win.png"));
            imgwin.setBounds(124,230,266,88);
            getContentPane().add(imgwin);
        }

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                JLabel img=new JLabel(new ImageIcon("src\\StonePuzzle\\Images\\"+data[i][j]+".png"));
                img.setBounds(50+j*100,90+i*100,100,100);
                getContentPane().add(img);
            }
        }
        JLabel img1=new JLabel(new ImageIcon("src\\StonePuzzle\\Images\\background.png"));
        img1.setBounds(26,30,450,484);
        getContentPane().add(img1);

        JLabel counter=new JLabel("步数:"+count);
        counter.setBounds(60,20,60,20);
        getContentPane().add(counter);

        JButton btn=new JButton("重新开始游戏");
        btn.setBounds(360,20,100,20);
        getContentPane().add(btn);
        btn.setFocusable(false);
        btn.addActionListener(e -> {
            initData();
            count=0;
            paintView();
        });

        getContentPane().repaint();

    }

    public boolean victory(){
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if(win[i][j]!=data[i][j]){
                    return false;
                }
            }
        }
        return true;
    }

    public void initData(){
        Random r=new Random();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int x=r.nextInt(4);
                int y=r.nextInt(4);
                int swap=data[i][j];
                data[i][j]=data[x][y];
                data[x][y]=swap;
            }
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if(data[i][j]==0){
                    row=i;
                    column=j;
                }
            }
        }
    }



    @Override
    public void keyPressed(KeyEvent e) {
        int k=e.getKeyCode();
        move(k);
        paintView();
    }

    private void move(int k) {
        if(victory()){
            return;
        }

        if(k==65||k==37){
            if(column==3){}else{
                int swap=data[row][column];
                data[row][column]=data[row][column+1];
                data[row][column+1]=swap;
                column++;
                count++;
            }
        }else if(k==87||k==38){
            if(row==3){}else{
                int swap=data[row][column];
                data[row][column]=data[row+1][column];
                data[row+1][column]=swap;
                row++;
                count++;
            }
        }else if(k==68||k==39){
            if(column==0){}else{
                int swap=data[row][column];
                data[row][column]=data[row][column-1];
                data[row][column-1]=swap;
                column--;
                count++;
            }
        }else if(k==83||k==40){
            if(row==0){}else{
                int swap=data[row][column];
                data[row][column]=data[row-1][column];
                data[row-1][column]=swap;
                row--;
                count++;
            }
        }else if(k==90){
            data=new int[][]{{1,2,3,4},
                             {5,6,7,8},
                             {9,10,11,12},
                             {13,14,15,0}};
        }
    }

    //---------------------------------
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
    //---------------------------------
}
