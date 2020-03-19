package cw3.dodatkowe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

class Voting {

    private static int slowMedian(List<Integer> l) {
        Collections.sort(l);
        return l.get(l.size() / 2);
    }

    private static int pickPivot(List<Integer> l) {
        if (l.size() < 5) {
            return slowMedian(l);
        }
        List<Integer> medians = new ArrayList<>();
        ArrayList<Integer> chunk = new ArrayList<>();
        int i = 0;
        for (Integer num : l) {
            if (i >= l.size() / 5 * 5) {
                break;
            }
            chunk.add(num);
            if (i % 5 == 4) {
                medians.add(slowMedian(chunk));
                chunk = new ArrayList<>();
            }
            i++;
        }
        int res[] = new int[medians.size()];
        i = 0;
        for (Integer med : medians) {
            res[i] = med.intValue();
            i++;
        }
        return median(res);
    }

    // may be logarithmic not linear -> magic 5 to improve\private static int
    // nth(int arr[], int n) {
    private static int nth(List<Integer> a, int n) {
        if (a.size() == 1) {
            if (n == 0) {
                return a.get(0);
            }
            return -1;
        }
        int pivot = pickPivot(a);
        List<Integer> smaller = new ArrayList<>();
        List<Integer> bigger = new ArrayList<>();
        List<Integer> equal = new ArrayList<>();
        for (Integer num : a) {
            if (num < pivot) {
                smaller.add(num);
            } else if (num == pivot) {
                equal.add(num);
            } else {
                bigger.add(num);
            }
        }

        if (n < smaller.size()) {
            return nth(smaller, n);
        }
        if (n < smaller.size() + equal.size()) {
            return pivot;
        }
        return nth(bigger, n - smaller.size() - equal.size());
    }

    private static int nth(int arr[], int n) {
        Integer a[] = Arrays.stream(arr).boxed().toArray(Integer[]::new);
        return nth(Arrays.asList(a), n);
    }

    // return -1 if median is not decimal
    private static int median(int arr[]) {
        if (arr.length % 2 == 1) {
            return nth(arr, arr.length / 2);
        } else {
            int a = nth(arr, arr.length / 2 - 1);
            int b = nth(arr, arr.length / 2);
            if (a == b) {
                return a;
            }
            return -1;
        }
    }

    private static int smallest(int arr[]) {
        int s = -1;
        for (int val : arr) {
            if (s == -1) {
                s = val;
            } else {
                s = Math.min(s, val);
            }
        }
        return s;
    }

    private static int biggest(int arr[]) {
        int s = -1;
        for (int val : arr) {
            if (s == -1) {
                s = val;
            } else {
                s = Math.max(s, val);
            }
        }
        return s;
    }

    // Todo make it work
    public static int winnner(int votes[]) {
        int med = median(votes);
        if (med != -1) {
            if (med == smallest(votes) || med == biggest(votes)) {
                return med;
            }
        }
        return 0;
    }

    private static void test() {
        int test0[] = { 1, 1 };
        int test1[] = { 1, 2 };
        int test2[] = { 1, 1, 1, 2 };
        int test3[] = { 1, 1, 2, 2 };
        int test4[] = { 999999999, 999999999 };
        int test5[] = { 1, 2, 2, 2, 2, 3 };
        assert winnner(test0) == 1;
        assert winnner(test1) == 0;
        assert winnner(test2) == 1;
        assert winnner(test3) == 0;
        assert winnner(test4) == 999999999;
        assert winnner(test5) == 2;
    }

    public static void main(String[] args) {
        test();
        Scanner sc = new Scanner(System.in);
        int s = sc.nextInt();
        int votes[] = new int[s];
        for (int i = 0; i < s; i++) {
            votes[i] = sc.nextInt();
        }
        System.out.println(winnner(votes));
        sc.close();
    }

}