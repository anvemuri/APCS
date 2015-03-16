public class RomanNumeralsDriver {
	public RomanNumeralsAVemuriPeriod7(){}
	public static void main(String [] args){
		int x = 0;
		RomanNumbers n = new RomanNumbers();
		x = n.romanToArabic("I");
		System.out.print("I = " + n.romanToArabic("I"));
		System.out.println(" = " + n.arabicToRoman(x));
		x = n.romanToArabic("IV");
		System.out.print("IV = " + n.romanToArabic("IV"));
		System.out.println(" = " + n.arabicToRoman(x));
		x = n.romanToArabic("LXIII");
		System.out.print("LXIII = " + n.romanToArabic("LXIII"));
		System.out.println(" = " + n.arabicToRoman(x));
		x = n.romanToArabic("LXIV");
		System.out.print("LXIV = " + n.romanToArabic("LXIV"));
		System.out.println(" = " + n.arabicToRoman(x));
		x = n.romanToArabic("DCCXLV");
		System.out.print("DCCXLV = " + n.romanToArabic("DCCXLV"));
		System.out.println(" = " + n.arabicToRoman(x));
		x = n.romanToArabic("MCMLXXIII");
		System.out.print("MCMLXXIII = " + n.romanToArabic("MCMLXXIII"));
		System.out.println(" = " + n.arabicToRoman(x));
		x = n.romanToArabic("MMMDXCIII");
		System.out.print("MMMDXCIII = " + n.romanToArabic("MMMDXCIII"));
		System.out.println(" = " + n.arabicToRoman(x));
	}
}
class RomanNumbers{
	String num;
	int arabic;
	public RomanNumbers(){}
	
	public String arabicToRoman(int x) {
		String roman = "";
        int num = x;
        while (num >= 1000){
           roman+= "M";
           num-=1000;
        }
        while (num >= 900){
           roman+= "CM";
           num-=900;
        }
        while (num >= 500){
           roman+= "D";
           num-=500;
        }
        while (num >= 400){
           roman+= "CD";
           num-=400;
        }
        while (num >= 100){
           roman+= "C";
           num-=100;
        }
        while (num >= 90){
           roman+= "XC";
           num-=90;
        }
        while (num >= 50){
           roman+= "L";
           num-=50;
        }
        while (num >= 40){
           roman+= "XL";
           num-=40;
        }
        while (num >= 10){
           roman+= "X";
           num-=10;
        }
        while (num >= 9){
           roman+= "IX";
           num-=9;
        }
        while (num >= 5){
           roman+= "V";
           num-=5;
        }
        while (num >= 4){
           roman+= "IV";
           num-=4;
        }
        while (num >= 1){
           roman+= "I";
           num-=1;
        }    
        return roman;
	}
	
	public int romanToArabic(String x) {
		int arabic = 0;
		int length = x.length();
		int i = 0;

		while (i < length){
			char l = x.charAt(i);
			int num = toNum(l);
			
			i++;
			if (i == length)
				arabic += num;
			else {
				char k = x.charAt(i);
				int numNext = toNum(k);
				if (numNext > num){
					 arabic += (numNext - num);
					 i++;
				}
				else
					arabic += num;
			}
		}//while
		
		return arabic;
	}//end romanToArabic
	
	private int toNum(char letter) {
	   switch (letter) {
	      case 'I':  return 1;
	      case 'V':  return 5;
	      case 'X':  return 10;
	      case 'L':  return 50;
	      case 'C':  return 100;
	      case 'D':  return 500;
	      case 'M':  return 1000;
	      default:   return -1;
	   }
	}
}
