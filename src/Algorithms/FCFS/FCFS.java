package Algorithms.FCFS;
import java.util.ArrayList;
import java.util.List;

class ProcessFCFS {
    int id, arrivalTime, burstTime, waitingTime, turnaroundTime;

    public ProcessFCFS(int id, int arrivalTime, int burstTime) {
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.waitingTime = 0;
        this.turnaroundTime = 0;
    }
}

public class FCFS {
    public static void main(String[] args) {
        List<ProcessFCFS> processes = new ArrayList<>();
        processes.add(new ProcessFCFS(1, 0, 10));
        processes.add(new ProcessFCFS(2, 6, 8));
        processes.add(new ProcessFCFS(3, 7, 4));
        processes.add(new ProcessFCFS(4, 9, 5));

        int currentTime = 0;
        for (ProcessFCFS p : processes) {
            if (currentTime < p.arrivalTime) {
                currentTime = p.arrivalTime;
            }
            p.waitingTime = currentTime - p.arrivalTime;
            p.turnaroundTime = p.waitingTime + p.burstTime;
            currentTime += p.burstTime;
        }

        double totalWaitingTime = 0;
        double totalTurnaroundTime = 0;
        System.out.println("           First Come First Serve Scheduling Algorithm         ");
        System.out.println("===========================================================================");
        System.out.println(" Process ID   Arrival Time   Burst Time   Waiting Time   Turnaround Time ");
        for (ProcessFCFS p : processes) {
            totalWaitingTime += p.waitingTime;
            totalTurnaroundTime += p.turnaroundTime;
            System.out.println("    P" + p.id + "            " + p.arrivalTime + "             " + p.burstTime + "           " + p.waitingTime + "             " + p.turnaroundTime);
        }
        System.out.println("===========================================================================");

        double averageWaitingTime = totalWaitingTime / processes.size();
        double averageTurnaroundTime = totalTurnaroundTime / processes.size();

        System.out.println("FCFS Average Waiting Time: " + averageWaitingTime);
        System.out.println("FCFS Average Turnaround Time: " + averageTurnaroundTime);
    }
}
