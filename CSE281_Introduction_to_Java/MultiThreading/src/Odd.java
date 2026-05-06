public class Odd extends Thread{
    private int start;
    private int end;


    public void run()
    {
        odd();
    }


    private void odd() {

        for(int i = 1; i <= end; i++)
        {
            if(i % 2 != 0)
            {
                System.out.println(i);
            }
        }
    }

    public Odd(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }
}
