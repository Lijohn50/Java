public class PrintEven extends Thread{

    private int start;
    private int end;

    public void run()
    {
        printEven();
    }

    private void printEven()
    {
        for(int i = start; i <= end; i++)
        {
            if(i % 2 == 0)
            {
                System.out.println(i);
            }
        }
    }



    public PrintEven(int start, int end) {
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
