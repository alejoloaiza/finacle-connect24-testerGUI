
import org.jpos.util.*;

import java.util.ArrayList;
import java.sql.Timestamp;
import java.util.Date;
import org.jpos.iso.*;
import org.jpos.iso.channel.*;
import org.jpos.iso.packager.GenericPackager;
public class FinacleTesterAccountStatement {

	public static void main(String [] args) throws Exception
	{
		
      
        	 System.out.print("Inicio");
     		Logger logger = new Logger ();
     		 GenericPackager packager = new GenericPackager("finacle-packager.xml");
     		 ISOChannel channel = new ASCIIChannel("10.8.72.107", 16327,packager);
     		 
     		 ((LogSource)channel).setLogger (logger, "channel");
     		 
              channel.connect();

              ISOMsg m = new ISOMsg();

              m.setPackager(packager);

              m.setMTI("1200");
              m.set(2, "0");
              m.set(3, "370000");

              m.set(11, "67867867a8");
              m.set(12, "20150731101010");
              m.set(17, "20150731");
              m.set(24, "200");
              m.set(32, "0");

              m.set(102, "1000       BC000001");
              m.set(123, "SUC");
              m.set(125,"0125CIF006           ");
              
	 
              java.util.Date date= new java.util.Date();
              System.out.println(ISOUtil.hexdump(m.pack()));
              channel.send(m);
              ISOMsg r = new ISOMsg();
              r.setPackager(packager);
              
              r = channel.receive();    	 
              System.out.println(ISOUtil.hexdump(r.pack()));
              printISOMessage(r);
        	
        	 channel.disconnect();
        	 java.util.Date date2= new java.util.Date();
        	 System.out.println("Recibio: " + new Timestamp(date2.getTime()) );
        	 System.out.println("Diferencia: " + (date2.getTime() - date.getTime()) + "ms");
         }
     
             
      public static void printISOMessage(ISOMsg isoMsg) {
          try {
          	System.out.println("MTI = " + isoMsg.getMTI());
              //System.out.printf("MTI = %s%n", isoMsg.getMTI());
              for (int i = 1; i <= isoMsg.getMaxField(); i++) {
                  if (isoMsg.hasField(i)) {
                	  System.out.println("Field (" + i + ") = " + isoMsg.getString(i));
                      //System.out.printf("Field (%s) = %s%n", i, isoMsg.getString(i));
                  }
              }
          } catch (ISOException e) {
              e.printStackTrace();
          }

	}
}