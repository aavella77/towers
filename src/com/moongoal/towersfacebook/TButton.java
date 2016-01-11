package com.moongoal.towersfacebook;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;


/**
 * TButton (Tablero Button)
 */
public class TButton extends Button {

	 private int TowerHeigth = 0;

     /**
      * Constructors
      */
     public TButton(Context context) {
          super(context);
     }
     public TButton(Context context, AttributeSet attrs) {
    	 super(context, attrs);
     }
     
     /**
	   * methods
	   */	  
	  public void  setTowerHeight(int newValue) {
	       TowerHeigth = newValue;
	  } 
	  public int  getTowerHeight() {
	      return TowerHeigth;
	  }

     
}