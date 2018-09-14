package Task2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.util.Scanner;

public class Main {
    private static OperatingSystemMXBean bean = (com.sun.management.OperatingSystemMXBean)ManagementFactory.getOperatingSystemMXBean();
    private static float memory;
    private static float CPU;

    private static void cpuLoad() {
        CPU = (float)
                ((com.sun.management.OperatingSystemMXBean) bean).getSystemCpuLoad() * 100;
        System.out.println("CPU: " + (int)CPU + "%");
    }
    private static void memoryLoad() {
        memory = ((float)(((com.sun.management.OperatingSystemMXBean) bean).getTotalPhysicalMemorySize() -
                ((com.sun.management.OperatingSystemMXBean) bean).getFreePhysicalMemorySize()) /
                (float)((com.sun.management.OperatingSystemMXBean) bean).getTotalPhysicalMemorySize()) * 100;
        System.out.println("Memory: " + (int)memory + "%\n");
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int choose;
        System.out.println("Select from the list: \n1. Show process list \n2. Cpu load and Memory Load");
        choose = in.nextInt();
        switch (choose){
            case 1:
                try {
                    String line;
                    Process p = Runtime.getRuntime().exec(System.getenv("windir") +"\\system32\\"+"tasklist.exe");
                    BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
                    while ((line = input.readLine()) != null)
                        System.out.println(line);
                    input.close();
                } catch (Exception err) {
                    err.printStackTrace();
                }
                break;
            case 2:
                while (true) {
                    try {
                        Thread.sleep(1000);
                    }
                    catch (InterruptedException ex) {
                        System.out.println("Interrupted Exception");
                    }
                    cpuLoad();
                    memoryLoad();
                }
            default:
                System.out.println("Error!");
                break;
        }
    }
}
