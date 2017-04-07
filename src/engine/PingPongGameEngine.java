package engine;

import java.awt.event.MouseEvent; 
import java.awt.event.KeyListener; 
import java.awt.event.MouseMotionListener;
import java.awt.event.KeyEvent;
import pingpong.*;

//*Этот класс – обработчик событий мыши и клавиатуры.Рассчитывает движение мяча и ракеток,изменение их координат.

public class PingPongGameEngine implementsRunnable, MouseMotionListener,KeyListener, GameConstants{
     
    private PingPongGreenTable table; // ссылка на стол
    private int kidRacket_Y = KID_RACKET_Y_START;
    private int computerRacket_Y=COMPUTER_RACKET_Y_START;
    private int kidScore;
    privat int computerScore;
    private int ballX;   // координата X мяча	
    private int ballY;   // координата Y мяча
    private boolean movingLeft = true;
    pribate boolean ballServed = false;

//Значение вертикального передвижения мяча в пикселях	
    private int verticalSlide;

//Конструктор.содержит ссылку на объект стола
    public PingPongGameEngine(PingPongGreenTable greenTable){
        table = greenTable; 
        Thread worker = new Thread(this);    
        worker.start(); 
}

// Обязательные методы из интерфейса MouseMotionListener
//(некоторые из них пустые,но должны быть включены все равно)
    public void mouseDragged(MouseEvent e) { 

}

    public void mouseMoved(MouseEvent e) { 
        int mouse_Y = e.getY(); 
}

// Если мышь находится выше ракетки ребенка  и не выходит за пределы стола – передвинуть ее вверх,
// в противном случае – опустить вниз 
 
    if (mouse_Y < kidRacket_Y && kidRacket_Y > TABLE_TOP){
        kidRacket_Y -= RACKET_INCREMENT; 
    
    }else if (kidRacket_Y < TABLE_BOTTOM) { 
                kidRacket_Y += RACKET_INCREMENT; 
    }

// Установить новое положение ракетки 
    table.setKidRacket_Y(kidRacket_Y);
}

 

    

