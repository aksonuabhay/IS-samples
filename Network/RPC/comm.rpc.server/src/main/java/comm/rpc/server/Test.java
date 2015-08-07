package comm.rpc.server;

public class Test {
    public int printYes()
    {
    	System.out.println("In Yessssssssss");
    	return 0;
    }
    
    public int printNo()
    {
    	System.out.println("In Noooooo");
    	return 100;
    }
    
    public int printNumber(int a)
    {
    	System.out.println("In Number   "  + a);
    	return a;
    }
    
    public String printString(String a)
    {
    	System.out.println("In Number   "  + a);
    	return a;
    }
    
    //Has some problems
    public void noReturn()
    {
    	System.out.println("In Noooooo");
    	return 100;
    }
}
