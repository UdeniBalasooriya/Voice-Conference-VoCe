import java.net.* ;
import java.io.FileWriter;
import java.io.IOException;

public class send{
   private final static int packetsize = 1000 ;
   int sendport;
   int receiveport;
   String sendIP;
   
   public send(int sendport,int receiveport,String sendIP,int serialNo){
	
	this.receiveport=receiveport;
	this.sendport=sendport;
    this.sendIP=sendIP;
  //try () statement to close socket without finally
	try(DatagramSocket sendsocket = new DatagramSocket( sendport ) ;){
         InetAddress host = InetAddress.getByName( sendIP ) ;
		 
            try{
			 DatagramPacket sendpacket = new DatagramPacket( Record.tempBuffer, packetsize,host,receiveport ) ;//recoverable
             sendsocket.send( sendpacket ) ;
             //System.out.println("sent:"+ sendpacket.getAddress() + " " + sendpacket.getPort()) ;
			 //write data to the txt file
			 (Record.writer).write(System.currentTimeMillis()+" "+serialNo+"\r\n") ;
			} catch( Exception e1 ){System.out.println( "E1"+e1 ) ;}
				
	 }
     catch( Exception e ){System.out.println( "E2"+e ) ; }  
	  
  }
}