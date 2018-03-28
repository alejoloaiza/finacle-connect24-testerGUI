
import org.jpos.util.*;

import java.util.ArrayList;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Random;

import org.jpos.iso.*;
import org.jpos.iso.channel.*;
import org.jpos.iso.packager.GenericPackager;
public class FinacleTesterCheque {

	public static void main(String [] args) throws Exception
	{
		
		Random randomno = new Random();
        	 System.out.print("Inicio");
     		Logger logger = new Logger ();
     		 GenericPackager packager = new GenericPackager("finacle-packager.xml");
     		ISOChannel channel = new ASCIIChannel("10.8.73.209", 22147,packager); 
     		 //ISOChannel channel = new ASCIIChannel("10.8.87.147", 22147,packager); 
     		 
     		 ((LogSource)channel).setLogger (logger, "channel");
     		 
              channel.connect();

              ISOMsg m = new ISOMsg();

              m.setPackager(packager);
              m.setMTI("1200");
              m.set(2, "0");
              m.set(3, "170000");
              m.set(4, "10000000");
              //m.set(11,"000000001118");
              m.set(11,Integer.toString(randomno.nextInt(100000000)));
              m.set(12, "20160815101010");
              m.set(17, "20160815");
              m.set(24, "200");
              m.set(32, "00000001000");
              m.set(49, "COP");
              m.set(102, "1000               99101000026     ");
              m.set(123, "AFC");
              //m.set(126, "");
              m.set(125, "P                         BTAFC                                                          ");

              channel.send(m);
             // System.out.println("PRIMER MENSAJE");
              printISOMessage(m);
              java.util.Date date= new java.util.Date();
              System.out.println(ISOUtil.hexdump(m.pack()));
//              for (int a=1;a<=100;a++){
//            	  channel.send(m);
//                  System.out.println("OTRO MENSAJE " + a);
//                  m.set(11, Integer.toString(randomno.nextInt(100000000)));
//                //  printISOMessage(m);
//              }
//             
             ISOMsg r = new ISOMsg();
              r.setPackager(packager);
             // System.out.println("RECIBIR");
              r = channel.receive();    	 
              System.out.println(ISOUtil.hexdump(r.pack()));
              printISOMessage(r);
             // Thread.sleep(60000);
        	 channel.disconnect();
        	 //java.util.Date date2= new java.util.Date();
        	 //System.out.println("Recibio: " + new Timestamp(date2.getTime()) );
        	 //System.out.println("Diferencia: " + (date2.getTime() - date.getTime()) + "ms");
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