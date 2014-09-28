package myPack.myGame;
import java.util.ArrayList;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;


public class myView extends View  {
	
	ArrayList<Point> posX;
	ArrayList<Point> posY;
	int scoreX ;
	int scoreY ;
	int tie ;
	char [][] map = new char[3][3]; 
	boolean playerX ; 
	int colorofX;
	int colorofY;
	int colorlines;
	int colorback;
	char currentXOrO;
	int xofLine;
	int yofLine;
	int x2ofLine;
	int y2ofLine;
	Paint p;
	boolean draw ;
	boolean stop ;


	public myView(Context context) {
		super(context);		
		scoreX = 0;
		scoreY = 0 ;
		tie = 0 ;
		playerX = true; ;
		draw = false;
		stop = false;
		p = new Paint();
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				map[i][j] = '1';
			}
		}
		posX = new ArrayList<Point>(); 
		posY = new ArrayList<Point>(); 
		currentXOrO = 'x';
		colorofX = Color.GREEN;
		colorofY = Color.RED;
		colorlines = Color.WHITE;
		colorback = Color.BLACK;

	}
	
	@Override
	protected void onDraw(Canvas canvas) {
	    // TODO Auto-generated method stub
	    super.onDraw(canvas);
	  
	    Log.v("dd", "-----------ddddddddddddddddd");
	    p.setColor(colorlines);
	    p.setStyle(Paint.Style.FILL);
	    this.setBackgroundColor(colorback);
	    int x =0 ;
	    int y=0;
	    canvas.drawLine(0, y, this.getWidth(), y, p);
	    y+=150;
	    canvas.drawLine(0, y, this.getWidth(), y, p);
	    y+=150;
	    canvas.drawLine(0, y, this.getWidth(), y, p);
	    y+=150;
	    canvas.drawLine(0, y, this.getWidth(), y, p);
	    
	    canvas.drawLine(x, 0, x,y, p);
	    x+=this.getWidth()/3;;
	    canvas.drawLine(x, 0, x, y, p);
	    x+=this.getWidth()/3;;
	    canvas.drawLine(x, 0, x, y, p);
	    x+=(this.getWidth()/3);
	    canvas.drawLine(x, 0, x-1, y, p);
	    
	    char next ='X';
	    if (currentXOrO == 'x' && draw)
	      next = 'O';
	    p.setTextSize(30);
	    x = 0;
	    y+=35;
	    canvas.drawText("Players Turn : " +next, x, y, p);
	    y+=10 ;
	    canvas.drawLine(x, y, this.getWidth(), y, p);
	    y+=35;
	    canvas.drawText("X wins : " +scoreX + "  |  O wins : " + scoreY +"  |  Tie : " + tie , x, y, p);  
	    y+=10 ;
	    canvas.drawLine(x, y, this.getWidth(), y, p);
	    
	    p.setTextSize(92);
	    if (draw){
//	      draw = false;
	      p.setColor(colorofX);
	      for (int i = 0; i < posX.size(); i++) {
	        Point pp = posX.get(i);        
	        canvas.drawText("X", pp.x-27, pp.y+33, p);
	      }    
	      p.setColor(colorofY);
	      for (int i = 0; i < posY.size(); i++) {
	        Point pp = posY.get(i);
	        canvas.drawText("O", pp.x-27, pp.y+33, p);
	      }
	    
	        if (checkwinner(currentXOrO)){
	          p.setColor(Color.RED);
	          p.setStyle(Paint.Style.STROKE);
	          canvas.drawLine(xofLine, yofLine, x2ofLine, y2ofLine, p);
	          canvas.drawLine(xofLine-2, yofLine-2, x2ofLine-2, y2ofLine-2, p);
	          canvas.drawLine(xofLine-1, yofLine-1, x2ofLine-1, y2ofLine-1, p);
	          canvas.drawLine(xofLine+1, yofLine+1, x2ofLine+1, y2ofLine+1, p);
	          canvas.drawLine(xofLine+2, yofLine+2, x2ofLine+2, y2ofLine+2, p);
	          if (!stop){
        	  if (currentXOrO == 'o')
  	            scoreY++;
  	          else 
  	            scoreX++;
	            stop = true;
	            invalidate();
	          }
	        }else if (checkTie()){
	          
	          
	          if (!stop){
	        	  tie++;
	             stop = true;
	            invalidate();
	            }
	        }
	      
	  }
	    

	  
	  
	  }
	
	private boolean checkTie() {
		// TODO Auto-generated method stub
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (map[i][j] == '1')
					return false;
			}
		}
		return true;
	}

	private boolean checkwinner(char a) {
		// TODO Auto-generated method stub
		boolean win = false;
		for (int i = 0 ; i < 3 && !win ; i++){   // rows 
			win = true;
			for (int j = 0; j < 3; j++) {
				if (map[i][j] != a)
					win = false;
			}
			if (win){
				yofLine = 150 * i + 75;
				xofLine = 20 ; y2ofLine= yofLine ;
				x2ofLine = this.getWidth() -20; 
			}				
		}
		if (!win){
			for (int i = 0 ; i < 3 && !win ; i++){  // cols
				win = true;
				for (int j = 0; j < 3; j++) {
					if (map[j][i] != a)
						win = false;
				}
				if (win){
					xofLine = 160 * i + 80;
					yofLine = 20 ; x2ofLine= xofLine ;
					y2ofLine = 400;
				}
			}
		}
		if (!win){
			if (map [0][0] == a && map [1][1]== a && map [2][2]== a){
				win = true;
				xofLine = 75  ;  yofLine = 75;
				x2ofLine= 400 ; y2ofLine = 400;
			}
		}
		if(!win){
			if (map [0][2] == a && map [1][1]== a && map [2][0]== a){
				win = true;
				xofLine = 400  ;  yofLine = 75 ;
				x2ofLine= 75 ; y2ofLine = 400;
			}
		}
		return win;	
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
	  int action = event.getAction(); 
	  if (action==MotionEvent.ACTION_DOWN && !stop){
	    int newx = (int)event.getX();
	    int newy = (int)event.getY();
	    draw = true;
	    if(newy <450 && map[newy/150][newx/160] == '1'){
	      if(playerX){
	        playerX = false;
	        posX.add(new Point((newx/160)*160+80,(newy/150)*150+75));
	        map[newy/150][newx/160] = 'x';
	        currentXOrO='x';
	      }else{
	        playerX = true;
	        posY.add(new Point((newx/160)*160+80,(newy/150)*150+75));
	        map[newy/150][newx/160] = 'o';
	        currentXOrO='o';
	      }
	      invalidate();
	    }
	  }else {
	    draw = true;
	    int newx = (int) event.getX();
	    int newy = (int) event.getY();  
	  //  gra.add(new Point(newx,newy));
	    Log.v("dd", "-----fff--ddddddddddddddddd");
	    //vi.onDraw(vi.c);
	    //invalidate();
	  }
	    return false;
	  }

public void reINT (){
	    playerX = true; ;
	    draw = false;
	    stop = false;
	    p = new Paint();
	    for (int i = 0; i < 3; i++) {
	      for (int j = 0; j < 3; j++) {
	        map[i][j] = '1';
	      }
	    }
	    posX = new ArrayList<Point>(); 
	    posY = new ArrayList<Point>(); 
	    invalidate();    
	  }
	  public void clearScore(){
	    scoreX = 0;
	    scoreY = 0 ;
	    tie = 0 ;
	    invalidate();
	  }


}
