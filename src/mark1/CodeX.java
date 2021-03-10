package mark1;

public class CodeX {
	public static void main(String[] args) {
		try {
			int a=0;
			int b=3;
			int c=a/b;
			//System.out.println(c);
		}catch(Exception e) {
			System.out.println("exception");
		}finally {
			System.out.println("Finally");
		}
	}
}
