package discnt;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Lesson1 {
    private static int sum = 0;
    private static int summary = 0;

    public static void main(String[] args) throws IOException {
        FileInputStream fstream = new FileInputStream("src\\main\\java\\discnt\\discnt.in");
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
        int i = 0;
        List<String> line = new ArrayList<>();
        String strLine;
        while ((strLine = br.readLine()) != null) {
            line.add(i, strLine);
            i++;
        }
        br.close();
        String[] parts = line.get(0).split(" ");
        int[] products = new int[parts.length];
        for (int n = 0; n < parts.length; n++) {
            products[n] = Integer.parseInt(parts[n]);
        }
        Double disc = new Double(line.get(1));

        bubbleSort(products);
        bestComboProducts(products);
        double bestPrice = summary + (sum - ((sum / 100) * disc));

        PrintWriter pw1 = new PrintWriter(new BufferedWriter(new FileWriter("src\\main\\java\\discnt\\discnt.out")));
        pw1.close(); // Make sure the first PrintWriter object name is different from the second one.

        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("src\\main\\java\\discnt\\discnt.out", true))); // PrintWriter in append-mode. When you recreate the text file with the same name, the file contents are erased because the previous object was not in append mode.
        pw.print(String.format("%.2f", bestPrice));
        pw.close();

    }

    private static void bestComboProducts(int[] products) {

        int i = products.length / 3;
        for (int j = i; j != 0; j--) {
            sum += products[products.length - j];
        }
        for (int c = 0; c < products.length - i; c++) {
            summary += products[c];
        }
    }

    private static void bubbleSort(int[] a) {
        for (int i = 1; i < a.length; i++)
            for (int j = i; j > 0 && a[j - 1] > a[j]; j--)
                swap(a,j - 1, j);

    }

    private static void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
}
