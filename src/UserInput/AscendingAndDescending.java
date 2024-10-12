import java.util.Scanner;
public class AscendingAndDescending {
	public static void AscendingAndDescending_Sorting(int parameterArray[], String type){ 
        if(type == "A"){
            int tempPosition = 0,tempVar = 0;
            for (int i=0;i<parameterArray.length;i++){ 
                tempPosition = i; 
                for(int j=i+1;j<parameterArray.length;j++){
                    if(parameterArray[j]<parameterArray[tempPosition])tempPosition=j;
                }
                tempVar=parameterArray[tempPosition];
                parameterArray[tempPosition]=parameterArray[i]; 
                parameterArray[i]=tempVar; 
            }
        }
        else{
            int tempPosition = 0,tempVar = 0;
            for (int i=0;i<parameterArray.length;i++){ 
                tempPosition = i; 
                for(int j=i+1;j<parameterArray.length;j++){
                    if(parameterArray[j]>parameterArray[tempPosition])tempPosition=j;
                }
                tempVar=parameterArray[tempPosition];
                parameterArray[tempPosition]=parameterArray[i]; 
                parameterArray[i]=tempVar; 
            }
        }
    }
    public static String printArray(int parameterArray[]){
        String returnString = "";
        for (int i=0;i<parameterArray.length;i++){
            if(i<parameterArray.length-1){returnString+=parameterArray[i]+" ";}
            else{returnString+=parameterArray[i];}
        }
        return returnString;
    } 
	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
    	int[] integerArray = new int[3];
        System.out.print("User input: ");
        for(int k=0;k<3;k++){
            integerArray[k] = input.nextInt();
        }
        System.out.print("Output:\n");
        AscendingAndDescending.AscendingAndDescending_Sorting(integerArray, "A");
        String pringArr = AscendingAndDescending.printArray(integerArray);
        System.out.println("In ascending order: "+pringArr);
        AscendingAndDescending.AscendingAndDescending_Sorting(integerArray, "D");
        pringArr = AscendingAndDescending.printArray(integerArray);
        System.out.println("In descending order: "+pringArr);
	}
}