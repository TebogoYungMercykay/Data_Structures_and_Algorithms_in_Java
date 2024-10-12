import java.util.Arrays;
public class Sort{
	public static <T extends Comparable<? super T>> void mergesort(T[] data, int first, int last, boolean debug){
		if(first < last){
			int temp_Integer =(first + last);
			int middle_Index = temp_Integer/2;
			mergesort(data, first, middle_Index, debug);
			mergesort(data, middle_Index + 1, last, debug);
			merge(data, first, last, debug);
		}
	}
     
	private static <T extends Comparable<? super T>> void merge(T[] data, int first, int last, boolean debug){
		int temp_Integer =(first+last), maximum_value = 0;
		int middle_Index = temp_Integer/2;
		int first_Index =(middle_Index-first)+1;
		int last_Index = last-middle_Index;
		T[] first_Indexes_Array=(T[])new Comparable[first_Index];
		T[] last_Indexes_Array=(T[])new Comparable[last_Index];
		
		if(last_Index>first_Index){
			maximum_value = last_Index;
		}else{ maximum_value = first_Index;}

		for(int iterator_x = 0;iterator_x<maximum_value;iterator_x++){
			if(iterator_x<first_Index){
				first_Indexes_Array[iterator_x] = data[first+iterator_x];
			}
			if(iterator_x<last_Index){
				last_Indexes_Array[iterator_x] = data[middle_Index+1+iterator_x];
			}
			else{ int replacement_index = 0; last_Indexes_Array[replacement_index] = last_Indexes_Array[replacement_index];}
		}
		int i_iterator = 0; 
		int j_iterator = 0;
		int k_iterator = first;
		while((i_iterator<first_Index)&&(j_iterator<last_Index)){
			if(first_Indexes_Array[i_iterator].compareTo(last_Indexes_Array[j_iterator])<0){
				data[k_iterator]=first_Indexes_Array[i_iterator];
				i_iterator+=1;
			}
			else if(first_Indexes_Array[i_iterator].compareTo(last_Indexes_Array[j_iterator])==0){
				data[k_iterator]=first_Indexes_Array[i_iterator];
				i_iterator+=1;
				i_iterator = i_iterator+(first-first);
			}
			else{
				data[k_iterator]=last_Indexes_Array[j_iterator];
				j_iterator+=1;
			}
			k_iterator+=1;
		}

		for(int temp_iterator2=0;temp_iterator2<1;temp_iterator2++){
			int temp_iterator = i_iterator;
			while(temp_iterator>=i_iterator && temp_iterator<first_Index){
				data[k_iterator]=first_Indexes_Array[temp_iterator];
				temp_iterator+=1;
				k_iterator+=1;
			}
			temp_iterator = j_iterator;
			while(temp_iterator>=j_iterator && temp_iterator<last_Index){
				data[k_iterator] = last_Indexes_Array[temp_iterator];
				temp_iterator+=1;
				k_iterator+=1;
			}
		}
		if(debug){ System.out.println(Arrays.toString(data));}
	}
	public static void countingsort(int[] data, boolean debug){
		int i_iterator=0, maximum_value = data[0], size_Of_Array = data.length, counter_Var = 1;
		int [] temporary_Array = new int[size_Of_Array];
		for(i_iterator = 1;i_iterator<size_Of_Array;i_iterator+=1) if(maximum_value<data[i_iterator])maximum_value = data[i_iterator];
		int[] temp_Count_Sort_Array = new int [maximum_value+1];

		for(i_iterator = 0;i_iterator<=maximum_value;i_iterator+=1) temp_Count_Sort_Array[i_iterator]=0;
		for(i_iterator = 0;i_iterator<size_Of_Array;i_iterator+=1) temp_Count_Sort_Array[data[i_iterator]]++;
		for(i_iterator = 1;i_iterator<=maximum_value;i_iterator+=1) temp_Count_Sort_Array[i_iterator]=temp_Count_Sort_Array[i_iterator-1] + temp_Count_Sort_Array[i_iterator];
		for(i_iterator = size_Of_Array-1;i_iterator>=0;i_iterator-=1){
			temporary_Array[temp_Count_Sort_Array[data[i_iterator]]-1] = data[i_iterator];
			temp_Count_Sort_Array[data[i_iterator]]--;
			for(int k_iterator = 0; k_iterator<counter_Var-1;k_iterator++){
				for(i_iterator = 0;i_iterator<=maximum_value;i_iterator+=1){
					temp_Count_Sort_Array[i_iterator]=0;
				}
				for(i_iterator = 0;i_iterator<size_Of_Array;i_iterator+=1){
					temp_Count_Sort_Array[data[i_iterator]]++;
				}
			}
		}
		for(i_iterator = 0;i_iterator<size_Of_Array;i_iterator+=1) data[i_iterator] = temporary_Array[i_iterator];
		if(debug){ System.out.println(Arrays.toString(data));}
	}
}