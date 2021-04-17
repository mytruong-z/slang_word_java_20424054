/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slangWord;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

/**
 *
 * @author apple
 */
public class SlangReadFile {
    private TreeMap<String, List<String>> map = new TreeMap<>();
	private static SlangReadFile obj = new SlangReadFile();
	private int stringLength;
	private String FILE_SLANG = "slang.txt";
	private String FILE_ORIGINAL_SLANG = "orginal-slang.txt";
	private String FILE_HISTORY_SLANG = "history-slang.txt";
        
        private SlangReadFile() {
		try {
			String current = new java.io.File(".").getCanonicalPath();
			System.out.println("Current dir:" + current);
			FILE_SLANG = current + "\\" + FILE_SLANG;
			FILE_ORIGINAL_SLANG = current + "\\" + FILE_ORIGINAL_SLANG;
			FILE_HISTORY_SLANG = current + "\\" + FILE_HISTORY_SLANG;
			readFile(FILE_SLANG);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
        
        public static SlangReadFile getInstance() {
		if (obj == null) {
			synchronized (SlangReadFile.class) {
				if (obj == null) {
					obj = new SlangReadFile();// Đọc hết file
				}
			}
		}
		return obj;
	}

        void readFile(String file) throws Exception {
		map.clear();
		String slag = null;
		Scanner scanner = new Scanner(new File(file));
		scanner.useDelimiter("`");
		scanner.next();
		String temp = scanner.next();
		String[] part = temp.split("\n");
		int i = 0;
		int flag = 0;
		stringLength = 0;
		while (scanner.hasNext()) {
			List<String> meaning = new ArrayList<String>();
			slag = part[1].trim();
			temp = scanner.next();
			part = temp.split("\n");
			if (map.containsKey(slag)) {
				meaning = map.get(slag);
			}
			if (part[0].contains("|")) {
				System.out.println(part[0]);
				String[] d = (part[0]).split("\\|");
				for (int ii = 0; ii < d.length; ii++)
					System.out.println(d[ii]);
				Collections.addAll(meaning, d);
				stringLength += d.length - 1;
			} else {
				meaning.add(part[0]);
			}
		
			map.put(slag, meaning);
			i++;
			stringLength++;
		}
		scanner.close();
	}
    
        void saveFile(String file) {
		try {
			PrintWriter printWriter = new PrintWriter(new File(file));
			StringBuilder stringBuilder = new StringBuilder();

			stringBuilder.append("Slag`Meaning\n");
			String s[][] = new String[map.size()][3];
			Set<String> keySet = map.keySet();
			Object[] keyArray = keySet.toArray();
			for (int i = 0; i < map.size(); i++) {
				Integer in = i + 1;
				s[i][0] = in.toString();
				s[i][1] = (String) keyArray[i];
				List<String> meaning = map.get(keyArray[i]);
				stringBuilder.append(s[i][1] + "`" + meaning.get(0));
				for (int j = 1; j < meaning.size(); j++) {
					stringBuilder.append("|" + meaning.get(j));
				}
				stringBuilder.append("\n");
			}
		
			printWriter.write(stringBuilder.toString());
			printWriter.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}
        
        public String[][] getData() {
		String s[][] = new String[stringLength][3];
		Set<String> slagListSet = map.keySet();
		Object[] slagList = slagListSet.toArray();
		int index = 0;
		for (int i = 0; i < stringLength; i++) {
			s[i][0] = String.valueOf(i);
			s[i][1] = (String) slagList[index];
			List<String> meaning = map.get(slagList[index]);
			s[i][2] = meaning.get(0);
			System.out.println(s[i][0] + "\t" + s[i][1] + "\t" + s[i][2]);
			for (int j = 1; j < meaning.size(); j++) {
				if (i < stringLength)
					i++;
				s[i][0] = String.valueOf(i);
				s[i][1] = (String) slagList[index];
				s[i][2] = meaning.get(j);
				System.out.println(s[i][0] + "\t" + s[i][1] + "\t" + s[i][2]);
			}
			index++;
		}
		return s;
	}

}
