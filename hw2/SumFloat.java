public class SumFloat{
	public static void main(String[] args){
		float sum = 0;
		for(int i = 0; i < args.length; i++){
			sum += getSum(args[i]);
		}
		System.out.println(sum);
	}
	public static float getSum(String s){
		float sum=0;
		s += " /";
		s=s.replace("+","");
		int index=0;
		while(index < s.length()){
			s=s.strip();
			if(Character.isWhitespace(s.charAt(index))){
				sum += Float.parseFloat(s.substring(0,index));
				s = s.replaceFirst(s.substring(0,index),"");
				index = 0;
			}
			else index++;
		}
		return sum;
	}
} 
