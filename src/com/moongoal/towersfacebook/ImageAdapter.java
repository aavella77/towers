package com.moongoal.towersfacebook;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import com.moongoal.towersfacebook.TowersFacebook;

public class ImageAdapter extends BaseAdapter {
    private Context mContext;

    //private int[][] tablero = { {4,0,0,0} , {3,1,0,3} , {2,0,3,1} , {1,4,1,0} };
    int ROWS = 4;
    int COLS = 4;
    
    public ImageAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }


    
    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {  // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(100, 100));
            //imageView.setAdjustViewBounds(false);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.forceLayout();
            imageView.setPadding(0, 0, 0, 0);
        } else {
            imageView = (ImageView) convertView;
        }

        Log.d("Towers", "currentView" +TowersFacebook.currentView);
        switch (TowersFacebook.currentView) {

        case 1: //North
            int p=0;
        	for (int i = 3 ; i >= 0 ;  i--) {
          	   for (int j = 0; j < COLS; j++) {
          		  if ( TowersFacebook.tablero[i][j] == 4 ){
          		        for (int h = 3 ; h >= 0 ;  h--) {
          		     	   for (int k = 0; k < COLS; k++) {
          		     		  if ( k == j ) { // A column
          		     			 p = switchRow(j);
          			             mThumbIds[h*ROWS+p]=R.drawable.green_row_4;
          			             Log.d("Towers", "North Green" +i +j +h +k);
          		     		  }
          		     	   }
           		        }   
          		  }
          		  if ( TowersFacebook.tablero[i][j] == 3 ){
        		        for (int h = 3 ; h >= 0 ;  h--) {
        		     	   for (int k = 0; k < COLS; k++) {
        		     		  if ( ( k == j ) && ( h > 0) ){ 
        		     			 p = switchRow(j);
        			             mThumbIds[h*ROWS+p]=R.drawable.red_row_3;
          			             Log.d("Towers", "North Red" +i +j +h +k);
        		     		  }
        		     	   }
         		    }   
        		     }
          		  if ( TowersFacebook.tablero[i][j] == 2 ){
          		        for (int h = 3 ; h >= 0 ;  h--) {
          		     	   for (int k = 0; k < COLS; k++) {
          		     		  if ( ( k == j ) && ( h > 1) ){ 
          		     			 p = switchRow(j);
          			             mThumbIds[h*ROWS+p]=R.drawable.orange_row_2;
          			             Log.d("Towers", "North Orange" +i +j +h +k);
          		     		  }
          		     	   }
           		    }   
          		  }
          		  if ( TowersFacebook.tablero[i][j] == 1 ){
        		        for (int h = 3 ; h >= 0 ;  h--) {
        		     	   for (int k = 0; k < COLS; k++) {
        		     		  if ( ( k == j ) && ( h > 2) ){ 
        		     			 p = switchRow(j);
        			             mThumbIds[h*ROWS+p]=R.drawable.yellow_row_1;
          			             Log.d("Towers", "North Yellow" +i +j +h +k);
        		     		  }
        		     	   }
         		    }   
        		      }    		  
          	   }
             }
        	
        	
        break;	
        
        case 2: // South
            
            
            for (int i = 0 ; i < ROWS ;  i++) {
         	   for (int j = 0; j < ROWS; j++) {
         		  if ( TowersFacebook.tablero[i][j] == 4 ){
         		        for (int h = 0 ; h < ROWS ;  h++) {
         		     	   for (int k = 0; k < ROWS; k++) {
         		     		  if ( k == j ) { 
         			             mThumbIds[h*ROWS+k]=R.drawable.green_row_4;
          			             Log.d("Towers", "South Green" +i +j +h +k);
         		     		  }
         		     	   }
          		        }   
         		  }
         		  if ( TowersFacebook.tablero[i][j] == 3 ){
       		        for (int h = 0 ; h < ROWS ;  h++) {
       		     	   for (int k = 0; k < ROWS; k++) {
       		     		  if ( ( k == j ) && ( h > 0) ){ 
       			             mThumbIds[h*ROWS+k]=R.drawable.red_row_3;
      			             Log.d("Towers", "South Red" +i +j +h +k);
       		     		  }
       		     	   }
        		    }   
       		     }
         		  if ( TowersFacebook.tablero[i][j] == 2 ){
         		        for (int h = 0 ; h < ROWS ;  h++) {
         		     	   for (int k = 0; k < ROWS; k++) {
         		     		  if ( ( k == j ) && ( h > 1) ){ 
         			             mThumbIds[h*ROWS+k]=R.drawable.orange_row_2;
          			             Log.d("Towers", "South Orange" +i +j +h +k);
         		     		  }
         		     	   }
          		    }   
         		  }
         		  if ( TowersFacebook.tablero[i][j] == 1 ){
       		        for (int h = 0 ; h < ROWS ;  h++) {
       		     	   for (int k = 0; k < ROWS; k++) {
       		     		  if ( ( k == j ) && ( h > 2) ){ 
       			             mThumbIds[h*ROWS+k]=R.drawable.yellow_row_1;
      			             Log.d("Towers", "South Yellow" +i +j +h +k);
       		     		  }
       		     	   }
        		    }   
       		      }    		  
         	   }
            }
            break;
            

        case 3: // East
        	int m =0; 
           	for (int i = 0; i < ROWS; i++) {
           	   for (int j = 0 ; j < COLS ;  j++) {
           		  if ( TowersFacebook.tablero[i][j] == 4 ){
           		        for (int h = 3; h >= 0; h-- ) {
           		     	   for (int k = 3 ; k >= 0 ;  k--) {
           		     		  if ( k == j ) { // A row
           		     			 m = switchRow(i); 
           			             mThumbIds[h*ROWS+m]=R.drawable.green_row_4;
           			             Log.d("Towers", "East Green" +i +j +h +k + "and m=" +m);
           		     		  }
           		     	   }
            		    }   
           		  }
           		  if ( TowersFacebook.tablero[i][j] == 3 ){
         		        for (int h = 3; h >= 0; h--) {
         		     	   for (int k = 3 ; k >= 0 ;  k--) {
         		     		  if ( ( k == j ) && ( h > 0) ){
         		     			 m = switchRow(i); 
         			             mThumbIds[h*ROWS+m]=R.drawable.red_row_3;
           			             Log.d("Towers", "East Red" +i +j +h +k + "and m=" +m);
         		     		  }
         		     	   }
          		         }   
         		  }
           		  if ( TowersFacebook.tablero[i][j] == 2 ){
           		        for (int h = 3; h >= 0; h-- ) {
           		     	   for (int k = 3 ; k >= 0 ;  k--) {
           		     		  if ( ( k == j ) && ( h > 1) ){ 
           		     			 m = switchRow(i); 
           			             mThumbIds[h*ROWS+m]=R.drawable.orange_row_2;
           			             Log.d("Towers", "East Orange" +i +j +h +k + "and m=" +m);
           		     		  }
           		     	   }
            		    }   
           		  }
           		  if ( TowersFacebook.tablero[i][j] == 1 ){
         		        for (int h = 3; h >= 0; h--) {
         		     	   for (int k = 3 ; k >= 0 ;  k--) {
         		     		  if ( ( k == j ) && ( h > 2) ){ 
         		     			 m = switchRow(i); 
         		     			 mThumbIds[h*ROWS+m]=R.drawable.yellow_row_1;
           			             Log.d("Towers", "East Yellow" +i +j +h +k + "and m=" +m);
         		     		  }
         		     	   }
          		         }   
         		   }    		  
           	   }
              }
        	
        break;
        
        case 4: // West

           	for (int i = 3; i >= 0; i--) {
           	   for (int j = 3 ; j >= 0 ;  j--) {
           		  if ( TowersFacebook.tablero[i][j] == 4 ){
           		        for (int h = 3; h >= 0; h-- ) {
           		     	   for (int k = 3 ; k >= 0 ;  k--) {
           		     		  if ( k == j ) { // A row
           			             mThumbIds[h*ROWS+i]=R.drawable.green_row_4;
           			             Log.d("Towers", "East Green" +i +j +h +k);
           		     		  }
           		     	   }
            		    }   
           		  }
           		  if ( TowersFacebook.tablero[i][j] == 3 ){
         		        for (int h = 3; h >= 0; h--) {
         		     	   for (int k = 3 ; k >= 0 ;  k--) {
         		     		  if ( ( k == j ) && ( h > 0) ){
         			             mThumbIds[h*ROWS+i]=R.drawable.red_row_3;
           			             Log.d("Towers", "East Red" +i +j +h +k);
         		     		  }
         		     	   }
          		         }   
         		  }
           		  if ( TowersFacebook.tablero[i][j] == 2 ){
           		        for (int h = 3; h >= 0; h-- ) {
           		     	   for (int k = 3 ; k >= 0 ;  k--) {
           		     		  if ( ( k == j ) && ( h > 1) ){ 
           			             mThumbIds[h*ROWS+i]=R.drawable.orange_row_2;
           			             Log.d("Towers", "East Orange" +i +j +h +k);
           		     		  }
           		     	   }
            		    }   
           		  }
           		  if ( TowersFacebook.tablero[i][j] == 1 ){
         		        for (int h = 3; h >= 0; h--) {
         		     	   for (int k = 3 ; k >= 0 ;  k--) {
         		     		  if ( ( k == j ) && ( h > 2) ){ 
         		     			 mThumbIds[h*ROWS+i]=R.drawable.yellow_row_1;
           			             Log.d("Towers", "East Yellow" +i +j +h +k);
         		     		  }
         		     	   }
          		         }   
         		   }    		  
           	   }
              }
        	
        break;
        }
        
        imageView.setImageResource(mThumbIds[position]);
        return imageView;
    }
    

    // references to our images
    private Integer[] mThumbIds = {
    R.drawable.blank_row_x, R.drawable.blank_row_x,R.drawable.blank_row_x, R.drawable.blank_row_x,
    R.drawable.blank_row_x, R.drawable.blank_row_x,R.drawable.blank_row_x, R.drawable.blank_row_x,
    R.drawable.blank_row_x, R.drawable.blank_row_x,R.drawable.blank_row_x, R.drawable.blank_row_x,
    R.drawable.blank_row_x, R.drawable.blank_row_x,R.drawable.blank_row_x, R.drawable.blank_row_x,
    };
    
    public int switchRow(int x){
    	
    	switch (x){
        case 0:
    		return(3);
    	case 1:
    		return(2);
    	case 2:
    		return(1);
    	case 3:
    		return(0);
    	}
    	return(0);
    }



}

