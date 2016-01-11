/**
 * 
 */
package com.moongoal.towersfacebook;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

public final class TableroCell extends Button{

	/**
	 * the public TableroCell has one additional field
	 */
	private int TowerHeigth;
	
	/**
	 * the public TableroCell has one constructor
	 */
	  public TableroCell(Context context) {
	          super(context); 
	  }
	  
	  public TableroCell(Context context, AttributeSet attrs) {
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
