import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class MyClass {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		System.out.print("hello, wolrd");
		Scanner file = new Scanner(new File("C:\\Programms\\GitHub\\Hash-Code-2017\\src\\text.txt"));
		int n = file.nextInt();
		int[] A = new int[n];
		for (int i = 0; i < n; i++) {
			A[i] = file.nextInt();
		}

		PrintWriter wr = new PrintWriter("C:\\Programms\\GitHub\\Hash-Code-2017\\src\\text2.txt");
		wr.println(Arrays.toString(A));
		wr.close();
		file.close();
	}

}
