
import org.jpos.util.*;

import java.util.ArrayList;

import org.jpos.iso.*;
import org.jpos.iso.channel.*;
import org.jpos.iso.packager.GenericPackager;
public class FinacleTesterTransfer {

	public static void main(String [] args) throws Exception
	{
		
      
        	 System.out.print("Inicio");
     		Logger logger = new Logger ();
     		 GenericPackager packager = new GenericPackager("finacle-packager.xml");
     		 ISOChannel channel = new ASCIIChannel("10.4.33.135", 13106,packager);
     		 
     		 ((LogSource)channel).setLogger (logger, "channel");
     		 
              channel.connect();

              ISOMsg m = new ISOMsg();

              m.setPackager(packager);

              m.setMTI("1200");
             m.set(2, "634522");
              m.set(3, "400000");
              m.set(4, "1400");
              m.set(11, "234234");
              m.set(12, "20150730101010");
             m.set(17, "20150730");
              m.set(24, "200");
              m.set(32, "12345678901");
              m.set(41, "CBLIB");
              m.set(49, "USD"); 
              m.set(102, "8000       PA00000180100000112");
              m.set(103, "  8000       PA00000180110000466");
              m.set(123, "002");
              m.set(124, "ATM");

              channel.send(m);	 
        	 
              channel.send(m);
              ISOMsg r = new ISOMsg();
              r.setPackager(packager);
              
              r = channel.receive();    	 
              System.out.println(ISOUtil.hexdump(r.pack()));
              printISOMessage(r);
        	
        	 channel.disconnect();
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