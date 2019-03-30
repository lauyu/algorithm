package com.test;

public class Unzip {

	public static void unzip(String src) {
		int i = 0;
		int count = 0;
		Character last = null;
		StringBuilder builder = new StringBuilder(src.length());
		while (i<src.length()) {
			char cur = src.charAt(i);
//			System.out.println("cur="+cur + ", last="+last);
			if(isDigital(cur)) {
				count = Character.digit(cur, 10);
				if((i+1)>=src.length() 
						|| Character.isDigit(src.charAt(i+1)) 
						|| (last!=null && last.charValue() == src.charAt(i+1)) ) {
					System.out.println("!error");
					return;
				}
				last = src.charAt(++i);
				while (count>0) {
					builder.append(last.charValue());	
					count--;
				}
//				last = null;
			} else {
				if(last!=null && last.charValue() == cur){
					System.out.println("!error");
					return;
				}
				last = cur;
				builder.append(cur);
			}
			i++;
		}
		System.out.println(builder.toString());
	}
	
	public static void unzip2(String str) {
		int i = 0;
		int count = 1;
		Character last = null;
		StringBuilder builder = new StringBuilder(str.length());
		while (i<str.length()) {
			char cur = str.charAt(i);
//			System.out.println("cur="+cur + ", last="+last);
			if(count>1 && isDigital(cur)) {
				System.out.println("!error");
				return;
			}
			if(isDigital(cur)) {
				count = Character.digit(cur, 10);
				i++;
				continue;
			}
			if (last != null && last.charValue() == cur) {
				System.out.println("!error");
				return;
			}
			last = cur;
			while (count>=1) {
				builder.append(cur);
				count--;
			}
			count = 1;
			i++;
		}
		if(count>1) {
			System.out.println("!error");
			return;	
		}
		System.out.println(builder.toString());
		
	}

	private static boolean isDigital(char cur) {
		return Character.isDigit(cur);
	}
	
	public static void main(String[] args) {
//		unzip("3a2b4c");
		unzip2("cd3adb4c2b");
//		System.out.println(Character.digit('6', 10));
//		System.out.println("3abb4cd".charAt(1));
	}

}
