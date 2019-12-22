package com.example.healthapp;

import java.util.ArrayList;

public class Database {
    public static ArrayList<SingleElement> childHealth = new ArrayList<SingleElement>(){
        {
            add(new SingleElement("healthnews/sishu", "Web Page", "https://healthnews.com.bd/category/%e0%a6%86%e0%a6%aa%e0%a6%a8%e0%a6%be%e0%a6%b0-%e0%a6%b6%e0%a6%bf%e0%a6%b6%e0%a7%81/"));
            add(new SingleElement("শিশুদের স্বাস্থ্য নিয়ে পরামর্শ,যত্ন ও খাবার রেসিপি", "Facebook", "https://www.facebook.com/%E0%A6%B6%E0%A6%BF%E0%A6%B6%E0%A7%81%E0%A6%A6%E0%A7%87%E0%A6%B0-%E0%A6%B8%E0%A7%8D%E0%A6%AC%E0%A6%BE%E0%A6%B8%E0%A7%8D%E0%A6%A5%E0%A7%8D%E0%A6%AF-%E0%A6%A8%E0%A6%BF%E0%A7%9F%E0%A7%87-%E0%A6%AA%E0%A6%B0%E0%A6%BE%E0%A6%AE%E0%A6%B0%E0%A7%8D%E0%A6%B6%E0%A6%AF%E0%A6%A4%E0%A7%8D%E0%A6%A8-%E0%A6%93-%E0%A6%96%E0%A6%BE%E0%A6%AC%E0%A6%BE%E0%A6%B0-%E0%A6%B0%E0%A7%87%E0%A6%B8%E0%A6%BF%E0%A6%AA%E0%A6%BF-937674976593734/"));
            add(new SingleElement("মা ও শিশুর স্বাস্থ্য বিষয়ক টিপস", "Facebook", "https://www.facebook.com/mombaby2016tips/"));
        }
    };

    public static ArrayList<SingleElement> pregnantHealth = new ArrayList<SingleElement>(){
        {
            add(new SingleElement("healthnews/pregnancy", "Web Page", "https://healthnews.com.bd/category/%e0%a6%aa%e0%a7%8d%e0%a6%b0%e0%a7%87%e0%a6%97%e0%a6%a8%e0%a7%87%e0%a6%a8%e0%a7%8d%e0%a6%b8%e0%a6%bf/")
            );
        }
    };

    public static ArrayList<SingleElement> nutrition = new ArrayList<SingleElement>(){
        {
            add(new SingleElement("ehaspatal", "Web Page", "http://blog.ehaspatal.com/?cat=5"));
            add(new SingleElement("healthcare/pusti", "Web Page", "https://www.sastobd.com/%e0%a6%ad%e0%a6%bf%e0%a6%9f%e0%a6%be%e0%a6%ae%e0%a6%bf%e0%a6%a8/"));
        }
    };

    public static ArrayList<SingleElement> disease = new ArrayList<SingleElement>(){
        {
            add(new SingleElement("healthnews/rogbalai", "Web Page", "https://healthnews.com.bd/category/%e0%a6%b0%e0%a7%8b%e0%a6%97%e0%a6%ac%e0%a6%be%e0%a6%b2%e0%a6%be%e0%a6%87/"));
            add(new SingleElement("ehaspatal", "Web Page", "http://blog.ehaspatal.com/?cat=3"));
        }
    };

    /*
    public static ArrayList<SingleElement> diabetes = new ArrayList<SingleElement>(){
        {
            add(new SingleElement("Doctor NDTV বাংলা/diabetes", "Web Page", "https://doctor.ndtv.com/bengali/diabetes"));
        }
    };
     */

    public static ArrayList<SingleElement> diet = new ArrayList<SingleElement>(){
        {
            add(new SingleElement("ehaspatal", "Web Page", "http://blog.ehaspatal.com/?cat=8"));
        }
    };

    /*public static ArrayList<SingleElement> cancer = new ArrayList<SingleElement>(){
        {
            add(new SingleElement("Doctor NDTV বাংলা/cancer", "Web Page", "https://doctor.ndtv.com/bengali/cancer"));
        }
    };*/

    /*public static ArrayList<SingleElement> heartDisease = new ArrayList<SingleElement>(){
        {
            add(new SingleElement("Doctor NDTV বাংলা/heart", "Web Page", "https://doctor.ndtv.com/bengali/heart"));
        }
    };*/

    /*public static ArrayList<SingleElement> sexualHealth = new ArrayList<SingleElement>(){
        {
            add(new SingleElement("Doctor NDTV বাংলা/sexual-health", "Web Page", "https://doctor.ndtv.com/bengali/sexual-health"));
        }
    };*/

    public static ArrayList<SingleElement> others = new ArrayList<SingleElement>(){
        {
            add(new SingleElement("প্রথম আলো আমার ডাক্তার", "Web Page", "https://www.prothomalo.com/lifestyle-doctor"));
            add(new SingleElement("bdnews24-health", "Web Page", "https://bangla.bdnews24.com/health/"));
            add(new SingleElement("Doctor NDTV বাংলা", "Web Page", "https://doctor.ndtv.com/bengali/news"));
            add(new SingleElement("Doctor NDTV বাংলা/diabetes", "Web Page", "https://doctor.ndtv.com/bengali/diabetes"));
            add(new SingleElement("Doctor NDTV বাংলা/cancer", "Web Page", "https://doctor.ndtv.com/bengali/cancer"));
            add(new SingleElement("Doctor NDTV বাংলা/heart", "Web Page", "https://doctor.ndtv.com/bengali/heart"));

        }
    };

}
