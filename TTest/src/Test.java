import java.util.ArrayList;
import java.util.Stack;

import javax.script.ScriptException;

public class Test {

	// �迭�� ��Ұ� ���������� �ǿ����� ���� �ĺ��ϴ� �޼ҵ�
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

	// �������� ���� �켱������ Return �Ѵ�.
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

	// ����ǥ����� ����ǥ������� ��迭 �Ͽ� Return
	public static String InfixToPost(String input) {	

		String array[] = input.split(" ");
		String result = "";

		// �����ڸ� ���� Stack ����
		Stack<String> operStack = new Stack<String>();

		// �ǿ����ڸ� ���� ArrayList ����
		ArrayList<String> str = new ArrayList<>();

		for (String string : array) {

			// ������ �ϰ��
			if (isOper(string)) {

				// �ݴ� ��ȣ�� ������ ��� Stack ��Ҹ� Pop �Ͽ� ArrayList�� Add
				if (string.equals(")") || string == ")") {

					while (operStack.peek() == "(") {
						str.add(operStack.pop());
					}

				}

				// �����ڸ� Stack�� �������, Stack�� Peek �����ڿ��� �켱������ ������
				// Peek �����ڰ� �켱������ �� ������� ��� Pop�ѵ� Push �Ѵ�.
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

				// Stack�� ��������� �켱���� ���� �ʿ䰡 ������ �׳� Push
				
			}
			// �����ڰ� �ƴϸ� �ǿ����� �̹Ƿ� ��� list�� �׳� add
			else {
				str.add(string);
			}

		}

		// Stack�� ��� ��Ҹ� List�� Peek ���� Add
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
	

		// �����ڸ� ���� Stack ����
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

