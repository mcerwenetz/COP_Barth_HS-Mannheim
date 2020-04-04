package java_refresh.files;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;
import java.io.FileWriter;

public class Files {

	public static void main(String[] args) {
		String pathname = "C:\\Users\\Marius\\eclipse-workspace\\20cop01\\Introduction\\src\\files\\file.txt";
		File a = new File(pathname);
		
		if (!a.exists()) {
		try {
			a.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		}
		
		File b = new File("C:\\Users\\Marius\\eclipse-workspace\\20cop01\\Introduction\\src\\files\\test.txt");
		a.renameTo(b);
		a.delete();
		
		File file = new File("src\\files\\hallo.txt");
		try {
			String file_content="Hallo Welt\nNeue Zeile";
			if(!file.exists()) {
				file.createNewFile();
			}
			
			FileWriter writer = new FileWriter(file);
			
			writer.write(file_content);
			writer.flush();
			writer.close();
			
			FileReader fr = new FileReader(file);
			int filelength = (int) file.length();
			char [] text = new char[filelength];
			fr.read(text);
			
			for (char c : text)
				System.out.print(c);
			fr.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
