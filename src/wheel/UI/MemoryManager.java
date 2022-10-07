package wheel.UI;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public final class MemoryManager {
	private static final String fileName = "user_files/custom_wheel.txt";

	public static void saveDesignToMemory(String savedDesign, String nameOfTheDesign) {
		try (
			FileWriter fileWriter = new FileWriter(fileName, true);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			PrintWriter theWriter = new PrintWriter(bufferedWriter)
		) {
			theWriter.println(String.format("%s===%s", nameOfTheDesign, savedDesign));
		} catch (IOException exception) {
			throw new RuntimeException("Error saving the design to the file " + fileName);
		}
	}

	public static List<String> readDesignsFromMemory() {
		File readingFile = new File(fileName);

		try (Scanner fileScanner = new Scanner(readingFile)) {
			List<String> listOfStrings = new ArrayList<>();

			while (fileScanner.hasNextLine()) {
				listOfStrings.add(fileScanner.nextLine());
			}

			return listOfStrings;
		} catch (Exception exception) {
			throw new RuntimeException("Error reading the file" + fileName);
		}
	}
}
