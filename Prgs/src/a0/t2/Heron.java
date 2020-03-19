package a0.t2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Heron {
	double x=10.0;
	double epsilon=10e-7;
	double sol;
	
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getEpsilon() {
		return epsilon;
	}

	public void setEpsilon(double epsilon) {
		this.epsilon = epsilon;
	}

	public double getSol() {
		return sol;
	}

	public void setSol(double sol) {
		this.sol = sol;
	}

	public void get_inputs() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("Bitte x eingeben");
		try {
			String temp = reader.readLine();
			if (!temp.isEmpty()) {
				this.x= Double.parseDouble(temp);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Bitte epsilon eingeben");
		try {
			String temp = reader.readLine();
			if (!temp.isEmpty()) {
				this.epsilon=Double.parseDouble(temp);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void calcSol() {
		double a = (1+this.x)/2;
		double a_new=(a+this.x/a)/2;
		
		do {
			a=a_new;
			a_new=(a+this.x/a)/2;
		}while(a -a_new > this.epsilon);

		this.sol=a_new;
	}

}
