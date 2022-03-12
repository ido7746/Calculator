package com.example.calculator;

import java.util.*;



public class ArithmeticApp{
    public static String solve(String expretion, int base){
        // check the expretion
        for(int i=0;i<expretion.length();i++){
            char digit = expretion.charAt(i);
            if(digit<'A'&&digit<'Z'&&digit!='-'&&digit!='+'&&digit!='*'&&digit!='/'&&digit<'1'&&digit>'9')
                throw new RuntimeException("Error: invalid expression: \""+digit+"\"");
        }
        while(expretion.indexOf('*')!=-1 || expretion.indexOf('/')!=-1){
            int indexOfMul = expretion.indexOf('*');
            int indexOfDiv = expretion.indexOf('/');
            if(indexOfDiv==-1 || (indexOfMul<indexOfDiv&&indexOfMul!=-1)){
                expretion = doOperate(expretion, '*', base);
            }
            else if(indexOfMul==-1 || (indexOfDiv<indexOfMul&&indexOfDiv!=-1)){
                expretion = doOperate(expretion, '/', base);
            }
        }
        while(expretion.indexOf('+')!=-1 || expretion.indexOf('-',1)!=-1){
            int indexOfAdd = expretion.indexOf('+');
            int indexOfSub = expretion.indexOf('-',1);
            if(indexOfSub==-1 || (indexOfAdd<indexOfSub&&indexOfAdd!=-1)){
                expretion = doOperate(expretion, '+', base);
            }
            else if(indexOfAdd==-1 || (indexOfSub<indexOfAdd&&indexOfSub!=-1)){
                expretion = doOperate(expretion, '-', base);
            }
        }
        return expretion.toUpperCase();
    }
    static public int convertToDecimal(int base, String num){
        try {
            int endNumIndex=-1;
            int beginNumIndex=-1;
            for(int i=0;i<num.length()&&beginNumIndex<0;i++){
                if(num.charAt(i)!=' ')
                    beginNumIndex=i;
            }
            for(int i=beginNumIndex;i<num.length();i++){
                if(num.charAt(i)==' '&&endNumIndex<0)
                    endNumIndex=i;
                if(endNumIndex>0&&num.charAt(i)!=' ')
                    throw new RuntimeException();
            }
            if(endNumIndex<0)
                endNumIndex=num.length();
            num = num.substring(beginNumIndex,endNumIndex);
            if(num.charAt(0)=='-'){
                return -Integer.parseInt(num.substring(1), base);
            }
            return Integer.parseInt(num, base);
        }
        catch (Exception e){
            throw new RuntimeException("Error: invalid expression: \""+num+"\"");//if the number out of range
        }

    }
    static public String convertToBase(int base, int num){
        if(num<0)
            return '-'+Integer.toString(num*-1, base);
        return Integer.toString(num, base);
    }
    static public String doArithmetic(String num1, String num2, char sign, int base){//get to object that represent number in base and sign and return the solution in base
        int num1_ = convertToDecimal(base, num1);
        int num2_ = convertToDecimal(base, num2);
        if(sign=='+')
            return convertToBase(base, num1_+num2_);
        if(sign=='*')
            return convertToBase(base, num1_*num2_);
        if(sign=='-')
            return convertToBase(base, num1_-num2_);
        if(sign=='/'){
            if(num2_==0)
                throw new RuntimeException("Error: trying to divide by 0 (evaluated: \"0\")");
            return convertToBase(base, num1_/num2_);
        }
        return "";
    }
    static public String doOperate(String expretion, char operate, int base){
        int index=expretion.indexOf(operate);
        if(index==-1)
            return expretion;
        int begin = index-1;
        int end = index+1;
        for(;begin>0&&expretion.charAt(begin)!='+'&&expretion.charAt(begin)!='*'&&expretion.charAt(begin)!='-'&&expretion.charAt(begin)!='/';begin--);
        if(begin!=0)
            begin++;
        for(;end<expretion.length()&&expretion.charAt(end)!='+'&&expretion.charAt(end)!='-'&&expretion.charAt(end)!='*'&&expretion.charAt(end)!='/';end++);
        String num1 = expretion.substring(begin, index);
        String num2 = expretion.substring(index+1, end);
        String newNum = doArithmetic(num1, num2, operate, base);
        String left = expretion.substring(0, begin);
        String right = "";
        if(end!=expretion.length())
            right = expretion.substring(end,expretion.length());
        if(operate=='-'&&newNum.charAt(0)=='-'&& left.length()>0)
            left = left.substring(0, left.length()-1);
        return left + newNum + right;
    }

}