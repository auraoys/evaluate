import java.util.ArrayList;
import java.util.Stack;

import javax.script.ScriptException;

public class Test {

	// 배열의 요소가 연산자인지 피연산자 인지 식별하는 메소드
	public static boolean isOper(String input) {

		if (input == "+" || input.equals("+")) {
			return true;
		} else if (input == "-" || input.equals("-")) {
			return true;
		} else if (input == "*" || input.equals("*")) {
			return true;
		} else if (input == "/" || input.equals("/")) {
			return true;
		} else if (input == "(" || input.equals("(")) {
			return true;
		} else if (input == ")" || input.equals(")")) {
			return true;
		}

		return false;
	}

	// 연산자의 연산 우선순위를 Return 한다.
	public static int Priority(String input) {

		if (input == "+" || input.equals("+")) {
			return 1;
		} else if (input == "-" || input.equals("-")) {
			return 1;
		} else if (input == "*" || input.equals("*")) {
			return 2;
		} else if (input == "*" || input.equals("*")) {
			return 2;
		} else if (input == "(" || input.equals("(")) {
			return 0;
		} else if (input == ")" || input.equals(")")) {
			return 0;
		}

		return 0;

	}

	// 중위표기식을 후위표기식으로 재배열 하여 Return
	public static String InfixToPost(String input) {	

		String array[] = input.split(" ");
		String result = "";

		// 연산자를 담을 Stack 선언
		Stack<String> operStack = new Stack<String>();

		// 피연산자를 담을 ArrayList 선언
		ArrayList<String> str = new ArrayList<>();

		for (String string : array) {

			// 연산자 일경우
			if (isOper(string)) {

				// 닫는 괄호를 만나면 모든 Stack 요소를 Pop 하여 ArrayList에 Add
				if (string.equals(")") || string == ")") {

					while (operStack.peek() == "(") {
						str.add(operStack.pop());
					}

				}

				// 연산자를 Stack에 담기전에, Stack에 Peek 연산자와의 우선순위를 따지고
				// Peek 연산자가 우선순위가 더 높을경우 모두 Pop한뒤 Push 한다.
				if (!operStack.isEmpty()) {

					if(string == "(") {
						operStack.push(string);
					}else if (Priority(operStack.peek()) > Priority(string)) {
						str.add(operStack.pop());
						operStack.push(string);
					} else {
						operStack.push(string);
					}
				}else {
					operStack.push(string);
				}

				// Stack이 비어있으면 우선순위 따질 필요가 없으니 그냥 Push
				
			}
			// 연산자가 아니면 피연산자 이므로 결과 list에 그냥 add
			else {
				str.add(string);
			}

		}

		// Stack의 모든 요소를 List에 Peek 부터 Add
		for (int i = 0; i <= operStack.size(); i++) {
			str.add(operStack.pop());
		}
		
		while(str.indexOf("(") > 0) {
			str.remove( str.indexOf("("));
		}
		
		while(str.indexOf(")") > 0) {
			str.remove( str.indexOf(")"));
		}
		
		for (String item : str) {
			result += ' '+item;
		}

		return result.substring(1,result.length());
	}
	
	public static void CalcPost(String input) {
		
		
		String array[] = input.split(" ");
	

		// 연산자를 담을 Stack 선언
		Stack<String> Stack = new Stack<String>();
		
		String popBox1 = "";
		String popBox2 = "";
		
		int result = 0;
		
		
		for (String item : array) {
			
			if(isOper(item)) {
				
				popBox2 = Stack.pop();
				popBox1 = Stack.pop();
				
				switch (item) {
				case "+":					
					Stack.push( Double.toString(Double.parseDouble(popBox2) + Double.parseDouble(popBox1) ));									
					break;
				case "-":
					Stack.push( Double.toString(Double.parseDouble(popBox2) - Double.parseDouble(popBox1) ));											
					break;
				case "*":
					Stack.push( Double.toString(Double.parseDouble(popBox2) * Double.parseDouble(popBox1) ));										
					break;
				case "/":
					Stack.push( Double.toString(Double.parseDouble(popBox2) / Double.parseDouble(popBox1) ));										
					break;
				}
				
			}else {
				Stack.push(item);
			}
			
		}
		
		
		
		System.out.println(Stack.pop());
		
	}
	
	public static void evaluate(String input) {
		String postBox = InfixToPost(input);
		CalcPost(postBox);
	}

	public static void main(String[] args) throws ScriptException {
		// TODO Auto-generated method stub
		
		evaluate("1 + 2 * 3");
		evaluate("( 1 + 2 ) * 3");
		evaluate("1 / 32.5 + 167 * ( 3498 - 1155 ) * -721 * ( 4885 - 1 ) / 0.5");

	}
	
}

