import java.util.*;
public class P6_33_FloodGrid {
	public static void main(String[] args){
		Scanner kb = new Scanner (System.in);
		
		System.out.print("Terrain length: ");
		int l = kb.nextInt();
		System.out.print("Terrain width: ");
		int w = kb.nextInt();
		
		double[][] heights = new double [l][w];
		
		for (int m = 0; m < heights.length; m++){
			for (int n = 0; n<heights[0].length; n++ ){
				System.out.print("Height of terrain at "+m+", "+n+" :");
				heights[m][n] = kb.nextDouble();				
			}
		}
		
		printMatrix(heights);
		
		String[][] marker = new String[l][w];
		for (int m = 0; m < marker.length; m++){
			for (int n = 0; n<marker[0].length; n++ ){
				marker[m][n] = "  ";			
			}
		}
		
		System.out.print("Water level:");
		double waterLevel = kb.nextDouble();
		visualizeByIncrements(heights, waterLevel, marker);
		
		/*
		double[][] testHeights = {{1,2,3,1},{2,3,4,2},{2,4,1,4},{2,3,4,2}};
		printMatrix(testHeights);
		String[][] testMarker = new String[4][4];
		for (int m = 0; m < testMarker.length; m++){
			for (int n = 0; n<testMarker[0].length; n++ ){
				testMarker[m][n] = "  ";			
			}
		}
		
		double testWaterLevel = 4;
		
		//Tests fromEdge
		printMatrix(testHeights);
		fromEdge(testHeights, 2, testMarker);
		printMatrix(testMarker);
		fromEdge(testHeights, 3, testMarker);
		printMatrix(testMarker);
		fromEdge(testHeights, 4, testMarker);
		printMatrix(testMarker);
		
		
		//Tests fromX
		printMatrix(testHeights);
		fromEdge(testHeights, 2, testMarker);
		fromX(testHeights, 2, testMarker);
		printMatrix(testMarker);
		fromEdge(testHeights, 3, testMarker);
		fromX(testHeights, 3, testMarker);
		printMatrix(testMarker);
				
		//Tests visualizeByIncrements
		visualizeByIncrements(testHeights, testWaterLevel, testMarker);
		*/
	}
	
	/*public static void floodMap (double[][] heights, double waterLevel){
		for (int n = 0; n < heights[0].length; n++){
			for (int m = 0; m<heights.length; m++ ){
				if (heights[m][n] <= waterLevel)
					System.out.print("* ");
				else
					System.out.print("  ");
			}
			System.out.println();
		}
		
	}*/
	
	/**
	 * Tests the edges for points of entry based on water level.
	 * @param heights
	 * @param rise
	 * @param marker
	 * @return marker[][]
	 */
	public static String[][] fromEdge(double[][] heights, double rise, String[][] marker){
		//tests edge 0, 0 --> length-1
		for( int n = 0; n < heights.length; n++){
			if (heights [0][n] <= rise)
				marker [0][n] = "* ";
		}
		
		//tests edge 0 --> length[0]-1,0
		for (int m = 0; m< heights[0].length; m++){
			if (heights [m][0] <= rise)
				marker [m][0] = "* ";
		}
		
		//tests edge length[0]-1, 0 --> length -1
		for( int n = 0; n < heights.length; n++){
			if (heights [heights[0].length-1][n] <= rise)
				marker [heights[0].length-1][n] = "* ";
		}
		
		//tests edge length 0 --> length[0]-1, length -1
		for (int m = 0; m< heights[0].length; m++){
			if (heights [m][heights.length-1] <= rise)
				marker [m][heights.length-1] = "* ";
		}
		
		return marker;
	}
	
	/**
	 * Entry of water from pre-existing flooded areas.
	 * @param heights
	 * @param rise
	 * @param marker
	 * @return updated marker
	 */
	public static String[][] fromX(double[][] heights, double rise, String[][] marker){
		//Find the * in the array.
		for (int n = 0; n < heights[0].length; n++){
			for (int m = 0; m <heights.length; m++ ){
				if ( marker[m][n].equals("* ")){
					//tests if (m+1, n) needs to be marked. With bounds check.
					if (( m+1 < heights.length) && (heights[m+1][n] <= rise)){
						marker[m+1][n] = "* ";
					}
					
					//tests if (m-1, n) needs to be marker. With bounds check.
					if ((m-1 > 0) && (heights[m-1][n] <= rise)){
						marker[m-1][n] = "* ";
					}
					
					//tests if (m, n+1) needs to be marker. With bounds check.
					if (( n+1 < heights[0].length)&&(heights[m][n+1] <= rise)){
						marker[m][n+1] = "* ";
					}
					
					//tests if (m, n-1) needs to be marker. With bounds check.
					if (( n-1 > 0)&&(heights[m][n-1] <= rise)){
						marker[m][n-1] = "* ";
					}
				}
			}
		}
		return marker;
	}
	/**
	 * Runs increment visualization until water level is reached.
	 * @param heights
	 * @param waterLevel
	 * @param marker
	 */
	public static void visualizeByIncrements(double[][] heights, double waterLevel, String[][] marker){
		Scanner kb = new Scanner (System.in);
		System.out.print("Amount increment for each step: ");
		double increment = kb.nextDouble();
		double tempWaterLevel = 0;
		while (tempWaterLevel <= waterLevel){
			System.out.println("Current water level: "+tempWaterLevel);
			fromEdge(heights, tempWaterLevel, marker);
			fromX(heights, tempWaterLevel, marker);
			printMatrix(marker);	
			tempWaterLevel += increment;
		}
	}
	
	/**
	 * Following 2 overloaded methods print the arrays.
	 * @param matrix
	 */
	public static void printMatrix(double[][] matrix)
    {
        int row, col;

        for (row = 0; row < matrix.length; row++)
        {
            for (col = 0; col < matrix[row].length; col++)
                System.out.print(matrix[row][col]+"\t");

            System.out.println();
        }
        System.out.println();
    }
	
	public static void printMatrix(String[][] matrix)
    {
        int row, col;

        for (row = 0; row < matrix.length; row++)
        {
            for (col = 0; col < matrix[row].length; col++)
                System.out.print(matrix[row][col]);

            System.out.println();
        }
        System.out.println();
    }
}

/*
Terrain length: 4
Terrain width: 4
Height of terrain at 0, 0 :1
Height of terrain at 0, 1 :2
Height of terrain at 0, 2 :3
Height of terrain at 0, 3 :1
Height of terrain at 1, 0 :2
Height of terrain at 1, 1 :3
Height of terrain at 1, 2 :4
Height of terrain at 1, 3 :2
Height of terrain at 2, 0 :2
Height of terrain at 2, 1 :4
Height of terrain at 2, 2 :1
Height of terrain at 2, 3 :4
Height of terrain at 3, 0 :2
Height of terrain at 3, 1 :3
Height of terrain at 3, 2 :4
Height of terrain at 3, 3 :2
1.0	2.0	3.0	1.0	
2.0	3.0	4.0	2.0	
2.0	4.0	1.0	4.0	
2.0	3.0	4.0	2.0	

Water level:5
Amount increment for each step: .7
Current water level: 0.0
        
        
        
        

Current water level: 0.7
        
        
        
        

Current water level: 1.4
*     * 
        
        
        

Current water level: 2.0999999999999996
* *   * 
*     * 
*       
*     * 

Current water level: 2.8
* *   * 
*     * 
*       
*     * 

Current water level: 3.5
* * * * 
* *   * 
*       
* *   * 

Current water level: 4.2
* * * * 
* * * * 
* * * * 
* * * * 

Current water level: 4.9
* * * * 
* * * * 
* * * * 
* * * * 

*/
