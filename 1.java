import java.util.Scanner;
import java.util.Random;

class Frame
{
 private int value;
 private int SeqNum;
 
 public Frame(int value, int SeqNum)
 {
   this.value=value;
   this.SeqNum=SeqNum;
 }
 public int getvalue()
 {
 return value;
 }
 public int getSeqNum()
 {
 return SeqNum;
 }
 public void setSeqNUm(int setSeq)
 {
  SeqNum=setSeq;
 }
 public void setvalue(int v)
 {
 value=v;
 }
 }
 public class BubblesortFrames
 {
   public static void main(String args[])
   {
     Scanner sc =new Scanner(System.in);
     Random random=new Random();
     System.out.print("Enter The number of frames:");
     int n=sc.nextInt();
     Frame frames[]=new Frame[n];
     for(int i=0;i<n;i++)
     {
      System.out.print("enter the value of the Frame "+(i+1)+":");
      int val=sc.nextInt();
      int seqNo=random.nextInt(1000);
      frames[i]=new Frame(val,seqNo);
      }
      
      System.out.println("Frames before sorting are:");
       printF(frames);
       System.out.println("frames after sorting are :");
     bubblesort(frames,n);
      printF(frames);
      }
    
  public static void  printF(Frame frames[])
    {
    for(Frame it:frames)
    {
      System.out .println(it.getvalue()+"->"+it.getSeqNum());
      }
    }
    public static void bubblesort(Frame frames[], int n)
    {
    for(int i=0;i<n-1;i++)
    {
     for(int j=0;j<n-i-1;j++)
     {
       if(frames[j].getSeqNum()>frames[j+1].getSeqNum())
       {
         Frame temp=frames[j];
         frames[j]=frames[j+1];
         frames[j+1]=temp;
       }    
    }
    }
    }
    
 }
