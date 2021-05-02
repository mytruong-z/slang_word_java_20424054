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

public class SlangReadFile {
    //https://www.geeksforgeeks.org/treemap-in-java/
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
            FILE_SLANG = current + "/" + FILE_SLANG;
            FILE_ORIGINAL_SLANG = current + "/" + FILE_ORIGINAL_SLANG;
            FILE_HISTORY_SLANG = current + "/" + FILE_HISTORY_SLANG;
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
                String[] d = (part[0]).split("\\|");
                for (int ii = 0; ii < d.length; ii++)
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
            for (int j = 1; j < meaning.size(); j++) {
                if (i < stringLength)
                    i++;
                s[i][0] = String.valueOf(i);
                s[i][1] = (String) slagList[index];
                s[i][2] = meaning.get(j);
            }
            index++;
        }
        return s;
    }
    
    public String[][] getMeaning(String key) {
        List<String> listMeaning = map.get(key);
        if (listMeaning == null)
            return null;
        int size = listMeaning.size();
        String s[][] = new String[size][3];
        for (int i = 0; i < size; i++) {
            s[i][0] = String.valueOf(i);
            s[i][1] = key;
            s[i][2] = listMeaning.get(i);
        }
        return s;
    }

    public void set(String slag, String oldValue, String newValue) {
        System.out.println(oldValue + "\t" + newValue);
        List<String> meaning = map.get(slag);
        int index = meaning.indexOf(oldValue);
        meaning.set(index, newValue);
        this.saveFile(FILE_SLANG);
        System.out.println("string length:" + stringLength);
    }
    
    public void reset() {
        try {
            readFile(FILE_ORIGINAL_SLANG);
            this.saveFile(FILE_SLANG);
        } catch (Exception e) {            
            e.printStackTrace();
        }
    }

    public void delete(String slag, String value) {
        List<String> meaningList = map.get(slag);
        int index = meaningList.indexOf(value);
        if (meaningList.size() == 1) {
            map.remove(slag);
            } else {
                meaningList.remove(index);
                map.put(slag, meaningList);
            }
            stringLength--;
            this.saveFile(FILE_SLANG);
    }

    public void addNew(String slag, String meaning) {
        List<String> meaningList = new ArrayList<>();
        meaningList.add(meaning);
        stringLength++;
        map.put(slag, meaningList);
        this.saveFile(FILE_SLANG);
    }

    public void addDuplicate(String slag, String meaning) {
        List<String> meaningList = map.get(slag);
        meaningList.add(meaning);
        stringLength++;
        map.put(slag, meaningList);
        this.saveFile(FILE_SLANG);
    }

    public void addOverwrite(String slag, String meaning) {
        List<String> meaningList = map.get(slag);
        meaningList.set(0, meaning);
        map.put(slag, meaningList);
        this.saveFile(FILE_SLANG);
    }

    public boolean checkSlang(String slag) {
        for (String keyIro : map.keySet()) {
            if (keyIro.equals(slag))
                return true;
        }
        return false;
    }
    
    //FindFrame Tham khảo từ internet 50%
    public String[][] findDefinition(String query) {
        List<String> keyList = new ArrayList<>();
        List<String> meaningList = new ArrayList<>();
        for (Entry<String, List<String>> entry : map.entrySet()) {
            List<String> meaning = entry.getValue();
                for (int i = 0; i < meaning.size(); i++) {
                    if (meaning.get(i).toLowerCase().contains(query.toLowerCase())) {
                        keyList.add(entry.getKey());
                        meaningList.add(meaning.get(i));
                    }
                }
        }
        int size = keyList.size();
        String s[][] = new String[size][3];

        for (int i = 0; i < size; i++) {
            s[i][0] = String.valueOf(i);
            s[i][1] = keyList.get(i);
            s[i][2] = meaningList.get(i);
        }
        return s;
    }
    
    //Tham khảo từ internet 30%
    public void saveHistory(String slag, String meaning) throws Exception {
        File file1 = new File(FILE_HISTORY_SLANG);
        FileWriter fr = new FileWriter(file1, true);
        fr.write(slag + "`" + meaning + "\n");
        fr.close();
    }
    
    //Tham khảo từ internet 30%
    public String[][] readHistory() {
        List<String> his = new ArrayList<>();
        List<String> hisD = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File(FILE_HISTORY_SLANG));
            scanner.useDelimiter("`");
            String temp = scanner.next();
            String[] part = scanner.next().split("\n");
            his.add(temp);
            hisD.add(part[0]);
            while (scanner.hasNext()) {
                temp = part[1];
                part = scanner.next().split("\n");
                his.add(temp);
                hisD.add(part[0]);
            }
                scanner.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        
            int size = his.size();
            String str[][] = new String[size][3];
            for (int i = 0; i < size; i++) {
                str[size - i - 1][0] = String.valueOf(size - i);
                str[size - i - 1][1] = his.get(i);
                str[size - i - 1][2] = hisD.get(i);
            }
            return str;
	}

}
