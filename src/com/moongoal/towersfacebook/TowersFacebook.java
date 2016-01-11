package com.moongoal.towersfacebook;


import java.util.Random;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class TowersFacebook extends Activity {
	
	    static final String TAG = "Towers";
	
	    static final private int BACK_ID = Menu.FIRST;
	    static final private int CLEAR_ID = Menu.FIRST + 1;
	    
	    private int towerInAction = 0;
	    public int ROWS = 4;
	    public int COLS = 4;
	    /**
	     * from 0 to 7, which game are we playing now
	     */
	    Random generator = new Random();
	    private int GAME = generator.nextInt(10);
	    //private int GAME = 9;
	    
	    public static int[][] tablero = new int[4][4]; 
	    public static int currentView = 1;
	    public static final int NORTH = 1;
	    public static int SOUTH = 2;
	    public static int EAST = 3;
	    public static int WEST = 4;
	    
	    
	    private int[] northRef = new int[COLS];
	    private int[] eastRef  = new int[ROWS];
	    private int[] westRef  = new int[ROWS];
	    private int[] southRef = new int[COLS];
	    private int[][] Ref = new int[ROWS][COLS]; 

	    
	    private int[][] northSuperRef   = {{2, 1, 2, 2}, {1, 2, 4, 2}, {2, 1, 2, 4}, {1, 3, 2, 3}, {3, 2, 1, 3},
	    		                               {2, 2, 1, 2}, {2, 2, 3, 1}, {2, 3, 2, 1}, {1, 2, 2, 3}, {3, 1, 2, 2}};
	    private int[][] eastSuperRef    = {{3, 4, 1, 2}, {2, 3, 1, 2}, {3, 2, 2, 1}, {3, 2, 2, 1}, {2, 2, 1, 2},
	                                           {2, 3, 1, 2}, {1, 3, 2, 3}, {1, 2, 2, 3}, {3, 2, 1, 3}, {2, 2, 1, 3}};
	    private int[][] westSuperRef    = {{2, 1, 2, 2}, {1, 2, 3, 3}, {2, 3, 1, 2}, {1, 2, 2, 3}, {3, 2, 4, 1},
	                                           {3, 1, 2, 2}, {3, 1, 3, 2}, {3, 3, 1, 2}, {1, 3, 3, 2}, {2, 2, 3, 1}};
	    private int[][] southSuperRef   = {{3, 4, 1, 2}, {4, 2, 1, 2}, {2, 4, 2, 1}, {4, 2, 2, 1}, {1, 3, 3, 2},
	                                           {3, 1, 4, 2}, {2, 1, 2, 3}, {2, 1, 2, 3}, {2, 1, 3, 2}, {1, 2, 3, 2}};
	    
	            
	    private int[] countTower = {4, 4, 4, 4};

	    
	    private EditText mEditor;
	    
	    
	
		public TowersFacebook() {
			super();
			// TODO Auto-generated constructor stub
		}

		public int getROWS() {
			return ROWS;
		}

		public void setROWS(int rOWS) {
			ROWS = rOWS;
		}

		public int getCOLS() {
			return COLS;
		}

		public void setCOLS(int cOLS) {
			COLS = cOLS;
		}

		public int[][] getTablero() {
			return tablero;
		}

		public void setTablero(int[][] tablero) {
			this.tablero = tablero;
		}

	/** Called when the activity is first created. */
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

 
        // Inflate our UI from its XML layout description.
        setContentView(R.layout.main);
                
        // Watch for button clicks.
        Button NorthButton = (Button)findViewById(R.id.north);
        NorthButton.setOnClickListener(mNorthListener);

        Button SouthButton = (Button)findViewById(R.id.south);
        SouthButton.setOnClickListener(mSouthListener);
        
        Button EastButton = (Button)findViewById(R.id.east);
        EastButton.setOnClickListener(mEastListener);
        
        Button WestButton = (Button)findViewById(R.id.west);
        WestButton.setOnClickListener(mWestListener);
        
        //use the for below to generate random references
        northRef = northSuperRef[GAME];
        eastRef =  eastSuperRef[GAME];  
        westRef =  westSuperRef[GAME];   
        southRef = southSuperRef[GAME];
        Ref[0] = eastSuperRef[GAME];
        Ref[1] = westSuperRef[GAME];
        Ref[2] = northSuperRef[GAME];
        Ref[3] = southSuperRef[GAME];
       
       
   	for(int i = 0 ; i < COLS ; i++) {
		for(int j = 0 ; j < ROWS ; j++) {
			tablero[i][j] = 0;
		}
	} 
   	
    final Button bttnEastRef0 = (Button) findViewById(R.id.resultado_one_six);
    final Button bttnEastRef1 = (Button) findViewById(R.id.resultado_two_six);
    final Button bttnEastRef2 = (Button) findViewById(R.id.resultado_three_six);
    final Button bttnEastRef3 = (Button) findViewById(R.id.resultado_four_six);
    Button[] arreastRef = new Button[ROWS];
    arreastRef[0] = bttnEastRef0;
    arreastRef[1] = bttnEastRef1;
    arreastRef[2] = bttnEastRef2;
    arreastRef[3] = bttnEastRef3;  
    //FillReferenceRow(arreastRef, eastRef);
    
    final Button bttnWestRef0 = (Button) findViewById(R.id.resultado_one_zero);
    final Button bttnWestRef1 = (Button) findViewById(R.id.resultado_two_zero);
    final Button bttnWestRef2 = (Button) findViewById(R.id.resultado_three_zero);
    final Button bttnWestRef3 = (Button) findViewById(R.id.resultado_four_zero);
    Button[] arrwestRef = new Button[ROWS];
	 arrwestRef[0] = bttnWestRef0;
	 arrwestRef[1] = bttnWestRef1;
	 arrwestRef[2] = bttnWestRef2;
	 arrwestRef[3] = bttnWestRef3;
	 //FillReferenceRow(arrwestRef, westRef);
	 
    final Button bttnNorthRef0 = (Button) findViewById(R.id.resultado_zero_one);
    final Button bttnNorthRef1 = (Button) findViewById(R.id.resultado_zero_two);
    final Button bttnNorthRef2 = (Button) findViewById(R.id.resultado_zero_three);
    final Button bttnNorthRef3 = (Button) findViewById(R.id.resultado_zero_four);
	 Button[] arrnorthRef = new Button[COLS];
	 arrnorthRef[0] = bttnNorthRef0;
	 arrnorthRef[1] = bttnNorthRef1;
	 arrnorthRef[2] = bttnNorthRef2;
	 arrnorthRef[3] = bttnNorthRef3;
	 
    final Button bttnSouthRef0 = (Button) findViewById(R.id.resultado_six_one);
    final Button bttnSouthRef1 = (Button) findViewById(R.id.resultado_six_two);
    final Button bttnSouthRef2 = (Button) findViewById(R.id.resultado_six_three);
    final Button bttnSouthRef3 = (Button) findViewById(R.id.resultado_six_four);
    final Button testingcell = (Button) findViewById(R.id.esquina_four);
    Button[] arrsouthRef = new Button[COLS];
	 arrsouthRef[0] = bttnSouthRef0;
	 arrsouthRef[1] = bttnSouthRef1;
	 arrsouthRef[2] = bttnSouthRef2;
	 arrsouthRef[3] = bttnSouthRef3;
	 
	 Button[][] arrRef = new Button[ROWS][];
	 arrRef[0] = arreastRef;
	 arrRef[1] = arrwestRef;
	 arrRef[2] = arrnorthRef;
	 arrRef[3] = arrsouthRef;
	 
	/**
	 *  Here we fill out the tablero with all Target values
	 */
	 FillReferenceTabl(arrRef, Ref);
	
	
   // clear and start new game      
   final Button buttonClear = (Button) findViewById(R.id.esquina_one);
   buttonClear.setText(Integer.toString(GAME+1));
   buttonClear.setOnClickListener(new Button.OnClickListener() {
       public void onClick(View v) {
       	GAME = (GAME + 1) % 10 ;
       	buttonClear.setText(Integer.toString(GAME+1));
           northRef = northSuperRef[GAME];
           eastRef =  eastSuperRef[GAME];  
           westRef =  westSuperRef[GAME];   
           southRef = southSuperRef[GAME];
           Ref[0] = eastSuperRef[GAME];
           Ref[1] = westSuperRef[GAME];
           Ref[2] = northSuperRef[GAME];
           Ref[3] = southSuperRef[GAME];
           final Button bttnEastRef0 = (Button) findViewById(R.id.resultado_one_six);
           final Button bttnEastRef1 = (Button) findViewById(R.id.resultado_two_six);
           final Button bttnEastRef2 = (Button) findViewById(R.id.resultado_three_six);
           final Button bttnEastRef3 = (Button) findViewById(R.id.resultado_four_six);
           Button[] arreastRef = new Button[ROWS];
           arreastRef[0] = bttnEastRef0;
           arreastRef[1] = bttnEastRef1;
           arreastRef[2] = bttnEastRef2;
           arreastRef[3] = bttnEastRef3;  
           //FillReferenceRow(arreastRef, eastRef);
           
           final Button bttnWestRef0 = (Button) findViewById(R.id.resultado_one_zero);
           final Button bttnWestRef1 = (Button) findViewById(R.id.resultado_two_zero);
           final Button bttnWestRef2 = (Button) findViewById(R.id.resultado_three_zero);
           final Button bttnWestRef3 = (Button) findViewById(R.id.resultado_four_zero);
           Button[] arrwestRef = new Button[ROWS];
   		 arrwestRef[0] = bttnWestRef0;
   		 arrwestRef[1] = bttnWestRef1;
   		 arrwestRef[2] = bttnWestRef2;
   		 arrwestRef[3] = bttnWestRef3;
   		 //FillReferenceRow(arrwestRef, westRef);
   		 
           final Button bttnNorthRef0 = (Button) findViewById(R.id.resultado_zero_one);
           final Button bttnNorthRef1 = (Button) findViewById(R.id.resultado_zero_two);
           final Button bttnNorthRef2 = (Button) findViewById(R.id.resultado_zero_three);
           final Button bttnNorthRef3 = (Button) findViewById(R.id.resultado_zero_four);
   		 Button[] arrnorthRef = new Button[COLS];
   		 arrnorthRef[0] = bttnNorthRef0;
   		 arrnorthRef[1] = bttnNorthRef1;
   		 arrnorthRef[2] = bttnNorthRef2;
   		 arrnorthRef[3] = bttnNorthRef3;
   		 
           final Button bttnSouthRef0 = (Button) findViewById(R.id.resultado_six_one);
           final Button bttnSouthRef1 = (Button) findViewById(R.id.resultado_six_two);
           final Button bttnSouthRef2 = (Button) findViewById(R.id.resultado_six_three);
           final Button bttnSouthRef3 = (Button) findViewById(R.id.resultado_six_four);
           final Button testingcell = (Button) findViewById(R.id.esquina_four);
           Button[] arrsouthRef = new Button[COLS];
   		 arrsouthRef[0] = bttnSouthRef0;
   		 arrsouthRef[1] = bttnSouthRef1;
   		 arrsouthRef[2] = bttnSouthRef2;
   		 arrsouthRef[3] = bttnSouthRef3;
   		 Button[][] arrRef = new Button[ROWS][];
    		 arrRef[0] = arreastRef;
    		 arrRef[1] = arrwestRef;
    		 arrRef[2] = arrnorthRef;
    		 arrRef[3] = arrsouthRef;
       	FillReferenceTabl(arrRef, Ref);
       	SetTextBlack(arrRef);
    }
   });     
   
   // First Row       
   final Button button11 = (Button) findViewById(R.id.one_one);
   button11.setOnClickListener(new Button.OnClickListener() {
       public void onClick(View v) {
           // Perform action after click of a button
           toggleCell(button11,1,1);  
           
       }
   });

   final Button button12 = (Button) findViewById(R.id.one_two);
   button12.setOnClickListener(new Button.OnClickListener() {
       public void onClick(View v) {
           // Perform action after click of a button
           toggleCell(button12,1,2);

       }
   });

   final Button button13 = (Button) findViewById(R.id.one_three);
   button13.setOnClickListener(new Button.OnClickListener() {
       public void onClick(View v) {
           // Perform action after click of a button
       	toggleCell(button13,1,3);
       }
   });

   final Button button14 = (Button) findViewById(R.id.one_four);
   button14.setOnClickListener(new Button.OnClickListener() {
       public void onClick(View v) {
           // Perform action after click of a button
       	toggleCell(button14,1,4);
       }
   });

   // Second Row       
   final Button button21 = (Button) findViewById(R.id.two_one);
   button21.setOnClickListener(new Button.OnClickListener() {
       public void onClick(View v) {
           // Perform action after click of a button
           toggleCell(button21,2,1);        	
          
       }
   });

   final Button button22 = (Button) findViewById(R.id.two_two);
   button22.setOnClickListener(new Button.OnClickListener() {
       public void onClick(View v) {
           // Perform action after click of a button
           toggleCell(button22,2,2);
           
       }
   });

   final Button button23 = (Button) findViewById(R.id.two_three);
   button23.setOnClickListener(new Button.OnClickListener() {
       public void onClick(View v) {
           // Perform action after click of a button
       	toggleCell(button23,2,3);
       }
   });

   final Button button24 = (Button) findViewById(R.id.two_four);
   button24.setOnClickListener(new Button.OnClickListener() {
       public void onClick(View v) {
           // Perform action after click of a button
       	toggleCell(button24,2,4);
       }
   });

   // Third Row       
   final Button button31 = (Button) findViewById(R.id.three_one);
   button31.setOnClickListener(new Button.OnClickListener() {
       public void onClick(View v) {
           // Perform action after click of a button
           toggleCell(button31,3,1);        	
       }
   });

   final Button button32 = (Button) findViewById(R.id.three_two);
   button32.setOnClickListener(new Button.OnClickListener() {
       public void onClick(View v) {
           // Perform action after click of a button
           toggleCell(button32,3,2);
       }
   });

   final Button button33 = (Button) findViewById(R.id.three_three);
   button33.setOnClickListener(new Button.OnClickListener() {
       public void onClick(View v) {
           // Perform action after click of a button
       	toggleCell(button33,3,3);
       }
   });

   final Button button34 = (Button) findViewById(R.id.three_four);
   button34.setOnClickListener(new Button.OnClickListener() {
       public void onClick(View v) {
           // Perform action after click of a button
       	toggleCell(button34,3,4);
       }
   });

   // Fourth Row       
   final Button button41 = (Button) findViewById(R.id.four_one);
   button41.setOnClickListener(new Button.OnClickListener() {
       public void onClick(View v) {
           // Perform action after click of a button
           toggleCell(button41,4,1);
       }
   });

   final Button button42 = (Button) findViewById(R.id.four_two);
   button42.setOnClickListener(new Button.OnClickListener() {
       public void onClick(View v) {
           // Perform action after click of a button
           toggleCell(button42,4,2);
       }
   });

   final Button button43 = (Button) findViewById(R.id.four_three);
   button43.setOnClickListener(new Button.OnClickListener() {
       public void onClick(View v) {
           // Perform action after click of a button
       	toggleCell(button43,4,3);
       }
   });

   final Button button44 = (Button) findViewById(R.id.four_four);
   button44.setOnClickListener(new Button.OnClickListener() {
       public void onClick(View v) {
           // Perform action after click of a button
       	toggleCell(button44,4,4);
       }
   });

   
   //Tower Type
   final Button button71 = (Button) findViewById(R.id.tower_one);
   button71.setOnClickListener(new Button.OnClickListener() {
       public void onClick(View v) {
           // Perform action after click of a button
           towerInAction = 1; 
           
       }
   });

   final Button button72 = (Button) findViewById(R.id.tower_two);
   button72.setOnClickListener(new Button.OnClickListener() {
       public void onClick(View v) {
           // Perform action after click of a button
           towerInAction = 2;
       }
   });

   final Button button73 = (Button) findViewById(R.id.tower_three);
   button73.setOnClickListener(new Button.OnClickListener() {
       public void onClick(View v) {
           // Perform action after click of a button
       	towerInAction = 3;
       	// Sound if in error
       	// setDefault(Notification.DEFAULT_SOUND);
       }
   });

   final Button button74 = (Button) findViewById(R.id.tower_four);
   button74.setOnClickListener(new Button.OnClickListener() {
       public void onClick(View v) {
           // Perform action after click of a button
       	towerInAction = 4;
       }
   });
    
   // Calculate Results      
   final Button buttonCalc2 = (Button) findViewById(R.id.esquina_two);
   buttonCalc2.setOnClickListener(new Button.OnClickListener() {
       public void onClick(View v) {
       	//rowResult(2,eastRef);
       	handResult();
       }
   });
   
   // Start How to Play activity     
   final Button buttonHowToPlay = (Button) findViewById(R.id.howToPlay);
   buttonHowToPlay.setOnClickListener(new Button.OnClickListener() {
       public void onClick(View v) {
   		Intent myIntent = new Intent(TowersFacebook.this, Instructions.class);
		startActivity(myIntent);
       }
   });

   
}// End of "OnCreate"
    
	public void toggleCell(Button btn, int i, int j) {

        // If the cell is occupied, clean it. Update arrays	 
    	 if (tablero[i-1][j-1] != 0) {
    		 btn.setText("");
    		 countTower[tablero[i-1][j-1]-1] = countTower[tablero[i-1][j-1]-1] + 1;
    		 tablero[i-1][j-1] = 0;
    		 towerInAction = 0;
    		 return;
    	 }
    	 
         // If user has not selected a tower type then return
       	 if ( towerInAction == 0) {
       		 return;
       	 }   
    	 
    	 // If still towers of this type left
         if (countTower[towerInAction-1] > 0) {
         	 // In regular case, decrease the count of towers arrays
        	 countTower[towerInAction-1] = countTower[towerInAction-1] - 1;  
           
        	 // Set the new tower on the array
        	 tablero[i-1][j-1] = towerInAction;
        	 
        	 
        	 // If the cell is clean, set the current value on the screen
     	     switch (towerInAction) {
               case 1:
         	     btn.setText("1");
         	     btn.setTextColor(0xFFFFFF00);
         	     //btn.setBackgroundColor(0xFFFFFF00);
         	   return;
             case 2:
         	    btn.setText("2");
         	    btn.setTextColor(0xFF7E0000);
         	    //btn.setBackgroundColor(0xFF7E0000);
         	   return;
             case 3:
         	    btn.setText("3");
         	    btn.setTextColor(0xFFFF0000);
         	    //btn.setBackgroundColor(0xFFFF0000);
         	 return;
             case 4:
         	    btn.setText("4");
         	    btn.setTextColor(0xFF00FF00);
         	    //btn.setBackgroundColor(0xFF00FF00);
         	 return;
             }
 
          } else {
        	// Not enough towers, beep
         	return;
          }

    }

    /**
     * After filling out the 'tablero'
     */
    public void handResult() {
    	int rowCount = tablero.length;
        int colCount = tablero[0].length;
        int score = 0;
        
        final Button bttnWestRef0 = (Button) findViewById(R.id.resultado_one_zero);
        final Button bttnWestRef1 = (Button) findViewById(R.id.resultado_two_zero);
        final Button bttnWestRef2 = (Button) findViewById(R.id.resultado_three_zero);
        final Button bttnWestRef3 = (Button) findViewById(R.id.resultado_four_zero);
        Button[] arrwestRef = new Button[ROWS];
		arrwestRef[0] = bttnWestRef0;
		arrwestRef[1] = bttnWestRef1;
		arrwestRef[2] = bttnWestRef2;
		arrwestRef[3] = bttnWestRef3;
        //getting WEST results
		for (int i = 0 ; i < colCount ;  i++) {
    		int[] thisrow = getRowTablero(tablero, i);
    		int[] thisrow_res = rowResult(westRef[i],thisrow);
			if (thisrow_res[0] == 1) {
				arrwestRef[i].setTextColor(0xFF00FF00);
				score = score + 1;
//					Log.v("DEBUG1", "thisrow=" + displayArr(thisrow) + "thisrow_res=" + displayArr(thisrow_res));
			} 
			if (thisrow_res[0] == 0)
				arrwestRef[i].setTextColor(0xFFFF0000);	
    	}
    	
        final Button bttnEastRef0 = (Button) findViewById(R.id.resultado_one_six);
        final Button bttnEastRef1 = (Button) findViewById(R.id.resultado_two_six);
        final Button bttnEastRef2 = (Button) findViewById(R.id.resultado_three_six);
        final Button bttnEastRef3 = (Button) findViewById(R.id.resultado_four_six);
		Button[] arreastRef = new Button[ROWS];
		arreastRef[0] = bttnEastRef0;
		arreastRef[1] = bttnEastRef1;
		arreastRef[2] = bttnEastRef2;
		arreastRef[3] = bttnEastRef3;
        //getting EAST results
    	for (int i = 0 ; i < colCount ;  i++) {
    		int[] thisrow = getRowTablero(tablero, i);
    		int[] thisrow_res = rowResult(eastRef[i],reverseArr(thisrow));		
			if (thisrow_res[0] == 1) 
				arreastRef[i].setTextColor(0xFF00FF00);
			    score = score + 1; 
			if (thisrow_res[0] == 0) 
				arreastRef[i].setTextColor(0xFFFF0000);	
    	} 
    	
        final Button bttnNorthRef0 = (Button) findViewById(R.id.resultado_zero_one);
        final Button bttnNorthRef1 = (Button) findViewById(R.id.resultado_zero_two);
        final Button bttnNorthRef2 = (Button) findViewById(R.id.resultado_zero_three);
        final Button bttnNorthRef3 = (Button) findViewById(R.id.resultado_zero_four);
		Button[] arrnorthRef = new Button[COLS];
		arrnorthRef[0] = bttnNorthRef0;
		arrnorthRef[1] = bttnNorthRef1;
		arrnorthRef[2] = bttnNorthRef2;
		arrnorthRef[3] = bttnNorthRef3;
        //getting North results
    	for (int j = 0 ; j < rowCount ;  j++) {
    		int[] thiscol = getColTablero(tablero, j);
    		int[] thiscol_res = rowResult(northRef[j],thiscol);
			if (thiscol_res[0] == 1) 
				arrnorthRef[j].setTextColor(0xFF00FF00);
			    score = score + 1;
			if (thiscol_res[0] == 0) 
				arrnorthRef[j].setTextColor(0xFFFF0000);
    	}

        final Button bttnSouthRef0 = (Button) findViewById(R.id.resultado_six_one);
        final Button bttnSouthRef1 = (Button) findViewById(R.id.resultado_six_two);
        final Button bttnSouthRef2 = (Button) findViewById(R.id.resultado_six_three);
        final Button bttnSouthRef3 = (Button) findViewById(R.id.resultado_six_four);
        //final TableroCell bttnSouthRef3 = (TableroCell) findViewById(R.id.resultado_six_four);
        Button[] arrsouthRef = new Button[COLS];
		arrsouthRef[0] = bttnSouthRef0;
		arrsouthRef[1] = bttnSouthRef1;
		arrsouthRef[2] = bttnSouthRef2;
		arrsouthRef[3] = bttnSouthRef3;
        //getting SOUTH results
    	for (int j = 0 ; j < rowCount ;  j++) {
    		int[] thiscol = getColTablero(tablero, j);
    		int[] thiscol_res = rowResult(southRef[j],reverseArr(thiscol));
			if (thiscol_res[0] == 1)
				arrsouthRef[j].setTextColor(0xFF00FF00);
			    score = score + 1;
			if (thiscol_res[0] == 0) 
				arrsouthRef[j].setTextColor(0xFFFF0000);
    	}
        score = score * 77;
    	Log.v("Towers", "score= " +score);
    	
		Intent myIntent = new Intent(this, ScoreResults.class);
		myIntent.putExtra("score", score);
		startActivity(myIntent);
    }

    /**
     * returns array ,true-false,correct-answer ,user-answer
     */
    public int[] rowResult(int correct, int[] values) {
    	// will return 	result[0]=  1 if correct, 0 if incorrect
    	//  			result[1]=  correct value
    	//  			result[2]=  user value
    	int[] result = new int [3];
    	result[1] = correct;
    	result[2] = calcRow(values);
    	if (result[1] == result[2])	result[0] = 1;
    	else result[0] = 0;
    	Log.v("rowResult", displayArr(result));
    	return result;
    }
    

    /**
     * returns a string representation of an Integer Array
     */ 
    private String displayArr (int[] arr) {
    	String rtn = "{";
    	for (int i : arr) 
    		rtn = rtn + Integer.toString(i) + ", "; 
    	rtn = rtn.substring(0, rtn.length() - 2);
    	rtn = rtn + "}"; 
    	return rtn;
    }

    /**
     * returns the specified colum from the 'tablero'
     */
    public int[] getColTablero (int[][] tabl, int J){
    	int rowCount = tablero.length;
    	Log.v("getRowTablero", "rowCount" + rowCount);
    	Log.v("getRowTablero", "tablero" + J +"," + 0 +"=" + tabl[J][0]);
    	int[] col = new int [rowCount];
    	for (int i=0 ; i < rowCount ; i++) {
    		col[i] = tabl[i][J];
    	}
    	Log.v("getRowTablero", "col" + J + "=" + displayArr(col));
    	return col;
    }

    /**
     * returns the specified row from the 'tablero'
     */ 
    public int[] getRowTablero (int[][] tabl, int I){
    	int colCount = tabl[I].length;
    	Log.v("getRowTablero", "colCount" + colCount);
    	Log.v("getRowTablero", "tablero" + I +"," + 0 +"=" + tabl[I][0]);
    	int[] row = new int [colCount];
    	for (int j=0 ; j < colCount ; j++) {
    		row[j] = tabl[I][j];
    	}
    	Log.v("getRowTablero", "row" + I + "=" + displayArr(row));
    	return row;
    }

    /**
     * returns the reverse version of the array
     */   
    public int[] reverseArr (int[] arr){
    	int[] b = (int[])arr.clone();
    	for (int left=0, right=b.length-1; left<right; left++, right--) {
    		// exchange the first and last
    		int temp = b[left]; b[left]  = b[right]; b[right] = temp;
    	}
    	Log.v("reverseArr", "original=" + displayArr(arr) + "reversed=" + displayArr(b));
    	return b;
    }
    
    /**
     * Calculates how many towers can be seen in a row/colum
     */   
    public int calcRow (int[] values) {
    	int view = 0;
    	int current = 0;
    	Log.v("calcRow", "values=" + displayArr(values));
    	for (int i : values) {
    		Log.v("calcRow", "view=" + view + " current=" + current);
    		if (i > current) {
    			view += 1; 
    			current = i;
    		}
    	}
    	Log.v("calcRow", "finalview=" + view);
    	return view;
    }

    /**
     * Called when your activity's options menu needs to be created.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        // We are going to create two menus. Note that we assign them
        // unique integer IDs, labels from our string resources, and
        // given them shortcuts.
        menu.add(0, BACK_ID, 0, R.string.back).setShortcut('0', 'b');
        menu.add(0, CLEAR_ID, 0, R.string.clear).setShortcut('1', 'c');

        return true;
    }

    /**
     * Called right before your activity's option menu is displayed.
     */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);

        // Before showing the menu, we need to decide whether the clear
        // item is enabled depending on whether there is text to clear.
        menu.findItem(CLEAR_ID).setVisible(mEditor.getText().length() > 0);

        return true;
    }

    /**
     * Called when a menu item is selected.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case BACK_ID:
            finish();
            return true;
        case CLEAR_ID:
            mEditor.setText("");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A call-back for when the user presses the back Button.
     */
    OnClickListener mBackListener = new OnClickListener() {
        public void onClick(View v) {
            finish();
        }
    };

    /**
     * A call-back for when the user presses the clear TButton.
     */
    OnClickListener mClearListener = new OnClickListener() {
        public void onClick(View v) {
            mEditor.setText("");
        }
    };
    

    
    private OnClickListener mNorthListener = new OnClickListener()
    {
        public void onClick(View v)
        {
            // Here we start the next activity, and then call finish()
            // so that our own will stop running and be removed from the
            // history stack.
            Intent intent = new Intent();
            TowersFacebook.currentView = 1;
            intent.setClass(TowersFacebook.this, NorthView.class);
            startActivity(intent);

        }
    };
    
    private OnClickListener mSouthListener = new OnClickListener()
    {
        public void onClick(View v)
        {
            // Here we start the next activity, and then call finish()
            // so that our own will stop running and be removed from the
            // history stack.
            Intent intent = new Intent();
            intent.setClass(TowersFacebook.this, SouthView.class);
            TowersFacebook.currentView = 2;
            startActivity(intent);

        }
    };

    private OnClickListener mEastListener = new OnClickListener()
    {
        public void onClick(View v)
        {
            // Here we start the next activity, and then call finish()
            // so that our own will stop running and be removed from the
            // history stack.
            Intent intent = new Intent();
            TowersFacebook.currentView = 3;
            intent.setClass(TowersFacebook.this, EastView.class);
            startActivity(intent);

        }
    };
    
    private OnClickListener mWestListener = new OnClickListener()
    {
        public void onClick(View v)
        {
            // Here we start the next activity, and then call finish()
            // so that our own will stop running and be removed from the
            // history stack.
            Intent intent = new Intent();
            TowersFacebook.currentView = 4;
            intent.setClass(TowersFacebook.this, WestView.class);
            startActivity(intent);

        }
    };
    
    public void SetTextBlack(Button[][] bottons) {
      	int lenB = bottons.length;
      	int len2B = bottons[0].length;
      	for (int i = 0 ; i < lenB ;  i++) {
      		for (int j = 0 ; j < len2B ;  j++) {
      		    bottons[i][j].setTextColor(Color.BLACK);
      		}
      	}
    }
    
    public void FillReferenceTabl(Button[][] bottons, int[][] values) {
      	int lenB = bottons.length;
      	int len2B = bottons[0].length;
      	int lenV = values.length;
      	int len2V = values[0].length;
      	if (lenB != lenV || len2B != len2V) {
      		Log.v("ERROR", "FillReferenceTabl: different lengths, bottons[" 
      			+ lenB + "][" + len2B + "]  values[" + lenV + "][" + len2V + "] ");
      		return;
      	}
      	for (int i = 0 ; i < lenB ;  i++) 
      		FillReferenceRow(bottons[i], values[i]);
    }
    
    public void FillReferenceRow(Button[] bottons, int[] values) {
      	int lenB = bottons.length;
      	int lenV = values.length;
      	if (lenB != lenV) {
      		Log.v("ERROR", "FillReferenceRow: different lengths, bottons=" + lenB + "values=" + lenV);
      		return;
      	}
      	for (int i = 0 ; i < lenB ;  i++) 
      		bottons[i].setText(Integer.toString(values[i]));
    }
    
    
    
    
    
} // End of class "Towers"