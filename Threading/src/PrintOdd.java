public class PrintOdd extends Thread{

    private int start;
    private int end;

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

    public PrintOdd(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public void run()
    {
        printOdd();
    }


    private void printOdd()
    {
        for(int i = start; i <= end; i++)
        {
            if(i % 2 != 0)
            {
                System.out.println(i);
            }
        }
    }
}
