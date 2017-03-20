import java.io.*;
import java.util.*;

public class Lngpok {

    int MAX(int a,int b) {
       return ((a > b) ? a : b);
    }
    int MIN(int a,int b){   return ((a < b) ? a : b);}


    int median(int a, int b, int c)
    {
        int max;
        int min;

        max = MAX(a, MAX(b,c));
        min = MIN(a, MIN(b,c));

        return (a + b + c) - min - max;
    }

    private static final int RADIX = 10000;
    private static final int  MAX = 10000000;
    private static int countJokers= 0;
    private static Set<Integer> set = new HashSet<>();

    public static void main(String[] args) throws IOException {

        String inputFileName = args.length >= 2 ? args[0] : "lngpok/src/lngpok.in";
        String outputFileName = args.length >= 2 ? args[1] : "lngpok/src/lngpok.out";

        FileInputStream fstream = new FileInputStream(inputFileName);
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
        int[] carts = new int[parts.length];
        for (int n = 0; n < parts.length; n++) {
            carts[n] = Integer.parseInt(parts[n]);
        }
        int combo;

        carts = countSort(carts);

        if (set.isEmpty()){

            combo = countJokers;
        }else {
            combo = findCombinations(set, countJokers);
        }
        System.out.println(combo);

        PrintWriter pw1 = new PrintWriter(new BufferedWriter(new FileWriter(outputFileName)));
        pw1.close();
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(outputFileName, true)));
        pw.print(combo);
        pw.close();
    }

    private static int findCombinations(Set<Integer> set, int countJokers) {
        int [] combos = new int[set.size()];
        int [] combosHash = new int[set.size()];
        int jokers = countJokers;
        int i=0;
        int k=0;
        for (Integer c : set){
            combos[i] = c;
            i++;
            }
            Arrays.sort(combos);
        for (int count=0;count<combos.length-1;count++){
            if (combos[count+1]==combos[count]+1){
                combosHash[k]+=1;
            }else if ((combos[count] - combos[count+1]) <= jokers ){
                combosHash[k]+=1;
                jokers -= ((combos[count+1] - combos[count])-1);
                if (jokers<0){
                    Arrays.sort(combosHash);
                    return combosHash[combosHash.length-1]+countJokers;
                }
            }
            else {
                jokers = countJokers;
                k++;
            }
        }
        return combosHash[combosHash.length-1] + countJokers + 1;
    }

    static int[] countSort(int[] array) {
        int[] aux = new int[array.length];

        int min = array[0];
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
            } else if (array[i] > max) {
                max = array[i];
            }
        }

        int[] counts = new int[max - min + 1];

        for (int i = 0;  i < array.length; i++) {
            counts[array[i] - min]++;
        }

        counts[0]--;
        for (int i = 1; i < counts.length; i++) {
            counts[i] = counts[i] + counts[i-1];
        }

        for (int i = array.length - 1; i >= 0; i--) {
            if (array[i]==0){
                countJokers++;
            }
            aux[counts[array[i] - min]--] = array[i];
        }
        for (int anOut : array) {
            if (anOut==0){
                continue;
            }else {
            set.add(anOut);
            }
        }
        return aux;
    }

    static void countSort2(int[] a, int length, int exp)
    {
        int i;
    int[] hist = new int [RADIX];
    int[] out = new int[length];


        for (i = 0; i < length; i++)
        {
            int r = (a[i] / exp) % RADIX;
            hist[r] = hist[r] + 1;
        }

        for (i = 1; i < RADIX; i++)
        {
            hist[i] = hist[i] + hist[i - 1];
        }

        for (i = length - 1; i >= 0; i--)
        {
            int r = (a[i] / exp) % RADIX;
            out[hist[r] - 1] = a[i];
            hist[r] = hist[r] - 1;
        }

    }
    static void radixSort(int[] a, int length, int range) {
        int exp;

        for (exp = 1; (range / exp) > 0; exp = exp * RADIX) {
            countSort2(a, length, exp);
        }
    }
}
