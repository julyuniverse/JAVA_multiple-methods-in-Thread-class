public class JoinTest extends Thread {
    int start;
    int end;
    int total;

    // 생성자
    public JoinTest(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public void run() {
        int i;
        for (i = start; i <= end; i++) {
            total += i;
        }
    }

    public static void main(String[] args) {
        // main 쓰레드가 필요한 값은 lastTotal 값이 필요하다. 하지만 jt1과 jt2의 쓰레드 결괏값을 main이 기다려 주지 않는다.
        // 그래서 join()을 걸어준다.
        JoinTest jt1 = new JoinTest(1, 50);
        JoinTest jt2 = new JoinTest(51, 100);

        jt1.start();
        jt2.start();

        // jt1과 jt2의 쓰레드에 join() 함수를 걸어주는데 jt1과 jt2의 쓰레드가 끝나지 않을 수도 있기 때문에 예외 처리를 해야 한다.
        try {
            jt1.join();
            jt2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        int lastTotal = jt1.total + jt2.total;

        System.out.println("jt1.total = " + jt1.total);
        System.out.println("jt2.total = " + jt2.total);
        System.out.println("lastTotal = " + lastTotal);

        // 결과
        // jt1.total = 1275
        // jt2.total = 3775
        // lastTotal = 5050
    }
}
