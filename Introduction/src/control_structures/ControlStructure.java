package control_structures;

public class ControlStructure {
	public static void main(String[] args) {
		int i =2;
		switch (i) {
		case 2:
			System.out.println("zwei");
			break;
		case 1:
			System.out.println("eins");
			break;
		default:
			System.out.println("Ja, keine Ahnung");			
		}
		//With fall through
		switch (i) {
		case 2:
			System.out.println("zwei");
		case 1:
			System.out.println("eins");
		default:
			System.out.println("Ja, keine Ahnung");			
		}
		
		//while loop
		//ausgabe 2-9
		System.out.println("While-Schleife");
		while(i < 10) {
			System.out.println(i);
			i++;
		}
		i=0;
		
		//do while loop
		//Ausgabe 0 bis 9
		System.out.println("Do-While-Schleife");
		do {
			System.out.println(i);
			i++;			
		}while(i < 10);
		
		//for loop
		//Ausgabe 0 bis 9
		System.out.println("For-Schleife");
		for(int j=0; j < 10; j++) {
			System.out.println(j);
		}
		
		//if statement
		System.out.println("If");
		i=0;
		if (i > 0) {
			System.out.println("i ist größer als 0");			
		}
		else {
			System.out.println("i ist kleiner oder gleich 0");
		}
		
		//ternary operator
		System.out.println("ternary");
		//if i is greater then 0 it will be set to 1 else to 2
		i=(i > 0) ? 1 : 2;
		System.out.println(i);

	}
}
