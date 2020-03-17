package a0.t2;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;


public class Heron {
	double x=10.0;
	double epsilon=10e-7;
	double sol;
	
	public void get_inputs() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("Bitte x eingeben");
		try {
			String temp = reader.readLine();
			if (!temp.isBlank()) {
				this.x= Double.parseDouble(temp);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Bitte epsilon eingeben");
		try {
			String temp = reader.readLine();
			if (!temp.isBlank()) {
				this.epsilon=Double.parseDouble(temp);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void calcSol() {
		double a = (1+this.x)/2;
		double a_new=0;
		
		do {
			a_new=(a+this.x/a)/2;
			a=a_new;
		}while(a_new -a > this.epsilon);

		this.sol=a_new;
	}

}
