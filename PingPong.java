package pingpong;


import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.JLabel; //можно показать текст с иконкой
import javax.swing.WindowConstants;
import java.awt.Dimension; 
import java.awt.Container; 
import java.awt.Graphics; 
import java.awt.Color; 
import engine.PingPongGameEngine;
import static pingpong.GameConstants.BALL_START_X;
import static pingpong.GameConstants.BALL_START_Y;
import static pingpong.GameConstants.COMPUTER_RACKET_X;
import static pingpong.GameConstants.COMPUTER_RACKET_Y_START;
import static pingpong.GameConstants.KID_RACKET_X;
import static pingpong.GameConstants.KID_RACKET_Y_START;
import static pingpong.GameConstants.RACKET_LENGTH;
import static pingpong.GameConstants.RACKET_WIDTH;
import static pingpong.GameConstants.TABLE_HEIGHT;
import static pingpong.GameConstants.TABLE_WIDTH;

//Этот класс рисует стол для пинг-понга и отображает координаты точки,где пользователь кликнул мышью
//рисует стол,шар,ракетки,отображает счет

public class PingPongGreenTable extends JPanel implements GameConstants {
    
    private JLabel label;   
    private int computerRacket_Y = COMPUTER_RACKET_Y_START;   
    private int kidRacket_Y = KID_RACKET_Y_START;   
    private int ballX = BALL_START_X;   
    private int ballY = BALL_START_Y; 
    
    Dimension preferredSize= new Dimension(TABLE_WIDTH,TABLE_HEIGHT);
    
    
//Устанавливаем размер окна Вызывается виртуальной Java машиной 
    public Dimension getPreferredSize() {  
        return preferredSize;
    } 

//Конструктор. Создает обработчик событий мыши. 
    PingPongGreenTable(){ 
        PingPongGameEngine gameEngine =new PingPongGameEngine(this); 
    

// Обрабатывает клики мыши для передвижения координат 
    addMouseMotionListener(gameEngine);

 // Обрабатывает событие клавиатуры 
     addKeyListener(gameEngine); 
    }

// Добавить панель с JLabel в окно 
    void addPaneltoFrame(Container container) { 
        
        container.setLayout(new BoxLayout(container,  BoxLayout.Y_AXIS)); 
        container.add(this); 
        label = new JLabel("Press N for a new game, S to serve or Q to quit"); 
        container.add(label); 
    }
    
/* Перерисовать окно. Этот метод вызывается виртуальной  
машиной, когда нужно обновить экран или 
вызывается метод repaint() из PingPointGameEngine */
    public void paintComponent(Graphics g) { 

        super.paintComponent(g);       
         

// Нарисовать зеленый стол 
    g.setColor(Color.GREEN);    
    g.fillRect(0,0,TABLE_WIDTH,TABLE_HEIGHT)
    
// Нарисовать правую ракетку 
    g.setColor(Color.yellow);     
    g.fillRect(KID_RACKET_X, kidRacket_Y,RACKET_WIDTH, RACKET_LENGTH); 

// Нарисовать левую ракетку 
    g.setColor(Color.blue);     
    g.fillRect(COMPUTER_RACKET_X, computerRacket_Y,RACKET_WIDTH,RACKET_LENGTH);  

//Нарисовать мяч
    g.setColor(Color.red);     
    g.fillOval(ballX,ballY,10,10); 
    
    
    
// Нарисовать белые линии
    g.setColor(Color.white);     
    g.drawRect(10,10,300,200);     
    g.drawLine(160,10,160,210); 
    
// Установить фокус на стол, чтобы обработчик клавиатуры мог посылать команды столу 
    requestFocus();
    }

// Установить текущее положение ракетки 
public void setKidRacket_Y(int xCoordinate){ 
    this.kidRacket_Y = xCoordinate;
    repaint();//pingponggreentable перерисовывает сам себя
}

// Вернуть текущее положение ракетки  
public int getKidRacket_Y(int xCoordinate){
    return kidRacket_Y; 
}

// Установить текущее положение ракетки компьютера

public void setComputerRacket_Y(int yCoordinate){
    this.computerRacket_Y = yCoordinate;
    repaint();
}

// Установить игровое сообщение
    public void setMessageText(String text){
        label.setText(text);
        repaint();
    }
    
// Установить позицию мяча
    public void setBallPosition(int xPos, int yPos){
        ballX=xPos;
        ballY=yPos;
        repaint();
    }

public static void main(String[] args) { 

// Создать экземпляр окна 
    JFrame f = new JFrame("Ping Pong Green Table");
    
// Убедиться, что окно может быть закрыто по нажатию на крестик в углу
    f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); 

    PingPongGreenTable table = new PingPongGreenTable();
    table.addPaneltoFrame(f.getContentPane()); 

 // Установить размер окна и сделать его видимым 
    f.setBounds(0,0,TABLE_WIDTH+5, TABLE_HEIGHT+40);
    f.setVisible(true);
}

}
