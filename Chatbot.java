
import java.util.*;
import java.io.*;

public class Chatbot{
    private static String filename = "./WARC201709_wid.txt";
    private static ArrayList<Integer> readCorpus(){
        ArrayList<Integer> corpus = new ArrayList<Integer>();
        try{
            File f = new File(filename);
            Scanner sc = new Scanner(f);
            while(sc.hasNext()){
                if(sc.hasNextInt()){
                    int i = sc.nextInt();
                    corpus.add(i);
                }
                else{
                    sc.next();
                }
            }
        }
        catch(FileNotFoundException ex){
            System.out.println("File Not Found.");
        }
        return corpus;
    }
    static public void main(String[] args){
        ArrayList<Integer> corpus = readCorpus();
		int flag = Integer.valueOf(args[0]);
        
        if(flag == 100){
			int w = Integer.valueOf(args[1]);
            int count = 0;
            for(int i=0;i<corpus.size();i++){
                if(corpus.get(i)==w)
                    count++;
            }
            
            System.out.println(count);
            System.out.println(String.format("%.7f",count/(double)corpus.size()));
        }
        else if(flag == 200){
            int n1 = Integer.valueOf(args[1]);
            int n2 = Integer.valueOf(args[2]);
            double r = (double)n1/(double)n2;
            double value1 =0.0;
            double value2 =0.0;
            double prob =0.0;   
            int count = 0;      

             for(int i=0;i<4700;i++){
                 for(int j=0;j<corpus.size();j++){
                if(corpus.get(j)==i)
                    count++;
            }

            prob = count/(double)corpus.size();
            if(prob != 0){
                value2 = prob;
                if((r > value1 && r <= value2) || (r==0 && value1 == 0)){
                    System.out.println(i);
                    System.out.println(String.format("%.7f",value1));
                    System.out.println(String.format("%.7f",value2));
                        break;
                }
                else value1 = value2;
                }

            }

        }
        else if(flag == 300){
            int h = Integer.valueOf(args[1]);
            int w = Integer.valueOf(args[2]);
            int count = 0;
            ArrayList<Integer> words_after_h = new ArrayList<Integer>();
            for(int i=0;i<corpus.size()-1;i++){
                
                  if(corpus.get(i) == h && corpus.get(i+1) == w)
                    count++;

                 if(corpus.get(i) == h)
                    words_after_h.add(corpus.get(i));
            }

            //output 
            System.out.println(count);
            System.out.println(words_after_h.size());
            System.out.println(String.format("%.7f",count/(double)words_after_h.size()));
        }
        else if(flag == 400){
            int n1 = Integer.valueOf(args[1]);
            int n2 = Integer.valueOf(args[2]);
            int h = Integer.valueOf(args[3]);
            double r = (double)n1/(double)n2;
            ArrayList<Integer> words_after_h = new ArrayList<Integer>();
            int count =0;
            double value1 =0.0;
            double value2 =0.0;
            double prob =0.0;

            for(int j=0;j<corpus.size()-1;j++){                

                if(corpus.get(j) == h)
                    words_after_h.add(corpus.get(j));
            }

            for(int i=0;i<4700;i++){
                 for(int j=0;j<corpus.size()-1;j++){
                 if(corpus.get(j) == h && corpus.get(j+1) == i)
                    count++;
            }

            prob = count/(double)words_after_h.size();
            if(prob != 0){
                value2 = prob;
             

                if((r > value1 && r <= value2) || (r==0 && value1 == 0)){
                    System.out.println(i);
                    System.out.println(String.format("%.7f",value1));
                    System.out.println(String.format("%.7f",value2));
                        break;
                }
                else value1 = value2;

                }

            }
            
            
        }
        else if(flag == 500){
            int h1 = Integer.valueOf(args[1]);
            int h2 = Integer.valueOf(args[2]);
            int w = Integer.valueOf(args[3]);
            int count = 0;
            ArrayList<Integer> words_after_h1h2 = new ArrayList<Integer>();
            for(int i=0;i<corpus.size()-2;i++){
                if(corpus.get(i) == h1 && corpus.get(i+1) == h2 && corpus.get(i+2) == w)
                    count++;

                 if(corpus.get(i) == h1 && corpus.get(i+1) == h2)
                    words_after_h1h2.add(corpus.get(i));
            }

            //output 
            System.out.println(count);
            System.out.println(words_after_h1h2.size());
            if(words_after_h1h2.size() == 0)
                System.out.println("undefined");
            else
                System.out.println(String.format("%.7f",count/(double)words_after_h1h2.size()));
        }
        else if(flag == 600){
            int n1 = Integer.valueOf(args[1]);
            int n2 = Integer.valueOf(args[2]);
            int h1 = Integer.valueOf(args[3]);
            int h2 = Integer.valueOf(args[4]);
            double r = (double)n1/(double)n2;
            ArrayList<Integer> words_after_h1h2 = new ArrayList<Integer>();
            int count =0;
            double value1 =0.0;
            double value2 =0.0;
            double prob =0.0;

            for(int j=0;j<corpus.size()-2;j++){                

               if(corpus.get(j) == h1 && corpus.get(j+1) == h2)
                    words_after_h1h2.add(corpus.get(j));
            }

            for(int i=0;i<4700;i++){
                 for(int j=0;j<corpus.size()-2;j++){
                 if(corpus.get(j) == h1 && corpus.get(j+1) == h2 && corpus.get(j+2) == i)
                    count++;
            }

             if(words_after_h1h2.size() == 0){
                System.out.println("undefined");
                break;

            }

            prob = count/(double)words_after_h1h2.size();
            if(prob != 0){
                value2 = prob;
             

                if((r > value1 && r <= value2) || (r==0 && value1 == 0)){
                    System.out.println(i);
                    System.out.println(String.format("%.7f",value1));
                    System.out.println(String.format("%.7f",value2));
                        break;
                }
                else value1 = value2;

                }

            }
        }
        else if(flag == 700){

            int seed = Integer.valueOf(args[1]);
            int t = Integer.valueOf(args[2]);
            int h1=0,h2=0;

            Random rng = new Random();
            if (seed != -1) rng.setSeed(seed);

            if(t == 0){
                // TODO Generate first word using r
                double r = rng.nextDouble();

            double value1 =0.0;
            double value2 =0.0;
            double prob =0.0;   
            int count = 0;      

             for(int i=0;i<4700;i++){
                 for(int j=0;j<corpus.size();j++){
                if(corpus.get(j)==i)
                    count++;
            }

            prob = count/(double)corpus.size();
            if(prob != 0){
                value2 = prob;
                if((r > value1 && r <= value2) || (r==0 && value1 == 0)){
                  h1 = i;
                 break;
                }
                else value1 = value2;
                }

            }



                System.out.println(h1);
                if(h1 == 9 || h1 == 10 || h1 == 12){
                    return;
                }

             ArrayList<Integer> words_after_h = new ArrayList<Integer>();
            count =0;
            value1 =0.0;
            value2 =0.0;
            prob =0.0;
             r = rng.nextDouble();

            for(int j=0;j<corpus.size()-1;j++){                

                if(corpus.get(j) == h1)
                    words_after_h.add(corpus.get(j));
            }

            for(int i=0;i<4700;i++){
                 for(int j=0;j<corpus.size()-1;j++){
                 if(corpus.get(j) == h1 && corpus.get(j+1) == i)
                    count++;
            }

            prob = count/(double)words_after_h.size();
            if(prob != 0){
                value2 = prob;
             

                if((r > value1 && r <= value2) || (r==0 && value1 == 0)){
                    h2 = i;
                        break;
                }
                else value1 = value2;

                }

            }

                // TODO Generate second word using r
                System.out.println(h2);
            }
            else if(t == 1){
                h1 = Integer.valueOf(args[3]);
                // TODO Generate second word using r
                double r = rng.nextDouble();

            ArrayList<Integer> words_after_h = new ArrayList<Integer>();
            int count =0;
            double value1 =0.0;
            double value2 =0.0;
            double prob =0.0;
             

            for(int j=0;j<corpus.size()-1;j++){                

                if(corpus.get(j) == h1)
                    words_after_h.add(corpus.get(j));
            }

            for(int i=0;i<4700;i++){
                 for(int j=0;j<corpus.size()-1;j++){
                 if(corpus.get(j) == h1 && corpus.get(j+1) == i)
                    count++;
            }

            prob = count/(double)words_after_h.size();
            if(prob != 0){
                value2 = prob;
             

                if((r > value1 && r <= value2) || (r==0 && value1 == 0)){
                    h2 = i;
                        break;
                }
                else value1 = value2;

                }

            }


                System.out.println(h2);
            }
            else if(t == 2){
                h1 = Integer.valueOf(args[3]);
                h2 = Integer.valueOf(args[4]);
            }

            while(h2 != 9 && h2 != 10 && h2 != 12){
                double r = rng.nextDouble();
                int w  = 0;

            ArrayList<Integer> words_after_h1h2 = new ArrayList<Integer>();
            int count =0;
            double value1 =0.0;
            double value2 =0.0;
            double prob =0.0;

            for(int j=0;j<corpus.size()-2;j++){                

               if(corpus.get(j) == h1 && corpus.get(j+1) == h2)
                    words_after_h1h2.add(corpus.get(j));
            }

            for(int i=0;i<4700;i++){
                 for(int j=0;j<corpus.size()-2;j++){
                 if(corpus.get(j) == h1 && corpus.get(j+1) == h2 && corpus.get(j+2) == i)
                    count++;
            }

             if(words_after_h1h2.size() == 0){
                System.out.println("undefined");
                break;

            }

            prob = count/(double)words_after_h1h2.size();
            if(prob != 0){
                value2 = prob;
             

                if((r > value1 && r <= value2) || (r==0 && value1 == 0)){
                    w = i;
                        break;
                }
                else value1 = value2;

                }

            }

                // TODO Generate new word using h1,h2
                System.out.println(w);
                h1 = h2;
                h2 = w;
            }
        }

        return;

    }
}
