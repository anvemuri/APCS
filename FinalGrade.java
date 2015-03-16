//Calculates the grade needed on the final exam to get the desired game in a class.

import java.util.Scanner;

public class FinalGrade {
	
	static char userinput;
	double current, newcurrent;
	double goal, newgoal;
	int weightpercent;
	double weight;
	double finalgrade;
	
	public FinalGrade(){
		userinput = 0;
		newcurrent = 0.;
		current = 0.;
		newgoal = 0.;
		goal = 0.;
		weightpercent = 0;
		weight = 0;
		finalgrade = 0.;
		
	}
	
	public static void main (String [] args) {
		FinalGrade fg = new FinalGrade();
		fg.UserInput();
		if (userinput == 80 || userinput == 112)
			fg.GradeCalculation();
	}
	
	public void UserInput() {
		Scanner keyboard = new Scanner(System.in);
		
		System.out.print("Welcome to FinalGrade.java. In this program, you will be able to calculate what you need on the final to get the grade you want. Press 'P' to procede, and press 'Q' to quit.    ");
		userinput = keyboard.next().charAt(0);
		
		if (userinput == 80 || userinput == 112) {
			System.out.print("\nEnter your current grade (Ex. 87.56% ) --> "); 
			current = keyboard.nextDouble();
			
			System.out.print("\nEnter what you want your grade to be after finals (Ex. 89.5%) --> ");
			goal = keyboard.nextDouble();
			
			System.out.print("\nWhat percent of your grade is your final? --> ");
			weightpercent = keyboard.nextInt();
			
			System.out.println();
		}
	}
	
	public void GradeCalculation(){
		weight = (double)weightpercent/100;
		///System.out.println(weight);
		newcurrent = ((1-weight)*current);
		///System.out.println("notfinals'weight *current" + newcurrent);
		newgoal = goal - newcurrent;
		///System.out.println("aftersubtracting ^^from goal" + newgoal);
		finalgrade = newgoal/weight;
		///System.out.println(finalgrade);
		if (finalgrade > 100.)
			System.out.println("Sorry, but it's not possible for you to get " + goal + "% as your grade after finals, but you would need a " + finalgrade);
		else if (finalgrade < 50.)
			System.out.println("You are able to fail the final and get the grade you want..." + finalgrade);
		else 
			System.out.println("To get a " + goal + "% in the class, you need to get atleast a " + finalgrade + "% on the final");
		
	}
	
}
