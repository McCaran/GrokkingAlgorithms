package ai152.Heorhiev;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        System.out.println("\nПроверка работы бинарного поиска в массиве от 1 до 100:");
        testBinarySearch();
        System.out.println("\nПроверка сортировок:");
        testSorting();
        System.out.println("\n\nПроверка графа и поиска в ширину и глубину:");
        Graph.testGraph();
    }

    private static void testBinarySearch() {
        int testSearchValue = new Random().nextInt(100);
        System.out.println("Искомое значение: " + testSearchValue);
        int[] searchTestArray = createSortedArray(100);
        binarySearch(searchTestArray, testSearchValue);
    }

    private static void testSorting() {
        int[] sortTestArray = createUnsortedArray(10);
        System.out.println("Массив до сортировки пузырьком:");
        for (int i : sortTestArray) System.out.print(i + " ");
        bubbleSort(sortTestArray);
        System.out.println("\nМассив после сортировки пузырьком:");
        for (int i : sortTestArray) System.out.print(i + " ");
        sortTestArray = createUnsortedArray(10);
        System.out.println("\nМассив до быстрой сортировки:");
        for (int i : sortTestArray) System.out.print(i + " ");
        quickSort(sortTestArray, 0, sortTestArray.length-1);
        System.out.println("\nМассив после быстрой сортировки:");
        for (int i : sortTestArray) System.out.print(i + " ");
    }

    //Бинарный алгоритм поиска
    public static int binarySearch(int[] array, int value) {
        int operationsCount = 0;
        int resultIndex = -1;
        int low = 0;
        int high = array.length-1;
        int guess;
        while (low <= high) {
            operationsCount++;
            guess = (low+high)/2;
            System.out.println("Проверяется значение: " + array[guess]);
            if (array[guess] == value) {
                System.out.println("Совпадение найдено. Общее кол-во операций: " + operationsCount);
                return guess;
            } else if (array[guess] > value) {
                high = guess-1;
            } else {
                low = guess +1;
            }
            System.out.println("Совпадение не найдено");
        }
        return resultIndex;
    }

    //Алгоритм сортировки пузырьком
    public static int[] bubbleSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int swaps = 0;
            for (int j = 0; j < array.length-i; j++) {
                if (array[j] > array[j+1]) {
                    int temp = array[j+1];
                    array[j+1] = array[j];
                    array[j] = temp;
                    swaps++;
                }
            }
            if (swaps == 0) break;
        }
        return array;
    }

    //Алгорит быстрой сортировки
    public static void quickSort(int[] array, int low, int high) {
        if (array.length == 0) return;
        if (low >= high) return;
        int pivot = array[low + (high - low) / 2];
        int i = low, j = high;
        while (i <= j) {
            while (array[i] < pivot) {
                i++;
            }
            while (array[j] > pivot) {
                j--;
            }
            if (i <= j) {
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                i++;
                j--;
            }
        }

        if (low < j) quickSort(array, low, j);

        if (high > i) quickSort(array, i, high);
    }

    //Создание сортированного массива
    private static int[] createSortedArray(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = i+1;
        }
        return array;
    }

    //Создание случайного массива
    private static int[] createUnsortedArray(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = new Random().nextInt(100);
        }
        return array;
    }

}
