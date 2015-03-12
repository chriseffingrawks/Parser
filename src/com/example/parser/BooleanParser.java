/************************************************************************************
 * Build a logical expression calculator. The case sensitive keywords are:
 * 
 * 		NOT, TRUE, FALSE, AND, OR, parentheses and the period
 * 
 * Whitespace serves to separate alphabetic keywords. 
 * The AND operator should bind more tightly than OR. 
 * NOT is the tightest binding. 
 * A statement is terminated with a period. 
 * Parentheses allow one to overrule that precedence, such that:
 * 
 * 		FALSE OR TRUE AND FALSE OR NOT TRUE.
 * 
 * 				is equivalent to
 * 
 * 		FALSE OR (TRUE AND FALSE) OR NOT TRUE.
 *************************************************************************************
 * @author Christopher Bhagwandin
 *************************************************************************************
 * NOTES:
 * 	-This solution attempts to use Edsger W. Dijkstra Shunting Yard Algorithm, however
 *   done so unsuccessfully.
 *  -This uses two stacks to store the operands and the operators(values in the code)
 *  -The solution only works for simple 2 value test cases. ("FALSE OR TRUE .")
 *  -NOT does not work as expected.
 * 
 * ISSUES FACED:
 *  -The specification called for the space to separate only the alphabetic keywords.
 *   I did not stick to that requirement too strictly, so my code requires that all
 *   keywords (including the period) to be separated by whitespace.
 *   Provide input like below:
 *   		FALSE OR TRUE AND FALSE OR NOT TRUE .
 *  -The bindings proved to be additionally difficult than first anticipated. Perhaps
 *   a different solution could solve both these problems. (I did think of using a Tree
 *   to help parse the expression, but was not sure how I would populate the Tree to
 *   ensure the correct bindings. Need to review Context Free Grammar/parse trees).
 *  -The parenthesis also caused troubles and though they are in the case statements,
 *   do not help to accomplish the task.
 *   
 *************************************************************************************/

package com.example.parser;

import java.util.Scanner;
import java.util.Stack;

public class BooleanParser {

	public static Stack<String> operators;
	public static Stack<Boolean> values;

	public static void main(String[] args){

		operators = new Stack<String>();
		values = new Stack<Boolean>();

		Scanner sc = new Scanner(System.in);

		while(sc.hasNext()){

			String s = sc.next();

			if(s.equals(".")){
				//System.out.println("\n end of expression");
				break;				
			}else{
				switch(s){
				case "(":
					operators.push(s);
					continue;
				case "AND": 
					operators.push(s);
					continue;
				case "OR": 
					operators.push(s);
					continue;
				case "NOT": 
					operators.push(s);
					continue;
				case ")":

					String op = operators.pop();
					boolean v = values.pop();

					switch(op){
					case "AND": 
						v = v && values.pop();
						continue;
					case "OR":
						v = v || values.pop();
						continue;
					case "NOT":
						v = ! v;
						continue;
					}
					
					//push evaluated value onto values stack
					values.push(v);
					
					continue;
					
				default:
					if(s.equals("TRUE")){
						values.push(true);
					}else if(s.equals("FALSE")){
						values.push(false);
					}else{
						System.out.println("Parse error");
					}
				}
			}
		}//end of while

		if(!values.isEmpty()){
			System.out.println(values.pop());
		}else{
			System.out.println("Parse error");
		}
		
	}

}
