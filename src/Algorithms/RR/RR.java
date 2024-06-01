package Algorithms.RR;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.List;

class ProcessRR {
    int id, arrivalTime, burstTime, remainingTime, waitingTime, turnaroundTime, lastExecutionTime;

    public ProcessRR(int id, int arrivalTime, int burstTime) {
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.remainingTime = burstTime;
        this.waitingTime = 0;
        this.turnaroundTime = 0;
        this.lastExecutionTime = arrivalTime;
    }
}

public class RR {
    public static void main(String[] args) {
        List<ProcessRR> processRRS = new ArrayList<>();
        processRRS.add(new ProcessRR(1, 0, 10));
        processRRS.add(new ProcessRR(2, 6, 8));
        processRRS.add(new ProcessRR(3, 7, 4));
        processRRS.add(new ProcessRR(4, 9, 5));

        int timeQuantum = 4;
        int currentTime = 0;
        Queue<ProcessRR> readyQueue = new LinkedList<>();
        List<ProcessRR> finishedProcessRRS = new ArrayList<>();

        while (!processRRS.isEmpty() || !readyQueue.isEmpty()) {
            for (ProcessRR p : processRRS) {
                if (p.arrivalTime <= currentTime && !readyQueue.contains(p)) {
                    readyQueue.add(p);
                }
            }
            int finalCurrentTime = currentTime;
            processRRS.removeIf(p -> p.arrivalTime <= finalCurrentTime);

            if (!readyQueue.isEmpty()) {
                ProcessRR currentProcessRR = readyQueue.poll();
                if (currentProcessRR.lastExecutionTime < currentTime) {
                    currentProcessRR.waitingTime += currentTime - currentProcessRR.lastExecutionTime;
                }

                int executionTime = Math.min(timeQuantum, currentProcessRR.remainingTime);
                currentProcessRR.remainingTime -= executionTime;
                currentTime += executionTime;
                currentProcessRR.lastExecutionTime = currentTime;

                if (currentProcessRR.remainingTime > 0) {
                    readyQueue.add(currentProcessRR);
                } else {
                    currentProcessRR.turnaroundTime = currentTime - currentProcessRR.arrivalTime;
                    finishedProcessRRS.add(currentProcessRR);
                }
            } else {
                currentTime++;
            }
        }

        double totalWaitingTime = 0;
        double totalTurnaroundTime = 0;
        System.out.println("                  Round Robin Scheduling Algorithm         ");
        System.out.println("===========================================================================");
        System.out.println(" Process ID   Arrival Time   Burst Time   Waiting Time   Turnaround Time ");
        for (ProcessRR p : finishedProcessRRS) {
            totalWaitingTime += p.waitingTime;
            totalTurnaroundTime += p.turnaroundTime;
            System.out.println("    P" + p.id + "            " + p.arrivalTime + "             " + p.burstTime + "           " + p.waitingTime + "             " + p.turnaroundTime);
        }
        System.out.println("===========================================================================");

        double averageWaitingTime = totalWaitingTime / finishedProcessRRS.size();
        double averageTurnaroundTime = totalTurnaroundTime / finishedProcessRRS.size();

        System.out.println("RR Average Waiting Time: " + averageWaitingTime);
        System.out.println("RR Average Turnaround Time: " + averageTurnaroundTime);
    }
}
