package com.example.win_8.voicemail;

/**
 * Created by win-8 on 13-02-2017.
 */
public class Calculator {
    public float x;
    public float sum;
    boolean j=false,l=false;
    public double r;
    public float y;
    public String text,sign;

    public Calculator(String text){
        this.text=text;
    }
    public void calculate(){
        String[] arr = text.split(" ");
        int c=0;
        for(String a:arr){
            if(a.contains("0")||a.contains("1")||a.contains("2")||a.contains("3")||a.contains("4")||
                    a.contains("5")||a.contains("6")||a.contains("7")||a.contains("8")||a.contains("9")){

                if(c==0){
                     x = Integer.parseInt(a);
                    c++;
                }else{
                    y=Integer.parseInt(a);
                }
            }
        }
      sum=x;

        if(text.toLowerCase().contains("square")&&text.toLowerCase().contains("root")){
            r=Math.sqrt(x);
            l=true;
            sign =" square root ";
        }

        if(text.toLowerCase().contains("square")) {
                sum = x * x;
                sign = "square";
            }

        if(text.toLowerCase().contains("log")&&text.toLowerCase().contains("base")){
            sum= (float) (Math.log(x)/ Math.log(y));
            j=true;
        }
        if(j==false) {
            if (text.toLowerCase().contains("log")) {
                sum = (float) Math.log(x);
                sign="log of ";
            }
        }
        if(text.toLowerCase().contains("cube")){
            sum=sum*x*sum;
            sign="cube";
        }
        if((text.contains("x"))||text.contains("multiplied")||text.contains("into")||text.contains("times")){
            sum=x*y;
            sign=" multiplied by ";
        }
        if(text.toLowerCase().contains("square root")||text.toLowerCase().contains("squared root")){
            sum= (float) Math.sqrt(x);
            sign="square root";
        }
        else if((text.contains("+"))){
            sum =x+y;
            sign="+";
        }
        else if(text.contains("divided")){
            r=x/y;
            sign="/";
        }
        else if((text.contains("-"))||(text.contains("minus"))){
            sum =x-y;
            sign="-";
        }
        else if(text.toLowerCase().contains("to the power")){
            for(int i=1;i<y;i++){
                sum=sum*x;
            }

            sign ="to the power";
        }
        else if(text.toLowerCase().contains("antilog")){
            sum= (float) Math.exp(x);
            sign="antilog";
        }

        else if(text.toLowerCase().contains("%")){
            sum=(float)(x/100)*y;
            sign="percent";
        }
    }
    public float getX(){return x;}
    public float getY(){return y;}
    public String sign(){return sign;}
    public float print(){
        return sum;
    }
    public double getDouble(){
        return r;
    }
}
