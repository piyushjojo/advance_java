package tester;

import java.util.Locale;
import java.util.ResourceBundle;

public class TestI18N {

	public static void main(String[] args) {
		//create default locale
		Locale locale=new Locale("en");
		ResourceBundle bundle=ResourceBundle.getBundle("messages",locale);
		System.out.println(bundle.getString("greeting"));
		// create a Locale
		locale=new Locale("de", "GE");
		bundle=ResourceBundle.getBundle("messages", locale);
		System.out.println(bundle.getString("greeting"));

	}

}
