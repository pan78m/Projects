package Visualizer.Sorts;

import AlgoVisualizer.*;

public class QuickSort implements Runnable {

    private Integer[] toBeSorted;
    private VisualizerFrame frame;
    private boolean fast;

    public QuickSort(Integer[] toBeSorted, VisualizerFrame frame, boolean fast) {
        this.toBeSorted = toBeSorted;
        this.frame = frame;
        this.fast = fast;
    }

    public void run() {
        if (fast) {
            sortFast(0, toBeSorted.length - 1);
        } else {
            sortSlow(0, toBeSorted.length - 1);
        }
        SortingVisualizer.isSorting = false;
    }

    public void sortFast(int low, int high) {
        if (low < high) {
            int partitionIndex = partitionFast(low, high);
            sortFast(low, partitionIndex - 1);
            sortFast(partitionIndex + 1, high);
        }
    }

    public void sortSlow(int low, int high) {
        if (low < high) {
            int partitionIndex = partitionSlow(low, high);
            sortSlow(low, partitionIndex - 1);
            sortSlow(partitionIndex + 1, high);
        }
    }

    private int partitionFast(int low, int high) {
        int pivot = toBeSorted[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (toBeSorted[j] < pivot) {
                i++;
                swap(i, j);
            }
            frame.reDrawArray(toBeSorted, i, j, high);
            try {
                Thread.sleep(SortingVisualizer.sleep);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        swap(i + 1, high);
        frame.reDrawArray(toBeSorted, i + 1, high);
        try {
            Thread.sleep(SortingVisualizer.sleep);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return i + 1;
    }

    private int partitionSlow(int low, int high) {
        int pivot = toBeSorted[high];
        int i = low;
        for (int j = low; j < high; j++) {
            if (toBeSorted[j] < pivot) {
                swap(i, j);
                i++;
            }
            frame.reDrawArray(toBeSorted, i, j, high);
            try {
                Thread.sleep(SortingVisualizer.sleep);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        swap(i, high);
        frame.reDrawArray(toBeSorted, i, high);
        try {
            Thread.sleep(SortingVisualizer.sleep);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return i;
    }

    private void swap(int i, int j) {
        int temp = toBeSorted[i];
        toBeSorted[i] = toBeSorted[j];
        toBeSorted[j] = temp;
    }
}
