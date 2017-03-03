package lesson2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Lesson2ClassWork {
    public static void main(String[] args) throws IOException {

        FileInputStream fstream = new FileInputStream("src\\main\\resources\\discnt.in");
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

        mergeSort(products, 0, products.length - 1);
        //sortBU(products, products.length);

        System.out.println(Arrays.toString(products));

    }

    private static void mergeSort(int[] a, int start, int end) {
        int mid = end / 2;

        if (end > start) {
            mergeSort(a, start, mid);
            mergeSort(a, end, mid);
            merge(a, start, end);
        }
    }

    private static void merge(int[] a, int lo, int hi) {

        if (hi <= lo)
            return;
        int mid = lo + (hi - lo) / 2;
        mergeSort(a, lo, mid);
        mergeSort(a, mid + 1, hi);

        int[] buf = Arrays.copyOf(a, a.length);

        for (int k = lo; k <= hi; k++)
            buf[k] = a[k];

        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {

            if (i > mid) {
                a[k] = buf[j];
                j++;
            } else if (j > hi) {
                a[k] = buf[i];
                i++;
            } else if (buf[j] < buf[i]) {
                a[k] = buf[j];
                j++;
            } else {
                a[k] = buf[i];
                i++;
            }
        }
    }


    static void sortBU(int[] a, int lenght) {
        int sections = 1;
        while (sections < lenght) {
            for (int k = 0; k < lenght / sections; k += 2) {
                merge(a, k * sections, (k + 2) * sections - 1);
            }
            sections = sections * 2;
        }
    }
}
