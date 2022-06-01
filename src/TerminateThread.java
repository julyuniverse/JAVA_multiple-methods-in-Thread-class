import java.io.IOException;

public class TerminateThread extends Thread {
    private boolean flag = false; // while(flag)의 flag 변숫값

    // 생성자 (쓰레드 이름 설정)
    public TerminateThread(String name) {
        super(name);
    }

    public void run() {
        while (!flag) {
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(getName() + " end");
    }

    // 종료
    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public static void main(String[] args) throws IOException {
        TerminateThread threadA = new TerminateThread("A");
        TerminateThread threadB = new TerminateThread("B");
        TerminateThread threadC = new TerminateThread("C");

        threadA.start();
        threadB.start();
        threadC.start();

        int in;
        while (true) {
            in = System.in.read();
            if (in == 'A') {
                threadA.setFlag(true);
            } else if (in == 'B') {
                threadB.setFlag(true);
            } else if (in == 'C') {
                threadC.setFlag(true);
            } else if (in == 'M') {
                threadA.setFlag(true);
                threadB.setFlag(true);
                threadC.setFlag(true);
                break;
            } else {
                System.out.println("Please enter a type");
            }
        }

        System.out.println("main end");

        // 결과
        // A
        // Please enter a type
        // A end
        // C
        // Please enter a type
        // C end
        // M
        // main end
        // B end
    }
}
