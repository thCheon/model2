package util;

public class Common {
	public static String getCode(String code) {
		return code.replaceAll(" ", "&nbsp;").replaceAll("<", "&lt").replaceAll("\n", "<br>").replaceAll(">", "<&gt");
	}
	public static String replaceData(String data) {
		return data.replaceAll("\\?Redirect=Log&amp;logNo=","/");
	}
}
