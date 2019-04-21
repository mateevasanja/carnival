/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package array;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 *
 * @author user
 */
public class Array<E> {
    public static int brojDoProsek(int niza[]){
        double suma=0;
        for(int i=0;i<niza.length;i++){
            suma+=niza[i];
        }
        double prosek=suma/niza.length;
        Boolean start =true;
        double razlika=0;
        int najblizokBroj[]=new int[niza.length];
        int pozicija=0;
        for(int i=0;i<niza.length;i++){
            if(start){
                start=false;
                razlika=Math.abs(prosek-niza[i]);
                najblizokBroj[0]=niza[i];
                pozicija=1;
            } else{
                if(razlika>Math.abs(prosek-niza[i])){
                    najblizokBroj=new int[niza.length];
                    najblizokBroj[0]=niza[i];
                    razlika=Math.abs(prosek-niza[i]);
                    pozicija=1;
                } else if(razlika==Math.abs(prosek-niza[i])){
                    najblizokBroj[pozicija]=niza[i];
                    pozicija++;
                }
            }
        }
        int min=10000;
        int index=0;
        for(int i=0;i<pozicija;i++){
            if(najblizokBroj[i]<min){
            min=najblizokBroj[i];
            index=i;
            }
        }
        return najblizokBroj[index];
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        String s=stdin.readLine();
        int N=Integer.parseInt(s);
        int niza[]=new int[N];
        if(N<1||N>50){
            System.out.println("Vnesete soodveten broj");
        } else {
            for(int i=0;i<N;i++){
                String str=stdin.readLine();
                niza[i]=Integer.parseInt(str);
            }
            System.out.println(brojDoProsek(niza));
        }
    }
    
}
