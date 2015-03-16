//Counts the number of repetitions of each word in a document

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
public class CountWordsDriverAVemuriPeriod7 {
	static CountWords cw = new CountWords();
	static ArrayList<Comparable> a = new ArrayList<Comparable>();
	
	public CountWordsDriver(){}
	
	public static void main(String[]args){
		File inFile = new File("simple.txt");
		Scanner kb = null;
		//reading
		try{
			kb = new Scanner(inFile);
		}
		catch (FileNotFoundException e){
			System.out.println("File was not found.");
		}
		while(kb.hasNext()){
			String x = kb.next();
			x = x.toLowerCase();
			a.add(x);
		}
		cw.readFile(a);	
		cw.sortList();
		cw.countWords();
	}
}

class CountWords {
	ArrayList<Comparable> list = new ArrayList<Comparable>();
	ArrayList<Comparable> words = new ArrayList<Comparable>();
	int size = list.size();
	
	public int size(){
		return size;
	}
	
	public void readFile(ArrayList b){
		list = b;
	}
	
	public void sortList(){
		  for (int row = list.size()-1; row >= 0; row--){
				for(int col = 0; col < row; col++){
					if (list.get(col).compareTo(list.get(col+1)) < 0){
						Comparable temp = list.get(col);
						list.set(col, list.get(col+1));
						list.set(col+1, temp);
					}
				}
			}
		/*System.out.println();
		System.out.println("Bubble Sort");
		System.out.println();*/
	}
	
	public void countWords(){
		while (list.size() > 1){
			int count = 0;
			int i = list.size() - 1;
			Comparable word = list.get(i);
			do{
				count++;
				list.remove(i);
				i--;
			}
			while (word.compareTo(list.get(i)) == 0);
			/*if (count < 10){
				words.add("0" + count + word);
			}
			else
				words.add("" + count + word);*/
			words.add("" + count + word);
			//System.out.println(words);
		}
		words.add("01" + list.get(0));
		//System.out.println("end" + words);
		//return words;
		sortWords(words);
	}
	public void sortWords(ArrayList<Comparable> list){
		  for (int row = list.size()-1; row >= 0; row--){
				for(int col = 0; col < row; col++){
					if (list.get(col).compareTo(list.get(col+1)) < 0){
						Comparable temp = list.get(col);
						list.set(col, list.get(col+1));
						list.set(col+1, temp);
					}
				}
			}
		/*System.out.println();
		System.out.println("Bubble Sort");
		System.out.println();*/
		printData(list);
	}
	
	public void printData(ArrayList<Comparable> a){
		ArrayList<Comparable> words = new ArrayList<Comparable>();
		ArrayList<Comparable> freq = new ArrayList<Comparable>();
		for (int i = 0; i < 29; i++){
			String word = (String) a.get(i);
		/*	if (word.charAt(0) ==  '0')
				freq.add(word.substring(0, 2));
			else
				freq.add(word.substring(1, 2));*/
			freq.add(word.substring(0, 1));
			words.add(word.substring(1));
			System.out.println("" + (i+1) + "\t" + freq.get(i) + "\t" + words.get(i));
		}
		System.out.println("Number of unique words used = " + a.size());
		System.out.println("Total # of words = " + size());
	}
}

class Word implements Comparable<Word>{
	String myWord;
	int myCount;
	
	public Word(String word, int count){
		myWord = word;
		myCount = count;
	}
	
	@Override
	public int compareTo(Word arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
