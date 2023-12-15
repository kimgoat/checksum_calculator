import java.util.Scanner;

public class Main {
	public String polyCode;
	public String data ;
	public String result;

	public int intPolycode;
	public int getIntPolycode(){
		return this.intPolycode;
	}
	public void setIntPolycode(int intPolycode){
		this.intPolycode = intPolycode;
	}
	public int intData;
	public int getIntData(){
		return this.intData;
	}
	public void setIntData(int intData){
		this.intData = intData;
	}
	public String extendNum;

	
	public static void main(String args[]) {
		Main main = new Main();
		Scanner in = new Scanner(System.in);

		System.out.print("생성다항식을 입력하시오(input polynomial code):");
		main.polyCode = in.next();
		System.out.print("수신할 데이터를 입력하시오(input data):");
		main.data = in.next();

		main.extendData();
		main.XOR();
	}

	public int binaryToInt(String binaryString) {
		return Integer.parseInt(binaryString,2);
	}

	public String intToBinary(int num){
		return Integer.toBinaryString(num);
	}


	public String addZero(String poly,int n) {
		for(int i=0; i<n; i++) {
			poly += "0";
		}

		this.setIntPolycode(this.binaryToInt(poly));
		return poly;
	}

	

	public String extendData() {
		 int degree= this.polyCode.length()-1;
		 String temp = this.data+"";
	     for(int i=0; i<degree; i++) {
	    	 temp += "0";
	     }
	     this.extendNum = temp;

		 this.setIntData(this.binaryToInt(extendNum));
	     return extendNum;
	}

	public void XOR(){
		this.addZero(polyCode, this.extendNum.length()-this.polyCode.length());

		int temp = this.getIntData() ^ this.getIntPolycode();


		while(Math.pow(2, this.polyCode.length()-1)<temp){
			this.addZero(polyCode, this.intToBinary(temp).length()-this.polyCode.length());
			this.setIntData(temp);
			temp = this.getIntData() ^ this.getIntPolycode();
		}

		int degree= this.polyCode.length()-1;
		String tmp = "";
		for(int i=0; i<degree - intToBinary(temp).length(); i++){
			tmp += "0";
		}
		this.result = this.data +tmp+ intToBinary(temp);

		System.out.print("checksum: ");
		System.out.println(result);

		this.verifyError(result);
	}

	public void verifyError(String result){
		this.addZero(polyCode, this.extendNum.length()-this.polyCode.length());
		int temp = binaryToInt(result) ^ this.getIntPolycode();
		while(Math.pow(2, this.polyCode.length()-1)<temp){
			this.addZero(polyCode, this.intToBinary(temp).length()-this.polyCode.length());
			this.setIntData(temp);
			temp = this.getIntData() ^ this.getIntPolycode();
		}
		if(temp == 0){
			System.out.println("No error");
		}else{
			System.out.println("error");
		}
	}

}
