package com.example.win_8.voicemail;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by win-8 on 04-03-2017.
 */
public class Convert {
    String text,p;
    float x;StringBuffer s=new StringBuffer();
    public Convert(String text){
        this.text=text;
    }
    public void calculate(){
        String[] arr = text.split(" ");
        for(String a:arr){
            if(a.contains("0")||a.contains("1")||a.contains("2")||a.contains("3")||a.contains("4")||
                    a.contains("5")||a.contains("6")||a.contains("7")||a.contains("8")||a.contains("9")){

                    x = Float.valueOf(a);
            }
        }
        if(text.toLowerCase().contains("kg")||text.toLowerCase().contains("grams")||text.toLowerCase().contains("gm")){
            p=x+" kg = "+(x*1000)+"g";
        }

        if(text.toLowerCase().contains("fahrenheit")||text.toLowerCase().contains("celsius")){
            String[] arr2 = text.split(" ");
            String fah="fahrenheit";
            String cel="celsius";
            for(String a:arr2){
                if(a.toLowerCase().equals(fah)){
                    s.append("f");
                    break;
                }
                if(a.toLowerCase().equals(cel)){
                    s.append("c");
                    break;
                }

            }
            String f=s.toString();
            if(f.charAt(0)=='f'){
                float d1=((x - 32)*5)/9;
                p=d1+ "\u00b0"+"C";
            }
            else{
                float d=9*x/5 + 32;
                p=d+"\u00b0"+"F";
            }
        }
        if(text.toLowerCase().contains("feet")&&text.toLowerCase().contains("inches")){
            String[] arr2 = text.split(" ");
            String fah="feet";
            String cel="inches";
            for(String a:arr2){
                if(a.toLowerCase().equals(fah)){
                    s.append("f");
                    break;
                }
                if(a.toLowerCase().equals(cel)){
                    s.append("i");
                    break;
                }

            }
            String f=s.toString();
            if(f.charAt(0)=='f'){
                double d1=x*12;
                p=d1+" inches";
            }
            else{
                double d=x/12;
                Double toBeTruncated = new Double(d);

                Double truncatedDouble = BigDecimal.valueOf(toBeTruncated)
                        .setScale(3, RoundingMode.HALF_UP)
                        .doubleValue();
                p=truncatedDouble+" feet";
            }
        }
        if(text.toLowerCase().contains("centimetre")&&text.toLowerCase().contains("metre")){
            String[] arr2 = text.split(" ");
            String fah="centimetre";
            String fah1="centimetres";

            String cel="metre";
            String cel1="metres";

            for(String a:arr2){
                if(a.toLowerCase().equals(fah)||a.toLowerCase().equals(fah1)){
                    s.append("c");
                    break;
                }
                if(a.toLowerCase().equals(cel)||a.toLowerCase().equals(cel1)){
                    s.append("m");
                    break;
                }

            }
            String f=s.toString();
            if(f.charAt(0)=='c'){
                double d4=x/100;
                Double toBeTruncated = new Double(d4);

                Double truncatedDouble = BigDecimal.valueOf(toBeTruncated)
                        .setScale(3, RoundingMode.HALF_UP)
                        .doubleValue();
                p=truncatedDouble+" metre";
            }
            else{
                double d3=x*100;
                p=d3+" centimetre";
            }
        }

        if(text.toLowerCase().contains("cm")&&text.toLowerCase().contains("metre")){
            String[] arr2 = text.split(" ");
            String fah="cm";
            String cel="metre";
            for(String a:arr2){
                if(a.toLowerCase().equals(fah)){
                    s.append("c");
                    break;
                }
                if(a.toLowerCase().equals(cel)){
                    s.append("m");
                    break;
                }

            }
            String f=s.toString();
            if(f.charAt(0)=='c'){
                double d4=x/100;
                Double toBeTruncated = new Double(d4);

                Double truncatedDouble = BigDecimal.valueOf(toBeTruncated)
                        .setScale(3, RoundingMode.HALF_UP)
                        .doubleValue();
                p=truncatedDouble+" metre";
            }
            else{
                double d3=x*100;
                p=d3+" centimetre";
            }
        }
        if(text.toLowerCase().contains("millimetre")&&text.toLowerCase().contains("metre")){
            String[] arr2 = text.split(" ");
            String fah="millimetre";
            String fah1="millimetres";

            String cel="metre";
            String cel1="metres";

            for(String a:arr2){
                if(a.toLowerCase().equals(fah)||a.toLowerCase().equals(fah1)){
                    s.append("c");
                    break;
                }
                if(a.toLowerCase().equals(cel)||a.toLowerCase().equals(cel1)){
                    s.append("m");
                    break;
                }

            }
            String f=s.toString();
            if(f.charAt(0)=='c'){
                double d1=x/1000;
                Double toBeTruncated = new Double(d1);

                Double truncatedDouble = BigDecimal.valueOf(toBeTruncated)
                        .setScale(3, RoundingMode.HALF_UP)
                        .doubleValue();

                p=truncatedDouble+" metre";
            }
            else{
                double d=x*1000;
                p=d+" millimetre";
            }
        }
        //
        if(text.toLowerCase().contains("millimetre")&&text.toLowerCase().contains("centimetre")){
            String[] arr2 = text.split(" ");
            String fah="millimetre";
            String fah1="millimetres";

            String cel="centimetre";
            String cel1="centimetres";

            for(String a:arr2){
                if(a.toLowerCase().equals(fah)||a.toLowerCase().equals(fah1)){
                    s.append("m");
                    break;
                }
                if(a.toLowerCase().equals(cel)||a.toLowerCase().equals(cel1)){
                    s.append("c");
                    break;
                }

            }
            String f=s.toString();
            if(f.charAt(0)=='c'){
                double d1=x*10;
                Double toBeTruncated = new Double(d1);

                Double truncatedDouble = BigDecimal.valueOf(toBeTruncated)
                        .setScale(3, RoundingMode.HALF_UP)
                        .doubleValue();

                p=truncatedDouble+" millimetres";
            }
            else{
                double d=x/10;
                p=d+" centimetres";
            }
        }
        if(text.toLowerCase().contains("kilometre")&&text.toLowerCase().contains("millimetre")){
            String[] arr2 = text.split(" ");
            String fah="kilometre";
            String fah1="kilometres";

            String cel="millimetre";
            String cel1="millimetres";

            for(String a:arr2){
                if(a.toLowerCase().equals(fah)||a.toLowerCase().equals(fah1)){
                    s.append("k");
                    break;
                }
                if(a.toLowerCase().equals(cel)||a.toLowerCase().equals(cel1)){
                    s.append("m");
                    break;
                }

            }
            String f=s.toString();
            if(f.charAt(0)=='k'){
                double d1=x*1000000;
                p=d1+" millimetre";
            }
            else{
                double d=x/1000000;
                Double truncatedDouble = BigDecimal.valueOf(d)
                        .setScale(6, RoundingMode.HALF_UP)
                        .doubleValue();

                p=truncatedDouble+" Kilometre";
            }
        }
        if(text.toLowerCase().contains("kilometre")&&text.toLowerCase().contains("centimetre")){
            String[] arr2 = text.split(" ");
            String fah="kilometre";
            String fah1="kilometres";
            String cel="centimetre";
            String cel1="centimetres";
            for(String a:arr2){
                if(a.toLowerCase().equals(fah)||a.toLowerCase().equals(fah1)){
                    s.append("k");
                    break;
                }
                if(a.toLowerCase().equals(cel)||a.toLowerCase().equals(cel1)){
                    s.append("c");
                    break;
                }

            }
            String f=s.toString();
            if(f.charAt(0)=='k'){
                double d1=x*100000;
                p=d1+" centimetre";
            }
            else if(f.charAt(0)=='c'){
                double d=x/100000;
                Double truncatedDouble = BigDecimal.valueOf(d)
                        .setScale(5, RoundingMode.HALF_UP)
                        .doubleValue();

                p=truncatedDouble+" Kilometre";
            }
        }
        if(text.toLowerCase().contains("kilometre")&&text.toLowerCase().contains("metre")){
            String[] arr2 = text.split(" ");
            String fah="kilometre";
            String fah1="kilometres";

            String cel="metre";
            String cel1="metres";

            for(String a:arr2){
                if(a.toLowerCase().equals(fah)||a.toLowerCase().equals(fah1)){
                    s.append("k");
                    break;
                }
                if(a.toLowerCase().equals(cel)||a.toLowerCase().equals(cel1)){
                    s.append("c");
                    break;
                }

            }
            String f=s.toString();
            if(f.charAt(0)=='k'){
                double d1=x*1000;
                p=d1+" metre";
            }
            else{
                double d=x/1000;
                Double truncatedDouble = BigDecimal.valueOf(d)
                        .setScale(4, RoundingMode.HALF_UP)
                        .doubleValue();

                p=truncatedDouble+" Kilometre";
            }
        }
        if(text.toLowerCase().contains("millilitre")&&text.toLowerCase().contains("litre")){
            String[] arr2 = text.split(" ");
            String fah="millilitre";
            String fah1="millilitres";

            String cel="litre";
            String cel1="litres";
            for(String a:arr2){
                if(a.toLowerCase().equals(fah)||a.toLowerCase().equals(fah1)){
                    s.append("m");
                    break;
                }
                if(a.toLowerCase().equals(cel)||a.toLowerCase().equals(cel1)){
                    s.append("l");
                    break;
                }
            }
            String f=s.toString();
            if(f.charAt(0)=='m'){
                double d1=x/1000;
                Double truncatedDouble = BigDecimal.valueOf(d1)
                        .setScale(4, RoundingMode.HALF_UP)
                        .doubleValue();
                p=truncatedDouble+" litre";
            }
            else{
                double d=x*1000;
                p=d+" millilitre";
            }
        }

    }
    public String getP()
    {
        return p;
    }
}
