package BioDIGAPI;

import java.util.Arrays;

public class test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BioDIGAPI newBioDIG = new BioDIGAPI("http://192.168.88.143/api/");
		
		Color testColor = new Color(40,20,20);
		//System.out.println(testColor.toString());
		// Test GET for Tag api
		Tags testTag = newBioDIG.getTAG(1);
		System.out.println(testTag.toString());
				
		Point point1 = new Point(150,100);
		Point point2 = new Point(220,210);
		Point[] testPoint = new Point[] {point1,point2};
		//newBioDIG.editTAG(1,testPoint,testColor,"kakaka");
				
		//newBioDIG.editTAG(1,1,testPoint,testColor,"kakaka");
				
		//newBioDIG.deleteTAG(4);
		

	}

}
