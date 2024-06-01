package Algorithms.SJF;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class ProcessSJF {
    int id, arrivalTime, burstTime, waitingTime, turnaroundTime;

    public ProcessSJF(int id, int arrivalTime, int burstTime) {
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.waitingTime = 0;
        this.turnaroundTime = 0;
    }
}

public class SJF {
    public static void main(String[] args) {
        List<ProcessSJF> processSJFS = new ArrayList<>();
        processSJFS.add(new ProcessSJF(1, 0, 10));
        processSJFS.add(new ProcessSJF(2, 6, 8));
        processSJFS.add(new ProcessSJF(3, 7, 4));
        processSJFS.add(new ProcessSJF(4, 9, 5));

        int currentTime = 0;
        List<ProcessSJF> readyQueue = new ArrayList<>();
        List<ProcessSJF> finishedProcessSJFS = new ArrayList<>();

        while (!processSJFS.isEmpty() || !readyQueue.isEmpty()) {
            for (ProcessSJF p : processSJFS) {
                if (p.arrivalTime <= currentTime) {
                    readyQueue.add(p);
                }
            }
            processSJFS.removeAll(readyQueue);

            if (!readyQueue.isEmpty()) {
                readyQueue.sort(Comparator.comparingInt(p -> p.burstTime));
                ProcessSJF currentProcessSJF = readyQueue.remove(0);
                currentProcessSJF.waitingTime = currentTime - currentProcessSJF.arrivalTime;
                currentProcessSJF.turnaroundTime = currentProcessSJF.waitingTime + currentProcessSJF.burstTime;
                currentTime += currentProcessSJF.burstTime;
                finishedProcessSJFS.add(currentProcessSJF);
            } else {
                currentTime++;
            }
        }

        double totalWaitingTime = 0;
        double totalTurnaroundTime = 0;
        System.out.println("               Shortest Job First Scheduling Algorithm         ");
        System.out.println("===========================================================================");
        System.out.println(" Process ID   Arrival Time   Burst Time   Waiting Time   Turnaround Time ");
        for (ProcessSJF p : finishedProcessSJFS) {
            totalWaitingTime += p.waitingTime;
            totalTurnaroundTime += p.turnaroundTime;
            System.out.println("    P" + p.id + "            " + p.arrivalTime + "             " + p.burstTime + "           " + p.waitingTime + "             " + p.turnaroundTime);
        }
        System.out.println("===========================================================================");

        double averageWaitingTime = totalWaitingTime / finishedProcessSJFS.size();
        double averageTurnaroundTime = totalTurnaroundTime / finishedProcessSJFS.size();

        System.out.println("SJF Average Waiting Time: " + averageWaitingTime);
        System.out.println("SJF Average Turnaround Time: " + averageTurnaroundTime);
    }
}
