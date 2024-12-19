
public class Utility {
    public static boolean isEmpty(String str) {
		if (str == null) {
			return true;
		}
		if (str.trim().equals("")) {
			return true;
		}
		return false;
	}

	public static boolean isEmpty(Object obj) {
		if (obj == null) {
			return true;
		}
		return false;
	}

	public static boolean isInteger(String str) {
		return str.matches("\\d+");
	}

	public static boolean isDigit(String str) {
		return str.matches("\\[0-9]+.?[0-9]+");
	}

}
