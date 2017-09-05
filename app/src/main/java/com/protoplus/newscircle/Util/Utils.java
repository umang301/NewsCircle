package com.protoplus.newscircle.Util;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;

public class Utils {
	public static String resizeImage(String... a){
		if(a[a.length-1].equals("no")){
            return a[0];
        }
        else{
            ArrayList<String> ar=new ArrayList<String>(Arrays.asList(a));
            ar.remove(0);
            for (int i=0;i<ar.size();i++) {
                if(ar.get(i).substring(ar.get(i).length()-3,ar.get(i).length()).equalsIgnoreCase("r=G")){
                    ar.remove(i);
                    if(ar.size()>1){
                        ar.remove(0);
                    }
                    try{
                        return ar.get(0);

                    }catch(Exception e){
                        e.printStackTrace();

                        return null;
                    }
                }
            }

        }
        return null;
		
	}
	public static void CopyStream(InputStream is, OutputStream os)
    {
        final int buffer_size=1024;
        try
        {
             
            byte[] bytes=new byte[buffer_size];
            for(;;)
            {
              //Read byte from input stream
                 
              int count=is.read(bytes, 0, buffer_size);
              if(count==-1)
                  break;
               
              //Write byte from output stream
              os.write(bytes, 0, count);
            }
        }
        catch(Exception ex){}
    }
}
