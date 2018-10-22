import java.util.ArrayList;
import java.util.Scanner;

public class WarmUp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
		Boolean run = true;
		//args.int input[] = new int[100];
		ArrayList<Integer> input = new ArrayList<>();
		while(run) {
			
			int cek =sc.nextInt();
			if(cek!=42) {
				if(cek/10<=9) {
					input.add(cek);
				}
			}else {
				run=false;
				input.add(cek);
				input.add(sc.nextInt());
			}
			
				
		}
		for(int i=0; i<input.size()-2;i++) {
			System.out.println(input.get(i));
		}
		
	}

}
