
import org.jpos.util.*;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Random;

import org.jpos.iso.*;
import org.jpos.iso.channel.*;
import org.jpos.iso.packager.GenericPackager;
public class FinacleTesterDebit {
	public GenericPackager packager ;
	public ISOMsg m = new ISOMsg();
	
	public FinacleTesterDebit(){
		//System.out.print("Inicio Constructor");
	}
	
	public ArrayList FinacleTestDebitPrepare(String Field2,String Field3,String Field4,String Field11,String Field12,String Field17,String Field24,String Field32,String Field49,String Field102,String Field103,String Field123,String Field125,boolean chkField125) throws Exception {
		Random randomno = new Random();
		packager = new GenericPackager("finacle-packager.xml");
		m.setPackager(packager);
        m.setMTI("1200");
        m.set(2, Field2);
        m.set(3, Field3);
        m.set(4, Field4);
        if (Field11.equals("<auto>")){
        	Field11 = Integer.toString(randomno.nextInt(100000000));
        }
        m.set(11, Field11);
        m.set(12, Field12);
        m.set(17, Field17);
        m.set(24, Field24);
        m.set(32, Field32);
        //m.set(41, Field41);
        m.set(49, Field49);
        m.set(102, Field102);
     //   m.set(103, Field103);
        m.set(123, Field123);
        //m.set(124, Field124);
        m.set(125,Field125);
        return printISOMessage(m);
	}
	public ArrayList FinacleTestDebitConnect(String server, int port) throws Exception  {
		//System.out.print("Inicio");
		Logger logger = new Logger ();

		 ISOChannel channel = new ASCIIChannel(server, port,packager);
		 ((LogSource)channel).setLogger (logger, "channel");
         channel.connect();
         java.util.Date date= new java.util.Date();
    	 //System.out.println("Envio: " + new Timestamp(date.getTime()));
    	 //System.out.println(ISOUtil.hexdump(m.pack()));
         channel.send(m);
         ISOMsg r = new ISOMsg();
         r.setPackager(packager);
         
         r = channel.receive();
         
          channel.disconnect();
          java.util.Date date2= new java.util.Date();
        //  System.out.println("Recibio: " + new Timestamp(date2.getTime()) );
          System.out.println((date2.getTime() - date.getTime()));
         // System.out.println(ISOUtil.hexdump(r.pack()));
          return printISOMessage(r);
	}
	public ArrayList printISOMessage(ISOMsg isoMsg) {
		ArrayList txtReturn = new ArrayList();
        try {
        	txtReturn.add("MTI = " + isoMsg.getMTI());
          //  System.out.printf("MTI = %s%n", isoMsg.getMTI());
            for (int i = 1; i <= isoMsg.getMaxField(); i++) {
                if (isoMsg.hasField(i)) {
                	txtReturn.add("Field (" + i + ") = " + isoMsg.getString(i));
                    //System.out.printf("Field (%s) = %s%n", i, isoMsg.getString(i));
                }
            }
            return txtReturn;
        } catch (ISOException e) {
            e.printStackTrace();
            return txtReturn;
        }
    }
}